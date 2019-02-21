package com.originalstudio.skyexplorer;

import javax.swing.JPanel;

public class Game extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6693183466444000235L;
	
	private StarShip ship = new StarShip(
			new Sprite(
						5,
						(Main.frame.getHeight()-Img.starship.getHeight(null))/2,
						this, Img.starship
					)
			);

	// 
	public Game()
	{
		
	}
}
