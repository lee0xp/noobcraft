package de.lee0xp.client.hacks;

import org.lwjgl.input.Keyboard;

import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;

public class Fly extends ModBase
{
	
	public boolean stateBefore;
	@Override
	public void onTick() 
	{
		// TODO Auto-generated method stub
		
		getPlayer().capabilities.isFlying = true;
	}
	
	@Override
	public void onEnable()
	{
		stateBefore = getPlayer().capabilities.allowFlying;
		getPlayer().capabilities.allowFlying = true;
	}
	
	@Override
	public void onDisable()
	{
		getPlayer().capabilities.isFlying = false;
		getPlayer().capabilities.allowFlying = stateBefore;
	}
	
	@Override
	public void init()
	{
		init(Keyboard.KEY_F, "Fly", Category.MOVEMENT);
	}
}
