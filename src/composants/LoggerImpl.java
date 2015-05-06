package composants;

import java.util.Date;

import interfaces.ILog;
import SMA.Logger;

public class LoggerImpl extends Logger{

	@Override
	protected ILog make_log() {
		return new ILog() {
			
			@Override
			public void addLog(String log) {
				Date date = new Date();
				System.out.println("["+date.toString()+"] " + log);
			}
		};
	}

}
