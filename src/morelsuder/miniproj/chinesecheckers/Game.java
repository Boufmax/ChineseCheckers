package morelsuder.miniproj.chinesecheckers;

import java.util.List;
import java.util.Scanner;

/**
 * Creation of the Chinese Checkers' board
 * 
 * @author morejere, lurolsut
 * @version 9
 */

public class Game
{
	/**
	 * Board of the game
	 */
	private final Board board;
	
	/**
	 * List of playing players 
	 */
	private final List<Player> players;

	/**
	 * Game constructor : load a new Board.
	 * 
	 * @throws InvalidCharacterException,
	 *             InvalidFileException
	 */

	public Game(final List<Player> playerList) throws InvalidFileException, InvalidCharacterException
	{
		board = new Board(playerList.size());
		players = playerList;
	}
	
	/**
	 * To move a pawn
	 * @param oldLocation : old position of a pawn
	 * @param newLocation : future position of this pawn
	 */
	public void movePawn(Location oldLocation, Location newLocation)
	{
		board.move(oldLocation, newLocation);
	}
	/**
	 * Check if the game is done or not
	 * @return true if game is done, false until the game is done
	 */
	public boolean isWin(Player p, Location[] winningPos)
	{
		return board.checkIfWin(p,winningPos);
	}
	
	/**
	 * To start the game, until the game is done
	 */
	public void start()
	{
		Scanner sc = new Scanner(System.in);
		boolean isWin = false;
		System.out.print(board.toString());
		
		//Set winning position to player
		for(Player p:this.players)
		{
			setWinningPositionToPlayers(p);
		}
		
		//Loop for the game
		while(true)
		{
			for(Player p: this.players)
			{
				System.out.println(p.getplayerName() + " , it's your turn\n");
				Location[][] possibleMove = board.canMove(p.getColor());
				System.out.println(this.showPossibleMove(possibleMove));
				int choiceMove = choiceMove(sc, possibleMove[1]);
				this.movePawn(possibleMove[0][choiceMove], possibleMove[1][choiceMove]);
				if(this.isWin(p, p.getWinningPos()))
				{
					System.out.println(p.getplayerName() + " has win");
					isWin = true;
					break;
				}
				System.out.print(board.toString());
			}
			if(isWin)
			{
				break;
			}
		}	
	}
	
	/**
	 * Method to choose the move of a pawn
	 * 
	 * @param sc : to enter the index of chosen move
	 * @param listOfMove : the list of available move
	 * @return
	 */
	
	private int choiceMove(Scanner sc, Location[] listOfMove)
	{
		int choiceMove;
		do 
		{
			System.out.println("Choose one of the proposed next position");
			choiceMove = sc.nextInt();
			sc.nextLine();
		} while(choiceMove < 0 && choiceMove > listOfMove.length);
		return choiceMove;
	}
	
	private void setWinningPositionToPlayers(Player p)
	{
		p.setWinningPos(board.setWinningPosition(p.getColor()));
	}
	
	/**
	 * Build a string in order to show the possible movement
	 * 
	 * @param move : available move for a position
	 * @return a String that contains all possible movement in the move array.
	 */
	
	private String showPossibleMove(Location[][] move)
	{
		String str ="Possible move :\n\n";
		for(int i = 0;i<move[0].length;i++)
		{
			char c = (char)(65 +move[1][i].getY());
			char b = (char)(65 + move[0][i].getY());
			str += String.valueOf(i)+ " : "  +"(" + move[0][i].getX() + ", " + b +") --> "+ "("+move[1][i].getX() + ", "+ c + ")\n";
		}
		return str;
	}
}
