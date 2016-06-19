package me.dzmen.dzbasic.commands;

import java.util.Set;
import org.glydar.paraglydar.command.*;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

import me.dzmen.dzbasic.command_help;
import me.dzmen.dzbasic.main;

public class unban extends command_help implements CommandSet {

	private main plugin;
	
	public unban(main plugin){
		this.plugin = plugin;
	}

	@Command(name = "unban", usage = "<ip> - Unban a IP", permission = "dzbasic.unban", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome execute(CommandSender sender, String ip) {

		Set<String> list = plugin.getBans();

		if (list.contains(ip)) {
			try {
				plugin.removeBan(ip);
			} catch (Throwable any) {
				System.out.println("Java ERROR: " + any);
				// any.printStackTrace();
			}
			sender.sendMessage("[DZBASIC]" + ip + " is now unbanned!");
			return CommandOutcome.SUCCESS;
		} else {
			sender.sendMessage("[DZBASIC]" + ip + " not found.");
			return CommandOutcome.SUCCESS;
		}
	}

}