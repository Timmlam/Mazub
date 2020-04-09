package jumpingalien.model;

import java.util.Arrays;
import java.util.HashSet;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;


/**
 * A class of GameObjects with a given position, velocity, hitpoints and sprites.
 * @invar  The pixelX of each GameObject must be a valid pixelX for any
 *         GameObject.
 *       | this.hasValidPixelX(getPixelX)
 * @invar  The pixelY of each GameObject must be a valid pixelY for any
 *         GameObject.
 *       | this.hasValidPixelY(getPixelY) 
 * @invar  The orientation of each GameObject must be a valid orientation for any
 *         GameObject.
 *       | isValidOrientation(getOrientation()) 
 * @invar  The horizontalVelocity of each GameObject must be a valid horizontalVelocity for any
 *         GameObject.
 *       | isValidHorizontalVelocity(getHorizontalVelocity())
 * @invar  The minHorizontalVelocity of each GameObject must be a valid minHorizontalVelocity for any
 *         GameObject.
 *       | isValidMinHorizontalVelocity(getMinHorizontalVelocity())
 * @invar  The maxHorizontalVelocity of each GameObject must be a valid maxHorizontalVelocity for any
 *         GameObject.
 *       | isValidMaxHorizontalVelocity(getMaxHorizontalVelocity()) 
 * @invar  The verticalVelocity of each GameObject must be a valid verticalVelocity for any
 *         GameObject.
 *       | isValidVerticalVelocity(getVerticalVelocity())   
 * @invar  The horizontalAcceleration of each GameObject must be a valid horizontalAcceleration for any
 * 	       GameObject.
 *       | isValidHorizontalAcceleration(getHorizontalAcceleration())
 * @invar  The verticalAcceleration of each GameObject must be a valid verticalAcceleration for any
 *         GameObject.
 *       | isValidVerticalAcceleration(getVerticalAcceleration())
 * @invar  The sprites of each GameObject must be valid sprites for any
 *         GameObject.
 *       | isValidSprites(getSprites)
 * @invar  The currentSprite of each GameObject must be a valid currentSprite for any
 *         GameObject.
 *       | isValidCurrentSprite(getCurrentSprite())
 * @invar  The hitPoints of each GameObject must be a valid hitPoints for any
 *         GameObject.
 *       | isValidHitPoints(getHitPoints())
 *
 * @version 2.0       
 *       
 * @author Timm Lamberechts
 * @author Maarten Volkaerts
 * 
 */
public abstract class GameObject {
	
	/**
	 * Initialize this new GameObject with given actual position (x-coordinate and y-coordinate)
	 * and a given pixel position (pixelX and pixelY) .
	 *
	 * @param  pixelX
	 *         The pixelX in the gaming world  for this new GameObject.
	 * @param  pixelY
	 *         The pixelY in the gaming world  for this new GameObject. 
	 * @param  actualX
	 *         The x-coordinate in the actual world  for this new GameObject.
	 * @param  actualY
	 *         The y-coordinate in the actual world  for this new GameObject.
	 * @param  orientation
	 * 		   The orientation for this new GameObject.
	 * @param  horizontalVelocity
     *         The horizontalVelocity for this new GameObject.
     * @param  minHorizontalVelocity
     *         The minHorizontalVelocity for this new GameObject.
     * @param  maxHorizontalVelocity
     *         The maxHorizontalVelocity for this new GameObject.
     * @param  verticalVelocity
     *         The verticalVelocity for this new GameObject.
     * @param  horizontalAcceleration
     *         The horizontalAcceleration for this new GameObject.
     * @param  verticalAcceleration
     *         The verticalAcceleration for this new GameObject.
     * @param  isJumpingGameObject
     *         The isJumpingGameObject for this new GameObject.
     * @param  sprites
     *         The sprites for this new GameObject.
     * @param  currentSprite
     *         The currentSprite for this new GameObject.
     * @param  runtime
     *         The runtime for this new GameObject.
     * @param  standingStillTime
     *         The standingStillTime for this new GameObject.
     * @param  hitPoints
     *         The hitPoints for this new GameObject.
	 * @pre	 The given orientation is a valid orientation for this new GameObject. 
	 * 		 | hasValidOrientation(orientation)
	 * @pre    The given horizontalAcceleration must be a valid horizontalAcceleration for any GameObject.
	 *       | isValidHorizontalAcceleration(horizontalAcceleration)
	 * @post The actualX of this GameObject is set to
	 *       the given actualX.
	 *       | this.setActualX(actualX)
	 * @post The actualY of this GameObject is set to
	 *       the given actualY.
	 *       | this.setActualY(actualY)
	 * @post The pixelX of this GameObject is set to
	 *       the given pixelX.
	 *       | this.setPixelX(pixelX)
	 * @post The pixelY of this GameObject is set to
	 *       the given pixelY.
	 *       | this.setPixelY(pixelY)
	 * @post The orientation of this new GameObject is equal to the given
     *       orientation.
     *       | new.getOrientation() == orientation
     * @post The horizontalVelocity of this new GameObject is set to the given
     *       horizontalVelocity.
     *       | setHorizontalVelocity(horizontalVelocity)
     * @post The minHorizontalVelocity of this new GameObject is set to the given minHorizontalVelocity. 
     *       | setMinHorizontalVelocity(minHorizontalVelocity)
     * @post The maxHorizontalVelocity of this new GameObject is set to the given maxHorizontalVelocity. 
     *       | setMaxHorizontalVelocity(maxHorizontalVelocity)  
     * @post The verticalVelocity of this new GameObject is set to the given verticalalVelocity. 
     *       | setVerticalVelocity(verticalVelocity)
     * @post The horizontalAcceleration of this new GameObject is set to the given
     *       horizontalAcceleration.
     *       | setHorizontalAcceleration(horizontalAcceleration)
     * @post The verticalAcceleration of this new GameObject is set to
     *       the given verticalAcceleration.
     *       | this.setVerticalAcceleration(verticalAcceleration)
     * @post The hitPoints of this new GameObject are set to
     *       the given hitPoints.
     *       | this.setHitPoints(hitPoints)
     * @post The sprites of this new GameObject is set to
     *       the given sprites.
     *       | this.setSprite(sprites)
     * @post The currentSprite of this new GameObject is set to
     *         the given currentSprite.
     *       | this.setCurrentSprite(currentSprite)
	 */
	
	protected GameObject(int pixelX,int pixelY,double actualX,double actualY, String orientation,
			double horizontalVelocity,double minHorizontalVelocity, double maxHorizontalVelocity,
			double verticalVelocity, double horizontalAcceleration, double verticalAcceleration,int hitPoints, 
			Sprite currentSprite, Sprite ... sprites) 
					throws RuntimeException {
	
		this.setPixelX(pixelX);
		this.setPixelY(pixelY);
		this.setActualX(actualX);
		this.setActualY(actualY);
		this.setOrientation(orientation);
		this.setHorizontalVelocity(horizontalVelocity);
		this.setMinHorizontalVelocity(minHorizontalVelocity);
		this.setMaxHorizontalVelocity(maxHorizontalVelocity);
		this.setVerticalVelocity(verticalVelocity);
		this.setHorizontalAcceleration(horizontalAcceleration);
		this.setVerticalAcceleration(verticalAcceleration);
		this.setHitPoints(hitPoints);
		Sprite[] clonedSprites = Arrays.copyOf(sprites, sprites.length);
		this.setSprites(clonedSprites);
		this.setCurrentSprite(currentSprite);
		}
	
