package com.newlightstudio.skyexplorer.app;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;

class VGame extends JPanel
{
    JButton open_menu = new JButton("Открыть меню");
    Paint paint = null;
    
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
    
    void setPaint(Paint paint)
    {
        this.paint = paint;
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
        
        if (paint != null)
        {
            paint.paint(g);
        }
    }
}
