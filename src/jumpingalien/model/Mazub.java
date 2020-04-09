package jumpingalien.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import java.lang.RuntimeException;
import jumpingalien.util.Sprite;




/**
 * A class of Mazubs extending the class GameObjects.
 * 
 * @invar  The pixelX of each Mazub must be a valid pixelX for any
 *         Mazub.
 *       | this.hasValidPixelX(getPixelX)
 * @invar  The pixelY of each Mazub must be a valid pixelY for any
 *         Mazub.
 *       | this.hasValidPixelY(getPixelY) 
 * @invar  The orientation of each Mazub must be a valid orientation for any
 *         Mazub.
 *       | isValidOrientation(getOrientation()) 
 * @invar  The horizontalVelocity of each Mazub must be a valid horizontalVelocity for any
 *         Mazub.
 *       | isValidHorizontalVelocity(getHorizontalVelocity())
 * @invar  The minHorizontalVelocity of each Mazub must be a valid minHorizontalVelocity for any
 *         Mazub.
 *       | isValidMinHorizontalVelocity(getMinHorizontalVelocity())
 * @invar  The maxHorizontalVelocity of each Mazub must be a valid maxHorizontalVelocity for any
 *         Mazub.
 *       | isValidMaxHorizontalVelocity(getMaxHorizontalVelocity()) 
 * @invar  The verticalVelocity of each Mazub must be a valid verticalVelocity for any
 *         Mazub.
 *       | isValidVerticalVelocity(getVerticalVelocity())   
 * @invar  The horizontalAcceleration of each Mazub must be a valid horizontalAcceleration for any
 * 	       Mazub.
 *       | isValidHorizontalAcceleration(getHorizontalAcceleration())
 * @invar  The verticalAcceleration of each Mazub must be a valid verticalAcceleration for any
 *         Mazub.
 *       | isValidVerticalAcceleration(getVerticalAcceleration())
 * @invar  The sprites of each Mazub must be valid sprites for any
 *         Mazub.
 * @invar  The runtime of each Mazub must be a valid runtime for any
 *         Mazub.
 *       | isValidRuntime(getRuntime())
 * @invar  The standingStillTime of each Mazub must be a valid standingStillTime for any
 *         Mazub.
 *       | isValidStandingStillTime(getStandingStillTime())
 *       | isValidSprite(getSprite())
 * @invar  The currentSprite of each Mazub must be a valid currentSprite for any
 *         Mazub.
 *       | isValidCurrentSprite(getCurrentSprite())
 * @invar  The hitPoints of each Mazub must be a valid hitPoints for any
 *         Mazub.
 *       | isValidHitPoints(getHitPoints())
 *
 * @version 1.0       
 *       
 * @author Timm Lamberechts
 * @author Maarten Volkaerts
 * 
 */

public class Mazub extends GameObject{
	
	/**
	 * Initialize this new Mazub with given actual position (x-coordinate and y-coordinate)
	 * and a given pixel position (pixelX and pixelY) .
	 *
	 * @param  pixelX
	 *         The pixelX in the gaming world  for this new Mazub.
	 * @param  pixelY
	 *         The pixelY in the gaming world  for this new Mazub. 
	 * @param  actualX
	 *         The x-coordinate in the actual world  for this new Mazub.
	 * @param  actualY
	 *         The y-coordinate in the actual world  for this new Mazub.
	 * @param  orientation
	 * 		   The orientation for this new Mazub.
	 * @param  horizontalVelocity
     *         The horizontalVelocity for this new Mazub.
     * @param  minHorizontalVelocity
     *         The minHorizontalVelocity for this new Mazub.
     * @param  maxHorizontalVelocity
     *         The maxHorizontalVelocity for this new Mazub.
     * @param  verticalVelocity
     *         The verticalVelocity for this new Mazub.
     * @param  horizontalAcceleration
     *         The horizontalAcceleration for this new Mazub.
     * @param  verticalAcceleration
     *         The verticalAcceleration for this new Mazub.
     * @param  isJumpingMazub
     *         The isJumpingMazub for this new Mazub.
     * @param  sprites
     *         The sprites for this new Mazub.
     * @param  currentSprite
     *         The currentSprite for this new Mazub.
     * @param  runtime
     *         The runtime for this new Mazub.
     * @param  standingStillTime
     *         The standingStillTime for this new Mazub.
	 * @pre	 The given orientation is a valid orientation for this new Mazub. 
	 * 		 | hasValidOrientation(orientation)
	 * @pre    The given horizontalAcceleration must be a valid horizontalAcceleration for any Mazub.
	 *       | isValidHorizontalAcceleration(horizontalAcceleration)
	 * @effect This Mazub is initialized as a GameObject.
	 * 		 | super(pixelX,pixelY,actualX,actualY,orientation,horizontalVelocity,minHorizontalVelocity,maxHorizontalVelocity,
	 * 		 | verticalVelocity,horizontalAcceleration,verticalAcceleration,100,currentSprite,sprites)
     * @post the isJumpingMazub is set to the given isJumpingMazub
     *       | this.setIsJumpingMazub(isJumpingMazub) 
     * @post The runtime of this new Mazub is set to
     *         the given runtime.
     *       | this.setRuntime(runtime)
     * @post The standingStillTime of this new Mazub is set to
     *         the given standingStillTime.
     *       | this.setStandingStillTime(standingStillTime)
     * @post  The m of this new Mazub is set to the difference between 
     * 			the length of the sprites and 8 divided by 2.
     *       | this.setM((sprites.length-8)/2)
	 */
	
	public Mazub(int pixelX,int pixelY,double actualX,double actualY, String orientation,
			double horizontalVelocity,double minHorizontalVelocity, double maxHorizontalVelocity,
			double verticalVelocity, double horizontalAcceleration, double verticalAcceleration, 
			boolean isJumpingMazub,double runtime, double standingStillTime, Sprite currentSprite, Sprite ... sprites) 
					throws RuntimeException {
	super(pixelX,pixelY,actualX,actualY,orientation,horizontalVelocity,minHorizontalVelocity,maxHorizontalVelocity,
			verticalVelocity,horizontalAcceleration,verticalAcceleration,100,currentSprite,sprites);
		this.setIsJumpingMazub(isJumpingMazub);
		this.setRuntime(runtime);
		this.setStandingStillTime(standingStillTime);
		this.setM((sprites.length-8)/2);
		

		}

	/**
	 * Initialize this new Mazub with given pixel position.
	 *
	 * @param  pixelX
	 *         The x-coordinate of the pixel in the gaming world  for the left bottom corner this new Mazub.
	 * @param  pixelY
	 *         The y-coordinate of the pixel in the gaming world  for the left bottom corner this new Mazub.
	 * @param  sprites
	 *         The sprites for this new Mazub.
	 * @effect This Mazub is initialized as a GameObject with given pixelX, pixelY and sprites.
	 *		   The other variables are initialised with their default values.
	 *		 | this(pixelX,pixelY,(double) pixelX/100,(double)pixelY/100,"front",0,1,3,0,0,0,false,0,0,
	 *		 |	sprites[0],sprites)
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
	 * @effect The sprites of this new Mazub is set to the given sprites.
	 *       | this.setSprites(sprites)
	 * @effect The current sprite is set to first sprite in the array of sprites.
	 *       | this.setCurrentSprite(sprites[0])
	 * @effect The runtime is set to 0. 
	 *       | this.setRuntime(0)
	 * @effect The state isJumpingGameObject is set to false.
	 *       | this.setIsJumpingGameObject(false)
	 */
	
