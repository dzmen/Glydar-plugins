package me.dzmen.dzbasic.commands;

import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;
import me.dzmen.dzbasic.command_help;

public class heal extends command_help implements CommandSet {

	@Command(name = "heal", usage = "<player> - Heal a player", permission = "dzbasic.heal", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender, String playerName) {

		Player player = getPlayer(playerName);
		if (player != null) {
			float heal = 1;
			player.setHealth(heal);
			player.sendMessage("[DZBASIC]You have been healed!");
			return CommandOutcome.SUCCESS;
		} else {
			sender.sendMessage("[DZBASIC]" + playerName + " not found.");
			return CommandOutcome.SUCCESS;
		}
	}
}