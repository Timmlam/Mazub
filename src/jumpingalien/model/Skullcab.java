package jumpingalien.model;

import jumpingalien.util.Sprite;

/**
 * A class of Skullcabs extending the class Plant
 * @version 3.0
 * 
 * @author Timm Lamberechts
 * @author Maarten Volkaerts
 *
 */
public class Skullcab extends Plant {
	
	/**
	 * Initialize this new Skullcab with given pixel position (pixelX and pixelY).
	 * @param  pixelX
	 *         The pixelX in the gaming world  for this new Skullcab.
	 * @param  pixelY
	 *         The pixelY in the gaming world  for this new Skullcab. 
	 * @param  sprites
     *         The sprites for this new Skullcab. 
     * @effect
     * 		| this.lifeTime = 12
     * @effect
     * 		| this.setHitPoints(3)
     * @effect
     * 		| this.setOrientation("up")
     * @effect
     * 		| this.setHorizontalVelocity(0.5)
	 */
	public Skullcab(int pixelX,int pixelY, Sprite... sprites) {
		super(pixelX,pixelY,sprites);
		this.lifeTime = 12;
		this.setHitPoints(3);
		this.setCurrentSprite(sprites[0]);
		this.setOrientation("up");
		this.setVerticalVelocity(0.5);
		
	}
		
	/**
	 * A method to check whether this Skullcab overlaps the playable mazub of its world and if so to change
	 * the hitpoints of this Skullcab.
	 * @effect
	 * 		| if (this.collidesWith(xMazub,yMazub,xPMazub,yPMazub)) then
	 *		|	if (this.timeInContactWithMazub == 0) then (
	 *		|	this.getWorld().getPlayableMazub().setHitPoints(mazubCurrentHitPoints +50) and
	 *		|	this.setHitPoints(this.getHitPoints()-1) )
	 *		|	and this.timeInContactWithMazub += dt
	 *		|	if (this.timeInContactWithMazub >=0.6) then
	 *		|		this.getWorld().getPlayableMazub().setHitPoints(mazubCurrentHitPoints +50) then
	 *		|		this.timeInContactWithMazub = this.timeInContactWithMazub%0.6 and
	 *		|		this.setHitPoints(this.getHitPoints()-1)
	 *  	|			if (this.getHitPoints() == 0) then
	 *		|			this.terminate()
	 * @effect	
	 * 		| if ( ! this.collidesWith(xMazub,yMazub,xPMazub,yPMazub)) then
	 *		|	this.timeInContactWithMazub = 0	
	 */
	public void skullcabOverlapsPlayableMazub(double dt) {
		
		if ((this.getWorld() != null) && (this.getWorld().getPlayableMazub() != null)){
		int xMazub = this.getWorld().getPlayableMazub().getPixelX();
		int yMazub = this.getWorld().getPlayableMazub().getPixelY();
		int xPMazub = this.getWorld().getPlayableMazub().getCurrentSprite().getWidth();
		int yPMazub = this.getWorld().getPlayableMazub().getCurrentSprite().getHeight();
		int mazubCurrentHitPoints = this.getWorld().getPlayableMazub().getHitPoints();
			if (this.collidesWith(xMazub,yMazub,xPMazub,yPMazub)) {
				if (this.timeInContactWithMazub == 0) {
				this.getWorld().getPlayableMazub().setHitPoints(mazubCurrentHitPoints +50);
				this.setHitPoints(this.getHitPoints()-1);
				}
				this.timeInContactWithMazub += dt;
				if (this.timeInContactWithMazub >=0.6) {
					this.getWorld().getPlayableMazub().setHitPoints(mazubCurrentHitPoints +50);
					this.timeInContactWithMazub = this.timeInContactWithMazub%0.6;
					this.setHitPoints(this.getHitPoints()-1);
					if (this.getHitPoints() == 0) {
						this.terminate();
					}
				}	
			}
			if ( ! this.collidesWith(xMazub,yMazub,xPMazub,yPMazub)) {
				this.timeInContactWithMazub = 0;
			}
		}	
	}
	
	
	@Override
	public void advanceTime(double deltaT) throws IllegalArgumentException {
		if (deltaT <= -0.000001) 
			throw new IllegalArgumentException();
		if (deltaT > 0.2) 
			throw new IllegalArgumentException();
		if (! this.isDead()) {
			this.setMovingTime(this.getMovingTime() + deltaT);
			if (this.getMovingTime() <= 0.5) {				
				this.lifeTime -= deltaT;
				double newPosY = this.calculatePositionY(deltaT);
				if (((int) (100 * newPosY) < 0) || ((int) (100 * newPosY) >
				this.getWorld().getWorldHeight())){
					this.terminate();
				}
				else if ((newPosY >= 0) && (newPosY <=
				this.getWorld().getWorldHeight())) {
					this.setActualY(newPosY);
					this.setPixelY((int) (100 * newPosY));
				}
				if (this.getLifeTime() <= 0) {
					this.kill();
					}
				
			}
			if (this.getMovingTime() > 0.5) {
				double nbOfSecondsAfterSwitch = this.getMovingTime()-0.5;
				this.setMovingTime(0);
				this.advanceTime(deltaT-nbOfSecondsAfterSwitch);
				if (this.getVerticalVelocity()>0) {
					this.setVerticalVelocity(-0.5);
					this.setCurrentSprite(sprites[1]);
				}
				else {
					this.setVerticalVelocity(0.5);
					this.setCurrentSprite(sprites[0]);
					}
				this.setMovingTime(0);
				this.advanceTime(nbOfSecondsAfterSwitch);
				this.setMovingTime(nbOfSecondsAfterSwitch);
				
			}
			this.skullcabOverlapsPlayableMazub(deltaT);
		}
		
		else {
			this.setDelay(this.getDelay()+deltaT);
			if (this.getDelay() >=0.6)
				this.terminate();
			}
		
	}
	
	/**
	 * Variable registering the time this Skullcab is in contact with the playableMazub.
	 */
	public double timeInContactWithMazub=0;
	/**
	 * Check whether the given horizontalVelocity is a valid horizontalVelocity for
	 * any Skullcab.
	 *  
	 * @param  horizontalVelocity
	 *         The horizontalVelocity to check.
	 * @return  	
	 * 		  | result == (horizontalVelocity == 0)
	 */
	@Override
	public boolean isValidHorizontalVelocity(double horizontalVelocity) {
		if (horizontalVelocity == 0) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Check whether the given minHorizontalVelocity is a valid minHorizontalVelocity for
	 * any Skullcab.
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
	 * any Skullcab.
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