	public Mazub(int pixelX,int pixelY,Sprite...sprites)
			throws RuntimeException {
		this(pixelX,pixelY,(double) pixelX/100,(double)pixelY/100,"front",0,1,3,0,0,0,false,0,0,
				sprites[0],sprites);
		}

	
	/**
	 * Start moving the Mazub to the left.
	 * @pre  The mazub is not moving already.
	 * 	   | ! this.isMoving
	 * @effect The orientation of the Mazub is set to left.
	 *       | this.setOrientation("left")
	 * @effect The horizontal velocity of the Mazub is set to -1 m/s 
	 * 	     | this.setHorizontalVelocity(-1.0)
	 * @effect if the Mazub is not ducking, the horizontal acceleration 
	 * 		   is set to - the moving acceleration. 
	 * 	     | if (! this.isDucking()) {
	 * 	     |	 this.setHorizontalAcceleration(- movingAcceleration)
	 * @effect the sprite is changed to the right sprite.
	 * 		 | this.setCurrentSprite(this.getSprites()[this.determineSpriteIndex()])
	 */
	 public void startMoveLeft() {
		assert ! this.isMoving();
		this.setOrientation("left");	
		assert (orientation == "left");
		this.setHorizontalVelocity(-1.0);	
		if (! this.isDucking()) 
			this.setHorizontalAcceleration(- movingAcceleration);
		this.setCurrentSprite(this.getSprites()[this.determineSpriteIndex()]);
	 }
	
	/**
	 * Start moving the Mazub to the right.
	 * @pre   The mazub is not moving already.
	 * 	    | ! this.isMoving
	 * @effect The orientation of the Mazub is set to right.
	 *       | this.setOrientation("right")
	 * @effect The horizontal velocity of the Mazub is set to 1 m/s 
	 * 	     | this.setHorizontalVelocity(1.0)
	 * @effect if the Mazub is not ducking, the horizontal acceleration 
	 * 		   is set to the moving acceleration. 
	 * 	     | if (! this.isDucking()) {
	 * 	     |	 this.setHorizontalAcceleration(movingAcceleration)
	 * @effect the sprite is changed to the right sprite.
	 * 		 | this.setCurrentSprite(this.getSprites()[this.determineSpriteIndex()])
	 * 
	 */
	public void startMoveRight() {			
		assert ! this.isMoving();
		this.setOrientation("right");
		assert (orientation == "right");
	   	this.setHorizontalVelocity(1.0);
	   	if (! this.isDucking()) {
	   		this.setHorizontalAcceleration(movingAcceleration);	
	   	}
	   	this.setCurrentSprite(this.getSprites()[this.determineSpriteIndex()]);	
	   	
			
	}
	/**
	 * Stop moving the Mazub
	 * @pre The given mazub is moving
	 * 	  | this.isMoving
	 * @effect The horizontal velocity is set to 0
	 * 	   	 | this.setHorizontalVelocity(0)
	 * @effect The horizontal acceleration is set to 0
	 * 	   	 | this.setHorizontalAcceleration(0)
	 * @effect the sprite is changed to the right sprite
	 * 		 | this.setCurrentSprite(this.getSprites()[this.determineSpriteIndex()]);
	 */
	public void endMove() {
		assert this.isMoving();
		Sprite oldSprite = this.getCurrentSprite();
		this.setStandingStillTime(0);
		this.setHorizontalVelocity(0);
		this.setHorizontalAcceleration(0);
		this.setCurrentSprite(this.getSprites()[this.determineSpriteIndex()]);
		if (this.objectBumpsImpassableTerrainAbove(this.getPixelX(), this.getPixelY())) {
			this.setCurrentSprite(oldSprite);
		}
	
	}
	/**
	 * Return whether the Mazub is moving. 
	 * @return True if and only if the horizontalVelocity is not equal to zero.
	 */
	public boolean isMoving() {
		return Math.abs(this.getHorizontalVelocity()) > 0;
	}
	/**
	 * Return the horizontalAcceleration of this Mazub.
	 */
	@Basic @Raw
	public double getHorizontalAcceleration() {
		return this.horizontalAcceleration;
	}
	
	/**
	 * Checks whether the Mazub is jumping

	 * @return True if and only if the variable isJumpingMazub has stored true as its boolean.
	 *       | result == this.getIsJumpingMazub()
	 *       
	*/
	
	
	public boolean isJumping() {
		if (this.getIsJumpingMazub())
			return true;
		else
			return false;	
	}
	/**
	 * Start jumping the Mazub.
	 * @throws RuntimeException
	 * 		   The Mazub is already jumping.
	 * 		 | (this.isJumping())
	 * @effect If the Mazub is ducking, the ducking will be ended. 
	 * 		 | 	if (this.isDucking())
	 * 		 | 		this.endDuck()
	 * @effect The vertical velocity is set to the INIT_JUMPING_VELOCITY.
	 * 		 | this.setVerticalVelocity(INIT_JUMPING_VELOCITY);
	 * @effect The vertical acceleration is set to the VERTICAL_FALL_ACCELERATION.
	 * 		 | this.setVerticalAcceleration(VERTICAL_FALL_ACCELERATION);
	 * @effect The sprite is changed to the right sprite.
	 * 		 | this.setCurrentSprite(this.getSprites()[this.determineSpriteIndex()]);
	 * @effect the boolean variable registering whether the Mazub is jumping is set to true.
	 * 		 | this.setIsJumpingMazub(true)
	 */	
	public void startJump() throws RuntimeException {
		Sprite oldSprite = this.getCurrentSprite();
		if (this.isDead())
			throw new RuntimeException();
		if (this.isDucking())	
			this.endDuck();
		if (this.isJumping())
			throw new RuntimeException();
		if (! this.isJumping())
			this.setVerticalVelocity(Constants.INIT_JUMPING_VELOCITY);
			this.setVerticalAcceleration(Constants.VERTICAL_FALL_ACCELERATION);
		this.setIsJumpingMazub(true);
		this.setCurrentSprite(this.getSprites()[this.determineSpriteIndex()]);
		 
		
		
	}
	/**
	 * Stop jumping The Mazub.
	 * @throws RuntimeException
	 * 		   The Mazub is not jumping.
	 * 		 | (! this.isJumping())
	 * @effect If the Mazub has zero as pixelY, the verticalVelocity is set to zero.
	 * 		 | if (this.getPixelY() <= 0)
	 * 	     | 		this.setVerticalVelocity(0)
	 * @effect If the Mazub has zero as pixelY, the verticalAcceleration is set to zero..
	 * 		 | if (this.getPixelY() <= 0)		
	 * 		 | 		this.setVerticalAcceleration(0)
	 * @effect If the Mazub has a positive verticalVelocity, the verticalVelocity is set to zero.
	 * 		 | if (this.getVerticalVelocity() > 0)
	 * 	     | 		this.setVerticalVelocity(0)
	 * @effect the boolean variable registering whether the Mazub is jumping is set to false.
	 * 		 | this.setIsJumpingMazub(false)
	 */
	public void endJump() throws RuntimeException {
		if (! this.isJumping())
			throw new RuntimeException();
		this.setIsJumpingMazub(false);
		if (this.getWorld()== null) {
			if (this.getPixelY() <= 0) {
				this.setVerticalAcceleration(0);
				this.setVerticalVelocity(0);
			}
			else if (this.getVerticalVelocity() > 0)
				this.setVerticalVelocity(0);
		}
		if (this.getWorld() != null) {
			if ((this.objectOnImpassableTerrain(this.getPixelX(),this.getPixelY()))&&
					(this.isStandingOnGameObject(this.getPixelX(),this.getPixelY()))) {
				this.setVerticalAcceleration(0);
				this.setVerticalVelocity(0);
			}
			else if (this.getVerticalVelocity() > 0)
				this.setVerticalVelocity(0);
		}		
	}
//	private static final double INIT_JUMPING_VELOCITY = 8;
//
//	private static final double VERTICAL_FALL_ACCELERATION = -10;
//		
	/**
	 * Return the isJumpingMazub of this Mazub.
	 */
	@Basic @Raw
	public boolean getIsJumpingMazub() {
		return this.isJumpingMazub;
	}
	
	/**
	 * Check whether the given isJumpingMazub is a valid isJumpingMazub for
	 * any Mazub.
	 *  
	 * @param  isJumpingMazub
	 *         The isJumpingMazub to check.
	 * @return True if and only if the isJumpingMazub is true or false.
	 *       | result == true | false
	*/
	public static boolean isValidIsJumpingMazub(boolean isJumpingMazub) {
		return (true |false);
	}
	
