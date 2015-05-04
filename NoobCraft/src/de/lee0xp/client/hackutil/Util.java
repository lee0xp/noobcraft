package de.lee0xp.client.hackutil;

import java.util.ArrayList;
import java.util.List;

import de.lee0xp.client.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;

public class Util
{
	
	private Minecraft mc;
	private Client client;
	private List<String> friends;
	
	public Util()
	{
		client = Client.INSTANCE;
		mc = Minecraft.getMinecraft();
		friends = new ArrayList<>();
	}
	
	public List<String> getFriends()
	{
		return this.friends;
	}
	
	public void addFriend(String name)
	{
		this.friends.add(name);
	}
	
	public void removeFriend(String name)
	{
		this.friends.remove(name);
	}
	
	public boolean isFriend(String name)
	{
		return getFriends().contains(name);
	}
	
	static int timePassed = 0;
	static int delay = 5;
	
	public static void stealChest(final int windowId)
	{
		new Thread()
		{
			
			public void run()
			{
				for (int i = 0; i < Minecraft.getMinecraft().thePlayer.openContainer.getInventory().size() - 36; i++)
				{
					try
					{
						if (Minecraft.getMinecraft().thePlayer.openContainer.getInventory().get(i) != null)
						{
							Minecraft.getMinecraft().playerController.windowClick(windowId, i, 0, 1, Minecraft.getMinecraft().thePlayer);
							
							Thread.sleep(100);
						}
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}.start();
	}
	
	public static void storeChest(final int windowId)
	{
		new Thread()
		{
			
			public void run()
			{
				for (int i = Minecraft.getMinecraft().thePlayer.openContainer.getInventory().size() - 36; i < Minecraft.getMinecraft().thePlayer.openContainer.getInventory().size(); i++)
				{
					try
					{
						if (Minecraft.getMinecraft().thePlayer.openContainer.getInventory().get(i) != null)
						{
							Minecraft.getMinecraft().playerController.windowClick(windowId, i, 0, 1, Minecraft.getMinecraft().thePlayer);
							
							Thread.sleep(100);
						}
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}.start();
	}
	
}
