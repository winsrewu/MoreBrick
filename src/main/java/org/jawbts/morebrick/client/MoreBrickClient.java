package org.jawbts.morebrick.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jawbts.morebrick.MoreBrick;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class MoreBrickClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_WORLD_TICK.register((world) -> {
            if (MinecraftClient.getInstance().player!= null) {
                ClientPlayerEntity player = MinecraftClient.getInstance().player;
                if (player.getActiveStatusEffects().containsKey(MoreBrick.BLUDGEONED)
                        && Objects.requireNonNull(player.getStatusEffect(MoreBrick.BLUDGEONED)).getAmplifier() >= 2
                        && !(MinecraftClient.getInstance().currentScreen instanceof BlackScreen)) {
                    MinecraftClient.getInstance().setScreen(new BlackScreen());
                }
                if (!player.getActiveStatusEffects().containsKey(MoreBrick.BLUDGEONED)
                        && MinecraftClient.getInstance().currentScreen instanceof BlackScreen) {
                    MinecraftClient.getInstance().setScreen(null);
                }
            }
        });
    }
}
