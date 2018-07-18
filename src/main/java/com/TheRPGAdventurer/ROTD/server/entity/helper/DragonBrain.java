/*
** 2016 March 12
**
** The author disclaims copyright to this source code. In place of
** a legal notice, here is a blessing:
**    May you do good and not evil.
**    May you find forgiveness for yourself and forgive others.
**    May you share freely, never taking more than you give.
 */
package com.TheRPGAdventurer.ROTD.server.entity.helper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;
import com.TheRPGAdventurer.ROTD.server.entity.ai.EntityAIDragonAttack;
import com.TheRPGAdventurer.ROTD.server.entity.ai.EntityAIDragonCatchOwner;
import com.TheRPGAdventurer.ROTD.server.entity.ai.EntityAIDragonRide;
import com.TheRPGAdventurer.ROTD.server.entity.ai.air.EntityAIDragonFollowOwnerElytraFlying;
import com.TheRPGAdventurer.ROTD.server.entity.ai.air.EntityAIDragonLandAndCommenceFlyByAttack;
import com.TheRPGAdventurer.ROTD.server.entity.ai.ground.EntityAIDragonFollowOwner;
import com.TheRPGAdventurer.ROTD.server.entity.ai.ground.EntityAIDragonHunt;
import com.TheRPGAdventurer.ROTD.server.entity.ai.ground.EntityAIDragonMate;
import com.TheRPGAdventurer.ROTD.server.entity.ai.ground.EntityAIDragonWatchIdle;
import com.TheRPGAdventurer.ROTD.server.entity.ai.ground.EntityAIDragonWatchLiving;
import com.TheRPGAdventurer.ROTD.server.entity.ai.targeting.EntityAIBreathAttack;
import com.TheRPGAdventurer.ROTD.server.util.EntityClassPredicate;
import com.google.common.base.Predicate;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonBrain extends DragonHelper {

    // mutex 1: movement
    // mutex 2: looking
    // mutex 4: special state
    private final EntityAITasks tasks;
    
    // mutex 1: waypointing
    // mutex 2: continuous waypointing
    
    // mutex 1: generic targeting
    private final EntityAITasks targetTasks;
    public final EntityAITasks attackTasks;
    
    public DragonBrain(EntityTameableDragon dragon) {
        super(dragon);
        tasks = dragon.tasks;
        targetTasks = dragon.targetTasks;
        attackTasks = dragon.attackTasks;
    }
    
    public void setAvoidsWater(boolean avoidWater) {
        PathNavigate pathNavigate = dragon.getNavigator();
        if (pathNavigate instanceof PathNavigateGround) {
            PathNavigateGround pathNavigateGround = (PathNavigateGround) pathNavigate;
            pathNavigateGround.setCanSwim(!avoidWater); // originally setAvoidsWater()
        }
    }
    
    public void clearTasks() {
        clearTasks(tasks);
        clearTasks(targetTasks);
    }
    
    public void clearTasks(EntityAITasks tasks) {
        List<EntityAITaskEntry> taskEntries = new ArrayList<>(tasks.taskEntries);
        taskEntries.forEach(entry -> tasks.removeTask(entry.action));
    }
    
    public void updateAITasks() {
        // only hatchlings are small enough for doors
        // (eggs don't move on their own anyway and are ignored)
        // guessed, based on EntityAIRestrictOpenDoor - break the door down, don't open it
        if (dragon.getNavigator() instanceof PathNavigateGround) {
            PathNavigateGround pathNavigateGround = (PathNavigateGround) dragon.getNavigator();
            pathNavigateGround.setEnterDoors(dragon.isHatchling());
        }
        
        // clear current navigation target
        dragon.getNavigator().clearPathEntity();
        
        // clear existing tasks
        clearTasks();
        
        // eggs don't have any tasks
        if (dragon.isEgg()) {
            return;
        }
        
        float minAttackRange = 0;
        float maxAttackRange = 0;
        switch (dragon.getLifeStageHelper().getLifeStage()) {
            case EGG:
                break;
            case HATCHLING: {
                minAttackRange = 2.0F;
                maxAttackRange = 4.0F;
                break;
            }
            case JUVENILE: {
                minAttackRange = 3.0F;
                maxAttackRange = 8.0F;
                break;
            }
            case ADULT: {
                minAttackRange = 5.0F;
                maxAttackRange = 25.0F;
                break;
            }
            default: {
                System.err.println("Unknown lifestage:" + dragon.getLifeStageHelper().getLifeStage());
                break;
            }
        }
        
        tasks.addTask(0, new EntityAIDragonCatchOwner(dragon)); // mutex all
        tasks.addTask(1, new EntityAIDragonRide(dragon)); // mutex all
        tasks.addTask(2, dragon.getAISit()); // mutex 4+1
        tasks.addTask(3, new EntityAIDragonFollowOwnerElytraFlying(dragon)); // mutex all
        tasks.addTask(4, new EntityAIMoveTowardsRestriction(dragon, 1)); // mutex 1
        
        if (dragon.isFlying()) {
            tasks.addTask(2, new EntityAIDragonLandAndCommenceFlyByAttack(dragon, 1)); // mutex 1
        } else {
            tasks.addTask(2, new EntityAISwimming(dragon)); // mutex 4   

            tasks.addTask(6, new EntityAITempt(dragon, 0.75, dragon.getBreed().getBreedingItem(), false)); // mutex 2+1
            tasks.addTask(7, new EntityAIAttackMelee(dragon, 1, true)); // mutex 2+1
                       
            tasks.addTask(9, new EntityAIDragonFollowOwner(dragon, 1, 15, 128)); // mutex 2+1
            tasks.addTask(9, new EntityAIDragonFollowOwnerElytraFlying(dragon)); // mutex 2+1
            tasks.addTask(10, new EntityAIWander(dragon, 1)); // mutex 1
            tasks.addTask(11, new EntityAIDragonWatchIdle(dragon)); // mutex 2
            tasks.addTask(11, new EntityAIDragonWatchLiving(dragon, 16, 0.05f)); // mutex 2
            
            attackTasks.addTask(5, new EntityAINearestAttackableTarget(dragon, EntityLiving.class, 13, false, true, new Predicate<EntityLiving>(){public boolean apply(@Nullable EntityLiving p_apply_1_){return p_apply_1_ != null && IMob.VISIBLE_MOB_SELECTOR.apply(p_apply_1_);}}));
            attackTasks.addTask(5, new EntityAIDragonHunt(dragon, EntityAnimal.class, false, new EntityClassPredicate(EntitySheep.class, EntityPig.class, EntityChicken.class, EntityRabbit.class, EntityLlama.class))); // mutex 1
            
        }
                targetTasks.addTask(2, new EntityAIOwnerHurtByTarget(dragon)); // mutex 1
                targetTasks.addTask(3, new EntityAIOwnerHurtTarget(dragon)); // mutex 1
                targetTasks.addTask(4, new EntityAIHurtByTarget(dragon, true, new Class[] {})); // mutex 1
            
            if (dragon.isHatchling()) {
                tasks.addTask(5, new EntityAILeapAtTarget(dragon, 0.4F)); // mutex 1
            } else {                                    

            if (dragon.isAdult()) {
                tasks.addTask(5, new EntityAIDragonMate(dragon, 0.6)); // mutex 2+1
            }
        }
    }
}
