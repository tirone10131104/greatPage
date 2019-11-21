package dev.xlinsoft.greatPage.Entities;

import dev.xlin.tols.data.annotations.JDBId;
import dev.xlin.tols.data.annotations.JDBTable;

/**
 *
 * @author lyp
 */
@JDBTable(tableName = "tb_paper_data")
public class BeanPaperData
{

    @JDBId
    private int OID = 0;
    private String dataChunk = ""; 
    private int paperID = 0;
    private double x = 0;
    private double y = 0;
    private double wid = 0;
    private double het = 0;
    private int dataType = 0;
    private int layerID = 0;
    private int styleID = 0;

    public int getOID()
    {
        return OID;
    }

    public void setOID(int OID)
    {
        this.OID = OID;
    }

    public String getDataChunk()
    {
        return dataChunk;
    }

    public void setDataChunk(String dataChunk)
    {
        this.dataChunk = dataChunk;
    } 

    public int getPaperID()
    {
        return paperID;
    }

    public void setPaperID(int paperID)
    {
        this.paperID = paperID;
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

    public int getDataType()
    {
        return dataType;
    }

    public void setDataType(int dataType)
    {
        this.dataType = dataType;
    }

    public int getLayerID()
    {
        return layerID;
    }

    public void setLayerID(int layerID)
    {
        this.layerID = layerID;
    }

    public int getStyleID()
    {
        return styleID;
    }

    public void setStyleID(int styleID)
    {
        this.styleID = styleID;
    }

}
