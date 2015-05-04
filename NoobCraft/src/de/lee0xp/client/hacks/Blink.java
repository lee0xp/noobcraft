package de.lee0xp.client.hacks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.Packet;

import org.lwjgl.input.Keyboard;

import com.mojang.authlib.GameProfile;

import de.lee0xp.client.Client;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.FreecamEntity;
import de.lee0xp.client.hackutil.ModBase;
import de.lee0xp.client.hackutil.RenderUtils;

public class Blink extends ModBase
{
	
	public List<Packet> toSend = new ArrayList<Packet>();;
	FreecamEntity ep;
	
	@Override
	public void onTick()
	{
		//  TODO Auto-generated method stub
		//	mc.thePlayer.addChatMessage(new ChatComponentText(ep.jump()));
		//  RenderGlobal.chestfinder(ep.posX, ep.posY, ep.posZ);
		freecamEnt.setCurrentItemOrArmor(0, mc.thePlayer.getHeldItem());
		
		freecamEnt.setCurrentItemOrArmor(1, mc.thePlayer.getCurrentArmor(0));
		freecamEnt.setCurrentItemOrArmor(2, mc.thePlayer.getCurrentArmor(1));
		freecamEnt.setCurrentItemOrArmor(3, mc.thePlayer.getCurrentArmor(2));
		
		freecamEnt.setCurrentItemOrArmor(4, mc.thePlayer.getCurrentArmor(3));
		
	}
	
	private EntityOtherPlayerMP entity = null;
	public FreecamEntity freecamEnt = null;
	private double freecamX, freecamY, freecamZ, freecamPitch, freecamYaw;
	
	@Override
	public void onEnable()
	{
		
		if (mc.thePlayer != null && mc.theWorld != null)
		{
			freecamEnt = new FreecamEntity(mc.theWorld, mc.thePlayer.getGameProfile());
			freecamEnt.noClip = false;
			freecamEnt.setPosition(mc.thePlayer.posX, mc.thePlayer.boundingBox.minY, mc.thePlayer.posZ);
			freecamEnt.boundingBox = mc.thePlayer.boundingBox;
			freecamEnt.setCurrentItemOrArmor(0, mc.thePlayer.getHeldItem());
			
			freecamEnt.setCurrentItemOrArmor(1, mc.thePlayer.getCurrentArmor(0));
			freecamEnt.setCurrentItemOrArmor(2, mc.thePlayer.getCurrentArmor(1));
			freecamEnt.setCurrentItemOrArmor(3, mc.thePlayer.getCurrentArmor(2));
			
			freecamEnt.setCurrentItemOrArmor(4, mc.thePlayer.getCurrentArmor(3));
			mc.theWorld.spawnEntityInWorld(freecamEnt);
		}
		
		Client.sendMessage("§cPlacing fake player at §aX: " + (int) freecamEnt.posX + ", Y: " + (int) freecamEnt.posY + ",§a Z: §a" + (int) freecamEnt.posZ);
	}
	
	@Override
	public void onDisable()
	{
		this.enabled = false;
		
		if (freecamEnt != null && mc.theWorld != null)
		{
			freecamEnt.setHealth(0);
			freecamEnt.setDead();
			mc.theWorld.removeEntity(freecamEnt);
			freecamEnt = null;
		}
		for (Packet p : toSend)
		{
			if (p != null)
				Client.getInstance().sendPacket(p);
		}
		toSend.clear();
		this.enabled = true;
		
	}
	
	@Override
	public void init()
	{
		init(Keyboard.KEY_X, "Blink", Category.MOVEMENT);
	}

	
	
}
