package me.dzmen.dzeco;

import org.glydar.paraglydar.configuration.ConfigurationSection;

public class api {
	
	private main plugin;
	
	
	public boolean add(String playerName, String money) throws Exception{
		String regex = "[0-9]+";
		ConfigurationSection users = plugin.Ecolist.getConfigurationSection("users");
		// Check if config contains playerName
		if (users.contains(playerName)) {
			// Check if money only contains numbers
			if (money.matches(regex)) {
				String current_money = plugin.Ecolist.getString("users." + playerName + ".money");
				int num1 = Integer.parseInt(current_money);
				int num2 = Integer.parseInt(money);
				int total = num1 + num2;
				String new_money = Integer.toString(total);
				plugin.Ecolist.set("users." + playerName + ".money", new_money);
				plugin.eco_save();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean remove(String playerName, String money) throws Exception{
		String regex = "[0-9]+";
		ConfigurationSection users = plugin.Ecolist.getConfigurationSection("users");
		// Check if config contains playerName
		if (users.contains(playerName)) {
			// Check if money only contains numbers
			if (money.matches(regex)) {
				String current_money = plugin.Ecolist.getString("users." + playerName + ".money");
				int num1 = Integer.parseInt(current_money);
				int num2 = Integer.parseInt(money);
				int total = num1 - num2;
				String new_money = Integer.toString(total);
				plugin.Ecolist.set("users." + playerName + ".money", new_money);
				plugin.eco_save();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public String get(String playerName){
		ConfigurationSection users = plugin.Ecolist.getConfigurationSection("users");
		if (users.contains(playerName)) {
		String pmoney = plugin.Ecolist.getString("users." + playerName + ".money");			
		return pmoney;
		} else {
		String pmoney = "empty";
		return pmoney;
		}
	}
	
}