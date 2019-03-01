package com.originalstudio.skyexplorer;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class GameMenu extends JWindow {

	// SINGLETON
	private static GameMenu instance = null;
	
	public static GameMenu getInstance()
	{
		if (instance == null)
		{
			instance = new GameMenu();
		}
		
		return instance;
	}
	
	//
	private GameMenu()
	{
		setBounds((Main.frame.getWidth()-500)/2, (Main.frame.getHeight()-250)/2, 500, 250);
		
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		
		JButton resume = new JButton(), come_back = new JButton();
		resume.setBackground(Color.BLUE);
		come_back.setBackground(Color.BLUE);
		//
		resume.setFocusable(false);
		come_back.setFocusable(false);
		Main.frame.requestFocus();
		//
		Functions.buttonWithIcon(Img.b_resume_game_menu, resume);
		Functions.buttonWithIcon(Img.b_exit_game_menu, come_back);
		// РОЗОВЫЙ КУСЬ
		panel.add(resume);
		panel.add(come_back);
		
		resume.addActionListener(e -> resume());
		come_back.addActionListener(e -> exit());
		
		add(panel);
	}
	
	//
	private void resume()
	{
		make_hide();
	}
	
	private void exit()
	{
		make_hide();
		Main.frame.getContentPane().removeAll();
		Main.frame.getContentPane().revalidate();
        Main.frame.getContentPane().repaint();
		Menu.getInstance().showMenu();
	}
	
	//
	private void make_hide()
	{
		setVisible(false);
	}
	
	public void make_show()
	{
		setVisible(true);
	}
}
