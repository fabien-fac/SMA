package composants;

import interfaces.IActionsAgent;
import interfaces.IInfos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SMA.Agents.Agent;
import SMA.AgirAgent;
import SMA.DecisionAgent;
import SMA.PerceptionAgent;
import classes.DecisionAgentPrise;
import classes.Position;
import enums.Types;

public class AgentImpl extends Agent {

	private String nom;
	private int energie;
	private String couleur;
	private Position position;

	private int vitesse = 1000;
	private boolean actif = true;

	private IInfos boitePossede = null;
	private Map<String, IInfos> nids = new HashMap<String, IInfos>();

	public AgentImpl(String nom, Position position, String couleur) {
		this.nom = nom;
		this.position = position;
		this.couleur = couleur;
	}

	@Override
	public IInfos make_infosAgent() {
		return new IInfos() {

			@Override
			public String getType() {
				return Types.AGENT.toString();
			}

			@Override
			public Position getPosition() {
				return position;
			}

			@Override
			public String getNom() {
				return nom;
			}

			@Override
			public int getEnergie() {
				return energie;
			}

			@Override
			public String getCouleur() {
				return couleur;
			}
		};
	}

	@Override
	public IActionsAgent make_actionsAgent() {
		return new IActionsAgent() {

			@Override
			public void setVitesse(int _vitesse) {
				vitesse = _vitesse;
			}

			@Override
			public void setPasAPas(boolean actif) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setPause(boolean _actif) {
				actif = !_actif;
			}

		};
	}

	@Override
	protected PerceptionAgent make_percepetion() {
		return new PerceptionAgentImpl();
	}

	@Override
	protected DecisionAgent make_decision() {
		return new DecisionAgentImpl();
	}

	@Override
	protected AgirAgent make_action() {
		return new AgirAgentImpl();
	}

	@Override
	protected void start() {

		List<IInfos> nidsList = requires().demandeAction().getListNids();
		energie = requires().demandeAction().getInitialEnergie();
		vitesse = requires().demandeAction().getVitesse();

		for (IInfos nid : nidsList) {
			nids.put(nid.getCouleur(), nid);
		}

		new Thread() {
			public void run() {
				while (energie > 0) {
					if (actif) {
						lancerCycle();
					}
					try {
						Thread.sleep(vitesse);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("fin agent");
			}

		}.start();

	}

	private void lancerCycle() {
		List<IInfos> infosElementAutour = parts().percepetion()
				.perceptionAgent().getInfosElementAutour(position);
		List<IInfos> infosElementAPosition = parts().percepetion()
				.perceptionAgent().getInfosElementAPosition(position);

		DecisionAgentPrise decision = parts()
				.decision()
				.decisionAgent()
				.getDecision(infosElementAutour, infosElementAPosition,
						make_infosAgent(), boitePossede);
		
		parts().action().action().agir(decision, this, boitePossede);
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void ajouterEnergie(int energie) {
		this.energie += energie;
	}

	public void setBoite(IInfos boite) {
		boitePossede = boite;
	}
	
	public Map<String, IInfos> getNids() {
		return nids;
	}

}
