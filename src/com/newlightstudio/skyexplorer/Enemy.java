package com.newlightstudio.skyexplorer;

import java.awt.Component;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import static com.newlightstudio.skyexplorer.Img.*;
import static com.newlightstudio.skyexplorer.Functions.*;

public class Enemy extends Sprite
{
	private int min_w, min_speed = 1;
	private Sprite player;
	private Timer moving;

    Enemy(Component parent, Sprite player)
	{
		super(0, 0, enemy_r, enemy_l, parent, true);

		min_w = parent.getWidth();
		y = (parent.getHeight() - getHeight()) / 2;

		this.player = player;

		relocation();

        moving = new Timer(100, e -> move());
		moving.start();
	}
	
	void relocation()
	{
		x = -random(min_w + Math.abs(player.y), min_w * random(1, 15));

		move();
		
		parent.repaint();
	}

	private void move()
	{
		int new_direction;

        if (x > player.x)
        {
            new_direction = -1;
        }
        else
        {
            new_direction = 1;
        }

        min_speed = random(min_speed, 15);
        int l = min_speed * new_direction;

        moveX(new_direction, l);

        parent.repaint();
	}

	boolean aroundPlayer()
    {
        boolean around = false;

        if (x + getWidth() > 0 && x < parent.getWidth())
        {
            around = true;

            if (x + getWidth() > player.x && x < player.x + player.getWidth()
                && y + getHeight() > player.y && y < player.y + player.getHeight())
            {
                Game.control.die(
                        "<html><head></head>" +
                                "<body>" +
                                "<h2>Вы проиграли!</h2><br>" +
                                "<i>Вы столкнулись с противником</i>" +
                                "</body></html>"
                );

                moving.stop();
            }
        }

        return around;
    }

    private void shoot()
    {

    }

	@Override
	public void update(int x, int y, int dir, boolean repaint)
	{
		super.update(x, y, dir, repaint);

		if (dir != direction)
		{
			min_speed = 1;
		}
	}

	@Override
	protected void checkingKey(KeyEvent e)
	{
	}
}
