/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma_ihm;

import interfaces.IInfos;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.UIManager;

import classes.Position;

/**
 *
 * @author mathieu
 */
@SuppressWarnings("serial")
public class IhmFrame extends javax.swing.JFrame {

    private GrillePanel grille;
    private static SystemPanel system;
    private int ligne = 50;
    private int colonne = 50;
    private int robot;
    private InitialisationDialogue initDialog;
    private static IhmFrame ihmFrame;
    /**
     * Creates new form InterfaceRobot
     */
    public IhmFrame() {
        try {
            System.setProperty( "file.encoding", "UTF-8" );
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        system = new SystemPanel(ihmFrame);
        grille = new GrillePanel(ligne, colonne, system);
        jPanel1.add(grille, BorderLayout.CENTER);
        jPanel1.add(system, BorderLayout.EAST);
        initDialog = new InitialisationDialogue(this, true);
        
    }

    /**
     * Permet de transmettre les configurations nécessaires aux lancements de l'ihm
     * @param nbLigne
     * @param nbColonne
     * @param nbRobot
     */
    public void setConfiguration(int nbLigne, int nbColonne, int nbRobot) {
        ligne = nbLigne;
        colonne = nbColonne;
        robot = nbRobot;
    }
    
    /**
     * 
     * @param info
     */
    public void setInfoCase(List<IInfos> info) {
        grille.setInfoCase(info);
    }
    
    /**
     * Indique à l'ihm qu'un robot prend une boite
     * @param agent
     * @param boite
     */
    public void setInfoPrendreBoite(IInfos agent, IInfos boite) {
		grille.setInfoPrendreBoite(agent, boite);
	}
	
    /**
     * Indique à l'ihm qu'un robot dépose une boite dans un nid
     * @param agent
     * @param boite
     * @param nid
     */
	public void setInfoDeposerBoite(IInfos agent, IInfos boite, IInfos nid) {
		grille.setInfoDeposerBoite(agent, boite, nid);
	}
	
	/**
	 * Indique à l'ihm qu'un robot se déplace
	 * @param agent
	 * @param position
	 */
	public void setInfoDeplacer(IInfos agent, Position position, boolean possedeBoite) {
		grille.setInfoDeplacer(agent, position, possedeBoite);
	}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ramasse les boites");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1940, 1500));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jMenu1.setText("Menu");

        jMenuItem1.setText("Initialisation");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1970, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.out.println("Creation de la grille de taille");
        initDialog.setLocationRelativeTo(ihmFrame);
        initDialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IhmFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IhmFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IhmFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IhmFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ihmFrame = new IhmFrame();
                ihmFrame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
