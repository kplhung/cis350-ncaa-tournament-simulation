package edu.upenn.cis350.hwk4;

public interface SimulationStrategy {
	public String determineWinner(String teamA, String teamB, double eloA, double eloB);
}
