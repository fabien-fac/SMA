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

public class AgentImpl extends Agent{
	
	private final int INITIAL_ENERGIE = 100;
	
	private String nom;
	private int energie;
	private String couleur;
	private Position position;
	
	private int vitesse = 1000;
	private boolean actif = true;
	private boolean pasAPas;
	
	private IInfos boitePossede = null;
	private Map<String, IInfos> nids = new HashMap<String, IInfos>();
	
	public AgentImpl(String nom, Position position, String couleur) {
		this.nom = nom;
		this.position = position;
		this.couleur = couleur;
		this.energie = INITIAL_ENERGIE;
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
			public void setPause(boolean actif) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	
	@Override
	protected void start() {
		
		List<IInfos> nidsList = requires().demandeAction().getListNids();
		for(IInfos nid : nidsList){
			nids.put(nid.getCouleur(), nid);
		}
		
		new Thread(){
			public void run() {
				while(true){
					if(actif){
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
		IInfos boiteChoixTemp = null;
		
		List<IInfos> infosElementAutour = requires().percevoirAgent().getInfosElementAutour(position);
		List<IInfos> infosElementAPosition = requires().percevoirAgent().getInfosElementAPosition(position);
		
		boolean actionRealisee = false;
		
		if(boitePossede != null){
			
			if(estSurNidCorrespondant(infosElementAPosition, boitePossede.getCouleur())){
				IInfos nidPositionCourante = getNidPositionCourante(infosElementAPosition);
				deposerBoite(nidPositionCourante);
				actionRealisee = true;
			}
			else{
				allerANidCorrespondant(boitePossede.getCouleur());
				actionRealisee = true;
			}
		}
		else{
			int cpt = 0;
			for(IInfos element : infosElementAutour){
				
				if(estBoiteMemeCouleur(element)){
					prendreBoite(element);
					actionRealisee = true;
				}
				else if(estBoiteAutreCouleur(element)){
					if(estDerniereCaseEtudiee(cpt, infosElementAutour.size())){
						prendreBoite(element);
						actionRealisee = true;
					}
					else{
						boiteChoixTemp = element;
					}
				}
				else{
					if(estDerniereCaseEtudiee(cpt, infosElementAutour.size())){
						if(boiteChoixTemp != null){
							prendreBoite(boiteChoixTemp);
							boiteChoixTemp = null;
							actionRealisee = true;
						}
						else{
							marcherAleatoirement();
							actionRealisee = true;
						}
					}
				}
				cpt++;
			}
			
			if(!actionRealisee){
				marcherAleatoirement();
			}
		}
		
		
	};
	
	private IInfos getNidPositionCourante(List<IInfos> infosElementAPosition) {
		IInfos nid = null;
		for(IInfos info : infosElementAPosition){
			if(Types.NID.toString().equals(info.getType())){
				nid = info;
			}
		}
		
		return nid;
	}

	private void marcherAleatoirement() {
		Random random = new Random();
		int randX = random.nextInt(((position.getX()+1) - (position.getX()-1)) + 1) + (position.getX()-1);
		int randY = random.nextInt(((position.getY()+1) - (position.getY()-1)) + 1) + (position.getY()-1);
		
		Position pos = new Position(randX, randY);
		if(requires().demandeAction().deplacer(make_infosAgent(), pos, false)){
			position = pos;
		}
	}

	private boolean estDerniereCaseEtudiee(int cpt, int size) {
		return (cpt == size);
	}

	private boolean estBoiteAutreCouleur(IInfos element) {
		if(Types.BOITE.toString().equals(element.getType()) 
				&& !element.getCouleur().equals(couleur)){
			return true;
		}
		
		return false;
	}

	private void prendreBoite(IInfos boite) {
		if(requires().demandeAction().prendreBoite(make_infosAgent(), boite)){
			System.out.println(nom + " - prendre boite " + boite.getNom());
			boitePossede = boite;
		}
	}

	private boolean estBoiteMemeCouleur(IInfos element) {
		if(Types.BOITE.toString().equals(element.getType()) 
				&& element.getCouleur().equals(couleur)){
			return true;
		}
		
		return false;
	}

	private void allerANidCorrespondant(String couleurBoite) {
		if(nids.containsKey(couleurBoite)){
			IInfos nid = nids.get(couleurBoite);
			Position posNid = nid.getPosition();
			Position newPosition = new Position();
			
			if(posNid.getX() > position.getX()){
				newPosition.setX(position.getX()+1);
			}
			else if(posNid.getX() < position.getX()){
				newPosition.setX(position.getX()-1);
			}
			
			if(posNid.getY() > position.getY()){
				newPosition.setY(position.getY()+1);
			}
			else if(posNid.getY() < position.getY()){
				newPosition.setY(position.getY()-1);
			}
			
			if(requires().demandeAction().deplacer(make_infosAgent(), newPosition, true)){
				System.out.println(nom + " déplacement vers nids " + nid.getNom());
				position = newPosition;
			}
		}
	}

	private void deposerBoite(IInfos nid) {
		int newEnergie = requires().demandeAction().deposerBoite(make_infosAgent(), boitePossede, nid);
		if(energie > 0){
			energie = newEnergie; 
			boitePossede = null;
		}
	}

	private boolean estSurNidCorrespondant(List<IInfos> infosElementAPosition, String couleur){
		for(IInfos element : infosElementAPosition){
			if(Types.NID.toString().equals(element.getType()) &&
					couleur.equals(element.getCouleur())){
				return true;
			}
		}
		
		return false;
	}

}
