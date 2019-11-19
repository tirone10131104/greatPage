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

}
