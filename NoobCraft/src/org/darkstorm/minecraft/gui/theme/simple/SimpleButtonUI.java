package org.darkstorm.minecraft.gui.theme.simple;

import static org.lwjgl.opengl.GL11.*;

import java.awt.*;

import org.lwjgl.input.Mouse;
import org.darkstorm.minecraft.gui.component.ToggleButton;
import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.theme.AbstractComponentUI;
import org.darkstorm.minecraft.gui.util.RenderUtil;

import de.lee0xp.client.Client;

public class SimpleButtonUI extends AbstractComponentUI<ToggleButton>
{
	
	private final SimpleTheme theme;
	
	SimpleButtonUI(SimpleTheme theme)
	{
		super(ToggleButton.class);
		this.theme = theme;
		
		foreground = Color.WHITE;
		background = new Color(0, 128, 255);
	}
	
	@Override
	protected void renderComponent(ToggleButton button)
	{
		translateComponent(button, false);
		Rectangle area = button.getArea();
		area.width = button.getParent().getWidth() - 4;
		area.height = 11;
		
		glEnable(GL_BLEND);
		glDisable(GL_CULL_FACE);
		
		glDisable(GL_TEXTURE_2D);
		if (!false){
			RenderUtil.setColor(Client.getInstance().getHacks().byName(button.getText()).isEnabled() ? button.getBackgroundColor() : new Color(70, 70, 70));
			
		}
		glBegin(GL_QUADS);
		{
			glVertex2d(0, 0);
			glVertex2d(area.width, 0);
			glVertex2d(area.width, area.height);
			glVertex2d(0, area.height);
		}
		glEnd();
		Point mouse = RenderUtil.calculateMouseLocation();
		Component parent = button.getParent();
		while (parent != null)
		{
			mouse.x -= parent.getX();
			mouse.y -= parent.getY();
			parent = parent.getParent();
		}
		if (area.contains(mouse))
		{
			if (Mouse.isButtonDown(1))
			{
				if (Client.getInstance().getHacks().hasConfig(button.getText())){
					Client.getInstance().manager.addFrame(Client.getInstance().getHacks().byName(button.getText()).getFrame());
					Client.getInstance().manager.bringForward(Client.getInstance().getHacks().byName(button.getText()).getFrame());
				}
			}
			
		}
		glEnable(GL_TEXTURE_2D);
		
		String text = button.getText();
		int i = ((button.getParent().getWidth() - 4) / 2 - theme.getFontRenderer().getStringWidth(text) / 2);
		
		theme.getFontRenderer().drawString(text, i, area.height / 2 - theme.getFontRenderer().FONT_HEIGHT / 2, RenderUtil.toRGBA(button.getForegroundColor()));
		
		glEnable(GL_CULL_FACE);
		glDisable(GL_BLEND);
		translateComponent(button, true);
	}
	
	@Override
	protected Dimension getDefaultComponentSize(ToggleButton component)
	{
		return new Dimension(theme.getFontRenderer().getStringWidth(component.getText()) + 4, theme.getFontRenderer().FONT_HEIGHT + 4);
	}
	
	@Override
	protected Rectangle[] getInteractableComponentRegions(ToggleButton component)
	{
		return new Rectangle[]
		{ new Rectangle(0, 0, component.getWidth(), component.getHeight()) };
	}
	
	@Override
	protected void handleComponentInteraction(ToggleButton component, Point location, int button)
	{
		if (location.x <= component.getWidth() && location.y <= component.getHeight() && button == 0)
			component.press();
	}
}