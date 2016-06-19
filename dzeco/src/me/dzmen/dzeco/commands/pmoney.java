package me.dzmen.dzeco.commands;

import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.configuration.ConfigurationSection;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

import me.dzmen.dzeco.main;

public class pmoney implements CommandSet {

	private main plugin;
	
	public pmoney(main plugin){
		this.plugin = plugin;
	}

	@Command(name = "pmoney", usage = "<player> - See there balance", permission = "dzeco.pmoney", permissionDefault = PermissionDefault.TRUE)
	public CommandOutcome execute(CommandSender sender, String playerName) {
		ConfigurationSection users = plugin.Ecolist.getConfigurationSection("users");
		if (users.contains(playerName)) {
		String pmoney = plugin.Ecolist.getString("users." + playerName + ".money");
		String valuta = plugin.Ecolist.getString("valuta");
		sender.sendMessage("[DZECO]" + playerName + " balance: " + valuta + pmoney);			
		return CommandOutcome.SUCCESS;
		} else {
			sender.sendMessage("[DZECO]Player not found!");
			return CommandOutcome.SUCCESS;
		}
	}

}