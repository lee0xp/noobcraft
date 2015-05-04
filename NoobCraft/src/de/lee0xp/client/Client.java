package de.lee0xp.client;

import java.awt.Font;
import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.util.ChatComponentText;

import org.darkstorm.minecraft.gui.ExampleGuiManager;
import org.darkstorm.minecraft.gui.font.UnicodeFontRenderer;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import de.lee0xp.client.gui.ModsGui;
import de.lee0xp.client.hackutil.CheckKey;
import de.lee0xp.client.hackutil.ModManager;
import de.lee0xp.client.hackutil.Util;
import de.lee0xp.client.plugin.PluginLoader;

public class Client
{
	
	public static final String TITLE = "NoobCraft 1.8";
	public static int PROTOCOL = 48;
	public ExampleGuiManager manager;
	public GuiScreen modsGui;
	public static Client INSTANCE;
	public static CheckKey ck;
	public static String prefix = "§8[§3NC§8] §f";
	public Minecraft mc;
	public ModManager mm;
	public Util friends;
	public PluginLoader pl;
	public FontRenderer fontrender;
	public File ncFile;
	
	static
	{
		PROTOCOL = 47;
	}
	
	public Client()
	{
		INSTANCE = this;
		ncFile = new File("NoobCraft/");
		if (!ncFile.exists()){
			ncFile.mkdir();
		}
		mc = Minecraft.getMinecraft();
	
		mm = new ModManager(this, mc);

		manager = new ExampleGuiManager();
		initModsGui();
		friends = new Util();
		pl = new PluginLoader();
		fontrender = new UnicodeFontRenderer(new Font("Arial", Font.PLAIN, 20));
		try
		{
			for (File f : new File("NoobCraft/plugins/").listFiles())
			{
				if (f.toString().endsWith(".jar"))
				{
					pl.loadPlugin(f.getName());
				}
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Client getInstance()
	{
		return INSTANCE;
	}
	public ModManager getHacks()
	{
		return mm;
	}
	public void initModsGui()
	{
		modsGui = new ModsGui(manager);
		ck = new CheckKey(Minecraft.getMinecraft());
	}
	
	public void showGui()
	{
		//Client.INSTANCE.mm = new ModManager(Client.getInstance(), Minecraft.getMinecraft());
		Client.INSTANCE.manager = new ExampleGuiManager();
		Client.INSTANCE.modsGui = new ModsGui(Client.INSTANCE.manager);
		Client.INSTANCE.manager.setTheme(new SimpleTheme());
		//	mc.thePlayer.sendChatMessage(".reloadTheme");
		manager.setup();
		//sendMessage(prefix + "§eOpening the gui...");
		mc.displayGuiScreen(modsGui);
	}
	
	public static void sendMessage(String msg)
	{
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		p.addChatMessage(new ChatComponentText(msg));
	}
	
	public void sendPacket(Packet packet)
	{
		if (!mc.isSingleplayer() && (mc.currentScreen == null))/*To not accidentally send a packet in singleplayer*/
		{
			mc.thePlayer.sendQueue.addToSendQueue(packet);
			return;
		} else
		{
			return;
		}
	}
}
