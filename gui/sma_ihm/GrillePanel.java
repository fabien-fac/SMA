/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma_ihm;

import interfaces.IInfos;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import classes.Position;

/**
 *
 * @author mathieu
 */
@SuppressWarnings("serial")
public class GrillePanel extends javax.swing.JPanel {
    private int nbLigne;
    private int nbColonne;
    private Dimension dimensionCase;
    private static CasePanel casePanelTable[][];
    /**
     * Creates new form GrillePanel
     */
    public GrillePanel(int lignes, int colonnes, SystemPanel panel) {
        initComponents();
        nbLigne = lignes;
        nbColonne = colonnes;
        dimensionCase = calculTailleCase();
        setLayout(new GridLayout(lignes, colonnes, 0, 0));
        casePanelTable = new CasePanel[nbLigne][nbColonne];
        for(int colonne = 0; colonne < nbColonne; colonne ++) {
            for(int ligne = 0; ligne < nbLigne; ligne ++) {
                casePanelTable[ligne][colonne] = new CasePanel(dimensionCase, ligne, colonne,panel);
                add(casePanelTable[ligne][colonne]);
            }
        }
    }
    
    private Dimension calculTailleCase() {
        return new Dimension(this.getWidth()/nbLigne, this.getHeight()/nbColonne);
    }
    
    public void setDimensionGrille(int lignes, int colonne) {
        
    }
    
    public void setInfoCase(List<IInfos> info) {
    	for (IInfos iInfos : info) {
    		Position p = iInfos.getPosition();
            casePanelTable[p.getX()][p.getY()].setInfos(iInfos);
		}
    }
    
    public void setInfoCase(IInfos info) {
    		Position p = info.getPosition();
            casePanelTable[p.getX()][p.getY()].setInfos(info);
    }
    
    /**
     * Indique à l'ihm qu'un robot prend une boite
     * @param agent
     * @param boite
     */
    public void setInfoPrendreBoite(IInfos agent, IInfos boite) {
    	Position p = agent.getPosition();
    	casePanelTable[p.getX()][p.getY()].setInfoPrendreBoite(agent, boite);
	}
	
    /**
     * Indique à l'ihm qu'un robot dépose une boite dans un nid
     * @param agent
     * @param boite
     * @param nid
     */
	public void setInfoDeposerBoite(IInfos agent, IInfos boite, IInfos nid) {
		Position p = agent.getPosition();
    	casePanelTable[p.getX()][p.getY()].setInfoDeposerBoite(agent, boite, nid);
	}
	
	/**
	 * Indique à l'ihm qu'un robot se déplace
	 * @param agent
	 * @param position
	 */
	public void setInfoDeplacer(IInfos agent, Position position, IInfos boitePossede) {
		Position p = agent.getPosition();
		if(boitePossede != null) {
			String couleurBoite = casePanelTable[p.getX()][p.getY()].getBoiteCouleurs();
			if(casePanelTable[p.getX()][p.getY()].containsBoite()) {
				casePanelTable[p.getX()][p.getY()].effacerTraceRobot();;
		    	casePanelTable[position.getX()][position.getY()].setInfoDeplacerRobotBoite(agent,couleurBoite);
			} else {
				casePanelTable[p.getX()][p.getY()].effacerTraceRobotBoite();
		    	casePanelTable[position.getX()][position.getY()].setInfoDeplacerRobotBoite(agent,couleurBoite);
			}
		} else {
			casePanelTable[p.getX()][p.getY()].effacerTraceRobot();
	    	casePanelTable[position.getX()][position.getY()].setInfoDeplacerRobot(agent);
		}
    	
	}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(1, 0));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
