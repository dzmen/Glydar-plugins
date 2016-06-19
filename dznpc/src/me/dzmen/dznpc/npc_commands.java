package me.dzmen.dznpc;

import org.glydar.paraglydar.command.Command;
import org.glydar.paraglydar.command.CommandOutcome;
import org.glydar.paraglydar.command.CommandSet;
import org.glydar.paraglydar.models.NPC;
import org.glydar.paraglydar.models.Player;
import org.glydar.paraglydar.permissions.Permission.PermissionDefault;

public class npc_commands implements CommandSet  {
	
	private main plugin;
	private NPC npc;
	
	public npc_commands(main plugin) {
		this.plugin = plugin;
	}
	
		@Command(name = "npc", usage = " - Create a npc", permission = "dzbasic.npc", permissionDefault = PermissionDefault.ADMIN)
		public CommandOutcome execute(Player sender, String playerName) {
			
			
			return CommandOutcome.SUCCESS;
		}
	}