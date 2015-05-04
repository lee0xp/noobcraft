package de.lee0xp.client.gui;

import java.io.IOException;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import com.google.common.collect.Lists;

import de.lee0xp.client.Client;
import de.lee0xp.client.Splash;
import de.lee0xp.client.Vars;
import de.lee0xp.client.hackutil.ClassFinder;

public class GuiMainScreen extends GuiScreen
{
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	boolean alert = false;
	
	public String splashText = "";
	
	public void initGui()
	{
		if (!Vars.loggedIn)
		{
			this.mc.displayGuiScreen(new GuiLogin());
			return;
		}
		
		if (!alert && Vars.debug)
		{
			alert = true;
			List<String> alerts = Lists.newArrayList();
			
			boolean deob = false;
			boolean eclipse = false;
			
			try
			{
				Class.forName("ClientDebugger");
				deob = true;
			} catch (Exception e)
			{
				
			}
			
			if (deob)
				alerts.add("§eYou are running in a deobfuscated environment!");
			if (eclipse)
				alerts.add("§eThe client was started trough the eclipse Bootstrapper class!");
			
			alerts.add("");
			alerts.add("§a§lClient classlist");
			
			String classes = "";
			int i = 0;
			
			if (deob)
				for (Class clazz : ClassFinder.find("de.lee0xp.client"))
				{
					if (i < 5)
						classes += clazz.getName().replace("de.lee0xp.client.hacks.", "").replace("de.lee0xp.client.gui.", "").replace("de.lee0xp.client.hackutil.", "")
								.replace("de.lee0xp.client.plugin.interfaces.", "").replace("de.lee0xp.client.plugin.", "").replace("de.lee0xp.client.", "")
								+ ", ";
					else
					{
						alerts.add(classes);
						classes = "";
						i = -1;
					}
					i++;
				}
			
			if (deob)
				Alert.alert(alerts, "§c§lWarning!", this, false, null, null);
		}
		int var3 = this.height / 4 + 48;
		
		// id, we, he, up, left, text
		this.buttonList.add(new GuiButton(0, this.width / 2 - 120, this.height / 2 - 30, 120, 20, "Play"));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 120, this.height / 2 - 10, 120, 20, "Play online"));
		this.buttonList.add(new GuiButton(3, this.width / 2 - 120, this.height / 2 + 10, 120, 20, "Settings"));
		
		this.buttonList.add(new GuiButton(4, this.width / 2, this.height / 2 - 30, 120, 20, "Client settings"));
		this.buttonList.add(new GuiButton(5, this.width / 2, this.height / 2 - 10, 120, 20, "Your account"));
		this.buttonList.add(new GuiButton(2, this.width / 2, this.height / 2 + 10, 120, 20, "Exit"));
		
		splashText = Splash.random();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		final GuiMainScreen screen = this;
		int id = button.id;
		
		if (id == 0)
		{
			mc.displayGuiScreen(new GuiSelectWorld(this));
		} else if (id == 3)
		{
			mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
		} else if (id == 1)
		{
			mc.displayGuiScreen(new GuiMultiplayer(this));
		} else if (id == 2)
		{
			Alert.alert("§eBye, bye!", "§cYou are about to close the game!", this, true, new Thread()
			{
				
				public void run()
				{
					System.exit(0);
				}
			}, new Thread()
			{
				
				public void run()
				{
					mc.displayGuiScreen(screen);
				}
			});
		} else if (id == 4)
		{
			mc.displayGuiScreen(new GuiClientSettings(this));
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		
		if (Client.getInstance().getHacks().blink.isEnabled())
		{
			Client.getInstance().getHacks().blink.toggleSilent();
		}
		this.drawBackground(0);//, "textures/misc/shadow.png");
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		GlStateManager.disableAlpha();
		GlStateManager.enableAlpha();
		Tessellator var4 = Tessellator.getInstance();
		WorldRenderer var5 = var4.getWorldRenderer();
		short var6 = 274;
		int var7 = this.width / 2 - var6 / 2;
		byte var8 = 30;
		
		this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/title/noobcraft.png"));
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		this.drawTexturedModalRect(var7 + 0, var8 + 0, 0, 0, 155, 44);
		this.drawTexturedModalRect(var7 + 155, var8 + 0, 0, 45, 155, 44);
		
		var5.func_178991_c(-1);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) (this.width / 2 + 90), 70.0F, 0.0F);
		GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
		float var9 = 1.8F - MathHelper.abs(MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * (float) Math.PI * 2.0F) * 0.1F);
		var9 = var9 * 100.0F / (float) (this.fontRendererObj.getStringWidth(splashText) + 32);
		GlStateManager.scale(var9, var9, var9);
		if (!splashText.contains("§x"))
		{
			this.drawCenteredString(this.fontRendererObj, splashText, 0, -8, -256);
		} else
		{
			this.drawCenteredString(Client.getInstance().fontrender, splashText.replace("§x", ""), 0, -12, -256);
		}
		GlStateManager.popMatrix();
		
		this.drawString(this.fontRendererObj, "§eCoded by lee0xp", this.width / 175, this.height - 10, 0x11ff11);
		this.drawString(this.fontRendererObj, "§3Minecraft 1.8", this.width - 67, this.height - 10, 0x00ff00);
		
	}
}
