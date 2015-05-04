package de.lee0xp.client.hacks;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import de.lee0xp.client.Vars;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;

public class Crits extends ModBase
{
 
	@Override
	public void onTick()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onEnable()
	{
		
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	@Override
	public void onCall()
	{
		if (mc.thePlayer.onGround)
		{
			mc.thePlayer.onGround = false;
			mc.thePlayer.motionY = 0.1000000014901161D;
			mc.thePlayer.onGround = true;
		}
		
	}
	
	@Override
	public void init()
	{
		init(Keyboard.KEY_C, "Criticals", Category.PVP);
	}
	
}
