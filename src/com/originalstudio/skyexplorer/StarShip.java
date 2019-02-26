package com.originalstudio.skyexplorer;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.originalstudio.skyexplorer.Img.*;

import static java.awt.event.KeyEvent.*;

public class StarShip
{
	public Sprite ship;
	public int x, y;
	
	public StarShip(Component parent)
	{
		this.ship =
                new Sprite(Main.frame.getWidth()/2, Main.frame.getHeight()/2,
                        starship_r, starship_l, starship_t, starship_b,
                        parent, true)
		{
			@Override
			protected void checkingKey(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
					case VK_ENTER:
						shoot();
						break;
					
					case VK_UP:
						realMoveY(50, 500, 5, -1);
						break;
					
					case VK_DOWN:
                        realMoveY(50, 500, 5, 1);
						break;
					
					case VK_RIGHT:
                        realMoveX(50, 500, 5, 1);
						break;
					
					case VK_LEFT:
                        realMoveX(50, 500, 5, -1);
						break;
				}
			}
		};
		
		this.x = ship.x;
		this.y = ship.y;
		
		//
		this.ship.addControl(Main.frame);
	}
	
	private void shoot()
	{
		
	}
}
