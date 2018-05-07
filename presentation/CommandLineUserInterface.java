package edu.upenn.cis350.hwk4;

import java.util.Scanner;

public class CommandLineUserInterface {
	// options for user activities
	private String option1 = "1) Simulate a single hypothetical game";
	private String option2 = "2) Simulate the entire tournament";
	private String option3 = "3) Change a single team's elo rating";
	private String option4 = "4) Exit";
	
	private ControllerTier controller;
	
	public CommandLineUserInterface() {
		controller = new ControllerTier();
	}
	
	public void present() {
		prompt();
		System.out.println(" \n" + "Enter your choice");
		Scanner in = new Scanner(System.in);
		int choice = -1;
		if (!in.hasNextInt()) {
			present();
		} else {
			choice = in.nextInt();
		}
		Logger logger = Logger.getInstance();
		logger.logUserInput("User chose option " + choice);
		if (choice == 1) {
			String teamA = in.nextLine().toLowerCase();
			while (!controller.teamExists(teamA)) {
				optionOnePrompt("first");
				teamA = in.nextLine();
				logger.logUserInput("First team: " + teamA);
				teamA = teamA.toLowerCase();
			}
			
			optionOnePrompt("second");
			String teamB = in.nextLine();
			logger.logUserInput("Second team: " + teamB);
			teamB = teamB.toLowerCase();
			while (!controller.teamExists(teamB)) {
				optionOnePrompt("second");
				teamB = in.nextLine().toLowerCase();
				logger.logUserInput("Second team: " + teamB);
			}
			controller.simulateGame(DataTier.getProperTeamName(teamA), DataTier.getProperTeamName(teamB));
			present();
		} else if (choice == 2) {
			optionTwoPrompt();
			int stratChoice = in.nextInt();
			logger.logUserInput("Strategy: " + stratChoice);
			if (stratChoice == 1) {
				controller.simulateTournament(new CoinFlipStrategy());
				present();
			} else if (stratChoice == 2) {
				controller.simulateTournament(new EloStrategy());
				present();
			} else if (stratChoice == 3) {
				controller.simulateTournament(new FavoriteWinsStrategy());
				present();
			} else {
				System.out.println("Number must be an integer between 1 and 3, inclusive.");
				present();
			}
		} else if (choice == 3) {
			String team = in.nextLine();
			while (!controller.teamExists(team)) {
				optionThreePrompt();
				team = in.nextLine();
				logger.logUserInput("Team: " + team);
				team = team.toLowerCase();
			}
			team = team.toLowerCase();
			optionThreePromptElo();
			double newElo = in.nextDouble();
			logger.logUserInput("New elo score: " + newElo);
			controller.setNewElo(DataTier.getProperTeamName(team), newElo);
			present();
		} else if (choice == 4) {
			logger.close();
			in.close();
			System.exit(0);
		} else {
			present();
		}
	}
	
	// shows user prompt to pick an activity; gives list of options
	public void prompt() {
		System.out.println("\n" + option1 + "\n" + option2 + "\n" + option3 + "\n" + option4);
	}
	
	public void optionOnePrompt(String str) {
		System.out.println("Enter " + str + " team name.");
	}
	
	public void optionTwoPrompt() {
		System.out.println("How do you want to simulate the tournament?");
		String stratOne = "1) Coin flip -- every game is 50/50 odds";
		String stratTwo = "2) Elo -- every game is randomly decided but considering Elo";
		String stratThree = "3) Favorite wins -- The team with the higher Elo rating always wins. "
				+ "If two teams have the \n same Elo, pick one randomly";
		System.out.println(stratOne + "\n" + stratTwo + "\n" + stratThree);
	}
	
	public void optionThreePrompt() {
		System.out.println("Enter a team name.");
	}
	
	public void optionThreePromptElo() {
		System.out.println("Enter a number for that team's elo.");
	}
}
