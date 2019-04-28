package com.newlightstudio.skyexplorer.app.settings;

import javax.swing.JFrame;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class MSettings extends JFrame
{
    private File config;
    private FileReader config_reader;
    private FileWriter config_editor;
    private Scanner config_scanner;
    
    private ArrayList<String[]> cash,
            cash_multi;
    
    MSettings()
    {
        cash = new ArrayList<>();
        cash_multi = new ArrayList<>();
    }
    
    String[][] getSettings()
    {
        String[][] all_settings_as_array = new String[cash.size()][2];
    
        for (int i = 0; i < cash.size(); i++)
        {
            all_settings_as_array[i] = cash.get(i);
        }
        
        return all_settings_as_array;
    }
    
    String getSettings(String name) throws Exception
    {
        boolean found = false;
        String settings = null;
        
        for (String[] one_setting : cash)
        {
            if (one_setting[0].equals(name))
            {
                found = true;
                
                settings = one_setting[1];
                
                break;
            }
        }
        
        if (!found)
        {
            throw new Exception("This setting doesn't exist!");
        }
        
        return settings;
    }
    
    /*String[] getMultiSettings(String name)
    {
        
    }*/
    
    
    void editSetting(String name, String value) throws Exception
    {
        if (!updateCash(name, value))
        {
            throw new Exception("<b>Unknown error!</b> You should reload this game!");
        }
        
        StringBuilder settings_content = new StringBuilder();
        
        for (String[] part_of_cash : cash)
        {
            settings_content.append(part_of_cash[0]).append(": ").append(part_of_cash[1]);
        }
        
        String content = settings_content.toString();
        
        try
        {
            config_editor.write(content);
            config_editor.flush();
        }
        catch (IOException e)
        {
            throw new Exception("<b>Unknown error!</b> You should reload this game!");
        }
    }
    
    void initCash()
    {
        String set_name;
        StringBuilder set_value;
        String tmp_value;
        String[] elem_of_cash_multi_as_array;
        
        while (config_scanner.hasNext())
        {
            set_name = config_scanner.next().replace(":", "");
            set_value = new StringBuilder(config_scanner.next());
            
            if (set_value.toString().equals("{"))
            {
                while (!(tmp_value = config_scanner.next()).equals("}") || config_scanner.hasNext())
                {
                    set_value.append(tmp_value);
                }
                
                set_value.append("}");
                
                tmp_value = set_value.substring(1, set_value.length()-1);
                
                elem_of_cash_multi_as_array = tmp_value.split(", ");

                cash_multi.add(elem_of_cash_multi_as_array);
            }
            else
            {
                cash_multi.add(null);
            }
            
            cash.add(new String[]{set_name, set_value.toString()});
        }
    }
    
    boolean updateCash(String name, String new_value)
    {
        boolean res = false;
        String[] cashed_setting;
        
        for (int i = 0; i < cash.size(); i++)
        {
            cashed_setting = cash.get(i);
            
            if (cashed_setting[0].equals(name))
            {
                cashed_setting[1] = new_value;
                cash.set(i, cashed_setting);
                
                if (cash_multi.get(i) != null)
                {
                    // TODO: 12.04.2019 update "cash_multi"!
                }
                
                res = true;
                break;
            }
        }
        
        return res;
    }
    
    
    boolean initConfigFile()
    {
        boolean res = true;
        
        try
        {
            config = new File("res/config/config.ini");
        }
        catch (NullPointerException e)
        {
            res = false;
        }
        
        return res;
    }
    
    boolean initConfigReader()
    {
        boolean res = true;
        
        try
        {
            config_reader = new FileReader(config);
        }
        catch (FileNotFoundException e)
        {
            res = false;
        }
        
        return res;
    }
    
    boolean initConfigWriter()
    {
        boolean res = true;
        
        try
        {
            config_editor = new FileWriter(config);
        }
        catch(IOException e)
        {
            res = false;
        }
        
        return res;
    }
    
    void initScanner()
    {
        config_scanner = new Scanner(config_reader);
    }
}
