package ecosystems;

import interfaces.ICreateLogger;
import interfaces.IInfosSetLog;
import interfaces.ISignalLog;

import java.util.ArrayList;
import java.util.List;

import SMA.LogDispatcherEcosystem;
import classes.Action;

public class LogDispactcherEcosystemImpl extends LogDispatcherEcosystem {

	List<IInfosSetLog> loggersProxy = new ArrayList<IInfosSetLog>();

	@Override
	protected ISignalLog make_signalLog() {
		return new ISignalLog() {

			@Override
			public void signalNewLog(int id) {
				Action action = requires().logsEntrant().getAction(id);
				if (action != null) {

					for (IInfosSetLog logger : loggersProxy) {
						switch (action.getAction()) {
						case DEPLACEMENT:
							logger.setInfoDeplacer(action.getAgent(),
									action.getPosition(), null);
							break;
						case DEPLACEMENT_AVEC_BOITE:
							logger.setInfoDeplacer(action.getAgent(),
									action.getPosition(), action.getBoite());
							break;
						case PRENDRE_BOITE:
							logger.setInfoPrendreBoite(action.getAgent(),
									action.getBoite());
							break;
						case DEPOSER_BOITE:
							logger.setInfoDeposerBoite(action.getAgent(),
									action.getBoite(), action.getNid());
							break;
						case INITIALISATION:
							logger.setInfosInitiales(action
									.getNouveauxElements());
							break;
						case NOUVEL_ELEMENT:
							logger.setNouveauElement(action
									.getNouveauxElements().get(0));
							break;
						case SUICIDE_AGENT:
							logger.suicideAgent(action.getAgent());
							break;
						default:
							break;
						}
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
