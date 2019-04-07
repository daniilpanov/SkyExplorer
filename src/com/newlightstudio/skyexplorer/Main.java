package com.newlightstudio.skyexplorer;

import com.newlightstudio.skyexplorer.app.Controller;
import com.newlightstudio.skyexplorer.app.menu.CMenu;

import javax.swing.JFrame;
import java.awt.*;

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
        if (mode.equals("GAME"))
        {
            current_frame = CMenu.getInstance();
            mode = "MENU";
        }
        else if (mode.equals("MENU"))
        {
            current_frame = Controller.getInstance();
            mode = "MENU";
        }
    }
    
    private Main()
    {
        current_frame = CMenu.getInstance();
        mode = "MENU";
    }
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(Main::new);
    }
    
}
