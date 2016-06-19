package me.dzmen.dzperm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.configuration.ConfigurationSection;
import org.glydar.paraglydar.configuration.MemorySection;
import org.glydar.paraglydar.configuration.file.FileConfiguration;
import org.glydar.paraglydar.configuration.file.YamlConfiguration;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.PermissionAttachment;
import org.glydar.paraglydar.plugin.Plugin;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class main extends Plugin {

	HashMap<String, PermissionAttachment> permissions = new HashMap<String, PermissionAttachment>();
	public static final List<String> Permission = new ArrayList<>();
	public File PermFile;
	public FileConfiguration Permlist;
	
	public String getVersion() {
		return "0.0.1";
	}

	public String getName() {
		return "DZperm";
	}

	public void onEnable() {

		PermFile = new File(getConfigFolder(), "permissions.yml");
		Permlist = new YamlConfiguration();
		
		try {
			loadConf();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getServer().getCommandManager().register(this, new commands(this));
		getServer().getEventManager().register(this, new events(this));
		getLogger().info("Made by dzmen :D");
	}
	
	void loadConf() throws Exception {
		if (!PermFile.exists()) {
	        HashMap<String, Object> users = new HashMap<String, Object>();
	        HashMap<String, Object> userlist = new HashMap<String, Object>();
	        ArrayList<String> user_perm = new ArrayList<String>();
	        user_perm.add("permissions.example");
	        userlist.put("permissions", user_perm);
	        users.put("dzmen", userlist);
	        Permlist.set("users", users);
			perm_save();
		}

		Permlist.load(PermFile);
	}

	// Save ban file
	void perm_save() {
		try {
			Permlist.save(PermFile);
		} catch (IOException exc) {
			ParaGlydar.getLogger().warning(exc,
					"Error while trying to save banlist file");
		}
	}

	// Get player from text message
	public Player getPlayer(String s) {
		Iterator iterator = ParaGlydar.getServer().getConnectedPlayers()
				.iterator();
		Player player;
		do {
			if (!iterator.hasNext()) {
				return null;
			}
			player = (Player) iterator.next();
		} while (!player.getName().equalsIgnoreCase(s));
		return player;
	}
		
}
		