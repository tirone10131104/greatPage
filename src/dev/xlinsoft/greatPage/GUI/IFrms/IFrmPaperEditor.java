package dev.xlinsoft.greatPage.GUI.IFrms;

import dev.xlin.tols.data.wakeup;
import dev.xlinsoft.greatPage.Entities.BeanPaperData;
import dev.xlinsoft.greatPage.GUI.Drawers.PaperEditorDrawer;
import dev.xlinsoft.greatPage.GUI.fast;
import dev.xlinsoft.greatPage.Progs.PaperDataType;
import dev.xlinsoft.greatPage.Progs.PaperService;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
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
    //鼠标左键按下
    private boolean lbDown = false;
    //鼠标右键按下
    private boolean rbDown = false;
    private boolean lCtrlDown = false;
    private boolean lAltDown = false;
    //拖动前坐标
    private double ox = 0;
    private double oy = 0;

    //选择框原点
    private double selOX = 0;
    private double selOY = 0;

    private List ldata = null;

    private List lSelection = new ArrayList();

    private Rectangle2D selectRect = null;

    public IFrmPaperEditor(wakeup _up, int _paperID)
    {
        initComponents();
        up = _up;
        paperID = _paperID;
        initGUI();
    }

    private void initGUI()
    {
        preLoadData();
        sendDataToDrawer(true);
        palEditor.requestFocus();
    }

    //初次导入纸片上数据集
    private void preLoadData()
    {
        PaperService psrv = new PaperService(up);
        ldata = psrv.getPaperElements(paperID);
        if (ldata == null)
        {
            ldata = new ArrayList();
        }
        //尝试恢复外接矩形和DATA内容对象
        for (int i = 0; i < ldata.size(); i++)
        {
            BeanPaperData bpp = (BeanPaperData) ldata.get(i);
            bpp.setBorderRect(makeDataBorderRect2D(bpp));
        }
    }

    /**
     * 根据数据bean，制作一个外接矩形
     *
     * @param bpd
     * @return
     */
    private Rectangle2D.Double makeDataBorderRect2D(BeanPaperData bpd)
    {
        Rectangle2D.Double rect = null;
        if (bpd.getDataType() == PaperDataType.TEXT.value())
        {
            rect = new Rectangle2D.Double(bpd.getX(), bpd.getY(), bpd.getWid(), bpd.getHet());
        }
        return rect;
    }

    //发送数据到DRAWER
    //rimg,T为指定重绘图，F为不重置
    private void sendDataToDrawer(boolean rimg)
    {
        PaperEditorDrawer pedr = (PaperEditorDrawer) palEditor;
        //设置待绘制数据
        pedr.setPaperElements(ldata);
        //设置编辑器绘制动作
        if (rimg)
        {
            pedr.requestReImage();
        }
        else
        {
            pedr.requestRePaint();
        }
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

    private void requestChangeViewPort()
    {
        PaperEditorDrawer pedr = (PaperEditorDrawer) palEditor;
        pedr.changeViewPort(viewX, viewY);
    }

    //应答选择纸上数据节点
    private void dealSingleDataSelection(double x, double y)
    {
        BeanPaperData bpd = trySelectData(x, y);
        PaperEditorDrawer pedr = (PaperEditorDrawer) palEditor;
        //处理数据选择列表
        processDataToSelection(bpd);
        pedr.setSelection(lSelection);
        requestRepaint();
    }

    //根据按键状态，决定加减数据到选择列表
    private void processDataToSelection(BeanPaperData bpd)
    {
        if (bpd == null)
        {
            if (lCtrlDown == false && lAltDown == false)
            {
                lSelection.clear();
            }
        }
        else
        {
            if (lCtrlDown == false && lAltDown == false)
            {
                if (selectRect == null)
                {
                    //非框选模式，则为单选，需要预先清除所有已选
                    lSelection.clear();
                }
                if (lSelection.contains(bpd.getOID()) == false)
                {
                    lSelection.add(bpd.getOID());
                }
            }
            if (lCtrlDown)
            {
                //追加
                if (lSelection.contains(bpd.getOID()) == false)
                {
                    lSelection.add(bpd.getOID());
                }
            }
            if (lAltDown)
            {
                //剔除
                if (lSelection.contains(bpd.getOID()))
                {
                    for (int i = 0; i < lSelection.size(); i++)
                    {
                        int oid = (int) lSelection.get(i);
                        if (oid == bpd.getOID())
                        {
                            lSelection.remove(i);
                            break;
                        }
                    }
                }
            }
        }
    }

    //通过X,Y尝试选择一个数据
    private BeanPaperData trySelectData(double x, double y)
    {
        Point2D p = transformToRealCoordinate(x, y);
        //传入参数XY为屏幕XY，转换为实体XY 
        for (int i = 0; i < ldata.size(); i++)
        {
            BeanPaperData bpd = (BeanPaperData) ldata.get(i);
            if (bpd.getBorderRect().contains(p))
            {
                return bpd;
            }
        }
        return null;
    }

    private Point2D.Double transformToRealCoordinate(double x, double y)
    {
        Point2D.Double rpt = new Point2D.Double(x + viewX, y + viewY);
        return rpt;
    }

    private void newText(int scrX, int scrY)
    {
        String sip = fast.input("");
        if (sip == null)
        {
            return;
        }
        if (sip.trim().isEmpty())
        {
            return;
        }
        PaperService psrv = new PaperService(up);
        BeanPaperData bean = new BeanPaperData();
        bean.setDataChunk(sip);
        bean.setX(scrX + viewX);
        bean.setY(scrY + viewY);
        bean.setDataType(PaperDataType.TEXT.value());
        bean.setPaperID(paperID);
        Graphics2D g = (Graphics2D) palEditor.getGraphics();
        Font nft = new Font("宋体", Font.PLAIN, 12);
        g.setFont(nft);
        FontMetrics fms = g.getFontMetrics(nft);
        Rectangle2D rstr = fms.getStringBounds(sip.trim(), g);
        if (rstr != null)
        {
            bean.setWid(rstr.getWidth());
            bean.setHet(rstr.getHeight());
        }
        //插入数据
        int r = psrv.addPaperElement(bean);
        if (r > 0)
        {
            System.err.println(".a..oid = " + bean.getOID() + " idx = " + bean.getOrderId());
            bean.setOID(r);
            bean.setBorderRect(makeDataBorderRect2D(bean));
            ldata.add(bean);
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
                newText(evt.getX(), evt.getY());
                return;
            }
            else
            {
                if (selectRect == null)
                {
                    dealSingleDataSelection(evt.getX(), evt.getY());
                }
            }
        }
    }

    //鼠标按下
    private void dealMousePress(MouseEvent evt)
    {
        if (evt.getButton() == MouseEvent.BUTTON3)
        {
            rbDown = true;
            ox = evt.getX();
            oy = evt.getY();
        }
        else if (evt.getButton() == MouseEvent.BUTTON1)
        {
            //框选按钮压下。启用选择框
            selOX = evt.getX();
            selOY = evt.getY();
            selectRect = new Rectangle2D.Double(selOX, selOY, 0, 0);
            sendSelectionRect(selectRect);
            requestRepaint();
            lbDown = true;
        }
    }

    private void dealMouseDrag(MouseEvent evt)
    {
        if (lbDown)
        {
            //分发处理：---> 拖动选择框
            dealRectangleSelectDragged(evt);
        }
        if (rbDown)
        {
            //非框选模式。根据编辑状态进行分发
            //左键按下
            double ofx = evt.getX() - ox;
            double ofy = evt.getY() - oy;
            viewX = viewX - ofx;
            viewY = viewY - ofy;
            requestChangeViewPort();
            requestRepaint();
            ox = evt.getX();
            oy = evt.getY();

        }
    }

    private void dealRectangleSelectDragged(MouseEvent evt)
    {
        double nx = 0 ;
        double ny = 0 ;
        double w = 0 ;
        double h = 0 ; 
        if (selOX < evt.getX())
        {
            nx = selOX;
            w = evt.getX() - selOX;
        }
        else
        {
            nx = evt.getX();
            w = selOX - evt.getX();
        }
        if (selOY < evt.getY())
        {
            ny = selOY;
            h = evt.getY() - selOY;
        }
        else
        {
            ny = evt.getY();
            h = selOY - evt.getY();
        }
        selectRect = new Rectangle2D.Double(nx, ny, w, h);
        sendSelectionRect(selectRect);
        //进行框选
        PaperEditorDrawer pder = (PaperEditorDrawer) palEditor;
        Rectangle2D rectReal = pder.transformRectToReal(selectRect);
        for (int i = 0; i < ldata.size(); i++)
        {
            BeanPaperData bpd = (BeanPaperData) ldata.get(i);
            if (bpd.getBorderRect() != null)
            {
                if (bpd.getBorderRect().intersects(rectReal) || rectReal.contains(bpd.getBorderRect()))
                {
                    processDataToSelection(bpd);
                }
            }
        }
        //剔除掉框选区域以外的
        for (int i = 0; i < lSelection.size(); i++)
        {
            int oid = (int) lSelection.get(i);
            BeanPaperData bean = getPaperDataByOID(oid);
            if (bean.getBorderRect().intersects(rectReal) == false && rectReal.contains(bean.getBorderRect()) == false)
            {
                lSelection.remove(i);
                i--;
            }
        }
        pder.setSelection(lSelection);
        requestRepaint();
    }

    private BeanPaperData getPaperDataByOID(int oid)
    {
        for (int i = 0; i < ldata.size(); i++)
        {
            BeanPaperData bpd = (BeanPaperData) ldata.get(i);
            if (bpd.getOID() == oid)
            {
                return bpd;
            }
        }
        return null;
    }

    private void dealMouseReleased(MouseEvent evt)
    {
        lbDown = false;
        rbDown = false;
        //重新读取数据，然后重绘 
        //框选按钮压下状态，松开鼠标，则清除框选
        if (selectRect != null)
        {
            selectRect = null;
            sendSelectionRect(selectRect);
        }
        requestRepaint();
    }

    private void sendSelectionRect(Rectangle2D rect)
    {
        PaperEditorDrawer pedr = (PaperEditorDrawer) palEditor;
        pedr.setSelectionRectangle(rect);
    }

    private void dealKeyPressed(KeyEvent evt)
    {
        if (evt.getKeyCode() == KeyEvent.VK_CONTROL)
        {
            lCtrlDown = true;
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ALT)
        {
            lAltDown = true;
        }
    }

    private void dealKeyReleased(KeyEvent evt)
    {
        if (evt.getKeyCode() == KeyEvent.VK_CONTROL)
        {
            lCtrlDown = false;
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ALT)
        {
            lAltDown = false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        palEditor = new PaperEditorDrawer();
        jToolBar1 = new javax.swing.JToolBar();
        btnMove = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

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
        addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                formKeyReleased(evt);
            }
        });

        palEditor.setFocusCycleRoot(true);
        palEditor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {
            public void mouseDragged(java.awt.event.MouseEvent evt)
            {
                palEditorMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt)
            {
                palEditorMouseMoved(evt);
            }
        });
        palEditor.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                palEditorMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                palEditorMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                palEditorMouseReleased(evt);
            }
        });
        palEditor.addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                palEditorComponentResized(evt);
            }
        });
        palEditor.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                palEditorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                palEditorKeyReleased(evt);
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
            .addGap(0, 751, Short.MAX_VALUE)
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnMove.setText("移动");
        btnMove.setFocusable(false);
        btnMove.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMove.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnMove);

        jButton1.setText("删除");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(palEditor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1598, Short.MAX_VALUE)
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

    private void palEditorComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_palEditorComponentResized
    {//GEN-HEADEREND:event_palEditorComponentResized
        requestReImage();
    }//GEN-LAST:event_palEditorComponentResized

    private void formComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_formComponentResized
    {//GEN-HEADEREND:event_formComponentResized
        sendDataToDrawer(true);
    }//GEN-LAST:event_formComponentResized

    private void palEditorMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_palEditorMouseClicked
    {//GEN-HEADEREND:event_palEditorMouseClicked
        dealMouseClick(evt);
    }//GEN-LAST:event_palEditorMouseClicked

    private void palEditorMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_palEditorMousePressed
    {//GEN-HEADEREND:event_palEditorMousePressed
        dealMousePress(evt);
    }//GEN-LAST:event_palEditorMousePressed

    private void palEditorMouseDragged(java.awt.event.MouseEvent evt)//GEN-FIRST:event_palEditorMouseDragged
    {//GEN-HEADEREND:event_palEditorMouseDragged
        dealMouseDrag(evt);
    }//GEN-LAST:event_palEditorMouseDragged

    private void palEditorMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_palEditorMouseReleased
    {//GEN-HEADEREND:event_palEditorMouseReleased
        dealMouseReleased(evt);
    }//GEN-LAST:event_palEditorMouseReleased

    private void palEditorKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_palEditorKeyPressed
    {//GEN-HEADEREND:event_palEditorKeyPressed
        dealKeyPressed(evt);
    }//GEN-LAST:event_palEditorKeyPressed

    private void palEditorKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_palEditorKeyReleased
    {//GEN-HEADEREND:event_palEditorKeyReleased
        dealKeyReleased(evt);
    }//GEN-LAST:event_palEditorKeyReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_formKeyPressed
    {//GEN-HEADEREND:event_formKeyPressed
        dealKeyPressed(evt);
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_formKeyReleased
    {//GEN-HEADEREND:event_formKeyReleased
        dealKeyReleased(evt);
    }//GEN-LAST:event_formKeyReleased

    private void palEditorMouseMoved(java.awt.event.MouseEvent evt)//GEN-FIRST:event_palEditorMouseMoved
    {//GEN-HEADEREND:event_palEditorMouseMoved
        palEditor.requestFocusInWindow();
    }//GEN-LAST:event_palEditorMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMove;
    private javax.swing.JButton jButton1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel palEditor;
    // End of variables declaration//GEN-END:variables
}
