package com.newlightstudio.skyexplorer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class Menu extends JWindow
{
	// SINGLETON
	private static Menu instance = null;
	
	static Menu getInstance()
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
    	
    	JPanel
                //
				menu = new JPanel(new GridLayout(4, 1, 15, 15)),
                //
				main = new JPanel(new BorderLayout()),
				//
				trailer = new JPanel()
				{
					@Override
					protected void paintComponent(Graphics g) {
						super.paintComponent(g);
						g.drawImage(Img.start_game, 0, 0, null);
					}
				};
    	
    	JButton play = new JButton(), // PLAY
    			settings = new JButton(), // SETTINGS
    			help = new JButton(), // HELP
    			exit = new JButton(); // EXIT

		play.setIcon(Img.b_play);
		settings.setIcon(Img.b_settings);
		help.setIcon(Img.b_help);
		exit.setIcon(Img.b_exit);

    	menu.add(play);
    	menu.add(settings);
    	menu.add(help);
    	menu.add(exit);

    	trailer.setSize(Img.start_game.getWidth(null), Img.start_game.getHeight(null));

    	main.add(menu, BorderLayout.WEST);
    	main.add(trailer, BorderLayout.CENTER);

    	play.addActionListener(e -> play());
    	play.setFocusable(false);
    	
    	//settings.addActionListener(e -> );
    	settings.setFocusable(false);
    	
    	exit.addActionListener(e -> System.exit(0));
    	exit.setFocusable(false);
    	
    	Main.frame.requestFocus();
    	
    	getContentPane().add(main);
	}
	
	void showMenu()
	{
		setVisible(true);
	}
	
	void play()
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
