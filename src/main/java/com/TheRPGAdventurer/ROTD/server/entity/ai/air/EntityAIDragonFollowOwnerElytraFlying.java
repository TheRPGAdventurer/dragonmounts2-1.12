/*
** 2016 April 26
**
** The author disclaims copyright to this source code. In place of
** a legal notice, here is a blessing:
**    May you do good and not evil.
**    May you find forgiveness for yourself and forgive others.
**    May you share freely, never taking more than you give.
 */
package com.TheRPGAdventurer.ROTD.server.entity.ai.air;

import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;
import com.TheRPGAdventurer.ROTD.server.entity.ai.EntityAIDragonBase;

import net.minecraft.entity.player.EntityPlayer;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIDragonFollowOwnerElytraFlying extends EntityAIDragonBase {
    
    protected EntityPlayer owner;

    public EntityAIDragonFollowOwnerElytraFlying(EntityTameableDragon dragon) {
        super(dragon);
    }

    @Override
    public boolean shouldExecute() {
    	
    	if (!dragon.canFly()) {
    		return false;
    	}
        
        // don't follow if sitting
        if (dragon.isDragonSitting()) {
        	return false;
        }
        
//       owner = (EntityPlayer) dragon.getOwner();
        owner = dragon.getCommandingPlayer();
        
        // don't follow if ownerless 
        if (owner == null) {
            return false;
        }
        
        // don't follow if already being ridden
        if (dragon.isPassenger(owner)) {
            return false;
        }
        
        // follow only if the owner is using an Elytra
        return owner.isElytraFlying();
    }
    
    @Override
    public void updateTask() {
        dragon.getNavigator().tryMoveToEntityLiving(owner, 1);
    }
}
