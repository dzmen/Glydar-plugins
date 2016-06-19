package me.dzmen.dzauth;

import me.dzmen.dzauth.main;

import org.glydar.paraglydar.command.CommandSet;
import org.glydar.paraglydar.event.EventHandler;
import org.glydar.paraglydar.event.EventPriority;
import org.glydar.paraglydar.event.Listener;
import org.glydar.paraglydar.event.events.EntityMoveEvent;
import org.glydar.paraglydar.event.events.PlayerJoinEvent;
import org.glydar.paraglydar.geom.LongVector3;


public class system implements CommandSet, Listener {
	
	private main plugin;

	public system(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(final PlayerJoinEvent join) {
		boolean move = false;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void movement(EntityMoveEvent move){
		LongVector3 pos = move.getEntityData().getPosition();
		move.getPlayer().getEntityData().setPosition(pos);
	}
	
}