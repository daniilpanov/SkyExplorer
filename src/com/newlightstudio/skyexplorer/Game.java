package com.newlightstudio.skyexplorer;

import javax.swing.*;
import java.awt.*;

import static com.newlightstudio.skyexplorer.Img.*;
import static com.newlightstudio.skyexplorer.Functions.*;

public class Game extends LiveBackground
{
    // SINGLETON
    private static GameMenu game_menu = null;

    private static GameMenu getGameMenu()
    {
        if (game_menu == null)
        {
            game_menu = new GameMenu();
        }

        return game_menu;
    }

    static Game control;

    private static final long serialVersionUID = 6693183466444000235L;
    
    private StarShip starShip;
    Enemy enemy;

    //private int[] ship_fake_coordinates = new int[2];
    long score = 0;
    
    //
    Game()
    {
        setSize(Main.frame.getSize());
        getGameMenu();

        control = this;

        setLayout(new BorderLayout());
        JPanel game_menu = new JPanel();
        JButton open_menu = new JButton();
        buttonWithIcon(b_go_to_game_menu, open_menu);
        game_menu.add(open_menu);

        open_menu.addActionListener(e -> getGameMenu().make_show());

        this.add(game_menu, BorderLayout.NORTH);

        starShip = new StarShip(this);
        //
        starShip.setBorders();
        starShip.setArea(this);

        enemy = new Enemy(this, starShip);

        starShip.addEnemy(enemy);
    }

    void shiftX()
    {
        bg_x[2] = bg_x[0] - bg_w;
        bg_x = replaceCoordinates(bg_x, 2, 0);
        bg_x = replaceCoordinates(bg_x, 2, 1);
    }

    void die(String text)
    {
        //
        JOptionPane.showConfirmDialog(this, text, "You lose!",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        //
        getGameMenu().exit();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int i = 0; //
        do
        {
            i++; //
            //
            g.drawImage(bg, bg_x[i], 0, null);
        }
        //
        while (i < 2);

        // Рисуем игроков
        starShip.render(g);
        // врага рисуем только тогда, когда он будет в зоне видимости
        if (enemy.aroundPlayer())
        {
            enemy.render(g);
        }
    }
}
