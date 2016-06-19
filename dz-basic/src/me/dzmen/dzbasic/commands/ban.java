package me.dzmen.dzbasic.commands;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

import me.dzmen.dzbasic.command_help;
import me.dzmen.dzbasic.main;

public class ban extends command_help implements CommandSet {

	private main plugin;
	
	public ban(main plugin){
		this.plugin = plugin;
	}
		
	@Command(name = "ban", usage = "<player> - Ban a player", permission = "dzbasic.ban", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender, String playerName) {

		Player player = getPlayer(playerName);

		if (player != null) {
			try {
			plugin.addBan(player.getIp());
			} catch (Throwable any) {
                System.out.println("Java ERROR: "+any);
                //any.printStackTrace();
			}
			
			player.kickPlayer("[DZBASIC]Your are now BANNED!");
			ParaGlydar.getServer().broadcastMessage(
					"[DZBASIC]Banned player " + player.getName());
			return CommandOutcome.SUCCESS;
		} else {
			sender.sendMessage("[DZBASIC]" + playerName + " not found.");
			return CommandOutcome.SUCCESS;
		}
	}

}