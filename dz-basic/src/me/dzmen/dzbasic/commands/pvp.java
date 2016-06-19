package me.dzmen.dzbasic.commands;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;
import me.dzmen.dzbasic.command_help;

public class pvp extends command_help implements CommandSet {

	@Command(name = "pvp", usage = "- enable or disable pvp", permission = "dzbasic.pvp", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender) {
		if (ParaGlydar.getServer().getDefaultWorld().isPVPAllowed() == false) {
			ParaGlydar.getServer().getDefaultWorld().setPVPAllowed(true);
			sender.sendMessage("[DZBASIC]PVP is now enabled!");
			return CommandOutcome.SUCCESS;
		} else {
			ParaGlydar.getServer().getDefaultWorld().setPVPAllowed(false);
			sender.sendMessage("[DZBASIC]PVP is now disabled!");
			return CommandOutcome.SUCCESS;
		}
	}

}