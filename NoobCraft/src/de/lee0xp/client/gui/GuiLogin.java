package de.lee0xp.client.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;

import de.lee0xp.client.Client;
import de.lee0xp.client.Vars;

public class GuiLogin extends GuiScreen
{
	
	private GuiTextField txt;
	private GuiTextField txt1;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawBackground(0);
		int i = 50;
		this.drawCenteredString(Client.INSTANCE.fontrender, "§eLogin prompt", this.width / 2, this.height / 2 - 60, 16777215);
		this.drawString(fontRendererObj, "Username:", this.width / 2 - this.fontRendererObj.getStringWidth("Username:") - 52, this.height / 2 - 35, 0x00ff00);
		this.txt.drawTextBox();
		this.drawString(fontRendererObj, "Password:", this.width / 2 - this.fontRendererObj.getStringWidth("Username:") - 52, this.height / 2 + 10, 0x00ff00);
		this.txt1.drawTextBox();
		super.drawScreen(mouseX, mouseY, partialTicks);
		
	}
	
	@Override
	public void updateScreen()
	{
		this.txt.updateCursorCounter();
		this.txt1.updateCursorCounter();
	}
	
	@Override
	public void initGui()
	{
		this.fontRendererObj = Client.getInstance().fontrender;
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, (this.width / 2) - 60, this.height / 2 + 50 + 12, 120, 20, "Login"));
		txt = new GuiTextField(0, Client.getInstance().fontrender, this.width / 2 - 100, this.height / 2 - 20, 200, 20);
		txt1 = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 100, this.height / 2 + 25, 200, 20);
		
		try
		{
			
			File pws = new File("NoobCraft/remember-password.txt");
			if (!pws.exists()){
				return;
			}
			String mix = FileUtils.readFileToString(pws);
			URL url = new URL("http://lee0xp.de/projects/noobcraft/logins.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String s = null;
			while ((s = reader.readLine()) != null)
			{
				if (s.equals(mix))
				{
					Vars.loggedIn = true;
					this.mc.displayGuiScreen(new GuiMainMenu());
					return;
				}
			}
		} catch (Exception e)
		{
			
		}
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		this.txt.textboxKeyTyped(typedChar, keyCode);
		this.txt1.textboxKeyTyped(typedChar, keyCode);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		this.txt.mouseClicked(mouseX, mouseY, mouseButton);
		this.txt1.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if (button.id == 0)
		{
			String mix = txt.getText() + ":" + txt1.getText();
			
			URL url = new URL("http://lee0xp.de/projects/noobcraft/logins.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			 
			String s = null;
			while ((s = reader.readLine()) != null)
			{
				if (s.equals(mix))
				{
					Vars.loggedIn = true;
					FileUtils.write(new File("NoobCraft/remember-password.txt"), mix);
					this.mc.displayGuiScreen(new GuiMainMenu());
					return;
				}
			}
			Alert.alert("§cWrong password!", this, false, "§eThis a private client is!"); 
			return;
		}
		//Minecraft.getMinecraft().displayGuiScreen(gc);
	}
	
}
