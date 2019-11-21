package tester;

import dev.xlin.tols.data.wakeup;
import dev.xlinsoft.greatPage.Progs.database;

/**
 *
 * @author 22972
 */
public class testData
{

    public static void main(String[] args)
    {
        wakeup up = database.connectRemoteDB__39(); 
        up.close();
    }
 

}
