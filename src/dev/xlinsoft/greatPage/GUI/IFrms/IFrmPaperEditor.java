package dev.xlinsoft.greatPage.GUI.IFrms;

import dev.xlin.tols.data.wakeup;
import dev.xlinsoft.greatPage.Entities.BeanPaperData;
import dev.xlinsoft.greatPage.GUI.Drawers.PaperEditorDrawer;
import dev.xlinsoft.greatPage.GUI.fast;
import dev.xlinsoft.greatPage.Progs.PaperDataType;
import dev.xlinsoft.greatPage.Progs.PaperService;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 *
 * @author 22972
 */
public class IFrmPaperEditor extends javax.swing.JInternalFrame
{

    private wakeup up = null;
    private int paperID = 0;
    //窗口视口X,Y的坐标
    private double viewX = 0;
    private double viewY = 0;

    public IFrmPaperEditor(wakeup _up, int _paperID)
    {
        initComponents();
        up = _up;
        paperID = _paperID;
        initGUI();
    }

    private void initGUI()
    {
        sendDataToDrawer(true);
    }

    //发送数据到DRAWER
    //rimg,T为指定重绘图，F为不重置
    private void sendDataToDrawer(boolean rimg)
    {
        PaperService psrv = new PaperService(up);
        List les = psrv.getElementsByRectangle(paperID, viewX, viewY, palEditor.getWidth(), palEditor.getHeight());
        PaperEditorDrawer pedr = (PaperEditorDrawer) palEditor;
        //设置待绘制数据
        pedr.setPaperElements(les);
        //设置编辑器绘制动作
        if (rimg)
        {
            pedr.requestReImage();
        }
        else
        {
            pedr.requestRePaint();
        }
        //重绘
        pedr.requestPaintEditor();
    }

    private void requestReImage()
    {
        PaperEditorDrawer pedr = (PaperEditorDrawer) palEditor;
        pedr.requestReImage();
        pedr.requestPaintEditor();
    }

    //要求重绘画面
    private void requestRepaint()
    {
        PaperEditorDrawer pedr = (PaperEditorDrawer) palEditor;
        pedr.requestRePaint();
        pedr.requestPaintEditor();
    }

    private void newText(int scrX, int scrY)
    {
        String sip = fast.input("");
        if (sip == null )
        {
            return ; 
        }
        if (sip.trim().isEmpty())
        {
            return ; 
        }
        PaperService psrv = new PaperService(up);
        BeanPaperData bean = new BeanPaperData();
        bean.setDataChunk(sip);
        bean.setX(scrX);
        bean.setY(scrY);
        bean.setDataType(PaperDataType.TEXT.value());
        bean.setPaperID(paperID);
        Graphics2D g = (Graphics2D) palEditor.getGraphics();
        Font nft = new Font("宋体", Font.PLAIN, 12);
        g.setFont(nft);
        FontMetrics fms = g.getFontMetrics(nft);
        Rectangle2D rstr = fms.getStringBounds(sip.trim(), g);
        if (rstr!= null)
        {
            bean.setWid(rstr.getWidth());
            bean.setHet(rstr.getHeight());
        }
        //插入数据
        int r = psrv.addPaperElement(bean);
        if (r > 0 )
        {
            sendDataToDrawer(false);
        }
    }

    private void dealMouseClick(MouseEvent evt)
    {
        if (evt.getButton() == MouseEvent.BUTTON1)
        {
            if (evt.getClickCount() >= 2)
            {
                //添加
                newText(evt.getX() , evt.getY());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        palEditor = new PaperEditorDrawer();
        jToolBar1 = new javax.swing.JToolBar();
        btnTestRepaint = new javax.swing.JButton();
        btnSDT = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 0));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                formComponentResized(evt);
            }
        });

        palEditor.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                palEditorMouseClicked(evt);
            }
        });
        palEditor.addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                palEditorComponentResized(evt);
            }
        });

        javax.swing.GroupLayout palEditorLayout = new javax.swing.GroupLayout(palEditor);
        palEditor.setLayout(palEditorLayout);
        palEditorLayout.setHorizontalGroup(
            palEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        palEditorLayout.setVerticalGroup(
            palEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnTestRepaint.setText("RPT");
        btnTestRepaint.setToolTipText("");
        btnTestRepaint.setFocusable(false);
        btnTestRepaint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTestRepaint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTestRepaint.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnTestRepaintActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTestRepaint);

        btnSDT.setText("SDT");
        btnSDT.setFocusable(false);
        btnSDT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSDT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSDT.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSDTActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSDT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(palEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(palEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTestRepaintActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnTestRepaintActionPerformed
    {//GEN-HEADEREND:event_btnTestRepaintActionPerformed
        requestRepaint();
    }//GEN-LAST:event_btnTestRepaintActionPerformed

    private void palEditorComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_palEditorComponentResized
    {//GEN-HEADEREND:event_palEditorComponentResized
        requestReImage();
    }//GEN-LAST:event_palEditorComponentResized

    private void btnSDTActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSDTActionPerformed
    {//GEN-HEADEREND:event_btnSDTActionPerformed

    }//GEN-LAST:event_btnSDTActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_formComponentResized
    {//GEN-HEADEREND:event_formComponentResized
        sendDataToDrawer(true);
    }//GEN-LAST:event_formComponentResized

    private void palEditorMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_palEditorMouseClicked
    {//GEN-HEADEREND:event_palEditorMouseClicked
        dealMouseClick(evt);
    }//GEN-LAST:event_palEditorMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSDT;
    private javax.swing.JButton btnTestRepaint;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel palEditor;
    // End of variables declaration//GEN-END:variables
}
