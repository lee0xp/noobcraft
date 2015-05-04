package de.lee0xp.client.hacks;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;

public class AntiKnockback extends ModBase
{
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void onTick()
	{
		 
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
	public void init()
	{
		init(Keyboard.KEY_K, "NoVelocity", Category.PVP);
	}
}