	/**
	 * Initialize this new GameObject with given pixel position.
	 *
	 * @param  pixelX
	 *         The x-coordinate of the pixel in the gaming world  for the left bottom corner this new GameObject.
	 * @param  pixelY
	 *         The y-coordinate of the pixel in the gaming world  for the left bottom corner this new GameObject.
	 * @param  sprites
	 *         The sprites for this new GameObject.
	 *         
	 * @effect This new GameObject is initialized with the given x-coordinate.
	 * 		 | this.setPixelX(pixelX)
	 * @effect This new GameObject is initialized with the given y-coordinate.
	 *       | this.setPixelY(pixelY)
	 * @effect converts the pixel position in the gaming world to the actual position.
	 * 		 | this.setActualX( (double) (pixelX/100))
	 * 		 | this.setActualY( (double) (pixelY/100))
	 * @effect The orientation is set to its default value "front". 
	 *       | this.setOrientaion("front")
	 * @effect The horizontal velocity is set to 0. 
	 *       | this.setHorizontalVelocity(0)
	 * @effect The minimum horizontal velocity is set to 1. 
	 *       | this.MinHorizontalVelocity(1)
	 * @effect The maximum horizontal velocity is set to 3. 
	 *       | this.MaxHorizontalVelocity(3)
	 * @effect The vertical velocity is initiated as 0. 
	 *       | this.VerticalVelocity(0)
	 * @effect The vertical acceleration is set to 0.
	 *       | this.verticalAcceleration(0) 
	 * @effect The horizontal acceleration is set to 0. 
	 *       | this.HorizontalAcceleration(0)
	 * @effect The sprites of this new GameObject is set to the given sprites.
	 *       | this.setSprites(sprites)
	 * @effect The current sprite is set to first sprite in the array of sprites.
	 *       | this.setCurrentSprite(sprites[0])
	 * @effect The runtime is set to 0. 
	 *       | this.setRuntime(0)
	 * @effect The state isJumpingGameObject is set to false.
	 *       | this.setIsJumpingGameObject(false)
	 */
	
	public GameObject(int pixelX,int pixelY,Sprite...sprites)
			throws RuntimeException {
		this(pixelX,pixelY,(double) pixelX/100,(double)pixelY/100,"front",0,1,3,0,0,0,100,
				sprites[0],sprites);
		}

	/**
	 * Return the actualX of this GameObject.
	 */
	@Basic 
	@Raw
	public double getActualX() {
		return this.actualX;
	}

	/**
	 * Set the actualX of this GameObject to the given actualX.
	 * 
	 * @param  actualX
	 *         The new actualX for this GameObject.
	 * @post   The actualX of this new GameObject is equal to
	 *         the given actualX.
	 *       | new.getActualX() == actualX
	 */
	@Raw
	public void setActualX(double actualX) { 
		this.actualX = actualX; 
	}

	/**
	 * Variable registering the actualX of this GameObject.
	 */
	protected double actualX;
	
	/**
	 * Return the actualY of this GameObject.
	 */
	@Basic 
	@Raw
	public double getActualY() {
		return this.actualY;
	}
	
	/**
	 * Set the actualY of this GameObject to the given actualY.
	 * 
	 * @param  actualY
	 *         The new actualY for this GameObject.
	 * @post   The actualY of this new GameObject is equal to
	 *         the given actualY.
	 *       | new.getActualY() == actualY
	 */
	@Raw
	public void setActualY(double actualY) { 
		this.actualY = actualY;
		
	}

	/**
	 * Variable registering the actualY of this GameObject.
	 */
	protected double actualY;

	/**
	 * Return the pixelX of this GameObject.
	 */
	@Basic @Raw
	public int getPixelX() {
		return this.pixelX;
	}

	/**
	 * Check whether the given pixelX is a valid pixelX for
	 * any GameObject.
	 *  
	 * @param  pixelX
	 *         The pixelX to check.
	 * @return True if and only if the given pixelX of the GameObject is not lower than zero and 
	 * 		   is not above the value of the width.  
	 *       | result == (pixelX >= 0) && (pixelX <= Double.POSITIVE_INFINITY   )	
	 */
	public boolean isValidPixelX(int pixelX) {
			return( (pixelX >= 0) && (pixelX <= Double.POSITIVE_INFINITY ));	
	}

	/**
	 * Set the pixelX of this GameObject to the given pixelX.
	 * 
	 * @param  pixelX
	 *         The new pixelX for this GameObject.
	 * @post   The pixelX of this new GameObject is equal to
	 *         the given pixelX.
	 *       | new.getPixelX() == pixelX
	 * @throws IllegalArgumentException
	 *         The given pixelX is not a valid pixelX for any
	 *         GameObject.
	 *       | (! isValidPixelX(getPixelX())
	 */
	@Raw
	public void setPixelX(int pixelX) 
			throws IllegalArgumentException {
		if (! isValidPixelX(pixelX)) {
			throw new IllegalArgumentException();
		}
		this.pixelX = pixelX;
	}

	/**
	 * Variable registering the pixelX of this GameObject.
	 */
	private int pixelX;
	
	/**
	 * Return the pixelY of this GameObject.
	 */
	@Basic @Raw
	public int getPixelY() {
		return this.pixelY;
	}

	/**
	 * Check whether the given pixelY is a valid pixelY for
	 * any GameObject.
	 *  
	 * @param  pixelY
	 *         The pixelY to check.
	 * @return True if and only if the given pixelY of the GameObject is not lower than zero and 
	 * 		   is not above the value of the height.  
	 *       | result == (pixelY >= 0) && (pixelY <= Double.POSITIVE_INFINITY  )
	 */
	public boolean isValidPixelY(int pixelY) {
		return (pixelY >= 0) && (pixelY <= Double.POSITIVE_INFINITY );
	}

	/**
	 * Set the pixelY of this GameObject to the given pixelY.
	 * 
	 * @param  pixelY
	 *         The new pixelY for this GameObject.
	 * @post   The pixelY of this new GameObject is equal to
	 *         the given pixelY.
	 *       | new.getPixelY() == pixelY
	 * @throws IllegalArgumentException
	 *         The given pixelY is not a valid pixelY for any
	 *         GameObject.
	 *       | ! isValidPixelY(getPixelY())
	 */
	@Raw
	public void setPixelY(int pixelY) 
			throws IllegalArgumentException {
		if (! isValidPixelY(pixelY))
			throw new IllegalArgumentException();
		this.pixelY = pixelY;
	}

	/**
	 * Variable registering the pixelY of this GameObject.
	 */
	private int pixelY;
	
	/**
	 * Return the height of the gaming world that applies to all GameObjects.
	 * This is the highest possible value for the pixelY of a GameObject. 
	 * @return if this.getWorld()!null then result is this.getWorld().getWorldHeight(). Else
	 * 			result is Integer.MAX_VALUE.
	 * 		  | if (  this.getWorld() != null) then
	 *		  |	result == this.getWorld().getWorldHeight()
	 *		  |	else then
	 *		  |	result == Integer.MAX_VALUE
	 */
	@Basic
	public  int getPixelHeight() {
		if (  this.getWorld() != null) {
			return this.getWorld().getWorldHeight();
		}
		else {
			return Integer.MAX_VALUE;
		}
	}
	
	/**
	 * Set the height of the gaming world that applies to all GameObjects to the given height. 
	 * @param pixelHeight
	 *  	  The new height of the gaming world.
	 * @post The new height of the gaming world that applies to all GameObjects is equal to the given
	 * 		 height.
	 * 		| GameObject.pixelHeight = pixelHeight
	 */
	private static void setPixelHeight(int pixelHeight) {
		GameObject.pixelHeight = pixelHeight;
	}
	
	/**
	 * Variable registering the pixel height of the gaming world.
	 */
	private static int pixelHeight = 760008;
	
