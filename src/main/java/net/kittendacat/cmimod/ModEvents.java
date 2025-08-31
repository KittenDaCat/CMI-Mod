package net.kittendacat.cmimod;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.MinecraftServer;

public class ModEvents {
    private static int tickCounter = 0;
    private static final int TICK_INTERVAL = 10; // Check every 0.5 seconds (at 20 TPS)

    public static void registerEvents() {
        ServerTickEvents.END_SERVER_TICK.register(ModEvents::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        if (++tickCounter >= TICK_INTERVAL) {
            tickCounter = 0;
            for (PlayerEntity player : server.getPlayerManager().getPlayerList()) {
                StatusEffectInstance poison = player.getStatusEffect(StatusEffects.POISON);
                StatusEffectInstance oozing = player.getStatusEffect(StatusEffects.OOZING);
                StatusEffectInstance nausea = player.getStatusEffect(StatusEffects.NAUSEA);

                CMIMod.LOGGER.info("check");

                if (poison != null && oozing != null && nausea != null && player.getHealth() <= 1.0F) {
                    player.damage(player.getDamageSources().generic(), 1.0F);
                }
            }
        }
    }
}