	/**
	 * Set the isJumpingMazub of this Mazub to the given isJumpingMazub.
	 * 
	 * @param  isJumpingMazub
	 *         The new isJumpingMazub for this Mazub.
	 * @post   The isJumpingMazub of this new Mazub is equal to
	 *         the given isJumpingMazub.
	 *       | new.getIsJumpingMazub() == isJumpingMazub
	 * @throws RuntimeException
	 *         The given isJumpingMazub is not a valid isJumpingMazub for any
	 *         Mazub.
	 *       | ! isValidIsJumpingMazub(getIsJumpingMazub())
	 */
	@Raw
	public void setIsJumpingMazub(boolean isJumpingMazub) 
			throws RuntimeException {
		if (! isValidIsJumpingMazub(isJumpingMazub))
			throw new RuntimeException();
		this.isJumpingMazub = isJumpingMazub;
	}
	
	/**
	 * Variable registering the isJumpingMazub of this Mazub.
	 */
	private boolean isJumpingMazub;
	
	/**
	 * Checking whether the Mazub is ducking or not.
	 * @return True if and only if the Mazub has a ducking sprite as his currentSprite
	 * 		 | result == (this.getCurrentSprite() == this.getSprites()[1]) ||
	 * 		 | (this.getCurrentSprite() == this.getSprites()[6]) ||
	 * 		 | (this.getCurrentSprite() == this.getSprites()[7])
	 */
	public boolean isDucking() {
			if (this.getCurrentSprite() == this.getSprites()[1]) 
				return true;
			if (this.getCurrentSprite() == this.getSprites()[6])
				return true;
			if (this.getCurrentSprite() == this.getSprites()[7])
				return true;
			else 
				return false;
	}
	/**
	 * Changing the Mazub into a ducking Mazub.
	 * @effect If the Mazub is moving, the horizontalVelocity is set to the duckingVelocity.
	 * 		   The horizontalVelocity is positive when he is moving to the left, and negative
	 * 		   when he is moving to the right
	 * 		 | if (this.getOrientation() == "left") 
	 * 		 |		this.setHorizontalVelocity(-duckingVelocity)
	 * 		 | if (this.getOrientation() == "right") 
	 * 		 |		this.setHorizontalVelocity(duckingVelocity)
	 * @effect The horizontalAcceleration is set to zero.
	 * 		 | this.setHorizontalAcceleration(0);
	 * @effect The currentSprite of the Mazub is changed. The sprite is selected from the
	 * 		   list of possible sprite of the Mazub and depends on the orientation. 
	 * 		 | if (this.getOrientation() == "left")
	 * 		 | 		this.setCurrentSprite(this.getSprites()[7])
	 *		 | if (this.getOrientation() == "right")
	 * 		 | 		this.setCurrentSprite(this.getSprites()[6])
	 *		 | if (this.getOrientation() == "front")
	 * 		 | 		this.setCurrentSprite(this.getSprites()[1])
	 */
	public void startDuck() {
		this.setHorizontalAcceleration(0);
		if (this.isMoving()) {
			if (this.getOrientation() == "left") {
				this.setHorizontalVelocity(-duckingVelocity);
				this.setCurrentSprite(this.getSprites()[7]);
			}
			if (this.getOrientation() == "right") {
				this.setHorizontalVelocity(duckingVelocity);
				this.setCurrentSprite(this.getSprites()[6]);
			}
		}
		if (this.getOrientation() == "front")
			this.setCurrentSprite(this.getSprites()[1]);					
	}
		
	/**
	 * Changing the ducking Mazub into a standing straight Mazub.
	 * @effect If the Mazub is moving, the horizontal acceleration is set to the moving acceleration.
	 *         The sign of the acceleration depends on the orientation
	 *       | if (this.getOrientation() == "left")
	 *       |		this.setHorizontalAcceleration(-movingAcceleration)
	 *       | if (this.getOrientation() == "right")
	 *       |		this.setHorizontalAcceleration(movingAcceleration)
	 * @effect The currentSprite is updated. The new sprite depends on the orientation.
	 *       | if (this.getOrientation() == "left")
	 *       |		this.setCurrentSprite(this.getSprites()[8+m])
	 *       | if (this.getOrientation() == "right")
	 *       |		this.setCurrentSprite(this.getSprites()[8])
	 *       | if(this.getOrientation() == "front")
	 *       |		this.setCurrentSprite(this.getSprites()[0])
	 * @effect if the new currentSprite overlaps with a pixel that has solid ground as 
	 * 		   its geological feature, the currentSprite is set to the previous sprite
	 * 		   and the variable aboutToDuck is set to true
	 *       | if((this.collidesWithGeological(this.getPixelX(), this.getPixelY(), World.SOLID_GROUND)) 
	 *       |	|| (this.getWorld().MazubOverlapsOtherMazub(this.getPixelX(), this.getPixelY())
	 *       |  || (this.collidesWithGeological(this.getPixelX(), this.getPixelY(),World.SOLID_GROUND))))
	 *       | 		this.setCurrentSprite(oldSprite)
	 *       |		this.aboutToDuck = true
	 */
	public void endDuck() {
		Sprite oldSprite = this.getCurrentSprite();
		if (this.isMoving()) {
			if (this.getOrientation() == "left") {
				this.setHorizontalAcceleration(-movingAcceleration);
				this.setCurrentSprite(this.getSprites()[8+m]);
				if ((this.collidesWithGeologicals(this.getPixelX(), this.getPixelY(), Geological.getAllImpassable())) 
							|| (this.collidesWithGeologicals(this.getPixelX(), this.getPixelY(),
									Geological.getAllImpassable()))) {
					this.setCurrentSprite(oldSprite);
					this.aboutToDuck = true;				
				}
			}
			if (this.getOrientation() == "right") {			
				this.setHorizontalAcceleration(movingAcceleration);
				this.setCurrentSprite(this.getSprites()[8]);
				if((this.collidesWithGeologicals(this.getPixelX(), this.getPixelY(), Geological.getAllImpassable())) 
							|| (this.meetsObstacle(this.getPixelX(), this.getPixelY()))) {
						this.setCurrentSprite(oldSprite);
						this.aboutToDuck = true;			}
			}
		}
		if ((! this.isMoving()) && (this.getOrientation() != "front")) {
			if (this.endDuckBumpingOnObjectAbove(this.getPixelX(), this.getPixelY())) {
					this.setCurrentSprite(oldSprite);
					this.aboutToDuck = true;			
					}
		}
		if(this.getOrientation() == "front") {
			this.setCurrentSprite(this.getSprites()[0]);
			if (this.endDuckBumpingOnObjectAbove(this.getPixelX(), this.getPixelY())) {
					this.setCurrentSprite(oldSprite);
					this.aboutToDuck = true;
			}
		}
		
	}
	
	/**
	 * Variable that checks whether the Mazub is trying to duck.
	 */
	private boolean aboutToDuck = false;

	/**
	 * Variable that checks whether the Mazub is tortured by a dead skullcab.
	 */
	private boolean skullcabDeadAndHurtsMazub;
	
