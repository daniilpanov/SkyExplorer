package com.newlightstudio.skyexplorer.app.menu;

import com.newlightstudio.skyexplorer.app.Img;

import javax.swing.*;

class VMenu extends JPanel
{
    VMenu()
    {
    }
    
    void createButtonWithImg(Icon icon, JButton button)
    {
        //
        button.setSize(icon.getIconWidth(), icon.getIconHeight());
        //
        button.setIcon(icon);
    }
}
