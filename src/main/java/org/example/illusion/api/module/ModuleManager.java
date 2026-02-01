package org.example.illusion.api.module;

import io.github.nevalackin.radbus.Listen;
import org.example.illusion.IllusionClient;
import org.example.illusion.event.input.KeyPressEvent;
import org.example.illusion.api.Manager;
import org.example.illusion.module.client.ClickGuiModule;
import org.example.illusion.module.client.TestModule;

import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager extends Manager<Module> {
    public ModuleManager() {
        super(
                new ClickGuiModule(),
                new TestModule()
        );

        IllusionClient.getInstance().getEventBus().subscribe(this);
    }

    public final List<Module> getElements(Category category) {
        return elements.stream()
                .filter(element -> element.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Listen
    public void keyPresses(KeyPressEvent event) {
        if (event.getKey() == 0 || !event.isDown()) return;

        this.getElements().forEach(module -> {
            if (module.getBind() == event.getKey()) module.toggle();
        });
    }
}
