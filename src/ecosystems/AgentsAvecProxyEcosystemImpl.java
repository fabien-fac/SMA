package ecosystems;

import interfaces.IActionsAgent;
import interfaces.IInfos;

import java.util.HashMap;
import java.util.Map;

import SMA.Agents;
import SMA.AgentsAvecProxyEcosystem;
import SMA.ProxysAgent;

public class AgentsAvecProxyEcosystemImpl extends AgentsAvecProxyEcosystem {

	Map<String, Agents.Component> agents = new HashMap<String, Agents.Component>();

	@Override
	protected IInfos make_infos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IActionsAgent make_actionsAgentsProxy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ProxysAgent make_proxys() {
		return new ProxysAgentEcoSystemImpl();
	}

	@Override
	protected Agents make_agents() {
		return new AgentsEcosystemImpl();
	}

}
