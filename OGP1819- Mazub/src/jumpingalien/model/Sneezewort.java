package jumpingalien.model;

import jumpingalien.util.Sprite;

/**
 * A class of Sneezeworts extending the class Plant
 * @version 3.0
 * 
 * @author Timm Lamberechts
 * @author Maarten Volkaerts
 *
 */
public class Sneezewort extends Plant {
	
	/**
	 * Initialize this new Sneezewort with given pixel position (pixelX and pixelY).
	 *
	 * @param  pixelX
	 *         The pixelX in the gaming world  for this new Sneezewort.
	 * @param  pixelY
	 *         The pixelY in the gaming world  for this new Sneezewort. 
	 * @param  sprites
     *         The sprites for this new Sneezewort. 
     * @effect
     * 		| this.lifeTime = 10
     * @effect
     * 		| this.setHitPoints(1)
     * @effect
     * 		| this.setOrientation("left")
     * @effect
     * 		| this.setHorizontalVelocity(-0.5)
	 */
	public Sneezewort(int pixelX,int pixelY, Sprite... sprites) {
		super(pixelX,pixelY,sprites);
		this.lifeTime = 10;
		this.setHitPoints(1);
		this.setOrientation("left");
		this.setHorizontalVelocity(-0.5);
	}
	
	/**
	 * A method to check whether this Sneezewort overlaps the playable mazub of its world and if so to change 
	 * the hitpoints of this Sneezewort.
	 * @effect
	 * 		| if (this.collidesWith(xMazub,yMazub,xPMazub,yPMazub))
	 * 		|	then this.getWorld().getPlayableMazub().setHitPoints(mazubCurrentHitPoints +50)
	 *		|	 and this.terminate()	
	 */
	public void sneezewortOverlapsPlayableMazub() {
		
		if ((this.getWorld() != null) && (this.getWorld().getPlayableMazub() != null)){
		int xMazub = this.getWorld().getPlayableMazub().getPixelX();
		int yMazub = this.getWorld().getPlayableMazub().getPixelY();
		int xPMazub = this.getWorld().getPlayableMazub().getCurrentSprite().getWidth();
		int yPMazub = this.getWorld().getPlayableMazub().getCurrentSprite().getHeight();
		int mazubCurrentHitPoints = this.getWorld().getPlayableMazub().getHitPoints();
			if (this.collidesWith(xMazub,yMazub,xPMazub,yPMazub)) {
				this.getWorld().getPlayableMazub().setHitPoints(mazubCurrentHitPoints +50);
				this.terminate();
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
				double newPosX = this.calculatePositionX(deltaT);
				if ((newPosX < 0) || (newPosX >
				this.getWorld().getWorldWidth())){
					this.terminate();
				}
				if ((newPosX >= 0) && (newPosX <=
				this.getWorld().getWorldWidth())) {
					this.setActualX(newPosX);
					this.setPixelX((int) (100 * newPosX));
				}
				if (this.getLifeTime() <= 0) {
					this.kill();
					}
				
			}
			if (this.getMovingTime() > 0.5) {
				double nbOfSecondsAfterSwitch = this.getMovingTime()-0.5;
				this.setMovingTime(0);
				this.advanceTime(deltaT-nbOfSecondsAfterSwitch);
				if (this.getOrientation() == "left") {
					this.setOrientation("right");
					this.setHorizontalVelocity(0.5);
					this.setCurrentSprite(sprites[1]);
				}
				else {
					this.setOrientation("left");
					this.setHorizontalVelocity(-0.5);
					this.setCurrentSprite(sprites[0]);
					}
				this.setMovingTime(0);
				this.advanceTime(nbOfSecondsAfterSwitch);
				this.setMovingTime(nbOfSecondsAfterSwitch);
	
			}
			this.sneezewortOverlapsPlayableMazub();
		}
		
		else {
			this.setDelay(this.getDelay()+deltaT);
			if (this.getDelay() >=0.6)
				this.terminate();
			}
	}
	/**
	 * Check whether the given horizontalVelocity is a valid horizontalVelocity for
	 * any Sneezewort.
	 *  
	 * @param  horizontalVelocity
	 *         The horizontalVelocity to check.
	 * @return  	
	 * 		  | result ==  (( Math.abs(horizontalVelocity) == 0.5) || ( Math.abs(horizontalVelocity) == 0))
	 */
	@Override
	public boolean isValidHorizontalVelocity(double horizontalVelocity) {
		return (( Math.abs(horizontalVelocity) == 0.5) || ( Math.abs(horizontalVelocity) == 0));
	
	}
	/**
	 * Check whether the given minHorizontalVelocity is a valid minHorizontalVelocity for
	 * any Sneezewort.
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
	 * any Sneezewort.
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
