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
        super(x, y, -1, -1, Img.starship.getImage(), picture_view, false);
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
    protected boolean keyControl(KeyEvent e)
    {
        boolean pressed = false;
        
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
                //
                pressed = true;
                break;
                
            case VK_DOWN:
                //
                dir_y = 1;
                //
                movePictureView();
                //
                pressed = true;
                break;
            
            case VK_LEFT:
                //
                dir_x = -1;
                //
                movePictureView();
                //
                pressed = true;
                break;
                
            case VK_RIGHT:
                //
                dir_x = 1;
                //
                movePictureView();
                //
                pressed = true;
                break;
        }
        
        return pressed;
    }
    
    @Override
    protected void mouseControl(MouseEvent e)
    {
        int x = e.getX(),
                y = e.getY();
        
        double[] cathetus_and_dir = calculateCathetus(x, y);
        
        rotate(
                cathetus_and_dir[0] * cathetus_and_dir[2],
                cathetus_and_dir[1] * cathetus_and_dir[3]
        );
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        g.rotate(getRotation());
        
        g.drawImage(getImg(), getX(), getY(), null);
    }
    
    @Override
    public void move(int x, int y)
    {
        int old_x = this.x,
                old_y = this.y,
                width = x * dir_x,
                height = y * dir_y ;
    
        this.x += width;
        this.y += height;
        
        picture_view.repaint();
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
