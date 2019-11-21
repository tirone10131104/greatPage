package dev.xlinsoft.greatPage.Progs;

import dev.xlin.Enums.GenericReturns;
import dev.xlin.tols.data.JDBSession;
import dev.xlin.tols.data.wakeup;
import dev.xlin.tools.OIDCreator;
import dev.xlinsoft.greatPage.Entities.BeanPaperData;
import java.util.ArrayList;
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

    /**
     *
     * @param paper
     * @param x
     * @param y
     * @param w
     * @param h
     * @return
     */
    public List getElementsByRectangle(int paper, double x, double y, double w, double h)
    {
        String sql = makeGetElementsByRectangle(paper, x, y, w, h);
        return sn.query(sql, BeanPaperData.class);
    }

    /**
     *
     * @param paper
     * @param x
     * @param y
     * @param w
     * @param h
     * @param aex
     * @return
     */
    public List getElementsByRectangleExclude(int paper, double x, double y, double w, double h, ArrayList<Integer> aex)
    {
        String sql = makeGetElementsByRectangle(paper, x, y, w, h);
        if (aex != null && aex.isEmpty() == false)
        {
            sql = sql + " and OID not in (";
            for (int i = 0; i < aex.size(); i++)
            {
                int id = aex.get(i);
                sql = sql + id;
                if (i < aex.size() - 1)
                {
                    sql = sql + ",";
                }
            }
            sql = sql + ")";
        }
        return sn.query(sql, BeanPaperData.class);
    }

    //制作框选查询
    private String makeGetElementsByRectangle(int paper, double x, double y, double w, double h)
    {
        String sql = "select * from tb_paper_data where paperID = " + paper
                + " and x >" + x + " and y > " + y + " and x< " + (x + w) + " and y < " + (y + h); 
        return sql;
    }

}
