package com.originalstudio.skyexplorer;

import java.awt.*;

public class AreaBorder
{
    private Sprite sprite;
    private Component area;
    private Point[] points = new Point[4];
    
    public AreaBorder(Sprite sprite, Component area, int[] p1, int[] p2)
    {
        //
        this.sprite = sprite;
        this.area = area;
        // Точки:
        points[0] = new Point(p1[0], p1[1]); // лево-верх
        points[1] = new Point(p2[0], p1[1]); // право-верх
        points[2] = new Point(p1[0], p2[1]); // лево-низ
        points[3] = new Point(p2[0], p2[1]); // право-низ
    }
    
    private class Point
    {
        int x, y;
        
        Point(int x, int y)
        {
            this.x = x; this.y = y;
        }
    }
    
    public boolean checkMovingOpportunity(int pos_x, int pos_y, int length, int dir, int axis)
    {
        boolean opportunity = false;

        if (axis == Sprite.X)
        {
        	if (dir > 0)
        	{
        		if (pos_x + length >= points[1].x)
        		{
        			if (pos_y >= points[1].y && pos_y <= points[3].y)
        			{
        				opportunity = true;
        			}
        		}
        	}
        	else
        	{
        		if (pos_x - length <= points[0].x)
        		{
        			if (pos_y >= points[0].y && pos_y <= points[2].y)
        			{
        				opportunity = true;
        			}
        		}
        	}
        }
        else
        {
			if (dir > 0)
        	{
        		if (pos_y + length >= points[1].y)
        		{
					if (pos_x >= points[2].x && pos_x <= points[3].x)
        			{
						opportunity = true;
        			}
        		}
        	}
        	else
        	{
        		if (pos_y - length <= points[0].y)
        		{
        			if (pos_x >= points[0].x && pos_x <= points[1].x)
        			{
        				opportunity = true;
        			}
        		}
        	}
        }
        
        return opportunity;
    }
}
