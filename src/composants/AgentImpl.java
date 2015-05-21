package composants;

import interfaces.IActionsAgent;
import interfaces.IInfos;
import SMA.Agents.Agent;
import classes.Position;
import enums.Types;

public class AgentImpl extends Agent{
	
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
	public IInfos make_infosAgent() {
		return new IInfos() {
			
			@Override
			public String getType() {
				return Types.AGENT.toString();
			}
			
			@Override
			public Position getPosition() {
				return position;
			}
			
			@Override
			public String getNom() {
				return nom;
			}
			
			@Override
			public int getEnergie() {
				return energie;
			}
			
			@Override
			public String getCouleur() {
				return couleur;
			}
		};
	}

	@Override
	protected IActionsAgent make_actionsAgent() {
		return new IActionsAgent() {
			
			@Override
			public void deplacer(Position _position) {
				System.out.println("new position : " + _position.getX() + ", " + _position.getY());
				position = _position;
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
		};
	}

}
