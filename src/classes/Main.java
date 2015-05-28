package classes;

import composants.BigComponentImpl;


public class Main {

	public static void main(String[] args) {
		
		SMA.BigComponent.Component bigComponent = new BigComponentImpl().newComponent();
		
		bigComponent.control().changeVitesse(200);
		bigComponent.control().setNombreBoites(5);
		bigComponent.control().setNombreAgents(10);
		
		bigComponent.control().lancerSystem();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		bigComponent.control().changeVitesse(2000);
	}

}
