package classes;

import java.io.Serializable;

import interfaces.IInfos;

public class ElementDTO implements IInfos, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Position position;
	private String couleur;
	private int energie;
	private String type;
	private String nom;

	@Override
	public Position getPosition() {
		return position;
	}

	@Override
	public String getCouleur() {
		return couleur;
	}

	@Override
	public int getEnergie() {
		return energie;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}