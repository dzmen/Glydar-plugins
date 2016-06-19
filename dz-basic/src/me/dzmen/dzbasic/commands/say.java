package me.dzmen.dzbasic.commands;

import me.dzmen.dzbasic.command_help;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

public class say extends command_help implements CommandSet {

	@Command(name = "say", usage = "[words] - Broadcast a message", permission = "dzbasic.say", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender, String[] args) {
		String message = createString(args, 0);
		ParaGlydar.getServer().broadcastMessage("[SERVER]" + message);
		return CommandOutcome.SUCCESS;
	}
}