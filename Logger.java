package edu.upenn.cis350.hwk4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	private static Logger instance;
	private FileWriter writer;
	
	private Logger() {
		try {
			writer = new FileWriter(new File(Main.getLogFile()), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	public void logUserInput(String s) {
		try {
			writer.write(s + '\n');
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logGameResult(String result) {
		try {
			writer.write(result + '\n');
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
