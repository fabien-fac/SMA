package composants;

import interfaces.IDemandeActionsAgent;
import interfaces.IPercevoir;
import SMA.ProxysAgent.ProxyAgent;

public class ProxyAgentImpl extends ProxyAgent{
	
	private String nomAgent;
	
	public ProxyAgentImpl(String nomAgent) {
		this.nomAgent = nomAgent;
	}

	@Override
	protected IPercevoir make_percevoirProxySpecy() {
		return eco_requires().percevoirProxy();
	}

	public String getNomAgent() {
		return nomAgent;
	}

	@Override
	protected IDemandeActionsAgent make_demandeActionProxySpecy() {
		return eco_requires().demandeActionFromProxy();
	}
}
