package de.lee0xp.client.hackutil;




import com.mojang.authlib.GameProfile;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.util.MovementInput;
import net.minecraft.world.World;

public class FreecamEntity extends EntityOtherPlayerMP
{
	public FreecamEntity(World par1World, GameProfile par2Str)
	{
		super(par1World, par2Str);
	}
	 
	public MovementInput movementInput = null;

	public boolean flyMode = false;
	
	public void setMovementInput(MovementInput par1MovementInput)
	{
		this.movementInput = par1MovementInput;
		
		if(par1MovementInput.jump && !flyMode && onGround)
		{
			this.jump();
		}
		
		this.moveEntityWithHeading(par1MovementInput.moveStrafe, par1MovementInput.moveForward);
	}
	
	public void moveEntity(double x, double y, double z)
	{
		if(flyMode) onGround = true;
		super.moveEntity(x, y, z);
		if(flyMode) onGround = true;
	}
	
	public boolean isSneaking()
	{
		return false;//this.movementInput.sneak && !flyMode;
	}
	
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		if(flyMode)
		{
			noClip = true;
			motionX = 0;
			motionY = 0;
			motionZ = 0;
			this.landMovementFactor = 2;
			this.jumpMovementFactor = 2;
			if(this.movementInput != null)
			{
				if(this.movementInput.jump)
				{
					motionY += 2f;
				}
				if(this.movementInput.sneak)
				{
					motionY -= 2f;
				}
			}
		}else
		{
			if(!isSprinting())
			{
				this.landMovementFactor = 0.1F;
				this.jumpMovementFactor = 0.02F;
			}
			noClip = false;
		}
	}
}
