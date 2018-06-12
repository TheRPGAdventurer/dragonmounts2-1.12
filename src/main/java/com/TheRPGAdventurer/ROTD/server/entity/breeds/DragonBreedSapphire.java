package com.TheRPGAdventurer.ROTD.server.entity.breeds;

import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;
import com.TheRPGAdventurer.ROTD.server.entity.helper.breath.BreathNode;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class DragonBreedSapphire extends DragonBreed {

    DragonBreedSapphire() {
        super("sapphire", 0x4f69a8);
        
        addHabitatBlock(Blocks.BLUE_GLAZED_TERRACOTTA);
        addHabitatBlock(Blocks.WATER);
        addHabitatBlock(Blocks.FLOWING_WATER);
        
        addHabitatBiome(Biomes.OCEAN);
        addHabitatBiome(Biomes.RIVER);
        
        addImmunity(DamageSource.IN_FIRE);
        addImmunity(DamageSource.ON_FIRE);
        addImmunity(DamageSource.MAGIC);
        addImmunity(DamageSource.HOT_FLOOR);
        addImmunity(DamageSource.LIGHTNING_BOLT);
        addImmunity(DamageSource.WITHER);
        
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
	
//	@Override
//    public void continueAndUpdateBreathing(World world, Vec3d origin, Vec3d endOfLook, BreathNode.Power power, EntityTameableDragon dragon) {
//		dragon.getBreathHelper().getBreathAffectedAreaIce().continueBreathing(world, origin, endOfLook, power);
//		dragon.getBreathHelper().getBreathAffectedAreaIce().updateTick(world);
 //   }
    
//	@Override
 //   public void spawnBreathParticles(World world, BreathNode.Power power, int tickCounter, Vec3d origin, Vec3d endOfLook, EntityTameableDragon dragon) {
//		dragon.getBreathHelper().getEmitter().setBeamEndpoints(origin, endOfLook);
//		dragon.getBreathHelper().getEmitter().spawnBreathParticlesforIceDragon(world, power, tickCounter);
 //   }
    
}
	
