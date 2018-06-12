package com.TheRPGAdventurer.ROTD.client.render.layers;

import static org.lwjgl.opengl.GL11.GL_ONE;

import com.TheRPGAdventurer.ROTD.client.model.DragonModel;
import com.TheRPGAdventurer.ROTD.client.model.DragonModelMode;
import com.TheRPGAdventurer.ROTD.client.render.DragonRenderer;
import com.TheRPGAdventurer.ROTD.client.render.breeds.DefaultDragonBreedRenderer;
import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;

import net.minecraft.client.renderer.GlStateManager;

/**
 * Created by EveryoneElse on 14/06/2015.
 */
public class LayerRendererDragonGlow extends LayerRendererDragon {

    public LayerRendererDragonGlow(DragonRenderer renderer,
            DefaultDragonBreedRenderer breedRenderer, DragonModel model) {
        super(renderer, breedRenderer, model);
    }

    @Override
    public void doRenderLayer(EntityTameableDragon dragon, float moveTime,
            float moveSpeed, float partialTicks, float ticksExisted, float lookYaw,
            float lookPitch, float scale) {
        renderer.bindTexture(breedRenderer.getGlowTexture());
        
        GlStateManager.pushAttrib();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL_ONE, GL_ONE);
        GlStateManager.color(1, 1, 1, 1);

        disableLighting();
        model.setMode(DragonModelMode.FULL);
        model.render(dragon, moveTime, moveSpeed, ticksExisted, lookYaw, lookPitch, scale);
        enableLighting(dragon.getBrightnessForRender());
        
        GlStateManager.disableBlend();
        GlStateManager.popAttrib();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
