package com.TheRPGAdventurer.ROTD.server.network;

import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;
import com.TheRPGAdventurer.ROTD.util.math.MathX;

import io.netty.buffer.ByteBuf;
import net.ilexiconn.llibrary.server.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageDragonBreath extends AbstractMessage<MessageDragonBreath> {
	
	public int dragonId;
	public boolean breathing;

    public boolean isBreathing() {
      return breathing;
    }
	
	public MessageDragonBreath(int dragonId, boolean breathing) {
		this.dragonId = dragonId;
		this.breathing = breathing;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		dragonId = buf.readInt();
		breathing = buf.readBoolean();
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(breathing);	
		buf.writeInt(dragonId);
		
	}

	@Override
	public void onClientReceived(Minecraft arg0, MessageDragonBreath arg1, EntityPlayer arg2, MessageContext arg3) {
		
	}

	@Override
	public void onServerReceived(MinecraftServer server, MessageDragonBreath message, EntityPlayer player,
			MessageContext messageContext) {
		Entity entity = player.world.getEntityByID(message.dragonId);
		if (entity instanceof EntityTameableDragon) {
			EntityTameableDragon dragon = (EntityTameableDragon) entity;
			boolean breathingNow = message.isBreathing();
			boolean headAngleOk = areHeadAnglesWithinTolerance(dragon);
			if(breathingNow && headAngleOk && player.getRidingEntity() == dragon) {
				dragon.setBreathing(true);
			} else {
				dragon.setBreathing(false);
			}
		}
	}
	
	  /**
	   * Check the head yaw and pitch to verify it matches the beam weapon within acceptable limits
	   * @return
	   */
	  private boolean areHeadAnglesWithinTolerance(EntityTameableDragon dragon) {
	    Vec3d origin = dragon.getAnimator().getThroatPosition();
	    Vec3d lookDirection = dragon.getLook(1.0f);
	    Vec3d endOfLook = origin.addVector(lookDirection.x * 20,
	  		  lookDirection.y * 20, 
	  		  lookDirection.z * 20);
	    if (endOfLook == null) return false;
	    double deltaX = endOfLook.x - origin.x;
	    double deltaY = endOfLook.y - origin.y;
	    double deltaZ = endOfLook.z - origin.z;
	    double xzProjectionLength = MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);
	    double yaw = (Math.atan2(deltaZ, deltaX) * 180.0D / Math.PI) - 90.0F;
	    double pitch = (-(Math.atan2(deltaY, xzProjectionLength) * 180.0D / Math.PI));
	    double yawDeviation = MathX.normDeg(yaw - dragon.getRotationYawHead());
	    double pitchDeviation = MathX.normDeg(pitch - dragon.rotationPitch);
	    final double YAW_ANGLE_TOLERANCE = 20;
	    final double PITCH_ANGLE_TOLERANCE = 40;

	    return (Math.abs(yawDeviation) <= YAW_ANGLE_TOLERANCE
	            && Math.abs(pitchDeviation) <= PITCH_ANGLE_TOLERANCE);
	  }
}
