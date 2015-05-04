package de.lee0xp.client.gui;

public abstract class IAction
{
	
	private String text;
	
	public void setText(String t)
	{
		text = t;
	}
	
	public abstract void actionPerformed();
	public abstract void actionCancelled();
	
	public String getText()
	{
		return this.text;
	}
}
