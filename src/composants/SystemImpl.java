package composants;

import interfaces.IControl;
import interfaces.IDemandeActionsAgent;
import interfaces.IInfos;
import interfaces.IInfosGetLog;
import interfaces.IPercevoir;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import SMA.ActionsSystem;
import SMA.PersistanceSystem;
import classes.Action;
import classes.Case;
import classes.EtatInitial;
import classes.Position;
import enums.Actions;
import enums.Couleurs;
import enums.Types;

public class SystemImpl extends SMA.System {

	private final int INITIAL_ENERGIE_AGENT = 100;
	private int initalVitesseAgent = 1000;

	private int ecartEntreNids = 2;
	private int nbLignes = 50;
	private int nbColonnes = 50;
	private int taillePerceptionAgent = 3;
	private int nbAgents = 0;
	private int cptAgents = 0;
	private int nbBoites = 0;
	private int cptBoites = 0;
	private Case grille[][];
	private Random random = new Random();
	private List<IInfos> nids = new ArrayList<IInfos>();
	private final Object lockGrille = new Object();
	private boolean isStarted = false;
	private boolean isInitialised = false;

	private Timer timer = new Timer();
	private int delaisApparitionBoite = 2000;
	private int nbBoiteApparition = 1;

	public SystemImpl() {

	}

	@Override
	protected ActionsSystem make_actionsSystem() {
		return new ActionsSystemImpl();
	}

	@Override
	protected PersistanceSystem make_persistanceSystem() {
		return new PersistanceSystemImpl();
	}

