package com.newlightstudio.skyexplorer.app.sprite;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public abstract class CControlSprite extends CSprite
{
    public CControlSprite(int x, int y, int width, int height, int dir_x, int dir_y,
                          Image img, boolean decoration
    )
    {
        super(x, y, width, height, dir_x, dir_y, img, decoration);
    }
    
    public CControlSprite(int x, int y, int dir_x, int dir_y,
                   Image img, boolean decoration
    )
    {
        super(x, y, img.getWidth(null), img.getHeight(null),
                dir_x, dir_y, img, decoration
        );
    }
    
    /*
     *
     */
    public void addKeyControl(Component parent, boolean slower_down)
    {
        KeyAdapter keyAdapter = (slower_down)
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
                        keyControl(e);
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
                        
                        slowStopping();
                    }
                }
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
                         keyControl(e);
                     }
                 };
                 
        parent.addKeyListener(keyAdapter);
    }
    
    protected abstract void keyControl(KeyEvent e);
    
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
    
    protected abstract void mouseControl(MouseEvent e);
}
