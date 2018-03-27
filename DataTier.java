package edu.upenn.cis350.hwk4;

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class DataTier {
	private static TreeMap<String, Double> teamElo;
	private TreeMap<Integer, Game> gameList;
	private static TreeSet<Team> teams;
	private static TreeMap<String, String> normalizedTeamNames;
	
	public DataTier() {
		makeTeamEloStore();
		makeGameList();
		makeTeamSet();
	}
	
	public void makeTeamEloStore() {
		TeamEloStore tes = new TeamEloStore();
		if (Main.getEloFile().contains(".csv")) {
			teamElo = tes.getTeamEloStore("csv");
		} else if (Main.getEloFile().contains(".json")) {
			teamElo = tes.getTeamEloStore("json");
		}
		normalizedTeamNames = new TreeMap<String, String>();
		for (String s : teamElo.keySet()) {
			normalizedTeamNames.put(s.toLowerCase(), s);
		}
	}
	
	public void makeTeamSet() {
		teams = new TreeSet<Team>();
		for (Entry<String, Double> e : teamElo.entrySet()) {
			teams.add(new Team(e.getKey(), e.getValue()));
		}
	}
	
	public void makeGameList() {
		GameList gl = new GameList();
		gameList = gl.getGameList();
	}
	
	public static String getProperTeamName(String name) {
		return normalizedTeamNames.get(name);	
	}
	
	public static int getTeamElo(String team) {
		return teamElo.get(team).intValue();
	}

	public boolean teamExists(String teamName) {
		return normalizedTeamNames.containsKey(teamName);
	}
	
	public TreeMap<Integer, Game> getGameList() {
		TreeMap<Integer, Game> gameListCopy = new TreeMap<Integer, Game>();
		for (Entry<Integer, Game> e : gameList.entrySet()) {
			gameListCopy.put(e.getKey(), new Game(e.getValue().getGameNumber(), 
					e.getValue().getTeamA(), e.getValue().getTeamB(),
					e.getValue().getNextGameNumber()));
		}
		return gameListCopy;
	}
	
	public TreeSet<Team> getTeams() {
		TreeSet<Team> teamsCopy = new TreeSet<Team>();
		for (Team t : teams) {
			teamsCopy.add(new Team(t.getName(), t.getElo()));
		}
		return teamsCopy;
	}

	public void setNewElo(String team, double newElo) {
		teamElo.put(team, newElo);
	} 
}
