package de.lee0xp.client.hacks;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;
import de.lee0xp.client.hackutil.ModBaseEmpty;

public class Xray extends ModBaseEmpty
{
	
	private int id(Block v)
	{
		return Block.getIdFromBlock(v);
	}
	
	public List<Integer> blocks = Arrays.asList(id(Blocks.iron_ore), id(Blocks.gold_ore), id(Blocks.diamond_ore), id(Blocks.coal_ore), id(Blocks.lapis_ore), id(Blocks.redstone_ore),
			id(Blocks.emerald_ore), id(Blocks.chest), id(Blocks.ender_chest), id(Blocks.trapped_chest));
	
	 
	 
	@Override
	public void init()
	{
		init(Keyboard.KEY_Y, "Xray", Category.GRAPHICS);
	}
}
