package jumpingalien.model;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;
/**
 * A class of Slimes extending the class GameObjects that are part of a school.
 * 
 * @invar The ID of each Slime must be a valid ID for any Slime.
 * 		| this.isValidID(ID)
 * @invar  The pixelX of each Slime must be a valid pixelX for any
 *         Slime.
 *      | this.hasValidPixelX()
 * @invar  The pixelY of each Slime must be a valid pixelY for any
 *         Slime.
 *      | this.hasValidPixelY() 
 * @invar The school of each Slime must be a valid school for any Slime.
 * 		| this.isValidSchool(school)
 * @invar The sprites of each Slime must be valid sprites for any Slime.
 * 
 * @version 3.0 
 * 
 * @author Timm Lamberechts
 * @author Maarten Volkaerts
 *
 */
public class Slime extends GameObject implements Comparable<Slime>{

	/**
	 * Initialize this new Slime with given ID,pixelX,pixelY,school and sprites
	 * @param ID
	 * 			The  ID of this new Slime.
	 * @param pixelX
	 * 			The pixelX of this new Slime.
	 * @param pixelY
	 * 			The pixelY of this new Slime.
	 * @param school
	 * 			The school of this new Slime.
	 * @param sprites
	 * 			The sprites of this new Slime.
	 * @effect
	 *		|super(pixelX, pixelY,(double) pixelX/100,(double)pixelY/100,"right",0,
			|	0,2.5,0,0.7,0,100,sprites[0],sprites)
	 * @effect
	 * 		| this.ID =ID
	 * @effect
	 * 		| this.setSchool(school)
	 * @effect
	 *  | if (this.getWorld() != null)  then
	 *	|	if (this.collidesWithGeological(pixelX, pixelY, Geological.WATER)) 
	 *	|		then this.setInWater(true)
	 *	|	if (this.collidesWithGeological(pixelX, pixelY, Geological.GAS)) then
	 *	|		this.setInGas(true)
	 * @throws RuntimeException
	 * 			| if IDs.contains(ID)
	 */
	public Slime(long ID,int pixelX, int pixelY,School<Slime> school, Sprite[] sprites) throws RuntimeException {
		super(pixelX, pixelY,(double) pixelX/100,(double)pixelY/100,"right",0,
				0,2.5,0,0.7,0,100,sprites[0],sprites);
		this.ID =ID;
		this.setSchool(school);
		if (this.getWorld() != null) {
			if (this.collidesWithGeological(pixelX, pixelY, Geological.WATER)) {
				this.setInWater(true);
			}
			if (this.collidesWithGeological(pixelX, pixelY, Geological.GAS)) {
				this.setInGas(true);
			}
		}
		if (IDs.contains(ID))
			throw new RuntimeException();
		IDs.add(ID);
		
	}
	
	/**
	 * Check whether the given sprites is a valid sprites for
	 * any Slime.
	 *  
	 * @param  sprites
	 *         The sprites to check.
	 * @return 
	 *       | result == (sprites.length == 2)
	*/
	@Override
	boolean isValidSprite(Sprite... sprites) {
		if (sprites.length == 2)
			return true;
		else
			return false;
	}
	