	/**
	 * Return the width of the gaming world that applies to all GameObjects.
	 * This is the highest possible value for the pixelX of a GameObject. 
	 * @return if the GameObject has a world, the width of the world is returned.
	 * 		   Otherwise the highest possible integer is returned
	 * 		  | if (  this.getWorld() != null) then
	 *		  |	  result == this.getWorld().getWorldWidth()
	 *	      | else then
	 *		  |    result == Integer.MAX_VALUE
	 */
	@Basic
	public  int getPixelWidth() {
		if (  this.getWorld() != null)
				return this.getWorld().getWorldWidth();
		else {
			return Integer.MAX_VALUE;
		}
	}
	
	/**
	 * Set the width of the gaming world that applies to all GameObjects to the given width. 
	 * @param pixelWidth
	 *  	  The new width of the gaming world
	 * @post The new width of the gaming world that applies to all GameObjects is equal to the given
	 * 		 width.
	 * 		| GameObject.pixelWidth = pixelWidth
	 */
	private static void setPixelWidth(int pixelWidth) {
		GameObject.pixelWidth = pixelWidth;
	}
	
	/**
	 * Variable registering the actual pixel width of the gaming world.
	 */
	private static int pixelWidth = 1024000;
	
	/**
	 * Return the orientation of this GameObject.
	 */
	@Basic 
	@Raw
	public String getOrientation() {
		return this.orientation;
	}
	/**
	 * Check whether the given orientation is a valid orientation for
	 * any GameObject.
	 *  
	 * @param  orientation
	 *         The orientation to check.
	 * @return True if and only if the given orientation is either "left","right" or "front".
	 *       | result == 
	 *        (orientation == "left") | (orientation == "right") | (orientation == "front") | (orientation == "up") 			
	 */
	public static boolean isValidOrientation(String orientation) {
		return (orientation == "left") | (orientation == "right") | (orientation == "front") 
				 | (orientation == "up");
	}
	
	/**
	 * Set the orientation of this GameObject to the given orientation.
	 * 
	 * @param  orientation
	 *         The new orientation for this GameObject.
	 * @pre    The given orientation must be a valid orientation for any
	 *         GameObject.
	 *       | isValidOrientation(orientation)
	 * @post   The orientation of this GameObject is equal to the given
	 *         orientation.
	 *       | new.getOrientation() == orientation
	 */
	@Raw
	public void setOrientation(String orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	/**
	 * Variable registering the orientation of this GameObject.
	 */
	public String orientation;
	
	/**
	 * Return the horizontalVelocity of this GameObject.
	 */
	@Basic @Raw
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}
	
	/**
	 * Checks whether the given horizontal velocity is a valid horizontal velocity for any GameObject.
	 * @param horizontalVelocity
	 * 		  the horizontalVelocity to check
	 */
	public abstract boolean isValidHorizontalVelocity(double horizontalVelocity); 

	 /**
	  * Set the horizontalVelocity of this GameObject to the given horizontalVelocity.
	  * 
	  * @param  horizontalVelocity
	  *         The new horizontalVelocity for this GameObject.
	  * @post   If the given horizontalVelocity is a valid horizontalVelocity for any GameObject,
	  *         the horizontalVelocity of this new GameObject is equal to the given
	  *         horizontalVelocity. 
	  *       | new.getHorizontalVelocity() == horizontalVelocity
	  * @post   If the given horizontalVelocity is smaller in absolute value
	  *    	    than the maxHorizontalVelocity of the GameObject, the horizontalVelocity
	  * 	    is set to the minHorizontalVelocity. The sign depends on the orientation
	  *    	    of the GameObject.
	  *       | if (Math.abs(horizontalVelocity) < this.getMinHorizontalVelocity()) 
	  *  	  | if (this.getOrientation() == "left")
	  *		  |		this.horizontalVelocity = - this.getMinHorizontalVelocity()
	  *       | if (this.getOrientation() == "right")
	  *   	  |	 	this.horizontalVelocity =this.getMinHorizontalVelocity()
	  * @post   If the given horizontalVelocity is bigger in absolute value
	  * 		than the maxHorizontalVelocity of the GameObject, the horizontalVelocity
	  * 	    is set to the maxHorizontalVelocity and the horizontalAcceleration is set to zero.
	  * 		 The sign depends on the orientation of the GameObject.
	  *       | if (Math.abs(horizontalVelocity) > this.getMaxHorizontalVelocity()) 
	  * 	  |	this.setHorizontalAcceleration(0) 
	  * 	  |  if (this.getOrientation() == "left")
	  *		  |		this.horizontalVelocity = - this.getMaxHorizontalVelocity()
	  *       |  if (this.getOrientation() == "right")
	  *   	  |	 	this.horizontalVelocity =this.getMaxHorizontalVelocity()
	  * @post   If the given horizontalVelocity is the horizontalVelocity is set to zero
	  *       | this.horizontalVelocity = 0;
	  */
	@Raw
	public void setHorizontalVelocity(double horizontalVelocity) {
		if (isValidHorizontalVelocity(horizontalVelocity))
			this.horizontalVelocity = horizontalVelocity;
		else {
			if (Math.abs(horizontalVelocity) > this.getMaxHorizontalVelocity()) {
				this.setHorizontalAcceleration(0);
				if (this.getOrientation() == "left")
					this.horizontalVelocity = - this.getMaxHorizontalVelocity();
				if (this.getOrientation() == "right")
					this.horizontalVelocity =this.getMaxHorizontalVelocity();
			}
			if (Math.abs(horizontalVelocity) < this.getMinHorizontalVelocity()) {
				if (this.getOrientation() == "left")
					this.horizontalVelocity = - this.getMinHorizontalVelocity();
				if (this.getOrientation() == "right")
					this.horizontalVelocity = this.getMinHorizontalVelocity();
			}
			if (horizontalVelocity == 0) {
				this.horizontalVelocity = 0;
			}
		}		
					
	}
	
	/**
	 * Variable registering the horizontalVelocity of this GameObject.
	 */
	protected double horizontalVelocity;
	
	/**
	 * Return the minHorizontalVelocity of this GameObject.
	 */
	@Basic @Raw
	public double getMinHorizontalVelocity() {
		return this.minHorizontalVelocity;
	}
	
	/**
	 * Check whether the given minHorizontalVelocity is a valid minHorizontalVelocity for
	 * any GameObject.
	 *  
	 * @param  minHorizontalVelocity
	*/
	abstract boolean isValidMinHorizontalVelocity(double minHorizontalVelocity);
	
	/**
	 * Set the minHorizontalVelocity of this GameObject to the given minHorizontalVelocity.
	 * 
	 * @param  minHorizontalVelocity
	 *         The new minHorizontalVelocity for this GameObject.
	 * @post   If the given minHorizontalVelocity is a valid minHorizontalVelocity for any GameObject,
	 *         the minHorizontalVelocity of this new GameObject is equal to the given
	 *         minHorizontalVelocity. 
	 * 		 | new.getMinHorizontalVelocity() == minHorizontalVelocity1    
	 */
	@Raw
	public void setMinHorizontalVelocity(double minHorizontalVelocity) {
		if (isValidMinHorizontalVelocity(minHorizontalVelocity))
			this.minHorizontalVelocity = minHorizontalVelocity;	
	}
	
	/**
	 * Variable registering the minHorizontalVelocity of this GameObject.
	 */
	private double minHorizontalVelocity;
	
	/**
	 * Return the maxHorizontalVelocity of this GameObject.
	 */
	@Basic @Raw
	public double getMaxHorizontalVelocity() {
		return this.maxHorizontalVelocity;
	}
	
	/**
	 * Check whether the given maxHorizontalVelocity is a valid maxHorizontalVelocity for
	 * any GameObject.
	 *  
	 * @param  maxHorizontalVelocity
	 *         The maxHorizontalVelocity to check.
	 * @return True if and only if the given maximum horizontal velocity is not below the minimum
	 * 		   horizontal velocity.
	 *       | result == Math.abs(maxHorizontalVelocity) >= getMinHorizontalVelocity()
	*/
	public boolean isValidMaxHorizontalVelocity(double maxHorizontalVelocity) {
		return Math.abs(maxHorizontalVelocity) >= this.getMinHorizontalVelocity();
	}
	
