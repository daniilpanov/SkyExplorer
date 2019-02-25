package com.originalstudio.skyexplorer;

import org.omg.CORBA.PUBLIC_MEMBER;

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
    // Ключ массива картинок и текущая скорость (первый арг. конструктора таймера)
    private int i = 0, current_s = 0,
            // Разгон
            current_speed_x = 0, current_speed_y = 0;
    
    // 
    public static final int X = 1, Y = 2;
    
    private boolean reset_speed;
    
    // МЕТОДЫ ДЛЯ ЭЛЕМЕНТАРНЫХ ДЕЙСТВИЙ
    
    // Конструктор:
    public Sprite(int x, int y, Image sprite_r, Image sprite_l, Image sprite_t, Image sprite_b, Component parent, boolean reset_speed)
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
        this.reset_speed = reset_speed;
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
    
    // БОЛЕЕ СЛОЖНЫЕ МЕТОДЫ
    
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
    
    // ОЧЕНЬ СЛОЖНЫЕ МЕТОДЫ
    
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
    
    /**
     * Перемещение с эффектом нарастающей скорости
     * (
     *   images - массив с картинками для обновления после каждой итерации
     *   start_time - начальное время, end_time - конечное время (минимальная и конечная скорость)
     *   increment_time - на сколько будет увеличиваться скорость после каждой итерации таймера
     *   dir - направление (отрицательное/положительное), os - ось (X/Y)
     *   speed - скорость (количество пикселей, на которое будет сдвигаться спрайт после каждой итерации таймера)
     * )
     */
    public void moving(Image[] images, int start_time, int end_time, int increment_time, int dir, int os, int speed, Runnable stop)
    {
        // инициализируем текушую скорость
        // (скоростью здесь называется интервал таймера)
        current_s = start_time;
        
        // Инициализируем таймер
        movingInit(e ->
            {
                // обновляем интервал таймера
                moving.setDelay(current_s);
                // обновляем картинку спрайта
                updateCurrentSprite(images[i]);
                // если картинки не закончились, то
                if (i < images.length-1)
                {
                    // увеличиваем ключ массива с картинками на 1
                    i++;
                }
                // если мы двигаемся по оси X, то
                if (os == X)
                {
                    // вызываем метод для перемещения по X
                    moveX(dir, speed);
                }
                // а если по Y -
                else if (os == Y)
                {
                    // метод для перемещения по Y
                    moveY(dir, speed);
                }
                // увеличиваем скорость на заданную величину
                current_s -= increment_time;
                // если скорость больше максимальной, то
                if (current_s < end_time)
                {
                    // просто делаем скорость максимальной, т.е. уменьшаем
                    current_s = end_time;
                }
                // запускаем условие (если оно есть) (если оно истина - завершаем работу)
                stop.run();
            },
            // текущая скорость
            current_s);
    }
    
    // Полная остановка таймера:
    public void stop()
    {
        // останавливаем таймер,
        moving.stop();
        // удаляем его,
        moving = null;
        // изменяем ключ массива с картинками на 0
        i = 0;
        // и обновляем текущую скорость
        current_s = 0;
        // а также заменяем картинку спрайта на первоначальную
        updateCurrentSprite(sprite_r);
    }
    
    //
    public void realMoveX(int increment, int min, int max, int dir)
    {
        //
        current_speed_x = min;
        //
        moveX(dir, current_speed_x);
        //
        current_speed_x += increment;
        //
        if (current_speed_x > max)
        {
            current_speed_x = max;
        }
    }
    
    //
    public void realMoveY(int increment, int min, int max, int dir)
    {
        //
        current_speed_y = min;
        //
        moveX(dir, current_speed_y);
        //
        current_speed_y += increment;
        //
        if (current_speed_y > max)
        {
            current_speed_y = max;
        }
    }
    
    // ADDITIONALLY
    
    // Метод для установки слушателя клавиатуры:
    public void addControl(Component parent)
    {
        KeyAdapter listener = (reset_speed)
                ? new KeyAdapter() {
            /**
             * Invoked when a key has been pressed.
             *
             * @param e {@link KeyEvent}
             */
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                // и вызываем метод проверки
                checkingKey(e);
            }
    
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                // сбрасываем текущую скорость
                current_speed_x = 0;
                current_speed_y = 0;
            }
        }
                : new KeyAdapter() {
            /**
             * Invoked when a key has been pressed.
             *
             * @param e {@link KeyEvent}
             */
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                // и вызываем метод проверки
                checkingKey(e);
            }
        };
        
        // добавляем слушатель
        parent.addKeyListener(listener);
    }
    // Метод для проверки нажатых клавиш
    protected abstract void checkingKey(KeyEvent e);
}
