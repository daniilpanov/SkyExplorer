package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.app.sprite.Sprite;
import org.intellij.lang.annotations.MagicConstant;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class Radar extends Sprite
{
    static final String ENEMY_OBJ = "Enemy",
            FINISH_OBJ = "Finish";
    
    private ArrayList<Obj> objects = new ArrayList<>();
    
    Radar(int x, int y)
    {
        super(x, y, 0, 0, null, true);
    }
    
    void addObj(Obj[] objects)
    {
        this.objects.addAll(Arrays.asList(objects));
    }
    
    Obj[] getObjects()
    {
        return objects.toArray(new Obj[0]);
    }
    
    void displayObjects(Graphics2D g)
    {
        for (Obj object : getObjects())
        {
            // TODO: 30.04.2019 do the drawing all objects & circles on radar
        }
    }
    
    class Obj
    {
        private String type;
        private int x, y, width, height;
        private Image image_of_obj;
        
        Obj(Sprite real_obj,
            @MagicConstant(stringValues = {ENEMY_OBJ, FINISH_OBJ})
                    String type
        )
        {
            x = real_obj.getX();
            y = real_obj.getY();
            this.type = type;
        }
    }
}
