package com.TheRPGAdventurer.ROTD.client.items;

import net.minecraft.util.text.TextFormatting;

public enum EnumItemBreedTypes {
	
	AMETHYST(TextFormatting.DARK_PURPLE), 
	GARNET(TextFormatting.GOLD), 
	RUBY(TextFormatting.DARK_RED), 
	SAPPHIRE(TextFormatting.DARK_BLUE), 
	EMERALD(TextFormatting.DARK_GREEN),
	NETHER(TextFormatting.GOLD),
	END(TextFormatting.LIGHT_PURPLE);
	
	public TextFormatting color;
	
	private EnumItemBreedTypes(TextFormatting color) {
		this.color = color;
	}
}
