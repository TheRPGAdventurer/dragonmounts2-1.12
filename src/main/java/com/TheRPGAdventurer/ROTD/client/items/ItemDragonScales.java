package com.TheRPGAdventurer.ROTD.client.items;

import com.TheRPGAdventurer.ROTD.DragonMounts;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemDragonScales extends Item {

	public ItemDragonScales(String unlocalizedName) {
	    this.setUnlocalizedName(unlocalizedName);
	    this.setRegistryName(unlocalizedName);
	    this.setCreativeTab(DragonMounts.TAB);
	}

}
