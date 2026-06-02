package com.swill.killaura;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;

import java.util.List;
import java.util.Random;

public class KillAuraModule {
    private boolean enabled = false;
    private long lastAttack = 0;
    private final Random random = new Random();
    private final MinecraftClient mc = MinecraftClient.getInstance();

    public void toggle() {
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void onTick() {
        if (mc.player == null || mc.world == null) return;
        if (mc.player.isDead()) return;

        Box box = mc.player.getBoundingBox().expand(4.5);
        List<LivingEntity> targets = mc.world.getEntitiesByClass(LivingEntity.class, box, entity -> {
            if (entity == mc.player) return false;
            if (entity.isDead()) return false;
            if (entity.getHealth() <= 0) return false;
            return true;
        });

        if (targets.isEmpty()) return;

        LivingEntity target = targets.get(0);
        for (LivingEntity t : targets) {
            if (t.getHealth() < target.getHealth()) {
                target = t;
            }
        }

        long now = System.currentTimeMillis();
        int delay = 50 + random.nextInt(100);

        if (now - lastAttack >= delay) {
            mc.interactionManager.attackEntity(mc.player, target);
            mc.player.swingHand(Hand.MAIN_HAND);
            lastAttack = now;
        }
    }
}
