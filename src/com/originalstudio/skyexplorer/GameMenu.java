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
		
		JButton resume = new JButton(), exit = new JButton();
		resume.setBackground(Color.BLUE);
		exit.setBackground(Color.BLUE);
		
		Functions.buttonWithIcon(Img.b_resume, resume);
		Functions.buttonWithIcon(Img.b_exit, exit);
		// пнгнбши йсяэ
		panel.add(resume);
		panel.add(exit);
		
		resume.addActionListener(e -> resume());
		exit.addActionListener(e -> exit());
		
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
	public void make_hide()
	{
		setVisible(false);
	}
	
	public void make_show()
	{
		setVisible(true);
	}
}