	@Override
	public void advanceTime(double deltaT) throws IllegalArgumentException {
		if (deltaT < 0)
			throw new IllegalArgumentException();
		if (deltaT > 0.2) 
			throw new IllegalArgumentException();
		//int newPosX=this.getPixelY();
		//int newPosY=this.getPixelX();
		double deadtime = 0;
		Sprite oldSprite= this.getCurrentSprite();
		this.orientationCorrection(deltaT);
		this.updateSprites(deltaT);
		if (this.getWorld() == null) {
			if (! this.isDead()) {
				if (this.getActualY() > 0) {
					this.setVerticalAcceleration(Constants.VERTICAL_FALL_ACCELERATION);
					}		
					this.updatePosition(deltaT,deltaT);	
					this.setHorizontalVelocity(this.calculateHorizontalVelocity(deltaT));
					this.setVerticalVelocity(this.calculateVerticalVelocity(deltaT));
			}				
			else {
				this.setDelay(this.getDelay()+deltaT);
			}	
		}
		if (this.getWorld() != null) {	
			//if (this.isBlockingShark())
			if (! this.isDead()) {
				if (this.meetsHorizontalObstacle(this.getPixelX(),this.getPixelY())){;
					if (this.isMoving())
						this.endMove();
					this.setHorizontalAcceleration(0);
					this.setHorizontalVelocity(0);
					}
				if ((!this.objectOnImpassableTerrain(this.getPixelX(),this.getPixelY()))&&
						(!this.isStandingOnGameObject(this.getPixelX(), this.getPixelY()))){
					this.setVerticalAcceleration(Constants.VERTICAL_FALL_ACCELERATION);
				}
				double deltaTX = deltaT;
				double deltaTY = deltaT;
				double dt = calculateDT(deltaT);
				int nbdtX = 1;
				int nbdtY = 1;
				this.updateHitPoints(this.getPixelX(), this.getPixelY(),0);
				if (this.objectBumpsOnOtherObject(this.getPixelX(), this.getPixelY())) {
					this.setVerticalVelocity(0);
					this.setHorizontalAcceleration(0);
					this.setHorizontalVelocity(0);

				}
				for (double time = dt; time <= deltaT;time+= dt) {
					double newPosX = this.calculatePositionX(nbdtX*dt);
					double newPosY = this.calculatePositionY(nbdtY*dt);
					this.updateHitPoints((int)(100*newPosX),(int)(100*newPosY),dt);
					nbdtX += 1;
					nbdtY += 1;
					//if ((this.objectOnImpassableTerrain(this.getPixelX(),this.getPixelY()))&&
					//		(this.objectOnImpassableTerrain((int) newPosX, (int) newPosY))){
					//	this.setVerticalAcceleration(VERTICAL_FALL_ACCELERATION);
					//}
					if (this.objectBumpsOnOtherObject((int) (100*newPosX),(int) (100*newPosY))) {
						this.updatePosition((nbdtX-2)*dt, (nbdtY-2)*dt);
						this.setVerticalVelocity(0);
						if ((!this.objectOnImpassableTerrain(this.getPixelX(),this.getPixelY()))&&
								(!this.isStandingOnGameObject(this.getPixelX(),this.getPixelY()))){
							this.setVerticalAcceleration(Constants.VERTICAL_FALL_ACCELERATION);
						}
						this.setHorizontalAcceleration(0);
						this.setHorizontalVelocity(0);
						deltaTX -= (nbdtX-2)*dt;
						deltaTY -= (nbdtY-2)*dt;
						nbdtX=2;
						nbdtY = 2;
					}
					
					if (((this.objectOnImpassableTerrain((int) (100*newPosX),(int) (100*newPosY)))||
							(this.isStandingOnGameObject((int) (100*newPosX),(int) (100*newPosY))))
							&&(this.getVerticalVelocity()< 0)) {			
						this.setPixelY((int)(100*newPosY));
						this.setActualY(newPosY);
						deltaTY -= ((nbdtY-1)*dt);
						nbdtY = 1;
						this.setVerticalAcceleration(0);
						this.setVerticalVelocity(0);
							}
					if ((this.objectBumpsImpassableTerrainAbove((int) (100*newPosX),(int) (100*newPosY)))
							&&(this.getVerticalVelocity()> 0)) {
						this.setPixelY((int)(100*newPosY));
						this.setActualY(newPosY);
						deltaTY -= ((nbdtY-1)*dt);
						nbdtY = 1;
						this.setVerticalVelocity(0);
						}
					if (this.meetsHorizontalObstacle((int) (100*newPosX),
							(int) (100*newPosY))){
						this.setPixelX((int)(100*newPosX));
						this.setActualX(newPosX);
						if (this.isMoving())
							this.endMove();
						deltaTX -= ((nbdtX-1)*dt);
						nbdtX = 1;
						this.setHorizontalAcceleration(0);
						this.setHorizontalVelocity(0);
						}

						if (this.getHitPoints() <= 0) {
							this.kill();
							deadtime = deltaT-time;
							this.setDelay(this.getDelay()+deadtime);
							if (this.getDelay()>0.6) {
								this.terminate();
								}
							break;
						}
						this.updateHitpointsAfterInteractionsWithGameObjects((int)(100*newPosX), (int) (100*newPosY));
						this.eatPlants((int)(100*newPosX),(int) (100*newPosY),dt);
						if (this.isOutOfThisWorld(newPosX, newPosY)) {
							this.kill();
							this.terminate();
							break;
							}
					}
					if ((! this.isDead())&&(!this.isTerminated())) {
						double newPosX = this.calculatePositionX(deltaTX);
						double newPosY = this.calculatePositionY(deltaTY);
						if (this.objectBumpsOnOtherObject((int) (100*newPosX),(int) (100*newPosY))) {
							this.updateHitpointsAfterInteractionsWithGameObjects((int) (100*newPosX), (int) (100*newPosY));
							this.updatePosition(deltaTX-deltaT%dt, deltaTX-deltaT%dt);
							if ((!this.objectOnImpassableTerrain((int) (100*newPosX),(int) (100*newPosY)))&&
									(!this.isStandingOnGameObject((int) (100*newPosX),(int) (100*newPosY)))){
								this.setVerticalAcceleration(Constants.VERTICAL_FALL_ACCELERATION);
							}
							this.setHorizontalAcceleration(0);
							this.setHorizontalVelocity(0);	
						}
						else {
							this.updatePosition(deltaTX,deltaTY);
						}
						this.setVerticalVelocity(this.calculateVerticalVelocity(deltaTX));		
						this.setHorizontalVelocity(this.calculateHorizontalVelocity(deltaTY));
						this.eatPlants(this.getPixelX(),this.getPixelY(),deltaT % dt);
						this.updateHitPoints(this.getPixelX(),this.getPixelY(), deltaT%dt);
						if (! this.isDead()){
							if (this.getWorld() != null)
								if (this.collidesWithGeologicals(this.getPixelX(),this.getPixelY(),
										Geological.getAllImpassable())) 
									{
//									if (this.mazubBumpsSolidGroundAbove(this.getPixelX(), this.getPixelY())) {
//										this.setCurrentSprite(oldSprite);
//									}
									if (! this.mazubStartingInImpassableTerrain(this.getPixelX(), this.getPixelY())) {
										this.setCurrentSprite(oldSprite);
									}
									
								}
							//this.updateMazubAtTheBoundaryOfImpTile((int) (newPosX),(int) (newPosY));
							//this.meetsNewMazub();
	
							if (((this.objectOnImpassableTerrain(this.getPixelX(),this.getPixelY()))||
									(this.isStandingOnGameObject(this.getPixelX(),this.getPixelY())))
									&&(this.getVerticalVelocity()< 0)) { 
							if (this.isJumping())
								this.endJump();
							}
						if (this.meetsSlime(this.getPixelX(),this.getPixelY())) {
								if (this.getTimeSlimeBlocksMazub() == 0) {
									this.setHitPoints(this.getHitPoints()-20);
								}
								this.setTimeSlimeBlocksMazub(this.getTimeSlimeBlocksMazub()+dt);
								if (this.getTimeSlimeBlocksMazub() > 0.6) {
									this.setHitPoints(this.getHitPoints()-
										((int) (this.getTimeSlimeBlocksMazub()/0.6))*20);
									this.setTimeSlimeBlocksMazub((this.getTimeSlimeBlocksMazub()%0.6));									
								}		
							}
						if (this.meetsHorizontalObstacle(this.getPixelX(),this.getPixelY())) {
							
							this.setHorizontalAcceleration(0);
							this.setHorizontalVelocity(0);
								}
//						if ((this.objectOnImpassableTerrain(this.getPixelX(),this.getPixelY()))&&
//								(this.isStandingOnGameObject(this.getPixelX(),this.getPixelY())))  { 
//							if (this.isJumping())	
//								this.endJump();	
//							}
			
						}
							
			
						if (this.isOutOfThisWorld(this.getActualX(), this.getActualY())) {
							this.kill();	
							this.terminate();
									}
					}
			}
			
			else {
				this.setDelay(this.getDelay()+deltaT);
				if (this.getDelay()>0.6) {
					this.terminate();
				}
		}
		}
		this.skullcabDeadAndHurtsMazub = false;
		}

