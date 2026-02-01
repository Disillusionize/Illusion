package org.example.illusion.feature.config;

import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;
import org.example.illusion.IllusionClient;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.screen.api.setting.api.Setting;
import org.example.illusion.feature.screen.api.setting.impl.CheckSetting;
import org.example.illusion.feature.screen.api.setting.impl.ComboSetting;
import org.example.illusion.feature.screen.api.setting.impl.SliderSetting;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class ConfigManager {

    private final File configDir;
    private final File configFile;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ConfigManager() {
        configDir = FabricLoader.getInstance().getConfigDir().resolve(IllusionClient.ID).toFile();
        configFile = new File(configDir, "settings.json");
    }

    public void saveConfig() {
        if (!configDir.exists()) {
            configDir.mkdirs();
        }

        JsonObject configObject = new JsonObject();
        JsonArray modulesArray = new JsonArray();

        for (Module module : IllusionClient.getInstance().getModuleManager().getElements()) {
            JsonObject moduleObject = new JsonObject();
            moduleObject.addProperty("name", module.getName());
            moduleObject.addProperty("enabled", module.isEnabled());
            moduleObject.addProperty("bind", module.getBind());

            JsonArray settingsArray = getJsonElements(module);
            moduleObject.add("settings", settingsArray);
            modulesArray.add(moduleObject);
        }

        configObject.add("modules", modulesArray);

        try (Writer writer = new FileWriter(configFile)) {
            gson.toJson(configObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static @NotNull JsonArray getJsonElements(Module module) {
        JsonArray settingsArray = new JsonArray();
        for (Setting setting : module.getSettings()) {
            JsonObject settingObject = new JsonObject();
            settingObject.addProperty("name", setting.getName());

            if (setting instanceof CheckSetting) {
                settingObject.addProperty("value", ((CheckSetting) setting).isEnabled());
            } else if (setting instanceof SliderSetting) {
                settingObject.addProperty("value", ((SliderSetting) setting).getValue());
            } else if (setting instanceof ComboSetting) {
                settingObject.addProperty("value", ((ComboSetting) setting).getValue());
            }
            settingsArray.add(settingObject);
        }
        return settingsArray;
    }

    public void loadConfig() {
        if (!configFile.exists()) {
            return;
        }

        try (Reader reader = new FileReader(configFile)) {
            JsonParser parser = new JsonParser();
            JsonObject configObject = parser.parse(reader).getAsJsonObject();
            if (configObject.has("modules")) {
                JsonArray modulesArray = configObject.getAsJsonArray("modules");

                for (JsonElement moduleElement : modulesArray) {
                    JsonObject moduleObject = moduleElement.getAsJsonObject();
                    String moduleName = moduleObject.get("name").getAsString();
                    Module module = IllusionClient.getInstance().getModuleManager().getElement(moduleName);

                    if (module != null) {
                        if (moduleObject.has("enabled")) {
                            if (!module.getName().equalsIgnoreCase("clickgui")) {
                                module.setEnabled(moduleObject.get("enabled").getAsBoolean());
                            }
                        }
                        if (moduleObject.has("bind")) {
                            module.setBind(moduleObject.get("bind").getAsInt());
                        }

                        if (moduleObject.has("settings")) {
                            JsonArray settingsArray = moduleObject.getAsJsonArray("settings");
                            for (JsonElement settingElement : settingsArray) {
                                JsonObject settingObject = settingElement.getAsJsonObject();
                                String settingName = settingObject.get("name").getAsString();
                                Setting setting = getSettingByName(module, settingName);

                                if (setting != null && settingObject.has("value")) {
                                    JsonElement valueElement = settingObject.get("value");
                                    if (setting instanceof CheckSetting) {
                                        ((CheckSetting) setting).setEnabled(valueElement.getAsBoolean());
                                    } else if (setting instanceof SliderSetting) {
                                        ((SliderSetting) setting).setValue(valueElement.getAsFloat());
                                    } else if (setting instanceof ComboSetting) {
                                        ((ComboSetting) setting).setValue(valueElement.getAsString());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Setting getSettingByName(Module module, String name) {
        for (Setting setting : module.getSettings()) {
            if (setting.getName().equalsIgnoreCase(name)) {
                return setting;
            }
        }
        return null;
    }
}
