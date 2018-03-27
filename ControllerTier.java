package edu.upenn.cis350.hwk4;

public class ControllerTier {
	DataTier dataManager;
	
	public ControllerTier() {
		dataManager = new DataTier();
	}
	
	/*
	 * Checks to see if team exists in data layer based on elofile
	 * 
	 * @param: String name of team to check for existence
	 * @return: true if team exists in elofile, false otherwise
	 */
	public boolean teamExists(String teamName) {
		return dataManager.teamExists(teamName);
	}

	/*
	 * States % chance team A will beat team B and simulates game 
	 * 
	 * @param: String name of first team in single hypothetical game
	 * @param: String name of second team in single hypothetical game
	 */
	public void simulateGame(String teamA, String teamB) {
		@SuppressWarnings("static-access")
		int teamAElo = dataManager.getTeamElo(teamA);
		@SuppressWarnings("static-access")
		int teamBElo = dataManager.getTeamElo(teamB);
		SimulationStrategy s = new EloStrategy();
		double dr = teamAElo - teamBElo;
		double winExpectancy = 1 / (Math.pow(10, (-dr/400)) + 1);
		System.out.println(teamA + " will beat " + teamB + " with " + 
				winExpectancy + " chance.");
		String winner = s.determineWinner(teamA, teamB, teamAElo, teamBElo);
		String result = winner + " beat ";
		if (winner.equals(teamA)) {
			result += teamB;
		} else {
			result += teamA;
		}
		System.out.println(result);
		Logger logger = Logger.getInstance();
		logger.logGameResult(result);
	}
	
	public void simulateTournament(SimulationStrategy s) {
		Bracket b = new Bracket(dataManager.getGameList(), s, dataManager.getTeams());
		b.simulate();
	}

	public void setNewElo(String team, double newElo) {
		dataManager.setNewElo(team, newElo);
	}
}
