package interfaces;

import classes.Position;

public interface IInfosSetLog {
	public void setInfoDeplacer(IInfos agent, Position position, boolean possedeBoite);
	public void setInfoPrendreBoite(IInfos agent, IInfos boite);
	public void setInfoDeposerBoite(IInfos agent, IInfos boite, IInfos nid);
}
