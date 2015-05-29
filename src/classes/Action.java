package classes;

import interfaces.IInfos;
import enums.Actions;

public class Action {
	
	private long id;
	private Actions action;
	private IInfos agent;
	private IInfos boite;
	private IInfos nid;
	private Position position;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Actions getAction() {
		return action;
	}
	public void setAction(Actions action) {
		this.action = action;
	}
	public IInfos getAgent() {
		return agent;
	}
	public void setAgent(IInfos agent) {
		this.agent = agent;
	}
	public IInfos getBoite() {
		return boite;
	}
	public void setBoite(IInfos boite) {
		this.boite = boite;
	}
	public IInfos getNid() {
		return nid;
	}
	public void setNid(IInfos nid) {
		this.nid = nid;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
}
