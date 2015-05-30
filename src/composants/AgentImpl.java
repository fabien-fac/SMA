package composants;

import interfaces.IActionsAgent;
import interfaces.IInfos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import SMA.Agents.Agent;
import classes.Position;
import enums.Types;

public class AgentImpl extends Agent {

	private String nom;
	private int energie;
	private String couleur;
	private Position position;

	private int vitesse = 1000;
	private boolean actif = true;

	private IInfos boitePossede = null;
	private Map<String, IInfos> nids = new HashMap<String, IInfos>();

	public AgentImpl(String nom, Position position, String couleur) {
		this.nom = nom;
		this.position = position;
		this.couleur = couleur;
	}

	@Override
	public IInfos make_infosAgent() {
		return new IInfos() {

			@Override
			public String getType() {
				return Types.AGENT.toString();
			}

			@Override
			public Position getPosition() {
				return position;
			}

			@Override
			public String getNom() {
				return nom;
			}

			@Override
			public int getEnergie() {
				return energie;
			}

			@Override
			public String getCouleur() {
				return couleur;
			}
		};
	}

	@Override
	public IActionsAgent make_actionsAgent() {
		return new IActionsAgent() {

			@Override
			public void setVitesse(int _vitesse) {
				vitesse = _vitesse;
			}

			@Override
			public void setPasAPas(boolean actif) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setPause(boolean _actif) {
				actif = !_actif;
			}

		};
	}

	@Override
	protected void start() {

		List<IInfos> nidsList = requires().demandeAction().getListNids();
		energie = requires().demandeAction().getInitialEnergie();
		vitesse = requires().demandeAction().getVitesse();
		
		for (IInfos nid : nidsList) {
			nids.put(nid.getCouleur(), nid);
		}

		new Thread() {
			public void run() {
				while (energie > 0) {
					if (actif) {
						agir();
					}
					try {
						Thread.sleep(vitesse);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}.start();

	}

	private void agir() {
		IInfos boiteMemeCouleurTemp = null;
		IInfos boiteChoixTemp = null;

		List<IInfos> infosElementAutour = requires().percevoirAgent()
				.getInfosElementAutour(position);
		List<IInfos> infosElementAPosition = requires().percevoirAgent()
				.getInfosElementAPosition(position);

		boolean actionRealisee = false;

		if (boitePossede != null) {

			if (estSurNidCorrespondant(infosElementAPosition,
					boitePossede.getCouleur())) {
				IInfos nidPositionCourante = getNidPositionCourante(infosElementAPosition);
				deposerBoite(nidPositionCourante);
				actionRealisee = true;
			} else {
				allerANidCorrespondant(boitePossede.getCouleur());
				actionRealisee = true;
			}
		} else {
			int cpt = 0;
			for (IInfos element : infosElementAutour) {

				if (estBoiteMemeCouleur(element)) {
					if (element.getPosition().equals(position)) {
						prendreBoite(element);
						actionRealisee = true;
					} else {
						boiteMemeCouleurTemp = element;
					}
				} else if (estBoiteAutreCouleur(element)) {
					if (estDerniereCaseEtudiee(cpt, infosElementAutour.size())) {
						if (element.getPosition().equals(position)) {
							prendreBoite(element);
							actionRealisee = true;
						} else {
							boiteChoixTemp = element;
						}
					} else {
						boiteChoixTemp = element;
					}
				}
				
				if (actionRealisee) {
					break;
				}

				if (estDerniereCaseEtudiee(cpt, infosElementAutour.size())) {
					if (boiteMemeCouleurTemp != null) {
						allerVersElement(boiteMemeCouleurTemp);
						actionRealisee = true;
					} else if (boiteChoixTemp != null) {
						if (boiteChoixTemp.getPosition().equals(position)) {
							prendreBoite(boiteChoixTemp);
						} else {
							allerVersElement(boiteChoixTemp);
						}
						actionRealisee = true;
					} else {
						marcherAleatoirement();
						actionRealisee = true;
					}
				}

				cpt++;
			}

		}

	};

	private void allerVersElement(IInfos boite) {
		Position posElement = boite.getPosition();
		Position newPosition = new Position();

		if (posElement.getX() > position.getX()) {
			newPosition.setX(position.getX() + 1);
		} else if (posElement.getX() < position.getX()) {
			newPosition.setX(position.getX() - 1);
		} else {
			newPosition.setX(position.getX());
		}

		if (posElement.getY() > position.getY()) {
			newPosition.setY(position.getY() + 1);
		} else if (posElement.getY() < position.getY()) {
			newPosition.setY(position.getY() - 1);
		} else {
			newPosition.setY(position.getY());
		}

		if (requires().demandeAction().deplacer(make_infosAgent(), newPosition,
				true)) {
			position = newPosition;
			reduireEnergie(1);
		}
	}

	private IInfos getNidPositionCourante(List<IInfos> infosElementAPosition) {
		IInfos nid = null;
		for (IInfos info : infosElementAPosition) {
			if (Types.NID.toString().equals(info.getType())) {
				nid = info;
			}
		}

		return nid;
	}

	private void marcherAleatoirement() {
		Random random = new Random();
		int randX = random
				.nextInt(((position.getX() + 1) - (position.getX() - 1)) + 1)
				+ (position.getX() - 1);
		int randY = random
				.nextInt(((position.getY() + 1) - (position.getY() - 1)) + 1)
				+ (position.getY() - 1);

		Position pos = new Position(randX, randY);
		if (requires().demandeAction().deplacer(make_infosAgent(), pos, false)) {
			position = pos;
			reduireEnergie(1);
		}
	}

	private void reduireEnergie(int i) {
		energie -=i;
		if(energie <= 0){
			requires().demandeAction().suicide(make_infosAgent());
		}
	}

	private boolean estDerniereCaseEtudiee(int cpt, int size) {
		return (cpt + 1 == size);
	}

	private boolean estBoiteAutreCouleur(IInfos element) {
		if (Types.BOITE.toString().equals(element.getType())
				&& !element.getCouleur().equals(couleur)) {
			return true;
		}

		return false;
	}

	private void prendreBoite(IInfos boite) {
		if (requires().demandeAction().prendreBoite(make_infosAgent(), boite)) {
			boitePossede = boite;
		}
	}

	private boolean estBoiteMemeCouleur(IInfos element) {
		if (Types.BOITE.toString().equals(element.getType())
				&& element.getCouleur().equals(couleur)) {
			return true;
		}

		return false;
	}

	private void allerANidCorrespondant(String couleurBoite) {
		if (nids.containsKey(couleurBoite)) {
			IInfos nid = nids.get(couleurBoite);
			allerVersElement(nid);
		}
	}

	private void deposerBoite(IInfos nid) {
		int newEnergie = requires().demandeAction().deposerBoite(
				make_infosAgent(), boitePossede, nid);
		if (energie > 0) {
			energie += newEnergie;
			boitePossede = null;
		}
	}

	private boolean estSurNidCorrespondant(List<IInfos> infosElementAPosition,
			String couleurBoite) {
		for (IInfos element : infosElementAPosition) {
			if (Types.NID.toString().equals(element.getType())
					&& couleurBoite.equals(element.getCouleur())) {
				return true;
			}
		}

		return false;
	}

}
