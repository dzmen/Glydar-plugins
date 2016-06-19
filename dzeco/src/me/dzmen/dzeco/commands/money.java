package me.dzmen.dzeco.commands;

import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

import me.dzmen.dzeco.main;

public class money implements CommandSet {

	private main plugin;
	
	public money(main plugin){
		this.plugin = plugin;
	}

	@Command(name = "money", usage = " - See your balance", permission = "dzeco.money", permissionDefault = PermissionDefault.TRUE)
	public CommandOutcome execute(CommandSender sender) {
		String money = plugin.Ecolist.getString("users." + sender.getName() + ".money");
		String valuta = plugin.Ecolist.getString("valuta");
		sender.sendMessage("[DZECO]Balance: " + valuta + money);			
		return CommandOutcome.SUCCESS;
	}
	
}