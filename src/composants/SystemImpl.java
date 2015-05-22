package composants;

import java.util.ArrayList;
import java.util.List;

import interfaces.IControl;
import interfaces.IInfos;
import interfaces.IPercevoir;
import SMA.System;
import classes.Case;
import classes.Position;
import enums.Couleurs;

public class SystemImpl extends System{
	
	private int nbLignes = 50;
	private int nbColonnes = 50;
	private int taillePerceptionAgent = 3;
	
	private Case grille[][] = new Case[nbLignes][nbColonnes];
	
	public SystemImpl() {
		for (int i = 0; i < nbLignes; i++) {
			for (int y = 0; y < nbColonnes; y++) {
				grille[i][y] = new Case();
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
				ajoutNewAgent("agent1", 0, 0, Couleurs.BLEU.toString());
				ajoutNewAgent("agent2", 1, 1, Couleurs.ROUGE.toString());
				ajoutNewAgent("agent3", 2, 2, Couleurs.VERT.toString());
				ajoutNewAgent("agent4", 15, 15, Couleurs.BLEU.toString());
			}

		};
	}

	private void ajoutNewAgent(String nom, int i, int j, String couleur) {
		Position position = new Position(i, j);
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
}
