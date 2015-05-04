package de.lee0xp.client.plugin;

import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.net.URL;
import java.lang.reflect.Method;

public class PluginClassLoader
{
	
	private static final Class[] parameters = new Class[]
	{ URL.class };
	
	public static void addFile(String s) throws IOException
	{
		File f = new File(s);
		addFile(f);
	}//end method
	
	public static void addFile(File f)
	{
		try
		{
			addURL(f.toURL());
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end method
	
	public static void addURL(URL u)
	{
		
		URLClassLoader sysloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		Class sysclass = URLClassLoader.class;
		
		try
		{
			Method method = sysclass.getDeclaredMethod("addURL", parameters);
			method.setAccessible(true);
			method.invoke(sysloader, new Object[]
			{ u });
		} catch (Throwable t)
		{
			t.printStackTrace();
			
		}//end try catch
		
	}//end method
	
}//end class