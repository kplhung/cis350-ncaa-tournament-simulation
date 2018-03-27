package edu.upenn.cis350.hwk4;

public class Game extends Subject {
	private String teamA, teamB, winner, loser;
	private int gameNumber, nextGame;
	
	public Game(int gameNumber, String teamA, String teamB, int nextGame) {
		this.gameNumber = gameNumber;
		this.teamA = teamA;
		this.teamB = teamB;
		this.nextGame = nextGame;
	}
	
	public void setTeamA(String teamA) {
		this.teamA = teamA;
	}
	
	public void setTeamB(String teamB) {
		this.teamB = teamB;
	}
	
	public int getGameNumber() {
		return gameNumber;
	}
	
	public int getNextGameNumber() {
		return nextGame;
	}
	
	public String getTeamA() {
		return teamA;
	}
	
	public String getTeamB() {
		return teamB;
	}
	
	public void setWinner(SimulationStrategy s) {
		this.winner = s.determineWinner(teamA, teamB, DataTier.getTeamElo(teamA), DataTier.getTeamElo(teamB));
		if (winner.equals(teamA)) {
			this.loser = teamB;
		} else {
			this.loser = teamA;
		}
		notifyAllObservers();
	}
	
	public String getWinner() {
		return winner;
	}
	
	public String getLoser() {
		return loser;
	}
	
	
}
