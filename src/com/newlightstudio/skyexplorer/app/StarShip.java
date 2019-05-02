package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.app.sprite.ControlSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

import static java.awt.event.KeyEvent.*;

public class StarShip extends ControlSprite
{
    private AffineTransform transform = new AffineTransform();
    private JLabel picture_view;
    private Timer shooting;
    private Icon sprite_icon;
    
    public StarShip(int x, int y, JLabel picture_view)
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
        /*if (shoot())
                {
                
                }*/
        switch (e.getKeyCode())
        {
            case VK_ENTER:
                //
                
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
                cathetus_and_dir[0] * cathetus_and_dir[2] * (-1),
                cathetus_and_dir[1] * cathetus_and_dir[3] * (-1)
        );
        
        increment_x = 1;
        increment_y = calculateHeightAtOneLength(cathetus_and_dir[0], cathetus_and_dir[1]);
    
        picture_view.repaint();
    }
    
    @Override
    public void draw(Graphics2D g)
    {
        transform = new AffineTransform();
        transform.rotate(getRotation(), getWidth() / 2, getHeight() / 2);
        /*try
        {
            transform.invert();
        }
        catch (NoninvertibleTransformException e)
        {
            e.printStackTrace();
        }*/
        g.setTransform(transform);
        
        g.drawImage(getImg(), 0, 0, null);
    }
    
    @Override
    public void move(double x, double y)
    {
        double width = x * dir_x,
                height = y * dir_y;
    
        this.x += width;
        this.y += height;
        
        picture_view.repaint();
    }
    
    private void movePictureView()
    {
        picture_view.setLocation((int) x, (int) y);
    }
    
    /*private boolean shoot()
    {
        boolean res = false;
        
        return res;
    }*/
    
    public Icon getSpriteIcon()
    {
        return sprite_icon;
    }
}
