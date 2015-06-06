package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EtatInitialDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ElementDTO> infos;
	private String vitesseApparitionBoite;
	private String nbApparitionBoite;
	private String nbLignes;
	private String nbColonnes;
	
	public EtatInitialDTO() {
		infos = new ArrayList<ElementDTO>();
	}

	public List<ElementDTO> getInfos() {
		return infos;
	}

	public void setInfos(List<ElementDTO> infos) {
		this.infos = infos;
	}

	public String getVitesseApparitionBoite() {
		return vitesseApparitionBoite;
	}

	public void setVitesseApparitionBoite(String vitesseApparitionBoite) {
		this.vitesseApparitionBoite = vitesseApparitionBoite;
	}

	public String getNbApparitionBoite() {
		return nbApparitionBoite;
	}

	public void setNbApparitionBoite(String nbApparitionBoite) {
		this.nbApparitionBoite = nbApparitionBoite;
	}

	public String getNbLignes() {
		return nbLignes;
	}

	public void setNbLignes(String nbLignes) {
		this.nbLignes = nbLignes;
	}

	public String getNbColonnes() {
		return nbColonnes;
	}

	public void setNbColonnes(String nbColonnes) {
		this.nbColonnes = nbColonnes;
	}
	
}
