package de.lee0xp.client.plugin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;

import de.lee0xp.client.plugin.interfaces.NoobPlugin;

public class PluginLoader
{
	public HashMap<String, NoobPlugin> plugins;
	
	public PluginLoader()
	{
		plugins = new HashMap<>();
	}
	
	public void loadPlugin(String filename) throws Exception
	{
		System.out.println("Trying to load plugin " + filename);
		
		File file = new File("NoobCraft/plugins/" + filename);
		if (!file.exists())
		{
			System.out.println("File is missing: " + filename);
			return;
		}
		
		PluginClassLoader.addFile(file);
		Class c = Class.forName(readMain(file.toString()));
		NoobPlugin nb = (NoobPlugin) c.newInstance();
		
		if (!plugins.containsKey(nb.getDescription().name)){
			plugins.put(nb.getDescription().name, nb);

			nb.onEnable();
			return;
		}
		System.out.println("Error! Plugin " + nb.getDescription().name +  " already loaded!"); 
	}
	
	public String readMain(String file) throws IOException{
		OutputStream out = new FileOutputStream("NoobCraft/plugins/main.txt");
		FileInputStream fin = new FileInputStream(file);
		BufferedInputStream bin = new BufferedInputStream(fin);
		ZipInputStream zin = new ZipInputStream(bin);
		ZipEntry ze = null;
		while ((ze = zin.getNextEntry()) != null) {
		    if (ze.getName().equals("main.txt")) {
		        byte[] buffer = new byte[8192];
		        int len;
		        while ((len = zin.read(buffer)) != -1) {
		            out.write(buffer, 0, len);
		        }
		        out.close();
		        break;
		    }
		}
		zin.close();
		File tmp = new File("NoobCraft/plugins/main.txt");
		String contents = FileUtils.readFileToString(tmp);
		tmp.delete();
		return contents;
	}
}
