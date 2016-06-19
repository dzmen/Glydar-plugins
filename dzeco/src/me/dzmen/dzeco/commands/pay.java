package me.dzmen.dzeco.commands;

import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.configuration.ConfigurationSection;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

import me.dzmen.dzeco.main;

public class pay implements CommandSet {

	private main plugin;

	public pay(main plugin) {
		this.plugin = plugin;
	}

	@Command(name = "pay", usage = "<player> [money] - Pay someone (must be online!)", permission = "dzeco.pay", permissionDefault = PermissionDefault.TRUE)
	public CommandOutcome execute(CommandSender sender, String playerName,
			String money) throws Exception {
		String regex = "[0-9]+";
		Player player = plugin.getPlayer(playerName);
		// Check if config contains playerName
		if (player != null) {
			// Check if money only contains numbers
			if (money.matches(regex)) {
				String p1m = plugin.Ecolist.getString("users." + playerName + ".money");
				String p2m = plugin.Ecolist.getString("users." + sender.getName() + ".money");
				
				int num1 = Integer.parseInt(p2m);
				int num2 = Integer.parseInt(money);
				int p2m_total = num1 - num2;
				if (p2m_total > 0){
					// Add money to playerName
					int num3 = Integer.parseInt(p1m);
					int num4 = Integer.parseInt(money);
					int p1m_total = num3 + num4;
					String p1m_current = Integer.toString(p1m_total);
					plugin.Ecolist.set("users." + playerName + ".money", p1m_current);
					
					// Remove money from sender
					String p2m_current = Integer.toString(p2m_total);
					plugin.Ecolist.set("users." + sender.getName() + ".money", p2m_current);
					plugin.eco_save();
					
					//Message's
					String valuta = plugin.Ecolist.getString("valuta");
					sender.sendMessage("[DZECO]You sended " + valuta + money + " to " + playerName);
					player.sendMessage("[DZECO]You received " + valuta + money + " from " + sender.getName());
					return CommandOutcome.SUCCESS;
				}else {
					sender.sendMessage("[DZECO]You have not enough money!");
					return CommandOutcome.SUCCESS;
				}
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