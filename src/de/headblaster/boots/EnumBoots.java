package de.headblaster.boots;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.potion.PotionEffectType;

public enum EnumBoots {

	HEART("§cHeartBoots",Color.FUCHSIA,Effect.HEART,PotionEffectType.REGENERATION,"§cHeart Effect","§cRegeneration"),
    WATER("§1WaterBoots",Color.BLUE,Effect.WATERDRIP,PotionEffectType.WATER_BREATHING,"§1Waterdrip Effect","§9Water Breathing"),
    ENDER("§5EnderBoots",Color.PURPLE,Effect.ENDER_SIGNAL,PotionEffectType.NIGHT_VISION,"§5Ender Signal Effect","§dNight Vision"),
    REDSTONE("§cRedstoneBoots",Color.ORANGE,Effect.COLOURED_DUST,PotionEffectType.FAST_DIGGING,"§6Coloured Dust Effect","§6Fast Digging"),
    TNT("§4TNTBoots",Color.MAROON,Effect.EXPLOSION_HUGE,PotionEffectType.BLINDNESS,"§4Explosion Huge Effect","§cBlindness"),
    SMOKE("§7SmokeBoots",Color.GRAY,Effect.LARGE_SMOKE,PotionEffectType.BLINDNESS,"§7Large Smoke Effect","§8Blindess"),
    LAVA("§6LavaBoots",Color.ORANGE,Effect.LAVA_POP,PotionEffectType.FIRE_RESISTANCE,"§6Lava Pop Effect","§6Fire Resistance"),
    PORTAL("§dPortalBoots",Color.PURPLE,Effect.PORTAL,PotionEffectType.CONFUSION,"§5Portal Effect","§dConfusion"),
    NOTES("§2NoteBoots",Color.NAVY,Effect.NOTE,PotionEffectType.JUMP,"§2Note Effect","§aJump Boost"),
    FIRE("§6FireBoots",Color.RED,Effect.FLAME,PotionEffectType.FIRE_RESISTANCE,"§6Flame Effect","§cFire Resistance");
	
	public String name;
	public Color color;
	public Effect effect;
	public PotionEffectType potioneffectType;
	public String lore;
	public String lore2;
	
	protected Effect e = Effect.PORTAL;
	
	private EnumBoots(String name,Color color,Effect effect,PotionEffectType potioneffect,String lore,String lore2) {
		
		this.name = name;
		this.color = color;
		this.effect = effect;
		this.potioneffectType = potioneffect;
		this.lore = lore;
		this.lore2 = lore2;
		
	}
	
	public String getName() {
		
		return this.name;	
		
	}
	public Color getColor() {
		
		return this.color;
		
	}
	public Effect getEffect() {
		
		return this.effect;
		
	}
	
	public PotionEffectType getPotioneffectType() {
		return potioneffectType;
	
	}
	
	public String getLore() {
		return lore;
	}
	
	public String getLore2() {
		return lore2;
	}
	
}
