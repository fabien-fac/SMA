package composants;

import java.util.List;

import interfaces.IActionsAgent;
import interfaces.IInfos;
import SMA.Agents.Agent;
import classes.Position;
import enums.Types;

public class AgentImpl extends Agent{
	
	private final int INITIAL_ENERGIE = 100;
	
	private String nom;
	private int energie;
	private String couleur;
	private Position position;
	
	private int vitesse = 1000;
	private boolean actif = true;
	private boolean pasAPas;
	
	private IInfos boitePossede = null;
	
	public AgentImpl(String nom, Position position, String couleur) {
		this.nom = nom;
		this.position = position;
		this.couleur = couleur;
		this.energie = INITIAL_ENERGIE;
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
				System.out.println("new vitesse : " + vitesse);
			}

			@Override
			public void setPasAPas(boolean actif) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setPause(boolean actif) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	
	@Override
	protected void start() {
		new Thread(){
			public void run() {
				while(true){
					if(actif){
						agir();
					}
					System.out.println(nom);
					try {
						System.out.println("vitesse : " + vitesse);
						Thread.sleep(vitesse);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}.start();
		
	}
	
	private void agir() {
		System.out.print("Perception de " + nom + ": ");
		List<IInfos> infosElementAutour = requires().percevoirAgent().getInfosElementAutour(position);
		for(IInfos element : infosElementAutour){
			System.out.print("[(" + element.getType() +") " + element.getNom() +"]");
		}
		
	};

}
