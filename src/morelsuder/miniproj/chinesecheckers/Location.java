package morelsuder.miniproj.chinesecheckers;
/**
 * Location gives the position of a pawn on the board
 * 
 * @author morejere, lurolsut
 * @version 4
 */
public class Location
{
	/**
	 * location x of a pawn
	 */
	private int x;

	/**
	 * location y of a pawn
	 */
	private int y;

	/**
	 * Location constructor.
	 */
	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return the x position of the location
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * 
	 * @return the y position of the location
	 */
	public int getY()
	{
		return this.y;
	}
	
	/**
	 * @return a string representation of a location
	 */
	public String toString()
	{
		return "("+this.x+", "+this.y+")";
	}
}
