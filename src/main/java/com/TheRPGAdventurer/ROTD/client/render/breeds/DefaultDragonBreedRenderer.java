/*
** 2016 March 07
**
** The author disclaims copyright to this source code. In place of
** a legal notice, here is a blessing:
**    May you do good and not evil.
**    May you find forgiveness for yourself and forgive others.
**    May you share freely, never taking more than you give.
 */
package com.TheRPGAdventurer.ROTD.client.render.breeds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.TheRPGAdventurer.ROTD.DragonMounts;
import com.TheRPGAdventurer.ROTD.client.model.DragonModel;
import com.TheRPGAdventurer.ROTD.client.render.DragonRenderer;
import com.TheRPGAdventurer.ROTD.client.render.layers.LayerRendererDragonArmor;
import com.TheRPGAdventurer.ROTD.client.render.layers.LayerRendererDragonChest;
import com.TheRPGAdventurer.ROTD.client.render.layers.LayerRendererDragonGlow;
import com.TheRPGAdventurer.ROTD.client.render.layers.LayerRendererDragonSaddle;
import com.TheRPGAdventurer.ROTD.server.entity.EntityTameableDragon;
import com.TheRPGAdventurer.ROTD.server.entity.breeds.EnumDragonBreed;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DefaultDragonBreedRenderer implements DragonBreedRenderer {
    
    protected final List<LayerRenderer<EntityTameableDragon>> layers = new ArrayList<>();
    
    private final DragonRenderer renderer;
    private final DragonModel model;
    private EntityTameableDragon dragon;
    
    private final ResourceLocation bodyTexture;
    private final ResourceLocation glowTexture;
    private final ResourceLocation glowAnimTexture;
    private final ResourceLocation saddleTexture;
    private final ResourceLocation eggTexture;
    private final ResourceLocation dissolveTexture;
    private final ResourceLocation chestTexture;
//    private final ResourceLocation armorTexture;

    public DefaultDragonBreedRenderer(DragonRenderer parent, EnumDragonBreed breed) {
        renderer = parent;
        model = new DragonModel(breed);
        
        // standard layers
        layers.add(new LayerRendererDragonGlow(parent, this, model));
//        layers.add(new LayerRendererDragonGlowAnim(parent, this, model));
        layers.add(new LayerRendererDragonSaddle(parent, this, model));
        layers.add(new LayerRendererDragonArmor(parent, this, model));
        layers.add(new LayerRendererDragonChest(parent, this, model));
        
        // textures
        String skin = breed.getBreed().getSkin();
        bodyTexture = new ResourceLocation(DragonMounts.MODID, DragonRenderer.TEX_BASE + skin + "/body.png");
        glowTexture = new ResourceLocation(DragonMounts.MODID, DragonRenderer.TEX_BASE + skin + "/glow.png");
        glowAnimTexture = new ResourceLocation(DragonMounts.MODID, DragonRenderer.TEX_BASE + skin + "/glow_anim.png");
        saddleTexture = new ResourceLocation(DragonMounts.MODID, DragonRenderer.TEX_BASE + skin + "/saddle.png");
        eggTexture = new ResourceLocation(DragonMounts.MODID, DragonRenderer.TEX_BASE + skin + "/egg.png");
        dissolveTexture = new ResourceLocation(DragonMounts.MODID, DragonRenderer.TEX_BASE + "dissolve.png");
        chestTexture = new ResourceLocation(DragonMounts.MODID, DragonRenderer.TEX_BASE + skin + "/chest.png");
    }
    
    @Override
    public List<LayerRenderer<EntityTameableDragon>> getLayers() {
        return Collections.unmodifiableList(layers);
    }

    @Override
    public DragonRenderer getRenderer() {
        return renderer;
    }

    @Override
    public DragonModel getModel() {
        return model;
    }

    @Override
    public ResourceLocation getBodyTexture() {
        return bodyTexture;
    }

    @Override
    public ResourceLocation getGlowTexture() {
        return glowTexture;
    }
    
    @Override
    public ResourceLocation getGlowAnimTexture() {
        return glowAnimTexture;
    }

    @Override
    public ResourceLocation getSaddleTexture() {
        return saddleTexture;
    }

    @Override
    public ResourceLocation getEggTexture() {
        return eggTexture;
    }

    @Override
    public ResourceLocation getDissolveTexture() {
        return dissolveTexture;
    }

	@Override
	public ResourceLocation getChestTexture() {
		return chestTexture;
	}

	@Override
	public ResourceLocation getArmorTexture() {
		return null;
	}
}
