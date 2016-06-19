package me.dzmen.dzbasic.commands;

import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;
import me.dzmen.dzbasic.command_help;

public class help extends command_help implements CommandSet {

	@Command(name = "dzbasic", usage = "- Get all the dzbasic commands", permission = "dzbasic.help", permissionDefault = PermissionDefault.TRUE)
	public CommandOutcome execute(CommandSender sender, String[] args) {
		sender.sendMessage("---------[DZBASIC HELP]----------");
		sender.sendMessage("/msg to send message to a player");
		sender.sendMessage("/dzbasic to see this text");
		sender.sendMessage("-----------ADMIN ONLY------------");
		sender.sendMessage("/getip to get ip from player");
		sender.sendMessage("/kick to kick a player");
		sender.sendMessage("/say to broadcast a message");
		sender.sendMessage("/pvp to enable pvp");
		sender.sendMessage("/admin to make someone admin");
		sender.sendMessage("/quit to stop the server");
		return CommandOutcome.SUCCESS;
	}

}