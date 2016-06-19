package me.dzmen.dzeco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	public void onJoin(final PlayerJoinEvent join) throws Exception {
		Player player = join.getPlayer();
		ConfigurationSection users = plugin.Ecolist.getConfigurationSection("users");
		String regex = "[0-9]+";
		// Check if user not exist
		if (!users.contains(player.getName())) { 
			HashMap<String, Object> userlist = new HashMap<String, Object>();
			List<String> banks = new ArrayList<>();
			userlist.put("money", plugin.Ecolist.getString("begin-money"));
			userlist.put("banks", banks);
			users.set(player.getName(), userlist);
			plugin.Ecolist.set("users", users);
			plugin.eco_save();
		// Check if there is a string
		}else if (plugin.Ecolist.getString("users." + player.getName() + ".money") == null) {
			plugin.Ecolist.set("users." + player.getName() + ".money", "0");
			plugin.eco_save();
		// Check if money are numbers	
		}else if (!plugin.Ecolist.getString("users." + player.getName() + ".money").matches(regex)){
			plugin.Ecolist.set("users." + player.getName() + ".money", "0");
			plugin.eco_save();
		}

	}
}