package interfaces;

import java.util.List;

import classes.Position;


public interface IPercevoir {
	List<IInfos> getInfosElementAutour(Position position);
	List<IInfos> getInfosElementAPosition(Position position);
}
