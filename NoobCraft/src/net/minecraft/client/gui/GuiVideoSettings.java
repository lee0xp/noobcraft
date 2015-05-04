/*
 * Decompiled with CFR 0_99.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiOptionButton
 *  net.minecraft.client.gui.GuiOptionSlider
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.resources.I18n
 */
package net.minecraft.client.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

/*
 * Failed to analyse overrides
 */
public class GuiVideoSettings extends GuiScreen
{
	
	private GuiScreen parentGuiScreen;
	protected String screenTitle = "Video Settings";
	private GameSettings guiGameSettings;
	private boolean is64bit;
	private static GameSettings.Options[] videoOptions = new GameSettings.Options[]
	{ GameSettings.Options.GRAPHICS, GameSettings.Options.RENDER_DISTANCE, GameSettings.Options.AMBIENT_OCCLUSION, GameSettings.Options.FRAMERATE_LIMIT, GameSettings.Options.VIEW_BOBBING,
			GameSettings.Options.GUI_SCALE, GameSettings.Options.USE_VBO, GameSettings.Options.GAMMA, GameSettings.Options.BLOCK_ALTERNATIVES, GameSettings.Options.ANAGLYPH };
	private static final String __OBFID = "CL_00000718";
	private int lastMouseX = 0;
	private int lastMouseY = 0;
	private long mouseStillTime = 0;
	
	public GuiVideoSettings(GuiScreen par1GuiScreen, GameSettings par2GameSettings)
	{
		this.parentGuiScreen = par1GuiScreen;
		this.guiGameSettings = par2GameSettings;
	}
	
