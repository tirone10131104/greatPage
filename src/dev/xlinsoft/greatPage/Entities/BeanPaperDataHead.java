package dev.xlinsoft.greatPage.Entities;

import dev.xlin.tols.data.annotations.JDBId;
import dev.xlin.tols.data.annotations.JDBTable;

/**
 *
 * @author 22972
 */
@JDBTable(tableName = "tb_paper_data_head")
public class BeanPaperDataHead
{

    @JDBId
    private int OID = 0;
    private int paper = 0;
    private double x = 0;
    private double y = 0;
    private double wid = 0;
    private double het = 0;
    private int dataType = 0;
    private int layer = 0;
    private int styleID = 0;

    public int getOID()
    {
        return OID;
    }

    public void setOID(int OID)
    {
        this.OID = OID;
    }

    public int getPaper()
    {
        return paper;
    }

    public void setPaper(int paper)
    {
        this.paper = paper;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public int getDataType()
    {
        return dataType;
    }

    public void setDataType(int dataType)
    {
        this.dataType = dataType;
    }

    public int getLayer()
    {
        return layer;
    }

    public void setLayer(int layer)
    {
        this.layer = layer;
    }

    public int getStyleID()
    {
        return styleID;
    }

    public void setStyleID(int styleID)
    {
        this.styleID = styleID;
    }

    public double getWid()
    {
        return wid;
    }

    public void setWid(double wid)
    {
        this.wid = wid;
    }

    public double getHet()
    {
        return het;
    }

    public void setHet(double het)
    {
        this.het = het;
    }

}
