package com.newlightstudio.skyexplorer.app.sprite;

import java.awt.Graphics2D;
import java.awt.Image;

public class CSprite extends MSprite
{
    public CSprite(int x, int y, int width, int height, int dir_x, int dir_y,
                   Image img, boolean decoration
    )
    {
        super(x, y, width, height, dir_x, dir_y, img, decoration);
    }
    
    public CSprite(int x, int y, int dir_x, int dir_y,
                   Image img, boolean decoration
    )
    {
        super(x, y, img.getWidth(null), img.getHeight(null),
                dir_x, dir_y, img, decoration
        );
    }
    
    public void render(Graphics2D g)
    {
        g.drawImage(img, x, y, null);
    }
    
    public void setLocation(int x, int y)
    {
        this.x = x; this.y = y;
    }
    
    public void move(int x, int y, int dir_x, int dir_y)
    {
        this.dir_x = dir_x;
        this.dir_y = dir_y;
        
        move(x, y);
    }
    
    public void move(int x, int y)
    {
        this.x += x * dir_x;
        this.y += y * dir_y;
    }
    
    public void slowStopping()
    {
    
    }
    
    /*
     * ТУТ НАДО ПРЕДСТАВИТЬ ПРЯМОУГОЛЬНЫЙ ТРЕУГОЛЬНИК OWH
     * С ПРЯМЫМ УГЛОМ O
     * И СТОРОНАМИ WO (width) И HO (height)
     */
    
    public void rotate(Graphics2D g, double width, double height)
    {
        double theta = Math.atan2(width, height);
        
        g.rotate(theta);
    }
    
    public double[] calculateCathetus(int mouse_x, int mouse_y)
    {
        double width = Math.abs(Math.abs(mouse_x) - Math.abs(x)),
                height = Math.abs(Math.abs(mouse_y) - Math.abs(y));
        int dir_x = 1, dir_y = 1;
        
        if (mouse_x < x)
        {
            dir_x *= -1;
        }
        else if (mouse_x == x)
        {
            dir_x = this.dir_x;
        }
    
        if (mouse_y < y)
        {
            dir_y *= -1;
        }
        else if (mouse_y == y)
        {
            dir_y = this.dir_y;
        }
        
        return new double[]{width, height, dir_x, dir_y};
    }
    
    public double calculateFlightPath(double width, double height)
    {
        return height / width;
    }
}
