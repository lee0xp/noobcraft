package org.darkstorm.minecraft.gui.font;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glIsEnabled;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScaled;

import java.awt.Color;
import java.awt.Font;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import de.lee0xp.client.hackutil.Credits;

public class UnicodeFontRenderer extends FontRenderer
{
	
	private final UnicodeFont font;
	
	@SuppressWarnings("unchecked")
	public UnicodeFontRenderer(Font awtFont)
	{
		super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
		
		font = new UnicodeFont(awtFont);
		font.addAsciiGlyphs();
		font.getEffects().add(new ColorEffect(Color.WHITE));
		try
		{
			font.loadGlyphs();
		} catch (SlickException exception)
		{
			throw new RuntimeException(exception);
		}
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		FONT_HEIGHT = font.getHeight(alphabet) / 2;
	}
	
	@Override
	public int drawString(String string, int x, int y, int color)
	{
		if (string == null)
			return 0;
		string = Credits.parse(string);
		// glClear(256);
		// glMatrixMode(GL_PROJECTION);
		// glLoadIdentity();
		// IntBuffer buffer = BufferUtils.createIntBuffer(16);
		// glGetInteger(GL_VIEWPORT, buffer);
		// glOrtho(0, buffer.get(2), buffer.get(3), 0, 1000, 3000);
		// glMatrixMode(GL_MODELVIEW);
		// glLoadIdentity();
		// glTranslatef(0, 0, -2000);
		glPushMatrix();
		glScaled(0.5, 0.5, 0.5);
		
		boolean blend = glIsEnabled(GL_BLEND);
		boolean lighting = glIsEnabled(GL_LIGHTING);
		boolean texture = glIsEnabled(GL_TEXTURE_2D);
		if (!blend)
			glEnable(GL_BLEND);
		if (lighting)
			glDisable(GL_LIGHTING);
		if (texture)
			glDisable(GL_TEXTURE_2D);
		x *= 2;
		y *= 2;
		// glBegin(GL_LINES);
		// glVertex3d(x, y, 0);
		// glVertex3d(x + getStringWidth(string), y + FONT_HEIGHT, 0);
		// glEnd();
		
		string = string.replace("§l", "").replace("§r", "").replace("§k", "");
		int start = x;
		if (!string.startsWith("§") && !string.contains("§"))
		{
			font.drawString(x,y, string, new org.newdawn.slick.Color(color));
		} else
		{
			if (!string.startsWith("§")){
				string = "§f" + string;
			}
			for (String s : string.split("§"))
			{
				if (s.length() != 1)
				{
					font.drawString(
							start,
							y,
							s.length() > 1 ? s.substring(1) : s,
							new org.newdawn.slick.Color((s.startsWith("a") ? 0x00ff00 : (s.startsWith("b") ? 0x00ffff : s.startsWith("c") ? 0xff0000 : (s.startsWith("d") ? 0xff00ff : (s
									.startsWith("e") ? 0xffff00 : (s.startsWith("f") ? 0xffffff : (s.startsWith("0") ? 0x000000 : (s.startsWith("1") ? 0x0000ff : (s.startsWith("2") ? 0x008000 : (s
									.startsWith("3") ? 0x008b8b : (s.startsWith("4") ? 0x8b0000 : (s.startsWith("5") ? 0x800080 : s.startsWith("6") ? 0xffd700 : (s.startsWith("7") ? 0xd3d3d3 : (s
									.startsWith("8") ? 0x808080 : (s.startsWith("9") ? 0x0080ff : 0xffffff))))))))))))))));
					
					start += this.getStringWidth((s.length() > 1 ? s.substring(1) : s)) * 2;
				}
			}
		}
		if (texture)
			glEnable(GL_TEXTURE_2D);
		if (lighting)
			glEnable(GL_LIGHTING);
		if (!blend)
			glDisable(GL_BLEND);
		glPopMatrix();
		return x;
	}
	@Override
	public int func_175063_a(String string, float x, float y, int color)
	{
		return drawString(string, (int) x, (int) y, color);
	}
	
	@Override
	public int getCharWidth(char c)
	{
		return getStringWidth(Character.toString(c));
	}
	
	@Override
	public int getStringWidth(String string)
	{
		return font.getWidth(string) / 2;
	}
	
	public int getStringHeight(String string)
	{
		return font.getHeight(string) / 2;
	}
}
