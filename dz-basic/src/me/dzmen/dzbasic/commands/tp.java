package me.dzmen.dzbasic.commands;

import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.geom.LongVector3;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;
import me.dzmen.dzbasic.command_help;

// This file is temporary disabled :(
public class tp extends command_help implements CommandSet {

	@Command(name = "tp", usage = "<player> <player> - Teleport player to player", permission = "dzbasic.tp", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender, String playerName1,
			String playerName2) {

		Player player1 = getPlayer(playerName1);
		Player player2 = getPlayer(playerName2);

		LongVector3 pos = player2.getEntityData().getPosition();

		if (player1 != null & player2 != null) {
			player1.getEntityData().setPosition(pos);
			// To do: Get this working
		} else {
			sender.sendMessage("[dzbasic]Can't find a player");
		}
		return CommandOutcome.SUCCESS;
	}

}