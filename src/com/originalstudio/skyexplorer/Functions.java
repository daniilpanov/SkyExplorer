package com.originalstudio.skyexplorer;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

public class Functions
{

	private Functions()
	{
	}
	
	public static int random(int min, int max)
	{
		return min + (int)(Math.random() * max);
	}
	
	public static void buttonWithIcon(Icon icon, JButton button)
	{
		button.setPreferredSize(new Dimension(icon.getIconWidth()-1, icon.getIconHeight()));
		button.setIcon(icon);
	}
	
	public static int[] replaceCoordinates(int[] array, int i, int to)
	{
		int tmp = array[to];
		array[to] = array[i];
		array[i] = tmp;
		return array;
	}

}
