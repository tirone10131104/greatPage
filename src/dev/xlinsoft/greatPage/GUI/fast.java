/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.xlinsoft.greatPage.GUI;

import dev.xlin.swingTools2.listItem;
import dev.xlin.swingTools2.myTableModel;
import dev.xlin.tols.interfaces.EnumFormat;
import dev.xlin.tools.EnumTool;
import dev.xlin.tools.codeTools;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Tirone
 */
public class fast
{

    public static final int BOOLEAN_TRUE = 1;
    public static final int BOOLEAN_FALSE = 0;

    public static Object[] makeObjectArray(int size)
    {
        if (size <= 0)
        {
            return null;
        }
        return new Object[size];
    }

    public static final int INT_MIN_VALUE = Integer.MIN_VALUE;

    public static void msg(String info)
    {
        JOptionPane.showMessageDialog(null, info, "信息", JOptionPane.INFORMATION_MESSAGE);
    }

    public static int ask(String info)
    {
        return JOptionPane.showConfirmDialog(null, info, "询问", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    public static void warn(String info)
    {
        JOptionPane.showMessageDialog(null, info, "警告", JOptionPane.WARNING_MESSAGE);
    }

    public static void err(String info)
    {
        JOptionPane.showMessageDialog(null, info, "错误", JOptionPane.ERROR_MESSAGE);
    }

    public static void err(String info, long ecode)
    {
        JOptionPane.showMessageDialog(null, info + "\nCODE:" + ecode, "错误", JOptionPane.ERROR_MESSAGE);
    }

    public static String input(String info)
    {
        return JOptionPane.showInputDialog(null, info, "请输入：", JOptionPane.INFORMATION_MESSAGE);
    }

    public static String input(String stt, String sdv)
    {
        return JOptionPane.showInputDialog(null, stt, sdv);
    }

    public static void mainLog(String s)
    {
        System.err.println(codeTools.ConvertDatetimeToString(new Date()) + ":" + s);
    }

    /**
     * 读取复选框的常数值
     *
     * @param jck
     * @return
     */
    public static int readCheckBox(JCheckBox jck)
    {
        if (jck.isSelected())
        {
            return BOOLEAN_TRUE;
        }
        else
        {
            return BOOLEAN_FALSE;
        }
    }

    /**
     * 根据值设置复选框状态
     *
     * @param jck
     * @param val
     */
    public static void setCheckBoxValue(JCheckBox jck, int val)
    {
        if (val == BOOLEAN_TRUE)
        {
            jck.setSelected(true);
        }
        else
        {
            jck.setSelected(false);
        }
    }

    public static void setTextFieldSelectAll(JTextField jtx)
    {
        String s = jtx.getText();
        if (s.equals(""))
        {
            return;
        }
        else
        {
            jtx.setSelectionStart(0);
            jtx.setSelectionEnd(s.length());
        }
    }

    public static final int YES = JOptionPane.YES_OPTION;

//    /**
//     * 初始化下拉列表
//     *
//     * @param begin
//     * @param comb
//     */
//    public static void initConstCombo(String begin, JComboBox comb)
//    {
//        int[] ids = constChk.getFinalInts(iConst.class, begin);
//        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
//        if (ids != null)
//        {
//            for (int i = 0; i < ids.length; i++)
//            {
//                int id = ids[i];
//                listItem li = new listItem(iConst.translate(id), id);
//                dcbm.addElement(li);
//            }
//        }
//        comb.setModel(dcbm);
//    }
    /**
     * 从组合下拉列表中读取所选项的整数值。
     *
     * @param cmb
     * @return
     */
    public static int readCombo(JComboBox cmb)
    {
        if (cmb.getSelectedIndex() < 0)
        {
            return COMBO_VALUE_ERROR;
        }
        Object o = cmb.getSelectedItem();
        if (o.getClass() != listItem.class)
        {
            return COMBO_VALUE_ERROR;
        }
        try
        {
            listItem li = (listItem) o;
            int iv = (Integer) li.getListUserInfo();
            return iv;
        }
        catch (Exception ex)
        {
            return COMBO_VALUE_ERROR;
        }
    }

    /**
     *
     * @param txt
     */
    public static void doSelectTextBox(JTextField txt)
    {
        txt.setSelectionStart(0);
        txt.setSelectionEnd(txt.getText().length());
    }

    public static double testDoubleText(JTextField txt)
    {
        try
        {
            String s = txt.getText().trim();
            double dv = Double.parseDouble(s);
            return dv;
        }
        catch (Exception excp)
        {
            txt.setText("0");
            return Double.MIN_VALUE;
        }
    }

    public static long testLongText(JTextField txt)
    {
        try
        {
            String s = txt.getText().trim();
            long lv = Long.parseLong(s);
            return lv;
        }
        catch (Exception excp)
        {
            txt.setText("0");
            return Long.MIN_VALUE;
        }
    }

    public static int testIntegerText(JTextField txt)
    {
        try
        {
            String s = txt.getText().trim();
            int iv = Integer.parseInt(s);
            return iv;
        }
        catch (Exception excp)
        {
            txt.setText("0");
            return Integer.MIN_VALUE;
        }
    }

    public static int testIntegerText(JTextField txt, int errDefault)
    {
        try
        {
            String s = txt.getText().trim();
            int iv = Integer.parseInt(s);
            return iv;
        }
        catch (Exception excp)
        {
            txt.setText(errDefault + "");
            return errDefault;
        }
    }

    public static void clearTableModel(JTable tb)
    {
        try
        {
            myTableModel mtm = (myTableModel) tb.getModel();
            int ict = mtm.getRowCount();
            for (int i = 0; i < ict; i++)
            {
                mtm.removeRow(0);
            }
        }
        catch (Exception excp)
        {
            return;
        }
    }

    public static ArrayList makeIntArrayList(int[] ids)
    {
        ArrayList arl = new ArrayList();
        if (ids == null)
        {
            return arl;
        }
        for (int i = 0; i < ids.length; i++)
        {
            int id = ids[i];
            arl.add(id);
        }
        return arl;
    }

    public static final int COMBO_VALUE_ERROR = -999999999;

    public static double makeRound2(double dv)
    {
        return Math.round(dv * 100) / 100.0;
    }

    public static DefaultComboBoxModel makeComboBoxModelByEnum(Class cls)
    {
        DefaultComboBoxModel mod = new DefaultComboBoxModel();
        EnumFormat[] efs = EnumTool.valueArray(cls);
        for (int i = 0; i < efs.length; i++)
        {
            EnumFormat ef = efs[i];
            mod.addElement(new listItem(ef.label(), ef.value()));
        }
        return mod;
    }

}
