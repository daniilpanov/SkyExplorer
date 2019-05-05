package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.app.sprite.ControlSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

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
        boolean pressed = false,
                speed_up = false;
        int new_dir_x = dir_x,
                new_dir_y = dir_y;
        
        switch (e.getKeyCode())
        {
            case VK_ENTER:
                //
                
                break;
            
            case VK_UP:
                // Осуществляем ускорение:
                // Если лимит ускорения не превышен,
                if ((add_increment_x + add_increment_y) / 2 < 50)
                {
                    // с помощью этих малопонятных (и мне самому тоже) формул,
                    // увеличиваем разгон
                    add_increment_x += increment_x * 2 + add_increment_x / 2; // по OX
                    add_increment_y += increment_y * 2 + add_increment_y / 2; // по OY
                }
                
                //
                pressed = true;
                //
                speed_up = true;
                break;
                
            case VK_DOWN:
                //
                new_dir_x *= -1;
                new_dir_y *= -1;
                //
                add_increment_x = add_increment_y = 0;
                //
                pressed = true;
                break;
            
            case VK_LEFT:
                //
                new_dir_x *= -1;
                //
                add_increment_x = add_increment_y = 0;
                //
                pressed = true;
                break;
                
            case VK_RIGHT:
                //
                new_dir_y *= -1;
                //
                add_increment_x = add_increment_y = 0;
                //
                pressed = true;
                break;
        }
        
        //
        if (pressed)
        {
            //
            if (speed_up)
            {
                //
                movePictureView(
                        increment_x + add_increment_x,
                        increment_y + add_increment_y,
                        new_dir_x, new_dir_y
                );
            }
            // Иначе
            else
            {
                // просто передвигаем спрайт
                movePictureView(increment_x * 10,
                        increment_y * 10,
                        new_dir_x, new_dir_y
                );
            }
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
        transform.rotate(getRotation(), getWidth() >> 1, getHeight() >> 1);
        
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
    
    private void movePictureView(double len, double height, int dir_x, int dir_y)
    {
        this.move(len, height, dir_x, dir_y);
        
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
