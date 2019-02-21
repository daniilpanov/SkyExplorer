package com.originalstudio.skyexplorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class Sprite
{
    // Координаты спрайта
    public int x, y;
    // Картинки спрайта
    private Image sprite_r,
            sprite_l,
            sprite_t,
            sprite_b;
    // Текущая картинка спрайта
    public Image sprite;
    // На чём рисуется спрайт
    public Component parent;
    // Направление спрайта (1 - право, -1 - лево, 0 - исходная)
    public int direction_x = 1, // X
            direction_y = 0;
    
    // Движения спрайта
    private Timer moving;
    private int i = 0, current_speed = 0;
    
    // 
    public static final int X = 1, Y = 2;
    
    // Конструктор:
    public Sprite(int x, int y, Image sprite_r, Image sprite_l, Image sprite_t, Image sprite_b, Component parent)
    {
        // Задаём значение свойствам
        this.sprite_r = sprite_r;
        this.sprite_l = sprite_l;
        this.sprite_t = sprite_t;
        this.sprite_b = sprite_b;
        this.sprite = sprite_r;
        this.x = x;
        this.y = y;
        this.parent = parent;
    }
    
    // Рисование спрайта:
    public void render(Graphics g)
    {
        // если есть картинка,
        if (sprite != null) {
            // рисуем спрайт
            g.drawImage(sprite, x, y, null);
        }
    }
    
    // Обновление координат и направления спрайта
    // с флагом - надо ли перерисовывать или нет:
    public void update(int x, int y, int dir_x, int dir_y, boolean repaint)
    {
        // обновляем координаты
        this.x = x;
        this.y = y;
        // и если направление поменялось, то
        if (dir_x != direction_x) // (по X)
        {
            // записываем направление
            this.direction_x = dir_x;
            // и обновляем картинку
            updateCurrentSprite((dir_x > 0) ? sprite_r : sprite_l);
        }
        //
        else if (dir_y != direction_y) // (по Y)
        {
            // записываем направление
            this.direction_y = dir_y;
            // и обновляем картинку
            updateCurrentSprite((dir_y > 0) ? sprite_t : sprite_b);
        }
        // и если надо - перерисовываем
        else if (repaint)
        {
            parent.repaint();
        }
    }
    // с перерисовкой по-умолчанию:
    public void update(int x, int y, int dir_x, int dir_y)
    {
        this.update(x, y, dir_x, dir_y, true);
    }
    // только для движений вправо-влево
    public void moveX(int d, int l)
    {
        this.update(x + l * d, y, d, direction_y);
    }
    // только для движений вверх-вниз
    public void moveY(int d, int l)
    {
        this.update(x, y + l * d, direction_x, d);
    }
    
    // Смена картинки спрайта:
    public void updateSprite(Image sprite, Image sprite_r, Image sprite_l)
    {
        // задаём новые картинки
        this.sprite = sprite;
        this.sprite_r = sprite_r;
        this.sprite_l = sprite_l;
        // и перерисовываем
        parent.repaint();
    }
    // Смена <u>текущей</u> картинки спрайта:
    public void updateCurrentSprite(Image sprite)
    {
        // задаём новую картинку
        this.sprite = sprite;
        // и перерисовываем
        parent.repaint();
    }
    
    // Удаление изображения спрайта
    public void delete()
    {
        // обновляем картинку (задаём нулевое значение)
        updateSprite(null, null, null);
    }
    
    // Получение ширины и высоты спрайта
    public int getWidth()
    {
        return sprite.getWidth(null);
    }
    
    public int getHeight()
    {
        return sprite.getHeight(null);
    }
    
    // Инициализация таймера:
    private void movingInit(ActionListener e, int time)
    {
        // если таймер не работает, то создаём новый
        if (moving == null)
        {
            moving = new Timer(time, e);
            moving.start();
        }
    }
    // TODO: 19.02.2019 create new branch "hotfix-sprite_moving_in_timer"
    // Смена картинок спрайта (например, когда спрайт погибает):
    public void positions(Image[] images, int time)
    {
        movingInit(e ->
        {
            // обновляем картинку спрайта
            updateCurrentSprite(images[i]);
            i++; // увеличиваем ключ массива с картинками
            // если картинки закончились, то делаем стоп
            if (i >= images.length)
            {
                stop();
            }
        }, time);
    }
    
    // 
    public void moving(Image[] images, int time, int iter, int dir, int os, int min, int max, int incr)
    {
        // инициализируем текушую скорость
        current_speed = min;
        
        movingInit(e ->
        {
            //
            updateCurrentSprite(images[i]);
            i++; //
            //
            if (os == X)
            {
                moveX(dir, current_speed);
            }
            //
            else if (os == Y)
            {
                moveY(dir, current_speed);
            }
            // увеличиваем скорость на заданную величину
            current_speed += incr;
            // если скорость больше максимальной, то
            if (current_speed > max)
            {
                // просто делаем скорость максимальной, т.е. уменьшаем
                current_speed = max;
            }
            
            //
            if (i >= images.length || i >= iter)
            {
                stop();
            }
            
        }, time);
    }
    
    // TODO 19.02.2019 create new method calls "moving" for realistic speed (low and then fasten)
    // Полная остановка таймера:
    private void stop()
    {
        // останавливаем таймер,
        moving.stop();
        // удаляем его,
        moving = null;
        // изменяем ключ массива с картинками на 0
        i = 0;
        // и обновляем текущую скорость
        current_speed = 0;
    }
    
    // Метод для установки слушателя клавиатуры:
    public void addControl(Component parent)
    {
        // добавляем слушатель
        parent.addKeyListener(new KeyAdapter()
        {
            /**
             * Invoked when a key has been pressed.
             *
             * @param e {@link KeyEvent}
             */
            @Override
            public void keyPressed(KeyEvent e)
            {
                super.keyPressed(e);
                // и вызываем метод проверки
                checkingKey(e);
            }
        });
    }
    // Метод для проверки нажатых клавиш
    protected abstract void checkingKey(KeyEvent e);
}
