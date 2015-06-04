package interfaces;

import composants.AgentImpl;

import classes.DecisionAgentPrise;

public interface IAgirAgent {
	public void agir(DecisionAgentPrise decisionAgent, AgentImpl agentImpl, IInfos boitePossede);
}
