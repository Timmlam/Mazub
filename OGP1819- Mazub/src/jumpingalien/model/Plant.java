package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.util.Sprite;

/**
 * A class of plants extending the class GameObject.
 * 
* @invar  The pixelX of each Plant must be a valid pixelX for any
*         Plant.
*       | this.hasValidPixelX()
* @invar  The pixelY of each Plant must be a valid pixelY for any
*         Plant.
*       | this.hasValidPixelY()
* @invar  The sprites of each Plant must be valid sprites for any
*         Plant.
*       | isValidSprites(getSprites)
*       
* @version 3.0
* 
* @author Maarten Volkaerts
* @author Timm Lamberechts
* 
* 
* Timm Lamberechts - Burgerlijk ingenieur
* Maarten Volkaerts - Burgerlijk ingenieur
* 
* https://github.com/KUL-ogp/ogp1819-project-lamberechts-volkaerts
* 
*/
public abstract class Plant extends GameObject {
	
	/**
	 * Initialize this new Plant with given pixel position (pixelX and pixelY).
	 *
	 * @param  pixelX
	 *         The pixelX in the gaming world  for this new Plant.
	 * @param  pixelY
	 *         The pixelY in the gaming world  for this new Plant. 
	 * @param  sprites
     *         The sprites for this new Plant. 
     * @effect  This Mazub is initialized as a GameObject with the given pixelX, pixelY and sprites. 
	 *       |	super(pixelX, pixelY,(double) pixelX/100,(double)pixelY/100,"front",0,0,Double.POSITIVE_INFINITY,0,0,0,100,sprites[0], sprites);
	 */
	public Plant(int pixelX,int pixelY,Sprite... sprites) {
		super(pixelX, pixelY,(double) pixelX/100,(double)pixelY/100,"front",0,0,Double.POSITIVE_INFINITY,0,0,0,100,sprites[0], sprites);
		this.setMovingTime(0);
		}
	
	/**
	 * Check whether the given sprites are valid sprites for this Plant.
	 * @param sprites
	 * 		  The sprites to check.
	 * @return (sprites.length == 2)
	 */
	public  boolean isValidSprite(Sprite... sprites) {
		if (sprites.length !=2) 
			return false;
		
		return true;

	}
	
	/**
	 * Check whether the given horizontalAcceleration is a valid horizontalAcceleration for
	 * any Plant.
	 *  
	 * @param  horizontalAcceleration
	 *         The horizontalAcceleration to check.
	 * @return True if and only if the horizontal acceleration is equal to 0.
	 *       | result == this.getHorizontalAcceleration() == 0 
	 *       
	*/
	@Override
	boolean isValidHorizontalAcceleration(double horizontalAcceleration) {
		// TODO Auto-generated method stub
		return (horizontalAcceleration == 0);
	}
	
	/**
	 * Return the life time of this Plant.
	 */
	@Basic @Raw
	public double getLifeTime(){
		return this.lifeTime;	
	}
	
	/**
	 * Variable registering the life time of this Plant.
	 */
	protected double lifeTime;
		
	
	/**
	 * Set the given moving time as the moving time of this Plant.
	 * @param movingTime
	 * 		  The new moving time of this plant
	 * @post new.getMovingTime = movingTime
	 */
	public void setMovingTime(double movingTime) {
		this.movingTime = movingTime;
	}
	
	
	/**
	 * Return the moving time of this Plant.
	 */
	@Basic @Raw
	public double getMovingTime() {
		return movingTime;
	}
	
	/**
	 * Variable registering the moving time of this Plant.
	 */
	private double movingTime;
}
