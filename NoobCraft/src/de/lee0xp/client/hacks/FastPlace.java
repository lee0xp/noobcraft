package de.lee0xp.client.hacks;

import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;


public class FastPlace extends ModBase
{

	@Override
	public void onTick()
	{
		mc.rightClickDelayTimer = 0;
	}

	@Override
	public void onEnable()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init()
	{
		init(-1, "FastPlace", Category.UTIL);
	}
	
}
