package interfaces;

public interface IControl {
	
	public void mettreEnPause(boolean pause);
	public void modePasAPas(boolean actif);
	public void changeVitesse(int vitesse);
	public void initialiserSystem();
	public void lancerSystem();
	public void setNombreAgents(int nbAgents);
	public void setNombreBoites(int nbBoites);
	public void gestionApparitionBoites(int nbBoite, int delais);
	public void persisterSystem();
	public void changeTailleGrille(int nbLignes, int nbColonnes);
	public void chargerEtatInitial(String nomFichier);
}