package interfaces;

import SMA.Agents;
import classes.Position;

public interface ICreateAgent {

	public Agents.Component createAgent(String name, Position position, String couleur);
	
}
