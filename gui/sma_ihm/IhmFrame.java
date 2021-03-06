/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma_ihm;

import interfaces.IControl;
import interfaces.IInfos;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFileChooser;
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
    private int ligne;
    private int colonne;
    private int robot;
    private int boite;
    private InitialisationDialogue initDialog;
    private static IControl controleur;
    private boolean isOpen = true;
    
    /**
     * Creates new form InterfaceRobot
     */
    public IhmFrame(IControl controle) {
        try {
            System.setProperty( "file.encoding", "UTF-8" );
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        initDialog = new InitialisationDialogue(this, true);
        controleur = controle;
        initDialog.setVisible(true);
    }

    /**
     * Permet de transmettre les configurations nécessaires aux lancements de l'ihm
     * @param nbLigne
     * @param nbColonne
     * @param nbRobot
     * @param nbBoite 
     * @param vitesseAppartionBoite 
     * @param nbApparitionBoite 
     * @throws InterruptedException 
     */
    public void setConfiguration(int nbLigne, int nbColonne, int nbRobot, int nbBoite, int nbApparitionBoite, int vitesseAppartionBoite) throws InterruptedException {
        ligne = nbLigne;
        colonne = nbColonne;
        robot = nbRobot;
        boite = nbBoite;
        controleur.setNombreAgents(robot);
        controleur.setNombreBoites(boite);
        controleur.changeTailleGrille(ligne, colonne);
        controleur.gestionApparitionBoites(nbApparitionBoite, vitesseAppartionBoite);
        initialisationInterface();
    }
    
    public void initialisationInterface () {
    	system = new SystemPanel(this);
        grille = new GrillePanel(ligne, colonne, system);
        jPanel1.add(grille, BorderLayout.CENTER);
        jPanel1.add(system, BorderLayout.EAST);
    }
    
    /**
     * 
     * @param info
     */
    public void setInfoCase(List<IInfos> info) {
        grille.setInfoCase(info);
    }
    
    /**
     * 
     * @param info
     * @param nbColonnes 
     * @param nbLignes 
     */
    public void setInfoCaseInit(List<IInfos> info, int nbLignes, int nbColonnes) {
        ligne = nbLignes;
        colonne = nbColonnes;
        initialisationInterface();
        system.isInitialise();
        grille.setInfoCase(info);
    }
    
    /**
     * 
     * @param info
     */
    public void setInfoCase(IInfos info) {
        grille.setInfoCase(info);
    }
    
    /**
     * 
     */
    public void suicideAgent(IInfos agent) {
    	grille.suicideAgent(agent);
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
	 * @param newPosition 
	 */
	public void setInfoDeplacer(IInfos agent, Position position, IInfos boitePossede, Position newPosition) {
		grille.setInfoDeplacer(agent, position, boitePossede, newPosition);
	}
	
	/**
	 * Methodes permettant l'execution de l'ensemble des actions réalisable sur le system :
	 * - lancer le systeme
	 * - Mettre en pause
	 * - Changer la vitesse
	 * - Mode pas a pas
	 * @param controle
	 */

	public void initialiserSystem() {
		controleur.initialiserSystem();
		controleur.persisterSystem();
	}
    
	/**
	 * Lance l'application
	 */
	public void lancerApplication(){
		controleur.lancerSystem();
	}
	
	/**
	 * Met en pause l'application
	 */
	public void mettreEnPauseApplication(){
		controleur.mettreEnPause(true);
	}
	
	/**
	 * Relance l'application
	 */
	public void relancerApplication() {
		controleur.mettreEnPause(false);
	}
	
	/**
	 * Modifie le temps (en seconde) qui s'ecoule entre deux actions d'un agent
	 */
	public void changerVitesseApplication(int vitesse){
		controleur.changeVitesse(vitesse);
	}
	
	/**
	 * Arrete l'application
	 */
	public void avancerPasAPasApplication(){
		controleur.modePasAPas(true);
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
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1940, 1500));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jMenu1.setText("Menu");

        jMenuItem1.setText("Persistance");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        
        jMenuItem2.setText("Charger");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);

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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        controleur.persisterSystem();
    }
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
    	JFileChooser choix = new JFileChooser();
    	int retour=choix.showOpenDialog(this);
    	String path = "";
    	if(retour==JFileChooser.APPROVE_OPTION){
    	   // chemin absolu du fichier choisi
    	   path = choix.getSelectedFile().getAbsolutePath();
    	}
        controleur.chargerEtatInitial(path);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
