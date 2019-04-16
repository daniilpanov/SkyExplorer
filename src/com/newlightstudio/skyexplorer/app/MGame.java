package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.Main;
import com.newlightstudio.skyexplorer.app.sprite.CSprite;

import javax.swing.JFrame;

import static com.newlightstudio.skyexplorer.app.Img.*;

class MGame extends JFrame
{
    int scores;
    CSprite[][] living_bg = new CSprite[3][3];
    CStarShip hero;
    
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
                living_bg[i][j] = new CSprite(x, y, bg_width, bg_height, bg, true);
            }
        }
        
        //
        //
        int
                hero_w = starship_l.getWidth(null),
                hero_h = starship_l.getHeight(null),
                x = (int) ((Main.screen_size.getWidth() - hero_w) / 2),
                y = (int) ((Main.screen_size.getHeight() - hero_h) / 2);
        //
        hero = new CStarShip(x, y);
    }
}
