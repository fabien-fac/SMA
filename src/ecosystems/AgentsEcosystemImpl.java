package ecosystems;

import interfaces.ICreateAgent;
import interfaces.IGetAgent;

import java.util.HashMap;
import java.util.Map;

import SMA.Agents;
import classes.Position;

import composants.AgentImpl;

public class AgentsEcosystemImpl extends Agents{
	
	private Map<String, AgentImpl> agents = new HashMap<>();
	
	@Override
	protected ICreateAgent make_createElement() {
		return new ICreateAgent() {
			
			@Override
			public Agents.Component createAgent(String name, Position position, String couleur) {
				return createAgent(name, position, couleur);
			}
		};
	}

	@Override
	protected Agent make_Agent(String nom, Position position, String couleur) {
		AgentImpl agentImpl = null;
		
		if(!agents.containsKey(nom)){
			agentImpl = new AgentImpl(nom, position, couleur);
			agents.put(nom, agentImpl);
		}
		
		return agentImpl;
	}

	@Override
	protected IGetAgent make_getElement() {
		return new IGetAgent() {
			
			@Override
			public AgentImpl getAgent(String nom) {
				if(agents.containsKey(nom)){
					return agents.get(nom);
				}
				
				return null;
			}
		};
	}
	
}
