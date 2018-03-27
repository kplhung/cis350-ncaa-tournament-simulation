package edu.upenn.cis350.hwk4;

public class FavoriteWinsStrategy implements SimulationStrategy {

	@Override
	public String determineWinner(String teamA, String teamB, double eloA, double eloB) {
		if (eloA > eloB) {
			return teamA;
		} else if (eloA < eloB) {
			return teamB;
		} else {
			double rand = Math.random();
			if (rand < 0.5) {
				return teamA;
			} else {
				return teamB;
			}
		}
	}

}
