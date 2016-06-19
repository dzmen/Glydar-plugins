package me.dzmen.dzbasic.commands;

import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;
import me.dzmen.dzbasic.command_help;

public class kill extends command_help implements CommandSet {

	@Command(name = "kill", usage = "<player> - Kill a player", permission = "dzbasic.kill", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender, String playerName) {

		Player player = getPlayer(playerName);
		if (player != null) {
			player.kill();
			player.sendMessage("[DZBASIC]You have been command killed!");
			return CommandOutcome.SUCCESS;
		} else {
			sender.sendMessage("[DZBASIC]" + playerName + " not found.");
			return CommandOutcome.SUCCESS;
		}
	}

}