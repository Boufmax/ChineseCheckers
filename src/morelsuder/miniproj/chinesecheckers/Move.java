package morelsuder.miniproj.chinesecheckers;
/**
 * List of movement for the game
 * @author morejere, lurolsut
 * @version 3
 *
 */
public enum Move {

	UP_LEFT(-1,-1),
	UP_RIGHT(-1,1),
	LEFT(0,-2),
	RIGHT(0,2),
	DOWN_LEFT(1,-1),
	DOWN_RIGHT(1,1);
	
	/**
	 *  x point of a movement
	 */
	private int x;
	
	/**
	 *  y point of a movement
	 */
	private int y;
	
	/**
	 * move constructor
	 * @param x
	 * @param y
	 */
	private Move(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * get x point of the selected movement
	 * @return this.x
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * get y point of the select movement
	 * @return this.y
	 */
	public int getY()
	{
		return this.y;
	}
}
