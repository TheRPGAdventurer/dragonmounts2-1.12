/*
 ** 2013 May 30
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package com.TheRPGAdventurer.ROTD;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DragonMountsConfig {
    
    private static Configuration config;
    
    public static final String CATEGORY_MAIN = "main";
    public static final String CATEGORY_WORLDGEN = "worldGen";
    
    // config properties
    private static boolean disableBlockOverride = false;
    private static boolean debug = false;
	public static boolean shouldChangeBreedViaHabitatOrBlock = true;
    public static boolean canDragonDespawn = true;
    public static boolean useCommandingPlayer = false;
	
    public static boolean canSpawnSurfaceDragonNest = true;
	public static boolean canSpawnUnderGroundNest = false;
	public static boolean canSpawnNetherNest = true;
	public static boolean canSpawnNetherBoneNest = true;
	
	public static boolean canFireSetFire = true;
    
	// chances
	public static int MainNestRarity  = 300;
	public static int MainNestRarerityInX = 16;
	public static int MainNestRarerityInZ = 16;
	
	public static int netherNestRarity = 480;
	public static int netherNestRarerityInX = 16;
	public static int netherNestRarerityInZ = 16;
	
	public static int undergroundrarityMain = 50;
	public static int undergroundnestX = 64;
	public static int undergroundnestZ = 64;
	
	public static int boneNestMainRarity = 420;
	public static int boneNestRarerityInX = 16;
	public static int boneNestRarerityInZ = 16;
	
	// dragon
	public static int GET_TICKS_PER_STAGE = 24000;
    
    public static void PreInit() {
    	File configFile = new File(Loader.instance().getConfigDir(), "rotd.cfg");
    	config = new Configuration(configFile);
    	syncFromFiles();
    }
    
    public static void clientPreInit() {
    	MinecraftForge.EVENT_BUS.register(new ConfigEventHadler());
    }
    
    public static void syncFromFiles() {
    	syncconfigs(true, true);
    }
    
    public static void syncFromGui() {
    	syncconfigs(false, true);
    }
    
    public static void syncFromFields() {
    	syncconfigs(false, false);
    }
    
    public static Configuration getConfig() {return config;}

    public static boolean isDebug() {return debug;}

    public static boolean isDisableBlockOverride() {
        return disableBlockOverride;
    }	
    
	private static void syncconfigs(boolean loadFromConfigFile, boolean readFromConfig) {
		if(loadFromConfigFile) 
			config.load();		
		
		List<String> propOrder = Lists.newArrayList();
		Property prop;
		
		/**
		 *  MAIN
		 */
		prop = config.get(CATEGORY_MAIN, "debug", debug);
		prop.setComment("Debug mode. Unless you're a developer or are told to activate it, you don't want to set this to true.");
		debug = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_MAIN, "can eggs change breeds", shouldChangeBreedViaHabitatOrBlock);
		prop.setComment("Enables changing of egg breeds via block or environment");
		shouldChangeBreedViaHabitatOrBlock = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_MAIN, "disable block override", disableBlockOverride);
		prop.setComment("Disables right-click override on the vanilla dragon egg block. May help to fix issues with other mods.");
		disableBlockOverride = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_MAIN, "can dragons despawn", canDragonDespawn);
		prop.setComment("Enables or Disables dragons ability to despawn, works only for adult non tamed dragons");
		canDragonDespawn = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_MAIN, "use CommandingPlayer", useCommandingPlayer);
		prop.setComment("Use a commanding player method(Experimental) to make dragons land on multiple players");
		useCommandingPlayer = prop.getBoolean();
		propOrder.add(prop.getName());
        
		/**
		 *  WORLDGEN
		 */
        prop = config.get(CATEGORY_WORLDGEN, "canSpawnSurfaceDragonNest", canSpawnSurfaceDragonNest);
		prop.setComment("Enables spawning of nests in extreme hills");
		canSpawnSurfaceDragonNest = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_WORLDGEN, "canSpawnUnderGroundNest", canSpawnUnderGroundNest);
		prop.setComment("Enables spawning of dragon nests in underground caves (Buggy consumes a lot of memory :()");
		canSpawnUnderGroundNest = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_WORLDGEN, "canSpawnNetherNest", canSpawnNetherNest);
		prop.setComment("Enables spawning of nether dragon nests in the nether");
		canSpawnNetherNest = prop.getBoolean();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_WORLDGEN, "canSpawnNetherBoneNest", canSpawnNetherBoneNest);
		prop.setComment("Enables spawning of bone dragon nests in the nether");
		canSpawnNetherBoneNest = prop.getBoolean();
		propOrder.add(prop.getName());
		
		// chances
		
		// underground nest
		prop = config.get(CATEGORY_WORLDGEN, "0 underground nest main rarity", undergroundrarityMain);
		prop.setComment("Determines how rare underground nests will spawn. Higher numbers = higher rarity (in other words how many blocks for another nest to spawn),"
				+ "(Note: Expermiment on a new world when editing these numbers because it may cause damages to your own worlds)");
		undergroundrarityMain = prop.getInt();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_WORLDGEN, "0 undergroundNestX", undergroundnestX);
		prop.setComment("Determines how rare underground nests will spawn. Higher numbers = higher rarity (in other words how many blocks for another nest to spawn),"
				+ "(Note: Expermiment on a new world when editing these numbers because it may cause damages to your own worlds)");
		undergroundnestX = prop.getInt();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_WORLDGEN, "0 undergroundNestZ", undergroundnestZ);
		prop.setComment("Determines how rare underground nests will spawn. Higher numbers = higher rarity (in other words how many blocks for another nest to spawn),"
				+ "(Note: Expermiment on a new world when editing these numbers because it may cause damages to your own worlds)");
		undergroundnestZ = prop.getInt();
		propOrder.add(prop.getName());
		
		// surface extreme hills nest
		prop = config.get(CATEGORY_WORLDGEN, "1 Main Nest Rarity", MainNestRarity);
		prop.setComment("Determines how rare extreme hill nests will mainly spawn. Higher numbers = higher rarity (in other words  how many blocks for another nest to spawn), "
				+ "(Note: Expermiment on a new world when editing these numbers because it may cause damages to your own worlds)");
		MainNestRarity = prop.getInt();
		propOrder.add(prop.getName());
		
		// nether nest
		prop = config.get(CATEGORY_WORLDGEN, "2 Nether Nest Chance", netherNestRarity);
		prop.setComment("Determines how rare nether nests will mainly spawn. Higher numbers = higher rarity (in other words  how many blocks for another nest to spawn)"
				+ "(Note: Expermiment on a new world when editing these numbers because it may cause damages to your own worlds)");
		netherNestRarity = prop.getInt();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_WORLDGEN, "2 Nether Nest Rarity X", netherNestRarerityInX);
		prop.setComment("Determines how rare nether nests will spawn in the X Axis. Higher numbers = higher rarity (in other words  how many blocks for another nest to spawn)"
				+ "(Note: Expermiment on a new world when editing these numbers because it may cause damages to your own worlds)");
		netherNestRarerityInX = prop.getInt();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_WORLDGEN, "2 Nest Nether Rarity Z", netherNestRarerityInZ);
		prop.setComment("Determines how rare nether nests will spawn in the Z Axis. Higher numbers = higher rarity (in other words  how many blocks for another nest to spawn)"
				+ "(Note: Expermiment on a new world when editing these numbers because it may cause damages to your own worlds)");
		netherNestRarerityInZ = prop.getInt();
		propOrder.add(prop.getName());
		
		// bone nests
		prop = config.get(CATEGORY_WORLDGEN, "3 Bone Nest Rarity", boneNestMainRarity);
		prop.setComment("Determines how rare bone nests will mainly spawn. Higher numbers = higher rarity (in other words  how many blocks for another nest to spawn)"
				+ "(Note: Expermiment on a new world when editing these numbers because it may cause damages to your own worlds)");
		boneNestMainRarity = prop.getInt();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_WORLDGEN, "3 Bone nest Rarity X", boneNestRarerityInX);
		prop.setComment("Determines how rare bone nests will spawn in the X Axis. Higher numbers = higher rarity (in other words  how many blocks for another nest to spawn)"
				+ "(Note: Expermiment on a new world when editing these numbers because it may cause damages to your own worlds)");
		boneNestRarerityInX = prop.getInt();
		propOrder.add(prop.getName());
		
		prop = config.get(CATEGORY_WORLDGEN, "3 Bone nest Rarity Z", boneNestRarerityInZ);
		prop.setComment("Determines how rare bone nests will spawn Z Axis. Higher numbers = higher rarity (in other words  how many blocks for another nest to spawn)"
				+ "(Note: Expermiment on a new world when editing these numbers because it may cause damages to your own worlds)");
		boneNestRarerityInZ = prop.getInt();
		propOrder.add(prop.getName());
		
		config.setCategoryPropertyOrder(CATEGORY_WORLDGEN, propOrder);
		config.save();
		
        if (config.hasChanged()) {
        	config.save();
        }
	}
	
	public static class ConfigEventHadler {
		
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
			if(event.getModID().equals(DragonMounts.MODID)) {
				syncFromGui();
			}
		}
	}
  
}
