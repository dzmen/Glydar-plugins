package me.dzmen.dzbasic.commands;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;
import me.dzmen.dzbasic.command_help;
import me.dzmen.dzbasic.main;

public class pass extends command_help implements CommandSet {

	private main plugin;

	public pass(main plugin) {
		this.plugin = plugin;
	}

	@Command(name = "pass", usage = "<password> - Enable admin", permission = "dzbasic.pass", permissionDefault = PermissionDefault.TRUE)
	public CommandOutcome execute(CommandSender sender, String passwd) {

		Player player = getPlayer(sender);
		String pass = encrypt(passwd);

		if (plugin.adminlist.contains(player.getName())) {
			if (plugin.adminlist.getString(player.getName()).equals(pass)) {
				ParaGlydar.getLogger().info("[DZBASIC]" + player.getName() + " is now admin");
				sender.sendMessage("[DZBASIC]You are now admin :)!");
				player.setAdmin(true);
				return CommandOutcome.SUCCESS;
			} else {
				sender.sendMessage("[DZBASIC]Wrong password!");
				return CommandOutcome.SUCCESS;
			}
		} else {
			sender.sendMessage("[DZBASIC]You dont have admin rights!");
			return CommandOutcome.SUCCESS;
		}

	}

}