	/**
	 * Set the maxHorizontalVelocity of this GameObject to the given maxHorizontalVelocity.
	 * 
	 * @param  maxHorizontalVelocity
	 *         The new maxHorizontalVelocity for this GameObject.
	 * @post   If the given maxHorizontalVelocity is a valid maxHorizontalVelocity for any GameObject,
	 *         the maxHorizontalVelocity of this new GameObject is equal to the given
	 *         maxHorizontalVelocity.
	 *       | new.getMaxHorizontalVelocity() == minHorizontalVelocity
	 * @post   if the given maxHorizontalVelocity of this new GameObject is not valid,
	 *         the maxHorizonalVelocity is set to the minHorizontalVelocity.
	 *       | new.getMaxHorizontalVelocity() == this.getMinHorizontalVelocity()    
	 */
	@Raw
	public void setMaxHorizontalVelocity(double maxHorizontalVelocity) {
		if (isValidMaxHorizontalVelocity(maxHorizontalVelocity))
			this.maxHorizontalVelocity = maxHorizontalVelocity;
		else 
			this.maxHorizontalVelocity = this.getMinHorizontalVelocity();
	}

	/**
	 * Variable registering the maxHorizontalVelocity of this GameObject.
	 */
	private double maxHorizontalVelocity;
	
	/**
	 * Return the verticalVelocity of this GameObject.
	 */
	@Basic @Raw
	public double getVerticalVelocity() {
		return this.verticalVelocity;
	}
	
	/**
	 * Check whether the given verticalVelocity is a valid verticalVelocity for
	 * any GameObject.
	 *  
	 * @param  verticalVelocity
	 *         The verticalVelocity to check.
	*/
	public abstract boolean isValidVerticalVelocity(double verticalVelocity);
	
	/**
	 * Set the verticalVelocity of this GameObject to the given verticalVelocity.
	 * 
	 * @param verticalVelocity
	 *        The new verticalVelocity for this GameObject.
	 * @post If the given verticalVelocity is a valid verticalVelocity for any GameObject,
	 *       the verticalVelocity of this new GameObject is equal to the given
	 *       verticalVelocity.
	 *     | new.getVerticalVelocity() == verticalVelocity
	 * @post If the given verticalVelocity is not valid, the verticalVelocity is 
	 * 		 set to 0.
	 *     | new.getVerticalVelocity() == 0
	 */
	@Raw
	public void setVerticalVelocity(double verticalVelocity) {
		if (isValidVerticalVelocity(verticalVelocity))
			this.verticalVelocity = verticalVelocity;
		else
			this.verticalVelocity = 0;
	}
	
	/**
	 * Variable registering the verticalVelocity of this GameObject.
	 */
	protected double verticalVelocity;
	
	/**
	 * Variable registering the maximal vertical velocity that applies to all GameObjects.		
	 */
	protected static final double MAX_VERTICAL_VELOCITY = 8;
	
	/**
	 * Return the horizontalAcceleration of this GameObject.
	 */
	@Basic @Raw
	public double getHorizontalAcceleration() {
		return this.horizontalAcceleration;
	}
	
	/**
	 * Check whether the given horizontalAcceleration is a valid horizontalAcceleration for
	 * any GameObject.
	 *  
	 * @param  horizontalAcceleration
	 *         The horizontalAcceleration to check.
	*/
	abstract boolean isValidHorizontalAcceleration(double horizontalAcceleration);
	
	/**
	 * Set the horizontalAcceleration of this GameObject to the given horizontalAcceleration.
	 * 
	 * @param  horizontalAcceleration
	 *         The new horizontalAcceleration for this GameObject.
	 * @pre    The given horizontalAcceleration must be a valid horizontalAcceleration for any
	 *         GameObject.
	 *       | isValidHorizontalAcceleration(horizontalAcceleration)
	 * @post   The horizontalAcceleration of this GameObject is equal to the given
	 *         horizontalAcceleration.
	 *       | new.getHorizontalAcceleration() == horizontalAcceleration
	 */
	@Raw
	public void setHorizontalAcceleration(double horizontalAcceleration) {
		assert isValidHorizontalAcceleration(horizontalAcceleration);
		this.horizontalAcceleration = horizontalAcceleration;
	}
	
	/**
	 * Variable registering the horizontalAcceleration of this GameObject.
	 */
	protected double horizontalAcceleration;


	/**
	 * Return the verticalAcceleration of this GameObject.
	 */
	@Basic @Raw
	public double getVerticalAcceleration() {
		return this.verticalAcceleration;
	}
	
	/**
	 * Check whether the given verticalAcceleration is a valid verticalAcceleration for
	 * any GameObject.
	 *  
	 * @param  verticalAcceleration
	 *         The verticalAcceleration to check.
	 * @return True if and only if the given vertical acceleration is equal to either 0 or 
	 * 		   to the VERTICAL_FALL_ACCELERATION	
	 *       | result == this.getVerticalAcceleration() == 0 | 
	 *       |  this.getVerticalAcceleration() == Constants.VERTICAL_FALL_ACCELERATION
	*/
	public boolean isValidVerticalAcceleration(double verticalAcceleration) {
		return (this.getVerticalAcceleration() == 0 | 
		       this.getVerticalAcceleration() == Constants.VERTICAL_FALL_ACCELERATION);
	}
	
	/**
	 * Set the verticalAcceleration of this GameObject to the given verticalAcceleration.
	 * 
	 * @param  verticalAcceleration
	 *         The new verticalAcceleration for this GameObject.
	 * @post   The verticalAcceleration of this new GameObject is equal to
	 *         the given verticalAcceleration.
	 *       | new.getVerticalAcceleration() == verticalAcceleration
	 * @throws RuntimeException
	 *         The given verticalAcceleration is not a valid verticalAcceleration for any
	 *         GameObject.
	 *       | ! isValidVerticalAcceleration(getVerticalAcceleration())
	 */
	@Raw
	public void setVerticalAcceleration(double verticalAcceleration) 
			throws RuntimeException {
		if (! isValidVerticalAcceleration(verticalAcceleration))
			throw new RuntimeException();
		this.verticalAcceleration = verticalAcceleration;
	}
	
	/**
	 * Variable registering the verticalAcceleration of this GameObject.
	 */		
	protected double verticalAcceleration;
		
	/**
	 * Return the sprites of this GameObject.
	 */
	@Basic @Raw
	public Sprite[] getSprites() {
		Sprite[] clonedSprites = Arrays.copyOf(this.sprites, sprites.length);
		return clonedSprites;
	}
	
	/**
	 * Checks whether the given sprites are valid sprites for any GameObject.
	 * @param sprites
	 * 		  The sprites that have to be checked.
	 */
	abstract boolean isValidSprite(Sprite... sprites);
	
	/**
	 * Set the sprites of this GameObject to the given sprites.
	 * 
	 * @param  sprites
	 *         The new sprites for this GameObject.
	 * @post   The sprites of this new GameObject is equal to
	 *         the given sprites.
	 *       | new.getSprite() == sprites
	 * @throws RuntimeException
	 *         The given sprites is not a valid sprites for any
	 *         GameObject.
	 *       | ! isValidSprite(getSprite())
	 */
	@Raw
	public void setSprites(Sprite... sprites) 
			throws RuntimeException {
		if (! isValidSprite(sprites))
			throw new RuntimeException();
		this.sprites = sprites;
	}

	/**
	 * Variable registering the sprites of this GameObject.
	 */
	protected Sprite[] sprites;

	/**
	 * Return the currentSprite of this GameObject.
	 */
	@Basic @Raw
	public Sprite getCurrentSprite() {
		return this.currentSprite;
	}
	
