package com.JumpReset.JumpReset;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.Random;
import net.minecraftforge.common.MinecraftForge;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraftforge.client.ClientCommandHandler;

@Mod(modid = "sumoclient", name = "SumoClient", version = "1.0", acceptedMinecraftVersions = "[1.8.9]", clientSideOnly = true)
public class SumoClient {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new SumoClientToggleCommand());
    }
    private static final Random random = new Random();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    Minecraft mc = Minecraft.getMinecraft();
    @SubscribeEvent
    public void onDamage(TickEvent.PlayerTickEvent e) {
        if (SumoClientToggleCommand.sumoclientToggled && mc.thePlayer.hurtTime > 0 && mc.thePlayer.onGround) {
            if (random.nextDouble() < 0.4) {
                int value = random.nextInt(5) + 1;
                KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), true);
                executor.schedule(() -> {
                    KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), false);
                }, value, TimeUnit.MILLISECONDS);
            }
        }
    }
    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        if (event.target instanceof EntityPlayer && SumoClientToggleCommand.sumoclientToggled) {
            int wait = this.random.nextInt(30) + 10;
            executor.schedule(() -> mc.thePlayer.setSprinting(true), wait, TimeUnit.MILLISECONDS);
        }
    }
}



