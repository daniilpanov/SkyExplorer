package com.newlightstudio.skyexplorer.app.sprite;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public abstract class ControlSprite extends Sprite
{
    public ControlSprite(int x, int y, int width, int height, int dir_x, int dir_y,
                         Image img, Component parent, boolean decoration
    )
    {
        super(x, y, width, height, dir_x, dir_y, img, parent, decoration);
    }
    
    public ControlSprite(int x, int y, int dir_x, int dir_y,
                         Image img, Component parent, boolean decoration
    )
    {
        super(x, y, img.getWidth(null), img.getHeight(null),
                dir_x, dir_y, img, parent, decoration
        );
    }
    
    protected abstract boolean keyControl(KeyEvent e);
    
    protected abstract void mouseControl(MouseEvent e);
    
    /*
     *
     */
    public void addKeyControl(Component parent, boolean slow_stop)
    {
        // Слушатель клавиатуры:
        KeyAdapter keyAdapter =
            // Если нужна медленная остановка, то
            (slow_stop)
            // нам нужен такой слушатель
            ? new KeyAdapter()
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
                    // Осуществляем контроль с помощью клавиш
                    if (keyControl(e))
                    {
                        // если нажата одна из нужных клавиш,
                        // останавливаем остановку и продолжаем движение
                        setMayStopping(false);
                    }
                }
                
                /**
                 * Invoked when a key has been released.
                 *
                 * @param e {@link KeyEvent}
                 */
                @Override
                public void keyReleased(KeyEvent e)
                {
                    super.keyReleased(e);
                    //
                    setMayStopping(true);
                    // При поднятии клавиши
                    // будет произведена медленная остановка
                    slowStopping();
                }
            }
            // А если же медленная остановка не требуется, то
            : new KeyAdapter()
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
                    // просто осуществляем контроль спрайта клавишами
                    keyControl(e);
                }
            };
        // Добавляем полученный слушатель
        parent.addKeyListener(keyAdapter);
    }
    
    /*
     *
    */
    public void addMouseControl(Component parent)
    {
        MouseMotionAdapter mouseAdapter = new MouseMotionAdapter()
        {
            /**
             * Invoked when the mouse button has been moved on a component
             * (with no buttons no down).
             *
             * @param e {@link MouseEvent}
             */
            @Override
            public void mouseMoved(MouseEvent e)
            {
                super.mouseMoved(e);
                
                mouseControl(e);
            }
        };
        
        parent.addMouseMotionListener(mouseAdapter);
    }
}
