package composants;

import interfaces.IInfos;
import interfaces.IInfosSetLog;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import SMA.Logger;
import classes.Position;

public class LoggerImpl extends Logger{
	
	private final String baseName = "LogSMA-";
	private SimpleDateFormat dateFormat;
	private PrintWriter writer;
	
	public LoggerImpl() {
		try {
			
			Path currentRelativePath = Paths.get("");
			String currentDirectory = currentRelativePath.toAbsolutePath().toString();
			
			dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			Date date = new Date();
			String dateLog = dateFormat.format(date);
			dateLog = dateLog.replace(" ", "_");
			dateLog = dateLog.replace("/", "-");
			dateLog = dateLog.replace(":", "-");
			
			String fileName = currentDirectory+baseName+dateLog;
			System.out.println("Log file : " + fileName);
			
			File file = new File(fileName);
			
			writer = new PrintWriter(file, "UTF-8");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected IInfosSetLog make_log() {
		return new IInfosSetLog() {
			
			@Override
			public void setInfoPrendreBoite(IInfos agent, IInfos boite) {
				StringBuilder sb = new StringBuilder();
				sb.append("L'agent ");
				sb.append(agent.getNom());
				sb.append(" (");
				sb.append(agent.getCouleur());
				sb.append(") prend la boite ");
				sb.append(boite.getNom());
				sb.append(" (");
				sb.append(boite.getCouleur());
				sb.append(")");
				
				ecrireLog(sb.toString());
			}
			
			@Override
			public void setInfoDeposerBoite(IInfos agent, IInfos boite, IInfos nid) {
				StringBuilder sb = new StringBuilder();
				sb.append("L'agent ");
				sb.append(agent.getNom());
				sb.append(" (");
				sb.append(agent.getCouleur());
				sb.append(") dépose la boite ");
				sb.append(boite.getNom());
				sb.append(" (");
				sb.append(boite.getCouleur());
				sb.append(") dans le nid ");
				sb.append(nid.getNom());
				sb.append(" (");
				sb.append(nid.getCouleur());
				sb.append(")");
				
				ecrireLog(sb.toString());
			}
			
			@Override
			public void setInfoDeplacer(IInfos agent, Position oldPosition, IInfos boitePossede, Position newPosition) {
				StringBuilder sb = new StringBuilder();
				sb.append("L'agent ");
				sb.append(agent.getNom());
				sb.append(" (");
				sb.append(agent.getCouleur());
				sb.append(") se déplace en (");
				sb.append(newPosition.getX());
				sb.append(", ");
				sb.append(newPosition.getY());
				sb.append(") ");
				if(boitePossede != null){
					sb.append("avec une boite");
				}
				
				ecrireLog(sb.toString());
			}

			@Override
			public void setNouveauElement(IInfos nouveauElement) {
				StringBuilder sb = new StringBuilder();
				sb.append("Ajout de l'element ");
				sb.append(nouveauElement.getNom());
				sb.append(" à la position : ");
				sb.append(nouveauElement.getPosition().getX());
				sb.append(", ");
				sb.append(nouveauElement.getPosition().getY());
				ecrireLog(sb.toString());
			}

			@Override
			public void suicideAgent(IInfos agent) {
				StringBuilder sb = new StringBuilder();
				sb.append("L'agent ");
				sb.append(agent.getNom());
				sb.append(" est mort");
				ecrireLog(sb.toString());
			}

			@Override
			public void setInfosInitiales(List<IInfos> infosInitiales,
					int nbLignes, int nbColonnes) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	private void ecrireLog(String log){
			Date date = new Date();
			writer.print("["+dateFormat.format(date)+"] ");
			writer.println(log);
			writer.flush();
	}
	
	public IInfosSetLog getInfosSetLog(){
		return make_log();
	}

}