	/**
	 * A method that checks whether the Mazub meets an obstacle.
	 * @param posX
	 * 		  pixelX of the Mazub
	 * @param posY
	 * 		  pixelY of the Mazub
	 * @return True if and only if the mazub bumps on Impassable Terrain with either his upside,
	 * 		   his leftside or his rightside.
	 * 		 | result == (this.objectBumpsImpassableTerrainAbove(posX, posY))||
	 *       | (this.objectBumpsImpassableTerrainLeft(posX, posY)) || (this.objectBumpsImpassableTerrainRight(posX, posY))
	 */
	public boolean meetsObstacle(int posX, int posY) {
		if (this.objectBumpsImpassableTerrainAbove(posX, posY)) {
			return true;
		}
		if (this.objectBumpsImpassableTerrainLeft(posX, posY)) {
			return true;
		}
		if (this.objectBumpsImpassableTerrainRight(posX, posY)) {
			return true;
		}
		else {
			return false;
		}
	}

	/** 
	 * A method that determines whether a Mazub is in contact with a slime.
	 * @return true if and only if the Mazub collides with a Slime from the 
	 * 			same world
	 * 		 | for (GameObject otherGameObjects:this.getWorld().getGameObjects())
	 * 		 | 		if (otherGameObjects instanceof Slime)
	 * 		 |			if (this.collidesWithOtherGameObect(newPosX,newPosY,otherGameObjects))
	 * 		 |				return true
	 */

	public boolean meetsSlime(int newPosX,int newPosY) {
		
		for (GameObject otherGameObjects:this.getWorld().getGameObjects()) {
			if (otherGameObjects instanceof Slime) {
				if (this.collidesWithOtherGameObect(newPosX,newPosY,otherGameObjects))
					return true;
			}
		}
		return false;
	}
	/** 
	 * A method that determines whether a Mazub is in contact with a Shark.
	 * @return true if and only if the Mazub collides with a Shark from the 
	 * 			same world
	 * 		 | for (GameObject otherGameObjects:this.getWorld().getGameObjects())
	 * 		 | 		if (otherGameObjects instanceof Shark)
	 * 		 |			if (this.collidesWithOtherGameObect(newPosX,newPosY,otherGameObjects))
	 * 		 |				return true
	 */
	public boolean meetsShark(int newPosX,int newPosY) {
		
		for (GameObject otherGameObjects:this.getWorld().getGameObjects()) {
			if (otherGameObjects instanceof Shark) {
				if (this.collidesWithOtherGameObect(newPosX,newPosY,otherGameObjects))
					return true;
			}
		}
		return false;
	}
			
	
	

	/** 
	 * A method that accounts for collisions between the Mazub and the plants 
	 * @param posX
	 * 		  The pixelX of the Mazub.
	 * @param posY
	 * 		  the pixelY of the Mazub.
	 * @effect If the mazub collides with a plant that is  dead, the mazub loses 20 hitpoints and
	 * 			the plant is terminated.
	 * 		 | if (plant.collidesWith(newPosX, newPosY,
	 * 		 | 		this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight())) 
	 *       | 		if ((plant.isDead())
	 *       |			if (this.skullcabDeadAndHurtsMazub==false)
	 *       | 				this.setHitPoints(this.getHitPoints()-20)
	 *       |			plant.terminate()
	 * @effect If the mazub collides with a Sneezewort that is not dead and the mazub has not 
	 * 		   reached his maximum of 500 hitpoints yet, 50 hitpoints are added.
	 * 		 | if (plant.collidesWith(newPosX, newPosY,
	 * 		 | 		this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight())) 
	 *       | 		if ((! plant.isDead()) && (this.getHitPoints() <500)) 
	 *       |			if (plant instanceof Sneezewort)
	 *       | 				this.setHitPoints(this.getHitPoints()+50)
	 *       |				plant.terminate()
	 * @effect If the mazub collides with a Skullcab that is not dead and the mazub has not 
	 * 		   reached his maximum of 500 hitpoints yet, 50 hitpoints are added to the mazub and
	 * 			1 hitpoint is subtracted form the SkullCab at the time of the first contact.
	 * 			This is repeated after every full period of contact of 0.6s. 
	 * 		 | if (plant.collidesWith(newPosX, newPosY,
	 * 		 | 		this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight())) 
	 *       | 		if ((! plant.isDead()) && (this.getHitPoints() <500)) 
	 *       |			if (plant instanceof Skullcab) 
	 *       |				if (((Skullcab)plant).timeInContactWithMazub == 0)
	 *       | 					this.setHitPoints(this.getHitPoints()+50)
	 *       |					((Skullcab)plant).setHitPoints(((Skullcab)plant).getHitPoints()-1)
	 *       |				if (((Skullcab)i).timeInContactWithMazub >=0.6) 
	 *       | 					this.setHitPoints(this.getHitPoints()+50)
	 *       |					((Skullcab)plant).setHitPoints(((Skullcab)plant).getHitPoints()-1)
	 *       |					plant.terminate()
	 *       |				if (((Skullcab)plant).getHitPoints() == 0)
	 *       |					((Skullcab)plant).kill()
	 */
	public void eatPlants(int newPosX,int newPosY,double dt) {
		Set<GameObject> copyGameObjects = this.getWorld().getGameObjects();
		Set<GameObject> newSet = copyGameObjects.stream().collect(Collectors.toSet());	
		for (GameObject i: newSet) {	
			if (i instanceof Plant) {
				if (i.collidesWith(newPosX, newPosY,
						this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight())) {
					if ( i.isDead() ) {
						if (this.skullcabDeadAndHurtsMazub==false)
							this.setHitPoints(this.getHitPoints()-20);
						i.terminate();
					}
					if ((! i.isDead()) && (this.getHitPoints() <500)) {
						if (i instanceof Sneezewort) {
						i.terminate();
						this.setHitPoints(this.getHitPoints()+50);
						}
						if (i instanceof Skullcab) {
							if (((Skullcab)i).timeInContactWithMazub == 0) {
								this.setHitPoints(this.getHitPoints()+50);
								((Skullcab)i).setHitPoints(((Skullcab)i).getHitPoints()-1);
							}
								
							((Skullcab)i).timeInContactWithMazub += dt;
							if (((Skullcab)i).timeInContactWithMazub >=0.6) {
								this.setHitPoints(this.getHitPoints() +50);
								((Skullcab)i).timeInContactWithMazub = ((Skullcab)i).timeInContactWithMazub%0.6;
								((Skullcab)i).setHitPoints(((Skullcab)i).getHitPoints()-1);
								if (((Skullcab)i).getHitPoints() == 0) {
									this.skullcabDeadAndHurtsMazub = true;
									((Skullcab)i).kill();
								}
							}
						}
					}
			
				}
			if (( ! i.collidesWith(newPosX, newPosY,
					this.getCurrentSprite().getWidth(), this.getCurrentSprite().getHeight()))
					&& (i instanceof Skullcab))
				((Skullcab)i).timeInContactWithMazub = 0;
			
			}
		}
	}

