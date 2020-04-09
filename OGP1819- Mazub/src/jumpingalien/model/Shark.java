package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

import jumpingalien.util.Sprite;
/**
 * @invar  The pixelX of each Shark must be a valid pixelX for any
 *         Shark.
 *      | this.hasValidPixelX()
 * @invar  The pixelY of each Shark must be a valid pixelY for any
 *         Shark.
 *      | this.hasValidPixelY() 
 * @invar The sprites of each Shark must be valid sprites for any Shark
 * 
 * @version 3.0 
 * 
 * @author Timm Lamberechts
 * @author Maarten Volkaerts
 */
public class Shark extends GameObject{
	
	/**
	 * Initialize this new Shark with pixelX,pixelY and sprites
	 * @param pixelX
	 * 			The pixelX of this new Shark.
	 * @param pixelY
	 * 			The pixelY of this new Shark.
	 * @param sprites
	 * 			The sprites of this new Shark.
	 * @effect
	 *		| uper(pixelX, pixelY,(double) pixelX/100,(double)pixelY/100,"left",0,0,Double.POSITIVE_INFINITY,0,0,0,100,sprites[0], sprites)
	 * @effect
	 * 		| if (this.getWorld() != null)
	 * 		|		if (!(this.collidesWithGeological(pixelX, pixelY, Geological.WATER)))
	 * 		|			this.setLeftWater(true)
	 * @effect
	 * 		| if (this.getWorld() != null)
	 * 		|		if (! this.objectOnImpassableTerrain(this.getPixelX(), this.getPixelY())&&
	 * 		|			(! this.isInWater(this.getPixelX(),this.getPixelY()))) 
	 * 		|			this.setVerticalAcceleration(-10)
	 * @effect
	 * 		| if (this.getWorld() == null)
	 * 		|		if (this.getPixelY() >0)
	 * 		|			this.setVerticalAcceleration(-10)
	 */
	public Shark(int pixelX, int pixelY, Sprite[] sprites) throws RuntimeException {
		super(pixelX, pixelY,(double) pixelX/100,(double)pixelY/100,"left",0,0,Double.POSITIVE_INFINITY,0,0,0,100,sprites[0], sprites);

		if (this.getWorld() != null) {
			if (!(this.collidesWithGeological(pixelX, pixelY, Geological.WATER))) {
				this.setLeftWater(true);
			}
			if (! this.objectOnImpassableTerrain(this.getPixelX(), this.getPixelY())&&
					(! this.isInWater(this.getPixelX(),this.getPixelY()))) {
				this.setVerticalAcceleration(-10);
			}
		}
		if (this.getWorld() == null) {
			if (this.getPixelY() >0) {
				this.setVerticalAcceleration(-10);
			}
		}

	}
	/**
	 * Check whether the given sprites are valid sprites for
	 * any Shark.
	 *  
	 * @param  sprites
	 *         The sprites to check.
	 * @return  
	 *       | if (sprites == null)
	 *       |		return false
	 *       | else if (sprites.length != 3)
	 *       |		return false
	 *       | for (int index=0; index < sprites.length; index += 1) 
	 *       |	 	if (sprites[index]== null)
	 *       |			return false
	 *       | return true
	 */
	@Override
	public boolean isValidSprite(Sprite... sprites) {
		if (sprites == null)
			return false;
		else if (sprites.length != 3)
			return false;
		else
			for (int index=0; index < sprites.length; index += 1) {
				if (sprites[index]== null)
					return false;
			}
			return true;
		
	}

