package classes;

import interfaces.IInfos;

import java.util.ArrayList;
import java.util.List;

import enums.Actions;

public class Action {
	
	private long id;
	private Actions action;
	private IInfos agent;
	private IInfos boite;
	private IInfos nid;
	private Position oldPosition;
	private Position newPosition;
	private List<IInfos> nouveauxElements = new ArrayList<IInfos>();
	
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
	
	public Position getOldPosition() {
		return oldPosition;
	}
	public void setOldPosition(Position oldPosition) {
		this.oldPosition = oldPosition;
	}
	public Position getNewPosition() {
		return newPosition;
	}
	public void setNewPosition(Position newPosition) {
		this.newPosition = newPosition;
	}
	public List<IInfos> getNouveauxElements() {
		return nouveauxElements;
	}
	public void setNouveauxElements(List<IInfos> nouveauxElements) {
		this.nouveauxElements = nouveauxElements;
	}
	
}
