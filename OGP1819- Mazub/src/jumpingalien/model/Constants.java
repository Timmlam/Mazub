package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Value;

/**
 * A class of constants that are used in the game of Mazub.
 * 
 * @version 3.0
 * 
 * @author Timm Lamberechts
 * @author Maarten Volkaerts
 *
 */
@Value
public class Constants {
	/**
	 * Constant registering the vertical fall acceleration of the game objects in the gaming world.
	 */
	protected static final double  VERTICAL_FALL_ACCELERATION=-10;
	
	/**
	 * Constant registering the initial jumping velocity of Mazub.
	 */
	protected static final double INIT_JUMPING_VELOCITY = 8;
}
