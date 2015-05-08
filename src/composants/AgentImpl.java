package composants;

import interfaces.IActionsAgent;
import interfaces.IInfos;
import SMA.Agents.Agent;
import classes.Position;
import enums.Types;

public class AgentImpl extends Agent implements IActionsAgent, IInfos{
	
	private final int INITIAL_ENERGIE = 100;
	
	private String nom;
	private int energie;
	private String couleur;
	private Position position;
	
	public AgentImpl(String nom, Position position, String couleur) {
		this.nom = nom;
		this.position = position;
		this.couleur = couleur;
		this.energie = INITIAL_ENERGIE;
	}

	@Override
	protected IInfos make_infosAgent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IActionsAgent make_actionsAgent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deplacer(Position position) {
		System.out.println("new position : " + position.getX() + ", " + position.getY());
		this.position = position;
	}

	@Override
	public void prendreBoite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deposerBoite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suicide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public String getCouleur() {
		return couleur;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public String getType() {
		return Types.AGENT.toString();
	}

	public int getEnergie() {
		return energie;
	}

}
