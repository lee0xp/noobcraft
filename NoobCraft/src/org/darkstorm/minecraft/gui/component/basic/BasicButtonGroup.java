package org.darkstorm.minecraft.gui.component.basic;

import java.util.*;

import org.darkstorm.minecraft.gui.component.*;

public class BasicButtonGroup implements ButtonGroup {
	private List<ToggleButton> buttons = new ArrayList<ToggleButton>();

	@Override
	public void addButton(ToggleButton button) {
		if(button == null)
			throw new NullPointerException();
		synchronized(buttons) {
			buttons.add(button);
		}
	}

	@Override
	public void removeButton(ToggleButton button) {
		if(button == null)
			throw new NullPointerException();
		synchronized(buttons) {
			buttons.remove(button);
		}
	}

	@Override
	public ToggleButton[] getButtons() {
		synchronized(buttons) {
			return buttons.toArray(new ToggleButton[buttons.size()]);
		}
	}

}