	@Override
	public void advanceTime(double deltaT) throws IllegalArgumentException {
		if (deltaT < 0)
			throw new IllegalArgumentException();
		if (deltaT > 0.2) 
			throw new IllegalArgumentException();
		if (this.hasCollidedRecently()){
			if (! this.meetsMazub(this.getPixelX(),this.getPixelY()) ) {
				if (this.getOrientation()=="right")
					this.setHorizontalAcceleration(0.7);
				if (this.getOrientation()=="left") {
					this.setHorizontalAcceleration(-0.7);
				}

			}
			}
		if (! this.isDead()) {
			double dt = calculateDT(deltaT);
			int nbdtX = 1;
			int nbdtY = 1; 
			this.updateHitPoints(this.getPixelX(),this.getPixelY(),0);
			double deltaTX = deltaT;
			for (double time = dt; time < deltaT;time+= dt) {
				double newPosX = this.calculatePositionX(nbdtX*dt);
				double newPosY = this.calculatePositionY(nbdtY*dt);
				nbdtX += 1;
				nbdtY += 1;
				if (this.objectBumpsImpassableTerrainRight((int) (100*newPosX),(int) (100*newPosY))) {
					this.setActualX(newPosX);
					this.setPixelX((int) (100 * newPosX));	
					deltaTX -= (nbdtX-1) *dt;
					nbdtX = 1;
					this.setHorizontalAcceleration(0);
					this.setHorizontalVelocity(0);
				}
				if (this.objectBumpsImpassableTerrainLeft((int) (100*newPosX),(int) (100*newPosY))) {
					this.setActualX(newPosX);
					this.setPixelX((int) (100 * newPosX));	
					deltaTX -= (nbdtX-1) *dt;
					nbdtX = 1;
					this.setHorizontalAcceleration(0);
					this.setHorizontalVelocity(0);
					
				}
				if (this.hasCollidedRecently()) {
					this.timeSlimeBlocksMazub += dt;
					if (timeSlimeBlocksMazub >= 0.6) {
						this.timeSlimeBlocksMazub = (this.timeSlimeBlocksMazub%0.6);
						this.setCollidedRecently(false);
					}
						
				}
				if (this.meetsMazub((int)(100*newPosX), (int) (100*newPosY))) {
					this.updatePosition((nbdtX-2)*dt, (nbdtY-2)*dt);
					deltaTX -= (nbdtX-2)*dt;
					nbdtX = 2;
					this.setHorizontalVelocity(0);
					this.setHorizontalAcceleration(0);
					if (! this.hasCollidedRecently()) {
						this.setHitPoints(this.getHitPoints()-30);
						this.updateHitpointsSchool();
						this.setCollidedRecently(true);
					}
					}
				
					
				if ((( !this.objectBumpsImpassableTerrainRight((int) (100*newPosX),(int) (100*newPosY))) &&
						(! this.objectBumpsImpassableTerrainLeft((int) (100*newPosX),(int) (100*newPosY)))) && 
						((this.meetsOtherSlime((int)(100*newPosX), (int) (100*newPosY))))
					) {
						this.updatePosition((nbdtX-2)*dt, (nbdtY-2)*dt);
						deltaTX -= (nbdtX-2)*dt;	
						nbdtX = 2;
	
					if (this.getOrientation() == "right") {
						this.setOrientation("left");
						this.setCurrentSprite(this.getSprites()[1]);
						this.setHorizontalVelocity(0);
						this.setHorizontalAcceleration(-0.7);
					}
					else if (this.getOrientation() == "left") {
						this.setOrientation("right");
						this.setCurrentSprite(this.getSprites()[0]);
						this.setHorizontalVelocity(0);
						this.setHorizontalAcceleration(0.7);
					}
	
				}
				if (this.meetsShark((int)(100*newPosX), (int) (100*newPosY))) {
					this.kill();
					this.terminate();
				}
				if ((newPosX < 0) || (newPosX >this.getWorld().getWorldWidth())){
					this.terminate();
				}
				
				this.updateHitPoints((int) (100*newPosX),(int) (100*newPosY), dt);	
			}
			double newPosX = this.calculatePositionX(deltaTX);
			double newPosY = this.calculatePositionY(deltaT);
			if (this.hasCollidedRecently()) {
				this.timeSlimeBlocksMazub += (deltaTX%dt);
				if (timeSlimeBlocksMazub >=0.6) {
					this.timeSlimeBlocksMazub = (this.timeSlimeBlocksMazub%0.6);
					this.setCollidedRecently(false);
				}
			}
		
			if (this.meetsOtherSlime((int) (100*newPosX),(int) (100*newPosY))){
				this.updatePosition(deltaTX-deltaT%dt, deltaT);
				this.setHorizontalVelocity(0);
				if (this.getOrientation() == "right") {
					this.setOrientation("left");
					this.setCurrentSprite(this.getSprites()[1]);
					this.setHorizontalVelocity(0);
					this.setHorizontalAcceleration(-0.7);
				}
				else if (this.getOrientation() == "left") {
					this.setOrientation("right");
					this.setCurrentSprite(this.getSprites()[0]);
					this.setHorizontalVelocity(0);
					this.setHorizontalAcceleration(0.7);
				}
			}
			else if (this.meetsShark((int) (100*newPosX),(int) (100*newPosY))){
				this.kill();
				this.terminate();
			}
			else if (this.meetsMazub((int) (100*newPosX),(int) (100*newPosY))){
				this.updatePosition(deltaTX-deltaT%dt, deltaT);
				this.setHorizontalVelocity(0);
				this.setHorizontalAcceleration(0);
				if (! this.hasCollidedRecently()) {
					this.setHitPoints(this.getHitPoints()-30);
					this.updateHitpointsSchool();
					this.setCollidedRecently(true);
				}
			}
			else {
				this.updatePosition(deltaTX,deltaT);
			}
			
			this.updateHitPoints(this.getPixelX(),this.getPixelY(), deltaT%dt);
			if ((this.getPixelX() < 0) || (this.getPixelX()>
			this.getWorld().getWorldWidth())){
				this.terminate();
			}

				this.setHorizontalVelocity(this.calculateHorizontalVelocity(deltaTX));
				this.setVerticalVelocity(this.calculateVerticalVelocity(deltaTX));
		}
		else {
			this.setDelay(this.getDelay()+deltaT);
			if (this.getDelay()>0.6) {
				this.terminate();
			}
		}
	}
	
