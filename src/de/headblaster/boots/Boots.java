package de.headblaster.boots;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Boots extends JavaPlugin{
	
	public static String PREFIX = ChatColor.translateAlternateColorCodes('&', "&4[&cBoots§4]§c");
	
	@Override
	public void onEnable() {
				
		this.setStandard();
		
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
					
					if(args.length == 0) {
					
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
						
						List<String> lore = new ArrayList<>();
						lore.add(boots.getLore());
						lore.add(boots.getLore2());
						meta.setLore(lore);
						
						meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
						meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);

						boot.setItemMeta(meta);
						
						inv.setItem(slot, boot);
						slot++;
						
						p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 200, 200);
						//boots settings can-drop false
					}
					
					
					
					} else if(args.length == 1) {
						
						  if(args[0].equalsIgnoreCase("help")) {
								
								p.sendMessage(PREFIX + "/boots - Opens the boots menu");
								p.sendMessage(PREFIX + "/boots settings prize [Integer] - Set the Prize of the Boots(Essentials Economy)");
								p.sendMessage(PREFIX + "/boots settings can-drop [Boolean(true|false)] - Set if the player can drop the boots");
								p.sendMessage(PREFIX + "/boots settings remove-on-leave [Boolean(true|false)] - Set if the boots remove if the player leaves the server");
								
							}
						
						
						
					}
					
					
					else if(args.length == 3) {
						
						if(args[0].equalsIgnoreCase("settings")) {
							if(p.hasPermission("boots.settings")) {
							if(args[1].equalsIgnoreCase("can-drop")) {
								try {
								BootsEvents.can_drop = Boolean.parseBoolean(args[2]);
								
								BootsEvents.CONFIG.set("can-drop", args[2]);
								
								BootsEvents.CONFIG.save(BootsEvents.file);
								
								p.sendMessage(PREFIX + "Setting Changed!");
								
								} catch(IOException e) {
									
									p.sendMessage(PREFIX + "'" + args[2] +"' must be a boolean(true|false)");
									
								} 
							} else if(args[1].equalsIgnoreCase("remove-on-leave")) {
								
								try {
									BootsEvents.remove_on_leave = Boolean.parseBoolean(args[2]);
									
									BootsEvents.CONFIG.set("remove-on-leave", args[2]);
									
									BootsEvents.CONFIG.save(BootsEvents.file);
									
									p.sendMessage(PREFIX + "Setting Changed!");
									
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								
							} else if(args[1].equalsIgnoreCase("prize")) {
								
								try {
									BootsEvents.prize_per_boots = Integer.parseInt(args[2]);
									BootsEvents.CONFIG.set("prize-per-boots", args[2]);
									
									BootsEvents.CONFIG.save(BootsEvents.file);
									
									p.sendMessage(PREFIX + "Setting Changed!");
									
									} catch(IOException e) {
										
										p.sendMessage(PREFIX + "'" + args[2] +"' must be an integer(1;2;3;4;5;6;7;8;9.....)");
										
									} 
							}
							}
							
						}
						
					}
				}
				
			}
			
		}
		
		
		return true;
	}
	
	public void setStandard() {
		if(!BootsEvents.file.exists()) {
		BootsEvents.CONFIG.set("can-drop", false);
		BootsEvents.CONFIG.set("remove-on-leave", true);
		BootsEvents.CONFIG.set("prize-per-boots", 100);
		
		try {
			BootsEvents.CONFIG.save(BootsEvents.file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
	}
	
}
