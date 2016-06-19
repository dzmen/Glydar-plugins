package me.dzmen.dzauth;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.configuration.file.FileConfiguration;
import org.glydar.paraglydar.configuration.file.YamlConfiguration;
import org.glydar.paraglydar.plugin.Plugin;
import me.dzmen.dzeco.api;

public class main extends Plugin {

	public File AuthListFile;
	public FileConfiguration Authlist;
	public api api;
	
	public String getVersion() {
		return "0.0.1";
	}

	public String getName() {
		return "DZAuth";	
	}

	public void onEnable() {

		AuthListFile = new File(getConfigFolder(), "logins.yml");
		Authlist = new YamlConfiguration();
		
		try {
			loadConf();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getLogger().info("Made by dzmen :D");
		getServer().getCommandManager().register(this, new system(this));
		getServer().getEventManager().register(this, new system(this));
	}
	
	// Make md5 (Thanks to http://javarevisited.blogspot.nl/2013/03/generate-md5-hash-in-java-string-byte-array-example-tutorial.html)
	public String encrypt(String password) {
		String digest = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for (byte b : hash) {
				sb.append(String.format("%02x", b & 0xff));
			}
			digest = sb.toString();

		} catch (UnsupportedEncodingException ex) {

		} catch (NoSuchAlgorithmException ex) {

		}
		
		return digest;

	}
	
	// ////////////////////////////////////////////
	// ////////AUTH SYSTEM/////////////////////////
	// ////////////////////////////////////////////

	
	private void loadConf() throws Exception {
		if (!AuthListFile.exists()) {
			Auth_save();
		}
		Authlist.load(AuthListFile);
	}
	
	
	// Save Auth file
	private void Auth_save() {
		try {
			Authlist.save(AuthListFile);
		} catch (IOException exc) {
			ParaGlydar.getLogger().warning(exc,
					"Error while trying to save banlist file");
		}
	}

	// add Admins
	public void addAuth(String player, String password) {
		Authlist.set(player, password);
		Auth_save();
	}

	// Remove Admins
	public void removeAuth(String player) {
		Authlist.set(player, null);
		Auth_save();
	}

	// ////////////////////////////////////////////
	// ////////END AUTH SYSTEM/////////////////////
	// ////////////////////////////////////////////
		
}