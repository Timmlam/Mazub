package jumpingalien.model;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of Worlds with a width, height, tiles, a target tile, a visible window and geological features.
 * @invar  The tileLength of each World must be a valid tileLength for any
 *         World.
 *       | isValidTileLength(getTileLength())
 * @invar  The nbTilesX of each World must be a valid nbTilesX for any
 *         World.
 *       | isValidNbTilesX(getNbTilesX())
 * @invar  The nbTilesY of each World must be a valid nbTilesY for any
 *         World.
 *       | isValidNbTilesY(getNbTilesY())
 * @invar  The targetTile of each World must be a valid targetTile for any
 *         World.
 *       | isValidTargetTile(getTargetTile())
 * @invar  Each World can have its visibleWindowWidth as visibleWindowWidth.
 *       | canHaveAsVisibleWindowWidth(this.getVisibleWindowWidth())
 * @invar  Each World can have its visibleWindowHeight as visibleWindowHeight.
 *       | canHaveAsVisibleWindowHeight(this.getVisibleWindowHeight())      
 * @invar  The geologicalFeatures of each World must be a valid geologicalFeatures for any
 *         World.
 *       | isValidGeologicalFeatures(getGeologicalFeatures())
 * @invar Each World can have must have valid gameObjects
 * 	     | hasvalidGameObjects(getGameObjects)
 * 
 * @version 3.0
 * 
 * @author Maarten Volkaerts
 * @author Timm Lamberechts
 * 
 * Timm Lamberechts - Burgerlijk ingenieur
 * Maarten Volkaerts - Burgerlijk ingenieur
 * 
 * https://github.com/KUL-ogp/ogp1819-project-lamberechts-volkaerts
 * 
 * 
 */

public class World {

	/**
	 * Initialize this new World with given tileLength, nbTilesX, nbTilesY, targetTile, visibleWindowWidth,
	 * visibleWindowHeight and geological features.
	 * 
	 * @param  tileLength
	 *         The tileLength for this new World.
	 * @param  nbTilesX
	 *         The nbTilesX for this new World.
	 * @param  nbTilesY
	 *         The nbTilesY for this new World.
	 * @param  targetTile
	 *         The targetTile for this new World.
	 * @param  visibleWindowWidth
	 *         The visibleWindowWidth for this new World.
	 * @param  visibleWindowHeight
	 *         The visibleWindowHeight for this new World.
	 * @param  geologicalFeatures
	 *         The geologicalFeatures for this new World.
	 *         
	 * @effect setTileLength(tileLength)       
	 * @effect setNbTilesX(nbTilesX)
	 * @effect setNbTilesY(nbTilesY)
	 * @effect setTargetTile(targetTile)
	 * @effect new.getVisibleWindowWidth() == visibleWindowWidth
	 * @effect new.getVisibleWindowHeight() == visibleWindowHeight
	 * @effect setGeologicalFeatures(geologicalFeatures)
	 * 
	 * @throws RuntimeException
	 *         This new World cannot have the given visibleWindowWidth as its visibleWindowWidth.
	 *       | ! canHaveAsVisibleWindowWidth(this.getVisibleWindowWidth())
	 * @throws RuntimeException
	 *         This new World cannot have the given visibleWindowHeight as its visibleWindowHeight.
	 *       | ! canHaveAsVisibleWindowHeight(this.getVisibleWindowHeight())
	 */
	
	public World(int tileLength,int nbTilesX,int nbTilesY,int[] targetTile,int visibleWindowWidth, int visibleWindowHeight,
			Geological[] geologicalFeatures) throws RuntimeException {
		this.setTileLength(tileLength);
		this.setNbTilesX(nbTilesX);
		this.setNbTilesY(nbTilesY);
		this.setTargetTile(targetTile);
		if (! canHaveAsVisibleWindowWidth(visibleWindowWidth))
			throw new RuntimeException();
		this.visibleWindowWidth = visibleWindowWidth;
		if (! canHaveAsVisibleWindowHeight(visibleWindowHeight))
			throw new RuntimeException();
		this.visibleWindowHeight = visibleWindowHeight;
		this.setGeologicalFeatures(geologicalFeatures);
		
	}

