package classes;

import SMA.BigComponent.Component;

import composants.BigComponentImpl;


public class Main {

	public static void main(String[] args) {
		
		SMA.BigComponent.Component bigComponent = new BigComponentImpl().newComponent();
		bigComponent.control().lancerSystem();

		
		//		SMA.AgentsAvecProxyEcosystem.Component agents = new AgentsAvecProxyEcosystemImpl()._newComponent(b, start)
//		Component logger = new LoggerImpl().newComponent();
//		SMA.System.Component system = new SystemImpl().newComponent();
//		
//		SMA.AgentsAvecProxyEcosystem ecosystem = new AgentsAvecProxyEcosystemImpl();
//		
//		// creation d'agents
//		Agent.Component a1 = system.create().createAgent("a1", new Position(0,0), Couleurs.BLEU.toString());
//		Agent.Component a2 = system.create().createAgent("a2", new Position(1,1), Couleurs.ROUGE.toString());
//		
//		a1.actionAgentService().deplacer(new Position(2, 2));
//		System.out.println(a2.infosService().getCouleur());
	}

}
