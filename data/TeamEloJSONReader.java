package edu.upenn.cis350.hwk4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class TeamEloJSONReader extends TeamEloReader {

	@Override
	public TreeMap<String, Double> read() {
		TreeMap<String, Double> teamElos = new TreeMap<String, Double>();
		File eloFile = new File(Main.getEloFile());
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(eloFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String str;
		try {
			while ((str = br.readLine()) != null) {
				if (str.equals("[")) {
					continue;
				} else if (str.contains("{")) {
					String teamName = "";
					double elo = 0.0;
					for (int i = 0; i < 4; i++) {
						str = br.readLine();
						if (i == 0) {
							teamName = str.substring(str.indexOf(':') + 3, str.lastIndexOf('"'));
						} else if (i == 3) {
							elo = Double.parseDouble(str.substring(str.indexOf(':') + 2));
						}
					}
					teamElos.put(teamName, elo);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return teamElos;
	}

}
