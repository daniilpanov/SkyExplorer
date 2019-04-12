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
    
    private ArrayList<String[]> cash = new ArrayList<>(),
            fixed = new ArrayList<>(),
            cash_multi = new ArrayList<>();
    
    private ArrayList<String> errors = new ArrayList<>();
    
    MSettings()
    {
        if (!initConfigFile())
        {
            errors.add("<b>Configuration file is not found!</b> We will try to create it!");
            
            if (!initConfigWriter())
            {
                errors.add("Sorry, we can not to create configuration file... " +
                        "Please, <i>reload this game</i>. " +
                        "If it will be <u>display again, text us</u>. Thank you!");
            }
            else
            {
                fixed.add(
                        new String[]{
                                String.valueOf(errors.size()-1),
                                "Configuration file was created!"
                        }
                );
                
                if (initConfigReader())
                {
                    initScanner();
                }
                else
                {
                    errors.add("<b>Unknown error!</b> You should reload this game!");
                }
            }
        }
        else
        {
            if (initConfigReader())
            {
                initScanner();
                
                initCash();
            }
            else
            {
                errors.add("<b>Unknown error!</b> You should reload this game!");
            }
        }
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
    
    
    boolean editSetting(String name, String value)
    {
        if (!updateCash(name, value))
        {
            errors.add("<b>Unknown error!</b> You should reload this game!");
            return false;
        }
        
        boolean res = true;
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
            res = false;
            e.printStackTrace();
        }
    
    
        return res;
    }
    
    private void initCash()
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
    
    private boolean updateCash(String name, String new_value)
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
    
    
    private boolean initConfigFile()
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
    
    private boolean initConfigReader()
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
    
    private boolean initConfigWriter()
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
    
    private void initScanner()
    {
        config_scanner = new Scanner(config_reader);
    }
}
