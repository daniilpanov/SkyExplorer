import com.newlightstudio.skyexplorer.app.settings.MSettings;

public class TestConfig extends MSettings
{
    private TestConfig()
    {
        super();
    
        for (int i = 0; i < cash.size(); i++)
        {
            System.out.println(cash.get(i)[0] + "&" + cash.get(i)[1] + " - {");
            
            for (int j = 0; j < cash_multi.size(); j++)
            {
                if (cash_multi.get(i) == null)
                {
                    break;
                }
                
                System.out.print(cash_multi.get(i)[j]);
            }
    
            System.out.println("}");
        }
    }
    
    public static void main(String[] args)
    {
        new TestConfig();
    }
}
