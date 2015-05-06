package classes;

import SMA.Logger.Component;
import SMA.System.Agent;
import composants.LoggerImpl;
import composants.SystemImpl;
import enums.Couleurs;

public class Main {

	public static void main(String[] args) {
		Component logger = new LoggerImpl().newComponent();
		SMA.System.Component system = new SystemImpl().newComponent();
		
		// creation d'agents
		Agent.Component a1 = system.create().createAgent("a1", new Position(0,0), Couleurs.BLEU.toString());
		Agent.Component a2 = system.create().createAgent("a2", new Position(1,1), Couleurs.ROUGE.toString());
		
		a1.actionAgentService().deplacer(new Position(2, 2));
		System.out.println(a2.infosService().getCouleur());
	}

}
