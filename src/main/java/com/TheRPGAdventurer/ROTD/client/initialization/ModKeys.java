package com.TheRPGAdventurer.ROTD.client.initialization;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModKeys {
	
    public static final String KEY_CATEGORY = "key.categories.gameplay";
    public static  KeyBinding KEY_BREATH;
    
    public static void init() {
    	KEY_BREATH = new KeyBinding("key.dragon.breath", Keyboard.KEY_R, KEY_CATEGORY);
        ClientRegistry.registerKeyBinding(KEY_BREATH);
    	
    }
    
}
