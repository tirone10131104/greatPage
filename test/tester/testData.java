package tester;

import dev.xlin.tols.data.wakeup;
import dev.xlinsoft.greatPage.Entities.BeanPaperDataHead;
import dev.xlinsoft.greatPage.Progs.PaperDataType;
import dev.xlinsoft.greatPage.Progs.PaperService;
import dev.xlinsoft.greatPage.Progs.database;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 22972
 */
public class testData
{

    public static void main(String[] args)
    {
        wakeup up = database.connectRemoteDB__39();
//        test002_removeData(up);
//        test001_addData(up);
        test003_selectRect(up);
        up.close();
    }

    public static void test003_selectRect(wakeup up)
    {
        //EX
        ArrayList aex = new ArrayList();
        aex.add(1);

        PaperService psrv = new PaperService(up);
        List les = psrv.getElementsByRectangleExclude(0, -2, -2, 4, 4 , aex);
        if (les == null)
        {
            System.err.println(".LES.NULL");
        }
        else
        {
            System.err.println(".les.size = " + les.size());
            for (int i = 0; i < les.size(); i++)
            {
                BeanPaperDataHead bean = (BeanPaperDataHead) les.get(i);
                System.err.println(".BEAN .HEAD.OID = " + bean.getOID());
            }
        }

    }

    public static void test002_removeData(wakeup up)
    {
        PaperService psrv = new PaperService(up);
        int r = psrv.removePapaerElement(510690999);
        System.err.println("test002_removeData  removePapaerElement r = " + r);
    }

    //测试插入数据
    public static void test001_addData(wakeup up)
    {
        BeanPaperDataHead bean = new BeanPaperDataHead();
        bean.setDataType(PaperDataType.TEXT.value());
        String txt = "四季风1";
        PaperService psrv = new PaperService(up);
        int r = psrv.addPaperElement(bean, txt);
        System.err.println("test001_addData addPaperElement r = " + r);
    }

}