	/**
	 * A method to compare Slimes with each other.
	 * @return
	 * 		| Long.compare(this.getID(), o.getID())
	 */
	@Override
	public int compareTo(Slime o) {
		return Long.compare(this.getID(), o.getID());
	}
	
	/**
	 * Set the school of this Slime to the given school.
	 * @param school
	 * 		The new school for this Slime.
	 * @post
	 * 		| if (school != null) then
	 *		| school.addSlime(this)
	 *@post
	 *		| if (school == null) then 
	 *		| this.school = null
	 */
	public void setSchool(School<Slime> school)  {
		if (school != null) {
			school.addSlime(this);
		}
		if (school == null)
			this.school = null;
	}
	
	/**
	 * Return the school of this slime.
	 */
	@Basic @Raw
	public School<Slime> getSchool() {
		return this.school;
	}
	
	/**
	 * Variable registering the school of this Slime.
	 */
	public School<Slime> school;
	
	/**
	 * Return the ID of this Slime.
	 */
	@Basic @Raw
	public long getID(){
		return this.ID;
	}
	
	/**
	 * Variable registering the ID of this Slime.
	 */
	public final long ID;
	
	/**
	 * Method to switch this slime from his school to the given school.
	 * @param newSchool
	 * 		The new school of this slime.
	 * @effect
	 * 		| this.getSchool().removeSlime(this)
	 * @effect 
	 * 		| for (Slime oldSchoolSlime:oldSchool.getAllSlimes()) do
	 *		| if (oldSchoolSlime != this)  then
	 *		|	 this.setHitPoints(this.getHitPoints()-1) and 
	 *		|	oldSchoolSlime.setHitPoints(oldSchoolSlime.getHitPoints()+1)
	 * @effect
	 * 		| this.setSchool(newSchool)
	 * @effect
	 * 		| for (Slime newSchoolSlime:this.getSchool().getAllSlimes()) do
			| if (newSchoolSlime != this) then
			|	this.setHitPoints(this.getHitPoints()+1) and 
			|	newSchoolSlime.setHitPoints(newSchoolSlime.getHitPoints()-1)
	 * @throws RuntimeException
	 * 		| this.isTerminated()
	 * @throws RuntimeException
	 * 		| this.getSchool() == null
	 */
	public void switchSchool(School<Slime> newSchool) throws RuntimeException {
		if (this.isTerminated())
			throw new RuntimeException();
		if (this.getSchool() == null)
			throw new RuntimeException();
		School<Slime> oldSchool = this.getSchool();
		this.getSchool().removeSlime(this);
		for (Slime oldSchoolSlime:oldSchool.getAllSlimes())
			if (oldSchoolSlime != this) {
				this.setHitPoints(this.getHitPoints()-1);
				oldSchoolSlime.setHitPoints(oldSchoolSlime.getHitPoints()+1);
			}
		this.setSchool(newSchool);
		for (Slime newSchoolSlime:this.getSchool().getAllSlimes())
			if (newSchoolSlime != this) {
				this.setHitPoints(this.getHitPoints()+1);
				newSchoolSlime.setHitPoints(newSchoolSlime.getHitPoints()-1);
			}
			
	}
	
