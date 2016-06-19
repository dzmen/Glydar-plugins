package me.dzmen.dzbasic.commands;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

import me.dzmen.dzbasic.command_help;

public class quit extends command_help implements CommandSet {

	// Will add Myrninvollo idea later ;), It is still not working :/
	@Command(name = "quit", usage = " - Stop the server", permission = "dzbasic.quit", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender) {
		ParaGlydar.getServer().shutdown();
		return CommandOutcome.SUCCESS;
	}

}