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
    // Направление спрайта (1 - право, -1 - лево)
    public int direction = 1, axis = X;
    
    // Движения спрайта
    private Timer moving;
    // Ключ массива картинок и текущая скорость (первый арг. конструктора таймера)
    private int i = 0, current_s = 0,
            // Разгон
            current_speed_x = 0, current_speed_y = 0;
    
    private int[][] border_points = new int[4][2];
    
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
    
    // Устанавливаем границы, за которые спрайт не может выходить (просто setter)
    public void setBorders(int min_x, int min_y, int max_x, int max_y)
    {
    	border_points[0][0] = min_x; border_points[0][1] = min_y;
    	border_points[1][0] = min_x; border_points[1][1] = max_y;
    	border_points[2][0] = max_x; border_points[2][1] = min_y;
    	border_points[3][0] = max_x; border_points[3][0] = max_y;
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
    public void update(int x, int y, int dir, int axis, boolean repaint)
    {
        // обновляем координаты
        this.x = x;
        this.y = y;
        // и если направление или ось поменялось, то
        if (dir != direction || axis != this.axis)
        {
            // записываем направление
            this.direction = dir;
            this.axis = axis;
            // и обновляем картинку
            updateCurrentSprite((dir < 0) ? ((axis == X) ? sprite_l : sprite_t) : ((axis == X) ? sprite_r : sprite_b));
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
    public void moveX(int dir, int l)
    {
        this.update(x + l * dir, y, dir, X);
    }
    // только для движений вверх-вниз
    public void moveY(int dir, int l)
    {
        this.update(x, y + l * dir, dir, Y);
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
    public void moving(Image[] images, int start_time, int end_time, int increment_time, int dir, int os, int speed)
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
        if (current_speed_x == 0)
        {
            current_speed_x = min;
        }
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
        if (current_speed_y == 0)
        {
            current_speed_y = min;
        }
        //
        moveY(dir, current_speed_y);
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
