package dev.xlinsoft.greatPage.Progs;

import dev.xlin.Enums.GenericReturns;
import dev.xlin.tols.data.JDBSession;
import dev.xlin.tols.data.wakeup;
import dev.xlin.tools.OIDCreator;
import dev.xlinsoft.greatPage.Entities.BeanPaperData;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author lyp
 */
public class PaperService
{

    private wakeup up = null;
    private JDBSession sn = null;

    public PaperService(wakeup _up)
    {
        up = _up;
        sn = new JDBSession(up);
    }

    /**
     *
     * @param bean
     * @return
     */
    public int addPaperElement(BeanPaperData bean)
    {
        if (bean == null)
        {
            return GenericReturns.PARAM_IS_NULL.value();
        }
        //创建ID
        int oid = OIDCreator.createOID(up, "tb_paper_data", "OID", 1, Integer.MAX_VALUE - 1);
        int midx = createOrderIndex(bean.getPaperID());
        bean.setOrderId(midx);
        //准备datachunk  
        bean.setOID(oid);
        //存储 
        int r0 = sn.save(bean);
        if (r0 != GenericReturns.SUCCESS.value())
        {
            return -r0;
        }
        return oid;
    }

    private int createOrderIndex(int paperID)
    {
        String sql = "select max(orderId) as midx  from tb_paper_data where paperID = " + paperID;
        try
        {
            Hashtable[] hts = up.querySQL(sql);
            Hashtable ht = hts[0];
            return (int) ht.get("midx") + 1;
        }
        catch (Exception excp)
        {
            return -1;
        }
    }

    /**
     *
     * @param oid
     * @return
     */
    public int removePapaerElement(int oid)
    {
        //删除数据
        int r1 = sn.delete(BeanPaperData.class, oid);
        return r1;
    }

    /**
     *
     * @param oid
     * @return
     */
    public BeanPaperData getPaperDataElement(int oid)
    {
        return (BeanPaperData) sn.get(BeanPaperData.class, oid);
    }

    public List getPaperElements(int paperID)
    {
        String sql = "select * from tb_paper_data where paperID = " + +paperID +" order by orderId";
        return sn.query(sql, BeanPaperData.class);
    }

}
