package com.TheRPGAdventurer.ROTD.client.items;

import com.TheRPGAdventurer.ROTD.DragonMounts;
import com.TheRPGAdventurer.ROTD.client.initialization.EnumItemBreedTypes;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemDragonScales extends Item {
	
	private EnumItemBreedTypes type;

	public ItemDragonScales(String unlocalizedName, EnumItemBreedTypes type) {
	    this.setUnlocalizedName(unlocalizedName);
	    this.setRegistryName(unlocalizedName);
	    this.setCreativeTab(DragonMounts.TAB);
	    this.type = type;
	}

}