	/**
	 * Initialize this new World with given tileLength, nbTilesX, nbTilesY, targetTile, visibleWindowWidth,
	 * visibleWindowHeight.
	 * 
	 * @param  tileLength
	 *         The tileLength for this new World.
	 * @param  nbTilesX
	 *         The nbTilesX for this new World.
	 * @param  nbTilesY
	 *         The nbTilesY for this new World.
	 * @param  targetTile
	 *         The targetTile for this new World.
	 * @param  visibleWindowWidth
	 *         The visibleWindowWidth for this new World.
	 * @param  visibleWindowHeight
	 *         The visibleWindowHeight for this new World.
	 *         
	 * @effect setTileLength(tileLength)       
	 * @effect setNbTilesX(nbTilesX)
	 * @effect setNbTilesY(nbTilesY)
	 * @effect setTargetTile(targetTile)
	 * @effect new.getVisibleWindowWidth() == visibleWindowWidth
	 * @effect new.getVisibleWindowHeight() == visibleWindowHeight
	 * @effect setGeologicalFeatures(new Geological[] {})
	 */
	public World(int tileLength,int nbTilesX,int nbTilesY,int[] targetTile,int visibleWindowWidth, 
			int visibleWindowHeight ) {
		this(tileLength, nbTilesX, nbTilesY,targetTile,visibleWindowWidth,visibleWindowHeight, 
				new Geological[] {});
	}
	
	/**
	 * Return the tileLength of this World.
	 */
	@Basic @Raw
	public int getTileLength() {
		return this.tileLength;
	}
	
	/**
	 * Check whether the given tileLength is a valid tileLength for
	 * any World.
	 *  
	 * @param  tileLength
	 *         The tileLength to check.
	 * @return 
	 *       | result == tileLength >0
	*/
	public static boolean isValidTileLength(int tileLength) {
		return tileLength >0;
	}
	
	/**
	 * Set the tileLength of this World to the given tileLength.
	 * 
	 * @param  tileLength
	 *         The new tileLength for this World.
	 * @post   If the given tileLength is a valid tileLength for any World,
	 *         the tileLength of this new World is equal to the given
	 *         tileLength.
	 *       | if (isValidTileLength(tileLength))
	 *       |   then new.getTileLength() == tileLength
	 */
	@Raw
	public void setTileLength(int tileLength) {
		if (isValidTileLength(tileLength)) {
			this.tileLength = tileLength;
		}
		else {
			this.tileLength = 0;}
	}
	
	/**
	 * Variable registering the tileLength of this World.
	 */
	private int tileLength;

	/**
	 * Return the nbTilesX of this World.
	 */
	@Basic @Raw
	public int getNbTilesX() {
		return this.nbTilesX;
	}
	
	/**
	 * Check whether the given nbTilesX is a valid nbTilesX for
	 * any World.
	 *  
	 * @param  nbTilesX
	 *         The nbTilesX to check.
	 * @return 
	 *       | result == nbTilesX > 0	
	*/
	public static boolean isValidNbTilesX(int nbTilesX) {
		return nbTilesX >0;
	}
	
	/**
	 * Set the nbTilesX of this World to the given nbTilesX.
	 * 
	 * @param  nbTilesX
	 *         The new nbTilesX for this World.
	 * @post   If the given nbTilesX is a valid nbTilesX for any World,
	 *         the nbTilesX of this new World is equal to the given
	 *         nbTilesX.
	 *       | if (isValidNbTilesX(nbTilesX))
	 *       |   then new.getNbTilesX() == nbTilesX
	 */
	@Raw
	public void setNbTilesX(int nbTilesX) {
		if (isValidNbTilesX(nbTilesX)) {
			this.nbTilesX = nbTilesX;
		}
		else {
			this.nbTilesX = 0;
		}
	}
	
	/**
	 * Variable registering the nbTilesX of this World.
	 */
	private int nbTilesX;
	
	/**
	 * Return the nbTilesY of this World.
	 */
	@Basic @Raw
	public int getNbTilesY() {
		return this.nbTilesY;
	}
	
	/**
	 * Check whether the given nbTilesY is a valid nbTilesX for
	 * any World.
	 *  
	 * @param  nbTilesY
	 *         The nbTilesY to check.
	 * @return 
	 *       | result == nbTilesY > 0	
	*/
	public static boolean isValidNbTilesY(int nbTilesY) {
		return nbTilesY > 0;
	}
	
	/**
	 * Set the nbTilesY of this World to the given nbTilesY.
	 * 
	 * @param  nbTilesY
	 *         The new nbTilesY for this World.
	 * @post   If the given nbTilesY is a valid nbTilesY for any World,
	 *         the nbTilesY of this new World is equal to the given
	 *         nbTilesY.
	 *       | if (isValidNbTilesY(nbTilesY))
	 *       |   then new.getNbTilesY() == nbTilesY
	 */
	@Raw
	public void setNbTilesY(int nbTilesY) {
		if (isValidNbTilesY(nbTilesY))
			this.nbTilesY = nbTilesY;
		else
			this.nbTilesY = 0;
			
	}
	
	/**
	 * Variable registering the nbTilesY of this World.
	 */
	private int nbTilesY;
	
	/**
	 * A method to inspect the width of the world.
	 * @return this.getNbTilesX()*this.getTileLength()
	 */
	public int getWorldWidth(){
		return ( this.getNbTilesX()*this.getTileLength());
	}
	/**
	 * A method to inspect the height of the world.
	 * @return (this.getNbTilesY()*this.getTileLength()
	 */
	public int getWorldHeight(){
		return (this.getNbTilesY()*this.getTileLength());
	}
	
