package com.TheRPGAdventurer.ROTD.client.initialization;

import com.TheRPGAdventurer.ROTD.client.items.ItemDragonScales;

import net.minecraft.item.Item;

public class ModItems {
	
	public static final Item ForestDragonScales;
	public static final Item FireDragonScales;
	public static final Item IceDragonScales;
	public static final Item WaterDragonScales;
	public static final Item AetherDragonScales;
	public static final Item NetherDragonScales;
	public static final Item EnderDragonScales;
	
	
	public static final Item[] ITEMS = {
		ForestDragonScales = new ItemDragonScales("forest_dragonscales", EnumItemBreedTypes.FOREST),
		FireDragonScales = new ItemDragonScales("fire_dragonscales", EnumItemBreedTypes.FIRE),
		IceDragonScales = new ItemDragonScales("ice_dragonscales", EnumItemBreedTypes.ICE),
		WaterDragonScales = new ItemDragonScales("water_dragonscales", EnumItemBreedTypes.WATER),
		AetherDragonScales = new ItemDragonScales("aether_dragonscales", EnumItemBreedTypes.AETHER),
		NetherDragonScales = new ItemDragonScales("nether_dragonscales", EnumItemBreedTypes.NETHER),
		EnderDragonScales = new ItemDragonScales("ender_dragonscales", EnumItemBreedTypes.END),
	};
}
