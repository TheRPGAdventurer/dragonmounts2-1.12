package com.TheRPGAdventurer.ROTD.client.items;

import com.TheRPGAdventurer.ROTD.DragonMounts;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class ItemBlockBase extends ItemBlock {
	

	public ItemBlockBase(String name, Block block) {
		super(block);
		this.setRegistryName(DragonMounts.MODID, name);
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.setCreativeTab(DragonMounts.TAB);
	}

}