	@Override
	public void advanceTime(double deltaT) {
		if (this.getGameJustStarted()) {
			this.setGameJustStarted(false);
			this.startJump();
			this.setOrientation("left");
			this.setHorizontalAcceleration(-1.5);
			this.setCurrentSprite(sprites[1]);
		}
		if (this.isBlockingMazub()) {
			this.setTimeMeetingWithMazub(this.getTimeMeetingWithMazub()+deltaT);
			if (this.getTimeMeetingWithMazub() > 0.6) {
				this.setBlocksMazub(false);
				this.advanceTime(this.getTimeMeetingWithMazub()-0.6);
			}
		}
		else if (this.isMoving()) {
			if (this.getMovingTime()+deltaT < 0.5){
				int nbdtX = 1;
				int nbdtY = 1;
				double deltaTX= deltaT;
				double deltaTY = deltaT;
				this.setMovingTime(this.getMovingTime()+deltaT);
				double dt = calculateDT(deltaT);
				if (this.meetsHorizontalObstacle(this.getPixelX(),this.getPixelY())) {
					this.stopMove();
				}
				if (this.objectBumpsOnOtherObject(this.getPixelX(),this.getPixelY())) {
					this.setVerticalVelocity(0);
					this.setHorizontalAcceleration(0);
					this.setHorizontalVelocity(0);
				}
				for (double time = dt; time < deltaT;time+= dt) {
					double newPosX = this.calculatePositionX(nbdtX*dt);
					double newPosY = this.calculatePositionY(nbdtY*dt);
					nbdtX +=1;
					nbdtY += 1;
					if (this.objectBumpsOnOtherObject((int) (100*newPosX),(int) (100*newPosY))) {
						this.updatePosition((nbdtX-2)*dt, (nbdtY-2)*dt);
						this.setVerticalVelocity(0);
						this.setHorizontalAcceleration(0);
						this.setHorizontalVelocity(0);
						deltaTX -= (nbdtX-2)*dt;
						deltaTY -= (nbdtY-2)*dt;
						nbdtX=2;
						nbdtY = 2;
					}
					if (this.meetsHorizontalObstacle((int) (100 * newPosX),
							(int) (100 * newPosY))) {
						this.updatePosition(deltaTX, deltaTY);	
						this.stopMove();
						deltaTX -= (nbdtX-1) *dt;
						nbdtX = 1;
					
					}
					if ((this.objectBumpsImpassableTerrainAbove((int) (100 * newPosX),
							(int) (100 * newPosY)))&& (this.getVerticalVelocity()>0)) {
						this.setActualY(newPosY);
						this.setPixelY((int) (100 * newPosY));	
						this.endJump();
						deltaTY -= (nbdtY-1) *dt;
						nbdtY = 1;
					}
					if ((this.isInWater(this.getPixelX(),this.getPixelY()))&&
							(! this.isInWater((int) (100 * newPosX),(int) (100 * newPosY)))) {
						this.setActualY(newPosY);
						this.setPixelY((int) (100 * newPosY));	
						this.setVerticalVelocity(this.calculateVerticalVelocity((nbdtY-1) *dt));
						this.setVerticalAcceleration(-10);
						deltaTY -= (nbdtY-1) *dt;
						nbdtY = 1;
					}
					if ((! this.isInWater(this.getPixelX(),this.getPixelY()))&&
							(this.isInWater((int) (100 * newPosX),(int) (100 * newPosY)))) {
						this.setActualY(newPosY);
						this.setPixelY((int) (100 * newPosY));	
						this.endJump();
						deltaTY -= (nbdtY-1) *dt;
						nbdtY = 1;
					}
					if ((this.objectOnImpassableTerrain((int) (100 * newPosX),
							(int) (100 * newPosY)))&&(this.getVerticalVelocity()<0)){
						this.setActualY(newPosY);
						this.setPixelY((int) (100 * newPosY));		
						this.endJump();
						deltaTY -= (nbdtY-1) *dt;
						nbdtY = 1;
					}
					if ((this.isInWater((int) (100 * newPosX),
							(int) (100 * newPosY)))&&(this.getVerticalVelocity()<0)){
						this.setActualY(newPosY);
						this.setPixelY((int) (100 * newPosY));		
						this.endJump();
						deltaTY -= (nbdtY-1) *dt;
						nbdtY = 1;
					}
						
					if (this.isOutOfThisWorld(newPosX, newPosY)){
						this.terminate();
						break;
					}
					if (this.isBlockingMazub()) {
						this.setTimeMeetingWithMazub(this.getTimeMeetingWithMazub()+ dt);
					}
					this.updateHitpoints((int) (100*newPosX),
							(int) (100*newPosY), dt);
				}
				if (!this.isTerminated()) {
					double defPosX = this.calculatePositionX(deltaTX);
					double defPosY = this.calculatePositionY(deltaTY);
					this.setActualX(defPosX);
					this.setPixelX((int) (100 * defPosX));	
					this.setActualY(defPosY);
					this.setPixelY((int) (100 * defPosY));	
					if (this.meetsHorizontalObstacle(this.getPixelX(),this.getPixelY())) {	
						this.stopMove();
	
					}
					if ((this.objectBumpsImpassableTerrainAbove((int) (100 * defPosX),
							(int) (100 * defPosY))) && (this.getVerticalVelocity()>0)) {	
						this.endJump();
					}
					if ((this.objectOnImpassableTerrain((int) (100 * defPosX),
							(int) (100 * defPosY)))&&(this.getVerticalVelocity()<0)){	
						this.endJump();
					}
					
					
					if (this.isOutOfThisWorld(this.getActualX(), this.getActualY())) {
						this.terminate();
					}
					if (this.isBlockingMazub()) {
						this.setTimeMeetingWithMazub(this.getTimeMeetingWithMazub()+deltaT%dt);
					}
					this.updateHitpoints(this.getPixelX(),
							this.getPixelY(),deltaT%dt);
					
					this.setHorizontalVelocity(this.calculateHorizontalVelocity(deltaTX));
					this.setVerticalVelocity(this.calculateVerticalVelocity(deltaTY));
				}
			}
			else if (this.getMovingTime()+deltaT >= 0.5) {
				double nbOfSecondsBeforeStop = 0.5-this.getMovingTime();
				this.setMovingTime(0);
				this.advanceTime(nbOfSecondsBeforeStop);
				this.setOldOrientation(this.getOrientation());
				this.setOrientation("front");
				this.stopMove();
				this.setCurrentSprite(this.getSprites()[0]);
				this.endJump();
				this.setMovingTime(0);
				this.advanceTime(deltaT-nbOfSecondsBeforeStop);
			}
		}
		else if (! this.isMoving()) {
			if (this.getMovingTime()+deltaT < 1) {
				this.setMovingTime(this.getMovingTime()+deltaT);
				if (!this.objectOnImpassableTerrain(this.getPixelX(), this.getPixelY())&&
						(!this.isInWater(this.getPixelX(),this.getPixelY()))) {
					int nbdtY = 1;
					double deltaTY = deltaT;
					double dt = calculateDT(deltaT);
					for (double time = dt; time < deltaT;time+= dt) {

						double newPosY = this.calculatePositionY(nbdtY*dt);	
						nbdtY += 1;
						if ((this.objectBumpsImpassableTerrainAbove(this.getPixelX(),
								(int) (100 * newPosY)))&&(this.getVerticalVelocity()>0)) {
							this.setActualY(newPosY);
							this.setPixelY((int) (100 * newPosY));	
							
							this.endJump();
							deltaTY -= (nbdtY-1)*dt;
							nbdtY = 1;
						}
						if ((this.objectOnImpassableTerrain(this.getPixelX(),
								(int) (100 * newPosY)))&&(this.getVerticalVelocity()<0)){
							this.setActualY(newPosY);
							this.setPixelY((int) (100 * newPosY));		
							this.endJump();
							deltaTY -= (nbdtY-1)*dt;
							nbdtY = 1;
						}
						if ((! this.isInWater(this.getPixelX(),this.getPixelY()))&&
								(this.isInWater(this.getPixelX(),(int) (100 * newPosY)))) {
							this.setActualY(newPosY);
							this.setPixelY((int) (100 * newPosY));		
							this.endJump();
							deltaTY -= (nbdtY-1) *dt;
							nbdtY = 1;
						}
						if (this.isOutOfThisWorld(this.getActualX(), newPosY)){
							this.terminate();
							break;
						}
						this.updateHitpoints(this.getPixelX(), this.getPixelY(), dt);
					}
					if (!this.isTerminated()) {
						double newPosY = this.calculatePositionY(deltaTY);
						this.setActualY(newPosY);
						this.setPixelY((int) (100 * newPosY));	
		
						if (this.objectBumpsImpassableTerrainAbove(this.getPixelX(),
								(int) (100 * newPosY))) {	
							this.endJump();
						}
						if ((this.objectOnImpassableTerrain(this.getPixelY(),
								(int) (100 * newPosY)))&&(this.getVerticalVelocity()<0)){	
							this.endJump();
						}
						if (this.isOutOfThisWorld(this.getActualX(),this.getActualY())){
							this.terminate();
						}
						this.updateHitpoints(this.getPixelX(),
								this.getPixelY(),deltaT%dt);
						this.setVerticalVelocity(this.calculateVerticalVelocity(deltaTY));		
						}		
		}
			}
			else if (this.getMovingTime()+deltaT >= 1) {
				double nbOfSecondsBeforeStart = 1-this.getMovingTime();
				this.setMovingTime(0);
				this.advanceTime(nbOfSecondsBeforeStart);
				this.startJump();
				if (this.getOldOrientation() == "left") {
					this.setOrientation("right");
					this.setHorizontalAcceleration(1.5);
					this.setCurrentSprite(sprites[2]);
				}
				else if (this.getOldOrientation() == "right") {
					this.setOrientation("left");
					this.setHorizontalAcceleration(-1.5);
					this.setCurrentSprite(sprites[1]);
				}
				this.setMovingTime(0);
				this.advanceTime(deltaT-nbOfSecondsBeforeStart);
		}
		}
			
		
	}
	/**
	 * A method that makes the Shark start Jumping
	 * @effect
	 * 		| if ((this.collidesWithGeological(this.getPixelX(), this.getPixelY(), Geological.WATER))||
	 * 		|		(this.objectOnImpassableTerrain(this.getPixelX(), this.getPixelY())))
	 * 		|	this.setVerticalVelocity(2.0)
	 * 		|	if (!this.isInWater(this.getPixelX(),this.getPixelY())) 
	 * 		|		this.setVerticalAcceleration(-10)
	 */
	public void startJump(){
		if ((this.collidesWithGeological(this.getPixelX(), this.getPixelY(), Geological.WATER))||
				(this.objectOnImpassableTerrain(this.getPixelX(), this.getPixelY()))){
			this.setVerticalVelocity(2.0);
			if (!this.isInWater(this.getPixelX(),this.getPixelY())) {
				this.setVerticalAcceleration(-10);

			}
		}
	}	
	/**
	 * A method that stops the Shark from jumping.
	 * @effect
	 * 		| if (this.objectOnImpassableTerrain(this.getPixelX(), this.getPixelY())||
	 * 		|		(this.isInWater(this.getPixelX(),this.getPixelY()))) 
	 * 		|	this.setVerticalAcceleration(0)
	 * 		|	this.setVerticalVelocity(0)
	 * 		| else if (this.getVerticalVelocity() > 0)
	 * 		|	this.setVerticalVelocity(0)
	 */
	public void endJump() {
		if (this.objectOnImpassableTerrain(this.getPixelX(), this.getPixelY())||
				(this.isInWater(this.getPixelX(),this.getPixelY()))) {
			this.setVerticalAcceleration(0);
			this.setVerticalVelocity(0);
		}
		else if (this.getVerticalVelocity() > 0)
			this.setVerticalVelocity(0);
		
	}
	/**
	 * A method that stops the Shark from moving.
	 * @effect
	 * 		| this.setHorizontalAcceleration(0)
	 * 		| this.setHorizontalVelocity(0)
	 */
	public void stopMove() {
		this.setHorizontalAcceleration(0);
		this.setHorizontalVelocity(0);
	}
	/**
	 * A method that checks whether the Shark is still moving.
	 * @return (this.getHorizontalAcceleration()!=0)
	 */
	public boolean isMoving() {
		return (this.getHorizontalAcceleration()!=0);
	}
	
