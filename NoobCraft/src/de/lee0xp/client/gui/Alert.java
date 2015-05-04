package de.lee0xp.client.gui;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class Alert
{
	
	public static void alert(final List<String> text, final String title, final GuiScreen sc, final boolean error, final Thread t, final Thread t2)
	{
		
		Minecraft.getMinecraft().displayGuiScreen(new GuiScreen()
		{
			public void drawScreen(int mouseX, int mouseY, float partialTicks)
			{
				this.drawBackground(0);
				int i = 50;
				for (String s : text)
				{
					this.drawCenteredString(this.fontRendererObj, s, this.width / 2, this.height /2 - (i+10), 16777215);
					i -= 10;
				}
				this.drawCenteredString(this.fontRendererObj, title, this.width / 2, 30, 16777215);
				super.drawScreen(mouseX, mouseY, partialTicks);
			}
			
			public void initGui()
			{
				this.buttonList.clear();
				this.buttonList.add(new GuiOptionButton(0, this.width / 2 - (error ? 155 : 72), this.height - 35, "OK"));
				if (error)
					this.buttonList.add(new GuiOptionButton(1, this.width / 2 - 155 + 160, this.height - 35, I18n.format("Cancel", new Object[0])));
			}
			
			protected void actionPerformed(GuiButton button) throws IOException
			{
				
				if (button.id == 0)
				{
					if (t == null)
						this.mc.displayGuiScreen(sc);
					else
						t.start();
					
					/*
					 * DEBUG CODE HERE
					 */
					
					//this.mc.displayGuiScreen(new GuiCredits());
				}
				else if (button.id == 1)
				{
					if (t2 == null)
						System.exit(0);
					else
						t2.start();
				}
			}
			
		});
	}
	
	public static void alert(String text, String title, GuiScreen gc, boolean bool)
	{
		alert(Arrays.asList(text), title, gc, bool, null, null);
	}
	
	public static void alert(String title, GuiScreen gc, boolean bool, String... text)
	{
		alert(Arrays.asList(text), title, gc, bool, null, null);
	}
	
	public static void alert(String text, String title, GuiScreen gc, boolean bool, Thread thread)
	{
		alert(Arrays.asList(text), title, gc, bool, thread, null);
	}
	
	public static void alert(String text, String title, GuiScreen gc, boolean bool, Thread thread, Thread t2)
	{
		alert(Arrays.asList(text), title, gc, bool, thread, t2);
	}
	
	public static void inputBox(final String text, final String title, final String def, final GuiScreen gc, final IAction t)
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiScreen()
		{
			
			private GuiTextField txt;
			
			@Override
			public void drawScreen(int mouseX, int mouseY, float partialTicks)
			{
				this.drawBackground(0);
				int i = 50;
				this.drawCenteredString(this.fontRendererObj, text, this.width / 2, this.height / 2 - i, 16777215);
				
				this.drawCenteredString(this.fontRendererObj, title, this.width / 2, this.height / 2 - 80, 16777215);
				this.txt.drawTextBox();
				super.drawScreen(mouseX, mouseY, partialTicks);
			}
			
			@Override
			public void updateScreen()
			{
				this.txt.updateCursorCounter();
			}
			
			@Override
			public void initGui()
			{
				Keyboard.enableRepeatEvents(true);
				this.buttonList.clear();
				this.buttonList.add(new GuiOptionButton(0, this.width / 2 - 155, this.height / 4 + 120 + 12, "OK"));
				txt = new GuiTextField(0, this.fontRendererObj, this.width / 2 - 100, 106, 200, 20);
				txt.setText(def);
				this.buttonList.add(new GuiOptionButton(1, this.width / 2 - 155 + 160, this.height / 4 + 120 + 12, I18n.format("Cancel", new Object[0])));
			}
			
			@Override
			protected void keyTyped(char typedChar, int keyCode) throws IOException
			{
				this.txt.textboxKeyTyped(typedChar, keyCode);
			}
			
			@Override
			protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
			{
				super.mouseClicked(mouseX, mouseY, mouseButton);
				this.txt.mouseClicked(mouseX, mouseY, mouseButton);
			}
			
			@Override
			protected void actionPerformed(GuiButton button) throws IOException
			{
				t.setText(txt.getText());
				if (button.id == 0)
				{
					t.actionPerformed();
				} else if (button.id == 1)
				{
					t.actionCancelled();
				}
				
				//Minecraft.getMinecraft().displayGuiScreen(gc);
			}
			
		});
	}
}
