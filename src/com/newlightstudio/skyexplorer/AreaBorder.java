package com.newlightstudio.skyexplorer;

import java.awt.*;

class AreaBorder
{
	private int max_x, max_y;

    AreaBorder(Component parent)
    {
    	max_x = parent.getWidth();
		max_y = parent.getHeight();
    }
    
    boolean checkMovingOpportunity(int pos_x, int pos_y, int length, int dir, int axis)
    {
        boolean opportunity = false;

        if (axis == Sprite.X)
        {
        	if (dir > 0)
        	{
        		if (pos_x + length >= max_x)
        		{
        			opportunity = true;
        		}
        	}
        	else
        	{
				int min_x = Main.frame.getWidth()/2 + Img.starship_l.getWidth(null);
				if (pos_x - length <= min_x)
        		{
    				opportunity = true;
        		}
        	}
        }
        else
        {
			if (dir > 0)
        	{
        		if (pos_y + length >= max_y)
        		{
        			opportunity = true;
        		}
        	}
        	else
        	{
				int min_y = 0;
				if (pos_y - length <= min_y)
        		{
        			opportunity = true;
        		}
        	}
        }
        
        return opportunity;
    }
}
