package classes;

import composants.BigComponentImpl;
import composants.IHMImpl;
import composants.LoggerImpl;


public class Main {

	public static void main(String[] args) {
		
		SMA.BigComponent.Component bigComponent = new BigComponentImpl().newComponent();
		
		bigComponent.control().changeVitesse(1000);
		bigComponent.control().setNombreBoites(5);
		bigComponent.control().setNombreAgents(10);
		
		
		IHMImpl ihmImpl = new IHMImpl();
		bigComponent.gestionLogger().ajoutLogger(ihmImpl.getInfosSetLog());
		LoggerImpl logger = new LoggerImpl();
		bigComponent.gestionLogger().ajoutLogger(logger.getInfosSetLog());
		
		bigComponent.control().lancerSystem();
	}

}
