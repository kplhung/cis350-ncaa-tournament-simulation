package edu.upenn.cis350.hwk4;

public class Main {
	private static String eloFile;
	private static String gamesFile;
	private static String logFile;
	
	public static void main(String[] args) {
		eloFile = args[0];
		gamesFile = args[1];
		logFile = args[2];
		
		CommandLineUserInterface userInterface = new CommandLineUserInterface();
		userInterface.present();
	}
	
	static String getEloFile() {
		return eloFile;
	}
	
	static String getGamesFile() {
		return gamesFile;
	}
	
	static String getLogFile() {
		return logFile;
	}
}
