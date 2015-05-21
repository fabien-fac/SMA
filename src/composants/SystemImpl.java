package composants;

import classes.Position;
import enums.Couleurs;
import interfaces.IControl;
import interfaces.IPercevoir;
import SMA.System;

public class SystemImpl extends System{

	@Override
	protected IPercevoir make_perceptionSystem() {
		return new IPercevoir() {
			
		};
	}

	@Override
	protected IControl make_control() {
		return new IControl() {
			
			@Override
			public void modePasAPas(boolean actif) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mettreEnPause() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void changeVitesse(int vitesse) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void lancerSystem() {
				requires().createAgents().createAgentAvecProxy("agent1", new Position(0, 1),  Couleurs.BLEU.toString());
				requires().createAgents().createAgentAvecProxy("agent2", new Position(1, 2),  Couleurs.ROUGE.toString());
				
			}
		};
	}

}
