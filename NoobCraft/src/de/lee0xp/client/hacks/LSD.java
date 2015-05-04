package de.lee0xp.client.hacks;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import de.lee0xp.client.Vars;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;


public class LSD extends ModBase
{

	@Override
	public void onTick()
	{
		 
	}

	@Override
	public void onEnable()
	{
		Minecraft.getMinecraft().entityRenderer.setShader(new ResourceLocation("shaders/post/wobble.json"));
		Minecraft.getMinecraft().thePlayer.addPotionEffect(new PotionEffect(9, 3600 * 20, 1));
	}

	@Override 
	public void onDisable()
	{
		Minecraft.getMinecraft().entityRenderer.theShaderGroup = null;
		Minecraft.getMinecraft().thePlayer.removePotionEffect(9);
	}
	
	@Override
	public void init()
	{
		init(-1, "LSD", Category.GRAPHICS);
	}
	
	
}
