package interfaces;

import java.util.List;

import classes.Position;

public interface IInfosSetLog {
	public void setInfoDeplacer(IInfos agent, Position oldPosition, IInfos boitePossede, Position newPosition);
	public void setInfoPrendreBoite(IInfos agent, IInfos boite);
	public void setInfoDeposerBoite(IInfos agent, IInfos boite, IInfos nid);
	public void setInfosInitiales(List<IInfos> infosInitiales, int nbLignes, int nbColonnes);
	public void setNouveauElement(IInfos nouveauElement);
	public void suicideAgent(IInfos agent);
}
