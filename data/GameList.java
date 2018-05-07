package edu.upenn.cis350.hwk4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class GameList {

	public TreeMap<Integer, Game> getGameList() {
		TreeMap<Integer, Game> gameList = new TreeMap<Integer, Game>();
		File gamesFile = new File(Main.getGamesFile());
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(gamesFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String str;
		try {
			br.readLine();
			while ((str = br.readLine()) != null) {
				int gameNumber = Integer.parseInt(str.substring(0, str.indexOf(',')));
				str = str.substring(str.indexOf(',') + 1);
				String teamA = str.substring(0, str.indexOf(','));
				String teamB = str.substring(str.indexOf(',') + 1, str.lastIndexOf(','));
				int nextGame = Integer.parseInt(str.substring(str.lastIndexOf(',') + 1));
				gameList.put(gameNumber, new Game(gameNumber, teamA, teamB, nextGame));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return gameList;
	}

}
