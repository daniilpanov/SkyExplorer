package com.newlightstudio.skyexplorer.app.menu;

import javax.swing.*;
import java.awt.*;

class VMenu extends JPanel
{
    VMenu()
    {
        setLayout(new GridLayout(2, 2));
    }
    
    void createButtonWithImg(Icon icon, JButton button)
    {
        //
        button.setSize(icon.getIconWidth(), icon.getIconHeight());
        //
        button.setIcon(icon);
    }
}
