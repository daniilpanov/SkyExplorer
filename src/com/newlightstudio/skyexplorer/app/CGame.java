package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.Main;
import com.newlightstudio.skyexplorer.app.sprite.CSprite;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CGame extends MGame
{
    private VGame view = new VGame();
    
    public CGame()
    {
        super();
        //setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Main.screen_size);
        setExtendedState(MAXIMIZED_BOTH);
        requestFocus();
        
        view.addImageToButton(Img.b_go_to_game_menu, view.open_menu);
        view.addActionListenerToOpenMenu(e -> openMenu());
        view.setPaint((this::paint));
        
        getContentPane().add(view);
        setVisible(true);
    }
    
    private void stop()
    {
        Main.switcher();
    }
    
    private void openMenu()
    {
        JWindow menu_window = new JWindow();
        JPanel menu_panel = new JPanel();
        JButton menu_resume = new JButton("resume"),
                menu_stop = new JButton("stop");
        
        view.addImageToButton(Img.b_resume_game_menu, menu_resume);
        view.addImageToButton(Img.b_exit_game_menu, menu_stop);
    
        menu_panel.add(menu_resume);
        menu_panel.add(menu_stop);
        
        menu_window.getContentPane().add(menu_panel);
        
        menu_resume.addActionListener(e -> menu_window.setVisible(false));
        menu_stop.addActionListener(e -> Main.switcher());
        
        menu_window.setSize(
                (int)(Main.screen_size.getWidth()/2),
                (int)(Main.screen_size.getHeight()/2)
        );
        menu_window.setVisible(true);
    }
    
    public void die(String mess)
    {
        JOptionPane.showConfirmDialog(view, mess, "You lose!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        stop();
    }
    
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
    
        for (CSprite[] bg_x : living_bg)
        {
            for (CSprite one_bg : bg_x)
            {
                one_bg.render(g2d);
            }
        }
    }
}
