/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma_ihm;

import interfaces.IInfos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import classes.Position;
import enums.Couleurs;
import enums.Types;


/**
 *
 * @author mathieu
 */
@SuppressWarnings("serial")
public class CasePanel extends javax.swing.JPanel {

    private Dimension dimensionPanel;
    private final int ordonneeCase;
    private final int abscisseCase;
    private SystemPanel test;
    private List<IInfos> infoList;
    private RobotLabel robotLabel;
    private BoiteLabel boiteLabel;
    private BoiteLabel boiteLabel2;
    private int indexBoiteNonPorte;

    /**
     * Creates new form CasePanel
     */
    public CasePanel(Dimension dimPanel, int abscisse, int ordonnee, SystemPanel system ) {
        initComponents();
        abscisseCase = abscisse;
        ordonneeCase = ordonnee;
        dimensionPanel = dimPanel;
        setSize(dimensionPanel);
        setBackground(Color.white);
        test = system;
        infoList = new ArrayList<IInfos>();
        robotLabel = new RobotLabel();
        boiteLabel = new BoiteLabel();
        boiteLabel2 = new BoiteLabel();
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(boiteLabel);
        add(robotLabel);
    }

    public void setInfos(IInfos info) {
    	IInfos tmp = info;
		infoList.add(tmp);
        if (info.getType().equals(Types.AGENT.toString())) {
            robotLabel.setColorLabel(info.getCouleur());
            robotLabel.repaint();
        } else if (info.getType().equals(Types.BOITE.toString())) {
            boiteLabel.setColorLabel(info.getCouleur());
            boiteLabel.repaint();
        } else if (info.getType().equals(Types.NID.toString())) {
            if(info.getCouleur().equals(Couleurs.ROUGE.toString())) {
                    setBackground(Color.RED);
            } else if(info.getCouleur().equals(Couleurs.BLEU.toString())) {
                    setBackground(Color.BLUE);
            } else if(info.getCouleur().equals(Couleurs.VERT.toString())) {
                    setBackground(Color.GREEN);
            }
        }
        validate();
    }
    
	public void setInfoPrendreBoite(IInfos agent, IInfos boite) {
		robotLabel.setColorLabel(agent.getCouleur());
		boiteLabel.viderLabel();
		validate();
	}
	
	public void setInfoDeposerBoite(IInfos agent, IInfos boite, IInfos nid) {
		robotLabel.setColorLabel(agent.getCouleur());
		boiteLabel.viderLabel();
		validate();
	}
	
	public void setInfoDeplacerRobot(IInfos agent) {
		robotLabel.setColorLabel(agent.getCouleur());
		infoList.add(agent);
		validate();
	}
	
	public void setInfoDeplacerRobotBoite(IInfos agent, IInfos boite) {
		robotLabel.setColorLabel(agent.getCouleur());
		infoList.add(agent);
		/* S'il n'y a qu'une seule boite dans la case */
		if(indexBoiteNonPorte == -1){
			boiteLabel.setColorLabel(boite.getCouleur());
		/* S'il y a deux boite dans la case */
		} else {
			boiteLabel2.setColorLabel(boite.getCouleur());
		}
		infoList.add(boite);
		
		validate();
	}
	
	public void effacerTraceRobot () {
		robotLabel.viderLabel();
		validate();
	}
	
	public void effacerTraceRobotBoite () {
		robotLabel.viderLabel();
		/* S'il n'y a qu'une seule boite dans la case */
		if(indexBoiteNonPorte == -1){
			boiteLabel.viderLabel();
		/* S'il y a deux boite dans la case */
		} else {
			boiteLabel2.viderLabel();
		}
		
		validate();
	}
	
	public boolean containsBoite() {
		int i=0;
		for (IInfos iInfos : infoList) {
			if(iInfos.getType().equals(Types.BOITE.toString())) {
				indexBoiteNonPorte = i;
				return true;
			}
			i++;
		}
		indexBoiteNonPorte=-1;
		return false;
	}
	
	public String getBoiteCouleurs () {
		for (IInfos iInfos : infoList) {
			if(iInfos.getType().equals(Types.BOITE.toString())) {
				return iInfos.getCouleur();
			}
		}
		return null;
	}

	public void suicideAgent(IInfos agent) {
		effacerTraceRobot();
	}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        test.updateInformation(infoList, new Position(abscisseCase,ordonneeCase));
    }//GEN-LAST:event_formMouseReleased



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
