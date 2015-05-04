package net.minecraft.client.renderer.tileentity;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import de.lee0xp.client.Client;
import de.lee0xp.client.hacks.ChestESP;
import de.lee0xp.client.hackutil.RenderUtils;

public class TileEntityChestRenderer extends TileEntitySpecialRenderer
{
	
	private static final ResourceLocation textureTrappedDouble = new ResourceLocation("textures/entity/chest/trapped_double.png");
	private static final ResourceLocation textureChristmasDouble = new ResourceLocation("textures/entity/chest/christmas_double.png");
	private static final ResourceLocation textureNormalDouble = new ResourceLocation("textures/entity/chest/normal_double.png");
	private static final ResourceLocation textureTrapped = new ResourceLocation("textures/entity/chest/trapped.png");
	private static final ResourceLocation textureChristmas = new ResourceLocation("textures/entity/chest/christmas.png");
	private static final ResourceLocation textureNormal = new ResourceLocation("textures/entity/chest/normal.png");
	private ModelChest simpleChest = new ModelChest();
	private ModelChest largeChest = new ModelLargeChest();
	private boolean isChristams;
	private static final String __OBFID = "CL_00000965";
	
	public TileEntityChestRenderer()
	{
		Calendar var1 = Calendar.getInstance();
		
		if (var1.get(2) + 1 == 12 && var1.get(5) >= 24 && var1.get(5) <= 26)
		{
			this.isChristams = true;
		}
	}
	
	public void func_180538_a(TileEntityChest paramChest, double paramDouble1, double paramDouble2, double paramDouble3, float p_180538_8_, int p_180538_9_)
	{
		int var10;
		
		if (!paramChest.hasWorldObj())
		{
			var10 = 0;
		} else
		{
			Block var11 = paramChest.getBlockType();
			var10 = paramChest.getBlockMetadata();
			
			if (var11 instanceof BlockChest && var10 == 0)
			{
				((BlockChest) var11).checkForSurroundingChests(paramChest.getWorld(), paramChest.getPos(), paramChest.getWorld().getBlockState(paramChest.getPos()));
				var10 = paramChest.getBlockMetadata();
			}
			
			paramChest.checkForAdjacentChests();
		}
		
		if (paramChest.adjacentChestZNeg == null && paramChest.adjacentChestXNeg == null)
		{
			ModelChest var15;
			
			if (paramChest.adjacentChestXPos == null && paramChest.adjacentChestZPos == null)
			{
				var15 = this.simpleChest;
				
				if (p_180538_9_ >= 0)
				{
					this.bindTexture(DESTROY_STAGES[p_180538_9_]);
					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(4.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				} else if (paramChest.getChestType() == 1)
				{
					this.bindTexture(textureTrapped);
				} else if (this.isChristams)
				{
					this.bindTexture(textureChristmas);
				} else
				{
					this.bindTexture(textureNormal);
				}
			} else
			{
				var15 = this.largeChest;
				
				if (p_180538_9_ >= 0)
				{
					this.bindTexture(DESTROY_STAGES[p_180538_9_]);
					GlStateManager.matrixMode(5890);
					GlStateManager.pushMatrix();
					GlStateManager.scale(8.0F, 4.0F, 1.0F);
					GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
					GlStateManager.matrixMode(5888);
				} else if (paramChest.getChestType() == 1)
				{
					this.bindTexture(textureTrappedDouble);
				} else if (this.isChristams)
				{
					this.bindTexture(textureChristmasDouble);
				} else
				{
					this.bindTexture(textureNormalDouble);
				}
			}
			
			GlStateManager.pushMatrix();
			GlStateManager.enableRescaleNormal();
			
			if (p_180538_9_ < 0)
			{
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			}
			
			GlStateManager.translate((float) paramDouble1, (float) paramDouble2 + 1.0F, (float) paramDouble3 + 1.0F);
			GlStateManager.scale(1.0F, -1.0F, -1.0F);
			GlStateManager.translate(0.5F, 0.5F, 0.5F);
			short var12 = 0;
			
			if (var10 == 2)
			{
				var12 = 180;
			}
			
			if (var10 == 3)
			{
				var12 = 0;
			}
			
			if (var10 == 4)
			{
				var12 = 90;
			}
			
			if (var10 == 5)
			{
				var12 = -90;
			}
			
			if (var10 == 2 && paramChest.adjacentChestXPos != null)
			{
				GlStateManager.translate(1.0F, 0.0F, 0.0F);
			}
			
			if (var10 == 5 && paramChest.adjacentChestZPos != null)
			{
				GlStateManager.translate(0.0F, 0.0F, -1.0F);
			}
			
			GlStateManager.rotate((float) var12, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(-0.5F, -0.5F, -0.5F);
			float var13 = paramChest.prevLidAngle + (paramChest.lidAngle - paramChest.prevLidAngle) * p_180538_8_;
			float var14;
			
			if (paramChest.adjacentChestZNeg != null)
			{
				var14 = paramChest.adjacentChestZNeg.prevLidAngle + (paramChest.adjacentChestZNeg.lidAngle - paramChest.adjacentChestZNeg.prevLidAngle) * p_180538_8_;
				
				if (var14 > var13)
				{
					var13 = var14;
				}
			}
			
			if (paramChest.adjacentChestXNeg != null)
			{
				var14 = paramChest.adjacentChestXNeg.prevLidAngle + (paramChest.adjacentChestXNeg.lidAngle - paramChest.adjacentChestXNeg.prevLidAngle) * p_180538_8_;
				
				if (var14 > var13)
				{
					var13 = var14;
				}
			}
			
			var13 = 1.0F - var13;
			var13 = 1.0F - var13 * var13 * var13;
			var15.chestLid.rotateAngleX = -(var13 * (float) Math.PI / 2.0F);
			var15.renderAll();
			GlStateManager.disableRescaleNormal();
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			if (p_180538_9_ >= 0)
			{
				GlStateManager.matrixMode(5890);
				GlStateManager.popMatrix();
				GlStateManager.matrixMode(5888);
			}
		}
		
		if (Client.getInstance().getHacks().byClass(ChestESP.class).isEnabled())
		{
			RenderUtils.drawChestESP(paramDouble1, paramDouble2, paramDouble3);
		}
	}
	
	public void renderTileEntityAt(TileEntity p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_)
	{
		this.func_180538_a((TileEntityChest) p_180535_1_, p_180535_2_, p_180535_4_, p_180535_6_, p_180535_8_, p_180535_9_);
	}
}