	/**
	 * Check whether the given horizontal velocity is a valid horizontal velocity for any Slime.
	 * @return
	 * 		| result == ((horizontalAcceleration == 0)|| Math.abs((horizontalAcceleration)) == 0.7)
	 */
	@Override
	boolean isValidHorizontalAcceleration(double horizontalAcceleration) {
		return ((horizontalAcceleration == 0)|| Math.abs((horizontalAcceleration)) == 0.7);
	}
	
	/**
	 * Updates the hitpoints of a Slime when time has passed and/or his position has changed.
	 * @param newPosX
	 * 		  The pixelX of the Slime.
	 * @param newPosY
	 * 		  The pixelY of the Slime.
	 * @param deltaT
	 * 		  The time that has passed.
	 * @effect 
	 * 		 | if (! this.collidesWithGeological(newPosX,newPosY, Geological.WATER)) then
	 *		 | this.setTimeInWater(0) and
	 *		 | this.setInWater(false)
	 * @effect 
	 *       | if (this.collidesWithGeological(newPosX,newPosY, Geological.WATER)) then
	 *		 | if (this.isInWater()) then
	 *	     |	this.setTimeInWater(this.getTimeInWater() + deltaT)
	 *		 |	if (this.getTimeInWater() >0.4) then
	 *		 |		this.setHitPoints(this.getHitPoints()- ((int) (this.getTimeInWater()/0.4))*4) and
	 *		 |		this.updateHitpointsSchool() and
	 *		 |		this.setTimeInWater(this.getTimeInWater()%0.4)
	 *		 | else then this.setInWater(false)
	 * @effect
	 *		 | if (! this.collidesWithGeological(newPosX,newPosY, Geological.GAS)) then
	 *	     | this.setTimeInGas(0) and
	 *	     | this.setInGas(false)
	 * @effect 
	 *       |if (this.collidesWithGeological(newPosX,newPosY, Geological.MAGMA)) then
	 *		 |	this.kill() 
	 * @effect
	 * 		 | if (this.collidesWithGeological(newPosX,newPosY, Geological.GAS)) then
	 *		 |   if (this.isInGas()) then
	 *		 |		this.setTimeInGas(this.getTimeInGas() + deltaT)
	 *		 |	if (this.getTimeInGas() > 0.3) then
	 *		 |		this.setHitPoints(this.getHitPoints()+ ((int) (this.getTimeInGas()/0.3))*2) and
	 *		 |		this.setTimeInGas(this.getTimeInGas()%0.3)
	 *		 | else then this.setInGas(true)
     * @effect
     * 		 | if (this.getHitPoints() <=0) then
	 * 		 |		this.kill() and
	 *		 |		this.setDelay(this.getDelay()+deltaT)
	 *		 |		if (this.getDelay()>0.6) then
	 *		 |			this.terminate()
	 */
	public void updateHitPoints(int newPosX, int newPosY, double deltaT) {
		if (! this.collidesWithGeological(newPosX,newPosY, Geological.WATER)) {
			this.setTimeInWater(0);
			this.setInWater(false);
		}
		if (this.collidesWithGeological(newPosX,newPosY, Geological.WATER)){
			if (this.isInWater()) {
				this.setTimeInWater(this.getTimeInWater() + deltaT);
				if (this.getTimeInWater() >0.4) {
					this.setHitPoints(this.getHitPoints()- ((int) (this.getTimeInWater()/0.4))*4);
					this.updateHitpointsSchool();
					this.setTimeInWater(this.getTimeInWater()%0.4);
					}
			}
			else
				this.setInWater(true);
		}
		if (! this.collidesWithGeological(newPosX,newPosY, Geological.GAS)) {
			this.setTimeInGas(0);
			this.setInGas(false);
		}
		if (this.collidesWithGeological(newPosX,newPosY, Geological.MAGMA)) {
			this.kill();
		}
		if (this.collidesWithGeological(newPosX,newPosY, Geological.GAS)) {
			if (this.isInGas()) {
				this.setTimeInGas(this.getTimeInGas() + deltaT);
				if (this.getTimeInGas() > 0.3) {
					this.setHitPoints(this.getHitPoints()+ ((int) (this.getTimeInGas()/0.3))*2);
					this.setTimeInGas(this.getTimeInGas()%0.3);
				}
			}
			else
				this.setInGas(true);	
			}
		if (this.getHitPoints() <=0) {
			this.kill();
			this.setDelay(this.getDelay()+deltaT);
			if (this.getDelay()>0.6) {
				this.terminate();
				}
		}
	}
	
