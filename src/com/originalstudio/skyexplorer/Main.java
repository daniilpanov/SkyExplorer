package com.originalstudio.skyexplorer;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * <h1>Стандартный главный класс</h1>
 */
public class Main extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7008553333134883994L;
	
	// Делаем доступным объект фрейма из любого класса
	public static JFrame frame;

	// 
	public static void main(String[] args)
    {
        EventQueue.invokeLater(Main::new);
    }
    
    private Main()
    {
    	// 
    	frame = this;
    	//
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
    	//setUndecorated(true);
    	//
    	setExtendedState(MAXIMIZED_BOTH);
    	setVisible(true);
    	//
    	Menu.getInstance().showMenu();
    }
}
