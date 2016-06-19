package me.dzmen.dzeco.commands;

import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.configuration.ConfigurationSection;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

import me.dzmen.dzeco.main;

public class mtake implements CommandSet {

	private main plugin;

	public mtake(main plugin) {
		this.plugin = plugin;
	}

	// Same as mgive :D!
	//only different: "int total = num1 - num2"
	@Command(name = "mtake", usage = "<player> [money] - Take someone's money", permission = "dzeco.mtake", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender, String playerName,
			String money) throws Exception {
		String regex = "[0-9]+";
		ConfigurationSection users = plugin.Ecolist.getConfigurationSection("users");
		
		// Check if config contains playerName
		if (users.contains(playerName)) {
			// Check if money only contains numbers
			if (money.matches(regex)) {
				String current_money = plugin.Ecolist.getString("users." + playerName + ".money");
				int num1 = Integer.parseInt(current_money);
				int num2 = Integer.parseInt(money);
				int total = num1 - num2;
				String new_money = Integer.toString(total);
				plugin.Ecolist.set("users." + playerName + ".money", new_money);
				String valuta = plugin.Ecolist.getString("valuta");
				sender.sendMessage("[DZECO]You sended " + valuta + new_money + " to " + playerName);
				plugin.eco_save();
				return CommandOutcome.SUCCESS;
			} else {
				sender.sendMessage("[DZECO]Money can only be numbers!");
				return CommandOutcome.SUCCESS;
			}
		} else {
			sender.sendMessage("[DZECO]Player not found!");
			return CommandOutcome.SUCCESS;
		}
	}
}