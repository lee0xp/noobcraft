package de.lee0xp.client.hackutil;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import de.lee0xp.client.Client;
import de.lee0xp.client.hacks.AntiKnockback;
import de.lee0xp.client.hacks.Blink;
import de.lee0xp.client.hacks.ChestESP;
import de.lee0xp.client.hacks.ChestLooter;
import de.lee0xp.client.hacks.Cocaine;
import de.lee0xp.client.hacks.Crits;
import de.lee0xp.client.hacks.FastPlace;
import de.lee0xp.client.hacks.Fly;
import de.lee0xp.client.hacks.Jetpack;
import de.lee0xp.client.hacks.KillAura;
import de.lee0xp.client.hacks.LSD;
import de.lee0xp.client.hacks.ModFullbright;
import de.lee0xp.client.hacks.ModZoom;
import de.lee0xp.client.hacks.PlayerESP;
import de.lee0xp.client.hacks.Xray;

public class ModManager
{
	
	private List<ModBase> mods = new ArrayList<ModBase>();
	private Client c;
	private Minecraft mc;
	
	public ModFullbright fullbright;
	public ModZoom zoom;
	public KillAura killAura;
	public AntiKnockback noKnockback;
	public Crits crits;
	public LSD lsd;
	public ChestESP chestFinder;
	public ChestLooter chestLooter;
	public PlayerESP tracer;
	public Fly fly;
	public Xray xray;
	public Blink blink;
	public Jetpack jetPack;
	public FastPlace fastPlace;
	public Cocaine cocaine;
	
	public boolean chat = true;
	
	public ModManager(Client c, Minecraft mc)
	{
		this.c = c;
		this.mc = mc;
		
		mods.add(fullbright = new ModFullbright());
		mods.add(zoom = new ModZoom());
		mods.add(killAura = new KillAura());
		mods.add(noKnockback = new AntiKnockback());
		mods.add(crits = new Crits());
		mods.add(lsd = new LSD());
		mods.add(chestFinder = new ChestESP());
		mods.add(chestLooter = new ChestLooter());
		mods.add(tracer = new PlayerESP());
		mods.add(fly = new Fly());
		mods.add(xray = new Xray());
		mods.add(blink = new Blink());
		mods.add(jetPack = new Jetpack());
		mods.add(fastPlace = new FastPlace());
		mods.add(cocaine = new Cocaine());
	}
	
	public boolean hasConfig(String hack)
	{
		return byName(hack).hasConfig();
	}
	
	public List<ModBase> getMods()
	{
		return mods;
	}
	
	public void disableChat()
	{
		chat = false;
	}
	
	public void enableChat()
	{
		chat = true;
	}
	
	public boolean isChat()
	{
		return chat;
	}
	
	public List<ModBase> getMods(Category c2)
	{
		List<ModBase> il = new ArrayList<ModBase>();
		for (ModBase mb : getMods())
		{
			if (mb.category == c2)
			{
				il.add(mb);
			}
		}
		return il;
	}
	
	public ModBase byClass(Class<? extends ModBase> clazz)
	{
		for (ModBase mb : getMods())
		{
			if (mb.getClass().equals(clazz))
				return mb;
		}
		return mods.get(0);
	}
	
	public ModBase byName(String text)
	{
		for (ModBase mb : getMods())
		{
			if (mb.name.equals(text))
				return mb;
		}
		return mods.get(0);
	}
	
}
