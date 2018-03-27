package edu.upenn.cis350.hwk4;

import java.util.TreeMap;

public class TeamEloStore {
	TreeMap<String, Double> getTeamEloStore(String readerType) {
		TeamEloReaderFactory terf = new TeamEloReaderFactory();
		TeamEloReader reader = terf.getTeamEloStore(readerType);
		
		TreeMap<String, Double> elos = reader.read();
		return elos;
	}
}
