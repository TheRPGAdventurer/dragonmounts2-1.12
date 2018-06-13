package com.TheRPGAdventurer.ROTD.server.entity.breeds;

import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.registry.EntityRegistry;


public class DragonBreedForest extends DragonBreed {

    DragonBreedForest() {
        super("forest", 0x298317);
        
        addHabitatBlock(Blocks.YELLOW_FLOWER);
        addHabitatBlock(Blocks.RED_FLOWER);
        addHabitatBlock(Blocks.MOSSY_COBBLESTONE);
        addHabitatBlock(Blocks.VINE);
        addHabitatBlock(Blocks.SAPLING);
        
        addHabitatBiome(Biomes.JUNGLE);
        addHabitatBiome(Biomes.JUNGLE_HILLS);
        
    }

    @Override
    public void onEnable(EntityTameableDragon dragon) {}

    @Override
    public void onDisable(EntityTameableDragon dragon) {}

    @Override
    public void onDeath(EntityTameableDragon dragon) {}
    
	@Override
	public boolean canChangeBreed() {
		return true;
	}
    
}
	
