package de.lee0xp.client.hackutil;

import org.darkstorm.minecraft.gui.component.Frame;

import de.lee0xp.client.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public abstract class ModBase
{
	
	protected boolean enabled = false;
	protected Minecraft mc = Minecraft.getMinecraft();
	
	public abstract void onTick();
	public abstract void onEnable();
	public abstract void onDisable();
	
	public ModBase()
	{
	}
	
	public void onCall()
	{
		
	}
	
	public abstract void init();
	
	public void init(int i, String name)
	{
		this.key = i;
		this.name = name;
		this.category = Category.UTIL;
	}
	
	public void init(int i, String name, Category c)
	{
		this.key = i;
		this.name = name;
		this.category = c;
	}
	
	public String name;
	public int key;
	public Category category;
	
	public void toggleSilent()
	{
		if (enabled == true)
		{
			onDisable();
		} else if (enabled == false)
		{
			onEnable();
		}
		
		enabled = !enabled;
	}
	public void toggle()
	{
	
		if (enabled == true)
		{
			onDisable();
		} else if (enabled == false)
		{
			onEnable();
		}
		
		enabled = !enabled;
		if (Client.getInstance().getHacks().isChat())
			Client.sendMessage(Client.INSTANCE.prefix + "§c" + (name != null ? name : "null") + ": §e" + (isEnabled() ? "§a§lOn" : "§c§lOff"));
		
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	protected EntityPlayer getPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
	
	protected Minecraft getMinecraft()
	{
		return Minecraft.getMinecraft();
	} 
	
	public boolean hasConfig()
	{
		return false; 
	}
	public Frame getFrame()
	{
		return null;
	}
}
