package de.lee0xp.client.hacks;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import de.lee0xp.client.Vars;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;

public class ChestESP extends ModBase
{
	
	public ChestESP()
	{
		 // TODO Auto-generated constructor stub
	}

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
		init(Keyboard.KEY_V, "ChestESP", Category.GRAPHICS);
	}
	
}
