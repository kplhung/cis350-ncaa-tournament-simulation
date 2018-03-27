package edu.upenn.cis350.hwk4;

import java.util.TreeMap;
import java.util.TreeSet;

public class Bracket extends Observer{
	TreeMap<Integer, Game> games;
	SimulationStrategy strategy;
	TreeSet<Team> teams;
	
	public Bracket(TreeMap<Integer, Game> games, SimulationStrategy strategy, TreeSet<Team> teams) {
		this.games = games;
		this.strategy = strategy;
		this.teams = teams;
	}

	/**
	 * Attaches this bracket to all games
	 */
	public void attachBracketToAllGames() {
		for (Game g : games.values()) {
			g.attach(this);
		}
	}
	
	/**
	 * Attaches all teams to all games
	 */
	public void attachTeamsToGames() {
		for (Game g : games.values()) {
			for (Team t : teams) {
				g.attach(t);
			}
		}
	}
	
	public void printTeamResultsHeading() {
		System.out.println("=====Team Results=====");
	}
	
	public void printTournamentTeamResult(String team, int numWins, int numLosses) {
		System.out.println(team + " : " + numWins + "W - " + numLosses + "L");
	}
	
	public void printGameResultsHeading() {
		System.out.println("=====Game Results======");
	}
	
	public void printTournamentTeamResult(int gameNumber, String teamA, String teamB, String winner) {
		System.out.println("Game #" + gameNumber + " - " + teamA + " vs. " + teamB + ", winner: " + winner);
	}
	
	public void simulate() {
		attachBracketToAllGames();
		attachTeamsToGames();
		for (Game g : games.values()) {
			g.setWinner(strategy);
		}
		
		// log team results
		printTeamResultsHeading();
		for (Team t : teams) {
			printTournamentTeamResult(t.getName(), t.getNumWins(), t.getNumLosses());
		}
		
		// log game results
		System.out.println("");
		printGameResultsHeading();
		for (Game g : games.values()) {
			printTournamentTeamResult(g.getGameNumber(), g.getTeamA(), g.getTeamB(), g.getWinner());
		}
	}
	
	public void updateGame(Game g) {
		String winner = g.getWinner();
		String loser = g.getLoser();
		int nextGame = g.getNextGameNumber();
		String result = winner + " beat " + loser;
		Logger logger = Logger.getInstance();
		logger.logGameResult(result);
		if (nextGame == -1) {
			// final game
			return;
		}
		Game next = games.get(nextGame);
	
		if (next.getTeamA().equals("")) {
			next.setTeamA(winner);
		} else {
			next.setTeamB(winner);
		}
	}

	@Override
	public void update(Subject s) {
		updateGame((Game) s);
	}
}