	/**
	 * Updates the hitpoints of a mazub when time has passed and/or his position has changed.
	 * @param newPosX
	 * 		  pixelX of the mazub
	 * @param newPosY
	 * 		  pixelY of the mazub
	 * @param deltaT
	 * 		  the time that has passed
	 * @effect if the given Mazub is in water and not in magma or gas, the Mazub 
	 * 			loses 2 hitpoints every 0.2 seconds.
	 *       | if ((this.collidesWithGeological(newPosX,newPosY, World.WATER)) && 
	 *		 |	(! this.collidesWithGeological(newPosX,newPosY, World.MAGMA))) &&
	 *		 |	(! this.collidesWithGeological(newPosX,newPosY, Geological.GAS))))
	 *		 | 		if (this.isInWater())
	 *		 |			if (this.getTimeInWater() >0.2)
	 *		 |				this.setHitPoints(this.getHitPoints()- ((int) (this.getTimeInWater()/0.2))*2)
	 *		 |		if (!this.isInWater())
	 *		 |			this.setInWater(true)
	 *		 |			
	 * @effect if the given Mazub is in magma, he loses 50 hitpoints 
	 *         every 0.2 seconds. And 50 hitpoints at the time of the first contact.
	 *       | if (this.collidesWithGeological(newPosX,newPosY, World.MAGMA))
	 *       |		if (this.isInMagma())
	 *		 |	    	if (this.getTimeInMagma() > 0.2)
	 *		 |				this.setHitPoints(this.getHitPoints()- ((int) (this.getTimeInMagma()/0.2))*50)
	 *		 |		if (!this.isInMagma())
	 *		 |			this.setHitPoints(this.getHitPoints()-50)
	 * @effect if the given Mazub is in gas and not in magma, he loses 4 hitpoints 
	 *         every 0.2 seconds. And 4 hitpoints at the time of the first contact.
	 *       | if ((this.collidesWithGeological(newPosX,newPosY, World.MAGMA)) &&
	 *       |	(! this.collidesWithGeological(newPosX,newPosY, Geological.MAGMA)))
	 *       |		if (this.isInGas()) 
	 *		 |	    	if (this.getTimeInGas() > 0.2)
	 *		 |				this.setHitPoints(this.getHitPoints()- ((int) (this.getTimeInGas()/0.2))*4)
	 *		 |		if (!this.isInGas())
	 *		 |			this.setHitPoints(this.getHitPoints()-4)
	 */
	public void updateHitPoints(int newPosX, int newPosY, double deltaT) {
		if ((this.collidesWithGeological(newPosX,newPosY, Geological.WATER)) && (
				(! this.collidesWithGeological(newPosX,newPosY, Geological.MAGMA)) && 
				(! this.collidesWithGeological(newPosX,newPosY, Geological.GAS)))){
			if (this.isInWater()) {
					this.setTimeInWater(this.getTimeInWater() + deltaT);
					if (this.getTimeInWater() >0.2) {
						this.setHitPoints(this.getHitPoints()- ((int) (this.getTimeInWater()/0.2))*2);
						this.setTimeInWater(this.getTimeInWater()%0.2);
						}
			}
			if (!this.isInWater())
				this.setInWater(true);
		}
		if (! this.collidesWithGeological(newPosX,newPosY, Geological.WATER)) {
			this.setTimeInWater(0);
			this.setInWater(false);
		}
		if (! this.collidesWithGeological(newPosX,newPosY, Geological.MAGMA)) {
			this.setTimeInMagma(0);
			this.setInMagma(false);
		
		}
		if (this.collidesWithGeological(newPosX,newPosY, Geological.MAGMA)) {
			if (this.isInMagma()) {
				this.setTimeInMagma(this.getTimeInMagma() + deltaT);
				if (this.getTimeInMagma() > 0.2) {
					this.setHitPoints(this.getHitPoints()- ((int) (this.getTimeInMagma()/0.2))*50);
					this.setTimeInMagma(this.getTimeInMagma()%0.2);
				}		
			}
			if (!this.isInMagma()) {
				this.setHitPoints(this.getHitPoints()-50);
				this.setInMagma(true);
					}
			}
		
		if (!this.collidesWithGeological(newPosX,newPosY, Geological.GAS)){
			this.setTimeInGas(0);
			this.setInGas(false);
		}
		
		if ((this.collidesWithGeological(newPosX,newPosY, Geological.GAS))  && 
				(! this.collidesWithGeological(newPosX,newPosY, Geological.MAGMA))){
			
			if (this.isInGas()) {
				this.setTimeInGas(this.getTimeInGas() + deltaT);
				if (this.getTimeInGas() > 0.2) {
					this.setHitPoints(this.getHitPoints()- ((int) (this.getTimeInGas()/0.2))*4);
					this.setTimeInGas(this.getTimeInGas()%0.2);	
			}
			}
			if (!this.isInGas()) {
				this.setHitPoints(this.getHitPoints()-4);
				this.setInGas(true);	
			}
	
			
	}
	}
	
	/**
	 * A method that updates the currrentSprite when a given amount of time is passed.
	 * @param deltaT
	 * 		  the time that has passed
	 * @effect if the new sprite has an index lower than 8 in the list of possible 
	 *         sprites for the mazub, the currentSprite is changed to the new sprite for the mazub
	 * 		 | if (this.determineSpriteIndex()<8)
	 *       |     this.setCurrentSprite(this.getSprites()[this.determineSpriteIndex()])
	 * @effect if the new sprite has index 8 in the list of possible 
	 *         sprites for the mazub, the Mazub is in a state where his currentSprite alternates.
	 * 		 | if (this.determineSpriteIndex()==8)
	 *       |     this.animatedSprites(deltaT,Arrays.copyOfRange(this.getSprites(), 8, 13))
	 * @effect if the new sprite has index 8 + m in the list of possible 
	 *         sprites for the mazub, the Mazub is in a state where his currentSprite alternates.
	 * 		 | if (this.determineSpriteIndex()==8+m)
	 *       |     this.animatedSprites(deltaT,Arrays.copyOfRange(this.getSprites(), 13, 18))
	 */
	public void updateSprites(double deltaT) {
		int index = this.determineSpriteIndex();			
		if (index < 8) {
			this.setCurrentSprite(this.getSprites()[index]);
			this.setRuntime(0);
		}
		if (index == 8) {
			this.animatedSprites(deltaT,Arrays.copyOfRange(this.getSprites(), 8, 8+m));
		}
		if (index == 8+m) {
			this.animatedSprites(deltaT,Arrays.copyOfRange(this.getSprites(), 8+m,8+2*m));
		}
	}
	

	
		
	/**
	 * A method to determine the right sprite it there are multiple sprites to describe the 
	 * current state of the Mazub.
	 * @param deltaT
	 * 		  The time interval deltaT
	 * @param subSpriteArray
	 * 		  The array of images (a subset of the array of sprites) to describe the current state of the Mazub.
	 * @effect The current sprite of the Mazub is set to the right sprite to describe its current position.
	 */
	private void animatedSprites(double deltaT,Sprite[] subSpriteArray) {
		this.setRuntime(this.getRuntime()+deltaT);
		int k = (int) (this.getRuntime() / 0.075);
		int l = k% subSpriteArray.length;
		int oldindex = this.returnIndexOfArray(subSpriteArray);
		int newIndex = oldindex +l;
		if (newIndex >=subSpriteArray.length) {
			newIndex = newIndex - subSpriteArray.length;
		}
		this.setCurrentSprite(subSpriteArray[newIndex]);			
		this.setRuntime(this.getRuntime() % 0.075);
		}

	/**
	 * Returns the index of the currentSprite in an array with all possible sprites.
	 * @param array
	 * 		  array with all possible sprites
	 * @return the index where the sprite is found in the array
	 *       | for (int i= 0; i < array.length; i++ ) 
     *	     |     if (this.getCurrentSprite() == array[i])
	 *		 |			index = i
	 *@return zero if the sprite is not found in the array
	 *       | index = 0
	 */
	public int returnIndexOfArray(Sprite[] array) {
		for (int i= 0; i < array.length; i++ ) {
			if (this.getCurrentSprite() == array[i])
				return i;
		}
		return 0;	
	}
	/**
	 * A method to determine the index in the list of possible sprite for the new currentSprite.
	 */
	public int determineSpriteIndex() {
		if (! this.isMoving()) {
			Sprite oldSprite = this.getCurrentSprite();
			if (this.getOrientation() == "front") {
				if (this.isDucking())
					return 1;
				else
					return 0;
			}
			else if (this.getOrientation() == "left") {
				return 3;
			}
			else
				return 2;
		}
		else {
			if (this.getOrientation() == "left") {
				if (this.isDucking())
					return 7;
				else
					if (this.isJumping())
						return 5;
					else
						return 8+m;
			}
			else {
				if (this.isDucking())
					return 6;
				else
					if (this.isJumping())
						return 4;
					else
						return 8;
			}
		}			
	}		
	/**
	 * A method to change the orientation of the Mazub if he is standing still for longer than 1 second.	
	 * @param deltaT
	 * 		  The time that is to be added to the time that he is standing still.
	 * @return If the updated standingStillTime is bigger than 1, the orientation is set to
	 * 		   front.
	 * 		 | if (standingStillTime >= 1) 
     *		 |	this.setOrientation("front")
     *@return If the updated standingStillTime is bigger than 1, the standingStillTime is 
	 * 		  set to zero
	 * 		 | if (standingStillTime >= 1) 
     *		 |	this.setOrientation("front")
	 */
	public void orientationCorrection(double deltaT) {
		if ((! this.isMoving()) && (this.getOrientation() != "front"))
			this.setStandingStillTime(this.getStandingStillTime() + deltaT);
			if (standingStillTime >= 1) {
				this.setOrientation("front");
				this.setStandingStillTime(0);
			}	
		
	}
	