	/**
	 * Return the position of the geological feature on the given index in the list of 
	 * geological features.
	 * @param index
	 * 			The index in the list of geological features.
	 * @return new in[] {pixelX,pixelY}
	 */
	public int[] getPositionGeologicalFeature(int index) {
		int pixelX = this.getTileLength()*(index%this.getNbTilesX());
		int pixelY = this.getTileLength()*((int)(index/this.getNbTilesX()));
		return new int[] {pixelX,pixelY};
	}
	
	/**
	 * Return the geologicalFeatures of this World.
	 */
	@Basic @Raw
	public Geological[] getGeologicalFeatures() {
		return this.geologicalFeatures;
	}
	
	/**
	 * Check whether the given geologicalFeatures is a valid geologicalFeatures for
	 * any World.
	 *  
	 * @param  geologicalFeatures
	 *         The geologicalFeatures to check.
	 * @return |if (geologicalFeatures.length == 0) 
	 *		   |then result == true
	 *		   |if (geologicalFeatures.length > this.getNbTilesX()*this.getNbTilesY()) 
	 *		   |	then result == false
	 *		   | else
	 *		   | 	 result == true
	 *       
	*/
	public boolean isValidGeologicalFeatures(Geological[] geologicalFeatures) {
		if (geologicalFeatures.length == 0) {
			return true;
		}		
		if (geologicalFeatures.length > this.getNbTilesX()*this.getNbTilesY()) {
			return false;
		}
		return true;
			
		
	}
	
	/**
	 * Set the geologicalFeatures of this World to the given geologicalFeatures.
	 * 
	 * @param  geologicalFeatures
	 *         The new geologicalFeatures for this World.
	 * @post   If the given geologicalFeatures is a valid geologicalFeatures for any World,
	 *         the geologicalFeatures of this new World is equal to the given
	 *         geologicalFeatures.
	 *       | if (isValidGeologicalFeatures(geologicalFeatures))
	 *       |   then new.getGeologicalFeatures() == geologicalFeatures
	 *       | for (index0 = 0;index0 < geologicalFeatures.length; index0++ )
	 * 		 |	if (! isValidGeologicalFeature(geologicalFeatures[index0])) 
	 *		 |	geologicalFeatures[index0] = Geological.AIR
	 *		 |	
	 *       |if (isValidGeologicalFeatures(geologicalFeatures)) 		
	 *		 |	int index;
	 *		 |		if (geologicalFeatures.length == 0) 
	 *		 |			then for (index = 0;index < totalNbTiles; index++ )
	 *	     |					geologicalFeaturesCopy[index] = Geological.AIR
	 *		 |	
	 *		 |	if (geologicalFeatures.length < totalNbTiles && geologicalFeatures.length !=0 ) 
	 *		 | 		then for (index = geologicalFeatures.length;index < totalNbTiles; index++ )
	 *		 |				geologicalFeaturesCopy[index] = Geological.AIR
	 *		 |	this.geologicalFeatures = geologicalFeatures
	 *      
	 */
	@Raw
	public void setGeologicalFeatures(Geological[] geologicalFeatures) {
		for (int index0 = 0;index0 < geologicalFeatures.length; index0++ )
			if (! isValidGeologicalFeature(geologicalFeatures[index0])) {
				geologicalFeatures[index0] = Geological.AIR;
			}
		int totalNbTiles = this.getNbTilesX()*this.getNbTilesY();
		Geological[] geologicalFeaturesCopy = Arrays.copyOf(geologicalFeatures, totalNbTiles);
		if (isValidGeologicalFeatures(geologicalFeatures)) {		
			if (geologicalFeatures.length == 0) {
				for (int index = 0;index < totalNbTiles; index++ )
					geologicalFeaturesCopy[index] = Geological.AIR;
			}
			if (geologicalFeatures.length < totalNbTiles && geologicalFeatures.length !=0 ) {
				for (int index = geologicalFeatures.length;index < totalNbTiles; index++ )
					
					geologicalFeaturesCopy[index] = Geological.AIR;
			}
			this.geologicalFeatures = geologicalFeaturesCopy;
		}

	}
	
	/**
	 * Variable registering the geologicalFeatures of this World.
	 */
	private Geological[] geologicalFeatures;
	
	/**
	 * Check whether the given geological feature is a valid geological feature.
	 * @param geologicalFeature
	 * 			The geological feature to check.
	 * @return 
	 * 		| for (Geological geo: Geological.values()) do
	 *		| if (geologicalFeature == geo)
	 *		|	then result == true
	 */
	public boolean isValidGeologicalFeature(Geological geologicalFeature) {
		for (Geological geo: Geological.values())
			if (geologicalFeature == geo)
				return true;
		return false;
	}
	
