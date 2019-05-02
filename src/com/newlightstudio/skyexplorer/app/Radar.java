package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.app.sprite.Sprite;
import org.intellij.lang.annotations.MagicConstant;

import javax.swing.JLabel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class Radar extends Sprite
{
    public static final String ENEMY_OBJ = "Enemy",
            FINISH_OBJ = "Finish", PLANET = "Planet";
    
    private ArrayList<Obj> objects = new ArrayList<>();
    private JLabel view;
    
    Radar(int x, int y, Component parent)
    {
        super(x, y, 0, 0, null, parent, true);
        
        view = new JLabel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                
                g.setColor(Color.GREEN);
    
                for (int size = 150, pos = 0; size > 0 && pos < 150; size -= 5, pos += 5)
                {
                    g.drawOval(pos, pos, size, size);
                }
                
                displayObjects((Graphics2D) g);
            }
        };
        
        view.setBackground(Color.BLACK);
    }
    
    void addObj(Obj[] objects)
    {
        this.objects.addAll(Arrays.asList(objects));
    }
    
    Obj[] getObjects()
    {
        return objects.toArray(new Obj[0]);
    }
    
    private void displayObjects(Graphics2D g)
    {
        for (Obj object : getObjects())
        {
            // TODO: 30.04.2019 do the drawing all objects & circles on radar
        }
    }
    
    private void drawEnemy(Graphics2D g)
    {
    
    }
    
    void update()
    {
        view.repaint();
    }
    
    class Obj
    {
        private String type;
        private double x, y;
        
        Obj(Sprite real_obj,
            @MagicConstant(stringValues = {ENEMY_OBJ, FINISH_OBJ, PLANET})
                    String type
        )
        {
            x = real_obj.getX();
            y = real_obj.getY();
            this.type = type;
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
}
