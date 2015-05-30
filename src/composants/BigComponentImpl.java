package composants;

import interfaces.IControl;
import interfaces.IGestionLogger;
import interfaces.IInfosSetLog;
import SMA.AgentsAvecProxyEcosystem;
import SMA.BigComponent;
import SMA.IHM;
import SMA.LogDispatcherEcosystem;
import SMA.System;
import ecosystems.AgentsAvecProxyEcosystemImpl;
import ecosystems.LogDispactcherEcosystemImpl;

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

	@Override
	protected LogDispatcherEcosystem make_logDispatcher() {
		return new LogDispactcherEcosystemImpl();
	}

	@Override
	protected IGestionLogger make_gestionLogger() {
		return new IGestionLogger() {
			
			@Override
			public void ajoutLogger(IInfosSetLog newLogger) {
				parts().logDispatcher().createLogger().createNewLogger(newLogger);
			}
		};
	}
	
	@Override
	protected void start() {
		provides().gestionLogger().ajoutLogger(parts().ihm().infosLog());
	}

}