	/**
	 * Check whether the given currentSprite is a valid currentSprite for
	 * any GameObject.
	 *  
	 * @param  currentSprite
	 *         The currentSprite to check.
	 * @return True if and only if the current Sprite is given in the array with all possible sprites
	 *       | result == for (Sprite sprite : sprites)
	 *		 |				if (sprite == currentSprite)
	 *		 |					return true;
	 *		 |			return false;
	 */
	public boolean isValidCurrentSprite(Sprite currentSprite) {
		for (Sprite sprite : sprites) {
			if (sprite == currentSprite)
				return true;
			}
		return false;
	}
	
	/**
	 * Set the currentSprite of this GameObject to the given currentSprite.
	 * 
	 * @param  currentSprite
	 *         The new currentSprite for this GameObject.
	 * @post   The currentSprite of this new GameObject is equal to
	 *         the given currentSprite.
	 *       | new.getCurrentSprite() == currentSprite
	 * @throws RuntimeException
	 *         The given currentSprite is not a valid currentSprite for any
	 *         GameObject.
	 *       | ! isValidCurrentSprite(getCurrentSprite())
	 */
	@Raw
	public void setCurrentSprite(Sprite currentSprite) 
			throws RuntimeException {
		if (! isValidCurrentSprite(currentSprite))
			throw new RuntimeException();
		this.currentSprite = currentSprite;
	}
	
	/**
	 * Variable registering the currentSprite of this GameObject.
	 */
	private Sprite currentSprite;
	
	/**
	 * Set the world of the GameObject to the given world.
	 * @post The new world of this GameObject is equal to the given world. 
	 * 		| new.getWorld() = world
	 */
	public void setWorld(World world) {
		this.world = world;
	}
	
	/**
	 * return the world of the GameObject.
	 */
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Variable registering the world of the GameObject.
	 */
	protected World world;
	
	/**
	 * Terminate the GameObject.
	 * @effect the association with the world is broken if there was one. The variable isTerminated
	 * is set to true.
	 * 		   | this.isTerminated = true;
	 * 		   | if (this.getWorld() != null) 
	 * 		   |   this.getWorld().removeGameObject(this);
	 * 		   |	   this.setWorld(null);
	 */
	public void terminate() {
		this.isTerminated = true;
		if (this.getWorld() != null) {
			this.getWorld().removeGameObject(this);
			this.setWorld(null);
		}
	}
	
	/**
	 * Boolean checking whether the GameObject is terminated. 
	 * @return true if and only if the GameObject is terminated
	 * 		| result == this.isTerminated
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * Boolean registering whether the GameObject is terminated.
	 */
	private boolean isTerminated = false;
	
	/**
	 * kills a GameObject that is not dead yet
	 * @effect the horizontal velocity is set to zero
	 * 	      |this.setHorizontalVelocity(0)
	 * @effect the horizontal acceleration is set to zero
	 * 		  |this.setHorizontalAcceleration(0)
	 * @effect the vertical acceleration is set to zero
	 * 		  |this.setVerticalAcceleration(0)
	 * @effect the vertical acceleration is set to zero
	 * 		  |this.setVerticalAcceleration(0)
	 * @effect the delay is set to zero
	 * 		  |this.setDelay(0)
	 */
	public void kill() {
		if (! this.isDead) {
			this.isDead = true;
			this.setHorizontalVelocity(0);
			this.setHorizontalAcceleration(0);
			this.setVerticalVelocity(0);
			this.setVerticalAcceleration(0);
			this.setDelay(0);
			this.setHitPoints(0);
		}		
	}

	/**
	 * Set the delay of the GameObject to the given delay.
	 * @param delay
	 * 		The updated delay of the GameObject.
	 * @post The delay of this GameObject is set to the given delay.
	 * 		| new.delay = delay
	 */
	public void setDelay(double delay) {
		this.delay = delay;
		
	}
	
	/**
	 * Return the delay of the GameObject.
	 */
	public double getDelay() {
		return this.delay;
	}
	
	/**
	 * Variable registering the delay of the dead GameObject. This is the time to wait to 
	 * terminate the GameObject.
	 */
	private double delay=0;

	/**
	 * Boolean checking whether the GameObject is dead.
	 */
	public boolean isDead() {
		return this.isDead;
	}
	
	/**
	 * Boolean registering whether the GameObject is dead.
	 */
	private boolean isDead = false;
	
	/**
	 * Return the hitPoints of this GameObject.
	 */
	@Basic @Raw
	public int getHitPoints() {
		return this.hitPoints;
	}
	
	/**
	 * Check whether the given hitPoints is a valid hitPoints for
	 * any GameObject.
	 *  
	 * @param  hitPoints
	 *         The hitPoints to check.
	 * @return true if and only if the given number of hitPoints is in between 0 and 500 
	 *       | result == ((hitPoints >=0) && (hitPoints <=500))	
	*/
	public static boolean isValidHitPoints(int hitPoints) {
		return ((hitPoints >=0) && (hitPoints <=500));
	}
	
	/**
	 * Set the hitPoints of this GameObject to the given hitPoints.
	 * 
	 * @param  hitPoints
	 *         The new hitPoints for this GameObject.
	 * @post   If the given hitPoints is a valid hitPoints for any GameObject,
	 *         the hitPoints of this GameObject is equal to the given
	 *         hitPoints.
	 *       | if (isValidHitPoints(hitPoints))
	 *       |   then new.getHitPoints() == hitPoints
	 * @post   If the given number of hitPoints is less than zero, the hitPoints 
	 * 		   of this GameObject is set to 0 and is killed.
	 * 		 | if (hitPoints <= 0)
	 *		 | 	this.hitPoints = 0
	 *		 | 	this.kill()
	 * @post   If the given number of hitPoints is bigger than 500, the hitPoints 
	 * 		   of this GameObject is set to 500.
	 *       | if (hitPoints >500)
	 *		 | 	this.hitPoints = 500
	 */
	@Raw
	public void setHitPoints(int hitPoints) {
		if (isValidHitPoints(hitPoints))
			this.hitPoints = hitPoints;
		if (hitPoints >500)
			this.hitPoints = 500;
		if (hitPoints <=0) {
			this.hitPoints = 0;
			this.kill();
		}
	}
	
	/**
	 * Variable registering the hitPoints of this GameObject.
	 */
	private int hitPoints;
	
	/**
	 * Advance the time of this GameObject.
	 * @param deltaT
	 * 		  The time period after which the positions and velocities are updated.
	 */
	public abstract void advanceTime(double deltaT);
	
	/**
	 * A method that calculates dt that divides a bigger time period delta T in smaller time.
	 * periods
	 * @param deltaT
	 * 		  The bigger time interval 
	 * @effect dt is calculated 
	 * 		 | dt = 0.01/(sqrt(this.getHorizontalVelocity()*this.getHorizontalVelocity()
	 *       | + this.getVerticalVelocity()*this.getVerticalVelocity())+sqrt(this.getHorizontalAcceleration()*this.getHorizontalAcceleration()
	 *       | + this.getVerticalAcceleration()*this.getVerticalAcceleration())*deltaT)
	 * @effect If dt is infinity, delta T is returned
	 *		 | dt = deltaT 
	 */
	public double calculateDT(double deltaT) {
		double vX = this.getHorizontalVelocity();
		double vY = this.getVerticalVelocity();
		double aX = this.getHorizontalAcceleration();
		double aY = this.getVerticalAcceleration();

		double firstTermDenominator = Math.sqrt(vX*vX+vY*vY);
		double secondTermDenominator = Math.sqrt(aX*aX+aY*aY);
		double denominator = firstTermDenominator + (secondTermDenominator*deltaT);
		if ((vX== 0) & (vY == 0) & (aX == 0) & (aY == 0)) {
			return deltaT;
			}
		return (0.01/denominator);
		}
	
