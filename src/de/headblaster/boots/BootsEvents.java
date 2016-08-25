package de.headblaster.boots;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

public class BootsEvents implements Listener {

	
	public Boots main;
	
	public BootsEvents(Boots boots) {
         
		this.main = boots;
		
	}
	
	public static File file = new File("plugins/Boots/config.yml");
	public static FileConfiguration CONFIG = YamlConfiguration.loadConfiguration(file);

	public static boolean can_drop = CONFIG.getBoolean("can-drop");
	public static int prize_per_boots = CONFIG.getInt("prize-per-boots");
	public static boolean remove_on_leave = CONFIG.getBoolean("remove-on-leave");
	
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		
	if(p.getInventory().getBoots() != null) {	
			
			String bootname = p.getInventory().getBoots().getItemMeta().getDisplayName();
			
			for(EnumBoots boots : EnumBoots.values()) {
			for(Player all : Bukkit.getOnlinePlayers()) {
				if(bootname.equalsIgnoreCase(boots.getName())) {
					
					if(p.isSneaking()) {
						all.spigot().playEffect(p.getLocation(), boots.getEffect(), 0, 0, 0, 3, 3, 3, 100, 1000);
						p.addPotionEffect(new PotionEffect(boots.getPotioneffectType(),60,2));
						
					}
					
				all.spigot().playEffect(p.getLocation(), boots.getEffect(), 0, 0, 0, 0, 0, 0, 10, 100);
				p.addPotionEffect(new PotionEffect(boots.getPotioneffectType(),60,2));
				
				break;
				}
			}
			
			
			}
	}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e) throws UserDoesNotExistException {
		
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getName().equalsIgnoreCase("§cBoots")) {
	if(e.getCurrentItem() != null) {	
		
		if(Economy.hasEnough(p.getName(), prize_per_boots)) {
		
		if(e.getClickedInventory().getName().equalsIgnoreCase("§cBoots")) {
			
			e.setCancelled(true);
			
			for(EnumBoots boots : EnumBoots.values()) {
				
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(boots.getName())) {
					
					p.getInventory().setBoots(e.getCurrentItem());
					p.sendMessage(Boots.PREFIX + "You've got the " + boots.getName());
					p.playSound(p.getLocation(), Sound.CLICK, 10, 10);
					p.closeInventory();
					try {
						Economy.subtract(p.getName(), prize_per_boots);
					} catch (NoLoanPermittedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}	
				}
				
			} else {//donothing
			}
		} else {
			p.sendMessage(Boots.PREFIX + "§4You doesnt have enough money!");
		}
		} 
		}
		
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		if(can_drop == false) {
		try {
		for (EnumBoots boots : EnumBoots.values()) {
		if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(boots.getName())) { 
			e.setCancelled(true);
		} else {
			//donothing
		}
		}
		} catch(Exception ex) {
		}
		} else {
			//do nothing
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		if(remove_on_leave == true) {
		for(ItemStack is : p.getInventory().getContents()) {
			
			for(EnumBoots boots : EnumBoots.values()) {
				
				if(is != null) {
					if(is.hasItemMeta()) {
				if(is.getItemMeta().getDisplayName().equalsIgnoreCase(boots.getName())) {
					
					p.getInventory().remove(is);
				
				}
				}
				}
				
			}
			
		}
		
		} else {
			//do nothing
		}
	}
	
}
