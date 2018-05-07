package edu.upenn.cis350.hwk4;

public class EloStrategy implements SimulationStrategy {

	// every game is randomly decided, but considering elo
	@Override
	public String determineWinner(String teamA, String teamB, double eloA, double eloB) {
		double dr = eloA - eloB;
		double winExpectancy = 1 / (Math.pow(10, (-dr/400)) + 1);
		double rand = Math.random();
		if (rand < winExpectancy) {
			return teamA;
		} else {
			return teamB;
		}
	}
}
