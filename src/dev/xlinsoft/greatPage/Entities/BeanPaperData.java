package dev.xlinsoft.greatPage.Entities;

import dev.xlin.tols.data.annotations.JDBId;
import dev.xlin.tols.data.annotations.JDBNoSave;
import dev.xlin.tols.data.annotations.JDBNoUpdate;
import dev.xlin.tols.data.annotations.JDBTable;
import java.awt.geom.Rectangle2D;

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
    private int orderId = 0;
    @JDBNoSave
    @JDBNoUpdate
    private Rectangle2D.Double borderRect = null;

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

    public Rectangle2D.Double getBorderRect()
    {
        return borderRect;
    }

    public void setBorderRect(Rectangle2D.Double borderRect)
    {
        this.borderRect = borderRect;
    }

    public int getOrderId()
    {
        return orderId;
    }

    public void setOrderId(int orderId)
    {
        this.orderId = orderId;
    }

}
