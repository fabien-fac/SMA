package composants;

import java.util.ArrayList;
import java.util.List;

import interfaces.IAddAction;
import interfaces.IInfosGetLog;
import SMA.ActionsSystem;
import classes.Action;

public class ActionsSystemImpl extends ActionsSystem{
	
	private Object lockActions = new Object();
	private long cptActions = 0;
	private List<Action> actions = new ArrayList<Action>();

	@Override
	protected IAddAction make_addAction() {
		return new IAddAction() {
			
			@Override
			public void addAction(Action action) {
				synchronized (lockActions) {
					action.setId(cptActions);
					actions.add((int) cptActions, action);

					requires().signalLog().signalNewLog((int) cptActions);
					cptActions++;
				}
			}
		};
	}

	@Override
	protected IInfosGetLog make_getLog() {
		return new IInfosGetLog() {
			
			@Override
			public Action getAction(int idAction) {
				Action action = null;
				synchronized (lockActions) {
					if (idAction <= cptActions) {
						action = actions.get(idAction);
					}
					return action;
				}
			}
		};
	}

}
