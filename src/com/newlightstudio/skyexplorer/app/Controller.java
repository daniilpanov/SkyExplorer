package com.newlightstudio.skyexplorer.app;

import com.newlightstudio.skyexplorer.Main;

import javax.swing.*;

public class Controller extends Model
{
    private View view = new View();
    
    public Controller()
    {
        //setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Main.screen_size);
        
        requestFocus();
        
        setVisible(true);
    }
    
    private void stop()
    {
    
    }
    
    public void die(String mess)
    {
        JOptionPane.showConfirmDialog(view, mess, "You lose!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        stop();
    }
}
