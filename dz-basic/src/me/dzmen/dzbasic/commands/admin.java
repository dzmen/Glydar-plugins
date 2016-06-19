package me.dzmen.dzbasic.commands;


import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;
import me.dzmen.dzbasic.command_help;
import me.dzmen.dzbasic.main;

public class admin extends command_help implements CommandSet {

	private main plugin;

	public admin(main plugin) {
		this.plugin = plugin;
	}

	@Command(name = "admin", usage = "<player> <password> - Make a player admin, you need password on game join", permission = "dzbasic.admin", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender, String playerName, String passwd){

		Player player = getPlayer(playerName);
		String pass = encrypt(passwd);
		
		if (player != null) {
			if (player.isAdmin() == false) {
				player.setAdmin(true);
				plugin.addAdmin(player.getName(), pass);
				ParaGlydar.getLogger().info("[DZBASIC]" + player.getName() + " is now admin");
				player.sendMessage("[DZBASIC]You are now admin!");
				return CommandOutcome.SUCCESS;
			} else {
				player.setAdmin(false);
				plugin.removeAdmin(playerName);
				ParaGlydar.getLogger().info("[DZBASIC]" + player.getName() + " is removed from admin");
				player.sendMessage("[DZBASIC]Admin status has been removed!");
				return CommandOutcome.SUCCESS;
			}
		} else {
			sender.sendMessage("[DZBASIC]" + playerName + " not found. Player must be online to make admin!");
			return CommandOutcome.SUCCESS;
		}

	}

}