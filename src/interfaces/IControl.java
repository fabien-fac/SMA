package interfaces;

public interface IControl {
	
	public void mettreEnPause(boolean pause);
	public void modePasAPas(boolean actif);
	public void changeVitesse(int vitesse);
	public void lancerSystem();
	public void setNombreAgents(int nbAgents);
	public void setNombreBoites(int nbBoites);
}
