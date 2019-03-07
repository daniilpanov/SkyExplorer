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
	public static final Icon b_go_to_game_menu = getIcon("b_open_game_menu.png"),
			b_resume_game_menu = getIcon("b_resume.png"),
			b_exit_game_menu = getIcon("b_exit_at_game_menu.png"),

			b_exit = getIcon("b_exit.png"),
			b_help = getIcon("b_help.png"),
			b_play = getIcon("b_play.png"),
			b_settings = getIcon("b_settings.png");
	
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