	/**
	 * A method checking whether a Shark is in water.
	 * @param posX
	 * 		  The pixelX of the Shark
	 * @param posY
	 * 		  The pixelY of the Shark
	 * @return
	 * 		  |	for (index=0;index <=this.getCurrentSprite().getWidth();
	 *		  |	index ++) 
	 *		  |   if (this.getWorld().getGeologicalFeature(posX+index, posY) == Geological.WATER)
	 *		  |		return true
	 */
	public boolean isInWater(int posX, int posY) {
		posY += this.getCurrentSprite().getHeight();
		
		int index;
		for (index=0;index <=this.getCurrentSprite().getWidth();
				index ++) {
			if (this.getWorld().getGeologicalFeature(posX+index, posY) == Geological.WATER)
				return true;
			}
			return false;
	}	
	/**
	 * A method updating the hitpoints of the Shark after interactions with other GameObjects
	 * @param x
	 * 		  the pixelX of the Shark
	 * @param y	
	 * 		  the pixelY of the Shark
	 * @effect 
	 * 	   	  | for (GameObject otherGameObject: this.getWorld().getGameObjects()) 
	 *		  |		if (this.collidesWithOtherGameObect(x, y, otherGameObject)) 
	 *		  | 		if ((otherGameObject instanceof Slime)&& (! this.isDead()) &&
	 *		  |				(! otherGameObject.isDead())) 
	 *		  |					if (! this.getCollidingWithSlime()) 
	 *        |						this.setHitPoints(this.getHitPoints()+10)
	 *        |						this.setCollidingWithSlime(true)
	 *		  |			if ((otherGameObject instanceof Mazub)&& (! this.isDead()) &&
	 *		  |				(! otherGameObject.isDead())) 
	 *		  |				if (! this.getListMazubs().contains(otherGameObject)) 
	 *		  |					this.setHitPoints(this.getHitPoints() - 50)
	 *		  |					this.addHittenMazub((Mazub) otherGameObject)
	 *		  |				else 
	 *		  |					this.setBlocksMazub(true)
	 */
	public void updateHitpointsAfterInteractionsWithGameObjects(int x, int y) {
		int xP = this.getPixelWidth();
		int yP = this.getPixelHeight();
		for (GameObject otherGameObject: this.getWorld().getGameObjects()) {
			if (this.collidesWithOtherGameObect(x, y, otherGameObject)) {
				if ((otherGameObject instanceof Slime)&& (! this.isDead()) &&
								(! otherGameObject.isDead())) {
							if (! this.getCollidingWithSlime()) {
								this.setHitPoints(this.getHitPoints()+10);
								this.setCollidingWithSlime(true) ;
							}
						}
				if ((otherGameObject instanceof Mazub)&& (! this.isDead()) &&
								(! otherGameObject.isDead())) {
							if (! this.getListMazubs().contains(otherGameObject)) {
								this.setHitPoints(this.getHitPoints() - 50);
								this.addHittenMazub((Mazub) otherGameObject);
							}
							else {
								this.setBlocksMazub(true);
							}							
								
							}
						}
			}
	}
	/**
	 * A method that updates the hitpoints of the Shark after he loses contact with Water.
	 * @param posX
	 * 		  the pixelX of the Shark
	 * @param posY
	 * 		  the pixelY of the Shark
	 * @param deltaT
	 * 		  the time period after which the hitpoints have to be updated
	 * @effect
	 * 		 | if (this.collidesWithGeological(posX,posY, Geological.WATER)==false) 
	 *		 |		if (this.getLeftWater() == true) 
	 *		 |			this.setTimeOutWater(this.getTimeOutWater()+deltaT)
	 *		 |		else
	 *		 |			this.setLeftWater(true)
	 *		 |	if (this.collidesWithGeological(posX,posY, Geological.WATER)==true) 
	 *		 |		this.setTimeOutWater(0)
	 *		 |		this.setLeftWater(false)
	 *		 |	if (this.getTimeOutWater() >= 0.2) 
	 *		 |		this.setHitPoints(this.getHitPoints()-((int)(this.getTimeOutWater()/0.2))*6)
	 *		 |		this.setTimeOutWater((this.getTimeOutWater()%0.2))
	 */
	public void updateHitpointsSharkNotInWater(int posX, int posY, double deltaT) {
		if (this.collidesWithGeological(posX,posY, Geological.WATER)==false) {
			if (this.getLeftWater() == true) {
				this.setTimeOutWater(this.getTimeOutWater()+deltaT);
			}
			else
				this.setLeftWater(true);
		}
		if (this.collidesWithGeological(posX,posY, Geological.WATER)==true) {
			this.setTimeOutWater(0);
			this.setLeftWater(false);
		}
		if (this.getTimeOutWater() >= 0.2) {
			this.setHitPoints(this.getHitPoints()-((int)(this.getTimeOutWater()/0.2))*6);
			this.setTimeOutWater((this.getTimeOutWater()%0.2));
		}
	}
	
