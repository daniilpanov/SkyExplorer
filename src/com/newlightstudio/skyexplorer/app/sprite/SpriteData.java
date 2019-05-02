package com.newlightstudio.skyexplorer.app.sprite;

import java.awt.Image;

class SpriteData
{
    protected int
            // Размеры
            width, height,
            // Направление
            dir_x, dir_y;
    protected double
            // Координаты
            x, y,
            // Those variables are increments of speed to make moving more realistic
            add_increment_x = 0, add_increment_y = 0,
            // Для дивжений во все стороны
            increment_x, increment_y;
    // Изображение спрайта
    protected Image img;
    // Является ли спрайт декорацией (возможность столкновения)
    boolean decoration;
    // Поворот изображения спрайта
    double rotation;
    
    public boolean isDecoration()
    {
        return decoration;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getDirX()
    {
        return dir_x;
    }
    
    public int getDirY()
    {
        return dir_y;
    }
    
    public Image getImg()
    {
        return img;
    }
    
    public double getRotation()
    {
        return rotation;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
}
