package com.TheRPGAdventurer.ROTD.client.inventory;

import com.TheRPGAdventurer.ROTD.client.blocks.BlockDragonBreedEgg;
import com.TheRPGAdventurer.ROTD.client.initialization.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {

	public CreativeTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Blocks.DRAGON_EGG);
	}

	@Override
	public boolean hasSearchBar() {
		return false;
	}
}
