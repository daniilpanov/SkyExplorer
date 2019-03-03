package com.originalstudio.skyexplorer;

import javax.swing.*;

import static com.originalstudio.skyexplorer.Img.bg;

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
        System.out.println("OK!!! Moving bg (x)");
    }
    
    @Override
    public void updateBgY(int length, int dir)
    {
        System.out.println("OK!!! Moving bg (y)");
    }
}
