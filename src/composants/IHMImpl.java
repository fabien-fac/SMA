package composants;

import classes.Position;
import interfaces.IInfos;
import interfaces.IInfosSetLog;
import SMA.IHM;

public class IHMImpl extends IHM{

	@Override
	protected IInfosSetLog make_infosLog() {
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
			public void setInfoDeplacer(IInfos agent, Position position, boolean possedeBoite) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public IInfosSetLog getInfosSetLog(){
		return make_infosLog();
	}
	
}