	/**
	 * Determines whether the given GameObject collides with a GameObject with
	 * given position and measurements.
	 * @param xOther
	 * 		  pixelX of the other GameObject
	 * @param yOther
	 * 		  pixelY of the other GameObject
	 * @param xPOther
	 * 		  the width of the other GameObject
	 * @param yPOther
	 * 		  the height of the other GameObject
	 * @return True if there is an overlap between the pixels of this GameObject and the other one.
	 * 		  | if ((this.getPixelX() +(this.getCurrentSprite().getWidth() -1) < xOther) || (xOther +(xPOther - 1) < this.getPixelX()) || ( this.getPixelY() +(this.getCurrentSprite().getHeight()-1) < yOther) 
	 *        |  || (yOther +(yPOther - 1) < this.getPixelY()))
	 *		  |		return false
	 *	      |	else
	 *		  |		return true
	 */
	public boolean collidesWith(int xOther,int yOther,int xPOther, int yPOther) {
		int x = this.getPixelX();
		int y = this.getPixelY();
		int xP = this.getCurrentSprite().getWidth();
		int yP = this.getCurrentSprite().getHeight();
		if ((x +(xP -1) < xOther) || (xOther +(xPOther - 1) < x) || (y +(yP -1) < yOther) 
			|| (yOther +(yPOther - 1) < y))
			return false;
		else
			return true;
	}
	
	/**
	 * A method that checks whether the given GameObject collides with an other given GameObject.
	 * @param posX
	 * 		  the pixelX of the GameObject
	 * @param posY
	 * 		  the pixelY of the GameObject
	 * @param other
	 * 		  the other GameObject
	 * @return True if there is an overlap between the pixels of this GameObject and the other one.
	 * 		  | if ((posX +(this.getCurrentSprite().getWidth() -1) < other.getPixelX()) || (other.getPixelX() +(other.getCurrentSprite().getWidth() - 1) < posX) 
	 * 		  |	|| (posY +(this.getCurrentSprite().getHeight() -1) < other.getPixelY()) || (other.getPixelY() +(other.getCurrentSprite().getHeight() - 1) < posY))
	 *		  |		return false;
	 *		  | else
	 *		  | 	return true;
	 */
	public boolean collidesWithOtherGameObect(int posX,int posY,GameObject other) {
		int x = posX;
		int y = posY;
		int xP = this.getCurrentSprite().getWidth();
		int yP = this.getCurrentSprite().getHeight();
		int xOther = other.getPixelX();
		int yOther = other.getPixelY();
		int xPOther = other.getCurrentSprite().getWidth();
		int yPOther = other.getCurrentSprite().getHeight();
		if ((x +(xP -1) < xOther) || (xOther +(xPOther - 1) < x) || (y +(yP -1) < yOther) 
				|| (yOther +(yPOther - 1) < y))
				return false;
			else
				return true;
	}
	
	/**
	 * Determines whether the given GameObject collides with a tile that consists
	 * of the given geological when he is moved to the given position.
	 * @param x
	 * 		  pixelX of the GameObject
	 * @param y
	 * 		  pixelY of the GameObject
	 * @param geologicals
	 * 		  the set of geological that is checked for overlap with the GameObject
	 * @return true if the GameObject does not overlap with all the tiles that contain
	 * 		   the given geological
	 * 		  | for (int index=0; index < this.getWorld().getGeologicalFeatures().length; index++) 
	 *   	  |		if (this.getWorld().getGeologicalFeatures()[index] == geological)
	 *   	  |			nbGeologicals +=1
	 *   	  |			if ((x +(this.getCurrentSprite().getWidth() -1) < this.getWorld().getPositionGeologicalFeature(index)[0]) || 
	 *   	  |   		(this.getWorld().getPositionGeologicalFeature(index)[0] +(this.getWorld().getTileLength() - 1) < x) ||
	 *   	  |			(y +(this.getCurrentSprite().getHeight() -1) < this.getWorld().getPositionGeologicalFeature(index)[1]) || 
	 *   	  |			(this.getWorld().getPositionGeologicalFeature(index)[1] +(this.getWorld().getTileLength() - 1) < y)) 
	 *   	  |				nbNotCollisions +=1
	 *   	  |	if (nbGeologicals == nbNotCollisions) 
	 *   	  |		return false
	 *   	  |	else
	 *        |		return true
	 */
	public boolean collidesWithGeological(int x,int y,Geological geological) {
		int nbGeologicals=0;
		int nbNotCollisions = 0;
		int xP = this.getCurrentSprite().getWidth();
		int yP = this.getCurrentSprite().getHeight();
		for (int index=0; index < this.getWorld().getGeologicalFeatures().length; index++) {
			if (this.getWorld().getGeologicalFeatures()[index] == geological) {
				nbGeologicals +=1;
				int xOther = this.getWorld().getPositionGeologicalFeature(index)[0];
				int yOther = this.getWorld().getPositionGeologicalFeature(index)[1];
				int xPOther = this.getWorld().getTileLength();
				int yPOther = this.getWorld().getTileLength();
				if ((x +(xP -1) < xOther) || (xOther +(xPOther - 1) < x) ||
						(y +(yP -1) < yOther) || (yOther +(yPOther - 1) < y)) {
					nbNotCollisions +=1;
				}
			}
			}
		if (nbGeologicals == nbNotCollisions) 
			return false;
		else
			return true;
		
		}
	
	/**
	 * Determines whether the given GameObject collides with a tile that consists
	 * of the given geologicals when he is moved to the given position.
	 * @param x
	 * 		  pixelX of the GameObject
	 * @param y
	 * 		  pixelY of the GameObject
	 * @param geologicals
	 * 		  the set of geologicals that is checked for overlap with the GameObject
	 * @return true if the GameObject does not overlap with all the tiles that contain
	 * 		   the given geologicals
	 * 		  | for (int index=0; index < this.getWorld().getGeologicalFeatures().length; index++) 
	 *   	  |		if (geologicals.contains(this.getWorld().getGeologicalFeatures()[index]))
	 *   	  |			nbGeologicals +=1
	 *   	  |			if ((x +(this.getCurrentSprite().getWidth() -1) < this.getWorld().getPositionGeologicalFeature(index)[0]) || 
	 *   	  |   		(this.getWorld().getPositionGeologicalFeature(index)[0] +(this.getWorld().getTileLength() - 1) < x) ||
	 *   	  |			(y +(this.getCurrentSprite().getHeight() -1) < this.getWorld().getPositionGeologicalFeature(index)[1]) || 
	 *   	  |			(this.getWorld().getPositionGeologicalFeature(index)[1] +(this.getWorld().getTileLength() - 1) < y)) 
	 *   	  |				nbNotCollisions +=1
	 *   	  |	if (nbGeologicals == nbNotCollisions) 
	 *   	  |		return false
	 *   	  |	else
	 *        |		return true
	 */
	public boolean collidesWithGeologicals(int x,int y,HashSet<Geological> geologicals) {
		int nbGeologicals=0;
		int nbNotCollisions = 0;
		int xP = this.getCurrentSprite().getWidth();
		int yP = this.getCurrentSprite().getHeight();
		for (int index=0; index < this.getWorld().getGeologicalFeatures().length; index++) {
			if (geologicals.contains(this.getWorld().getGeologicalFeatures()[index])) {
				nbGeologicals +=1;
				int xOther = this.getWorld().getPositionGeologicalFeature(index)[0];
				int yOther = this.getWorld().getPositionGeologicalFeature(index)[1];
				int xPOther = this.getWorld().getTileLength();
				int yPOther = this.getWorld().getTileLength();
				if ((x +(xP -1) < xOther) || (xOther +(xPOther - 1) < x) ||
						(y +(yP -1) < yOther) || (yOther +(yPOther - 1) < y)) {
					nbNotCollisions +=1;
				}
			}
			}
		
		if (nbGeologicals == nbNotCollisions) 
			return false;
		
		else
			return true;
		
		}
	
