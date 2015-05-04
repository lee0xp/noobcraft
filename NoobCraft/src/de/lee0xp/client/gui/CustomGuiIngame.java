package de.lee0xp.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.network.play.client.C0BPacketEntityAction.Action;

import org.lwjgl.input.Keyboard;

import de.lee0xp.client.Client;
import de.lee0xp.client.Vars;
import de.lee0xp.client.hackutil.ModBase;

public class CustomGuiIngame extends GuiIngame
{
	
	public CustomGuiIngame(Minecraft mcIn)
	{
		super(mcIn);
	}
	
	boolean zoom = false;
	
	@Override
	public void render(float f)
	{
		Minecraft.getMinecraft().thePlayer.worldObj.setRainStrength(0);
		
		/*	C0BPacketEntityAction p = new C0BPacketEntityAction(Minecraft.theMinecraft.thePlayer, Action.START_SNEAKING);
			Client.getInstance().sendPacket(p);
			*/
		if (Minecraft.getMinecraft().thePlayer.isDead)
		{
			if (Client.getInstance().getHacks().blink.isEnabled())
				Client.getInstance().getHacks().blink.toggle();
		}
		
		for (ModBase m : Client.getInstance().getHacks().getMods())
		{
			
			if (m.key != -1)
			{
				if (Client.ck.checkKey(m.key))
				{
					m.toggle();
				}
			}
		}
		
		if (Client.ck.checkKey(Keyboard.KEY_O))
		{
			Client.getInstance().showGui();
		}
		
		if (Client.ck.checkKey(Keyboard.KEY_V))
		{
			new Thread("FallDown thread")
			{
				
				@Override
				public void run()
				{
					Client.getInstance().getHacks().disableChat();
					Client.getInstance().getHacks().fly.toggle();
					try
					{
						Thread.sleep(400);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					Client.getInstance().getHacks().fly.toggle();
					Client.getInstance().getHacks().enableChat();
				}
			}.start();
		}
		
		Client.INSTANCE.mm.zoom.onTick();
		super.render(f);
		
		int i = 2;
		for (ModBase mb : Client.INSTANCE.mm.getMods())
		{
			if (mb.isEnabled())
			{
				this.drawString(Client.getInstance().fontrender, mb.name, 2, i, 0x00ff00);
				i += 10;
				mb.onTick();
			}
			
		}
		
	}
}
