package com.TheRPGAdventurer.ROTD.client.initialization;

import com.TheRPGAdventurer.ROTD.DragonMounts;
import com.TheRPGAdventurer.ROTD.client.items.EnumItemBreedTypes;
import com.TheRPGAdventurer.ROTD.client.items.ItemDiamondShears;
import com.TheRPGAdventurer.ROTD.client.items.gemset.ItemDragonAxe;
import com.TheRPGAdventurer.ROTD.client.items.gemset.ItemDragonPickAxe;
import com.TheRPGAdventurer.ROTD.client.items.gemset.ItemDragonShovel;
import com.TheRPGAdventurer.ROTD.client.items.gemset.ItemDragonSword;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ModTools {
	
	static float damage = 10.0F; static float speed = -2.9F; static float netherDamage = 12.0F; static float netherSpeed = -3.0F; static float enderDamage = 9.0F; static float enderSpeed = -3.3F;

	public static final ToolMaterial JadeDragonScaleMaterial     = EnumHelper.addToolMaterial(DragonMounts.MODID + ":jadedragonscales", 4, 1700, 8.0F, 5.0F, 11);
	public static final ToolMaterial RubyDragonScaleMaterial     = EnumHelper.addToolMaterial(DragonMounts.MODID + ":rubydragonscales", 4, 1700, 8.0F, 5.0F, 11);
	public static final ToolMaterial GarnetDragonScaleMaterial   = EnumHelper.addToolMaterial(DragonMounts.MODID + ":garnetdragonscales", 4, 1700, 8.0F, 5.0F, 11);
	public static final ToolMaterial AmethystDragonScaleMaterial = EnumHelper.addToolMaterial(DragonMounts.MODID + ":amethystdragonscales", 4, 1700, 8.0F, 5.0F, 11);
	public static final ToolMaterial SapphireDragonScaleMaterial = EnumHelper.addToolMaterial(DragonMounts.MODID + ":sapphiredragonscales", 4, 1700, 8.0F, 5.0F, 11);
	public static final ToolMaterial NetherDragonScaleMaterial   = EnumHelper.addToolMaterial(DragonMounts.MODID + ":netherdragonscales", 5, 1770, 8.0F, 7.0F, 11);
	public static final ToolMaterial EnderDragonScaleMaterial    = EnumHelper.addToolMaterial(DragonMounts.MODID + ":enderdragonscales", 5, 2000, 8.0F, 7.0F, 11);
	
	public static ItemDragonPickAxe jadeDragonPickaxe = new ItemDragonPickAxe(JadeDragonScaleMaterial, "jade_dragon_pickaxe", EnumItemBreedTypes.EMERALD);
	public static ItemDragonAxe  jadeDragonAxe = new ItemDragonAxe(JadeDragonScaleMaterial, "jade_dragon_axe", damage, speed, EnumItemBreedTypes.EMERALD);
	public static ItemDragonShovel jadeDragonShovel = new ItemDragonShovel(JadeDragonScaleMaterial, "jade_dragon_shovel", EnumItemBreedTypes.EMERALD);
	public static ItemDragonSword jadeDragonSword = new ItemDragonSword(JadeDragonScaleMaterial, "jade_dragon_sword", EnumItemBreedTypes.EMERALD);
	
	public static ItemDragonPickAxe garnetDragonPickaxe = new ItemDragonPickAxe(GarnetDragonScaleMaterial, "garnet_dragon_pickaxe", EnumItemBreedTypes.GARNET);
	public static ItemDragonAxe garnetDragonAxe = new ItemDragonAxe(GarnetDragonScaleMaterial, "garnet_dragon_axe", damage, speed, EnumItemBreedTypes.GARNET);
	public static ItemDragonShovel garnetDragonShovel = new ItemDragonShovel(GarnetDragonScaleMaterial, "garnet_dragon_shovel", EnumItemBreedTypes.GARNET);
	public static ItemDragonSword garnetDragonSword = new ItemDragonSword(GarnetDragonScaleMaterial, "garnet_dragon_sword", EnumItemBreedTypes.GARNET);
	
	public static ItemDragonPickAxe sapphireDragonPickaxe = new ItemDragonPickAxe(SapphireDragonScaleMaterial, "sapphire_dragon_pickaxe", EnumItemBreedTypes.SAPPHIRE);
	public static ItemDragonAxe sapphireDragonAxe = new ItemDragonAxe(SapphireDragonScaleMaterial, "sapphire_dragon_axe", damage, damage, EnumItemBreedTypes.SAPPHIRE);
	public static ItemDragonShovel sapphireDragonShovel = new ItemDragonShovel(SapphireDragonScaleMaterial, "sapphire_dragon_shovel", EnumItemBreedTypes.SAPPHIRE);
	public static ItemDragonSword sapphireDragonSword = new ItemDragonSword(SapphireDragonScaleMaterial, "sapphire_dragon_sword", EnumItemBreedTypes.SAPPHIRE);
    
	public static ItemDragonPickAxe amethystDragonPickaxe = new ItemDragonPickAxe(AmethystDragonScaleMaterial, "amethyst_dragon_pickaxe", EnumItemBreedTypes.AMETHYST);
	public static ItemDragonAxe amethystDragonAxe = new ItemDragonAxe(AmethystDragonScaleMaterial, "amethyst_dragon_axe", damage, damage, EnumItemBreedTypes.AMETHYST);
	public static ItemDragonShovel amethystDragonShovel = new ItemDragonShovel(AmethystDragonScaleMaterial, "amethyst_dragon_shovel", EnumItemBreedTypes.AMETHYST);
	public static ItemDragonSword amethystDragonSword = new ItemDragonSword(AmethystDragonScaleMaterial, "amethyst_dragon_sword", EnumItemBreedTypes.AMETHYST);
	
	public static ItemDragonPickAxe rubyDragonPickaxe = new ItemDragonPickAxe(RubyDragonScaleMaterial, "ruby_dragon_pickaxe", EnumItemBreedTypes.RUBY);
	public static ItemDragonAxe rubyDragonAxe = new ItemDragonAxe(RubyDragonScaleMaterial, "ruby_dragon_axe", damage, damage, EnumItemBreedTypes.RUBY);
	public static ItemDragonShovel rubyDragonShovel = new ItemDragonShovel(RubyDragonScaleMaterial, "ruby_dragon_shovel", EnumItemBreedTypes.RUBY);
	public static ItemDragonSword rubyDragonSword = new ItemDragonSword(RubyDragonScaleMaterial, "ruby_dragon_sword", EnumItemBreedTypes.RUBY);
	
	public static ItemDragonPickAxe netherDragonPickaxe = new ItemDragonPickAxe(NetherDragonScaleMaterial, "nether_dragon_pickaxe", EnumItemBreedTypes.NETHER);
	public static ItemDragonAxe netherDragonAxe = new ItemDragonAxe(NetherDragonScaleMaterial, "nether_dragon_axe", netherDamage, netherSpeed, EnumItemBreedTypes.NETHER);
	public static ItemDragonShovel netherDragonShovel = new ItemDragonShovel(NetherDragonScaleMaterial, "nether_dragon_shovel", EnumItemBreedTypes.NETHER);
	public static ItemDragonSword netherDragonSword = new ItemDragonSword(NetherDragonScaleMaterial, "nether_dragon_sword", EnumItemBreedTypes.NETHER);
	
	public static ItemDragonPickAxe enderDragonPickaxe = new ItemDragonPickAxe(EnderDragonScaleMaterial, "ender_dragon_pickaxe", EnumItemBreedTypes.END);
	public static ItemDragonAxe enderDragonAxe = new ItemDragonAxe(EnderDragonScaleMaterial, "ender_dragon_axe", enderDamage, enderSpeed, EnumItemBreedTypes.END);
	public static ItemDragonShovel enderDragonShovel = new ItemDragonShovel(EnderDragonScaleMaterial, "ender_dragon_shovel", EnumItemBreedTypes.END);
	public static ItemDragonSword enderDragonSword = new ItemDragonSword(EnderDragonScaleMaterial, "ender_dragon_sword", EnumItemBreedTypes.END);
	
	public static ItemDiamondShears diamond_shears = new ItemDiamondShears(ToolMaterial.DIAMOND, "diamond_shears");
	
	public static final Item[] TOOLS = {
		jadeDragonPickaxe, jadeDragonAxe, jadeDragonShovel, jadeDragonSword,
		rubyDragonPickaxe, rubyDragonAxe, rubyDragonShovel, rubyDragonSword,
	    amethystDragonAxe, amethystDragonPickaxe, amethystDragonShovel, amethystDragonSword,
		garnetDragonAxe, garnetDragonPickaxe, garnetDragonShovel,  garnetDragonSword,
	    sapphireDragonAxe, sapphireDragonPickaxe, sapphireDragonShovel, sapphireDragonSword,
	    netherDragonAxe, netherDragonPickaxe, netherDragonShovel, netherDragonSword,
	    enderDragonAxe, enderDragonPickaxe, enderDragonShovel, enderDragonSword,
	    diamond_shears
	};
	
	public static void initRepairs() {
		AmethystDragonScaleMaterial.setRepairItem(new ItemStack(ModItems.AmethystDragonScales));
		GarnetDragonScaleMaterial.setRepairItem(new ItemStack(ModItems.GarnetDragonScales));
		JadeDragonScaleMaterial.setRepairItem(new ItemStack(ModItems.JadeDragonScales));
		RubyDragonScaleMaterial.setRepairItem(new ItemStack(ModItems.RubyDragonScales));
		SapphireDragonScaleMaterial.setRepairItem(new ItemStack(ModItems.SapphireDragonScales));
		NetherDragonScaleMaterial.setRepairItem(new ItemStack(ModItems.NetherDragonScales));
		EnderDragonScaleMaterial.setRepairItem(new ItemStack(ModItems.EnderDragonScales));

	}
	
}