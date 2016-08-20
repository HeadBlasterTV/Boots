package de.headblaster.boots;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.potion.PotionEffectType;

public enum EnumBoots {

	HEART("§cHeartBoots",Color.FUCHSIA,Effect.HEART,PotionEffectType.REGENERATION),
    WATER("§1WaterBoots",Color.BLUE,Effect.WATERDRIP,PotionEffectType.WATER_BREATHING),
    ENDER("§5EnderBoots",Color.PURPLE,Effect.ENDER_SIGNAL,PotionEffectType.NIGHT_VISION),
    REDSTONE("§cRedstoneBoots",Color.ORANGE,Effect.COLOURED_DUST,PotionEffectType.FAST_DIGGING),
    TNT("§4TNTBoots",Color.MAROON,Effect.EXPLOSION_HUGE,PotionEffectType.BLINDNESS),
    SMOKE("§7SmokeBoots",Color.GRAY,Effect.LARGE_SMOKE,PotionEffectType.BLINDNESS),
    LAVA("§6LavaBoots",Color.ORANGE,Effect.LAVA_POP,PotionEffectType.FIRE_RESISTANCE),
    PORTAL("§dPortalBoots",Color.PURPLE,Effect.PORTAL,PotionEffectType.CONFUSION),
    NOTES("§2NoteBoots",Color.NAVY,Effect.NOTE,PotionEffectType.JUMP),
    FIRE("§6FireBoots",Color.RED,Effect.FLAME,PotionEffectType.FIRE_RESISTANCE);
	
	public String name;
	public Color color;
	public Effect effect;
	public PotionEffectType potioneffectType;
	
	protected Effect e = Effect.PORTAL;
	
	private EnumBoots(String name,Color color,Effect effect,PotionEffectType potioneffect) {
		
		this.name = name;
		this.color = color;
		this.effect = effect;
		this.potioneffectType = potioneffect;
		
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
	
}
