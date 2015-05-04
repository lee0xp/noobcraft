package de.lee0xp.client.hacks;

import org.lwjgl.input.Keyboard;

import de.lee0xp.client.Client;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;
import de.lee0xp.client.hackutil.ModBaseEmpty;

public class Jetpack extends ModBaseEmpty
{
	
	private boolean couldFly;
	@Override
	public void onEnable(){
		if (Client.getInstance().getHacks().byClass(Fly.class).isEnabled()){
			Client.getInstance().getHacks().byClass(Fly.class).toggle();
		}
		couldFly = getPlayer().capabilities.allowFlying;
		getPlayer().capabilities.allowFlying = false;
	}
	
	@Override
	public void onDisable(){
		getPlayer().capabilities.allowFlying = couldFly;
	}
	
	@Override
	public void init()
	{
		init(Keyboard.KEY_J, "Jetpack", Category.MOVEMENT);
	}
	
}
