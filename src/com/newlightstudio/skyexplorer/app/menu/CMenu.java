package com.newlightstudio.skyexplorer.app.menu;

import com.newlightstudio.skyexplorer.Main;
import com.newlightstudio.skyexplorer.app.Img;

import javax.swing.*;
import java.awt.*;

public class CMenu extends MMenu
{
    private static CMenu inst = null;
    
    public static CMenu getInstance()
    {
        if (inst == null)
        {
            inst = new CMenu();
        }
        
        return inst;
    }
    
    
    private VMenu view;
    
    private CMenu()
    {
        setLayout(new GridLayout(2, 1));
        //setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Main.screen_size);
        setExtendedState(MAXIMIZED_BOTH);
        
        add(new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                
                g.drawImage(Img.start_game, 0, 0, null);
            }
        });
        
        view = new VMenu();
        
        JButton play = new JButton(),
                settings = new JButton(),
                help = new JButton(),
                exit = new JButton();
        
        view.createButtonWithImg(Img.b_play, play);
        view.createButtonWithImg(Img.b_settings, settings);
        view.createButtonWithImg(Img.b_help, help);
        view.createButtonWithImg(Img.b_exit, exit);
        
        view.add(play);
        view.add(settings);
        view.add(help);
        view.add(exit);
        
        play.addActionListener(e -> play());
        settings.addActionListener(e -> settings());
        help.addActionListener(e -> help());
        exit.addActionListener(e -> exit());
        
        play.setFocusable(false);
        settings.setFocusable(false);
        help.setFocusable(false);
        
        getContentPane().add(view);
    }
    
    private void play()
    {
        Main.switcher();
    }
    
    private void settings()
    {
    
    }
    
    private void help()
    {
    
    }
    
    private void exit()
    {
        System.exit(0);
    }
}
