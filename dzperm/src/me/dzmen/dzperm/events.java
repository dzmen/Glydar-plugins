package me.dzmen.dzperm;

import java.util.ArrayList;
import java.util.HashMap;

import org.glydar.paraglydar.configuration.ConfigurationSection;
import org.glydar.paraglydar.event.EventHandler;
import org.glydar.paraglydar.event.EventPriority;
import org.glydar.paraglydar.event.Listener;
import org.glydar.paraglydar.event.events.PlayerJoinEvent;
import org.glydar.paraglydar.models.Player;


public class events implements Listener {

	private main plugin;

	public events(main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onJoin(final PlayerJoinEvent join) {
		Player player = join.getPlayer();
		// Check if user exist
		if (!plugin.Permlist.contains(player.getName())) {
	        ConfigurationSection users = plugin.Permlist.getConfigurationSection("users");
	        HashMap<String, Object> userlist = new HashMap<String, Object>();
	        ArrayList<String> user_perm = new ArrayList<String>();
	        userlist.put("permissions", user_perm);
	        users.set(player.getName(), userlist);
	        plugin.Permlist.set("users", users);
			plugin.perm_save();		

		}
		
	}
}