	/**
	 * Return the geological feature of this World on a given position.
	 * @param pixelX
	 * 		The pixelX (x-position) of the geological feature.
	 * @param pixelY
	 * 		The pixelY (y-position) of the geological feature.
	 * @return 
	 * 		| int index = tilePosY * this.getNbTilesX() + tilePosX
	 *		|	if (index >= this.getGeologicalFeatures().length)
	 *		|	then result == Geological.AIR
	 *		| else
	 *		|   result == this.getGeologicalFeatures()[index]
	 * 
	 */
	@Basic @Raw
	public Geological getGeologicalFeature(int pixelX,int pixelY) {
		int tilePosX = ((int) pixelX/this.getTileLength());
		int tilePosY = ((int) pixelY/this.getTileLength());
		int index = tilePosY * this.getNbTilesX() + tilePosX;
		if (index >= this.getGeologicalFeatures().length)
			return Geological.AIR;
		
		return this.getGeologicalFeatures()[index];
	}
	
	/**
	 * Set the given geological feature as the geological feature of this world on the
	 * given position.
	 * @param pixelX
	 * 		The pixelX (x-position) of the new geological feature.
	 * @param pixelY
	 * 		The pixelY (y-position) of the new geological feature.
	 * @param geologicalFeature
	 * 		The geological feature to set on the given position in this world.
	 * @post
	 * 		|int index = tilePosY * this.getNbTilesX() + tilePosX
	 *		|	if (index < this.getGeologicalFeatures().length)
	 *		|		then if (isValidGeologicalFeature(geologicalFeature)) 
	 *		|				then new.geologicalFeatures[index] = geologicalFeature;
	 *		|		else 
	 *		|			new.geologicalFeatures[index] = Geological.AIR;
	 */
	public void setGeologicalFeature(int pixelX, int pixelY,Geological geologicalFeature) {
		int tilePosX = ((int) pixelX/this.getTileLength());
		int tilePosY = ((int) pixelY/this.getTileLength());
		int index = tilePosY * this.getNbTilesX() + tilePosX;
		if (index < this.getGeologicalFeatures().length)
			if (isValidGeologicalFeature(geologicalFeature)) {
				this.geologicalFeatures[index] = geologicalFeature;
			}
			else {
				this.geologicalFeatures[index] = Geological.AIR;

		}
	}

	/**
	 * Return the targetTile of this World.
	 */
	@Basic @Raw
	public int[] getTargetTile() {
		return this.targetTile;
	}
	
	/**
	 * Check whether the given targetTile is a valid targetTile for
	 * any World.
	 *  
	 * @param  targetTile
	 *         The targetTile to check.
	 * @return 
	 *       | result == true
	*/
	public static boolean isValidTargetTile(int[] targetTile) {
		return true;
	}
	
	/**
	 * Set the targetTile of this World to the given targetTile.
	 * 
	 * @param  targetTile
	 *         The new targetTile for this World.
	 * @pre    The given targetTile must be a valid targetTile for any
	 *         World.
	 *       | isValidTargetTile(targetTile)
	 * @post   The targetTile of this World is equal to the given
	 *         targetTile.
	 *       | new.getTargetTile() == targetTile
	 */
	@Raw
	public void setTargetTile(int[] targetTile) {
		assert isValidTargetTile(targetTile);
		this.targetTile = targetTile;
		
	}
	
	/**
	 * Variable registering the targetTile of this World.
	 */
	private int[] targetTile;

	/**
	 * Return the visibleWindowWidth of this World.
	 */
	@Basic @Raw @Immutable
	public int getVisibleWindowWidth() {
		return this.visibleWindowWidth;
	}
	
