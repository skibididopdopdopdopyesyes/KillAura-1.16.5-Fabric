package com.swill.killaura;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KillAuraMod implements ClientModInitializer {
    public static KillAuraModule killAura = new KillAuraModule();
    public static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        toggleKey = new KeyBinding(
            "key.killaura.toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_R,
            "category.killaura"
        );
        // Регистрация через Fabric API всё равно нужна, но её нет.
        // Поэтому лучше использовать KeyBindingHelper (требует Fabric API).
    }
}
