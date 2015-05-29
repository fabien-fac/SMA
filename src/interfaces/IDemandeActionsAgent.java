package interfaces;

import java.util.List;

import classes.Position;

public interface IDemandeActionsAgent {

	public boolean deplacer(IInfos agent, Position position, boolean possedeBoite);
	public boolean prendreBoite(IInfos agent, IInfos boite);
	public int deposerBoite(IInfos agent, IInfos boite, IInfos nid);
	public List<IInfos> getListNids();
}
