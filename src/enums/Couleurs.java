package enums;

public enum Couleurs {
	BLEU("bleu"),
	ROUGE("rouge"),
	VERT("vert");
	
	private String couleur;
	
	private Couleurs(String couleur) {
		this.couleur = couleur;
	}
	
	@Override
	public String toString() {
		return couleur;
	}
}
