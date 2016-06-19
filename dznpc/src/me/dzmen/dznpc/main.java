package me.dzmen.dznpc;

import java.util.Iterator;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.plugin.Plugin;

public class main extends Plugin {
	
	public String getVersion() {
		return "0.0.1";
	}

	public String getName() {
		return "DZnpc";
	}

	public void onEnable() {

		getLogger().info("Made by dzmen :D");
		getServer().getCommandManager().register(this, new npc_commands(this));
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