package interfaces;

import java.util.List;

import classes.Position;

public interface IDemandeActionsAgent {

	public boolean deplacer(IInfos agent, Position position, IInfos boitePossede);
	public boolean prendreBoite(IInfos agent, IInfos boite);
	public int deposerBoite(IInfos agent, IInfos boite, IInfos nid);
	public List<IInfos> getListNids();
	public int getInitialEnergie();
	public void suicide(IInfos agent);
	public int getVitesse();
}
