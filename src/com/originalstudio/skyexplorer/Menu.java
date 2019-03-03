package com.originalstudio.skyexplorer;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class Menu extends JWindow
{
	// SINGLETON
	private static Menu instance = null;
	
	public static Menu getInstance()
	{
		if (instance == null)
		{
			instance = new Menu();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4060336903641762950L;
	
	// 
	private Menu()
	{
    	setSize(Toolkit.getDefaultToolkit().getScreenSize());
    	
    	JPanel panel = new JPanel(new GridLayout(4, 1, 15, 15));
    	
    	JButton play = new JButton("PLAY"),
    			settings = new JButton("SETTINGS"),
    			help = new JButton("HELP"),
    			exit = new JButton("EXIT");
    	
    	panel.add(play);
    	panel.add(settings);
    	panel.add(help);
    	panel.add(exit);
    	
    	play.addActionListener(e -> play());
    	play.setFocusable(false);
    	
    	//settings.addActionListener(e -> );
    	settings.setFocusable(false);
    	
    	exit.addActionListener(e -> System.exit(0));
    	exit.setFocusable(false);
    	
    	Main.frame.requestFocus();
    	
    	getContentPane().add(panel);
	}
	
	public void showMenu()
	{
		setVisible(true);
	}
	
	public void play()
	{
		setVisible(false);
		//
		JPanel game = new Game();
		game.setLocation(0, 0);
		Main.frame.getContentPane().add(game);
		//
		Main.frame.getContentPane().revalidate();
		Main.frame.getContentPane().repaint();
	}
}
