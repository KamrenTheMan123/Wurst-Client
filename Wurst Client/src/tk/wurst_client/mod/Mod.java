/*
 * Copyright � 2014 - 2015 | Alexander01998 | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package tk.wurst_client.mod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import org.darkstorm.minecraft.gui.component.basic.BasicSlider;

import tk.wurst_client.Client;

public class Mod
{
	private final String name = getClass().getAnnotation(Info.class).name();
	private final String description = getClass().getAnnotation(Info.class).description();
	private final Category category = getClass().getAnnotation(Info.class).category();
	private boolean enabled;
	protected ArrayList<BasicSlider> sliders = new ArrayList<BasicSlider>();
	private long currentMS = 0L;
	protected long lastMS = -1L;

	public enum Category
	{
		AUTOBUILD,
		BLOCKS,
		CHAT,
		COMBAT,
		FUN,
		HIDDEN,
		RENDER,
		MISC,
		MOVEMENT,
		SETTINGS,
		WIP;
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Info
	{	
		String name();
		String description();
		Category category();
	}
	
	public final String getName()
	{
		return name;
	}
	
	public String getRenderName()
	{
		return name;
	}
	
	public final String getDescription()
	{
		return description;
	}
	
	public final Category getCategory()
	{
		return category;
	}
	
	public final boolean isEnabled()
	{
		return enabled;
	}
	
	public final void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
		onToggle();
		if(enabled)
			onEnable();
		else
			onDisable();
		Client.wurst.fileManager.saveMods();
	}
	
	public final void toggle()
	{
		setEnabled(!isEnabled());
	}
	
	public final ArrayList<BasicSlider> getSliders()
	{
		return sliders;
	}
	
	public final void setSliders(ArrayList<BasicSlider> newSliders)
	{
		sliders = newSliders;
	}
	
	public final void noCheatMessage()
	{
		Client.wurst.chat.warning(name + " cannot bypass NoCheat+.");
	}
	
	public final void updateMS()
	{
		currentMS = System.currentTimeMillis();
	}
	
	public final void updateLastMS()
	{
		lastMS = System.currentTimeMillis();
	}
	
	public final boolean hasTimePassedM(long MS)
	{
		return currentMS >= lastMS + MS;
	}
	
	public final boolean hasTimePassedS(float speed)
	{
		return currentMS >= lastMS + (long)(1000 / speed);
	}
	
	public void onToggle()
	{}
	
	public void onEnable()
	{}
	
	public void onDisable()
	{}
	
	public void initSliders()
	{}
	
	public void updateSettings()
	{}
}
