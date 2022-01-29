package morelsuder.miniproj.chinesecheckers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class which will contains method to initialize the board
 *
 * @author morejere, lurolsut
 * @version 12
 */
public class Board
{
	/**
	 * Number of column for the string array
	 * 
	 */
	private static final int COLUMN = 25;
	/**
	 * Number of row for the string array
	 */
	private static final int ROW = 17;
	/** Allowed no matter the number of players. */
	private static final List<Character> GLOBALLY_ALLOWED_CHARS = Arrays.asList('\n', ' ', 'R', '*');
	/** Allowed depending the number of players. */
	private static final Character[][] ALLOWED_CHARS = { {}, {}, { 'B' }, { 'W', 'B' }, { 'W', 'B', 'Y' }, {},
			{ 'G', 'B', 'Y', 'W', 'O' } };
	/**
	 * String array which will contains all the element of the board
	 */
	private final Color[][] board;
	private final int numberOfPlayer;

	/**
	 * Board constructor.
	 * 
	 * @param numberOfPlayer : the number of player who are playing
	 * @throws InvalidFileException,
	 *             InvalidCharacterException
	 */
	public Board(int numberOfPlayer) throws InvalidFileException, InvalidCharacterException
	{
		this.board = new Color[ROW][COLUMN];
		this.numberOfPlayer = numberOfPlayer;
		initWithFile();
	}

	/**
	 * Initialize the board with a file according to numberOfPlayer
	 * 
	 * @throws InvalidCharacterException,
	 *             InvalidFileException
	 */
	private void initWithFile() throws InvalidFileException, InvalidCharacterException
	{
		try (final BufferedReader br = new BufferedReader(new FileReader(String.format("star%dP.txt", numberOfPlayer))))
		{
			int i = 0;
			int j = 0;
			int ch;

			while ((ch = br.read()) != -1)
			{
				final char c = (char) ch;

				if (!isValid(c))
				{
					throw new InvalidCharacterException(String.format("Invalid character read (%X), file may be corrupted", (int)c));
				}

				if (c == '\n')
				{
					i++;
					j = 0;
				} else
				{
					board[i][j++] = Color.valueOf(c);
				}

			}
		} catch (final FileNotFoundException e)
		{
			throw new InvalidFileException("File not foud");
		} catch (final IOException e1)
		{
			throw new InvalidFileException("Invalid File");
		}
	}
	
	/**
	 * Method that detect possible movements of pawn for a player.
	 * @param C, color of a player
	 * @return Array with pawn which can move and their possible future positions
	 */
	public Location[][] canMove(Color C)
	{
		ArrayList<Location> pawnCanMove = new ArrayList<Location>();
		ArrayList<Location> possibleMove = new ArrayList<Location>();
		
		for(int i = 0 ; i<ROW ; i++)
		{
			for(int j = 0 ; j<COLUMN ; j++)
			{
				for(Move move:Move.values())
				{
					int newX = move.getX();
					int newY = move.getY();
					if(board[i][j] == C) //Check if a pawn can move
					{
						if(i+newX >= 0 && j+newY >= 0 && i+newX<ROW && j+newY < ROW) 
						{
								if(board[i+newX][j+newY] != Color.INVALID)
								{
									if(board[i+newX][j+newY] == Color.EMPTY)
									{
										pawnCanMove.add(new Location(i,j));
										possibleMove.add(new Location(i+newX,j+newY));
									}
								}
							}
						}
					}
				}
			}
		Location[] Pawn = new Location[pawnCanMove.size()]; //Array that contains current position of pawns
		Pawn = pawnCanMove.toArray(Pawn);
		Location[] move = new Location[possibleMove.size()]; //Array that contains possible future position of pawns
		move = possibleMove.toArray(move);
		Location[][] canMove = {Pawn,move}; //Array that contains the 2 other array
		return canMove;
	}
	/**
	 * 
	 * @param position
	 * @return the new position of the pawn
	 */
	public void move(Location oldPosition, Location newPosition)
	{
		 board[newPosition.getX()][newPosition.getY()] = board[oldPosition.getX()][oldPosition.getY()];
		 board[oldPosition.getX()][oldPosition.getY()] = Color.EMPTY;
	}
	
	/**
	 * Future method to check if a player has won.
	 * @param : a player and his winning position array
	 * @return true if a player has win, false while a player has not win.
	 */
	public boolean checkIfWin(Player p, Location[] winningPos)
	{
		final int AMOUNT_OF_PAWN = 10;
		int cpt = 0;
		for(int i = 0 ; i<winningPos.length ; i++)
		{
			if(cpt == AMOUNT_OF_PAWN)
			{
				return true;
			}
			if(board[winningPos[i].getX()][winningPos[i].getY()] == p.getColor())
			{
				cpt++;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param ch : character read
	 * @return true if the character i valid, else it returns false and catch InvalidCharacterException
	 */
	private boolean isValid(final char ch)
	{
		return GLOBALLY_ALLOWED_CHARS.contains(ch) || Arrays.asList(ALLOWED_CHARS[numberOfPlayer]).contains(ch);
	}
	
	/**
	 * Create an array of the winning location  and set it to player
	 * @param c, the color of a player
	 * @return winning location
	 */
	public Location[] setWinningPosition(Color c)
	{
		final int AMOUNT_OF_PAWN = 10;
		int nbPawn=0;
		Location[] winningPos = new Location[AMOUNT_OF_PAWN];
		while(nbPawn != AMOUNT_OF_PAWN)
		{
			for(int i = 0 ; i<ROW ; i++)
			{
				for(int n = 0 ; n<COLUMN ; n++)
				{
					if(this.board[i][n] == c)
					{
						winningPos[nbPawn] = new Location(ROW-i-1,COLUMN-n-1);
						nbPawn++;
					}
				}
			}
		}
			
		return winningPos;
	}

	/**
	 * Print board on the console.
	 * @return a string that will display the board
	 */

	public String toString()
	{
		String str ="  ";
		for(int i = 0 ; i< COLUMN; i++)
		{
			int l = ((char)65 + i);
			str += String.valueOf((char)l);
		}
		str += "\n";
		for (int i = 0; i < ROW; i++)
		{
			if(i<=9)
			{
				str += String.valueOf(i) + "  ";
			}
			else
			{
				str += String.valueOf(i) + " ";
			}
			for (int n = 0; n < COLUMN; n++)
			{
				str += (board[i][n]);
			}
			str += "\n";
		}
		return str;
	}

}