	/**
	 * Set the timeInWater of this GameObject to the given timeInWater.
	 * 
	 * @param  timeInWater
	 *         The timeInWater for all GameObjects.
	 * @post   The timeInWater is equal to the given
	 *         timeInWater.
	 *       | new.timeInWater = timeInWater
	 */
	public void setTimeInWater(double timeInWater) {
		this.timeInWater = timeInWater;
	}
	
	/**
	 * Return the timeInWater of this GameObject.
	 */
	public double getTimeInWater() {
		return this.timeInWater;
	}
	
	/**
	 * A variable registering the time this GameObject has spend in water.
	 */
	private double timeInWater=0;
	
	/**
	 * Return the timeInMagma of this GameObject.
	 */
	public double getTimeInMagma() {
		return this.timeInMagma;
	}

	/**
	 * Set the timeInMagma of this GameObject to the given timeInMagma.
	 * @param  timeInMagma
	 *         The timeInMagma for all GameObjects.
	 * @post   The timeInMagma is equal to the given
	 *         timeInMagma.
	 *       | new.timeInMagma = timeInMagma
	 */
	public void setTimeInMagma(double timeInMagma) {
		this.timeInMagma = timeInMagma;
	}
	
	/**
	 * A variable registering the time the GameObject has spend in the water.
	 */
	private double timeInMagma=0;
	
	/**
	 * Set the timeInWater that applies to all GameObjects to the given timeInGas.
	 * 
	 * @param  timeInGas
	 *         The timeInGas for all GameObjects.
	 * @post   The timeInGas is equal to the given
	 *         timeInGas.
	 *       | new.timeInGas = timeInGas
	 */
	public void setTimeInGas(double timeInGas) {
		this.timeInGas = timeInGas;
	}
	
	/**
	 * Return the timeInGas of this GameObject.
	 */
	public double getTimeInGas() {
		return this.timeInGas;
	}
	
	/**
	 * A variable registering the time the GameObject has spend in gas.
	 */
	private double timeInGas=0;
	
