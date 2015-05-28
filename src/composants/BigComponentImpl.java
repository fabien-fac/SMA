package composants;

import interfaces.IControl;
import SMA.AgentsAvecProxyEcosystem;
import SMA.BigComponent;
import SMA.IHM;
import SMA.System;
import ecosystems.AgentsAvecProxyEcosystemImpl;

public class BigComponentImpl extends BigComponent{

	@Override
	protected System make_system() {
		return new SystemImpl(); 
	}

	@Override
	protected AgentsAvecProxyEcosystem make_agents() {
		return new AgentsAvecProxyEcosystemImpl();
	}

	@Override
	protected IControl make_control() {
		return parts().system().control();
	}

	@Override
	protected IHM make_ihm() {
		return new IHMImpl();
	}

}
