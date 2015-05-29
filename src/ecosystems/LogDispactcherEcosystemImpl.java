package ecosystems;

import interfaces.ICreateLogger;
import interfaces.IInfosSetLog;
import interfaces.ISignalLog;

import java.util.ArrayList;
import java.util.List;

import SMA.LogDispatcherEcosystem;
import classes.Action;

public class LogDispactcherEcosystemImpl extends LogDispatcherEcosystem{
	
	List<IInfosSetLog> loggersProxy = new ArrayList<IInfosSetLog>();

	@Override
	protected ISignalLog make_signalLog() {
		return new ISignalLog() {
			
			@Override
			public void signalNewLog(int id) {
				Action action = requires().logsEntrant().getAction(id);
				
				for(IInfosSetLog logger : loggersProxy){
					switch (action.getAction()) {
					case DEPLACEMENT:
						logger.setInfoDeplacer(action.getAgent(), action.getPosition(), false);
						break;
					case DEPLACEMENT_AVEC_BOITE:
						logger.setInfoDeplacer(action.getAgent(), action.getPosition(), true);
						break;
					case PRENDRE_BOITE:
						logger.setInfoPrendreBoite(action.getAgent(), action.getBoite());
						break;
					case DEPOSER_BOITE:
						logger.setInfoDeposerBoite(action.getAgent(), action.getBoite(), action.getNid());
					default:
						break;
					}
				}
			}
			
		};
	}

	@Override
	protected ICreateLogger make_createLogger() {
		
		return new ICreateLogger() {
			
			@Override
			public void createNewLogger(IInfosSetLog loggerDest) {
				loggersProxy.add(loggerDest);
			}
		};
	}


}
