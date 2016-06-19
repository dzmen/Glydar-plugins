package me.dzmen.dzbasic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import me.dzmen.dzbasic.commands.*;
import me.dzmen.dzbasic.events;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.configuration.file.FileConfiguration;
import org.glydar.paraglydar.configuration.file.YamlConfiguration;
import org.glydar.paraglydar.plugin.Plugin;

public class main extends Plugin {

	public static final String BanName = "bans";
	public static final List<String> BanList = new ArrayList<>();
	public File BanListFile;
	public FileConfiguration Banlist;

	public File adminListFile;
	public FileConfiguration adminlist;

	public File ConfigFile;
	public FileConfiguration Config;

	public String getVersion() {
		return "0.0.1";
	}

	public String getName() {
		return "DZbasic";
	}

	public void onEnable() {

		BanListFile = new File(getConfigFolder(), "banlist.yml");
		Banlist = new YamlConfiguration();

		ConfigFile = new File(getConfigFolder(), "config.yml");
		Config = new YamlConfiguration();

		adminListFile = new File(getConfigFolder(), "admins.yml");
		adminlist = new YamlConfiguration();

		try {
			loadConf();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Conf_check();

		getServer().getCommandManager().register(this, new kick());
		getServer().getCommandManager().register(this, new msg());
		getServer().getCommandManager().register(this, new getip());
		getServer().getCommandManager().register(this, new help());
		getServer().getCommandManager().register(this, new say());

		getServer().getCommandManager().register(this, new admin(this));
		// getServer().getCommandManager().register(this, new tp()); TODO: Not
		// working yet :(
		getServer().getCommandManager().register(this, new pvp());
		getServer().getCommandManager().register(this, new kill());
		getServer().getCommandManager().register(this, new heal());
		getServer().getCommandManager().register(this, new quit());
		getServer().getCommandManager().register(this, new pass(this));
		getServer().getCommandManager().register(this, new ban(this));
		getServer().getCommandManager().register(this, new unban(this));
		// Register events
		getServer().getEventManager().register(this, new events(this));
		// Log info (Because I like :P)
		getLogger().info("Made by dzmen :D");
	}

	// //////////////////////
	// CONFIG LOADER!!!/////
	// //////////////////////

	private void loadConf() throws Exception {
		if (!BanListFile.exists()) {
			Banlist.addDefault(BanName, BanList);
			Banlist.options().copyDefaults(true);
			Ban_save();
		}
		if (!ConfigFile.exists()) {
			Config.options()
					.header("welcome = The welcome message that a player recieved on joining\r\n"
							+ "join = The message who recieved everybody if someone join\r\n"
							// +
							// "leave = The message who recieved everybody when someone leaves\r\n"
							// - No leave event yet :(
							+ "Have fun :D");
			Config.addDefault("welcome",
					"This is the default welcome message :)");
			Config.addDefault("join", "%p joined the server");
			// Config.addDefault("leave", " %P left the server"); - No leave
			// event yet :(

			Config.options().copyDefaults(true);
			Conf_save();
		}
		if (!adminListFile.exists()) {
			Admin_save();
		}
		adminlist.load(adminListFile);
		Banlist.load(BanListFile);
		Config.load(ConfigFile);

	}

	// ////////////////////////////////////////////
	// ////////CONFIG SYSTEM///////////////////////
	// ////////////////////////////////////////////
	// Thanks to aumgn for config functions :D

	// Save config file
	private void Conf_save() {
		try {
			Config.save(ConfigFile);
		} catch (IOException exc) {
			ParaGlydar.getLogger().warning(exc,
					"Error while trying to save config file");
		}
	}

	// Check config file
	private void Conf_check() {
		if (!Config.contains("welcome")) {
			Config.set("welcome", "This is the default welcome message :)");
		}
		if (!Config.contains("join")) {
			Config.set("join", "%p joined the server");
		}

	}

	// ////////////////////////////////////////////
	// ////////END CONFIG SYSTEM///////////////////
	// ////////////////////////////////////////////

	// ////////////////////////////////////////////
	// ////////BAN SYSTEM//////////////////////////
	// ////////////////////////////////////////////

	// Save ban file
	private void Ban_save() {
		try {
			Banlist.save(BanListFile);
		} catch (IOException exc) {
			ParaGlydar.getLogger().warning(exc,
					"Error while trying to save banlist file");
		}
	}

	// Get Bans
	public Set<String> getBans() {
		return ImmutableSet.copyOf(Banlist.getStringList(BanName));
	}

	// add Ban
	public boolean addBan(String ip) {
		Set<String> bansystem = Sets.newHashSet(Banlist.getStringList(BanName));
		boolean added = bansystem.add(ip);
		if (added) {
			Banlist.set(BanName, new ArrayList<>(bansystem));
			Ban_save();
		}
		return added;
	}

	// Remove Ban
	public boolean removeBan(String ip) {
		Set<String> bans = Sets.newHashSet(Banlist.getStringList(BanName));
		boolean removed = bans.remove(ip);
		if (removed) {
			Banlist.set(BanName, new ArrayList<>(bans));
			Ban_save();
		}
		return removed;
	}

	// ////////////////////////////////////////////
	// ////////END BAN SYSTEM//////////////////////
	// ////////////////////////////////////////////

	// ////////////////////////////////////////////
	// ////////ADMIN SYSTEM////////////////////////
	// ////////////////////////////////////////////

	// Save Admin file
	private void Admin_save() {
		try {
			adminlist.save(adminListFile);
		} catch (IOException exc) {
			ParaGlydar.getLogger().warning(exc,
					"Error while trying to save banlist file");
		}
	}

	// add Admins
	public void addAdmin(String player, String password) {
		adminlist.set(player, password);
		Admin_save();
	}

	// Remove Admins
	public void removeAdmin(String player) {
		adminlist.set(player, null);
		Admin_save();
	}

	// ////////////////////////////////////////////
	// ////////END ADMIN SYSTEM////////////////////
	// ////////////////////////////////////////////

}
