package composants;

import interfaces.IDecisionAgent;
import interfaces.IInfos;

import java.util.List;

import classes.DecisionAgentPrise;
import classes.Position;
import SMA.DecisionAgent;
import enums.DecisionsAgent;
import enums.Types;

public class DecisionAgentImpl extends DecisionAgent{

	@Override
	protected IDecisionAgent make_decisionAgent() {
		return new IDecisionAgent() {

			@Override
			public DecisionAgentPrise getDecision(List<IInfos> infosElementAutour,
					List<IInfos> infosElementAPosition, IInfos self,
					IInfos boitePossede) {
				
				IInfos boiteMemeCouleurTemp = null;
				IInfos boiteChoixTemp = null;
				DecisionAgentPrise decisionAgent = null;
				Position position = self.getPosition();
				
				if(self.getEnergie() <= 0){
					decisionAgent = new DecisionAgentPrise();
					decisionAgent.setDecisionsAgent(DecisionsAgent.SUICIDE);
					System.out.println("decision suicide");
					return decisionAgent;
				}
				
				if (boitePossede != null) {

					if (estSurNidCorrespondant(infosElementAPosition,
							boitePossede.getCouleur())) {
						IInfos nid = getNidPositionCourant(infosElementAPosition);
						decisionAgent = new DecisionAgentPrise();
						decisionAgent.setDecisionsAgent(DecisionsAgent.DEPOSER_BOITE);
						decisionAgent.setNid(nid);
						decisionAgent.setBoite(boitePossede);
					} else {
						decisionAgent = new DecisionAgentPrise();
						decisionAgent.setDecisionsAgent(DecisionsAgent.DEPLACEMENT_VERS_NID);
						decisionAgent.setCouleurNid(boitePossede.getCouleur());
					}
				} else {
					int cpt = 0;
					for (IInfos element : infosElementAutour) {

						if (estBoiteMemeCouleur(element, self)) {
							if (element.getPosition().equals(position)) {
								decisionAgent = new DecisionAgentPrise();
								decisionAgent.setDecisionsAgent(DecisionsAgent.PRENDRE_BOITE);
								decisionAgent.setBoite(element);
							} else {
								boiteMemeCouleurTemp = element;
							}
						} else if (estBoiteAutreCouleur(element, self)) {
							if (estDerniereCaseEtudiee(cpt, infosElementAutour.size())) {
								if (element.getPosition().equals(position)) {
									decisionAgent = new DecisionAgentPrise();
									decisionAgent.setDecisionsAgent(DecisionsAgent.PRENDRE_BOITE);
									decisionAgent.setBoite(element);
								} else {
									boiteChoixTemp = element;
								}
							} else {
								boiteChoixTemp = element;
							}
						}
						
						if (decisionAgent != null) {
							break;
						}

						if (estDerniereCaseEtudiee(cpt, infosElementAutour.size())) {
							if (boiteMemeCouleurTemp != null) {
								decisionAgent = new DecisionAgentPrise();
								decisionAgent.setDecisionsAgent(DecisionsAgent.DEPLACEMENT_VERS_BOITE);
								decisionAgent.setBoite(boiteMemeCouleurTemp);
							} else if (boiteChoixTemp != null) {
								if (boiteChoixTemp.getPosition().equals(position)) {
									decisionAgent = new DecisionAgentPrise();
									decisionAgent.setDecisionsAgent(DecisionsAgent.PRENDRE_BOITE);
									decisionAgent.setBoite(boiteChoixTemp);
								} else {
									decisionAgent = new DecisionAgentPrise();
									decisionAgent.setDecisionsAgent(DecisionsAgent.DEPLACEMENT_VERS_BOITE);
									decisionAgent.setBoite(boiteChoixTemp);
								}
							} else {
								decisionAgent = new DecisionAgentPrise();
								decisionAgent.setDecisionsAgent(DecisionsAgent.DEPLACEMENT_ALEATOIRE);
							}
						}

						cpt++;
					}

				}
				return decisionAgent;
				
			}

		};
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
	
	private boolean estBoiteMemeCouleur(IInfos element, IInfos self) {
		if (Types.BOITE.toString().equals(element.getType())
				&& element.getCouleur().equals(self.getCouleur())) {
			return true;
		}

		return false;
	}
	
	private boolean estBoiteAutreCouleur(IInfos element, IInfos self) {
		if (Types.BOITE.toString().equals(element.getType())
				&& !element.getCouleur().equals(self.getCouleur())) {
			return true;
		}

		return false;
	}

	private boolean estDerniereCaseEtudiee(int cpt, int size) {
		return (cpt + 1 == size);
	}
	
	private IInfos getNidPositionCourant(
			List<IInfos> infosElementAPosition) {
		for(IInfos element: infosElementAPosition){
			if(element.getType().equals(Types.NID.toString())){
				return element;
			}
		}
		
		return null;
	}
}
