package com.TheRPGAdventurer.ROTD.client.initialization;

import net.minecraft.util.text.TextFormatting;

public enum EnumItemBreedTypes {
	
	AETHER(TextFormatting.BLUE), 
	WATER(TextFormatting.AQUA), 
	ICE(TextFormatting.WHITE), 
	FIRE(TextFormatting.DARK_RED), 
	FOREST(TextFormatting.DARK_GREEN),
	NETHER(TextFormatting.GOLD),
	END(TextFormatting.LIGHT_PURPLE);
	
	public TextFormatting color;
	
	private EnumItemBreedTypes(TextFormatting color) {
		this.color = color;
	}
}
