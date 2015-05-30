package classes;

import composants.BigComponentImpl;
import composants.LoggerImpl;


public class Main {

	public static void main(String[] args) {
		
		SMA.BigComponent.Component bigComponent = new BigComponentImpl().newComponent();
		
		bigComponent.control().changeVitesse(50);
		bigComponent.control().setNombreBoites(15);
		bigComponent.control().setNombreAgents(5);
		
		LoggerImpl logger = new LoggerImpl();
		bigComponent.gestionLogger().ajoutLogger(logger.getInfosSetLog());
	}

}
