package interfaces;

import classes.Position;
import SMA.System;

public interface ICreateElement {

	public System.Agent.Component createAgent(String name, Position position, String couleur);
	
}
