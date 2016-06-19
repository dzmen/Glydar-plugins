package me.dzmen.dzbasic;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.event.EventHandler;
import org.glydar.paraglydar.event.EventPriority;
import org.glydar.paraglydar.event.Listener;
import org.glydar.paraglydar.event.events.PlayerJoinEvent;
import org.glydar.paraglydar.models.Player;

import me.dzmen.dzbasic.command_help;
import me.dzmen.dzbasic.main;

public class events extends command_help implements Listener {

	private main plugin;

	public events(main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(final PlayerJoinEvent join) {
		Player player = join.getPlayer();
		if (plugin.Banlist.getStringList(main.BanName).contains(
				join.getPlayer().getIp())) {
			join.getPlayer().kickPlayer(
					"You are banned! (sorry for crashing the game :( )");
		} else {
			// Welcome message
			join.getPlayer().sendMessageToPlayer(
					plugin.Config.getString("welcome"));

			// Join message
			ParaGlydar.getServer().broadcastMessage(
					plugin.Config.getString("join").replace("%p",
							join.getPlayer().getName()));
		}
		

		// Check admin status
		if (plugin.adminlist.contains(player.getName())) {
			player.sendMessage("[DZBASIC]To enable admin, type /pass <password>!");		

		}

	}

}