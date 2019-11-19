package dev.xlinsoft.greatPage.Progs;

import dev.xlin.tols.interfaces.EnumFormat;
import dev.xlin.tools.EnumTool;

/**
 *
 * @author 22972
 */
public enum PaperDataType implements EnumFormat
{
    TEXT("文本", 1);
    private String lab = "";
    private int csid = 0;

    private PaperDataType(String s, int id)
    {
        lab = s;
        csid = id;
    }

    @Override
    public int value()
    {
        return csid;
    }

    @Override
    public String label()
    {
        return lab;
    }

    @Override
    public boolean isEnumValue(int i)
    {
        return EnumTool.isEnumValue(this.getClass(), i);
    }

    @Override
    public int getMinValue()
    {
        return EnumTool.getEnumMinValue(this.getClass());
    }

    @Override
    public int getMaxValue()
    {
        return EnumTool.getEnumMaxValue(this.getClass());
    }
}
