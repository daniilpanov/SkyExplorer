import com.newlightstudio.skyexplorer.app.StarShip;

import javax.swing.*;
import java.awt.*;

public class TestForStarship extends JFrame
{
    private StarShip ship;
    
    public static void main(String[] args)
    {
        new TestForStarship();
    }
    
    private TestForStarship()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 800, 600);
    
        JLabel label = new JLabel()
        {
            @Override
            public void paint(Graphics g)
            {
                super.paint(g);
                
                ship.draw((Graphics2D) g);
            }
        };
        
        ship = new StarShip(500, 500, label);
        
        ship.addKeyControl(this, false);
        ship.addMouseControl(this);
        
        JPanel p = new JPanel(null);
        p.add(label);
        add(p);
        
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
    }
}
