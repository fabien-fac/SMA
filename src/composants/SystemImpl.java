package composants;

import interfaces.ICreateElement;
import SMA.System;
import classes.Position;

public class SystemImpl extends System {
	
	public Component newComponent() {
		return new Component() {
			@Override
			public ICreateElement create() {
				return make_create();
			}
		};
	}
	
	@Override
	protected ICreateElement make_create() {
		return new ICreateElement() {
			
			@Override
			public Agent.Component createAgent(String nom, Position position, String couleur) {
				return newAgent(nom, position, couleur);
			}
		};
	}

	@Override
	protected Agent make_Agent(String nom, Position position, String couleur) {
		log("Création agent : " + nom + ", " + couleur + position);
		return new AgentImpl(nom, position, couleur);
	}

	private void log(String log){
		requires().logSystem().addLog(log);
	}
}
