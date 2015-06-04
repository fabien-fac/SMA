package interfaces;

import java.util.List;

import classes.DecisionAgentPrise;

public interface IDecisionAgent {
	public DecisionAgentPrise getDecision(List<IInfos> elementsAutour, List<IInfos> elementsMemePosition, IInfos self, IInfos boitePossede); 
}
