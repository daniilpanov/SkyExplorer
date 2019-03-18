package com.newlightstudio.skyexplorer;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.Timer;

import static java.awt.event.KeyEvent.*;

public class StarShip extends Sprite
{
    private ArrayList<Timer> shoot = new ArrayList<>();
    private Enemy enemy;

    StarShip(Component parent)
    {
        super(Main.frame.getWidth()/2, Main.frame.getHeight()/2,
                Img.starship_r, Img.starship_l, parent, true);

        //
        shoot.add(null);
        //
        this.addControl(Main.frame);
    }

    void addEnemy(Enemy enemy)
    {
        this.enemy = enemy;
    }

    private void shoot()
    {
        if (enemy.aroundPlayer())
        {
            if (enemy.y <= y + getHeight()/2 && enemy.y + enemy.getHeight() >= y)
            {
                enemy.relocation();
            }
        }
    }

    @Override
    protected void checkingKey(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case VK_ENTER:
                shoot();
                break;

            case VK_LEFT:
                this.realMoveX(4, 2, 50, -1);
                break;
        }
    }
}
