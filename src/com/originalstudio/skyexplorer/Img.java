package com.originalstudio.skyexplorer;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

// STATIC CLASS - DATA OF ALL IMAGES
public class Img
{
	/**
	 * PATH {@link String}
	 * Эта константа используется для автодополнения путей к картинкам
	 */
	private static final String PATH = "res/img/";

	// КАРТИНКИ ДЛЯ САМОЙ ИГРЫ (будут показываться во время игрового процесса):
	public static final Image
			// Корабли (разные направления движения)
			starship_r = getImg("starship_r.png"), // вправо
			starship_l = getImg("starship_l.png"), // влево
			starship_t = getImg("starship_t.png"), // вверх
			starship_b = getImg("starship_b.png"), // вниз
			// Фон
			bg = getImg("bg.png"),
			// Заставка
			start_game = getImg("satrting.png");
	// Планеты (3)
	public static final Image[] planets = getImages("planet", "png", 3, 1);

	// ИКОНКИ ДЛЯ КНОПОК:
	public static final Icon
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
	@NotNull
	@Contract("_ -> new")
	private static Icon getIcon(String name)
	{
		return new ImageIcon(PATH + name);
	}

	/**
	 * Метод для получения массива картинок
	 * name {@link String}  - имя файла,
	 * type {@link String} - расширение файла,
	 * length int - количество картинок (длина массива),
	 * begin int - с какого номера начинаются картинки;
	 * @return Image
	 */
	private static Image[] getImages(String name, String type, int length, int begin)
	{
		Image[] images = new Image[length];

		for (int i = 0; i < images.length; i++)
		{
			images[i] = getImg(name + (i+begin) + "." + type);
		}

		return images;
	}
}
