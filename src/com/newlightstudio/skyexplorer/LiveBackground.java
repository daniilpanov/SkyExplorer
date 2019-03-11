package com.newlightstudio.skyexplorer;

import javax.swing.*;

import static com.newlightstudio.skyexplorer.Img.bg;

public class LiveBackground extends JPanel implements Bg
{
    static int bg_w = bg.getWidth(null), bg_h = bg.getHeight(null);
    
    int[][] bg_x =
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
    
    @Override
    public void updateBgX(int length, int dir)
    {
        length *= dir * (-1);
        
        for (int i = 0; i < bg_x.length; i++)
        {
        	for(int j = 0; j < bg_x[i].length; j++)
        	{
        		bg_x[i][j] += length;
        	}
        }
        if (bg_x[0][0] >= 0 || bg_x[0][2] <= 0)
        {
        	Game.control.shiftX(dir);
        }
        repaint();
    }
    
    @Override
    public void updateBgY(int length, int dir)
    {
        length *= dir * (-1);
        
        for (int i = 0; i < bg_y.length; i++)
        {
        	for(int j = 0; j < bg_y[i].length; j++)
        	{
        		bg_y[i][j] += length;
        	}
        }
        if (bg_y[0][0] >= 0 || bg_y[0][2] <= 0)
        {
            Game.control.shiftY(dir);
        }
        repaint();
    }
}
