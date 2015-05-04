package org.darkstorm.minecraft.gui.component;

public interface ButtonGroup {
	public void addButton(ToggleButton button);

	public void removeButton(ToggleButton button);

	public ToggleButton[] getButtons();
}
