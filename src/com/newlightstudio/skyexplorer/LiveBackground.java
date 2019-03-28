package com.newlightstudio.skyexplorer;

import javax.swing.*;

import static com.newlightstudio.skyexplorer.Img.bg;

class LiveBackground extends JPanel
{
    static int bg_w = bg.getWidth(null);
    
    int[] bg_x =
	            {
                        0,
                        0,
                        0
                };
    
    void updateBgX(int length, int dir)
    {
        length *= -dir;

        Game.control.enemy.moveX(dir, -length);

        for (int i = 0; i < bg_x.length; i++)
        {
            bg_x[i] += length;
        }
        if (bg_x[0] + bg_w >= 0 || bg_x[2] <= 0)
        {
        	Game.control.shiftX();
        }
        repaint();
    }
}
