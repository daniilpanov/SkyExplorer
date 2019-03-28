package com.newlightstudio.skyexplorer;

import java.awt.*;

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
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		size.height -= 40;
    	setSize(size);
    	
    	// Все необходимые панели:
    	JPanel
				// самая главная панель, на которую помещаются остальные
				main = new JPanel(new BorderLayout()),
				// само меню
				menu = new JPanel(new GridLayout(4, 1, 15, 15)),
				// и заставка
				trailer = new JPanel()
				{
					@Override
					protected void paintComponent(Graphics g) {
						// (тут сразу пишем, чтоб наша картинка с заставкой рисовалась)
						super.paintComponent(g);
						g.drawImage(Img.start_game, 0, 0, null);
					}
				};
    	
    	// Создаём пункты меню:
    	JButton play = new JButton(), // PLAY
    			settings = new JButton(), // SETTINGS
    			help = new JButton(), // HELP
    			exit = new JButton(); // EXIT
		// и устанавливаем на них соответствующие иконки
		play.setIcon(Img.b_play);
		settings.setIcon(Img.b_settings);
		help.setIcon(Img.b_help);
		exit.setIcon(Img.b_exit);
		// и разумеется, добавляем их на панель меню
    	menu.add(play);
    	menu.add(settings);
    	menu.add(help);
    	menu.add(exit);
		
    	// Устанавливаем нужный размер панели с заставкой
    	trailer.setSize(Img.start_game.getWidth(null), Img.start_game.getHeight(null));
		// и добавляем все панели на главную
    	main.add(menu, BorderLayout.WEST);
    	main.add(trailer, BorderLayout.CENTER);

    	// Устанавливаем на пункты меню слушатели
		// а также снимаем возможность фокуса (чтобы работал слушатель клавиатуры)
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
