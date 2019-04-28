package com.newlightstudio.skyexplorer.app.settings;

import java.util.ArrayList;

public class CSettings extends MSettings
{
    private static final String SUCCESSFUL = "Successful",
            WARNING = "Warning", DANGER = "Danger";
    
    private ArrayList<String[]> logs = new ArrayList<>();
    
    
    public CSettings()
    {
        super();
        
        if (!initConfigFile())
        {
            logs.add(new String[] {
                    "<b>Configuration file is not found!</b> We will try to create it!",
                    WARNING
            });
        
            if (!initConfigWriter())
            {
                logs.add(new String[] {
                        "Sorry, we can not to create configuration file... " +
                        "Please, <i>reload this game</i>. " +
                        "If it will be <u>display again, text us</u>. Thank you!",
                        DANGER
                });
            }
            else
            {
                logs.add(new String[] {
                        "Configuration file was created!",
                        SUCCESSFUL
                });
            
                if (initConfigReader())
                {
                    initScanner();
                }
                else
                {
                    logs.add(new String[] {
                            "<b>Unknown error!</b> You should reload this game!",
                            DANGER
                    });
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
                logs.add(new String[] {
                        "<b>Unknown error!</b> You should reload this game!",
                        DANGER
                });
            }
        }
    }
}
