package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.app.sprite.CControlSprite;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static java.awt.event.KeyEvent.*;

public class CStarShip extends CControlSprite
{
    CStarShip(int x, int y)
    {
        super(x, y, -1, -1, Img.starship_l, false);
    }
    
    @Override
    protected void keyControl(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case VK_ENTER:
                
                break;
            
            case VK_UP:
                
                break;
                
            case VK_DOWN:
                
                break;
            
            case VK_LEFT:
                
                break;
                
            case VK_RIGHT:
                
                break;
        }
    }
    
    @Override
    protected void mouseControl(MouseEvent e)
    {
        int x = e.getX(),
                y = e.getY();
        
        double[] cathetus_and_dir = calculateCathetus(x, y);
        
        
    }
}
