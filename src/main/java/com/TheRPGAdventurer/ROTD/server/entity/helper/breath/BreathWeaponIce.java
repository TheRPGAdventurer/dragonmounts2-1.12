package com.TheRPGAdventurer.ROTD.server.entity.helper.breath;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import com.TheRPGAdventurer.ROTD.DragonMountsConfig;
import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

/**
 * Created by TGG on 5/08/2015.
 *
 * Models the effects of a breathweapon on blocks and entities
 * Usage:
 * 1) Construct with a parent dragon
 * 2) affectBlock() to apply an area of effect to the given block (eg set fire to it)
 * 3) affectEntity() to apply an area of effect to the given entity (eg damage it)
 *
 * Currently does fire only.  Intended to be subclassed later on for different weapon types.
 */
public class BreathWeaponIce extends BreathWeapon {
	
  protected EntityTameableDragon dragon;
	
  public BreathWeaponIce(EntityTameableDragon i_dragon) {
    super(i_dragon);
  }

  /** if the hitDensity is high enough, manipulate the block (eg set fire to it)
   * @param world
   * @param blockPosition  the world [x,y,z] of the block
   * @param currentHitDensity
   * @return the updated block hit density
   */
  public BreathAffectedBlock affectBlock(World world, Vec3i blockPosition, BreathAffectedBlock currentHitDensity) {
    checkNotNull(world);
    checkNotNull(blockPosition);
    checkNotNull(currentHitDensity);

    BlockPos blockPos = new BlockPos(blockPosition);
    IBlockState iBlockState = world.getBlockState(blockPos);
    Block block = iBlockState.getBlock();

    Random rand = new Random();
    
    int flammability;
    for (EnumFacing facing : EnumFacing.values()) {
      BlockPos sideToIgnite = blockPos.offset(facing);
      if (block.isFlammable(world, sideToIgnite, facing)) {
        flammability = block.getFlammability(world, sideToIgnite, facing);
        float thresholdForIgnition = convertFlammabilityToHitDensityThreshold(flammability);
        float thresholdForDestruction = thresholdForIgnition * 50;
        float densityOfThisFace = currentHitDensity.getHitDensity(facing);
        if (densityOfThisFace >= thresholdForIgnition && world.isAirBlock(sideToIgnite) && DragonMountsConfig.canFireSetFire) {
          final float MIN_PITCH = 0.8F;
          final float MAX_PITCH = 1.2F;
          final float VOLUME = 1.0F;
          world.playSound(null, new BlockPos(sideToIgnite.getX() + 0.5, sideToIgnite.getY() + 0.5, sideToIgnite.getZ() + 0.5),
                  SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.NEUTRAL, VOLUME, MIN_PITCH + rand.nextFloat() * (MAX_PITCH - MIN_PITCH));
          world.setBlockState(sideToIgnite, Blocks.SNOW_LAYER.getDefaultState());
        }
        
      }
    }
    return new BreathAffectedBlock();  // reset to zero
  }
  /** if the hitDensity is high enough, manipulate the entity (eg set fire to it, damage it)
   * A dragon can't be damaged by its own breathweapon;
   * @param world
   * @param entityID  the ID of the affected entity
   * @param currentHitDensity the hit density
   * @return the updated hit density; null if the entity is dead, doesn't exist, or otherwise not affected
   */
  public BreathAffectedEntity affectEntity(World world, Integer entityID, BreathAffectedEntity currentHitDensity) {
    checkNotNull(world);
    checkNotNull(entityID);
    checkNotNull(currentHitDensity);

    if (entityID == dragon.getEntityId()) return null;
    if(dragon.getControllingPassenger() != null) {
    if (entityID == dragon.getControllingPlayer().getEntityId()) return null;}

    Entity entity = world.getEntityByID(entityID);
    if (entity == null || !(entity instanceof Entity) || entity.isDead) {
      return null;
    }
    
    final float DAMAGE_PER_HIT_DENSITY = 4.5F;

    float hitDensity = currentHitDensity.getHitDensity();
//    if (currentHitDensity.applyDamageThisTick()) {
          entity.attackEntityFrom(DamageSource.causeMobDamage(dragon), DAMAGE_PER_HIT_DENSITY);
   //       entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40*10, 2));
  //  }

    return currentHitDensity;
  }
}
