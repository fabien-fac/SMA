package composants;

import interfaces.IControl;
import interfaces.IDemandeActionsAgent;
import interfaces.IInfos;
import interfaces.IInfosGetLog;
import interfaces.IPercevoir;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import classes.Action;
import classes.Case;
import classes.Position;
import enums.Actions;
import enums.Couleurs;

public class SystemImpl extends SMA.System{
	
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
	private List<IInfos> nids = new ArrayList<IInfos>();
	private final Object lockGrille = new Object();
	private final Object lockActions = new Object();
	
	private List<Action> actions = new ArrayList<Action>();
	private long cptActions = 0;
	
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
		
		nids.add(nidBleu);
		nids.add(nidRouge);
		nids.add(nidVert);
		
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
				
				avertireLoggers();
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

	private Position getRandomPositionInGrille(){
		
		int randX = random.nextInt(nbLignes);
		int randY = random.nextInt(nbColonnes);
		
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

	@Override
	protected IDemandeActionsAgent make_actionsSurAgent() {
		return new IDemandeActionsAgent() {
			
			@Override
			public boolean prendreBoite(IInfos agent, IInfos boite) {
				boolean res = false;
				synchronized (lockGrille) {
					Case c = grille[boite.getPosition().getX()][boite.getPosition().getY()];
					if(c.contientBoite(boite)){
						c.retirerElement(boite);
						res = true;
					}
				}
				
				if(res){
					Action action = new Action();
					action.setAction(Actions.PRENDRE_BOITE);
					action.setAgent(agent);
					action.setBoite(boite);
					
					addAction(action);
				}
				
				return res;
			}
			
			@Override
			public int deposerBoite(IInfos agent, IInfos boite, IInfos nid) {
				
				int energie = -1;
				
				synchronized (lockGrille) {
					Case c = grille[agent.getPosition().getX()][agent.getPosition().getY()];
					if(!c.contientBoite()){
						c.addElement(boite);
						
						energie = 10;
					}
				}
				
				if(energie > -1){
					Action action = new Action();
					action.setAction(Actions.DEPOSER_BOITE);
					action.setAgent(agent);
					action.setBoite(boite);
					action.setNid(nid);
					
					addAction(action);
				}
				
				return energie;
			}
			
			@Override
			public boolean deplacer(IInfos agent, Position newPos, boolean possedeBoite) {
				boolean res = false;
				if(isValidPosition(newPos)){
					synchronized (lockGrille) {
						Case cDest = grille[newPos.getX()][newPos.getY()];
						if(!cDest.contientAgent()){
							
							Case cOrigine = grille[agent.getPosition().getX()][agent.getPosition().getY()];
							cOrigine.retirerElement(agent);
							
							cDest.addElement(agent);
							res = true;
						}
					}
				}
				
				if(res){
					Action action = new Action();
					if(possedeBoite){
						action.setAction(Actions.DEPLACEMENT_AVEC_BOITE);
					}
					else{
						action.setAction(Actions.DEPLACEMENT);
					}
					action.setAgent(agent);
					action.setPosition(newPos);
					
					addAction(action);
				}
				
				return res;
			}

			@Override
			public List<IInfos> getListNids() {
				return nids;
			}
		};
	}

	@Override
	protected IInfosGetLog make_infosLog() {
		return new IInfosGetLog() {
			@Override
			public Action getAction(int id) {
				return getActionFromList(id);
			}
		};
	}
	
	private Action getActionFromList(int idAction){
		Action action = null;
		synchronized (lockActions) {
			if(idAction <= cptActions){
				action = actions.get(idAction);
			}
			return action;
		}
		
	}
	
	private void addAction(Action action){
		synchronized (lockActions) {
			action.setId(cptActions);
			actions.add((int) cptActions, action);
			
			requires().signalLog().signalNewLog((int) cptActions);
			cptActions++;
		}
	}

	private void avertireLoggers() {
		Action action = new Action();
		action.setAction(Actions.INITIALISATION);
		List<IInfos> infos = new ArrayList<IInfos>();
		for (int i = 0; i < nbLignes; i++) {
			for (int y = 0; y < nbColonnes; y++) {
				Case c = grille[i][y];
				if(!c.caseVide()){
					infos.addAll(c.getElements());
				}
			}
		}
		action.setNouveauxElements(infos);
		addAction(action);
	}

}
