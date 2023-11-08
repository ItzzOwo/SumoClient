package com.JumpReset.JumpReset;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class SumoClientToggleCommand extends CommandBase {
    public static boolean sumoclientToggled = false;
    public String getCommandName() {
        return "sumoclient";
    }
    public void processCommand(ICommandSender sender, String[] args) {
        SumoClientToggleCommand.sumoclientToggled = !SumoClientToggleCommand.sumoclientToggled;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Sumo Client: "+(SumoClientToggleCommand.sumoclientToggled ? "§aEnabled" : "§cDisabled")));
    }
    public String getCommandUsage(ICommandSender sender) {
        return "/sumoclient";
    }
    public int getRequiredPermissionLevel() {
        return 0;
    }
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