	/**
	 * Update the hitpoints of the other slimes in the school of this slime.
	 * @effect 
	 * 		| if (this.getSchool() != null) then
	 *		| 	for (Slime otherslime: this.getSchool().getAllSlimes()) do
	 *		|		if (otherslime != this) then
	 *		|			otherslime.setHitPoints(otherslime.getHitPoints()-1)
	 */
	public void updateHitpointsSchool() {
		if (this.getSchool() != null) {
			for (Slime otherslime: this.getSchool().getAllSlimes()) {
				if (otherslime != this)
					otherslime.setHitPoints(otherslime.getHitPoints()-1);
			}
		}
	}
	

	/**
	 * ArrayList storing all the IDs of the Slimes of this class.
	 */
	public static ArrayList<Long> IDs = new ArrayList<>();
	
	/**
	 * Clear the list that is storing the IDs of all Slimes of this class.
	 * @effect 
	 * 		| IDs.clear()
	 */
	public static void clearSlimeIDs() {
		IDs.clear();
	}
	
	/**
	 * Checks whether this Slime overlaps with the playableMazub.
	 * @param newPosX
	 * 		The pixelX of this Slime.
	 * @param newPosY
	 * 		The pixelY of this Slime.
	 * @return
	 * 		| if ((this.getWorld() != null) && (this.getWorld().getPlayableMazub() != null)) then
	 *		|	if (this.collidesWithOtherGameObect(newPosX, newPosY, this.getWorld().getPlayableMazub()))
	 *		|		then result == true
	 */
	public boolean meetsMazub(int newPosX,int newPosY) {
			
			if ((this.getWorld() != null) && (this.getWorld().getPlayableMazub() != null))
				if (this.collidesWithOtherGameObect(newPosX, newPosY, this.getWorld().getPlayableMazub()))
					return true;
			return false;
		}
	
