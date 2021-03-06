/*
 * Copyright � 2014 - 2015 | Alexander01998 | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.wurst_client.mod.mods;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import tk.wurst_client.Client;
import tk.wurst_client.event.EventManager;
import tk.wurst_client.event.listeners.LeftClickListener;
import tk.wurst_client.mod.Mod;
import tk.wurst_client.mod.Mod.Category;
import tk.wurst_client.mod.Mod.Info;

@Info(category = Category.COMBAT,
	description = "Changes all your hits to critical hits.",
	name = "Criticals")
public class Criticals extends Mod implements LeftClickListener
{
	@Override
	public void onEnable()
	{
		EventManager.addLeftClickListener(this);
	}
	
	@Override
	public void onDisable()
	{
		EventManager.removeLeftClickListener(this);
	}
	
	@Override
	public void onLeftClick()
	{
		if(Minecraft.getMinecraft().objectMouseOver != null
			&& Minecraft.getMinecraft().objectMouseOver.entityHit instanceof EntityLivingBase)
			doCritical();
	}
	
	public static void doCritical()
	{
		if(!Client.wurst.modManager.getModByClass(Criticals.class)
			.isEnabled())
			return;
		if(!Minecraft.getMinecraft().thePlayer.isInWater()
			&& !Minecraft.getMinecraft().thePlayer
				.isInsideOfMaterial(Material.lava)
			&& Minecraft.getMinecraft().thePlayer.onGround)
		{
			Minecraft.getMinecraft().thePlayer.motionY = 0.1F;
			Minecraft.getMinecraft().thePlayer.fallDistance = 0.1F;
			Minecraft.getMinecraft().thePlayer.onGround = false;
		}
	}
}
