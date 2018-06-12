package com.TheRPGAdventurer.ROTD.client.initialization;

import com.TheRPGAdventurer.ROTD.DragonMounts;
import com.TheRPGAdventurer.ROTD.client.items.EnumItemBreedTypes;
import com.TheRPGAdventurer.ROTD.client.items.ItemDragonArmor;
import com.TheRPGAdventurer.ROTD.client.items.gemset.ItemGemDragonArmour;
import com.TheRPGAdventurer.ROTD.client.items.specialset.ItemEnderDragonArmour;
import com.TheRPGAdventurer.ROTD.client.items.specialset.ItemNetherDragonArmour;

import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.EnumHelper;

public class ModArmour {
	
	public static final ArmorMaterial JadeDragonScaleMaterial     = EnumHelper.addArmorMaterial("jadeDragonScale", DragonMounts.MODID + ":jadeDragonScale", 45, new int [] {4,7,9,4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 5.0F);
	public static final ArmorMaterial RubyDragonScaleMaterial     = EnumHelper.addArmorMaterial("rubyDragonScale", DragonMounts.MODID + ":rubyDragonScale", 45, new int [] {4,7,9,4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 5.0F);
	public static final ArmorMaterial GarnetDragonScaleMaterial   = EnumHelper.addArmorMaterial("garnetDragonScale", DragonMounts.MODID + ":garnetDragonScale", 45, new int [] {4,7,9,4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 5.0F);
	public static final ArmorMaterial AmethystDragonScaleMaterial = EnumHelper.addArmorMaterial("amethystDragonScale", DragonMounts.MODID + ":amethystDragonScale", 45, new int [] {4,7,9,4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 5.0F);
	public static final ArmorMaterial SapphireDragonScaleMaterial = EnumHelper.addArmorMaterial("sapphireDragonScale", DragonMounts.MODID + ":sapphireDragonScale", 45, new int [] {4,7,9,4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 5.0F);
	public static final ArmorMaterial NetherDragonScaleMaterial   = EnumHelper.addArmorMaterial("netherDragonScale", DragonMounts.MODID + ":netherDragonScale", 47, new int [] {4,7,9,4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 6.0F);
	public static final ArmorMaterial EnderDragonScaleMaterial    = EnumHelper.addArmorMaterial("enderDragonScale", DragonMounts.MODID + ":enderDragonScale", 50, new int [] {4,7,9,4}, 11, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 7.0F);
	
	public static ItemGemDragonArmour jadeDragonScaleHelmet;
	public static ItemGemDragonArmour jadeDragonScaleCap;
	public static ItemGemDragonArmour jadeDragonScaleChestplate;
	public static ItemGemDragonArmour jadeDragonScaleLeggings;
	public static ItemGemDragonArmour jadeDragonScaleBoots;
	
	public static ItemGemDragonArmour rubyDragonScaleHelmet;
	public static ItemGemDragonArmour rubyDragonScaleCap;
	public static ItemGemDragonArmour rubyDragonScaleChestplate;
	public static ItemGemDragonArmour rubyDragonScaleLeggings;
	public static ItemGemDragonArmour rubyDragonScaleBoots;
	
	public static ItemGemDragonArmour garnetDragonScaleHelmet;
	public static ItemGemDragonArmour garnetDragonScaleCap;
	public static ItemGemDragonArmour garnetDragonScaleChestplate;
	public static ItemGemDragonArmour garnetDragonScaleLeggings;
	public static ItemGemDragonArmour garnetDragonScaleBoots;
	
	public static ItemGemDragonArmour amethystDragonScaleHelmet;
	public static ItemGemDragonArmour amethystDragonScaleCap;
	public static ItemGemDragonArmour amethystDragonscaleChesplate;
	public static ItemGemDragonArmour amethystDragonScaleLeggings;
	public static ItemGemDragonArmour amethystDragonScaleBoots;
	
	public static ItemGemDragonArmour sapphireDragonScaleHelmet;
	public static ItemGemDragonArmour sapphireDragonScaleCap;
	public static ItemGemDragonArmour sapphireDragonScaleChestplate;
	public static ItemGemDragonArmour sapphireDragonScaleLeggings;
	public static ItemGemDragonArmour sapphireDragonScaleBoots;
	
	public static ItemNetherDragonArmour netherDragonScaleHelmet;
	public static ItemNetherDragonArmour netherDragonScaleCap;
	public static ItemNetherDragonArmour netherDragonScaleChestplate;
	public static ItemNetherDragonArmour netherDragonScaleLeggings;
	public static ItemNetherDragonArmour netherDragonScaleBoots;
	
	public static ItemEnderDragonArmour enderDragonScaleHelmet;
	public static ItemEnderDragonArmour enderDragonScaleCap;
	public static ItemEnderDragonArmour enderDragonScaleChestplate;
	public static ItemEnderDragonArmour enderDragonScaleLeggings;
	public static ItemEnderDragonArmour enderDragonScaleBoots;
	
	// for dragon
	public static ItemDragonArmor dragonarmor_diamond;
	public static ItemDragonArmor dragonarmor_gold;
	public static ItemDragonArmor dragonarmor_iron;
	
	public static final Item[] ARMOR =  {
		jadeDragonScaleCap = new ItemGemDragonArmour(JadeDragonScaleMaterial, 1, EntityEquipmentSlot.HEAD, "jade_dragonscale_cap", EnumItemBreedTypes.EMERALD),
		jadeDragonScaleChestplate = new ItemGemDragonArmour(JadeDragonScaleMaterial, 1, EntityEquipmentSlot.CHEST, "jade_dragonscale_chestplate", EnumItemBreedTypes.EMERALD),
		jadeDragonScaleLeggings = new ItemGemDragonArmour(JadeDragonScaleMaterial, 2, EntityEquipmentSlot.LEGS, "jade_dragonscale_leggings", EnumItemBreedTypes.EMERALD),
		jadeDragonScaleBoots = new ItemGemDragonArmour(JadeDragonScaleMaterial, 1, EntityEquipmentSlot.FEET, "jade_dragonscale_boots", EnumItemBreedTypes.EMERALD),
		
		rubyDragonScaleCap = new ItemGemDragonArmour(RubyDragonScaleMaterial, 1, EntityEquipmentSlot.HEAD, "ruby_dragonscale_cap", EnumItemBreedTypes.RUBY),
		rubyDragonScaleChestplate = new ItemGemDragonArmour(RubyDragonScaleMaterial, 1, EntityEquipmentSlot.CHEST, "ruby_dragonscale_chestplate", EnumItemBreedTypes.RUBY),
		rubyDragonScaleLeggings = new ItemGemDragonArmour(RubyDragonScaleMaterial, 2, EntityEquipmentSlot.LEGS, "ruby_dragonscale_leggings", EnumItemBreedTypes.RUBY),
		rubyDragonScaleBoots = new ItemGemDragonArmour(RubyDragonScaleMaterial, 1, EntityEquipmentSlot.FEET, "ruby_dragonscale_boots", EnumItemBreedTypes.RUBY),
		
		garnetDragonScaleCap = new ItemGemDragonArmour(GarnetDragonScaleMaterial, 1, EntityEquipmentSlot.HEAD, "garnet_dragonscale_cap", EnumItemBreedTypes.GARNET),
		garnetDragonScaleChestplate = new ItemGemDragonArmour(GarnetDragonScaleMaterial, 1, EntityEquipmentSlot.CHEST, "garnet_dragonscale_chestplate", EnumItemBreedTypes.GARNET),
		garnetDragonScaleLeggings = new ItemGemDragonArmour(GarnetDragonScaleMaterial, 2, EntityEquipmentSlot.LEGS, "garnet_dragonscale_leggings", EnumItemBreedTypes.GARNET),
		garnetDragonScaleBoots = new ItemGemDragonArmour(GarnetDragonScaleMaterial, 1, EntityEquipmentSlot.FEET, "garnet_dragonscale_boots", EnumItemBreedTypes.GARNET),
		
		amethystDragonScaleCap = new ItemGemDragonArmour(AmethystDragonScaleMaterial, 1, EntityEquipmentSlot.HEAD, "amethyst_dragonscale_cap", EnumItemBreedTypes.AMETHYST),
		amethystDragonscaleChesplate = new ItemGemDragonArmour(AmethystDragonScaleMaterial, 1, EntityEquipmentSlot.CHEST, "amethyst_dragonscale_chestplate", EnumItemBreedTypes.AMETHYST),
		amethystDragonScaleLeggings = new ItemGemDragonArmour(AmethystDragonScaleMaterial, 2, EntityEquipmentSlot.LEGS, "amethyst_dragonscale_leggings", EnumItemBreedTypes.AMETHYST),
		amethystDragonScaleBoots = new ItemGemDragonArmour(AmethystDragonScaleMaterial, 1, EntityEquipmentSlot.FEET, "amethyst_dragonscale_boots", EnumItemBreedTypes.AMETHYST),
		
		sapphireDragonScaleCap = new ItemGemDragonArmour(SapphireDragonScaleMaterial, 1, EntityEquipmentSlot.HEAD, "sapphire_dragonscale_cap", EnumItemBreedTypes.SAPPHIRE),
		sapphireDragonScaleChestplate = new ItemGemDragonArmour(SapphireDragonScaleMaterial,  1, EntityEquipmentSlot.CHEST, "sapphire_dragonscale_chestplate", EnumItemBreedTypes.SAPPHIRE),
		sapphireDragonScaleLeggings = new ItemGemDragonArmour(SapphireDragonScaleMaterial, 2, EntityEquipmentSlot.LEGS, "sapphire_dragonscale_leggings", EnumItemBreedTypes.SAPPHIRE),
		sapphireDragonScaleBoots = new ItemGemDragonArmour(SapphireDragonScaleMaterial, 1, EntityEquipmentSlot.FEET, "sapphire_dragonscale_boots", EnumItemBreedTypes.SAPPHIRE),
		
		netherDragonScaleCap = new ItemNetherDragonArmour(NetherDragonScaleMaterial, 1, EntityEquipmentSlot.HEAD, "nether_dragonscale_cap", new PotionEffect(MobEffects.RESISTANCE, 2, 1, true, false), EnumItemBreedTypes.NETHER),
		netherDragonScaleChestplate = new ItemNetherDragonArmour(NetherDragonScaleMaterial,  1, EntityEquipmentSlot.CHEST, "nether_dragonscale_chestplate", new PotionEffect(MobEffects.RESISTANCE, 2, 1, true, false), EnumItemBreedTypes.NETHER),
		netherDragonScaleLeggings = new ItemNetherDragonArmour(NetherDragonScaleMaterial, 2, EntityEquipmentSlot.LEGS, "nether_dragonscale_leggings", new PotionEffect(MobEffects.RESISTANCE, 2, 1, true, false), EnumItemBreedTypes.NETHER),
		netherDragonScaleBoots = new ItemNetherDragonArmour(NetherDragonScaleMaterial, 1, EntityEquipmentSlot.FEET, "nether_dragonscale_boots", new PotionEffect(MobEffects.RESISTANCE, 2, 1, true, false), EnumItemBreedTypes.NETHER),
		
		enderDragonScaleCap = new ItemEnderDragonArmour(EnderDragonScaleMaterial, 1, EntityEquipmentSlot.HEAD, "ender_dragonscale_cap", new PotionEffect(MobEffects.RESISTANCE, 2, 2, true, false), EnumItemBreedTypes.END),
		enderDragonScaleChestplate = new ItemEnderDragonArmour(EnderDragonScaleMaterial,  1, EntityEquipmentSlot.CHEST, "ender_dragonscale_chestplate", new PotionEffect(MobEffects.RESISTANCE, 2, 2, true, false), EnumItemBreedTypes.END),
		enderDragonScaleLeggings = new ItemEnderDragonArmour(EnderDragonScaleMaterial, 2, EntityEquipmentSlot.LEGS, "ender_dragonscale_leggings", new PotionEffect(MobEffects.RESISTANCE, 2, 2, true, false), EnumItemBreedTypes.END),
		enderDragonScaleBoots = new ItemEnderDragonArmour(EnderDragonScaleMaterial, 1, EntityEquipmentSlot.FEET, "ender_dragonscale_boots", new PotionEffect(MobEffects.RESISTANCE, 2, 2, true, false), EnumItemBreedTypes.END),
		
		dragonarmor_iron = new ItemDragonArmor( "dragonarmor_iron"),
		dragonarmor_gold = new ItemDragonArmor("dragonarmor_gold"),
		dragonarmor_diamond = new ItemDragonArmor("dragonarmor_diamond"),
		
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