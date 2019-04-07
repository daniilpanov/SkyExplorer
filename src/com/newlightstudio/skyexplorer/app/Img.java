package com.newlightstudio.skyexplorer.app;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

// STATIC CLASS - DATA OF ALL IMAGES
class Img
{
	/**
	 * PATH {@link String}
	 * Эта константа используется для автодополнения путей к картинкам
	 */
	private static final String PATH = "res/img/";

	// КАРТИНКИ ДЛЯ САМОЙ ИГРЫ (будут показываться во время игрового процесса):
	static final Image
			// Корабли (разные направления движения)
			starship_r = getImg("starship_r.png"), // вправо
			starship_l = getImg("starship_l.png"), // влево
			// Фон
			bg = getImg("bg.png"),
			// Заставка
			start_game = getImg("trailer.png"),
			// Торпеды (разные направления движения)
			torpedo_r = getImg("torpedo_r.png"),
			torpedo_l = getImg("torpedo_l.png"),
			// Враги (разные направления движения)
			enemy_r = getImg("enemy_r.png"),
			enemy_l = getImg("enemy_l.png");


	// ИКОНКИ ДЛЯ КНОПОК:
	static final Icon
			// Игровое меню
			b_go_to_game_menu = getIcon("b_open_game_menu.png"), // кнопка его открытия
			b_resume_game_menu = getIcon("b_resume.png"), // продолжить
			b_exit_game_menu = getIcon("b_exit_at_game_menu.png"), // выйти из игры
			// Кнопки главного меню
			b_exit = getIcon("b_exit.png"), // выход
			b_help = getIcon("b_help.png"), // помощь
			b_play = getIcon("b_play.png"), // играть
			b_settings = getIcon("b_settings.png"); // настройки

	// Закрываем возможность создавать объекты этого класса
	private Img()
	{
	}

	/**
	 * Метод для быстрого получения объекта типа Image
	 * @param name {@link String}
	 * @return Image
	 */
	private static Image getImg(String name)
	{
		return new ImageIcon(PATH + name).getImage();
	}

	/**
	 * Метод для быстрого получения объекта типа Icon
	 * @param name {@link String}
	 * @return Icon
	 */
	//@NotNull
	//@Contract("_ -> new")
	private static Icon getIcon(String name)
	{
		return new ImageIcon(PATH + name);
	}
}