	/**
	 * A method that that updates the hitpoints after the Shark has lost contact with water
	 * and after his interactions with other Gameobjects
	 * @param x
	 * 		  the pixelX of the Shark
	 * @param y
	 * 		  the pixelX of the Shark
	 * @param deltaT
	 * 		  the time period after which the hitpoints have to be updated
	 * @effect
	 * 		 | this.updateHitpointsSharkNotInWater(x, y, deltaT)
	 * 		 | this.updateHitpointsAfterInteractionsWithGameObjects(x, y)
	 */
	public void updateHitpoints(int x,int y, double deltaT) {
		this.updateHitpointsSharkNotInWater(x, y, deltaT);
		this.updateHitpointsAfterInteractionsWithGameObjects(x, y);
	}

	/**
	 * Set the blocksMazub that applies to all Sharks to the given blocksMazub.
	 * 
	 * @param  blocksMazub
	 *         The blocksMazub for the Sharks.
	 * @post   
	 *       | new.blocksMazub = blocksMazub
	 */
	private void setBlocksMazub(boolean blocksMazub) {
		this.blocksMazub = blocksMazub; 
	}
	
	/**
	 * Return the blocksMazub of this Shark.
	 */
	private boolean isBlockingMazub() {
		return this.blocksMazub;
	}
	
