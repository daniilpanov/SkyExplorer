package com.newlightstudio.skyexplorer;

import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JButton;

class Functions
{

	private Functions()
	{
	}
	
	static int random(int min, int max)
	{
		return (min < max) ? (min + (int)(Math.random() * max)) : max;
	}
	
	static void buttonWithIcon(Icon icon, JButton button)
	{
		button.setPreferredSize(new Dimension(icon.getIconWidth()-1, icon.getIconHeight()));
		button.setIcon(icon);
	}
	
	static int[] replaceCoordinates(int[] array, int i, int to)
	{
		int tmp = array[to];
		array[to] = array[i];
		array[i] = tmp;
		return array;
	}

}
