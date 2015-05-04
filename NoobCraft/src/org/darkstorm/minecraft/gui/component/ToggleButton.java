package org.darkstorm.minecraft.gui.component;

import org.darkstorm.minecraft.gui.listener.ButtonListener;

public interface ToggleButton extends Component, TextComponent {
	public void press();

	public void addButtonListener(ButtonListener listener);

	public void removeButtonListener(ButtonListener listener);

	public ButtonGroup getGroup();

	public void setGroup(ButtonGroup group);
}
