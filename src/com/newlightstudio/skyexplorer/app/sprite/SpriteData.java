package com.newlightstudio.skyexplorer.app.sprite;

import java.awt.Image;

class SpriteData
{
    protected int // Координаты
            x, y,
            // Размеры
            width, height,
            // Направление
            dir_x, dir_y,
            // This variable is increment of speed to make this more realistic
            increment = 1,
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
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getDir_x()
    {
        return dir_x;
    }
    
    public int getDir_y()
    {
        return dir_y;
    }
    
    public Image getImg()
    {
        return img;
    }
}
