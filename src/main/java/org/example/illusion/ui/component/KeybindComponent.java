package org.example.illusion.ui.component;

import org.example.illusion.module.Module;
import org.example.illusion.module.impl.ClickGuiModule;
import org.example.illusion.ui.menu.Theme;
import org.lwjgl.input.Keyboard;

public class KeybindComponent extends Widget {
    private final Module module;
    private boolean binding;

    public KeybindComponent(ModuleComponent parent, Module module, int offset) {
        super(parent, offset);
        this.module = module;
    }

    @Override
    public void renderComponent() {
        drawBackground();
        String text = binding ? "Press a key..." : "Key: " + Keyboard.getKeyName(module.getBind());
        drawLabel(text, 7, hovered ? Theme.getMainColor().getRGB() : -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (hovered && button == 0 && parent.open) binding = true;
    }

    @Override
    public void keyTyped(char typedChar, int key) {
        if (binding) {
            if (key == Keyboard.KEY_ESCAPE) {
                key = (module instanceof ClickGuiModule) ? Keyboard.KEY_RSHIFT : Keyboard.KEY_NONE;
            }
            module.setBind(key);
            binding = false;
        }
    }
}
