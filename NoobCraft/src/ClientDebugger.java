import de.lee0xp.client.Vars;
import java.io.IOException;
import java.util.Arrays;
import net.minecraft.client.main.Main;

public class ClientDebugger
{
  public static void main(String[] args)
    throws IOException
  {
	  if (args.length == 0){ 
		  Vars.debug = true;
	  }
	   
    Main.main((String[])concat(
      new String[] { "--version", "mcp", "--username", Vars.devUser, "--uuid", Vars.uuid, "--accessToken", Vars.authToken, "--assetsDir", "assets", "--assetIndex", "1.8", "--userProperties", "{}" }, args));
  }
  
  public static <T> T[] concat(T[] first, T[] second)
  {
    Object[] result = Arrays.copyOf(first, first.length + second.length);
    System.arraycopy(second, 0, result, first.length, second.length);
    return (T[]) result;
  }
}
