package dev.xlinsoft.greatPage.Progs;

import dev.xlin.tols.data.dbReturn;
import dev.xlin.tols.data.dbState;
import dev.xlin.tols.data.wakeup;

/**
 *
 * @author lyp
 */
public class database
{

    public static wakeup connectRemoteDB__39()
    {
        wakeup up = new wakeup(false);
        int dtype = dbState.DBTYPE_MYSQL_5; 
        String sip = "39.108.149.122";
        String uname = "developeID";
        String upwd = "maomao10131104LL";
        int r = up.createConnection(dtype, "jdbc:mysql://" + sip + ":3306/", uname, upwd);
        if (r != dbReturn.CONNECTION_CREATE_SUCCESS)
        {
            return null;
        }
        int i = up.useDB("great_paper");
        if (i != 5808)
        {
            return null;
        }
        return up;
    }

    public static wakeup connectLocalDB()
    {
        wakeup up = new wakeup(false);
        int dtype = dbState.DBTYPE_MYSQL_5; 
        String sip = "127.0.0.1";
        String uname = "root";
        String upwd = "admin";
        int r = up.createConnection(dtype, "jdbc:mysql://" + sip + ":3306/", uname, upwd);
        if (r != dbReturn.CONNECTION_CREATE_SUCCESS)
        {
            return null;
        }
        int i = up.useDB("great_paper");
        if (i != 5808)
        {
            return null;
        }
        return up;
    }

}
