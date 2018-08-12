package com.TheRPGAdventurer.ROTD.server.entity.helper.breath.breathweapons;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import com.TheRPGAdventurer.ROTD.DragonMounts;
import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;
import com.TheRPGAdventurer.ROTD.server.entity.helper.breath.BreathAffectedBlock;
import com.TheRPGAdventurer.ROTD.server.entity.helper.breath.BreathAffectedEntity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
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
 */
public class BreathWeaponEnder extends BreathWeapon {
	
  public EntityAreaEffectCloud entityareaeffectcloud;
	
  public BreathWeaponEnder(EntityTameableDragon i_dragon) {
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
   
    EntityDragon dragon1 = new EntityDragon(world);
    
    if (!world.isRemote) { 
        entityareaeffectcloud = new EntityAreaEffectCloud(world, blockPos.getX(), blockPos.getY(), blockPos.getZ());
        entityareaeffectcloud.setOwner(dragon1);
        entityareaeffectcloud.setParticle(EnumParticleTypes.DRAGON_BREATH);
        entityareaeffectcloud.setRadius(1.6F);
        entityareaeffectcloud.setDuration(750);
        entityareaeffectcloud.setRadiusPerTick((1.0F - entityareaeffectcloud.getRadius()) / (float)entityareaeffectcloud.getDuration());
        entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.WITHER, 150, 1));

        entityareaeffectcloud.setPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());                 
        int i = rand.nextInt(10000);
        if(i < 10) {
        world.spawnEntity(entityareaeffectcloud);
      }
    }  return new BreathAffectedBlock();  // reset to zero
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
    
    final float DAMAGE_PER_HIT_DENSITY = 4.9F;

    float hitDensity = currentHitDensity.getHitDensity();
    entity.attackEntityFrom(DragonMounts.DRAGON_BREATH, DAMAGE_PER_HIT_DENSITY);

    return currentHitDensity;
  }
}
