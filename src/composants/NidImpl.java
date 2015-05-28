package composants;

import classes.Position;
import enums.Types;
import interfaces.IInfos;

public class NidImpl implements IInfos{
	
	private String nom;
	private String couleur;
	private Position position;
	private String type;
	
	public NidImpl(String nom, String couleur, Position position) {
		this.nom = nom;
		this.couleur = couleur;
		this.position = position;
		this.type = Types.NID.toString();
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
	public int getEnergie() {
		return -1;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
}