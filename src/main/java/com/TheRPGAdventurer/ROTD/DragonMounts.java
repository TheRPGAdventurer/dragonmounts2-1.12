/*
 ** 2012 August 13
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package com.TheRPGAdventurer.ROTD;

import java.util.Random;

import com.TheRPGAdventurer.ROTD.client.gui.GuiHandler;
import com.TheRPGAdventurer.ROTD.client.inventory.CreativeTab;
import com.TheRPGAdventurer.ROTD.client.message.MessageDragonBreath;
import com.TheRPGAdventurer.ROTD.server.ServerProxy;
import com.TheRPGAdventurer.ROTD.server.network.MessageDragonArmor;
import com.TheRPGAdventurer.ROTD.server.world.DragonMountsWorldGenerator;

import net.ilexiconn.llibrary.server.network.NetworkWrapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Main control class for Forge.
 * 
 */
@Mod(
    dependencies = "required-after:llibrary@[" + DragonMounts.LLIBRARY_VERSION + ",)",
    modid = DragonMounts.MODID,
    name = DragonMounts.NAME,
    version = DragonMounts.VERSION,
    useMetadata = true,
    guiFactory = DragonMounts.GUI_FACTORY
)

public class DragonMounts {
    
	@NetworkWrapper({MessageDragonArmor.class, MessageDragonBreath.class})
	public static SimpleNetworkWrapper NETWORK_WRAPPER;
	
    public static final String NAME = "Dragon Mounts";
    public static final String MODID = "dragonmounts";
    public static final String VERSION = "1.12-1.4.5-SNAPSHOT-3";
    public static final String LLIBRARY_VERSION = "1.7.9";
    public static final String GUI_FACTORY = "com.TheRPGAdventurer.ROTD.DragonMountsConfigGuiFactory";
    
    @SidedProxy(
        serverSide = "com.TheRPGAdventurer.ROTD.server.ServerProxy",
        clientSide = "com.TheRPGAdventurer.ROTD.client.ClientProxy"
    )
    public static ServerProxy proxy;
    
    @Instance(value = MODID)
    public static DragonMounts instance;
    
    private ModMetadata metadata;
    private DragonMountsConfig config;
    public static CreativeTabs TAB;    
    
    public DragonMountsConfig getConfig() {
        return config;
    }
    
    public ModMetadata getMetadata() {
        return metadata;
    }
    
    @EventHandler
    public void PreInitialization(FMLPreInitializationEvent event) {    	
    	DragonMountsLootTables.registerLootTables();
    	TAB = new CreativeTab(MODID);
        metadata = event.getModMetadata();
        proxy.PreInitialization(event); 
    }

    @EventHandler
    public void Initialization(FMLInitializationEvent event) {
        proxy.Initialization(event); 
        proxy.render(); 
        GameRegistry.registerWorldGenerator(new DragonMountsWorldGenerator(), 0);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
       
    }

    @EventHandler
    public void PostInitialization(FMLPostInitializationEvent event) {
        proxy.PostInitialization(event);
    }
    
    @EventHandler
    public void ServerStarting(FMLServerStartingEvent event) {
        proxy.ServerStarting(event);
    }
    
    @EventHandler
    public void ServerStopped(FMLServerStoppedEvent event) {
        proxy.ServerStopped(event);
    }
}
