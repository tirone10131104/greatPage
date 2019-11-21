package dev.xlinsoft.greatPage.GUI.Drawers;

import dev.xlinsoft.greatPage.Entities.BeanPaperData;
import dev.xlinsoft.greatPage.Progs.PaperDataType;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author lyp
 */
public class PaperEditorDrawer extends JPanel
{

    //绘制图
    private BufferedImage imgBuffer = null;
    //需要绘制的数据列表
    private List les = null;
    private boolean bReImage = false;
    private boolean bRePaint = false;

    public PaperEditorDrawer()
    {

    }

    public void setPaperElements(List _les)
    {
        les = _les;
    }

    public void requestReImage()
    {
        bReImage = true;
        bRePaint = true;
    }

    public void requestRePaint()
    {
        bRePaint = true;
    }

    public void requestPaintEditor()
    {
        repaint();
    }

    @Override
    public void paintComponents(Graphics g)
    {

    }

    @Override
    public void paintComponent(Graphics g)
    {
        paintBufferImage();
        g.drawImage(imgBuffer, 0, 0, this);
    }

    private void paintBufferImage()
    {
//        System.err.println(".paintBufferImage IMG = " + bReImage +" RPT = " + bRePaint);
        if (imgBuffer == null)
        {
            imgBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            bRePaint = true;
            bReImage = false;
        }
        else if (bReImage)
        {
            imgBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            bRePaint = true;
            bReImage = false;
        }
        if (bRePaint)
        {
            Graphics2D g = (Graphics2D) imgBuffer.getGraphics();
            //↓整体绘制过程（开始）
            //10.绘主背景
            Rectangle2D rBack = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
            g.setColor(new Color(0, 0, 0));
            g.fill(rBack);
            if (les != null)
            {
                paintPaperElements(g);
            }
            bRePaint = false;
        }
    }

    private void paintPaperElements(Graphics2D g)
    {
        if (les == null)
        {
            return;
        }
        g.setColor(Color.white);
        Font nft = new Font("宋体", Font.PLAIN, 12);
        g.setFont(nft);
        for (int i = 0; i < les.size(); i++)
        {
            BeanPaperData bean = (BeanPaperData) les.get(i);
            if (bean.getDataType() == PaperDataType.TEXT.value())
            {
                g.drawString(bean.getDataChunk(), (float) bean.getX(), (float) bean.getY());
            }
        }
    }

}
