package org.darkstorm.minecraft.gui.theme.simple;

import java.awt.Font;

import net.minecraft.client.gui.FontRenderer;

import org.darkstorm.minecraft.gui.font.UnicodeFontRenderer;
import org.darkstorm.minecraft.gui.theme.AbstractTheme;

public class SimpleTheme extends AbstractTheme {
	public FontRenderer fontRenderer;

	public SimpleTheme() {
		fontRenderer = new UnicodeFontRenderer(new Font("Verdana", Font.PLAIN, 18));

		installUI(new SimpleFrameUI(this));
		installUI(new SimplePanelUI(this));
		installUI(new SimpleLabelUI(this));
		installUI(new SimpleButtonUI(this));
		installUI(new SimpleCheckButtonUI(this));
		installUI(new SimpleComboBoxUI(this));
		installUI(new SimpleSliderUI(this));
		installUI(new SimpleProgressBarUI(this));
	}

	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}
}
