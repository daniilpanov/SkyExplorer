package com.originalstudio.skyexplorer;

import java.awt.Component;
import java.awt.Image;

public class Sprite
{
	public int x, y;
	public Component parent;
	public Image sprite;
	
	public Sprite(int x, int y, Component parent, Image sprite)
	{
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.sprite = sprite;
	}
}
