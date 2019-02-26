package com.originalstudio.skyexplorer;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import static com.originalstudio.skyexplorer.Img.*;

public class Game extends JPanel
{
	/**
	 *
	 */
	private static final long serialVersionUID = 6693183466444000235L;
	
	private StarShip starShip = new StarShip(this);
	private int bg_w = bg.getWidth(null), bg_h = bg.getHeight(null);
	private int[][] bg_x =
				{
						{-bg_w, 0, bg_w},
						{-bg_w, 0, bg_w},
						{-bg_w, 0, bg_w}
				},
			bg_y =
				{
						{-bg_h, -bg_h, -bg_h},
						{0, 0, 0},
						{bg_h, bg_h, bg_h}
				};

	//
	public Game()
	{
	
	}
	
	public void shiftX(int dir)
	{
		if (dir == 1)
		{
			for (int i = 0; i < 3; i++)
			{
				bg_x[i][0] = bg_x[i][2] + bg_w;
				bg_x[i] = replaceCoordinates(bg_y[i], 0, 2);
                bg_x[i] = replaceCoordinates(bg_y[i], 0, 1);
			}
		}
		else if (dir == -1)
		{
			for (int i = 0; i < 3; i++)
			{
				bg_x[i][2] = bg_x[i][0] + bg_w;
                bg_x[i] = replaceCoordinates(bg_y[i], 2, 0);
                bg_x[i] = replaceCoordinates(bg_y[i], 2, 1);
			}
		}
	}
	
	public void shiftY(int dir)
	{
		if (dir == 1)
		{
			for (int i = 0; i < 3; i++)
			{
				bg_y[i][0] = bg_y[i][2] + bg_h;
				bg_y[i] = replaceCoordinates(bg_y[i], 0, 2);
                bg_y[i] = replaceCoordinates(bg_y[i], 0, 1);
			}
		}
		else if (dir == -1)
		{
			for (int i = 0; i < 3; i++)
			{
				bg_y[i][2] = bg_y[i][0] + bg_h;
                bg_y[i] = replaceCoordinates(bg_y[i], 2, 0);
                bg_y[i] = replaceCoordinates(bg_y[i], 2, 1);
			}
		}
	}
	
	private int[] replaceCoordinates(int[] array, int i, int to)
	{
		int tmp = array[to];
		array[to] = array[i];
		array[i] = tmp;
		return array;
	}
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
		int i = 0, j = 0; //
		do
		{
			j++; //
			//
			if (j >= 3)
			{
				j = 0;
				i++;
			}
			//
			g.drawImage(bg, bg_x[i][j], bg_y[i][j], null);
		}
		//
		while (i < 2);
        
        starShip.ship.render(g);
    }
}
