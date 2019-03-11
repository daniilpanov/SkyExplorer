package com.newlightstudio.skyexplorer;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class StarShip extends Sprite
{
	
	public StarShip(Component parent)
	{
		super(Main.frame.getWidth()/2, Main.frame.getHeight()/2,
                        Img.starship_r, Img.starship_l, Img.starship_t, Img.starship_b,
                        parent, true);
		
		//
		this.addControl(Main.frame);
	}
	
	private void shoot()
	{
		
	}
	
	@Override
	protected void checkingKey(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case VK_ENTER:
				shoot();
				break;
			
			case VK_UP:
				this.realMoveY(4, 2, 50, -1);
				//
				
				break;
			
			case VK_DOWN:
				this.realMoveY(4, 2, 50, 1);
				//
				
				break;
			
			case VK_RIGHT:
				this.realMoveX(4, 2, 50, 1);
				break;
			
			case VK_LEFT:
				this.realMoveX(4, 2, 50, -1);
				break;
		}
	}
}
