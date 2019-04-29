package com.newlightstudio.skyexplorer;

import com.newlightstudio.skyexplorer.app.CGame;
import com.newlightstudio.skyexplorer.app.menu.CMenu;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import static javax.swing.UIManager.*;

public class Main
{
    // Размер экрана
    public static final Dimension screen_size
            = Toolkit.getDefaultToolkit().getScreenSize();
    // Текущий JFrame (либо меню, либо игра)
    private static JFrame current_frame;
    // Текущий режим (меню - "MENU", или игра - "GAME")
    private static String mode;
    
    // Method-switcher Menu-Main_frame
    public static void switcher()
    {
        // Предыдущее окно
        JFrame old_frame = current_frame;
        
        // Если сейчас идёт игра
        if (mode.equals("GAME"))
        {
            // устанавливаем текущее окно
            current_frame = CMenu.getInstance();
            // делаем его видимым
            current_frame.setVisible(true);
            // закрываем старое окно
            old_frame.dispose();
            // и меняем режим
            mode = "MENU";
        }
        // Если показано меню
        else if (mode.equals("MENU"))
        {
            // создаём новый объект контроллера игры
            current_frame = new CGame();
            // делаем его видимым
            current_frame.setVisible(true);
            // убираем старое окно
            old_frame.setVisible(false);
            // и меняем режим
            mode = "GAME";
        }
    }
    
    private Main()
    {
        // Устанавливаем "настройки" по умолчанию:
        // сейчас текущее окно - меню
        current_frame = CMenu.getInstance();
        mode = "MENU";
        // делаем окно видимым
        current_frame.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        // УСТАНАВЛИВАЕМ LOOK-AND-FEEL
        try
        {
            // В цикле перебираем все возможные LookAndFeel
            for (LookAndFeelInfo lookAndFeel : getInstalledLookAndFeels())
            {
                // и если среди них есть "Nimbus", то устанавливаем его
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
        
        // ЗАПУСКАЕМ ПРОГРАММУ
        EventQueue.invokeLater(Main::new);
    }
    
}
