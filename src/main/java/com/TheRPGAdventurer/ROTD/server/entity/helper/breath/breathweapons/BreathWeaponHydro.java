package com.TheRPGAdventurer.ROTD.server.entity.helper.breath.breathweapons;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import com.TheRPGAdventurer.ROTD.DragonMountsConfig;
import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;
import com.TheRPGAdventurer.ROTD.server.entity.helper.breath.BreathAffectedBlock;
import com.TheRPGAdventurer.ROTD.server.entity.helper.breath.BreathAffectedEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

/**
 * Created by TGG on 5/08/2015.
 *
 * Models the effects of a breathweapon on blocks and entities
 * Usage: 1)
 * Construct with a parent dragon 2) affectBlock() to apply an area of effect to
 * the given block (eg set fire to it) 3) affectEntity() to apply an area of
 * effect to the given entity (eg damage it)
 *
 */
public class BreathWeaponHydro extends BreathWeapon {

	public BreathWeaponHydro(EntityTameableDragon i_dragon) {
		super(i_dragon);
	}

	/**
	 * if the hitDensity is high enough, manipulate the block (eg set fire to it)
	 * 
	 * @param world
	 * @param blockPosition
	 *            the world [x,y,z] of the block
	 * @param currentHitDensity
	 * @return the updated block hit density
	 */
	@Override
	public BreathAffectedBlock affectBlock(World world, Vec3i blockPosition, BreathAffectedBlock currentHitDensity) {
		checkNotNull(world);
		checkNotNull(blockPosition);
		checkNotNull(currentHitDensity);

		BlockPos blockPos = new BlockPos(blockPosition);
		IBlockState iBlockState = world.getBlockState(blockPos);
		Block block = iBlockState.getBlock();

		Random rand = new Random();
		BlockPos sideToIgnite = blockPos.offset(EnumFacing.UP);
		// if (DragonMountsConfig.canBreathSetIce ) {
		// world.setBlockState(sideToIgnite, Blocks.SNOW_LAYER.getDefaultState());} else

		world.spawnParticle(EnumParticleTypes.WATER_SPLASH, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0D,
				4.0D, 1.0D);

		if (block == Blocks.LAVA) {
			world.playSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH,
					SoundCategory.BLOCKS, 0.7f, 1.0f, false);
			world.setBlockState(blockPos, Blocks.OBSIDIAN.getDefaultState());
		}

		if (block == Blocks.FLOWING_LAVA) {
			world.playSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH,
					SoundCategory.BLOCKS, 0.7f, 1.0f, false);
			world.setBlockState(blockPos, Blocks.COBBLESTONE.getDefaultState());
		}

		if (block == Blocks.FIRE) {
			world.playSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH,
					SoundCategory.BLOCKS, 0.7f, 1.0f, false);
			world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
		}

		if (block == Blocks.FARMLAND) {
			Blocks.FARMLAND.isFertile(world, blockPos);
		}

		return new BreathAffectedBlock(); // reset to zero
	}

	/**
	 * if the hitDensity is high enough, manipulate the entity (eg set fire to it,
	 * damage it) A dragon can't be damaged by its own breathweapon;
	 * 
	 * @param world
	 * @param entityID
	 *            the ID of the affected entity
	 * @param currentHitDensity
	 *            the hit density
	 * @return the updated hit density; null if the entity is dead, doesn't exist,
	 *         or otherwise not affected
	 */
	@Override
	public BreathAffectedEntity affectEntity(World world, Integer entityID, BreathAffectedEntity currentHitDensity) {
		checkNotNull(world);
		checkNotNull(entityID);
		checkNotNull(currentHitDensity);

		if (entityID == dragon.getEntityId())
			return null;
		if (dragon.getControllingPassenger() != null) {
			if (entityID == dragon.getControllingPlayer().getEntityId())
				return null;
		}

		Entity entity = world.getEntityByID(entityID);
		if (entity == null || !(entity instanceof Entity) || entity.isDead) {
			return null;
		}

		final float DAMAGE_PER_HIT_DENSITY = 3.0F;

		float hitDensity = currentHitDensity.getHitDensity();

		if (entity instanceof EntityTameable) {
			EntityTameable entityTameable = (EntityTameable) entity;
			if (entityTameable.isTamed()) {
				return null;
			} else {
				entityTameable.attackEntityFrom(DamageSource.causeMobDamage(dragon), DAMAGE_PER_HIT_DENSITY);
			}
		} else {
			entity.attackEntityFrom(DamageSource.causeMobDamage(dragon), DAMAGE_PER_HIT_DENSITY);
		}

		if (entity.isBurning()) {
			entity.extinguish();
			entity.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 1.0f, 0.0f);
		}
		
		entity.isWet();		
		((EntityLivingBase) entity).knockBack(entity, 0.2F, dragon.posX - entity.posX, dragon.posZ - entity.posZ);
		PotionEffect iceEffect = new PotionEffect(MobEffects.SLOWNESS, 50 * 10);
		((EntityLivingBase) entity).addPotionEffect(iceEffect); // Apply a copy of the PotionEffect to the entity
		entity.playSound(SoundEvents.ENTITY_GENERIC_SPLASH, 0.4f, 1.0f);

		return currentHitDensity;
	}
}
