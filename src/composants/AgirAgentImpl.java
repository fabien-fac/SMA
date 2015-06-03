package composants;

import interfaces.IAgirAgent;
import interfaces.IInfos;

import java.util.Map;
import java.util.Random;

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
		Position position = infos.getPosition();
		int randX = random
				.nextInt(((position.getX() + 1) - (position.getX() - 1)) + 1)
				+ (position.getX() - 1);
		int randY = random
				.nextInt(((position.getY() + 1) - (position.getY() - 1)) + 1)
				+ (position.getY() - 1);

		Position pos = new Position(randX, randY);
		if (requires().demandeActionAgent().deplacer(infos, pos, false)) {
			self.setPosition(pos);
		}
	}
	
	private void allerANidCorrespondant(String couleurBoite, AgentImpl self) {
		Map<String, IInfos> nids = self.getNids();
		if (nids.containsKey(couleurBoite)) {
			IInfos nid = nids.get(couleurBoite);
			allerVersElement(nid, self);
		}
	}
	
	private void allerVersElement(IInfos boite, AgentImpl self) {
		IInfos infos = self.make_infosAgent();
		Position posElement = boite.getPosition();
		Position newPosition = new Position();
		Position position = infos.getPosition();

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

		if (requires().demandeActionAgent().deplacer(infos, newPosition,
				true)) {
			self.setPosition(newPosition);
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
}
