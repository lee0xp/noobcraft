package de.lee0xp.client.hacks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import de.lee0xp.client.Vars;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;
import de.lee0xp.client.hackutil.ModBaseEmpty;

public class PlayerESP extends ModBaseEmpty
{ 
	
	@Override
	public void init()
	{
		init(-1, "PlayerESP", Category.GRAPHICS);
	}
	
}
