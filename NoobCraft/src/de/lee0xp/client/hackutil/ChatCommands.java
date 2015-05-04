package de.lee0xp.client.hackutil;

import java.awt.Font;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import org.darkstorm.minecraft.gui.ExampleGuiManager;
import org.darkstorm.minecraft.gui.font.UnicodeFontRenderer;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import de.lee0xp.client.Client;
import de.lee0xp.client.Vars;
import de.lee0xp.client.gui.ModsGui;

public class ChatCommands
{
	
	public static boolean parse(String cmd)
	{
		if (cmd.startsWith("."))
		{
			if (!Vars.chatcommands){
				return false;
			}
			String lbl = cmd.replace(".", "").split(" ")[0];
			String args = "";
			for (int i = 1; i < cmd.split(" ").length; i++)
			{
				args += cmd.split(" ")[i];
			}
			Minecraft mc = Minecraft.getMinecraft();
			if (lbl.equals("gui"))
			{
				Client.INSTANCE.showGui();
				return true;
			}
			if (lbl.equalsIgnoreCase("test")){
				Client.sendMessage("§aa§bb§cc§dd§ee§ff§00§11§22§33§44§55§66§77§88§99");
				return true;
			}
			if (lbl.equalsIgnoreCase("reloadTheme"))
			{
				Client.INSTANCE.mm = new ModManager(Client.getInstance(), Minecraft.getMinecraft());
				Client.INSTANCE.manager = new ExampleGuiManager();
				Client.INSTANCE.modsGui = new ModsGui(Client.INSTANCE.manager);
				Client.INSTANCE.manager.setTheme(new SimpleTheme());
				Client.INSTANCE.fontrender = new UnicodeFontRenderer(new Font("Arial", Font.PLAIN, 20));
				return true;
			}
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(Client.prefix + "§cUnknown command"));
			return true;
		}
		return false;
	}
	
}
