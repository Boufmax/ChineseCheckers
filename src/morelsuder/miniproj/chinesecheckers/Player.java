package morelsuder.miniproj.chinesecheckers;

/**
 * @author morejere, lurolsut
 * @version 7
 */

public class Player
{
	/**
	 * Color of a player
	 */

	private Color color;
	/**
	 * name of the player
	 */

	private String playerName;
	
	/**
	 * Array of Location for a player to know his winning position.
	 */
	private Location[] winningPos;

	/**
	 * Player constructor
	 * 
	 * @param playerName
	 * @param color
	 */
	public Player(String playerName, Color color)
	{
		this.playerName = playerName;
		this.color = color;
	}
	
	/**
	 * Winning position setter
	 * @param win
	 */
	public void setWinningPos(Location[] win)
	{
		this.winningPos = win;
	}
	
	/**
	 * get current color
	 * @return color
	 */
	public Color getColor()
	{
		return this.color;
	}
	
	/**
	 * get current playerName
	 * @return playerName
	 */
	public String getplayerName()
	{
		return this.playerName;
	}
	
	/**
	 * Get get winning position for a player
	 * @return
	 */
	public Location[] getWinningPos()
	{
		return this.winningPos;
	}
	

}
