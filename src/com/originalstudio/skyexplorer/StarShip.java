package com.originalstudio.skyexplorer;

import java.awt.event.KeyEvent;

import javax.swing.Timer;

import static java.awt.event.KeyEvent.*;

public class StarShip
{
	public Sprite ship;
	public int x, y;
	
	private Timer moving = new Timer(500, e ->
	{
		
	});
	
	public StarShip(Sprite ship)
	{
		this.ship = ship;
		this.x = ship.x;
		this.y = ship.y;
	}
	
	public void movies(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case VK_ENTER:
			
			break;

		case VK_UP:
			
			break;
		
		case VK_DOWN:
			
			break;
		
		case VK_RIGHT:
			
			break;
			
		case VK_LEFT:
			
			break;
		}
	}
	
	private void shoot()
	{
		
	}
	
	private void move_x(byte k)
	{
		
	}
	
	private void move_y()
	{
		
	}
}
