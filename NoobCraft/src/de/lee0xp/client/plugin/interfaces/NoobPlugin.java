package de.lee0xp.client.plugin.interfaces;

public abstract class NoobPlugin
{
	
	public abstract void onEnable();
	public abstract void onDisable();
	public abstract PluginDescription getDescription();
	
	public static class PluginDescription
	{
		
		public String name, version, author;
		
		public PluginDescription(String name, String version, String author)
		{
			this.name = name;
			this.version = version;
			this.author = author;
		}
	}
}
