package edu.upenn.cis350.hwk4;

public class Team extends Observer implements Comparable<Team> {
	private String name;
	private double elo;
	private int numWins, numLosses;
	public Team(String name, Double elo) {
		this.name = name;
		this.elo = elo;
		numWins = 0;
		numLosses = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public double getElo() {
		return elo;
	}
	
	public int getNumWins() {
		return numWins;
	}
	
	public int getNumLosses() {
		return numLosses;
	}
	
	public void update(Game g) {
		if (name.equals(g.getWinner())) {
			numWins++;
		} else if (name.equals(g.getLoser())) {
			numLosses++;
		}
	}
	
	public void update(Subject s) {
		update((Game) s);
	}

	@Override
	public int compareTo(Team o) {
		return this.name.compareTo(o.getName());
	}
}
