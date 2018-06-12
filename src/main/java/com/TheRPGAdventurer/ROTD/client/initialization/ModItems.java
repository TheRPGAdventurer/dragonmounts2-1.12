package com.TheRPGAdventurer.ROTD.client.initialization;

import com.TheRPGAdventurer.ROTD.client.items.ItemDragonScales;

import net.minecraft.item.Item;

public class ModItems {
	
	public static final Item JadeDragonScales;
	public static final Item RubyDragonScales;
	public static final Item AmethystDragonScales;
	public static final Item SapphireDragonScales;
	public static final Item GarnetDragonScales;
	public static final Item NetherDragonScales;
	public static final Item EnderDragonScales;
	
	
	public static final Item[] ITEMS = {
		JadeDragonScales = new ItemDragonScales("jade_dragonscales"),
		RubyDragonScales = new ItemDragonScales("ruby_dragonscales"),
		AmethystDragonScales = new ItemDragonScales("amethyst_dragonscales"),
		SapphireDragonScales = new ItemDragonScales("sapphire_dragonscales"),
		GarnetDragonScales = new ItemDragonScales("garnet_dragonscales"),
		NetherDragonScales = new ItemDragonScales("nether_dragonscales"),
		EnderDragonScales = new ItemDragonScales("ender_dragonscales"),
	};
}
