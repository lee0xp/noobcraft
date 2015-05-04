package de.lee0xp.client.hacks;

import org.lwjgl.input.Keyboard;

import net.minecraft.potion.PotionEffect;
import de.lee0xp.client.Client;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;

public class ModFullbright extends ModBase
{
	
	float oldGamma;
	
	@Override
	public void onTick()
	{
		Client.INSTANCE.mc.gameSettings.gammaSetting = 10000;
	}
	
	@Override
	public void onEnable()
	{
		
	}
	
	@Override
	public void onDisable()
	{
		Client.INSTANCE.mc.gameSettings.gammaSetting = 0;
	}
	@Override
	public void init()
	{
		init(Keyboard.KEY_B, "Fullbright", Category.GRAPHICS);
	}
}
