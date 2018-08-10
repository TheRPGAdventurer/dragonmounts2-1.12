package com.TheRPGAdventurer.ROTD.server.handler;

import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;
import com.TheRPGAdventurer.ROTD.server.entity.helper.breath.BreathWeapon;
import com.TheRPGAdventurer.ROTD.server.entity.helper.breath.BreathWeaponEnder;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemGlassBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DragonBottleHandler {
	
	ItemGlassBottle bottle = new ItemGlassBottle();
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent.RightClickItem evt) {
		
		EntityAreaEffectCloud cloud = (EntityAreaEffectCloud) evt.getEntity();
		EntityTameableDragon dragon = new EntityTameableDragon(evt.getWorld());
		BreathWeaponEnder ender = new BreathWeaponEnder(dragon);
		
		if(evt.getItemStack() == new ItemStack(bottle) && cloud != null) {
			if(cloud == ender.entityareaeffectcloud) {
				this.turnBottleIntoItem(new ItemStack(bottle), 
				evt.getEntityPlayer(), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER));
			}		
		}		
	}
	
	protected ItemStack turnBottleIntoItem(ItemStack p_185061_1_, EntityPlayer player, ItemStack stack) {
        p_185061_1_.shrink(1);
        player.addStat(StatList.getObjectUseStats(bottle));

        if (p_185061_1_.isEmpty()) {
            return stack;
        } else {
            if (!player.inventory.addItemStackToInventory(stack)) {
                player.dropItem(stack, false);
           }     return p_185061_1_;
        }
    }
}
