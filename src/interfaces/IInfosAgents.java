package interfaces;

import classes.Position;

public interface IInfosAgents{
	public Position getPosition(String nomAgent);
	public String getCouleur(String nomAgent);
	public int getEnergie(String nomAgent);
	public String getNom(String nomAgent);
	public String getType(String nomAgent);
}
