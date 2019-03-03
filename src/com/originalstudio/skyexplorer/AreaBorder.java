package com.originalstudio.skyexplorer;

import java.awt.*;

public class AreaBorder
{
    private Sprite sprite;
    private Component area;
    private Wall[] walls = new Wall[4];
    
    public AreaBorder(Sprite sprite, Component area, int[] p1, int[] p2)
    {
        //
        this.sprite = sprite;
        this.area = area;
        // Точки:
        Point[] points = new Point[4];
        points[0] = new Point(p1[0], p1[1]); // лево-верх
        points[1] = new Point(p1[0], p2[1]); // право-верх
        points[2] = new Point(p2[0], p1[1]); // лево-низ
        points[3] = new Point(p2[0], p2[1]); // право-низ
        // Стены
        walls[0] = new Wall(points[0], points[1].x, Wall.AXIS_X); // верх
        walls[1] = new Wall(points[0], points[2].y, Wall.AXIS_Y); // лево
        walls[2] = new Wall(points[1], points[3].y, Wall.AXIS_Y); // право
        walls[3] = new Wall(points[2], points[3].x, Wall.AXIS_X); // низ
    }
    
    private class Point
    {
        int x, y;
        
        Point(int x, int y)
        {
            this.x = x; this.y = y;
        }
    }
    
    private class Wall
    {
        public static final int AXIS_X = 1, AXIS_Y = 2;
        int x, y, length, axis;
        
        Wall(Point start, int end, int axis)
        {
            x = start.x;
            y = start.y;
            length = end;
            this.axis = axis;
        }
        
        boolean checkMovingOpportunity(int x, int y, int axis, int dir)
        {
            boolean opportunity = false;
            
            // Если оси одинаковые, то двигаться точно будет можно.
            if (axis != this.axis)
            {
                // Проверяем направление
                if (dir > 0)
                {
                    if (this.axis == AXIS_X)
                    {
                        if (y >= this.y)
                        {
                            if (x >= this.x && x <= this.x + this.length)
                            {
                                opportunity = true;
                            }
                        }
                    }
                    else
                    {
                        if (x >= this.x)
                        {
                            if (y >= this.x && x <= this.y + this.length)
                            {
                                opportunity = true;
                            }
                        }
                    }
                }
                else
                {
                    if (this.axis == AXIS_X)
                    {
                        if (y <= this.y)
                        {
                            if (x >= this.x && x <= this.x + this.length)
                            {
                                opportunity = true;
                            }
                        }
                    }
                    else
                    {
                        if (x <= this.x)
                        {
                            if (y >= this.x && x <= this.y + this.length)
                            {
                                opportunity = true;
                            }
                        }
                    }
                }
            }
            
            return opportunity;
        }
    }
    
    public boolean checkMovingOpportunity(int length, int dir, int axis)
    {
        boolean opportunity = false;
        
        for (Wall wall : walls)
        {
            if (axis == Wall.AXIS_X)
            {
                if (wall.checkMovingOpportunity(sprite.x + length * dir, sprite.y, axis, dir))
                {
                    opportunity = true;
                    break;
                }
            }
            else
            {
                if (wall.checkMovingOpportunity(sprite.x, sprite.y + length * dir, axis, dir))
                {
                    opportunity = true;
                    break;
                }
            }
        }
        
        return opportunity;
    }
}
