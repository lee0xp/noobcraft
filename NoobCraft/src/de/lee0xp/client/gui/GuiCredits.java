package de.lee0xp.client.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

public class GuiCredits extends GuiScreen
{
	
	private static final Logger logger = LogManager.getLogger();
	private static final ResourceLocation field_146576_f = new ResourceLocation("textures/gui/title/minecraft.png");
	//private static final ResourceLocation field_146577_g = new ResourceLocation("textures/misc/vignette.png");
	private int field_146581_h;
	private List texts;
	private int field_146579_r;
	private float field_146578_s = 0.5F;
	private static final String __OBFID = "CL_00000719";
	
	/**
	 * Called from the main game loop to update the screen.
	 */
	
	public void updateScreen()
	{
		++this.field_146581_h;
		this.field_146581_h += 2;
		if (!(field_146581_h > 700))
			this.field_146581_h += 750;
		float var1 = (float) (this.field_146579_r + this.height + this.height + 24) / this.field_146578_s;
		
		if ((float) this.field_146581_h > (var1 - 270))
		{
			this.sendRespawnPacket();
		}
	}
	
	/**
	 * Fired when a key is typed (except F11 who toggle full screen). This is the equivalent of
	 * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
	 */
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if (keyCode == 1)
		{
			this.sendRespawnPacket();
		}
	}
	
	private void sendRespawnPacket()
	{
		this.mc.displayGuiScreen((GuiScreen) null);
	}
	
	/**
	 * Returns true if this GUI should pause the game when it is displayed in single-player
	 */
	public boolean doesGuiPauseGame()
	{
		return true;
	}
	
	/**
	 * Adds the buttons (and other controls) to the screen in question.
	 */
	public void initGui()
	{
		if (this.texts == null)
		{
			this.texts = Lists.newArrayList();
			
			try
			{
				this.texts.add("§3§lNoobCraft 1.8 §4§l - Credits");
				
				this.texts.add("");
				this.texts.add("");
				this.texts.add("");
				
				this.texts.add("§e- Main credits goes to");
				this.texts.add("");
				this.texts.add("§7    * §6Xmcgamer0X (lee0xp) §c- Main development");
				this.texts.add("");
				
				this.texts.add("§e- Other credits");
				this.texts.add("");
				this.texts.add("§7    * §aSearge §c- The MCP (Mod Coder Pack)");
				this.texts.add("");
				
				this.texts.add("§e- Libraries/APIs used");
				this.texts.add("");
				this.texts.add("§7    * §2Slick2D by ninjacave");
				this.texts.add("§7    * §2GUI API by darkstorm652");
				this.texts.add("§7    * §2MailAPI by lee0xp");
				
				this.field_146579_r = this.texts.size();
			} catch (Exception var9)
			{
				logger.error("Couldn\'t load credits", var9);
			}
		}
	}
	
	private void drawWinGameScreen(int p_146575_1_, int p_146575_2_, float p_146575_3_)
	{
		Tessellator var4 = Tessellator.getInstance();
		WorldRenderer var5 = var4.getWorldRenderer();
		this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
		var5.startDrawingQuads();
		var5.func_178960_a(1.0F, 1.0F, 1.0F, 1.0F);
		int var6 = this.width;
		float var7 = 0.0F - ((float) this.field_146581_h + p_146575_3_) * 0.5F * this.field_146578_s;
		float var8 = (float) this.height - ((float) this.field_146581_h + p_146575_3_) * 0.5F * this.field_146578_s;
		float var9 = 0.015625F;
		float var10 = ((float) this.field_146581_h + p_146575_3_ - 0.0F) * 0.02F;
		float var11 = (float) (this.field_146579_r + this.height + this.height + 24) / this.field_146578_s;
		float var12 = (var11 - 20.0F - ((float) this.field_146581_h + p_146575_3_)) * 0.005F;
		
		if (var12 < var10)
		{
			var10 = var12;
		}
		
		if (var10 > 1.0F)
		{
			var10 = 1.0F;
		}
		
		var10 *= var10;
		var10 = var10 * 96.0F / 255.0F;
		var5.func_178986_b(var10, var10, var10);
		var5.addVertexWithUV(0.0D, (double) this.height, (double) this.zLevel, 0.0D, (double) (var7 * var9));
		var5.addVertexWithUV((double) var6, (double) this.height, (double) this.zLevel, (double) ((float) var6 * var9), (double) (var7 * var9));
		var5.addVertexWithUV((double) var6, 0.0D, (double) this.zLevel, (double) ((float) var6 * var9), (double) (var8 * var9));
		var5.addVertexWithUV(0.0D, 0.0D, (double) this.zLevel, 0.0D, (double) (var8 * var9));
		var4.draw();
	}
	
	/**
	 * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawWinGameScreen(mouseX, mouseY, partialTicks);
		Tessellator var4 = Tessellator.getInstance();
		WorldRenderer var5 = var4.getWorldRenderer();
		short var6 = 274;
		int var7 = this.width / 2 - var6 / 2;
		int var8 = this.height + 50;
		float var9 = -((float) this.field_146581_h + partialTicks) * this.field_146578_s;
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, var9, 0.0F);
	//	this.mc.getTextureManager().bindTexture(field_146576_f);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	//	this.drawTexturedModalRect(var7, var8, 0, 0, 155, 44);
		//this.drawTexturedModalRect(var7 + 155, var8, 0, 45, 155, 44);
		var5.func_178991_c(16777215);
		int var10 = var8 + 200;
		int var11;
		for (var11 = 0; var11 < this.texts.size(); ++var11)
		{
			/*if (var11 == this.texts.size() - 0)
			{
				float var12 = (float) var10 + var9 - (float) (this.height / 2 - 6);
				
				if (var12 < 0.0F)
				{
					GlStateManager.translate(0.0F, -var12, 0.0F);
				}
			}*/
			
			if ((float) var10 + var9 + 12.0F + 8.0F > 0.0F && (float) var10 + var9 < (float) this.height)
			{
				String var13 = (String) this.texts.get(var11);
				
				if (var13.startsWith("[C]"))
				{
					this.fontRendererObj.func_175063_a(var13.substring(3), (float) (var7 + (var6 - this.fontRendererObj.getStringWidth(var13.substring(3))) / 2), (float) var10, 16777215);
				} else
				{
					this.fontRendererObj.fontRandom.setSeed((long) var11 * 4238972211L + (long) (this.field_146581_h / 4));
					this.fontRendererObj.func_175063_a(var13, (float) var7, (float) var10, 16777215);
				}
			}
			
			var10 += 12;
		}
		
		GlStateManager.popMatrix();
	 this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/misc/vignette.png"));
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(0, 769);
		var5.startDrawingQuads();
		var5.func_178960_a(1.0F, 1.0F, 1.0F, 1.0F);
		var11 = this.width;
		int var14 = this.height;
		var5.addVertexWithUV(0.0D, (double) var14, (double) this.zLevel, 0.0D, 1.0D);
		var5.addVertexWithUV((double) var11, (double) var14, (double) this.zLevel, 1.0D, 1.0D);
		var5.addVertexWithUV((double) var11, 0.0D, (double) this.zLevel, 1.0D, 0.0D);
		var5.addVertexWithUV(0.0D, 0.0D, (double) this.zLevel, 0.0D, 0.0D);
		var4.draw();
		GlStateManager.disableBlend();
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
