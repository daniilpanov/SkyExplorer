package com.newlightstudio.skyexplorer.app.sprite;

import javax.swing.*;
import java.awt.*;

public class Sprite extends SpriteData
{
    private Timer moving;
    private Component parent;
    private boolean may_stopping = true;
    
    public Sprite(int x, int y, int width, int height, int dir_x, int dir_y,
                  Image img, Component parent, boolean decoration
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
        // На чём будет рисоваться спрайт
        this.parent = parent;
    }
    
    public Sprite(int x, int y, int dir_x, int dir_y,
                  Image img, Component parent, boolean decoration
    )
    {
        this(x, y, img.getWidth(null), img.getHeight(null),
                dir_x, dir_y, img, parent, decoration
        );
    }
    
    public void draw(Graphics2D g)
    {
        g.drawImage(img, x, y, null);
    }
    
    public void setLocation(int x, int y)
    {
        this.x = x; this.y = y;
        parent.repaint();
    }
    
    public void setMayStopping(boolean may_stopping)
    {
        this.may_stopping = may_stopping;
    }
    
    public void move(int x, int y, int dir_x, int dir_y)
    {
        this.dir_x = dir_x;
        this.dir_y = dir_y;
        
        move(x, y);
    }
    
    public void move(int x, int y)
    {
        int old_x = this.x,
                old_y = this.y,
                width = x * dir_x,
                height = y * dir_y ;
        
        this.x += width;
        this.y += height;
        
        parent.repaint(old_x, old_y, width + getWidth(), height + getHeight());
    }
    
    /*
     *
    */
    public void slowMove(int w, int h, int dir_x, int dir_y)
    {
    
    }
    
    /*
     *
    */
    protected void slowStopping()
    {
        if (moving == null)
        {
            moving = new Timer(500, e ->
            {
                if (add_increment_x < 0 || add_increment_y < 0
                        || !may_stopping
                )
                {
                    add_increment_x = add_increment_y = 0;
        
                    moving.stop();
                    moving = null;
                }
                else
                {
                    double decrement_x = add_increment_x / 5,
                            decrement_y = add_increment_y / 5;
        
                    add_increment_x -= decrement_x;
                    add_increment_y -= decrement_y;
        
                    move(
                            (int) (increment_x + add_increment_x),
                            (int) (increment_y + add_increment_y)
                    );
                }
            });
            
            moving.start();
        }
        
    }
    
    /*
     * ТУТ НАДО ПРЕДСТАВИТЬ ПРЯМОУГОЛЬНЫЙ ТРЕУГОЛЬНИК OWH
     * С ПРЯМЫМ УГЛОМ O
     * И СТОРОНАМИ WO (width) И HO (height)
     * ***
     *
    */
    public void rotate(double width, double height)
    {
        rotation = Math.atan2(width, height);
    }
    
    /*
     * Считает длину катетов
     * Треугольник: от координатов мыши до координатов спрайта
     * 1-й катет - расстояние по X (WO)
     * 2-й катет - расстояние по Y (HO)
    */
    public double[] calculateCathetus(int mouse_x, int mouse_y)
    {
        // Находим длину и высоту
        double width = Math.abs(Math.abs(mouse_x) - Math.abs(x)),
                height = Math.abs(Math.abs(mouse_y) - Math.abs(y));
        // и направление
        int dir_x = this.dir_x,
                dir_y = this.dir_y;
        
        // Если координаты мыши меньше,
        if (mouse_x < x)
        {
            // придаём отрицательное значение
            // направлению по OX
            dir_x = -1;
        }
        // А если они больше,
        else if (mouse_x > x)
        {
            // направление становится положительным
            dir_x = 1;
        }
        
        // То же самое с OY:
        if (mouse_y < y)
        {
            dir_y = -1;
        }
        else if (mouse_y > y)
        {
            dir_y = 1;
        }
        
        return new double[]{width, height, dir_x, dir_y};
    }
    
    /*
     * Высчитывает, сколько нужно прибавить по OY при увеличении OX на 1
    */
    public double calculateFlightPath(double width, double height)
    {
        return height / width;
    }
    
    public boolean mayStopping()
    {
        return may_stopping;
    }
}
