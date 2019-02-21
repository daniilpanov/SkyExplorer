package com.originalstudio.skyexplorer;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Img
{
	private static final String PATH = "res/img/";
	
	public static final Image starship = getImg("starship.png"),
			flying_starship = getImg("starship_flying.png"),
			planet = getImg("planet.png"),
			bg = getImg("bg.jpg"),
			bg_with_tuman = getImg("bg_with_tuman.jpg");
	
	private Img()
	{
	}
	
	private static Image getImg(String name)
	{
		return new ImageIcon(PATH + name).getImage();
	}
}