	/**
	 * A variable that checks whether the Shark is blocked by a Shark.
	 */
	private boolean blocksMazub = false;

	/**
	 * Return the oldOrientation of this Shark.
	 */
	private String getOldOrientation() {
		return this.oldOrientation;
	}
	
	/**
	 * Set the oldOrientation that applies to all Sharks to the given oldOrientation.
	 * 
	 * @param  oldOrientation
	 *         The oldOrientation for the Sharks.
	 * @post   
	 *       | new.oldOrientation = oldOrientation
	 */
	private void setOldOrientation(String oldOrientation){
		this.oldOrientation = oldOrientation; 
	}
	
	/**
	 * A variable that gives the orientation of the last movement of the shark.
	 */
	private String oldOrientation = "left";
	
	/**
	 * A method that adds a given Mazub to the list of Mazubs that the Shark
	 * has hitted.
	 */
	public void addHittenMazub(Mazub mazub) {
		hittenMazubs.add(mazub);
	}
	
	/**
	 * A method that returns the list of Mazubs that the Shark
	 * has hitted.
	 */
	public List <Mazub> getListMazubs(){
		return this.hittenMazubs;
	}
	/**
	 * A list that keeps track of the Mazubs that the Shark has hitted.
	 */
	protected List <Mazub> hittenMazubs = new ArrayList<>();
				
