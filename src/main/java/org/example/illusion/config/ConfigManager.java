package org.example.illusion.config;

import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.illusion.IllusionClient;
import org.example.illusion.module.Module;
import org.example.illusion.setting.Setting;

import java.io.*;

public class ConfigManager {

    private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);

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

            JsonArray settingsArray = new JsonArray();
            for (Setting setting : module.getSettings()) {
                settingsArray.add(setting.toJson());
            }
            moduleObject.add("settings", settingsArray);
            modulesArray.add(moduleObject);
        }

        configObject.add("modules", modulesArray);

        try (Writer writer = new FileWriter(configFile)) {
            gson.toJson(configObject, writer);
        } catch (IOException e) {
            LOGGER.error("Failed to save configuration to {}", configFile.getAbsolutePath(), e);
        }
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
                                Setting setting = module.getSetting(settingName);

                                if (setting != null && settingObject.has("value")) {
                                    setting.fromJson(settingObject.get("value"));
                                }
                            }
                        }
                    } else {
                        LOGGER.warn("Failed to find module {}", moduleName);
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Failed to load configuration from {}", configFile.getAbsolutePath(), e);
        }
    }
}
