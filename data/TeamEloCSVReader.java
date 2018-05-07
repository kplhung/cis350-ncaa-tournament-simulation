package edu.upenn.cis350.hwk4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class TeamEloCSVReader extends TeamEloReader {
	@Override
	public TreeMap<String, Double> read() {
		TreeMap<String, Double> teamElos = new TreeMap<String, Double>();
		File eloFile = new File(Main.getEloFile());
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(eloFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String str;
		try {
			str = br.readLine();
			while ((str = br.readLine()) != null) {
				String team = str.substring(0, str.indexOf(','));
				Double elo = Double.parseDouble(str.substring(str.lastIndexOf(',') + 1));
				teamElos.put(team, elo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return teamElos;
	}

}
