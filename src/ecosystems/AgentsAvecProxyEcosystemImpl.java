package ecosystems;

import interfaces.IActionsAgent;
import interfaces.ICreateAgentAvecProxy;
import interfaces.IInfos;

import java.util.HashMap;
import java.util.Map;

import composants.AgentImpl;
import classes.Position;
import SMA.Agents;
import SMA.AgentsAvecProxyEcosystem;
import SMA.ProxysAgent;

public class AgentsAvecProxyEcosystemImpl extends AgentsAvecProxyEcosystem {
	
	Map<String, AgentAvecProxy.Component> agents = new HashMap<>();

	@Override
	protected IInfos make_infos() {
		AgentImpl agent = parts().agents().getElement().getAgent("agent1");
		if(agent != null){
			return agent.make_infosAgent();
		}
		else {
			return new IInfos() {
				
				@Override
				public String getType() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Position getPosition() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getNom() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public int getEnergie() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public String getCouleur() {
					// TODO Auto-generated method stub
					return null;
				}
			};
		}
	}

	@Override
	protected IActionsAgent make_actionsAgentsProxy() {
		return provides().actionsAgentsProxy();
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
			public void createAgentAvecProxy(String nom, Position position, String couleur) {
				if(!agents.containsKey(nom)){
					agents.put(nom, newAgentAvecProxy(nom, position, couleur));
				}
			}
		};
	}

}
