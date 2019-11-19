package dev.xlinsoft.greatPage.Progs;

import dev.xlin.Enums.GenericReturns;
import dev.xlin.tols.data.JDBSession;
import dev.xlin.tols.data.wakeup;
import dev.xlin.tools.OIDCreator;
import dev.xlinsoft.greatPage.Entities.BeanPaperData;
import dev.xlinsoft.greatPage.Entities.BeanPaperDataHead;
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
     * @param text
     * @return
     */
    public int addPaperElement(BeanPaperDataHead bean, String text)
    {
        if (bean == null)
        {
            return GenericReturns.PARAM_IS_NULL.value();
        }
        if (text == null)
        {
            return GenericReturns.PARAM_IS_NULL.value();
        }
        //创建ID
        int oid = OIDCreator.createOID(up, "tb_paper_data_head", "OID", 1, Integer.MAX_VALUE - 1);
        //准备datachunk 
        BeanPaperData bpd = new BeanPaperData();
        bpd.setDataChunk(text.trim());
        bpd.setOID(oid);
        bean.setOID(oid);
        //数据库事务启用
        boolean btrans = sn.decideOpenTranscation(up);
        //存储头
        int r0 = sn.save(bean);
        if (r0 != GenericReturns.SUCCESS.value())
        {
            sn.rollbackTranscation(up, btrans);
            return -r0;
        }
        //存储数据
        int r1 = sn.save(bpd);
        if (r1 != GenericReturns.SUCCESS.value())
        {
            sn.rollbackTranscation(up, btrans);
            return -r1;
        }
        sn.commitTranscation(up, btrans);
        return oid;
    }

    /**
     *
     * @param oid
     * @return
     */
    public int removePapaerElement(int oid)
    {
        //检查存在否
        BeanPaperDataHead bean = getPaperDataElement(oid);
        if (bean == null)
        {
            return GenericReturns.OBJECT_NOT_EXIST.value();
        }
        //启动事务
        boolean btrans = sn.decideOpenTranscation(up);
        //删除头
        int r0 = sn.delete(BeanPaperDataHead.class, oid);
        if (r0 != GenericReturns.SUCCESS.value())
        {
            sn.rollbackTranscation(up, btrans);
            return r0;
        }
        //删除数据
        int r1 = sn.delete(BeanPaperData.class, oid);
        if (r1 != GenericReturns.SUCCESS.value())
        {
            sn.rollbackTranscation(up, btrans);
            return r1;
        }
        sn.commitTranscation(up, btrans);
        return GenericReturns.SUCCESS.value();
    }

    /**
     *
     * @param oid
     * @return
     */
    public BeanPaperDataHead getPaperDataElement(int oid)
    {
        return (BeanPaperDataHead) sn.get(BeanPaperDataHead.class, oid);
    }

    /**
     *
     * @param oid
     * @return
     */
    public BeanPaperData getPaperData(int oid)
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
        return sn.query(sql, BeanPaperDataHead.class);
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
        return sn.query(sql, BeanPaperDataHead.class);
    }

    //制作框选查询
    private String makeGetElementsByRectangle(int paper, double x, double y, double w, double h)
    {
        String sql = "select * from tb_paper_data_head where paper = " + paper
                + " and x >" + x + " and y > " + y + " and x< " + (x + w) + " and y < " + (y + h);
        return sql;
    }

}
