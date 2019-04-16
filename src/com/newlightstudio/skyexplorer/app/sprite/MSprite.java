package com.newlightstudio.skyexplorer.app.sprite;

import java.awt.Image;

class MSprite
{
    int // Координаты
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
    Image img;
    // Является ли спрайт декорацией (возможность столкновения)
    boolean decoration;
    // Поворот изображения спрайта
    double rotation;
    
    MSprite(int x, int y, int width, int height, int dir_x, int dir_y,
            Image img, boolean decoration
    )
    {
        // Координаты
        this.x = x;
        this.y = y;
        // Размеры
        this.width = width;
        this.height = height;
        // Направления (по OX и OY)
        this.dir_x = dir_x;
        this.dir_y = dir_y;
        // Картинка
        this.img = img;
        // Является ли спрайт декорацией (возможность столкновения)
        this.decoration = decoration;
        // Поворот изображения
        this.rotation = 0;
    }
    
    MSprite(int x, int y, int dir_x, int dir_y,
            Image img, boolean decoration
    )
    {
        this(x, y, img.getWidth(null), img.getHeight(null),
                dir_x, dir_y, img, decoration
        );
    }
    
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
