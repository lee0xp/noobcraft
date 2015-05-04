package de.lee0xp.client.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import org.darkstorm.minecraft.gui.GuiManager;
import org.darkstorm.minecraft.gui.component.ToggleButton;
import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.component.Frame;
import org.darkstorm.minecraft.gui.component.basic.BasicButton;
import org.darkstorm.minecraft.gui.component.basic.BasicButton;
import org.darkstorm.minecraft.gui.component.basic.BasicFrame;
import org.darkstorm.minecraft.gui.listener.ButtonListener;
import org.darkstorm.minecraft.gui.theme.Theme;
import org.darkstorm.minecraft.gui.theme.simple.SimpleTheme;

import de.lee0xp.client.Client;
import de.lee0xp.client.Vars;
import de.lee0xp.client.hackutil.CType;
import de.lee0xp.client.hackutil.Category;
import de.lee0xp.client.hackutil.ModBase;

public class ModsGui extends GuiScreen
{
	
	private final GuiManager guiManager;
	
	private final Theme theme;
	
	public boolean setup = false;
	
	public ModsGui(GuiManager guiManager)
	{
		this.guiManager = guiManager;
		theme = new SimpleTheme();
		this.setup();
	}

	public void setup()
	{
		if (setup)
		{
			return;
		}
		setup = true;
		
		for (ModBase mb : Client.getInstance().getHacks().getMods()){
			mb.init();
		}
		addFramesByCategory();
		createZoomFrame();
	}
	
	private void addFramesByCategory()
	{
		int i = 10;
		for (Category c : Category.values()) 
		{
			final Frame frame = new BasicFrame((c.name().charAt(0) + "").toUpperCase() + c.name().substring(1, c.name().length()).toLowerCase());
			frame.setTheme(theme);
			
			for (final ModBase mb : Client.getInstance().getHacks().getMods(c))
			{
				BasicButton b = new BasicButton(mb.name != null ? mb.name : "missing_caption");
				b.addButtonListener(new ButtonListener()
				{
					
					@Override
					public void onButtonPress(ToggleButton button)
					{
						mb.toggle();
					}
					
				});
				
				frame.add(b);
			}
			
			frame.setX(i);
			frame.setY(10);
			
			Dimension defaultDimension = theme.getUIForComponent(frame).getDefaultSize(frame);
			frame.setWidth(defaultDimension.width);
			frame.setHeight(defaultDimension.height);
			frame.layoutChildren();
			frame.setPinnable(false);
			frame.setClosable(false);
			frame.setVisible(true);
			frame.setMinimized(false);
			
			Client.INSTANCE.manager.addFrame(frame);
			i += 100;
		}
		
	}
	
	private void createZoomFrame()
	{
		final Frame frame = new BasicFrame("Zoom");
		frame.setTheme(theme);
		
		BasicButton zoomUp = new BasicButton("+");
		BasicButton zoomDown = new BasicButton("-");
		BasicButton manualFov = new BasicButton("Manual FOV");
		
		manualFov.addButtonListener(new ButtonListener()
		{
			
			@Override
			public void onButtonPress(ToggleButton button)
			{
				Alert.inputBox("§eEnter the fov number (negatives flip the screen)", "§cManual FOV", CType.String(Minecraft.getMinecraft().gameSettings.fovSetting), ModsGui.this, new IAction()
				{
					
					@Override
					public void actionPerformed()
					{
						Vars.zoomFov = CType.Float(getText());
						Minecraft.getMinecraft().displayGuiScreen(ModsGui.this);
					}
					
					@Override
					public void actionCancelled()
					{
						Minecraft.getMinecraft().displayGuiScreen(ModsGui.this);
					}
					
				});
			}
			
		});
		zoomUp.addButtonListener(new ButtonListener()
		{
			
			@Override
			public void onButtonPress(ToggleButton button)
			{
				Vars.zoomFov -= 1;
			}
			
		});
		
		zoomDown.addButtonListener(new ButtonListener()
		{
			
			@Override
			public void onButtonPress(ToggleButton button)
			{
				Vars.zoomFov += 1;
			}
			
		});
		
		frame.add(zoomUp);
		
		frame.add(zoomDown);
		frame.add(manualFov);
		
		frame.setX(50);
		frame.setY(10);
		
		Dimension defaultDimension = theme.getUIForComponent(frame).getDefaultSize(frame);
		frame.setWidth(defaultDimension.width);
		frame.setHeight(defaultDimension.height);
		frame.layoutChildren();
		frame.setPinnable(false);
		frame.setClosable(false);
		frame.setVisible(true);
		frame.setMinimized(false);
		
		Client.INSTANCE.manager.addFrame(frame);
	}
	
