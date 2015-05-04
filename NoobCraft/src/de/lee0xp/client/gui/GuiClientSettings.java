package de.lee0xp.client.gui;

import java.io.IOException;

import de.lee0xp.client.Vars;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;

public class GuiClientSettings extends GuiScreen
{
	
	private Minecraft mc = Minecraft.getMinecraft();
	private GuiScreen screen;
	
	public GuiClientSettings(GuiScreen gs)
	{
		screen = gs;
		// TODO Auto-generated constructor stub
	}
	
	public void initGui()
	{
		
		int var3 = this.height / 4 + 48;
		
		// id, we, he, up, left, text
		this.buttonList.add(new GuiButton(0, this.width / 4, this.height / 5, 120, 20, (Vars.chatcommands ? "Disable §c§l. §fCommands" : "Enable §c§l. §fCommands")));
		
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if (keyCode == 1)
		{
			this.mc.displayGuiScreen(screen);
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		final GuiClientSettings screen = this;
		int id = button.id;
		
		switch (id)
		{
			case 0:
				Vars.chatcommands = !Vars.chatcommands;
				button.displayString = (Vars.chatcommands ? "Disable §c§l. §fCommands" : "Enable §c§l. §fCommands");
				
				break;
			default:
				break;
		}
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawBackground(0);
		this.drawString(this.fontRendererObj, "§eCoded by lee0xp", this.width / 175, this.height - 10, 0x11ff11);
		this.drawString(this.fontRendererObj, "§3Minecraft 1.8", this.width - 67, this.height - 10, 0x00ff00);
		this.drawCenteredString(this.fontRendererObj, "§eClient settings", this.width / 2, 16, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
	}
}