	private void placerBoites() {
		for (int i = 0; i < nbBoites; i++) {
			Position pos = getRandomPositionInGrille();
			if (!grille[pos.getX()][pos.getY()].contientBoite()) {
				IInfos boite = new BoiteImpl("Boite" + cptBoites,
						getRandomCouleur(), pos);
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

		IInfos nidBleu = new NidImpl("Nid bleu", Couleurs.BLEU.toString(),
				new Position(xb1, yb1));
		IInfos nidVert = new NidImpl("Nid vert", Couleurs.VERT.toString(),
				new Position(xb2, yb2));
		IInfos nidRouge = new NidImpl("Nid rouge", Couleurs.ROUGE.toString(),
				new Position(xb3, yb3));

		nids.add(nidBleu);
		nids.add(nidRouge);
		nids.add(nidVert);

		placerElementDansGrille(nidBleu, nidBleu.getPosition());
		placerElementDansGrille(nidVert, nidVert.getPosition());
		placerElementDansGrille(nidRouge, nidRouge.getPosition());

	}

	private void placerAgents() {
		for (int i = 0; i < nbAgents; i++) {
			Position pos = getRandomPositionInGrille();
			if (!grille[pos.getX()][pos.getY()].contientAgent()) {
				Position posAgent = new Position(pos.getX(), pos.getY());
				ajoutNewAgent("agent" + cptAgents, posAgent, getRandomCouleur());
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

				for (int i = position.getX() - taillePerceptionAgent; i < position
						.getX() + taillePerceptionAgent; i++) {
					for (int y = position.getY() - taillePerceptionAgent; y < position
							.getY() + taillePerceptionAgent; y++) {
						Position position2 = new Position(i, y);
						if (isValidPosition(position2)) {
							Case c = grille[i][y];
							if (!c.caseVide()) {
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

				if (isValidPosition(position)) {
					infos.addAll(grille[position.getX()][position.getY()]
							.getElements());
				}
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

				try {
					if (pause) {
						timer.cancel();

					} else {
						timer = new Timer();
						timer.schedule(new PopBoiteTask(),
								delaisApparitionBoite);
					}
				} catch (Exception e) {
				}
			}

			@Override
			public void changeVitesse(int vitesse) {
				initalVitesseAgent = vitesse;
				requires().actionsSurAgents().setVitesse(vitesse);
			}

			@Override
			public void lancerSystem() {
				if (!isStarted) {
					isStarted = true;
					requires().actionsSurAgents().setPause(false);
					timer.schedule(new PopBoiteTask(), delaisApparitionBoite);
				}
			}

			@Override
			public void setNombreAgents(int _nbAgents) {
				nbAgents = _nbAgents;
			}

			@Override
			public void setNombreBoites(int _nbBoites) {
				nbBoites = _nbBoites;
			}

			@Override
			public void gestionApparitionBoites(int nbBoite, int delais) {
				nbBoiteApparition = nbBoite;
				delaisApparitionBoite = delais;
			}

			@Override
			public void persisterSystem() {
				EtatInitial etatInitial = new EtatInitial();
				etatInitial.setInfos(getAllIInfosInGrille());
				etatInitial.setNbApparitionBoite(nbBoiteApparition);
				etatInitial.setVitesseApparitionBoite(delaisApparitionBoite);
				etatInitial.setNbLignes(nbLignes);
				etatInitial.setNbColonnes(nbColonnes);

				parts().persistanceSystem().persistance()
						.sauvegarderSystem(etatInitial);
			}

			@Override
			public void changeTailleGrille(int _nbLignes, int _nbColonnes) {
				nbLignes = _nbLignes;
				nbColonnes = _nbColonnes;
			}

			@Override
			public void initialiserSystem() {
				if(!isInitialised){
					isInitialised = true;
					grille = new Case[nbLignes][nbColonnes];
					for (int i = 0; i < nbLignes; i++) {
						for (int y = 0; y < nbColonnes; y++) {
							grille[i][y] = new Case();
						}
					}
	
					placerNids();
					placerBoites();
					placerAgents();
	
					avertireLoggers();
				}
			}

			@Override
			public void chargerEtatInitial(String nomFichier) {
				EtatInitial etatInitial = parts().persistanceSystem()
						.persistance().getEtatInitial(nomFichier);
				
				nbLignes = etatInitial.getNbLignes();
				nbColonnes = etatInitial.getNbColonnes();
				delaisApparitionBoite = etatInitial.getVitesseApparitionBoite();
				nbBoiteApparition = etatInitial.getNbApparitionBoite();
				
				grille = new Case[nbLignes][nbColonnes];
				for (int i = 0; i < nbLignes; i++) {
					for (int y = 0; y < nbColonnes; y++) {
						grille[i][y] = new Case();
					}
				}

				for (IInfos infos : etatInitial.getInfos()) {
					if (Types.AGENT.toString().equals(infos.getType())) {
						ajoutNewAgent(infos.getNom(), infos.getPosition(),
								infos.getCouleur());
					} else if (Types.BOITE.toString().equals(infos.getType())) {
						placerElementDansGrille(infos, infos.getPosition());
					} else if (Types.NID.toString().equals(infos.getType())) {
						placerElementDansGrille(infos, infos.getPosition());
					}
				}
				isInitialised = true;
				avertireLoggers();
			}
			
		};
	}

	private void ajoutNewAgent(String nom, Position position, String couleur) {
		IInfos agent = requires().createAgents().createAgentAvecProxy(nom,
				position, couleur);
		placerElementDansGrille(agent, position);
	}

	private void placerElementDansGrille(IInfos element, Position position) {
		if (isValidPosition(position)) {
			Case c = grille[position.getX()][position.getY()];
			if (!c.hasSameElementType(element)) {
				c.addElement(element);
				grille[position.getX()][position.getY()] = c;
			}
		}
	}

	private boolean isValidPosition(Position position) {
		return (position.getX() >= 0 && position.getY() >= 0
				&& position.getX() < nbColonnes && position.getY() < nbLignes);
	}

	private Position getRandomPositionInGrille() {

		int randX = random.nextInt(nbLignes);
		int randY = random.nextInt(nbColonnes);

		return new Position(randX, randY);
	}

	public String getRandomCouleur() {

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
					Case c = grille[boite.getPosition().getX()][boite
							.getPosition().getY()];
					if (c.contientBoite(boite)) {
						c.retirerElement(boite);
						res = true;
					}
				}

				if (res) {
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
					Case c = grille[agent.getPosition().getX()][agent
							.getPosition().getY()];
					// if (!c.contientBoite()) {

					if (c.getNid() != null) {
						if (agent.getCouleur().equals(boite.getCouleur())) {
							energie = 2 * INITIAL_ENERGIE_AGENT / 3;
						} else {
							energie = INITIAL_ENERGIE_AGENT / 3;
						}
					} else {
						c.addElement(boite);
					}
					// }
				}

				if (energie > -1) {
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
			public boolean deplacer(IInfos agent, Position oldPos,
					IInfos boitePossede, Position newPos) {
				boolean res = false;
				if (isValidPosition(newPos)) {
					synchronized (lockGrille) {
						Case cDest = grille[newPos.getX()][newPos.getY()];
						if (!cDest.contientAgent()) {
							Case cOrigine = grille[oldPos.getX()][oldPos.getY()];
							cOrigine.retirerElement(agent);

							cDest.addElement(agent);
							res = true;
						}
					}
				}

				if (res) {
					Action action = new Action();
					if (boitePossede != null) {
						action.setAction(Actions.DEPLACEMENT_AVEC_BOITE);
						action.setBoite(boitePossede);
					} else {
						action.setAction(Actions.DEPLACEMENT);
					}
					action.setAgent(agent);
					action.setOldPosition(oldPos);
					action.setNewPosition(newPos);

					addAction(action);
				}

				return res;
			}

			@Override
			public List<IInfos> getListNids() {
				return nids;
			}

			@Override
			public int getInitialEnergie() {
				return INITIAL_ENERGIE_AGENT;
			}

			@Override
			public void suicide(IInfos agent) {
				synchronized (lockGrille) {
					grille[agent.getPosition().getX()][agent.getPosition()
							.getY()].retirerElement(agent);
				}

				Action action = new Action();
				action.setAction(Actions.SUICIDE_AGENT);
				action.setAgent(agent);

				addAction(action);
			}

			@Override
			public int getVitesse() {
				return initalVitesseAgent;
			}
		};
	}

	@Override
	protected IInfosGetLog make_infosLog() {
		return parts().actionsSystem().getLog();
	}

	private void addAction(Action action) {
		parts().actionsSystem().addAction().addAction(action);
	}

	private void avertireLoggers() {
		Action action = new Action();
		action.setAction(Actions.INITIALISATION);
		List<IInfos> infos = getAllIInfosInGrille();
		action.setNouveauxElements(infos);
		action.setNbLignes(nbLignes);
		action.setNbColonnes(nbColonnes);
		addAction(action);
	}

	private List<IInfos> getAllIInfosInGrille() {

		List<IInfos> infos = new ArrayList<IInfos>();
		synchronized (lockGrille) {
			for (int i = 0; i < nbLignes; i++) {
				for (int y = 0; y < nbColonnes; y++) {
					Case c = grille[i][y];
					if (!c.caseVide()) {
						infos.addAll(c.getElements());
					}
				}
			}
		}

		return infos;
	}

	class PopBoiteTask extends TimerTask {

		@Override
		public void run() {

			for (int i = 0; i < nbBoiteApparition; i++) {
				Position position;
				Case c;
				do {
					position = getRandomPositionInGrille();
					c = grille[position.getX()][position.getY()];
				} while (c.contientBoite() || c.contientNid());

				IInfos boite = new BoiteImpl("Boite" + cptBoites,
						getRandomCouleur(), position);
				placerElementDansGrille(boite, position);
				cptBoites++;

				Action action = new Action();
				action.setAction(Actions.NOUVEL_ELEMENT);
				List<IInfos> infos = new ArrayList<IInfos>();
				infos.add(boite);
				action.setNouveauxElements(infos);
				addAction(action);

			}

			timer.schedule(new PopBoiteTask(), delaisApparitionBoite);
		}

	}

}