	public void initGui()
	{
		String[] var1;
		int x;
		this.screenTitle = I18n.format((String) "options.videoTitle", (Object[]) new Object[0]);
		this.buttonList.clear();
		this.is64bit = false;
		String[] var2 = var1 = new String[]
		{ "sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch" };
		int var3 = var1.length;
		for (int var4 = 0; var4 < var3; ++var4)
		{
			String var5 = var2[var4];
			String var6 = System.getProperty(var5);
			if (var6 == null || !var6.contains((CharSequence) "64"))
				continue;
			this.is64bit = true;
			break;
		}
		boolean var8 = false;
		var3 = this.is64bit ? 0 : -15;
		GameSettings.Options[] var9 = videoOptions;
		int var10 = var9.length;
		int var11 = 0;
		for (var11 = 0; var11 < var10; ++var11)
		{
			GameSettings.Options var7 = var9[var11];
			if (var7 == null)
				continue;
			x = this.width / 2 - 155 + var11 % 2 * 160;
			int y = this.height / 6 + 21 * (var11 / 2) - 10;
			if (var7.getEnumFloat())
			{
				this.buttonList.add(new GuiOptionSlider(var7.returnEnumOrdinal(), x, y, var7));
				continue;
			}
			this.buttonList.add(new GuiOptionButton(var7.returnEnumOrdinal(), x, y, var7, this.guiGameSettings.getKeyBinding(var7)));
		}
		int y = this.height / 6 + 21 * (var11 / 2) - 10;
		x = 0;
		x = this.width / 2 - 155 + 160;
		this.buttonList.add(new GuiOptionButton(202, x, y, "Quality..."));
		x = this.width / 2 - 155 + 0;
		this.buttonList.add(new GuiOptionButton(201, x, y += 21, "Details..."));
		x = this.width / 2 - 155 + 160;
		this.buttonList.add(new GuiOptionButton(212, x, y, "Performance..."));
		x = this.width / 2 - 155 + 0;
		this.buttonList.add(new GuiOptionButton(211, x, y += 21, "Animations..."));
		x = this.width / 2 - 155 + 160;
		this.buttonList.add(new GuiOptionButton(222, x, y, "Other..."));
		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168 + 11, I18n.format((String) "gui.done", (Object[]) new Object[0])));
	}
	
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		if (par1GuiButton.enabled)
		{
			int var2 = this.guiGameSettings.guiScale;
			if (par1GuiButton.id < 200 && par1GuiButton instanceof GuiOptionButton)
			{
				this.guiGameSettings.setOptionValue(((GuiOptionButton) par1GuiButton).returnEnumOptions(), 1);
				par1GuiButton.displayString = this.guiGameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(par1GuiButton.id));
			}
			if (par1GuiButton.id == 200)
			{
				this.mc.gameSettings.saveOptions();
				this.mc.displayGuiScreen(this.parentGuiScreen);
			}
			if (this.guiGameSettings.guiScale != var2)
			{
				ScaledResolution var3 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
				int var4 = var3.getScaledWidth();
				int var5 = var3.getScaledHeight();
				this.setWorldAndResolution(this.mc, var4, var5);
			}
			
		}
	}
	
	public void drawScreen(int x, int y, float z)
	{
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, this.is64bit ? 20 : 5, 16777215);
		if (this.is64bit || this.guiGameSettings.renderDistanceChunks > 8)
		{
			// empty if block
		}
		super.drawScreen(x, y, z);
		if (Math.abs(x - this.lastMouseX) > 5 || Math.abs(y - this.lastMouseY) > 5)
		{
			this.lastMouseX = x;
			this.lastMouseY = y;
			this.mouseStillTime = System.currentTimeMillis();
			return;
		}
		int activateDelay = 700;
		if (System.currentTimeMillis() < this.mouseStillTime + (long) activateDelay)
		{
			return;
		}
		int x1 = this.width / 2 - 150;
		int y1 = this.height / 6 - 5;
		if (y <= y1 + 98)
		{
			y1 += 105;
		}
		int x2 = x1 + 150 + 150;
		int y2 = y1 + 84 + 10;
		GuiButton btn = this.getSelectedButton(x, y);
		if (btn != null)
		{
			String s = this.getButtonName(btn.displayString);
			String[] lines = this.getTooltipLines(s);
			if (lines == null)
			{
				return;
			}
			this.drawGradientRect(x1, y1, x2, y2, -536870912, -536870912);
			for (int i = 0; i < lines.length; ++i)
			{
				String line = lines[i];
				this.fontRendererObj.func_175063_a(line, x1 + 5, y1 + 5 + i * 11, 14540253);
			}
		}
	}
	
	private String[] getTooltipLines(String btnName)
	{
		if (btnName.equals("Graphics"))
		{
			return new String[]
			{ "Visual quality", "  Fast  - lower quality, faster", "  Fancy - higher quality, slower", "Changes the appearance of clouds, leaves, water,", "shadows and grass sides." };
		}
		if (btnName.equals("Render Distance"))
		{
			return new String[]
			{ "Visible distance", "  2 Tiny - 32m (fastest)", "  4 Short - 64m (faster)", "  8 Normal - 128m", "  16 Far - 256m (slower)", "  32 Extreme - 512m (slowest!)",
					"The Extreme view distance is very resource demanding!", "Values over 16 Far are only effective in local worlds." };
		}
		if (btnName.equals("Smooth Lighting"))
		{
			return new String[]
			{ "Smooth lighting", "  OFF - no smooth lighting (faster)", "  Minimum - simple smooth lighting (slower)", "  Maximum - complex smooth lighting (slowest)" };
		}
		if (btnName.equals("Smooth Lighting Level"))
		{
			return new String[]
			{ "Smooth lighting level", "  OFF - no shadows", "  50% - light shadows", "  100% - dark shadows" };
		}
		if (btnName.equals("Max Framerate"))
		{
			return new String[]
			{ "Max framerate", "  VSync - limit to monitor framerate (60, 30, 20)", "  5-255 - variable", "  Unlimited - no limit (fastest)", "The framerate limit decreases the FPS even if",
					"the limit value is not reached." };
		}
		if (btnName.equals("View Bobbing"))
		{
			return new String[]
			{ "More realistic movement.", "When using mipmaps set it to OFF for best results." };
		}
		if (btnName.equals("GUI Scale"))
		{
			return new String[]
			{ "GUI Scale", "Smaller GUI might be faster" };
		}
		if (btnName.equals("Server Textures"))
		{
			return new String[]
			{ "Server textures", "Use the resource pack recommended by the server" };
		}
		if (btnName.equals("Advanced OpenGL"))
		{
			return new String[]
			{ "Detect and render only visible geometry", "  OFF - all geometry is rendered (slower)", "  Fast - only visible geometry is rendered (fastest)",
					"  Fancy - conservative, avoids visual artifacts (faster)", "The option is available only if it is supported by the ", "graphic card." };
		}
		if (btnName.equals("Fog"))
		{
			return new String[]
			{ "Fog type", "  Fast - faster fog", "  Fancy - slower fog, looks better", "  OFF - no fog, fastest", "The fancy fog is available only if it is supported by the ", "graphic card." };
		}
		if (btnName.equals("Fog Start"))
		{
			return new String[]
			{ "Fog start", "  0.2 - the fog starts near the player", "  0.8 - the fog starts far from the player", "This option usually does not affect the performance." };
		}
		if (btnName.equals("Brightness"))
		{
			return new String[]
			{ "Increases the brightness of darker objects", "  OFF - standard brightness", "  100% - maximum brightness for darker objects", "This options does not change the brightness of ",
					"fully black objects" };
		}
		if (btnName.equals("Chunk Loading"))
		{
			return new String[]
			{ "Chunk Loading", "  Default - unstable FPS when loading chunks", "  Smooth - stable FPS", "  Multi-Core - stable FPS, 3x faster world loading",
					"Smooth and Multi-Core remove the stuttering and ", "freezes caused by chunk loading.", "Multi-Core can speed up 3x the world loading and",
					"increase FPS by using a second CPU core." };
		}
		if (btnName.equals("Alternate Blocks"))
		{
			return new String[]
			{ "Alternate Blocks", "Uses alternative block models for some blocks.", "Depends on the selected resource pack." };
		}
		if (btnName.equals("Use VBOs"))
		{
			return new String[]
			{ "Vertex Buffer Objects", "Uses an alternative rendering model which is usually", "faster (5-10%) than the default rendering." };
		}
		if (btnName.equals("3D Anaglyph"))
		{
			return new String[]
			{ "3D Anaglyph", "Enables a stereoscopic 3D effect using different colors", "for each eye.", "Requires red-cyan glasses for proper viewing." };
		}
		return null;
	}
	
	private String getButtonName(String displayString)
	{
		int pos = displayString.indexOf(58);
		if (pos < 0)
		{
			return displayString;
		}
		return displayString.substring(0, pos);
	}
	
	private GuiButton getSelectedButton(int i, int j)
	{
		for (int k = 0; k < this.buttonList.size(); ++k)
		{
			boolean flag;
			GuiButton btn = (GuiButton) this.buttonList.get(k);
			boolean bl = flag = i >= btn.xPosition && j >= btn.yPosition && i < btn.xPosition + btn.width && j < btn.yPosition + btn.height;
			if (!flag)
				continue;
			return btn;
		}
		return null;
	}
	
	public static int getButtonWidth(GuiButton btn)
	{
		return btn.width;
	}
	
	public static int getButtonHeight(GuiButton btn)
	{
		return btn.height;
	}
}
