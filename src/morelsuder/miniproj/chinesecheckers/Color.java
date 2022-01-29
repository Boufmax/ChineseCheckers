package morelsuder.miniproj.chinesecheckers;

/**
 * Enumeration of color
 * 
 * @author morejere,lurolsut
 * @version 3
 */
public enum Color {

	RED("R"), BLACK("B"), WHITE("W"), YELLOW("Y"), GREEN("G"), ORANGE("O"), EMPTY("*"), INVALID(" ");

	/**
	 * Representation of a color
	 */
	String color;
	
	/**
	 * Color construction 
	 * @param nameColor
	 */
	Color(String nameColor) {
		this.color = nameColor;
	}

	/**
	 * Override of the valueOf method
	 * 
	 * @param c
	 * @return value Of the input character
	 * @throws InvalidCharacterException
	 */
	public static Color valueOf(char c) throws InvalidCharacterException {
		final String s = String.valueOf(c);

		for (final Color tempColor : Color.values()) {
			if (s.equals(tempColor.color)) {
				return tempColor;
			}
		}
		throw new InvalidCharacterException(String.format("\"%X\" is an unknown Color!", (int) c));
	}

	/**
	 * Override of the toString method
	 */
	public String toString() {
		return this.color;
	}
}