	/**
	 * Set the timeOutWater that applies to all Sharks to the given timeOutWater.
	 * 
	 * @param  timeOutWater
	 * @post 
	 *       | new.timeOutWater = timeOutWater
	 */
	public void setTimeOutWater(double timeOutWater) {
		this.timeOutWater = timeOutWater;
	}
	
	/**
	 * Return the timeOutWater of this Shark.
	 */
	public double getTimeOutWater() {
		return timeOutWater;
	}
	
	/**
	 * A variable registering the time the Shark has spend out of the water.
	 */
	private double timeOutWater=0;
	
	
	/**
	 * Set the movingTime that applies to all Sharks to the given movingTime.
	 * 
	 * @param  movingTime
	 *         The movingTime for the Sharks.
	 * @post   
	 *       | new.movingTime = movingTime
	 */
	public void setMovingTime(double movingTime) {
		this.movingTime = movingTime;
	}
	/**
	 * Return the movingTime of this Shark.
	 */
	public double getMovingTime() {
		return this.movingTime;
	}
	
	/**
	 * A variable keeps track of the time since the Shark stopped or started moving.
	 */
	public double movingTime=0;
	
	/**
	 * Set the isJumping that applies to all Sharks to the given isJumping.
	 * 
	 * @param  isJumping
	 *         The isJumping for the Sharks.
	 * @post   
	 *       | new.isJumping = isJumping
	 */
	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	/**
	 * Return the isJumping of this Shark.
	 */
	public boolean isJuming() {
		return this.isJumping;
	}
	
	/**
	 * A boolean that gives whether the Shark is jumping.
	 */
	public boolean isJumping=true;
	