	/**
	 * Checks whether this Slime overlaps an other Slime.
	 * @param newPosX
	 * 		The pixelX of this Slime.
	 * @param newPosY
	 * 		The pixelY of this Slime.
	 * @effect
	 * 		|for (GameObject otherGameObjects:this.getWorld().getGameObjects()) do
	 *		|	if ((otherGameObjects instanceof Slime) && (otherGameObjects != this)) then
	 *		|		if (this.collidesWithOtherGameObect(newPosX,newPosY,otherGameObjects)) then
	 *		|			if ((this.getSchool() !=null) and (((Slime) otherGameObjects).getSchool() != null)) then
	 *		|				if (this.getSchool().getAllSlimes().size() < 
	 *		|							((Slime) otherGameObjects).getSchool().getAllSlimes().size()) then
	 *		|					this.switchSchool(((Slime) otherGameObjects).getSchool())
	 *@return 
	 *		| if Slime has switched from school
	 *		| then result == true
	 */
	public boolean meetsOtherSlime(int newPosX,int newPosY) {
		for (GameObject otherGameObjects:this.getWorld().getGameObjects()) {
			if ((otherGameObjects instanceof Slime) && (otherGameObjects != this)) {
				if (this.collidesWithOtherGameObect(newPosX,newPosY,otherGameObjects)) {
					if ((this.getSchool() !=null) && (((Slime) otherGameObjects).getSchool() != null)) {
						
							if (this.getSchool().getAllSlimes().size() < 
										((Slime) otherGameObjects).getSchool().getAllSlimes().size()) {
								this.switchSchool(((Slime) otherGameObjects).getSchool());
							}				
					}
					return true;
				}		
			}
		}
		return false;
	}
	
	/**
	 * Checks whether this Slime overlaps with a Shark.
	 * @param newPosX
	 * 		The pixelX of this Slime.
	 * @param newPosY
	 * 		The pixelY of this Slime.
	 * @return
	 * 		| for (GameObject otherGameObjects:this.getWorld().getGameObjects()) do
	 * 		| if ((otherGameObjects instanceof Shark) and (! otherGameObjects.isDead())) then
	 *		|	if (this.collidesWithOtherGameObect(newPosX,newPosY,otherGameObjects)) then
	 *		|		result == true
	 */
	public boolean meetsShark(int newPosX,int newPosY) {
		
		for (GameObject otherGameObjects:this.getWorld().getGameObjects()) {
			if ((otherGameObjects instanceof Shark) && (! otherGameObjects.isDead())) {
				if (this.collidesWithOtherGameObect(newPosX,newPosY,otherGameObjects)) {
					return true;
				}
			
				}
		}
		return false;
	}
		
	/**
	 * Set the collidedRecently of this slime to the given boolean value.
	 * @param booleanValue
	 * 			The new boolean value of the CollidedRecently.
	 * @post
	 * 		| new.collidedRecently = booleanValue
	 */
	@Raw
	public void setCollidedRecently(boolean booleanValue) {
		this.collidedRecently = booleanValue;
	}
	
	/**
	 * Return if this Slime has recently collided with the playableMazub.
	 */
	@Basic @Raw
	public boolean hasCollidedRecently() {
		return this.collidedRecently;
	}
	
	/**
	 * Boolean registering whether this Slime has recently collided with the playableMazub.
	 */
	public boolean collidedRecently = false;
	
	/**
	 * Variable registering the time the Slime is blocked by the Mazub.
	 */
	private double timeSlimeBlocksMazub;

	/**
	 * Check whether the given horizontalVelocity is a valid horizontalVelocity for
	 * any Slime.
	 *  
	 * @param  horizontalVelocity
	 *         The horizontalVelocity to check.
	 * @return  	
	 * 		  | result == ( Math.abs(horizontalVelocity) < this.getMaxHorizontalVelocity())
	 */
	@Override
	public boolean isValidHorizontalVelocity(double horizontalVelocity) {
		if ( Math.abs(horizontalVelocity) >= this.getMaxHorizontalVelocity())
			return false;
		else
			return true;	
	}

	/**
	 * Check whether the given minHorizontalVelocity is a valid minHorizontalVelocity for
	 * any Slime.
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
	 * any Slime.
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