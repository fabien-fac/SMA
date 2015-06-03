package classes;

import interfaces.IInfos;
import enums.DecisionsAgent;

public class DecisionAgentPrise {

	private DecisionsAgent decisionsAgent;
	private IInfos boite;
	private IInfos nid;
	private String couleurNid;
	
	public DecisionAgentPrise() {
		super();
	}

	public DecisionsAgent getDecisionsAgent() {
		return decisionsAgent;
	}

	public void setDecisionsAgent(DecisionsAgent decisionsAgent) {
		this.decisionsAgent = decisionsAgent;
	}

	public IInfos getBoite() {
		return boite;
	}

	public void setBoite(IInfos boite) {
		this.boite = boite;
	}

	public String getCouleurNid() {
		return couleurNid;
	}

	public void setCouleurNid(String couleurNid) {
		this.couleurNid = couleurNid;
	}

	public void setNid(IInfos nid) {
		this.nid = nid;
	}

	public IInfos getNid() {
		return nid;
	}
}