	/**
	 * Return the gameJustStarted of this Shark.
	 */
	public boolean getGameJustStarted(){
		return this.gameJustStarted;
	}
	
	/**
	 * Set the gameJustStarted that applies to all Sharks to the given gameJustStarted.
	 * @param  gameJustStarted
	 *         The gameJustStarted for the Sharks.
	 * @post   
	 *       | new.gameJustStarted = gameJustStarted
	 */
	public void setGameJustStarted(boolean gameJustStarted) {
		this.gameJustStarted = gameJustStarted;
	}
	/**
	 * A boolean that is true until the first invocation of advanceTime.
	 */
	public boolean gameJustStarted = true;
	@Override
	boolean isValidHorizontalAcceleration(double horizontalAcceleration) {
		return ((horizontalAcceleration==0)||
				(Math.abs(horizontalAcceleration) == 1.5));
	}
	
	/**
	 * Return the hasLeftWater of this Shark.
	 */
	private boolean getLeftWater() {
		return this.hasLeftWater;
	}
	
	/**
	 * Set the hasLeftWater that applies to all Sharks to the given hasLeftWater.
	 * 
	 * @param  hasLeftWater
	 *         The hasLeftWater for the Shark.
	 * @post   
	 *       | new.hasLeftWater = hasLeftWater
	 */
	public void setLeftWater(boolean hasLeftWater) {
		this.hasLeftWater = hasLeftWater;
	}
	
	/**
	 * A boolean that gives whether the Shark has left the Water.
	 */
	public boolean hasLeftWater = false;
	
	/**
	 * Return the timeMeetingWithMazub of this Shark.
	 */
	public double getTimeMeetingWithMazub() {
		return this.timeMeetingWithMazub;
	}
	
	/**
	 * Set the timeMeetingWithMazub that applies to all Sharks to the given timeMeetingWithMazub.
	 * 
	 * @param  timeMeetingWithMazub
	 *         The timeMeetingWithMazub for the Shark.
	 * @post   
	 *       | new.timeMeetingWithMazub = timeMeetingWithMazub
	 */
	public void setTimeMeetingWithMazub(double timeMeetingWithMazub) {
		this.timeMeetingWithMazub = timeMeetingWithMazub;
		
	}
	/**
	 * A variable that keeps track of the duration of the time since the last collision
	 * between a Shark and a Mazub.
	 */
	public double timeMeetingWithMazub = 0;
	
	/**
	 * Return the collidingWithSlime of this Shark.
	 */
	public boolean getCollidingWithSlime() {
		return this.collidingWithSlime;
	}
	
	/**
	 * Set the collidingWithSlime that applies to all Sharks to the given collidingWithSlime.
	 * 
	 * @param  collidingWithSlime
	 *         The collidingWithSlime for the Sharks.
	 * @post   
	 *       | new.collidingWithSlime = collidingWithSlime
	 */
	public void setCollidingWithSlime(boolean collidingWithSlime) {
		this.collidingWithSlime = collidingWithSlime;
	}
	
	/**
	 * A variable that gives whether the Shark is colliding with a Slime.
	 */
	public boolean collidingWithSlime=false;

	/**
	 * Check whether the given horizontalVelocity is a valid horizontalVelocity for
	 * any Shark.
	 *  
	 * @param  horizontalVelocity
	 *         The horizontalVelocity to check.
	 * @return 
	 * 		  | result == true 	
	 */
	@Override
	public boolean isValidHorizontalVelocity(double horizontalVelocity) {
		return true;
	}
	
	/**
	 * Check whether the given minHorizontalVelocity is a valid minHorizontalVelocity for
	 * any Shark.
	 *  
	 * @param  minHorizontalVelocity
	 *         The minHorizontalVelocity to check.
	 * @return True 
	 *       | result == true
	*/
	@Override
	boolean isValidMinHorizontalVelocity(double minHorizontalVelocity) {
		return true;
	}
	
	/**
	 * Check whether the given verticalVelocity is a valid verticalVelocity for
	 * any Shark.
	 *  
	 * @param  verticalVelocity
	 *         The verticalVelocity to check.
	 * @return True 
	 *       | result == true
	*/
	@Override
	public boolean isValidVerticalVelocity(double verticalVelocity) {
		return true;
	}
}


