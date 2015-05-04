package de.lee0xp.client.hacks;

import java.awt.Dimension;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;

import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.component.basic.BasicButton;
import org.darkstorm.minecraft.gui.component.basic.BasicFrame;
import org.darkstorm.minecraft.gui.theme.Theme;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;
import org.lwjgl.input.Keyboard;

import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;

public class KillAura extends ModBase
{
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	int timePassed = 0;
	int delay = 8;
	
	public void onTick()
	{
		timePassed += 1;
		if (timePassed > delay)
		{
			timePassed = 0;
			for (Object o : mc.theWorld.loadedEntityList)
			{
				
				if (o instanceof EntityPlayer)
				{
					EntityPlayer ep = (EntityPlayer) o;
					if (!(ep instanceof EntityPlayerSP) && !ep.isDead && ep.canEntityBeSeen(mc.thePlayer) && mc.thePlayer.getDistanceToEntity(ep) < +5)
					
					{
						if (ep.getName().equals(getPlayer().getName()))
						{
							return; //probably blinkhack active :)
						}
						//this.LookAt(ep.posX, ep.posY, ep.posZ, mc.thePlayer);
						mc.thePlayer.setSprinting(false);
						mc.thePlayer.swingItem();
						//mc.thePlayer.setDead();
						mc.playerController.attackEntity(mc.thePlayer, ep);
						//mc.thePlayer.setPositionAndUpdate(ep.posX + 0.5, ep.posY + 0, ep.posZ + 0);
					}
					
				}
			}
			
		}
	}
	
	public void onEnable()
	{
		
	}
	
	public void onDisable()
	{
	}
	
	@Override
	public void init()
	{
		init(Keyboard.KEY_R, "Forcefield", Category.PVP);
	}
	
	public boolean hasConfig()
	{
		return true;
	}
	
	@Override
	public Frame getFrame(){
		Frame frame = new BasicFrame("KillAura");
		Theme theme = new SimpleTheme();
		
		frame.setTheme(theme);
		
		frame.add(new BasicButton("legit"));
		
		frame.setX(100);
		frame.setY(100);
		
		Dimension defaultDimension = theme.getUIForComponent(frame).getDefaultSize(frame);
		frame.setWidth(defaultDimension.width);
		frame.setHeight(defaultDimension.height);
		frame.layoutChildren();
		frame.setPinnable(false);
		frame.setClosable(false);
		frame.setVisible(true);
		frame.setMinimized(false);
		return frame;
	}
	
	public static void LookAt(double px, double py, double pz, EntityPlayer me)
	{
		double dirx = me.getPosition().getX() - px;
		double diry = me.getPosition().getY() - py;
		double dirz = me.getPosition().getZ() - pz;
		
		double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);
		
		dirx /= len;
		diry /= len;
		dirz /= len;
		
		double pitch = Math.asin(diry);
		double yaw = Math.atan2(dirz, dirx);
		
		//to degree
		pitch = pitch * 180.0 / Math.PI;
		yaw = yaw * 180.0 / Math.PI;
		
		yaw += 90f;
		me.rotationPitch = (float) pitch;
		me.rotationYaw = (float) yaw;
	}
	
}