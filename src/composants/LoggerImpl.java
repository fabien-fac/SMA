package composants;

import interfaces.IInfos;
import interfaces.IInfosSetLog;
import SMA.Logger;
import classes.Position;

public class LoggerImpl extends Logger{

	@Override
	protected IInfosSetLog make_log() {
		return new IInfosSetLog() {
			
			@Override
			public void setInfoPrendreBoite(IInfos agent, IInfos boite) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setInfoDeposerBoite(IInfos agent, IInfos boite, IInfos nid) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setInfoDeplacer(IInfos agent, Position position, boolean possedeBoites) {
				// TODO Auto-generated method stub
				
			}
		};
	}

}
