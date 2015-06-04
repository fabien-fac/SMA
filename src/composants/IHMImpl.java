package composants;

import interfaces.IInfos;
import interfaces.IInfosSetLog;

import java.util.List;

import sma_ihm.IhmFrame;
import SMA.IHM;
import classes.Position;

public class IHMImpl extends IHM {
	private IhmFrame ihmFrame;

	@Override
	protected IInfosSetLog make_infosLog() {
		return new IInfosSetLog() {

			@Override
			public void setInfoPrendreBoite(IInfos agent, IInfos boite) {
				ihmFrame.setInfoPrendreBoite(agent, boite);
			}

			@Override
			public void setInfoDeposerBoite(IInfos agent, IInfos boite,
					IInfos nid) {
				ihmFrame.setInfoDeposerBoite(agent, boite, nid);
			}

			@Override
			public void setInfoDeplacer(IInfos agent, Position oldPosition,
					IInfos boitePossede, Position newPosition) {
				ihmFrame.setInfoDeplacer(agent, oldPosition, boitePossede);
			}

			@Override
			public void setInfosInitiales(List<IInfos> infosInitiales) {
				ihmFrame.setInfoCase(infosInitiales);

			}

			@Override
			public void setNouveauElement(IInfos nouveauElement) {
				ihmFrame.setInfoCase(nouveauElement);

			}

			@Override
			public void suicideAgent(IInfos agent) {
				// TODO Auto-generated method stub
				
			}

		};
	}

	@Override
	protected void start() {
		ihmFrame = new IhmFrame(requires().controlIHM());
		ihmFrame.setTitle("Le SMA c'est super");
		ihmFrame.setDefaultCloseOperation(ihmFrame.EXIT_ON_CLOSE);
		ihmFrame.setVisible(true);
	}

	public IInfosSetLog getInfosSetLog() {
		return make_infosLog();
	}

}
