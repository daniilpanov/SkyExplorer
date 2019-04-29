package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.app.sprite.Sprite;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;

class VGame extends JPanel
{
    JButton open_menu = new JButton("Открыть меню");
    private Sprite[] drawing;
    
    VGame()
    {
        //
        setLayout(new BorderLayout());
        
        //
        JPanel for_open_menu = new JPanel();
        for_open_menu.setBackground(new Color(0, 0, 45));
        for_open_menu.add(open_menu);
        
        //
        add(for_open_menu, BorderLayout.NORTH);
    }
    
    void addImageToButton(Icon img, JButton button)
    {
        // Устанавливаем картинку
        button.setIcon(img);
        // Устанавливаем размер, как у картинки
        button.setPreferredSize(
                new Dimension(
                        img.getIconWidth()-11,
                        img.getIconHeight()
                )
        );
    }
    
    void addActionListenerToOpenMenu(ActionListener l)
    {
        open_menu.addActionListener(l);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    
        Graphics2D graphics2D = (Graphics2D) g;
        
        for (Sprite sprite : drawing)
        {
            sprite.draw(graphics2D);
        }
    }
    
    public void setDrawing(Sprite[] drawing)
    {
        this.drawing = drawing;
    }
}
