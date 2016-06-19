package me.dzmen.dzbasic;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;

import org.glydar.paraglydar.ParaGlydar;
import org.glydar.paraglydar.command.CommandSender;
import org.glydar.paraglydar.models.Player;

public class command_help {

	// Thanks to Essentials
	protected String createString(String[] args, int start) {
		return createString(args, start, " ");
	}

	// Thanks to Essentials
	protected String createString(String[] args, int start, String glue) {
		StringBuilder string = new StringBuilder();

		for (int x = start; x < args.length; x++) {
			string.append(args[x]);
			if (x != args.length - 1) {
				string.append(glue);
			}
		}

		return string.toString();
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

	// Get player from sender
	public Player getPlayer(CommandSender s) {
		Iterator iterator = ParaGlydar.getServer().getConnectedPlayers()
				.iterator();
		Player player;
		if (!iterator.hasNext()) {
			return null;
		}
		player = (Player) iterator.next();
		return player;
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

}