	/**
	 * Check whether this World can have the given visibleWindowWidth as its visibleWindowWidth.
	 *  
	 * @param  visibleWindowWidth
	 *         The visibleWindowWidth to check.
	 * @return 
	 *       | result == (visibleWindowWidth > 0 && visibleWindowWidth < this.getWorldWidth())
	*/
	@Raw
	public boolean canHaveAsVisibleWindowWidth(int visibleWindowWidth) {
		if (this.getWorldWidth() == 0) {
			return true;
		}
		if (visibleWindowWidth < 0) {
			return false;
		}
		if (visibleWindowWidth > this.getWorldWidth()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Variable registering the visibleWindowWidth of this World.
	 */
	private final int visibleWindowWidth;
	 
	/**
	 * Return the visibleWindowHeight of this World.
	 */
	@Basic @Raw @Immutable
	public int getVisibleWindowHeight() {
		return this.visibleWindowHeight;
	}
	
	/**
	 * Check whether this World can have the given visibleWindowHeight as its visibleWindowHeight.
	 *  
	 * @param  visibleWindowHeight
	 *         The visibleWindowHeight to check.
	 * @return 
	 *       | result == (visibleWindowHeight > 0 && visibleWindowHeight < this.getWorldHeight())
	*/
	@Raw
	public boolean canHaveAsVisibleWindowHeight(int visibleWindowHeight) {
		if (this.getWorldHeight() == 0) {
			return true;
		}
		if (visibleWindowHeight < 0) {
				return false;
		}
		if (visibleWindowHeight > this.getWorldHeight()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Variable registering the visibleWindowHeight of this World.
	 */
	private final int visibleWindowHeight;

	/**
	 * Return the visibleWindowX of this World.
	 */
	@Basic @Raw
	public int getVisibleWindowX() {
		return this.visibleWindowX;
	}
	
	/**
	 * Check whether the given visibleWindowX is a valid visibleWindowX for
	 * any World.
	 *  
	 * @param  visibleWindowX
	 *         The visibleWindowX to check.
	 * @return 
	 *       | if (visibleWindowX <0)
		 	 |     then result == false
		     | if( visibleWindowX + this.getVisibleWindowWidth() > this.getWorldWidth())
			 | 		then result == false
			 |	else 
			 |	then result == true
	*/
	public  boolean isValidVisibleWindowX(int visibleWindowX) {
		if (visibleWindowX <0)
			return false;
		if( visibleWindowX + this.getVisibleWindowWidth() > this.getWorldWidth())
			return false;
		else
			return true;
		
	}

	/**
	 * Set the visibleWindowX of this World to the correct visibleWindowX.
	 * @post 
	 * 		 |	if (this.getPlayableMazub() == null) 
	 *		 | 		then this.visibleWindowX = 0
	 *		 |	else if ( this.getPlayableMazub().getPixelX() - 200 < 0) 
	 *		 |		then this.visibleWindowX = 0
	 *		 |	else if (( this.getPlayableMazub().getPixelX() - 200 > 0) and (
	 *		 |	  this.getPlayableMazub().getPixelX() +this.getPlayableMazub().getCurrentSprite().getWidth()+ 200 < 
	 *		 | 	  this.getWorldWidth()))  
	 *		 |		then this.visibleWindowX = this.getPlayableMazub().getPixelX() - 200 
	 *		 |	else if ( this.getPlayableMazub().getPixelX() +this.getPlayableMazub().getCurrentSprite().getWidth()+ 200
	 *		 |	  > this.getWorldWidth()) 
	 *		 |		then this.visibleWindowX = this.getWorldWidth() - this.getVisibleWindowWidth()
	 * @post
	 * 		 |	if (this.visibleWindowX + this.getVisibleWindowWidth() > this.getWorldWidth())
	 *		 |		then this.visibleWindowX= this.getWorldWidth() - this.getVisibleWindowWidth()      
	 * @throws RuntimeException
	 *       | ! isValidVisibleWindowX(getVisibleWindowX())
	 */
	@Raw
	public void setVisibleWindowX() 	
			throws RuntimeException {
		if (this.getPlayableMazub() == null) {
			this.visibleWindowX = 0;}
		else if ( this.getPlayableMazub().getPixelX() - 200 < 0) {
			this.visibleWindowX = 0;}
		else if (( this.getPlayableMazub().getPixelX() - 200 > 0) && (
				this.getPlayableMazub().getPixelX() +this.getPlayableMazub().getCurrentSprite().getWidth()+ 200 < 
				this.getWorldWidth()))  {
			this.visibleWindowX = this.getPlayableMazub().getPixelX() - 200 ;}
		else if ( this.getPlayableMazub().getPixelX() +this.getPlayableMazub().getCurrentSprite().getWidth()+ 200
				> this.getWorldWidth()) {
			this.visibleWindowX = this.getWorldWidth() - this.getVisibleWindowWidth();}
		if (this.visibleWindowX + this.getVisibleWindowWidth() > this.getWorldWidth())
			this.visibleWindowX= this.getWorldWidth() - this.getVisibleWindowWidth();
		
	}
	
	/**
	 * Variable registering the visibleWindowX of this World.
	 */
	private int visibleWindowX;

	/**
	 * Return the visibleWindowY of this World.
	 */
	@Basic @Raw
	public int getVisibleWindowY() {
		return this.visibleWindowY;
	}
	
	/**
	 * Check whether the given visibleWindowY is a valid visibleWindowY for
	 * any World.
	 *  
	 * @param  visibleWindowY
	 *         The visibleWindowY to check.
	 * @return 
	 *       | if (visibleWindowY <0)
		 	 |		then result == false
		     | if( visibleWindowX + this.getVisibleWindowHeight() > this.getWorldHeight())
			 |		then result == false
			 | else
			 |		result == true;
	*/
	public  boolean isValidVisibleWindowY(int visibleWindowY) {
		if (visibleWindowY <0)
			return false;
		if( visibleWindowY + this.getVisibleWindowHeight() > this.getWorldHeight())
			return false;
		else
			return true;
		
	}
	
	/**
	 * Set the visibleWindowY of this World to the correct visibleWindowY.
	 * @post 
	 * 		 |	if (this.getPlayableMazub() == null) 
	 *		 | 		then this.visibleWindowY = 0
	 *		 |	else if ( this.getPlayableMazub().getPixelY() - 200 < 0) 
	 *		 |		then this.visibleWindowY = 0
	 *		 |	else if (( this.getPlayableMazub().getPixelY() - 200 > 0) and (
	 *		 |	  this.getPlayableMazub().getPixelY() +this.getPlayableMazub().getCurrentSprite().getHeight()+ 200 < 
	 *		 | 	  this.getWorldHeight()))  
	 *		 |		then this.visibleWindowY = this.getPlayableMazub().getPixelY() - 200 
	 *		 |	else if ( this.getPlayableMazub().getPixelY() +this.getPlayableMazub().getCurrentSprite().getHeight()+ 200
	 *		 |	  > this.getWorldHeight()) 
	 *		 |		then this.visibleWindowY = this.getWorldHeight() - this.getVisibleWindowHeight()
	 * @post
	 * 		 |	if (this.visibleWindowY + this.getVisibleWindowHeight() > this.getWorldHeight())
	 *		 |		then this.visibleWindowY= this.getWorldHeight() - this.getVisibleWindowHeight()      
	 * @throws RuntimeException
	 *       | ! isValidVisibleWindowY(getVisibleWindowY())
	 */
	@Raw
	public void setVisibleWindowY() 
			throws RuntimeException {
		if (this.getPlayableMazub() == null) {
			this.visibleWindowY = 0;}
		else if (( this.getPlayableMazub().getPixelY() - 200 < 0) && (this.getPlayableMazub().getPixelY() +this.getPlayableMazub().getCurrentSprite().getHeight()+ 200 < 
				this.getWorldHeight())) {
			this.visibleWindowY = 0;}
		else if (( this.getPlayableMazub().getPixelY() - 200 > 0) && (
				this.getPlayableMazub().getPixelY() +this.getPlayableMazub().getCurrentSprite().getHeight()+ 200 < 
				this.getWorldHeight()))  {
			this.visibleWindowY = this.getPlayableMazub().getPixelY() - 200 ;}
		else if ( this.getPlayableMazub().getPixelY() +this.getPlayableMazub().getCurrentSprite().getHeight()+ 200
				> this.getWorldHeight()) {
			this.visibleWindowY = this.getWorldHeight() - this.getVisibleWindowHeight();}
		if (this.visibleWindowY + this.getVisibleWindowHeight() > this.getWorldHeight())
			this.visibleWindowY= this.getWorldHeight() - this.getVisibleWindowHeight();
	}
	
	/**
	 * Variable registering the visibleWindowY of this World.
	 */
	private int visibleWindowY;

	/**
	 * Return the gameObjects of this World.
	 */
	@Basic @Raw
	public Set<GameObject> getGameObjects() {
		return this.gameObjects;
	}
	
	/**
	 * Check whether the given gameObjects is a valid gameObjects for
	 * any World.
	 *  
	 * @param  gameObjects
	 *         The gameObjects to check.
	 * @return 
	 *       | result == gameObjects.size() <= this.maxGameObjects
	*/
	public boolean isValidGameObjects(Set<GameObject> gameObjects) {
		return gameObjects.size() <= (this.maxGameObjects)  ;
	}
	
	/**
	 * Set the gameObjects of this World to the given gameObjects. 
	 * @param  gameObjects
	 *         The new gameObjects for this World.
	 * @post   The gameObjects of this new World is equal to
	 *         the given gameObjects.
	 *       | new.getGameObjects() == gameObjects
	 * @throws RuntimeException
	 *         The given gameObjects is not a valid gameObjects for any
	 *         World.
	 *       | ! isValidGameObjects(getGameObjects())
	 */
	@Raw
	public void setGameObjects(Set<GameObject> gameObjects) 
			throws RuntimeException {
		if (! isValidGameObjects(gameObjects))
			throw new RuntimeException();
		this.gameObjects = gameObjects;
	}
	
	/**
	 * Variable registering the gameObjects of this World.
	 */
	private Set<GameObject>  gameObjects = new HashSet<GameObject>();
	
	/**
	 * Add the given object to the world.
	 * @param object
	 *   	The game object to add to this world.
	 * @post
	 * 		| if ((this.gameObjects.size() == 0) && (object instanceof Mazub)) 
	 *		|	then this.getPlayableMazub == (Mazub) object
	 *		|	and getGameObjects.add(object)
	 *		|	and object.getWorld() == this
	 *		| else 
	 *      |   getGameObjects.add(object)
	 *      |   object.getWorld() == this  
	 * @throws RuntimeException
	 * 		| (this.isStarted() == true)
	 * @throws RuntimeException
	 * 		| (object.isTerminated())
	 * @throws RuntimeException
	 * 		| ((this.gameObjects.size() == this.maxGameObjects))
	 * @throws RuntimeExcpetion
	 *      | ((object.collidesWithGeological(object.getPixelX(), object.getPixelY(), Geological.getAllImpassable())) 
	 *		|	&&  (object instanceof Mazub)) 
	 *		|   &&  ( ! ((Mazub) object).mazubOnSolidGround())
	 * @throws RuntimeException
	 * 		| ((object instanceof Shark) || (object instanceof Slime)) 
	 *		|		and ((object.collidesWithGeologicals(object.getPixelX(), 
	 *		|		   object.getPixelY(), Geological.getAllImpassable()))
	 *		|		and  ( ! object.objectOnImpassableTerrain(object.getPixelX(),object.getPixelY()))) 					
	 */
	
	public void addGameObject(GameObject object) throws RuntimeException {
		if (this.isTerminated == false) {
			if (this.isStarted() == true)
				throw new RuntimeException();
			if ( object.isTerminated() )
				throw new RuntimeException();
			if (((this.gameObjects.size() == this.maxGameObjects) && (this.playableMazub != null)) ||
				((this.gameObjects.size() == this.maxGameObjects-1) && (this.playableMazub == null) 
						&& (! (object instanceof Mazub))))
				throw new RuntimeException();
			if ((this.playableMazub == null) && (object instanceof Mazub)) {
				this.setPlayableMazub((Mazub) object);
				this.gameObjects.add(object);
				object.setWorld(this);
				if ((object.collidesWithGeologicals(object.getPixelX(), object.getPixelY(), Geological.getAllImpassable())) &&
						(object instanceof Mazub)) {
							if ( ! (object.objectOnImpassableTerrain(object.getPixelX(),object.getPixelY()))) {
								this.removeGameObject(object);
								throw  new RuntimeException();
							}
						}
			}
			else {
				
				this.gameObjects.add(object);
				object.setWorld(this);
				if (object instanceof Mazub)
					throw new  RuntimeException();
				if ((object instanceof Shark) || (object instanceof Slime))
					if ((object.collidesWithGeologicals(object.getPixelX(), object.getPixelY(), Geological.getAllImpassable()))
							&&  ( ! object.objectOnImpassableTerrain(
									object.getPixelX(),object.getPixelY()))) {
						this.removeGameObject(object);
						throw new RuntimeException();
					}
			}
		}
		else {
			throw new RuntimeException();
		}
	}
	
	/**
	 * Remove the given object from this world.
	 * @param object
	 * 		The object to remove from this world.
	 * @post
	 * 		| if (this.getPlayableMazub == object)
	 *		|	then this.setPlayableMazub(null)
 	 *	@post	
     *		| this.gameObjects.remove(object)
	 *		| object.setWorld(null)
	 * @throws RuntimeException
	 * 		| (! this.hasAsGameObject(object))
	 * @throws RuntimeException
	 * 		| (object == null)
	 * 	
	 */
	public void removeGameObject(GameObject object) throws RuntimeException {
		if (! this.hasAsGameObject(object)) {
			throw new RuntimeException();
		}
		if (object == null) {
			throw new RuntimeException();
		}

		else { 
			if (this.getPlayableMazub() == object)
				this.setPlayableMazub(null);
			this.gameObjects.remove(object);
			object.setWorld(null);
		}		
	}
	
	/**
	 * Boolean checking whether the world contains the given game object.
	 * @param object
	 * 		The game object to check.
	 * @return this.gameObjects.contains(object)
	 * 		
	 */
	public boolean hasAsGameObject(GameObject object) {
		return this.gameObjects.contains(object);
	}
	
	/**
	 * Set the maximum of game objects of this world to the given maximum.
	 * @param maximum
	 * 			The maximum of game objects in this world.
	 * @post new.maxGameObjects = gameObjects
	 */
	@Raw
	public void setMaxGameObjects(int max) {
		this.maxGameObjects = max;
	}
	
	/**
	 * A method to get the maximum of game objects of this world.
	 * @ return this.maxGameObjects
	 */
	@Basic @Raw
	public int getMaxGameObjects(){
		return this.maxGameObjects;
		
	}
	
	/**
	 * A variable registering the maximum of game objects in the world
	 */
	private int maxGameObjects =  101;
	
	/**
	 * Set the given Mazub as the playable Mazub of this world.
	 * @param mazub
	 * 		The mazub to set as playable Mazub for this world.
	 * @post
	 *     | new.getPlayableMazub() == mazub
	 */
	public void setPlayableMazub(Mazub mazub) {
		this.playableMazub = mazub;
		this.setVisibleWindowX();
		this.setVisibleWindowY();
	}
	
	/**
	 * A method to return the playable mazub of this world.
	 * @return this.playableMazub
	 */
	@Basic @Raw 
	public Mazub getPlayableMazub() {
		return this.playableMazub;
		
	}
	
	/**
	 * Variable registering the playable mazub.
	 */
	private Mazub playableMazub;
	
	
	/**
	 * Terminate the given world
	 * @post new.isTerminated()
	 * @post 
	 * 		|if (! this.isTerminated())
	 *		| then this.getPlayableMazub.setWorld(null)
	 *		| and   new.getPlayableMazub = null
	 * @effect 
	 *		|	for (GameObject objects: copySet) do
	 *		|	this.removeGameObject(objects)
	 */
	public void terminate() {
		if (! this.isTerminated()) {
			this.isTerminated = true;
			if (this.getPlayableMazub() != null) {
				this.getPlayableMazub().setWorld(null);
				this.setPlayableMazub(null);
			}
			Set<GameObject> copySet = this.getGameObjects().stream().collect(Collectors.toSet());
			for (GameObject objects: copySet)
				this.removeGameObject(objects);
		}
	}
	
	@Basic @Raw
	/**
	 * Check whether the world is terminated
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	/**
	 * Variable registering whether the world is terminated
	 */
	private boolean isTerminated = false;
		
	public void advanceTime(double deltaT) throws IllegalArgumentException {
		if (deltaT < 0)
			throw new IllegalArgumentException();
		if (deltaT > 0.2) 
			throw new IllegalArgumentException();
		if (this.getPlayableMazub()!= null) {
			this.getPlayableMazub().advanceTime(deltaT);
		}
		Set<GameObject> copyGameObjects = this.getGameObjects();
		Set<GameObject> newSet = copyGameObjects.stream().collect(Collectors.toSet());	
		for (GameObject i: newSet) {
			if (i != this.getPlayableMazub()) {
				i.advanceTime(deltaT);
			}
		}
		this.setVisibleWindowX();
		this.setVisibleWindowY();
	}
	
	/**
	 * Start a game in the given world.
	 * @post new.startGame()
	 * @post |if (! this.startGame())
	 *		 | then this.getPlayableMazub.setWorld(null)
	 *		 |   && new.getPlayableMazub = null
	 */
	public void startGame() {
		if (! this.isStarted())
			this.started = true;
	}
	@Basic @Raw
	/**
	 * Check whether a game is started in the world.
	 */
	public boolean isStarted() {
		return this.started;
	}
	/**
	 * Variable registering whether a game is started in the world.
	 */
	private boolean started = false;
	
	/**
	 * A method to check whether the playable mazub of this world has reached the target tile.
	 * @return
	 * 		| if ((x +(xP -1) < xOther) || (xOther +(xPOther - 1) < x) ||
	 *		|	(y +(yP -1) < yOther) || (yOther +(yPOther - 1) < y)) 
	 *		|	then result == false
	 *		|	else 
	 *		|	 result == true
	 */
	public boolean reachedTargetTile() {
		int x = this.getPlayableMazub().getPixelX();
		int y = this.getPlayableMazub().getPixelY();
		int xP = this.getPlayableMazub().getCurrentSprite().getWidth();
		int yP = this.getPlayableMazub().getCurrentSprite().getHeight();
		
		int xOther = this.getTargetTile()[0]*this.getTileLength();
		int yOther = this.getTargetTile()[1]*this.getTileLength();
		int xPOther = this.getTileLength();
		int yPOther = this.getTileLength();
		if ((x +(xP -1) < xOther) || (xOther +(xPOther - 1) < x) ||
				(y +(yP -1) < yOther) || (yOther +(yPOther - 1) < y)) {
			return false;
			}
		else {
			return true;
			}
		}

	/**
	 * Add the given school of slimes to this world.
	 * @param school
	 * 		The school to add.
	 * @effect
	 * 		| this.schools.add(school)
	 * @throws RuntimeException
	 * 		| (this.getSchools().size() == 10)
	 */
	public void addSchool(School school) throws RuntimeException {
		if (this.getSchools().size() == 10)
			throw new RuntimeException();
		this.schools.add(school);
	}
	/**
	 * Return (a set of) all the schools of this world.
	 */
	@Raw
	public Set<School> getSchools(){
		Set<School> allSchools = new HashSet<>();
		Iterator<School> myIterator = new Iterator<School>(){
			private int index=0;
			@Override
			public boolean hasNext() {
				if (index < schools.size())
					return true;
				return false;
			}
			@Override
			public School next() {
				// TODO Auto-generated method stub
				if (hasNext()) {
					index++;
					return (School) schools.toArray()[index-1];
				}
				else
					throw new RuntimeException();	
			}
		};
		
		myIterator.forEachRemaining((e) -> allSchools.add(e));
		return allSchools;
		
	}
	
	/**
	 * Set registering all the schools of this World.
	 */
	public Set<School> schools = new HashSet<>();
	
}
