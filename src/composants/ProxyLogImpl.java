package composants;

import interfaces.IInfosSetLog;
import SMA.LogDispatcherEcosystem.ProxyLog;

public class ProxyLogImpl extends ProxyLog {

	private IInfosSetLog destinataire;

	public ProxyLogImpl(IInfosSetLog _destinataire) {
		destinataire = _destinataire;
	}

	public IInfosSetLog getDestinataire() {
		return destinataire;
	}
	
}
