package me.dzmen.dzeco;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import me.dzmen.dzeco.commands.*;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.configuration.file.FileConfiguration;
import org.glydar.paraglydar.configuration.file.YamlConfiguration;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.plugin.Plugin;

public class main extends Plugin {
	
	public File EcoFile;
	public FileConfiguration Ecolist;	
	
	public String getVersion() {
		return "0.0.1";
	}

	public String getName() {
		return "DZeco";
	}

	public void onEnable() {

		EcoFile = new File(getConfigFolder(), "ecosystem.yml");
		Ecolist = new YamlConfiguration();
		
		try {
			loadConf();
		} catch (Exception e) {
			e.printStackTrace();
		}

		getLogger().info("Made by dzmen :D");
		getServer().getCommandManager().register(this, new money(this));
		getServer().getCommandManager().register(this, new pmoney(this));
		getServer().getCommandManager().register(this, new mgive(this));
		getServer().getCommandManager().register(this, new mtake(this));
		getServer().getCommandManager().register(this, new pay(this));
		getServer().getEventManager().register(this, new events(this));
	}
	
	void loadConf() throws Exception {
		if (!EcoFile.exists()) {
			Ecolist.set("valuta", "$");
			Ecolist.set("begin-money", "10");
	        HashMap<String, Object> users = new HashMap<String, Object>();
	        HashMap<String, Object> userlist = new HashMap<String, Object>();
	        List<String> banks = new ArrayList<>();
	        userlist.put("money", "10");
	        userlist.put("banks", banks);
	        users.put("dzmen", userlist);
	        Ecolist.set("users", users);
			eco_save();
		}
		Ecolist.load(EcoFile);
	}

	// Save eco file
	public void eco_save() throws Exception {
		try {
			Ecolist.save(EcoFile);
			loadConf();
		} catch (IOException exc) {
			getLogger().warning(exc,
					"Error while trying to save dzeco file");
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
		