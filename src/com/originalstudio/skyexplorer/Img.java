package com.originalstudio.skyexplorer;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Img
{
	private static final String PATH = "res/img/";
	
	public static final Image starship_r = getImg("starship_r.png"),
			starship_l = getImg("starship_l.png"),
			starship_t = getImg("starship_t.png"),
			starship_b = getImg("starship_b.png"),
			planet = getImg("planet.png"),
			bg = getImg("bg.png"),
			bg_with_tuman = getImg("bg_with_tuman.jpg");
			//
	public static final Icon b_top_menu = getIcon("b_open_game_menu.jpg"),
			b_resume = getIcon("b_resume.jpg"), b_exit = getIcon("b_exit.jpg");
	
	private Img()
	{
	}
	
	private static Image getImg(String name)
	{
		return new ImageIcon(PATH + name).getImage();
	}
	
	private static Icon getIcon(String name)
	{
		return new ImageIcon(PATH + name);
	}
}
