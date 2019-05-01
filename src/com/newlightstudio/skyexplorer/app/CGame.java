package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.Main;
import com.newlightstudio.skyexplorer.app.sprite.Sprite;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import java.util.ArrayList;
import java.util.Arrays;

import static com.newlightstudio.skyexplorer.app.Img.starship;

public class CGame extends MGame
{
    private JWindow menu_window = new JWindow();
    private JLabel player_label = new JLabel("Hello!");
    
    public CGame()
    {
        super();
    
        // Устанавливаем спрайтов, которые нужно рисовать
        view.setDrawing(spritesToArray());
    
        //
        //
        int
                player_w = starship.getIconWidth(),
                player_h = starship.getIconHeight(),
                x = (int) ((Main.screen_size.getWidth() - player_w) / 2),
                y = (int) ((Main.screen_size.getHeight() - player_h) / 2);
        //
        player = new StarShip(x, y, player_label);
    
        //setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Main.screen_size);
        setExtendedState(MAXIMIZED_BOTH);
        requestFocus();
        
        view.addImageToButton(Img.b_go_to_game_menu, view.open_menu);
        view.addActionListenerToOpenMenu(e -> openMenu());
        
        view.add(player_label);
        
        getContentPane().add(view);
        setVisible(true);
    }
    
    private void stop()
    {
        Main.switcher();
    }
    
    private void openMenu()
    {
        // Меню при паузе
        JPanel menu_panel = new JPanel(); // панель меню
        JButton menu_resume = new JButton("resume"), // возобновить игру
                menu_stop = new JButton("stop"); // выйти из игры
        // Добавляем иконки на эти кнопки
        view.addImageToButton(Img.b_resume_game_menu, menu_resume);
        view.addImageToButton(Img.b_exit_game_menu, menu_stop);
        
        menu_panel.add(menu_resume);
        menu_panel.add(menu_stop);
        
        menu_window.getContentPane().add(menu_panel);
        
        menu_resume.addActionListener(
                e -> menu_window.setVisible(false)
        );
        menu_stop.addActionListener(
                e -> goToMenu()
        );
        
        int one_of_two_width = (int)(Main.screen_size.getWidth() / 2),
                one_of_two_height = (int)(Main.screen_size.getHeight() / 2);
        menu_window.setSize(
                one_of_two_width,
                one_of_two_height
        );
        menu_window.setLocation(
                one_of_two_width - menu_window.getWidth() / 2,
                one_of_two_height - menu_window.getHeight() / 2
        );
        menu_window.setVisible(true);
    }
    
    private void goToMenu()
    {
        menu_window.setVisible(false);
        Main.switcher();
    }
    
    public void die(String mess)
    {
        JOptionPane.showConfirmDialog(view, mess, "You lose!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        stop();
    }
    
    private Sprite[] spritesToArray()
    {
        ArrayList<Sprite> sprites = new ArrayList<>();
        
        for (Sprite[] bg_row : living_bg)
        {
            sprites.addAll(Arrays.asList(bg_row));
        }
        //
        Sprite[] sprites_as_array = new Sprite[sprites.size()];
        //
        sprites_as_array = sprites.toArray(sprites_as_array);
        
        return sprites_as_array;
    }
}