	/**
	 * Check whether the given sprites is a valid sprites for
	 * any Mazub.
	 *  
	 * @param  sprites
	 *         The sprites to check.
	 * @return True if and only if number of possible sprites is is bigger than 10
	 *         and divisible by 2.
	 *       | result ==  (sprites.length > 10) && (sprites.length % 2 == 0)
	*/
	public  boolean isValidSprite(Sprite... sprites) {
		if (sprites.length <= 10) 
			return false;
		if (sprites.length % 2 != 0)
			return false;
		return true;
	}
	
	/**
	 * Return the runtime of this Mazub.
	 */
	@Basic @Raw
	public double getRuntime() {
		return this.runtime;
	}
	
	/**
	 * Check whether the given runtime is a valid runtime for
	 * any Mazub.
	 *  
	 * @param  runtime
	 *         The runtime to check.
	 * @return True if and only if not below 0.
	 *       | result == runtime >= 0
	*/
	public static boolean isValidRuntime(double runtime) {
		return runtime >=0;
	}
	
	/**
	 * Set the runtime of this Mazub to the given runtime.
	 * 
	 * @param  runtime
	 *         The new runtime for this Mazub.
	 * @post   The runtime of this new Mazub is equal to
	 *         the given runtime.
	 *       | new.getRuntime() == runtime
	 * @throws RuntimeException
	 *         The given runtime is not a valid runtime for any
	 *         Mazub.
	 *       | ! isValidRuntime(getRuntime())
	 */
	@Raw
	public void setRuntime(double runtime) 
			throws RuntimeException {
		if (! isValidRuntime(runtime))
			throw new RuntimeException();
		this.runtime = runtime;
	}
	
	/**
	 * Variable registering the runtime of this Mazub.
	 */
	private double runtime;
	

	@Basic @Raw
	/**
	 * Return the standingStillTime of this Mazub.
	 */
	public double getStandingStillTime() {
		return this.standingStillTime;
	}
	
	/**
	 * Check whether the given standingStillTime is a valid standingStillTime for
	 * any Mazub.  
	 * @param  standingStillTime
	 *         The standingStillTime to check.
	 * @return True if and only if standingStillTime is not below 0.
	 *       | result == standingStillTime >= 0
	*/
	public static boolean isValidStandingStillTime(double standingStillTime) {
		return standingStillTime >=0;
	}
	
	/**
	 * Set the standingStillTime of this Mazub to the given runtime.
	 * 
	 * @param  standingStillTime
	 *         The new standingStillTime for this Mazub.
	 * @post   The standingStillTime of this new Mazub is equal to
	 *         the given standingStillTime.
	 *       | new.getStandingStillTime() == standingStillTime
	 * @throws RuntimeException
	 *         The given standingStillTime is not a valid standingStillTime for any
	 *         Mazub.
	 *       | ! isValidStandingStillTime(getStandingStillTime())
	 */
	@Raw
	public void setStandingStillTime(double standingStillTime) 
			throws RuntimeException {
		if (! isValidStandingStillTime(standingStillTime))
			throw new RuntimeException();
		this.standingStillTime = standingStillTime;
	}
	
	/**
	 * Variable registering the time since the Mazub stopped moving.
	 */
	private double standingStillTime;
	
	/**
	 * Return the blocksShark of this Mazub.
	 */
	private boolean getBlocksShark() {
		return this.blocksShark;
	}
	/**
	 * Set the blocksShark that applies to all Mazubs to the given blocksShark.
	 * 
	 * @param  blocksShark
	 *         The new blocksShark for all Mazubs.
	 * @post   The new blocksShark is equal to the given
	 *         blocksShark.
	 *       | new.blocksShark = blocksShark;
	 */
	private void setBlocksShark(boolean blocksShark) {
		this.blocksShark = blocksShark; 
	}

	/**
	 * A Boolean registering the blocksShark that applies to all Mazubs.
	 */
	private boolean blocksShark = false;
	
	/**
	 * Return the m of this Mazub.
	 */
	private int getM() {
		return this.m;
	}
	
	/**
	 * Set the m that applies to all Mazubs to the given m.
	 * 
	 * @param  m
	 *         The new m for all Mazubs.
	 * @post   The new m is equal to the given
	 *         m.
	 *       | new.m = m;
	 */
	private void setM(int m) {
		this.m = m;
	}
	/**
	 * Variable registering the number of multiple sprites m to describe the current position of the Mazub.
	 */
	private int m = 11;
	
	/**
	 * A method verifying whether the given horizontalAcceleration is a valid horizontalAcceleration.
	 * @param horizontalAcceleration
	 * 		  the given horizontalAcceleration
	 * @return true if the horizontalAcceleration is either 0 or the movingAcceleration.
	 * 		  | result = (horizontalAcceleration == 0)|| 
	 *		  |	(Math.abs(horizontalAcceleration) == movingAcceleration)
	 */
	@Override
	boolean isValidHorizontalAcceleration(double horizontalAcceleration) {
		return (horizontalAcceleration == 0)|| 
				(Math.abs(horizontalAcceleration) == movingAcceleration);
	}	
	/**
	 * Return the timeSlimeBlocksMazub of this Mazub.
	 */
	public double getTimeSlimeBlocksMazub() {
		return this.timeSlimeBlocksMazub;
	}
	/**
	 * Set the timeSlimeBlocksMazub that applies to all Mazubs to the given timeSlimeBlocksMazub.
	 * 
	 * @param  timeSlimeBlocksMazub
	 *         The timeSlimeBlocksMazub for the Mazubs.
	 * @post   
	 *       | new.timeSlimeBlocksMazub = timeSlimeBlocksMazub
	 */
	public void setTimeSlimeBlocksMazub(double timeSlimeBlocksMazub) {
		this.timeSlimeBlocksMazub = timeSlimeBlocksMazub;
	}
	/**
	 * Variable registering the timeSlimeBlocksMazub that applies to all Mazubs.
	 */
	public double timeSlimeBlocksMazub=0;

	/**
	 * A method that checks whether the new sprite of the mazub that just stopped ducking
	 * overlaps with an object on top of him. 
	 * @param x
	 * 		  pixelX of the mazub
	 * @param y
	 * 	 	  pixelY of the mazub
	 * @return True if and only if there is an object
	 * 			on the row on top of the highest row of the mazub.
	 * 		| for (GameObject gameObjects: this.getWorld().getGameObjects())
	 * 		| 	if ((!( gameObjects instanceof Plant))&& (gameObjects != this))
	 * 		|		for (int index=0;index < this.getPixelWidth();index ++)
	 * 		|			if (y + this.getPixelHeight() >= gameObjects.getPixelY())
	 * 		|				if ((gameObjects.getPixelX() <= x+index) && (x+index < gameObjects.getPixelX() +gameObjects.getPixelWidth()))
	 * 		|					return true
	 */
	public boolean endDuckBumpingOnObjectAbove(int x, int y) {
		int xP = this.getPixelWidth();
		int yP = this.getPixelHeight();
		for (GameObject gameObjects: this.getWorld().getGameObjects()) {
			if ((!( gameObjects instanceof Plant))&& (gameObjects != this)) {
				int xOther = gameObjects.getPixelX();
				int yOther = gameObjects.getPixelY();
				int xPOther = gameObjects.getPixelWidth();
				for (int index=0;index < xP;index ++) {
					if (y + yP >= yOther) {
						if ((xOther <= x+index) && (x+index < xOther +xPOther)) {
							return true;
						}
					}
				}
					}
				
					}
				return false;
				
			}
	
