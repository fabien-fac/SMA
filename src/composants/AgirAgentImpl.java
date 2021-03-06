package composants;

import interfaces.IAgirAgent;
import interfaces.IInfos;

import java.util.Map;
import java.util.Random;
import java.util.Stack;

import SMA.AgirAgent;
import classes.DecisionAgentPrise;
import classes.Position;

public class AgirAgentImpl extends AgirAgent {
	
	@Override
	protected IAgirAgent make_action() {
		return new IAgirAgent() {

			@Override
			public void agir(DecisionAgentPrise decisionAgent, AgentImpl self, IInfos boitePossede) {
				
				switch (decisionAgent.getDecisionsAgent()) {
				case DEPLACEMENT_ALEATOIRE:
					marcherAleatoirement(self);
					break;

				case DEPLACEMENT_VERS_NID:
					allerANidCorrespondant(decisionAgent.getCouleurNid(), self);
					break;

				case DEPLACEMENT_VERS_BOITE:
					allerVersElement(decisionAgent.getBoite(), self);
					break;
					
				case DEPOSER_BOITE:
					deposerBoite(decisionAgent.getNid(), self, boitePossede);
					break;

				case PRENDRE_BOITE:
					prendreBoite(decisionAgent.getBoite(), self);
					break;

				case SUICIDE:
					requires().demandeActionAgent().suicide(self.make_infosAgent());
					break;
					
				default:
					break;
				}

			}
		};
	}

	private void marcherAleatoirement(AgentImpl self) {
		Random random = new Random();
		IInfos infos = self.make_infosAgent();
		Position oldPosition = new Position(infos.getPosition().getX(), infos.getPosition().getY());
		Position position = new Position();
		position.setX(infos.getPosition().getX());
		position.setY(infos.getPosition().getY());
		int vitesse = getVitesseDeplacement(self);
		
		for(int i=0; i<vitesse; i++){
			int randX = random
					.nextInt(((position.getX() + 1) - (position.getX() - 1)) + 1)
					+ (position.getX() - 1);
			int randY = random
					.nextInt(((position.getY() + 1) - (position.getY() - 1)) + 1)
					+ (position.getY() - 1);

			position.setX(randX);
			position.setY(randY);
		}
		
		if (requires().demandeActionAgent().deplacer(infos, oldPosition, null, position)) {
			self.setPosition(position);
			self.ajouterEnergie(-vitesse);
		}
	}
	
	private void allerANidCorrespondant(String couleurBoite, AgentImpl self) {
		Map<String, IInfos> nids = self.getNids();
		if (nids.containsKey(couleurBoite)) {
			IInfos nid = nids.get(couleurBoite);
			allerVersElement(nid, self);
		}
	}
	
	private void allerVersElement(IInfos element, AgentImpl self) {
		Stack<Position> pile = new Stack<Position>();
		IInfos infos = self.make_infosAgent();
		Position posElement = element.getPosition();
		Position oldPosition = new Position(infos.getPosition().getX(), infos.getPosition().getY());
		Position positionAgent = new Position();
		positionAgent.setX(infos.getPosition().getX());
		positionAgent.setY(infos.getPosition().getY());

		int vitesse = getVitesseDeplacement(self);
		for(int i=0; i<vitesse; i++){
			
			Position newPosition = new Position();
			if (posElement.getX() > positionAgent.getX()) {
				newPosition.setX(positionAgent.getX() + 1);
			} else if (posElement.getX() < positionAgent.getX()) {
				newPosition.setX(positionAgent.getX() - 1);
			} else {
				newPosition.setX(positionAgent.getX());
			}
	
			if (posElement.getY() > positionAgent.getY()) {
				newPosition.setY(positionAgent.getY() + 1);
			} else if (posElement.getY() < positionAgent.getY()) {
				newPosition.setY(positionAgent.getY() - 1);
			} else {
				newPosition.setY(positionAgent.getY());
			}
		
			positionAgent = newPosition;
			pile.add(newPosition);
		}
		
		boolean moveDone = false;
		int energieConsome = vitesse;
		while(!moveDone && !pile.isEmpty()){
			Position position = pile.pop();
			if (requires().demandeActionAgent().deplacer(infos, oldPosition,
					self.getBoitePossede(), position)) {
				self.setPosition(position);
				energieConsome--;
				self.ajouterEnergie(-energieConsome);
				moveDone = true;
			}
		}
		
	}
	
	private void deposerBoite(IInfos nid, AgentImpl self, IInfos boitePossede) {
		int res = requires().demandeActionAgent().deposerBoite(
				self.make_infosAgent(), boitePossede, nid);
		if(res > -1){
			self.ajouterEnergie(res);
			self.setBoite(null);
		}
	}
	
	
	private void prendreBoite(IInfos boite, AgentImpl self) {
		if (requires().demandeActionAgent().prendreBoite(self.make_infosAgent(), boite)) {
			self.setBoite(boite);
		}
	}
	
	private int getVitesseDeplacement(AgentImpl self){
		int vitesse;
		IInfos infos = self.make_infosAgent();
		if(infos.getEnergie() > (2*self.getEnergieInitiale()/3)){
			vitesse = 3;
		}
		else if(infos.getEnergie() > (1*self.getEnergieInitiale()/3)){
			vitesse = 2;
		}
		else{
			vitesse = 1;
		}
		
		return vitesse;
	}
}
