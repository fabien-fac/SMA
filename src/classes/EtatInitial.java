package classes;

import interfaces.IInfos;

import java.util.ArrayList;
import java.util.List;

public class EtatInitial {
	
	private List<IInfos> infos;
	private int vitesseApparitionBoite;
	private int nbApparitionBoite;
	private int nbLignes;
	private int nbColonnes;
	
	public EtatInitial() {
		infos = new ArrayList<IInfos> ();
	}
	
	public List<IInfos> getInfos() {
		return infos;
	}
	public void setInfos(List<IInfos> infos) {
		this.infos = infos;
	}
	public int getVitesseApparitionBoite() {
		return vitesseApparitionBoite;
	}
	public void setVitesseApparitionBoite(int vitesseApparitionBoite) {
		this.vitesseApparitionBoite = vitesseApparitionBoite;
	}
	public int getNbApparitionBoite() {
		return nbApparitionBoite;
	}
	public void setNbApparitionBoite(int nbApparitionBoite) {
		this.nbApparitionBoite = nbApparitionBoite;
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public void setNbLignes(int nbLignes) {
		this.nbLignes = nbLignes;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public void setNbColonnes(int nbColonnes) {
		this.nbColonnes = nbColonnes;
	}

}
