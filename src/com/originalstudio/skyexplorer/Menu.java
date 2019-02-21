package com.originalstudio.skyexplorer;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class Menu extends JWindow
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4060336903641762950L;
	
	// SINGLETON
	private static Menu inst = null;
	
	public static Menu getInstance()
	{
		if (inst == null)
		{
			inst = new Menu();
		}
		return inst;
	}
	
	// 
	private Menu()
	{
    	setSize(Toolkit.getDefaultToolkit().getScreenSize());
    	
    	JPanel panel = new JPanel(new GridLayout(3, 1, 15, 15));
    	
    	JButton play = new JButton("PLAY"),
    			settings = new JButton("SETTINGS"),
    			exit = new JButton("EXIT");
    	
    	play.addActionListener(e -> play());
    	play.setFocusable(false);
    	
    	//settings.addActionListener(e -> );
    	settings.setFocusable(false);
    	
    	exit.addActionListener(e -> System.exit(0));
    	exit.setFocusable(false);
    	
    	Main.frame.requestFocus();
    	
    	panel.add(play);
    	panel.add(settings);
    	panel.add(exit);
    	
    	getContentPane().add(panel);
		setVisible(true);
	}
	
	public void showMenu()
	{
		setVisible(true);
	}
	
	public void play()
	{
		setVisible(false);
	}
}
