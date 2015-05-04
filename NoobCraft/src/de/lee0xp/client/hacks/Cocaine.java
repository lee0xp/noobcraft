package de.lee0xp.client.hacks;

import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;


public class Cocaine extends ModBase
{

	@Override
	public void onTick()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable()
	{
		mc.timer.timerSpeed = 2;
	}

	@Override
	public void onDisable()
	{
		mc.timer.timerSpeed = 1;
	}

	@Override
	public void init()
	{
		init(-1, "Cocaine", Category.UTIL);
	}
	
}
