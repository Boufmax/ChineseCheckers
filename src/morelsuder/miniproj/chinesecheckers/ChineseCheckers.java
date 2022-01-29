package morelsuder.miniproj.chinesecheckers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Chinese Checkers game
 *
 * @author morejere, lurulsut
 * @version 7
 */
public class ChineseCheckers {
	public static void main(String[] args) {
			/**
			 * Scanner is used to load information about : - number of player - names of
			 * players
			 */
			try (final Scanner sc = new Scanner(System.in)) {

			// number of player of the game (2,3,4,6)
			int nbPlayer = askNumberOfPlayers(sc);

			// Initialization of playerList
			List<Player> playerList = new ArrayList<Player>();
			for (int i = 0; i  < nbPlayer; i++) {
				System.out.println("Player "+i+" = color : " + Color.values()[i]);
				String pseudo = sc.nextLine();
				Color color = Color.values()[i];
				playerList.add(new Player(pseudo, color));
			}
			
			//Start the game
			try {
				Game g = new Game(playerList);
				g.start();

			} catch (InvalidFileException e1) {
				System.err.println(" Invalid file : " + e1.getMessage());
				System.exit(-1);
			} catch (InvalidCharacterException e2) {
				e2.printStackTrace();
				System.err.println("Invalid character read : " + e2.getMessage());
				System.exit(-2);
			}
		}

	}

	/**
	 * to ask the number of player
	 * @param sc
	 */
	private static int askNumberOfPlayers(Scanner sc) {
		int nbPlayer;
		do {
			System.out.println("Set the number of players (2,3,4 or 6)");
			nbPlayer = sc.nextInt();
			sc.nextLine();
		} while ((nbPlayer != 2) && (nbPlayer != 3) && (nbPlayer != 4) && (nbPlayer != 6));
		return nbPlayer;
	}	

}
