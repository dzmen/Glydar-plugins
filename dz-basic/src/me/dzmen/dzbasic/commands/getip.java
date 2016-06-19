package me.dzmen.dzbasic.commands;

import me.dzmen.dzbasic.command_help;

import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

public class getip extends command_help implements CommandSet {

	@Command(name = "getip", usage = "<player> - Get the ip from a player", permission = "dzbasic.getip", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender, String playerName) {

		Player player = getPlayer(playerName);
		if (player != null) {
			String ip = player.getIp();
			sender.sendMessage("[DZBASIC]IP from " + playerName + " is:" + ip);
			return CommandOutcome.SUCCESS;
		} else {
			sender.sendMessage("[DZBASIC]" + playerName + " not found.");
			return CommandOutcome.SUCCESS;
		}
	}
}