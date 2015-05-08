package ecosystems;

import interfaces.ICreateAgent;
import SMA.Agents;
import classes.Position;

import composants.AgentImpl;

public class AgentsEcosystemImpl extends Agents{

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
		return new AgentImpl(nom, position, couleur);
	}

}
