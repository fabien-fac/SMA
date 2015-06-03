package composants;

import java.util.List;

import classes.Position;
import interfaces.IInfos;
import interfaces.IPercevoir;
import SMA.PerceptionAgent;

public class PerceptionAgentImpl extends PerceptionAgent{

	@Override
	protected IPercevoir make_perceptionAgent() {
		return new IPercevoir() {
			
			@Override
			public List<IInfos> getInfosElementAutour(Position position) {
				return requires().perception().getInfosElementAutour(position);
			}
			
			@Override
			public List<IInfos> getInfosElementAPosition(Position position) {
				return requires().perception().getInfosElementAPosition(position);
			}
		};
	}

}
