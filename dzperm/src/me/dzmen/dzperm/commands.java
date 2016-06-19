package me.dzmen.dzperm;

import java.util.List;

import org.glydar.paraglydar.command.Command;
import org.glydar.paraglydar.command.CommandOutcome;
import org.glydar.paraglydar.command.CommandSender;
import org.glydar.paraglydar.command.CommandSet;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.PermissionAttachment;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;


public class commands implements CommandSet {

	private main plugin;
	
	public commands(main plugin) {
		this.plugin = plugin;
	}

	@Command(name = "addperm", usage = "<player> <permissions> - add permissions", permission = "dzperm.addperm", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome addperm(CommandSender sender, String playerName,
			String permission) {
		Player player = plugin.getPlayer(playerName);
        List<String> user_perm = plugin.Permlist.getConfigurationSection("users." + player.getName()).getStringList("permissions");
        user_perm.add(permission);
        plugin.Permlist.set("users." + player.getName() + ".permissions", user_perm);
		plugin.perm_save();
		return CommandOutcome.SUCCESS;
	}

	@Command(name = "reloadperm", usage = " - reload permissions file", permission = "dzperm.reloadperm", permissionDefault = PermissionDefault.ADMIN)
	public CommandOutcome reload(CommandSender sender) {
		try {
			plugin.loadConf();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return CommandOutcome.SUCCESS;
	}
}
