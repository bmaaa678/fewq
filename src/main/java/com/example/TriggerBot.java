package com.example.triggerbot;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.EntityHitResult;

public class TriggerBot implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.crosshairTarget instanceof EntityHitResult entityHit) {
                Entity target = entityHit.getEntity();
                ClientPlayerEntity player = client.player;

                if (target != null && player != null && player.getAttackCooldownProgress(0.0F) >= 1.0F) {
                    client.interactionManager.attackEntity(player, target);
                    player.swingHand(player.getActiveHand());
                }
            }
        });
    }
}
