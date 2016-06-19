package me.dzmen.dzbasic.commands;

import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;
import me.dzmen.dzbasic.command_help;

public class msg extends command_help implements CommandSet {

	@Command(name = "msg", usage = "<player> [words] - Send a message to a player", permission = "dzbasic.msg", permissionDefault = PermissionDefault.TRUE)
	public CommandOutcome execute(CommandSender sender, String playerName,
			String[] args) {
		Player player = getPlayer(playerName);
		if (player != null) {
			String message = "";
			if (args.length > 1) {
				message = createString(args, 0);
			}
			player.sendMessageToPlayer("[" + sender.getName() + " -> me] "
					+ message);
			return CommandOutcome.SUCCESS;
		} else {
			sender.sendMessage("[DZBASIC]" + playerName + " not found.");
			return CommandOutcome.SUCCESS;
		}

	}
}