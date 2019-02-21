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
                new Sprite(0, Main.frame.getHeight()/2,
                        starship_r, starship_l, starship_t, starship_b,
                        parent)
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
						this.moving(new Image[]{starship_t}, 10, 5, -1, Y, 5, 25, 5);
						break;
					
					case VK_DOWN:
						
						break;
					
					case VK_RIGHT:
						
						break;
					
					case VK_LEFT:
      
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
