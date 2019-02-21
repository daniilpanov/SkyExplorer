package com.originalstudio.skyexplorer;

import javax.swing.JPanel;
import java.awt.*;

import static com.originalstudio.skyexplorer.Img.*;

public class Game extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6693183466444000235L;
	
	private StarShip starShip = new StarShip(this);
	
	private int bg_x = 0, bg_y = 0;

	// 
	public Game()
	{
		
	}
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(bg, bg_x, bg_y, null);
        
        starShip.ship.render(g);
    }
}
