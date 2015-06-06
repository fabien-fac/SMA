package interfaces;

import java.util.List;

public interface IPersistanceSystem {
	public void sauvegarderSystem(List<IInfos> infos, int vitesseApparitionBoite, int nbApparitionBoite);
}
