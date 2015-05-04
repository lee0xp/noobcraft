package net.minecraft.client.gui.inventory;

import java.io.IOException;

import de.lee0xp.client.Client;
import de.lee0xp.client.Vars;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiChest extends GuiContainer
{
	
	private static final ResourceLocation field_147017_u = new ResourceLocation("textures/gui/container/generic_54.png");
	private IInventory upperChestInventory;
	private IInventory lowerChestInventory;
	
	/**
	 * window height is calculated with these values; the more rows, the heigher
	 */
	private int inventoryRows;
	private static final String __OBFID = "CL_00000749";
	
	public GuiChest(IInventory p_i46315_1_, IInventory p_i46315_2_)
	{
		super(new ContainerChest(p_i46315_1_, p_i46315_2_, Minecraft.getMinecraft().thePlayer));
		this.upperChestInventory = p_i46315_1_;
		this.lowerChestInventory = p_i46315_2_;
		this.allowUserInput = false;
		short var3 = 222;
		int var4 = var3 - 108;
		this.inventoryRows = p_i46315_2_.getSizeInventory() / 9;
		this.ySize = var4 + this.inventoryRows * 18;
		
	}
	
	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items). Args : mouseX, mouseY
	 */
	public void initGui()
	{
		this.buttonList.add(new GuiButton(0, this.width / 2 - 80, 10, 76, 20, "Steal"));
		this.buttonList.add(new GuiButton(1, this.width / 2 +4, 10, 76, 20, "Store"));
		super.initGui();
		
	}
	
	boolean stolen = false;
	
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		this.fontRendererObj.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, 4210752);
		this.fontRendererObj.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
		if (!stolen)
		{
			if (Client.getInstance().getHacks().chestLooter.isEnabled())
				de.lee0xp.client.hackutil.Util.stealChest(Minecraft.getMinecraft().thePlayer.openContainer.windowId);
			stolen = true;
		}
		
	}
	/**
	 * Args : renderPartialTicks, mouseX, mouseY
	 */
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(field_147017_u);
		int var4 = (this.width - this.xSize) / 2;
		int var5 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(var4, var5 + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if (button.id == 0)
			de.lee0xp.client.hackutil.Util.stealChest(Minecraft.getMinecraft().thePlayer.openContainer.windowId);
		if (button.id == 1)
			de.lee0xp.client.hackutil.Util.storeChest(Minecraft.getMinecraft().thePlayer.openContainer.windowId);
	}
}