	@Override
	protected void mouseClicked(int x, int y, int button) throws IOException
	{
		super.mouseClicked(x, y, button);
		for (Frame frame : guiManager.getFrames())
		{
			if (!frame.isVisible())
			{
				continue;
			}
			if (!frame.isMinimized() && !frame.getArea().contains(x, y))
			{
				for (Component component : frame.getChildren())
				{
					for (Rectangle area : component.getTheme().getUIForComponent(component).getInteractableRegions(component))
					{
						if (area.contains(x - frame.getX() - component.getX(), y - frame.getY() - component.getY()))
						{
							frame.onMousePress(x - frame.getX(), y - frame.getY(), button);
							guiManager.bringForward(frame);
							return;
						}
					}
				}
			}
		}
		for (Frame frame : guiManager.getFrames())
		{
			if (!frame.isVisible())
			{
				continue;
			}
			if (!frame.isMinimized() && frame.getArea().contains(x, y))
			{
				frame.onMousePress(x - frame.getX(), y - frame.getY(), button);
				guiManager.bringForward(frame);
				break;
			} else if (frame.isMinimized())
			{
				for (Rectangle area : frame.getTheme().getUIForComponent(frame).getInteractableRegions(frame))
				{
					if (area.contains(x - frame.getX(), y - frame.getY()))
					{
						frame.onMousePress(x - frame.getX(), y - frame.getY(), button);
						guiManager.bringForward(frame);
						return;
					}
				}
			}
		}
	}
	
	public void mouseClickMove(int x, int y, int button, long timeSinceLastClick)
	{
		super.mouseClickMove(x, y, button, timeSinceLastClick);
		for (Frame frame : guiManager.getFrames())
		{
			if (!frame.isVisible())
			{
				continue;
			}
			if (!frame.isMinimized() && !frame.getArea().contains(x, y))
			{
				for (Component component : frame.getChildren())
				{
					for (Rectangle area : component.getTheme().getUIForComponent(component).getInteractableRegions(component))
					{
						if (area.contains(x - frame.getX() - component.getX(), y - frame.getY() - component.getY()))
						{
							frame.onMouseRelease(x - frame.getX(), y - frame.getY(), button);
							guiManager.bringForward(frame);
							return;
						}
					}
				}
			}
		}
		for (Frame frame : guiManager.getFrames())
		{
			if (!frame.isVisible())
			{
				continue;
			}
			if (!frame.isMinimized() && frame.getArea().contains(x, y))
			{
				frame.onMouseRelease(x - frame.getX(), y - frame.getY(), button);
				guiManager.bringForward(frame);
				break;
			} else if (frame.isMinimized())
			{
				for (Rectangle area : frame.getTheme().getUIForComponent(frame).getInteractableRegions(frame))
				{
					if (area.contains(x - frame.getX(), y - frame.getY()))
					{
						frame.onMouseRelease(x - frame.getX(), y - frame.getY(), button);
						guiManager.bringForward(frame);
						return;
					}
				}
			}
		}
	}
	
	@Override
	public void drawScreen(int par2, int par3, float par4)
	{
		Frame[] frames = guiManager.getFrames();
		for (int i = frames.length - 1; i >= 0; i--)
		{
			frames[i].render();
		}
		super.drawScreen(par2, par3, par4);
	}
}