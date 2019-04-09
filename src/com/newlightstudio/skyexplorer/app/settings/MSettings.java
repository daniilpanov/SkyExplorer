package com.newlightstudio.skyexplorer.app.settings;

import javax.swing.JFrame;
import java.io.File;

class MSettings extends JFrame
{
    File configure = new File("res/config/ini.xml");
    
    private String[][] cash;
    private final String regex = "/(.*): (.*)/",
            // FIXME: регулярное выражение находит не всё!
            multi_regex = "/(.*): \\{(('([a-zA-Z0-9]*)': \"([a-zA-Z0-9]*)\").?.?)+\\}/";
    
    /*String[][] getSettings()
    {
    
    }
    
    String getSettings(String name)
    {
    
    }*/
    
    boolean editSetting(String name, String value)
    {
        clearCash(name);
        
        boolean res = false;
        
        
        
        return res;
    }
    
    private void clearCash(String name)
    {
    
    }
}
