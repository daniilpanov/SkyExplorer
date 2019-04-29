package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.Main;
import com.newlightstudio.skyexplorer.app.sprite.Sprite;

import javax.swing.JFrame;

import static com.newlightstudio.skyexplorer.app.Img.*;

class MGame extends JFrame
{
    int scores;
    Sprite[][] living_bg = new Sprite[3][3];
    StarShip player;
    
    MGame()
    {
        //
        scores = 0;
        
        //
        //
        int bg_width = bg.getWidth(null),
                bg_height = bg.getHeight(null);
        //
        for (int i = 0, y = -bg_height; i < living_bg.length; i++, y += bg_height)
        {
            for (int j = 0, x = -bg_width; j < living_bg[i].length; j++, x += bg_width)
            {
                living_bg[i][j] = new Sprite(x, y, bg_width, bg_height, bg, true);
            }
        }
    }
}
