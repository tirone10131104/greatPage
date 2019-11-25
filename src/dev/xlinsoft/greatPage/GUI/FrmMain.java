package dev.xlinsoft.greatPage.GUI;

import dev.xlin.tols.data.wakeup;
import dev.xlinsoft.greatPage.GUI.IFrms.IFrmPaperEditor;
import dev.xlinsoft.greatPage.Progs.dataconn;
import javax.swing.JFrame;

/**
 *
 * @author lyp
 */
public class FrmMain extends javax.swing.JFrame
{

    private wakeup up = null;

    public FrmMain()
    {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        up = dataconn.connectRemoteDB__120();
        testOpenPED();
    }

    private void testOpenPED()
    {
        IFrmPaperEditor ifPED = new IFrmPaperEditor(up, 0);
        desktop.add(ifPED);
        ifPED.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        desktop = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        btnTestPaper = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktop.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 786, Short.MAX_VALUE)
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnTestPaper.setText("TEST-PAP");
        btnTestPaper.setFocusable(false);
        btnTestPaper.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTestPaper.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTestPaper.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnTestPaperActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTestPaper);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1243, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktop))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTestPaperActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnTestPaperActionPerformed
    {//GEN-HEADEREND:event_btnTestPaperActionPerformed
        testOpenPED();
    }//GEN-LAST:event_btnTestPaperActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new FrmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTestPaper;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
