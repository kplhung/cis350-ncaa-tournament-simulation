package edu.upenn.cis350.hwk4;

public class CoinFlipStrategy implements SimulationStrategy {

	@Override
	public String determineWinner(String teamA, String teamB, double eloA, double eloB) {
		double rand = Math.random();
		if (rand < 0.5) {
			return teamA;
		} else {
			return teamB;
		}
	}
}
