/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma_ihm;

import interfaces.IInfos;

import java.util.List;

import classes.Position;

/**
 *
 * @author mathieu
 */
@SuppressWarnings("serial")
public class SystemPanel extends javax.swing.JPanel {

	private IhmFrame ihmFrame;
    /**
     * Creates new form SystemPanel
     */
    public SystemPanel(IhmFrame pere) {
        initComponents();
        ihmFrame = pere;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        informationPanel2 = new sma_ihm.InformationPanel();
        controlePanel2 = new sma_ihm.ControlePanel(this);

        setLayout(new java.awt.BorderLayout());
        add(informationPanel2, java.awt.BorderLayout.PAGE_END);
        add(controlePanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

	public void updateInformation(List<IInfos> info, Position p) {
	    informationPanel2.updateInformation(info, p);
	    validate();
	}
	
	public void lancerApplication () {
		ihmFrame.lancerApplication();
	}
	
	public void initialiserSystem () {
		ihmFrame.initialiserSystem();
	}
	
	/**
	 * Modifie le temps (en seconde) qui s'ecoule entre deux actions d'un agent
	 */
	public void changerVitesseApplication(int vitesse){
		ihmFrame.changerVitesseApplication(vitesse);
	}
	
	/**
	 * Met en pause l'application
	 */
	public void mettreEnPauseApplication(){
		ihmFrame.mettreEnPauseApplication();
	}
	
	/**
	 * Relance l'application
	 */
	public void relancerApplication() {
		ihmFrame.relancerApplication();
	}
	
	public void isInitialise () {
		controlePanel2.isInitialise();
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sma_ihm.ControlePanel controlePanel2;
    private sma_ihm.InformationPanel informationPanel2;
    // End of variables declaration//GEN-END:variables
}
