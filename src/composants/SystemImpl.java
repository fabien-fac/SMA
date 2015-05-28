package composants;

import interfaces.IControl;
import interfaces.IInfos;
import interfaces.IInfosGrille;
import interfaces.IPercevoir;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import SMA.System;
import classes.Case;
import classes.Position;
import enums.Couleurs;

public class SystemImpl extends System{
	
	private int ecartEntreNids = 2;
	private int nbLignes = 50;
	private int nbColonnes = 50;
	private int taillePerceptionAgent = 3;
	private int nbAgents = 0;
	private int cptAgents = 0;
	private int nbBoites = 0;
	private int cptBoites = 0;
	private Case grille[][] = new Case[nbLignes][nbColonnes];
	private Random random = new Random();
	
	public SystemImpl() {
		for (int i = 0; i < nbLignes; i++) {
			for (int y = 0; y < nbColonnes; y++) {
				grille[i][y] = new Case();
			}
		}
	}

	private void placerBoites() {
		for(int i=0; i<nbBoites; i++){
			Position pos = getRandomPositionInGrille();
			if(!grille[pos.getX()][pos.getY()].contientBoite()){
				IInfos boite = new BoiteImpl("Boite"+cptBoites, getRandomCouleur(), pos);
				placerElementDansGrille(boite, pos);
				cptBoites++;
			}
		}
	}

	private void placerNids() {
		int xb1 = nbColonnes / 2;
		int yb1 = 0;
		
		int xb2 = (nbColonnes / 2) + ecartEntreNids;
		int yb2 = 0;
		
		int xb3 = nbColonnes / 2;
		int yb3 = ecartEntreNids;
		
		IInfos nidBleu = new NidImpl("Nid bleu", Couleurs.BLEU.toString(), new Position(xb1, yb1));
		IInfos nidVert = new NidImpl("Nid vert", Couleurs.VERT.toString(), new Position(xb2, yb2));
		IInfos nidRouge = new NidImpl("Nid rouge", Couleurs.ROUGE.toString(), new Position(xb3, yb3));
		
		placerElementDansGrille(nidBleu, nidBleu.getPosition());
		placerElementDansGrille(nidVert, nidVert.getPosition());
		placerElementDansGrille(nidRouge, nidRouge.getPosition());
		
	}
	
	private void placerAgents() {
		for(int i =0; i < nbAgents; i++){
			Position pos = getRandomPositionInGrille();
			if(!grille[pos.getX()][pos.getY()].contientAgent()){
				ajoutNewAgent("agent"+cptAgents, pos, getRandomCouleur());
				cptAgents++;
			}
		}
	}

	@Override
	protected IPercevoir make_perceptionSystem() {
		return new IPercevoir() {

			@Override
			// Retourne les éléments autour d'une position donnée
			public List<IInfos> getInfosElementAutour(Position position) {
				List<IInfos> infos = new ArrayList<IInfos>();
				
				for(int i = position.getX() -taillePerceptionAgent; i < position.getX() + taillePerceptionAgent; i++){
					for(int y= position.getY() - taillePerceptionAgent; y < position.getY() + taillePerceptionAgent; y++){
						Position position2 = new Position(i, y);
						if(isValidPosition(position2)){
							Case c = grille[i][y];
							if(!c.caseVide()){
								infos.addAll(c.getElements());
							}
						}
					}
				}
				
				return infos;
			}

			@Override
			// Retourne les éléménts présents à une position donnée
			public List<IInfos> getInfosElementAPosition(Position position) {
				List<IInfos> infos = new ArrayList<IInfos>();
				
				return infos;
			}
			
		};
	}

	@Override
	protected IControl make_control() {
		return new IControl() {
			
			@Override
			public void modePasAPas(boolean actif) {
				requires().actionsSurAgents().setPasAPas(actif);
			}
			
			@Override
			public void mettreEnPause(boolean pause) {
				requires().actionsSurAgents().setPause(pause);
			}
			
			@Override
			public void changeVitesse(int vitesse) {
				requires().actionsSurAgents().setVitesse(vitesse);
			}

			@Override
			public void lancerSystem() {
				placerNids();
				placerBoites();
				placerAgents();
			}

			@Override
			public void setNombreAgents(int _nbAgents) {
				nbAgents = _nbAgents;
			}

			@Override
			public void setNombreBoites(int _nbBoites) {
				nbBoites =_nbBoites;
			}

		};
	}

	private void ajoutNewAgent(String nom, Position position, String couleur) {
		IInfos agent = requires().createAgents().createAgentAvecProxy(nom, position, couleur);
		placerElementDansGrille(agent, position);
	}
	
	private void placerElementDansGrille(IInfos element, Position position){
		if(isValidPosition(position)){
			Case c = grille[position.getX()][position.getY()];
			if(!c.hasSameElementType(element)){
				c.addElement(element);
				grille[position.getX()][position.getY()] = c;
			}
		}
	}

	private boolean isValidPosition(Position position) {
		return (position.getX() >= 0 && position.getY() >= 0
				&& position.getX() < nbColonnes && position.getY() < nbLignes);
	}

	@Override
	protected IInfosGrille make_infosGrille() {
		return new IInfosGrille() {
			
			@Override
			public List<IInfos> getGrilleInfos() {
				
				List<IInfos> infos = new ArrayList<IInfos>();
				for (int i = 0; i < nbLignes; i++) {
					for (int y = 0; y < nbColonnes; y++) {
						Case c = grille[i][y];
						if(!c.caseVide()){
							infos.addAll(c.getElements());
						}
					}
				}
				
				return infos;
				
			}
		};
	}
	
	private Position getRandomPositionInGrille(){
		
		int randX = random.nextInt(nbLignes + 1);
		int randY = random.nextInt(nbColonnes + 1);
		
		return new Position(randX, randY);
	}
	
	public String getRandomCouleur(){
		
		String couleur;
		int i = random.nextInt(3 + 1);
		switch (i) {
		case 0:
			couleur = Couleurs.BLEU.toString();
			break;

		case 1:
			couleur = Couleurs.VERT.toString();
			break;
		case 2:
			couleur = Couleurs.ROUGE.toString();
			break;
			
		default:
			couleur = Couleurs.BLEU.toString();
			break;
		}
		
		return couleur;
	}
}
