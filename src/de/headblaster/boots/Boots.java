package de.headblaster.boots;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Boots extends JavaPlugin{
	
	public static String PREFIX = ChatColor.translateAlternateColorCodes('&', "&4[&cBoots§4]§c");
	
	@Override
	public void onEnable() {
							
		System.out.println("[Boots] activated");
		
		Bukkit.getServer().getPluginManager().registerEvents(new BootsEvents(this), this);
		new BootsEvents(this);
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(command.getName().equalsIgnoreCase("boots")) {
				
				if(p.hasPermission("boots.boots")) {
					
					int reihen = 0;
					
					if(EnumBoots.values().length <= 9) reihen = 1;
					if(EnumBoots.values().length > 9) reihen = 2;
					if(EnumBoots.values().length > 2*9) reihen = 3;
					if(EnumBoots.values().length > 3*9) reihen = 4;
					if(EnumBoots.values().length > 4*9) reihen = 5;
					if(EnumBoots.values().length > 5*9) reihen = 6;
					
					
					Inventory inv = Bukkit.createInventory(null, 9*reihen,"§cBoots");
					p.openInventory(inv);
					
					for(int i = 0; i < inv.getSize();i++) {
						ItemStack comingsoon = new ItemStack(Material.REDSTONE);
						ItemMeta meta = comingsoon.getItemMeta();
						meta.setDisplayName("§4Coming Soon...");
					    comingsoon.setItemMeta(meta);
						
						inv.setItem(i,comingsoon);
						
					}
					
					int slot = 0;
					
					for(EnumBoots boots : EnumBoots.values()) {
						
						ItemStack boot = new ItemStack(Material.LEATHER_BOOTS);
						LeatherArmorMeta meta = (LeatherArmorMeta) boot.getItemMeta();
						
						meta.setColor(boots.getColor());
						meta.setDisplayName(boots.getName());
						meta.spigot().setUnbreakable(true);
						boot.setItemMeta(meta);
						
						inv.setItem(slot, boot);
						slot++;
						
						p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 200, 200);
						
					}

				}
				
			}
			
		}
		
		
		return true;
	}
	
}
