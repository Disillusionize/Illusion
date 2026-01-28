package org.example.illusion.feature.module.impl.visuals;

import io.github.nevalackin.radbus.Listen;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.impl.client.ModuleDisabledEvent;
import org.example.illusion.event.impl.client.ModuleEnabledEvent;
import org.example.illusion.event.impl.render.Render2DEvent;
import org.example.illusion.feature.screen.api.setting.impl.CheckSetting;
import org.example.illusion.feature.screen.api.setting.impl.ComboSetting;
import org.example.illusion.feature.screen.impl.Theme;
import org.example.illusion.feature.module.api.Category;
import org.example.illusion.feature.module.api.Module;
import org.example.illusion.feature.module.api.ModuleInfo;
import org.example.illusion.util.ColorUtils;
import org.example.illusion.util.FontUtils;

import java.util.ArrayList;
import java.util.Comparator;

@ModuleInfo(name = "ModOverlay", category = Category.VISUALS)
public class ModOverlay extends Module {
    private ArrayList<Module> enabled;

    private final ComboSetting sorting = new ComboSetting("Sorting", this, new String[]{"Length", "Alphabetical"});
    private final CheckSetting hideVisuals = new CheckSetting("Hide Visuals", this, true);

    public ModOverlay() {
        addSetting(sorting);
        addSetting(hideVisuals);
    }

    @Override
    public void onEnable() {
        refresh();
    }

    @Listen
    public void onRender2D(Render2DEvent event) {
        int y = 5;

        for (Module module : enabled) {
            FontUtils.drawString(module.getName(), 5, y, ColorUtils.gradient(Theme.getMainColor(), y).getRGB());
            y += FontUtils.HEIGHT + 1;
        }
    }

    @Listen
    public void onModuleEnabled(ModuleEnabledEvent event) {
        refresh();
    }

    @Listen
    public void onModuleDisabled(ModuleDisabledEvent event) {
        refresh();
    }

    @Override
    public void onUpdate() {
        refresh();
    }

    private void refresh() {
        enabled = new ArrayList<>();

        IllusionClient.getInstance()
                .getModuleManager().getElements()
                .forEach(module -> {
                    if (!module.isEnabled()) return;
                    if (hideVisuals.isEnabled() && module.getCategory().equals(Category.VISUALS)) return;

                    enabled.add(module);
                });

        sort();
    }

    private void sort() {
        if (sorting.getValue().equals("Length")) {
            enabled.sort(Comparator.comparing(it -> FontUtils.length(it.getName())));
        } else {
            enabled.sort(Comparator.comparing(Module::getName));
        }
    }
}
