package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.app.sprite.ControlSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static java.awt.event.KeyEvent.*;

public class StarShip extends ControlSprite
{
    private JLabel picture_view;
    private Timer shooting;
    private Icon sprite_icon;
    
    StarShip(int x, int y, JLabel picture_view)
    {
        super(x, y, -1, -1, Img.starship.getImage(), false);
        this.picture_view = picture_view;
        this.sprite_icon = Img.starship;
    
        picture_view.setIcon(getSpriteIcon());
        picture_view.setSize(
                getWidth(),
                getHeight()
        );
        picture_view.setLocation(x, y);
    }
    
    @Override
    protected void keyControl(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case VK_ENTER:
                //
                if (shoot())
                {
                
                }
                break;
            
            case VK_UP:
                //
                dir_y = -1;
                
                //
                movePictureView();
                break;
                
            case VK_DOWN:
                //
                dir_y = 1;
                
                //
                movePictureView();
                break;
            
            case VK_LEFT:
                //
                dir_x = -1;
                
                //
                movePictureView();
                break;
                
            case VK_RIGHT:
                //
                dir_x = 1;
                
                //
                movePictureView();
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
    
    @Override
    public void draw(Graphics2D g)
    {
    }
    
    private void movePictureView()
    {
        picture_view.setLocation(x, y);
    }
    
    private boolean shoot()
    {
        boolean res = false;
        
        return res;
    }
    
    public Icon getSpriteIcon()
    {
        return sprite_icon;
    }
}
