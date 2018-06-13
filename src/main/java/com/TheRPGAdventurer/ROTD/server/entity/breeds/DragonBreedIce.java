/*
 ** 2013 October 24
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package com.TheRPGAdventurer.ROTD.server.entity.breeds;

import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonBreedIce extends DragonBreed {

    private static final Block FOOTPRINT = Blocks.SNOW_LAYER;
    private static final float FOOTPRINT_CHANCE = 0.2f;

    public DragonBreedIce() {
        super("ice", 0x6fc3ff);

        addImmunity(DamageSource.MAGIC);
        addImmunity(DamageSource.HOT_FLOOR);
        addImmunity(DamageSource.LIGHTNING_BOLT);
        addImmunity(DamageSource.WITHER);

        addHabitatBlock(Blocks.SNOW);
        addHabitatBlock(Blocks.SNOW_LAYER);
        addHabitatBlock(Blocks.ICE);

        addHabitatBiome(Biomes.FROZEN_OCEAN);
        addHabitatBiome(Biomes.FROZEN_RIVER);
        addHabitatBiome(Biomes.ICE_MOUNTAINS);
        addHabitatBiome(Biomes.ICE_PLAINS);
    }

    @Override
    public void onUpdate(EntityTameableDragon dragon) {
        // place some snow footprints where the dragon walks
        if (dragon.isEgg() && !dragon.isFlying()) {
            World world = dragon.world;
            for (int i = 0; i < 4; i++) {
                if (world.rand.nextFloat() < FOOTPRINT_CHANCE) {
                    continue;
                }

                double bx = dragon.posX + (i % 2 * 2 - 1) * 0.25;
                double by = dragon.posY + 0.5;
                double bz = dragon.posZ + (i / 2 % 2 * 2 - 1) * 0.25;
                BlockPos blockPos = new BlockPos(bx, by, bz);
                IBlockState state = world.getBlockState(blockPos);
                // from EntitySnowman.onLivingUpdate, with slight tweaks
                if (world.getBlockState(blockPos).getMaterial() == Material.AIR
                        && world.getBiomeForCoordsBody(blockPos).getFloatTemperature(blockPos) <= 0.8F
                        && FOOTPRINT.canPlaceBlockAt(world, blockPos)) {
                    world.setBlockState(blockPos, FOOTPRINT.getDefaultState());
                }
            }
        }
    }

	@Override
	public void onEnable(com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon dragon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable(com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon dragon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeath(com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon dragon) {
		// TODO Auto-generated method stub
		
	}
}
