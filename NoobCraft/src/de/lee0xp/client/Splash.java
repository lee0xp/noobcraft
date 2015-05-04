package de.lee0xp.client;

import java.util.Random;

public class Splash
{
	
	private static String[] splashes = new String[] {};
	
	public static String random()
	{
		splashes = new String[]
		{ "Open source!", "Beta version!", "played by noobs!", "coded by lee0xp§e!", "§x§eTTF-Fonts!", "Open source!" };
		
		int rand = new Random().nextInt(splashes.length);
		return splashes[rand];
	}
}
