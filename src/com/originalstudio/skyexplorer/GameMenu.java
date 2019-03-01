package com.originalstudio.skyexplorer;

import javax.swing.JWindow;

public class GameMenu extends JWindow {

	private static GameMenu instance = null;
	
	public static GameMenu getInstance()
	{
		if (instance == null)
		{
			instance = new GameMenu();
		}
		
		return instance;
	}
	
	private GameMenu()
	{
		setBounds(0, 0, 500, 500);
	}

	public void make_hide()
	{
		setVisible(false);
	}
	
	public void make_show()
	{
		setVisible(true);
	}
}
