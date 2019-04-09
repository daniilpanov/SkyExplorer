package com.newlightstudio.skyexplorer;

import com.newlightstudio.skyexplorer.app.Controller;
import com.newlightstudio.skyexplorer.app.menu.CMenu;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import static javax.swing.UIManager.*;

public class Main
{
    public static final Dimension screen_size
            = Toolkit.getDefaultToolkit().getScreenSize();
    // Текущий JFrame (либо меню, либо игра)
    private static JFrame current_frame;
    // Текущий режим (меню - "MENU", или игра - "GAME")
    private static String mode;
    
    // Method-switcher Menu-Main_frame
    public static void switcher()
    {
        JFrame old_frame = current_frame;
        
        if (mode.equals("GAME"))
        {
            current_frame = CMenu.getInstance();
            current_frame.setVisible(true);
            old_frame.dispose();
            mode = "MENU";
        }
        else if (mode.equals("MENU"))
        {
            current_frame = new Controller();
            current_frame.setVisible(true);
            old_frame.setVisible(false);
            mode = "MENU";
        }
    }
    
    private Main()
    {
        //
        current_frame = CMenu.getInstance();
        mode = "MENU";
        
        current_frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        try
        {
            for (LookAndFeelInfo lookAndFeel : getInstalledLookAndFeels())
            {
                if (lookAndFeel.getName().equals("Nimbus"))
                {
                    setLookAndFeel(lookAndFeel.getClassName());
                }
            }
        }
        catch (IllegalAccessException | InstantiationException
                | UnsupportedLookAndFeelException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        
        
        EventQueue.invokeLater(Main::new);
    }
    
}
