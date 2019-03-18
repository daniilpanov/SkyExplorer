package com.newlightstudio.skyexplorer;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class Sprite
{
    // Координаты спрайта
    public int x, y;
    // Картинки спрайта
    private Image sprite_r,
            sprite_l;
    // Текущая картинка спрайта
    public Image sprite;
    // На чём рисуется спрайт
    public Component parent;
    // Направление спрайта (1 - право, -1 - лево)
    public int direction = 1;
    // Разгон
    private int current_speed_x = 0;
    
    // 
    public static final int X = 1, Y = 2;
    
    private boolean reset_speed;
    
    private AreaBorder border = null;
    private LiveBackground background = null;
    
    // МЕТОДЫ ДЛЯ ЭЛЕМЕНТАРНЫХ ДЕЙСТВИЙ
    
    // Конструктор:
    public Sprite(int x, int y, Image sprite_r, Image sprite_l, Component parent, boolean reset_speed)
    {
        // Задаём значение свойствам
        this.sprite_r = sprite_r;
        this.sprite_l = sprite_l;
        this.sprite = sprite_l;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.reset_speed = reset_speed;
    }
    
    // Устанавливаем границы, за которые спрайт не может выходить (просто setter)
    public void setBorders()
    {
    	border = new AreaBorder(parent);
    }
    //
    public void setArea(LiveBackground bg)
    {
        background = bg;
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
    public void update(int x, int y, int dir, boolean repaint)
    {
        // обновляем координаты
        this.x = x;
        this.y = y;
        // и если направление или ось поменялось, то
        if (dir != direction)
        {
            // записываем направление
            this.direction = dir;
            // и обновляем картинку
            updateCurrentSprite((dir < 0) ? sprite_l : sprite_r);
        }
        // и если надо - перерисовываем
        else if (repaint)
        {
            parent.repaint();
        }
    }
    // с перерисовкой по-умолчанию:
    public void update(int x, int y, int dir)
    {
        this.update(x, y, dir, true);
    }
    // только для движений вправо-влево
    public void moveX(int dir, int l)
    {
        if (border != null)
        {
            if (border.checkMovingOpportunity(x, y, l, dir, X))
            {
                background.updateBgX(l, dir);
            }
            else
            {
                this.update(x + l * dir, y, dir);
            }
        }
        else
        {
            this.update(x + l * dir, y, dir);
        }
    }
    
    // ОЧЕНЬ СЛОЖНЫЕ МЕТОДЫ

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
