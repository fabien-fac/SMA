package ecosystems;

import interfaces.ICreateProxyAgent;
import SMA.ProxysAgent;

import composants.ProxyAgentImpl;

public class ProxysAgentEcoSystemImpl extends ProxysAgent {

	@Override
	protected ICreateProxyAgent make_createProxyAgent() {
		return new ICreateProxyAgent() {

			@Override
			public SMA.ProxysAgent.ProxyAgent.Component createProxyAgent(
					String nomAgent) {
				return newProxyAgent(nomAgent);
			}
		};
	}

	@Override
	protected ProxyAgent make_ProxyAgent(String nomAgent) {
		return new ProxyAgentImpl(nomAgent);
	}

}
