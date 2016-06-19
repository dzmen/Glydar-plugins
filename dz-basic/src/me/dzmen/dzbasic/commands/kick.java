package me.dzmen.dzbasic.commands;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;
import me.dzmen.dzbasic.command_help;

public class kick extends command_help implements CommandSet {

	@Command(name = "kick", usage = "<player> [reason] - Kick a player", permission = "dzbasic.kick", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender, String playerName,
			String[] args) {

		Player player = getPlayer(playerName);
		if (player != null) {
			String reason = "Kicked by an operator!";
			if (args.length > 1) {
				reason = createString(args, 0);
			}
			player.kickPlayer("KICK: " + reason);
			ParaGlydar.getServer().broadcastMessage(
					"Kicked player " + player.getName()
							+ ". With reason:\n" + reason); 
			return CommandOutcome.SUCCESS;
		} else {
			sender.sendMessage("[DZBASIC]" + playerName + " not found.");
			return CommandOutcome.SUCCESS;
		}
	}

}