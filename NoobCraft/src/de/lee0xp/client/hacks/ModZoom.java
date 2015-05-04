package de.lee0xp.client.hacks;

import org.lwjgl.input.Keyboard;

import de.lee0xp.client.Client;
import de.lee0xp.client.Vars;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;


public class ModZoom extends ModBase
{
 
 	@Override
	public void onEnable()
	{
		Client.INSTANCE.mc.gameSettings.fovSetting = Vars.zoomFov;
	}

	@Override
	public void onDisable()
	{
		Client.INSTANCE.mc.gameSettings.fovSetting = 110;
	}

	@Override
	public void onTick()
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init()
	{
		init(Keyboard.KEY_LMENU, "Zoom", Category.GRAPHICS);
	}
}
