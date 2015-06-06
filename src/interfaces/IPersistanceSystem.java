package interfaces;

import classes.EtatInitial;

public interface IPersistanceSystem {
	public void sauvegarderSystem(EtatInitial etatInitial);
	public EtatInitial getEtatInitial(String nomFichier);
}
