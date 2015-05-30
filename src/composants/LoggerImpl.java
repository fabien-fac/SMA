package composants;

import interfaces.IInfos;
import interfaces.IInfosSetLog;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import SMA.Logger;
import classes.Position;

public class LoggerImpl extends Logger{
	
	private final String baseName = "C:/LogSMA-";
	private SimpleDateFormat dateFormat;
	private PrintWriter writer;
	
	public LoggerImpl() {
		try {
			dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			Date date = new Date();
			String dateLog = dateFormat.format(date);
			dateLog = dateLog.replace(" ", "_");
			dateLog = dateLog.replace("/", "-");
			
			dateLog = baseName+dateLog;
			
			File file = new File(dateLog);
			
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
			public void setInfoDeplacer(IInfos agent, Position position, boolean possedeBoite) {
				StringBuilder sb = new StringBuilder();
				sb.append("L'agent ");
				sb.append(agent.getNom());
				sb.append(" (");
				sb.append(agent.getCouleur());
				sb.append(") se déplace en (");
				sb.append(position.getX());
				sb.append(", ");
				sb.append(position.getY());
				sb.append(") ");
				if(possedeBoite){
					sb.append("avec une boite");
				}
				
				ecrireLog(sb.toString());
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