	/**
	 * A boolean checking whether the GameObject is standing on impassable terrain.
	 * @param posX
	 * 		  The pixelX of the GameObject.
	 * @param posY
	 * 		  the pixelY of the GameObject.
	 * @return True if and only if GameObject's bottom row of pixels consist of a pixel that
	 * 		   has an impassable terrain as its geological feature or if the row beneath the bottom row
	 * 		   consist of a pixel that has an impassable terrain as its geological feature
	 * 	     | for (index=0;index <=this.getCurrentSprite().getWidth();index ++) 
	 *		 | if (((! Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY))) &&
	 *		 |	(Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY-1)))) ||
	 *		 |		((Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY))) &&
	 *		 |				(!Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY+1)))))
	 *		 |			return true
	 */
	public boolean objectOnImpassableTerrain(int posX,int posY) {
		int index;
		for (index=0;index <this.getCurrentSprite().getWidth();
				index ++) {
			if (((! Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY))) &&
					(Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY-1)))) ||
					((Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY))) &&
						(!Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY+1)))))
				return true;
		}
			return false;
		
	}
	
	/**
	 * A method that updates the position of the GameObject after a given time period.
	 * @param deltaT
	 * 		  The given time period
	 * @effect the actualX is updated to his current value
	 *       | this.setActualX(this.calculatePositionX(deltaT))
	 * @effect the actualY is updated to his current value
	 *       | this.setActualY(this.calculatePositionY(deltaT))
	 * @effect the pixelX is updated to his current value
	 *       | this.setPixelX((int) (this.calculatePositionX(deltaT)*100))
	 * @effect the pixelY is updated to his current value
	 *       | this.setPixelY((int) (this.calculatePositionY(deltaT)*100))
	 */
	public void updatePosition(double deltaTX,double deltaTY) {
		double posX= this.calculatePositionX(deltaTX);
		double posY = this.calculatePositionY(deltaTY);
		this.setActualX(posX);
		this.setActualY(posY);
		this.setPixelX((int) (100*posX));
		this.setPixelY((int) (100*posY));
		
	}
	
	/**
	 * Returns the updated actualX after a given time interval.
	 * @param deltaT
	 * 		  The given time interval after which you have to update the actualX
	 * @return The calculated position of actualX starting from the current actualX, the current
	 * 	       horizontal velocity, the current horizontal acceleration and a given deltaT.
	 * 		 | newActualX = actualX +  (horizontalVelocity*deltaT) +
	 *		 |	 (0.5*horizontalAcceleration*deltaT*deltaT)
	 */
	public double calculatePositionX(double deltaT) {
		double actualX = this.getActualX();
		double horizontalVelocity = this.getHorizontalVelocity();
		double horizontalAcceleration = this.getHorizontalAcceleration();
		double newActualX = actualX +  (horizontalVelocity*deltaT) +
				 (0.5*horizontalAcceleration*deltaT*deltaT);
		
		return newActualX;
	}
	
	/**
	 * Calculate the updated actualY after a given time interval.
	 * @param deltaT
	 * 		  The given time interval after which you have to update the actualY.
	 * @return The calculated position of actualY starting from the current actualY, the current
	 * 	       horizontal velocity, the current horizontal acceleration and a given deltaT.
	 * 		 | newActualY = actualY +  (verticalVelocity*deltaT) +
	 *		 |	 (0.5*verticalAcceleration*deltaT*deltaT)
	 */
	public double calculatePositionY(double deltaT) {
		double actualY = this.getActualY();
		double verticalVelocity = this.getVerticalVelocity();
		double verticalAcceleration = this.getVerticalAcceleration();
		double newActualY = actualY + (verticalVelocity*deltaT) + 
				(0.5*(verticalAcceleration)*deltaT*deltaT);
		return newActualY;
	}

	/**
	 * Calculate the updated horizontal velocity after a given time interval.
	 * @param deltaT
	 * 		  The given time interval after which you have to update the horizontal velocity
	 * @return The calculated horizontal velocity starting from the current horizontal velocity
	 *         and the current horizontal acceleration and a given deltaT.
	 *       | newHorizontalVelocity = horizontalVelocity +  (horizontalAcceleration*deltaT)
	 */
	public double calculateHorizontalVelocity(double deltaT) {
		double horizontalVelocity = this.getHorizontalVelocity();
		double horizontalAcceleration = this.getHorizontalAcceleration();
		double newHorizontalVelocity = horizontalVelocity +  (horizontalAcceleration*deltaT);
		return newHorizontalVelocity;
			
	}
	
	/**
	 * Calculate the updated vertical velocity after a given time interval.
	 * @param deltaT
	 * 		The given time interval after which you have to update the vertical velocity
	 * @return The calculated vertical velocity starting from the current vertical velocity
	 *         and the current vertical acceleration and a given deltaT.
	 *       | newVerticalVelocity = (verticalVelocity +  (verticalAcceleration*deltaT))
	 * 
	 */
	public double calculateVerticalVelocity(double deltaT) {
		double verticalVelocity = this.getVerticalVelocity();
		double verticalAcceleration = this.getVerticalAcceleration();
		double newVerticalVelocity = (verticalVelocity +  (verticalAcceleration*deltaT));
		return newVerticalVelocity;
				
	}
	
	/**
	 * A boolean checking whether the GameObject is bumping with his upside against impassable terrain.
	 * @param posX
	 * 		  The pixelX of the GameObject.
	 * @param posY
	 * 		  the pixelY of the GameObject.
	 * @return True if and only if GameObject's top row of pixels consist of a pixel that
	 * 		   has impassable terrain as its geological feature.
	 * 	     | for (index=0;index <=this.getCurrentSprite().getWidth();index ++) 
	 *		 | 		if ((Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY))))
	 *		 |			return true
	 */
	public boolean objectBumpsImpassableTerrainAbove(int posX, int posY) {
		posY += (this.getCurrentSprite().getHeight());
		int index;
		for (index=1;index <this.getCurrentSprite().getWidth();
				index ++) {
			if ((Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY)))){
				return true;
			}
			}
			return false;
	}
	
	/**
	 * A boolean checking whether the GameObject is bumping to impassable terrain at his left side.
	 * @param posX
	 * 		  The pixelX of the GameObject.
	 * @param posY
	 * 		  the pixelY of the GameObject.
	 * @return True if and only if GameObject's leftmost column of pixels consist of a pixel that
	 * 		   has solid ground as its geological feature.
	 * 	     | for (int index=1;index <=this.getCurrentSprite().getHeight();index ++) do
	 *		 |	if (Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX-1, posY + index))) 
	 *		 |			return true
	 */
	public boolean objectBumpsImpassableTerrainLeft(int posX,int posY) {
		for (int index=1;index < this.getCurrentSprite().getHeight();
				index ++) {
			if (Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX-1, posY + index))) 
				return true;
			
		}
		return false;	
	}
	
	/**
	 * A boolean checking whether the GameObject is bumping on impassable terrain at his right side
	 * @param posX
	 * 		  The pixelX of the GameObject.
	 * @param posY
	 * 		  the pixelY of the GameObject.
	 * @return True if and only if GameObject rightmost column of pixels consist of a pixel that
	 * 		   has an impassable terrain as its geological feature.
	 * 	     | for (int index=1;index <=this.getCurrentSprite().getHeight();index ++) do
	 *		 |	 if (Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX, posY + index)))
	 *		 |			return true
	 */
	public boolean objectBumpsImpassableTerrainRight(int posX,int posY) {
		posX += this.getCurrentSprite().getWidth();
		for (int index=1;index <this.getCurrentSprite().getHeight();
				index ++) {
			if (Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX, posY + index)))
				return true;	
		}
		return false;
		}
	
	/**
	 * A method that checks whether the given GameObject bumps on an other GameObject.
	 * @param x
	 * 		  the pixelX of the GameObject.
	 * @param y
	 * 		  the pixelX of the GameObject.
	 * @return True if this GameObject collides with an other GameObject of this world.
	 * 		 | for (GameObject GameObjects: this.getWorld().getGameObjects()) do
	 * 		 | 		if ((!( GameObjects instanceof Plant)) && (GameObjects !=this))
	 *       |			if	(this.collidesWithOtherGameObect(x, y, GameObjects))
	 *       |				return true
	 */
	public boolean objectBumpsOnOtherObject(int x, int y) {
		for (GameObject GameObjects: this.getWorld().getGameObjects()) {
			if ((!( GameObjects instanceof Plant)) && (GameObjects !=this)) {
				if	(this.collidesWithOtherGameObect(x, y, GameObjects)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * A method that checks of the GameObject is standing on an other GameObject.
	 * @param x
	 * 		  the pixelX of the GameObject
	 * @param y
	 * 		  the pixelY of the GameObject
	 * @return True if the lowest row of the GameObject is located one row above
	 * 		   the highest row an other GameObject.
	 * 		  | for (GameObject newGameObject: this.getWorld().getGameObjects()) do
	 * 		  | 	for (int index=0;index < xP;index +=1) do
	 * 		  | 		if ((y == newGameObject.getPixelY() + newGameObject.getPixelHeight()&&(newGameObject.getPixelX() <= x+index) && (x+index < newGameObject.getPixelX() +newGameObject.getPixelWidth()))
	 * 		  |				return true 
	 */
	public boolean isStandingOnGameObject(int x, int y) {
		int xP = this.getPixelWidth();
		for (GameObject newGameObject: this.getWorld().getGameObjects()) {
			int xOther = newGameObject.getPixelX();
			int yOther = newGameObject.getPixelY();
			int xPOther = newGameObject.getPixelWidth();
			int yPOther = newGameObject.getPixelHeight();
				for (int index=0;index < xP;index +=1) {
					if ((y == yOther + yPOther)&&(xOther <= x+index) && 
							(x+index < xOther +xPOther)) {
						return true;
						}
					}
		}
		return false;
	}
	
	/**
	 * A method that checks whether the GameObject meets an obstacle in the horizontal
	 * direction.
	 * @param posX
	 * 		  the pixelX of the GameObject
	 * @param posY
	 * 		  the pixelY of the GameObject
	 * @return if the object is orientated left and bumps against impassable terrain on the left
	 * 		  | if((this.getOrientation()=="left")&& (this.objectBumpsImpassableTerrainLeft(posX,posY)))
	 * 		  |		return true
	 * @return if the object is orientated right and bumps against impassable terrain on the right
	 *		  | else if((this.getOrientation()=="right")&& (this.objectBumpsImpassableTerrainRight(posX,posY)))
	 * 		  |		return true
	 * 
	 */
	public boolean meetsHorizontalObstacle(int posX, int posY) {
		if((this.getOrientation()=="left")&& (this.objectBumpsImpassableTerrainLeft(posX,posY))){
			return true;
		}
		else if((this.getOrientation()=="right")&& (this.objectBumpsImpassableTerrainRight(posX,posY))){
			return true;
		}
		else
			return false;	
	}
	
	/**
	 * A method that checks whether the GameObject with given position is out of this world.
	 * @param posX
	 * 		  the pixelX of the GameObject
	 * @param posY
	 * 		  the pixelY of the GameObject
	 * @return true if the GameObject is outside the boundaries of the world.
	 * 		 | if ((posX < 0) || (100*posX >this.getWorld().getWorldWidth()))
	 * 		 | 		return true
	 * 		 | if ((posY < 0) || (100*posY >this.getWorld().getWorldHeight()))
	 * 		 | 		return true
	 * 		 | else
	 * 		 | 	   	return false
	 */
	public boolean isOutOfThisWorld(double posX, double posY) {
		if ((posX < 0) || (100*posX >
		this.getWorld().getWorldWidth())){
			return true;
		}
		if ((posY < 0) || (100*posY >
		this.getWorld().getWorldHeight())){
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Set if this GameObject is in water.
	 * @param inWater
	 * 		The boolean if this GameObject is in water or not.
	 * @post
	 * 		| new.inWater = inWater
	 */
	@Raw
	public void setInWater(boolean inWater) {
		this.inWater = inWater;
	}
	
	/**
	 * Return whether this GameObject is in water.
	 */
	public boolean isInWater() {
		return this.inWater;
	}
	
	/**
	 * Boolean registering whether this GameObject is in water.
	 */
	public boolean inWater = false;
	
	/**
	 * Set if this GameObject is in gas.
	 * @param inGas
	 * 		The boolean if this GameObject is in the gas or not.
	 * @post
	 * 		| new.inGas = inGas
	 */
	@Raw
	public void setInGas(boolean inGas) {
		this.inGas = inGas;
	}
	
	/**
	 * Return whether this GameObject is in gas.
	 */
	@Basic @Raw
	public boolean isInGas() {
		return this.inGas;
	}
	
	/**
	 * Boolean to check whether the GameObject is in the gas.
	 */
	public boolean inGas = false;
	
	/**
	 * Set if this GameObject is in magma.
	 * @param inWater
	 * 		The boolean if this GameObject is in magma or not.
	 * @post
	 * 		| new.inMagma = inMagma
	 */
	@Raw
	public boolean isInMagma() {
		return this.inMagma;
	}
	
	/**
	 * Return whether this GameObject is in magma.
	 */
	public void setInMagma(boolean inMagma) {
		this.inMagma = inMagma;
	}
	
	/**
	 * Boolean registering whether this GameObject is in magma.
	 */
	public boolean inMagma = false;

}
