package org.darkstorm.minecraft.gui.listener;

import org.darkstorm.minecraft.gui.component.ToggleButton;

public interface ButtonListener extends ComponentListener {
	public void onButtonPress(ToggleButton button);
}
