package com.TheRPGAdventurer.ROTD.client.gui;

import com.TheRPGAdventurer.ROTD.client.inventory.ContainerDragon;
import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		Entity entity = world.getEntityByID(x);
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

		switch (id) {

			case 0:
				if (entity != null) {
					if (entity instanceof EntityTameableDragon) {
						return new ContainerDragon((EntityTameableDragon) entity, player);
					}
				}
				break;
		}
		return null;

	}

	@SideOnly(Side.CLIENT)
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		Entity entity = world.getEntityByID(x);
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
		switch (id) {

			case 0:
				if (entity != null) {

					if (entity instanceof EntityTameableDragon) {
						return new GuiDragon(player.inventory, (EntityTameableDragon) entity);
					}
				}
				break;
		}
		return entity;
	}
}