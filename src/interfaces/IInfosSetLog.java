package interfaces;

import java.util.List;

import classes.Position;

public interface IInfosSetLog {
	public void setInfoDeplacer(IInfos agent, Position position, boolean possedeBoite);
	public void setInfoPrendreBoite(IInfos agent, IInfos boite);
	public void setInfoDeposerBoite(IInfos agent, IInfos boite, IInfos nid);
	public void setInfosInitiales(List<IInfos> infosInitiales);
	public void setNouveauElement(IInfos nouveauElement);
}
