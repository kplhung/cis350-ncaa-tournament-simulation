package edu.upenn.cis350.hwk4;

public class TeamEloReaderFactory {
	// factory method
	public TeamEloReader getTeamEloStore(String str) {
		if (str.equalsIgnoreCase("csv")) {
			return new TeamEloCSVReader();
		} else if (str.equalsIgnoreCase("json")) {
			return new TeamEloJSONReader();
		} else {
			return null;
		}
	}
}