	/**
	 * A method updating the hitpoints of the Mazub after interactions with other GameObjects
	 * @param x
	 * 		  the pixelX of the Mazub
	 * @param y	
	 * 		  the pixelY of the Mazub
	 * @effect If the mazub is in contact with a Slime, he loses 20 hitpoints at the time of the contact
	 * 			and again after every period of 0.6s of contact
	 *		  |	if (this.collidesWithOtherGameObect(x, y, otherGameObject)) 
	 *		  | 	if ((otherGameObject instanceof Slime)&& (! this.isDead()) &&
	 *		  |			(! otherGameObject.isDead())) 
	 *		  |				if (this.getTimeSlimeBlocksMazub() == 0) 
	 *        |					this.setHitPoints(this.getHitPoints()-20)
	 *        |				if (this.getTimeSlimeBlocksMazub() > 0.6)
	 *        |					this.setHitPoints(this.getHitPoints()-((int) (this.getTimeSlimeBlocksMazub()/0.6))*20)
	 *        |					this.setTimeSlimeBlocksMazub(this.getTimeSlimeBlocksMazub()%0.6))
	 * @effect A mazub loses 50 hitpoints at the time of contact with a Shark
	 *		  |		if ((otherGameObject instanceof Shark)&& (! this.isDead()) &&
	 *		  |			(! otherGameObject.isDead())) 
	 *		  |				this.setHitPoints(this.getHitPoints() - 50)
	 */
	public void updateHitpointsAfterInteractionsWithGameObjects(int x, int y) {
		for (GameObject otherGameObject: this.getWorld().getGameObjects()) {
			if (this.collidesWithOtherGameObect(x, y, otherGameObject)) {
				if ((otherGameObject instanceof Slime)&& (! this.isDead()) &&
								(! otherGameObject.isDead())) {
					if (this.getTimeSlimeBlocksMazub() == 0) {
						this.setHitPoints(this.getHitPoints()-20);
						}
					//this.timeSlimeBlocksMazub+=dt;
					if (this.getTimeSlimeBlocksMazub() > 0.6) {
						this.setHitPoints(this.getHitPoints()-
							((int) (this.getTimeSlimeBlocksMazub()/0.6))*20);
						this.setTimeSlimeBlocksMazub((this.getTimeSlimeBlocksMazub()%0.6));									
					}
				}
				if ((otherGameObject instanceof Shark)&& (! this.isDead()) &&
								(! otherGameObject.isDead())) {
					if (! this.getListSharks().contains(otherGameObject)) {
						this.setHitPoints(this.getHitPoints() - 50);
							this.addHittenShark((Shark) otherGameObject);
							}
					else {
						this.setBlocksShark(true);
							}			
						}					
								
							}
						}
			}
	/**
	 * A boolean checking whether the lowest row of pixels of the Mazub 
	 * is overlapping with impassable terrain. 
	 * @param posX
	 * 		  The pixelX of the Mazub.
	 * @param posY
	 * 		  the pixelY of the Mazub.
	 * @return True if and only if Mazub's bottom row of pixels consist of a pixel that
	 * 		   has impassable terrain as its geological feature while the rest of the mazub is 
	 * 		   not overlapping with impassable terrain.
	 * 	     | for (index=0;index <=this.getCurrentSprite().getWidth();index ++) 
	 *		 | if ((Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY))) &&
	 *		 |	(!Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY+1))))
	 *		 |			return true
	 */
	public boolean mazubStartingInImpassableTerrain(int posX,int posY) {
		int index;
		for (index=0;index <this.getCurrentSprite().getWidth();
				index ++) {
			if ((Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY))) &&
				(!Geological.getAllImpassable().contains(this.getWorld().getGeologicalFeature(posX+index, posY+1))))
				return true;
		}
	
			return false;
		
	}
	
	/**
	 * A method that adds a given Shark to the list of Sharks that the Mazub
	 * has hitted.
	 */
	public void addHittenShark(Shark shark) {
		hittenSharks.add(shark);
	}
	/**
	 * A method that returns the list of Sharks that the Mazub
	 * has hitted.
	 */
	public List <Shark> getListSharks(){
		return this.hittenSharks;
	}
	/**
	 * A list that keeps track of the Sharks that the Mazub has hitted.
	 */
	protected List <Shark> hittenSharks = new ArrayList<>();
	
	/**
	 * Check whether the given horizontalVelocity is a valid horizontalVelocity for
	 * any Mazub.
	 *  
	 * @param  horizontalVelocity
	 *         The horizontalVelocity to check.
	 * @return True if and only if the given horizontalVelocity is not below the minimum value and
	 *         not above the maximum value or if it is equal to zero.
	 *         | result == Math.abs(horizontalVelocity) >= getMinHorizontalVelocity() && 
	 *         | Math.abs(horizontalVelocity) <= getMaxHorizontalVelocity()	||
	 *		   |	horizontalVelocity == 0)
	 */
	@Override
	public boolean isValidHorizontalVelocity(double horizontalVelocity) {
		if ((Math.abs(horizontalVelocity) >= this.getMinHorizontalVelocity() && 
				Math.abs(horizontalVelocity) <= this.getMaxHorizontalVelocity()) ||
				horizontalVelocity == 0) {

			return true;
		}
		else
			return false;
	}
	
	/**
	 * Return the DuckingVelocity that applies to all Mazubs.
	 */
	public static double getDuckingVelocity() {
		return Mazub.duckingVelocity;
	}
	/**
	 * Set the duckingVelocity that applies to all Mazubs to the given duckingVelocity.
	 * 
	 * @param  duckingVelocity
	 *         The new duckingVelocity for all Mazubs.
	 * @post   The new duckingVelocity is equal to the given
	 *         duckingVelocity.
	 *       | Mazub.duckingVelocity = duckingVelocity;
	 */
	public static void setDuckingVelocity(double duckingVelocity) {
		Mazub.duckingVelocity = duckingVelocity;
	}

	/**
	 * Variable registering the duckingVelocity that applies to all Mazubs.
	 */
	public static double duckingVelocity = 1;
	
	/**
	 * Return the moving acceleration that applies to all Mazubs in motion.
	 */
	
	public static double getMovingAcceleration() {
		return movingAcceleration;
	}
	
	/**
	 * Set the movingAcceleration that applies to all Mazubs to the given movingAcceleration.
	 * 
	 * @param  movingAcceleration
	 *         The new movingAcceleration for all Mazubs.
	 * @post   The new movingAcceleration is equal to the given
	 *         movingAcceleration.
	 *       | Mazub.movingAcceleration = movingAcceleration;
	 */
	public static void setMovingAcceleration(double movingAcceleration) {
		Mazub.movingAcceleration = movingAcceleration;
	}
		
	/**
	 * Variable registering the movingAcceleration that applies to all Mazubs.
	 */
	protected static double movingAcceleration = 0.9;

	/**
	 * Check whether the given minHorizontalVelocity is a valid minHorizontalVelocity for
	 * any Mazub.
	 *  
	 * @param  minHorizontalVelocity
	 *         The minHorizontalVelocity to check.
	 * @return True if and only if the given minimum horizontal velocity is not below 1
	 * 			in absolute value.
	 *       | result == Math.abs(minHorizontalVelocity)  >= 1
	*/
	@Override
	boolean isValidMinHorizontalVelocity(double minHorizontalVelocity) {
		return Math.abs(minHorizontalVelocity) >= 1;
	}
	/**
	 * Check whether the given verticalVelocity is a valid verticalVelocity for
	 * any Mazub.
	 *  
	 * @param  verticalVelocity
	 *         The verticalVelocity to check.
	 * @return True if and only if the given vertical velocity is not above the maximum vertical velocity.
	 *       | result == verticalVelocity <= MAX_VERTICAL_VELOCITY
	*/
	@Override
	public boolean isValidVerticalVelocity(double verticalVelocity) {
		return verticalVelocity <= MAX_VERTICAL_VELOCITY;
	}
}


	
	
