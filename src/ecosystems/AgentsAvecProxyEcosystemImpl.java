package ecosystems;

import interfaces.IActionsAgent;
import interfaces.ICreateAgentAvecProxy;
import interfaces.IInfos;
import interfaces.IInfosAgents;

import java.util.HashMap;
import java.util.Map;

import SMA.Agents;
import SMA.AgentsAvecProxyEcosystem;
import SMA.ProxysAgent;
import classes.Position;

public class AgentsAvecProxyEcosystemImpl extends AgentsAvecProxyEcosystem {

	Map<String, AgentAvecProxy.Component> agents = new HashMap<>();

	
	@Override
	protected IActionsAgent make_actionsAgentsProxy() {
		return new IActionsAgent() {

			@Override
			public void setVitesse(int vitesse) {
				for (String nom : agents.keySet()) {
					parts().agents().getElement().getAgent(nom)
							.make_actionsAgent().setVitesse(vitesse);
				}

			}

			@Override
			public void setPasAPas(boolean actif) {
				for (String nom : agents.keySet()) {
					parts().agents().getElement().getAgent(nom)
							.make_actionsAgent().setPasAPas(actif);
				}
			}

			@Override
			public void setPause(boolean actif) {
				for (String nom : agents.keySet()) {
					parts().agents().getElement().getAgent(nom)
							.make_actionsAgent().setPause(actif);
				}
			}

		};
	}

	@Override
	protected ProxysAgent make_proxys() {
		return new ProxysAgentEcoSystemImpl();
	}

	@Override
	protected Agents make_agents() {
		return new AgentsEcosystemImpl();
	}

	@Override
	protected ICreateAgentAvecProxy make_createAgentAvecProxy() {
		return new ICreateAgentAvecProxy() {

			@Override
			public IInfos createAgentAvecProxy(String nom, Position position,
					String couleur) {
				if (!agents.containsKey(nom)) {
					agents.put(nom, newAgentAvecProxy(nom, position, couleur));
					
					return parts().agents().getElement().getAgent(nom).make_infosAgent();
				}
				
				return null;
			}
		};
	}

	@Override
	protected IInfosAgents make_infosSurAgents() {
		return new IInfosAgents() {
			
			@Override
			public String getType(String nomAgent) {
				if(agents.containsKey(nomAgent)){
					return parts().agents().getElement().getAgent(nomAgent).make_infosAgent().getType();
				}
				
				return null;
			}
			
			@Override
			public Position getPosition(String nomAgent) {
				if(agents.containsKey(nomAgent)){
					return parts().agents().getElement().getAgent(nomAgent).make_infosAgent().getPosition();
				}
				
				return null;
			}
			
			@Override
			public String getNom(String nomAgent) {
				if(agents.containsKey(nomAgent)){
					return parts().agents().getElement().getAgent(nomAgent).make_infosAgent().getNom();
				}
				
				return null;
			}
			
			@Override
			public int getEnergie(String nomAgent) {
				if(agents.containsKey(nomAgent)){
					return parts().agents().getElement().getAgent(nomAgent).make_infosAgent().getEnergie();
				}
				
				return -1;
			}
			
			@Override
			public String getCouleur(String nomAgent) {
				if(agents.containsKey(nomAgent)){
					return parts().agents().getElement().getAgent(nomAgent).make_infosAgent().getCouleur();
				}
				
				return null;
			}
		};
	}

}
