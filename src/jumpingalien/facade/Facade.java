package jumpingalien.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jumpingalien.internal.gui.sprites.JumpingAlienSprites;
import jumpingalien.model.GameObject;
import jumpingalien.model.Geological;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Skullcab;
import jumpingalien.model.Slime;
import jumpingalien.model.Sneezewort;
import jumpingalien.model.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.ShouldNotImplementException;
import jumpingalien.util.Sprite;

public class Facade implements IFacade {

	@Override
	public boolean isTeamSolution() {
		return true;
	}

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite... sprites) throws ModelException {
		try{
			return new Mazub(pixelLeftX,pixelBottomY,sprites);
		}
		catch (RuntimeException exc) {throw new ModelException("Cannot create this Mazub"); }
	}

	@Override
	public double[] getActualPosition(Mazub alien) throws ModelException {
		double[] position= {alien.getActualX(),alien.getActualY()};
		return position;	
		
	}

	@Override
	public void changeActualPosition(Mazub alien, double[] newPosition) throws ModelException {
		if (newPosition == null)
			throw new ModelException("New position is illegal");
		if ((Double.isNaN(newPosition[0])) | (Double.isNaN(newPosition[1]))) {
			throw new ModelException("New position is illegal");
		}
		if (newPosition.length >2)
			throw new ModelException("New position is illegal");
		if ((newPosition[0] > ((alien.getPixelWidth())/100)) |( newPosition[1] > 
		(alien.getPixelHeight())/100)|
				(newPosition[0] < 0 )|(newPosition[1] < 0)) {
			alien.terminate();
		}
		if (alien.getWorld() != null) {
			if (alien.collidesWithGeologicals((int) (newPosition[0]*100) ,(int) (newPosition[1]*100), 
					Geological.getAllImpassable())){
				throw new ModelException("Mazub in solid ground");
			}
		}
		try {
		alien.setActualX(newPosition[0]);
		alien.setActualY(newPosition[1]);
		if ((newPosition[0] >0) && (newPosition[1] >0)) {
		alien.setPixelX((int) (100*newPosition[0]));	
		alien.setPixelY((int) (100*newPosition[1]));
		}
			}
			catch (RuntimeException exc) {throw new ModelException("New position is illegal"); }		
	}

	@Override
	public int[] getPixelPosition(Mazub alien) throws ModelException {
		int[] position = {alien.getPixelX(),alien.getPixelY()};
		return position;
	}

	@Override
	public int getOrientation(Mazub alien) throws ModelException {
		if (alien.getOrientation() == "right")
			return 1;
		if (alien.getOrientation() == "left")
			return -1;
		else
			return 0;		
	}

	@Override
	public double[] getVelocity(Mazub alien) throws ModelException {
		double[] velocity = {alien.getHorizontalVelocity(),alien.getVerticalVelocity()};
		return velocity;
	}

	@Override
	public double[] getAcceleration(Mazub alien) throws ModelException {
		double[] acceleration = {alien.getHorizontalAcceleration(),alien.getVerticalAcceleration()};
		return acceleration;
	}
	@Override
	public Sprite[] getSprites(Mazub alien) throws ModelException, ShouldNotImplementException {
		if (!isTeamSolution()) {
			throw new ShouldNotImplementException("Not to be implemented by individual students");	
		}
		return alien.getSprites();
	
	}
	@Override
	public Sprite getCurrentSprite(Mazub alien) throws ModelException {
		if (!isTeamSolution()) {
			return JumpingAlienSprites.DEFAULT_MAZUB_SPRITE;
		}
		return alien.getCurrentSprite();
	}
	
	@Override
	public boolean isMoving(Mazub alien) throws ModelException {
		return alien.isMoving();
	}
	
	
	@Override
	public void startMoveLeft(Mazub alien) throws ModelException {
		if ((alien.isMoving()) | (alien.isDead()))
			throw new ModelException("Cannot start moving left!");
		else				
			alien.startMoveLeft();
				
		
	}

	@Override
	public void startMoveRight(Mazub alien) throws ModelException {
		if (alien.isMoving() | (alien.isDead()))
			throw new ModelException("Cannot start moving right!");
		else				
			alien.startMoveRight();
		
	
		
	}

	@Override
	public void endMove(Mazub alien) throws ModelException {
		if (! alien.isMoving())
			throw new ModelException("Cannot stop moving! Mazub is not moving");
		else				
			alien.endMove();
	}

	@Override
	public boolean isJumping(Mazub alien) throws ModelException {
		return alien.isJumping();
	}

	@Override
	public void startJump(Mazub alien) throws ModelException {
		try{ alien.startJump();
			
		}
		catch (RuntimeException exc) {throw new ModelException("Cannot start Jumping!"); }
		
	}

	@Override
	public void endJump(Mazub alien) throws ModelException {
		try{ alien.endJump();
		
		}
		catch (RuntimeException exc) {throw new ModelException("Cannot stop Jumping!"); }
		
	}

	@Override
	public boolean isDucking(Mazub alien) throws ModelException {
		return alien.isDucking();
	}

	@Override
	public void startDuck(Mazub alien) throws ModelException {
		try{ alien.startDuck();
		
		}
		catch (RuntimeException exc) {throw new ModelException("Cannot start Ducking!"); }	
	}

	@Override
	public void endDuck(Mazub alien) throws ModelException {
		try{ alien.endDuck();
		
		}
		catch (RuntimeException exc) {throw new ModelException("Cannot stop Ducking"); }	
	}
//
//	@Override
//	public void advanceTime(Mazub alien, double dt) throws ModelException {
//		if ( dt > 0 && dt <=0.2 )
//			alien.advanceTime(dt);
//	}

	@Override
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY, int[] targetTileCoordinate,
			int visibleWindowWidth, int visibleWindowHeight, int... geologicalFeatures) throws ModelException {
		if (targetTileCoordinate == null)
			throw new ModelException("Illegal target tile");
		if (targetTileCoordinate.length != 2)
			throw new ModelException("Illegal target tile");
		if (geologicalFeatures == null)
			throw new ModelException("Illegal geological features");
		Geological[] geoFeatures = new Geological[geologicalFeatures.length];
		
		if (geologicalFeatures.length>0){
		for (int index=0;index <geologicalFeatures.length;index++)
			geoFeatures[index] = Geological.getGeologicalWithNumber(geologicalFeatures[index]);
		}
		try {
		return new World(tileSize,nbTilesX,nbTilesY,targetTileCoordinate,visibleWindowWidth,visibleWindowHeight,
				geoFeatures);}
		catch (RuntimeException exc) {throw new ModelException("Not valid world");}
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminate();
		
	}

	@Override
	public int[] getSizeInPixels(World world) throws ModelException {
		int[] sizeInPixels = {world.getWorldWidth(),world.getWorldHeight()} ;
		return sizeInPixels;
	}

	@Override
	public int getTileLength(World world) throws ModelException {
		return world.getTileLength();
	}

	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY) throws ModelException {
		return world.getGeologicalFeature(pixelX, pixelY).getNumber();
	}

	@Override
	public void setGeologicalFeature(World world, int pixelX, int pixelY, int geologicalFeature) throws ModelException {
		Geological geological = Geological.AIR;
		for (Geological geologicals:Geological.values())
			if (geologicals.getNumber() == geologicalFeature)
				geological = geologicals;
		world.setGeologicalFeature(pixelX, pixelY, geological);
	}

	@Override
	public int[] getVisibleWindowDimension(World world) throws ModelException {
		int[] dimension = {world.getVisibleWindowWidth(),world.getVisibleWindowHeight()};
		return dimension;
	}
	
	@Override
	public int[] getVisibleWindowPosition(World world) throws ModelException {
		if (!isTeamSolution()) {
			try {
				int[] mazubPixelPosition = getPixelPosition(getMazub(world));
				return new int[]
					{ (int)Math.max(mazubPixelPosition[0]-100, 0),
					  (int)Math.max(mazubPixelPosition[1]-50, 0.0)};
			} catch (Exception exc) {
				throw new ModelException(exc);
			}
		}
		int[] position = {world.getVisibleWindowX(),world.getVisibleWindowY()};
		return position;
	}

	@Override
	public boolean hasAsGameObject(Object object, World world) throws ModelException {
		return world.hasAsGameObject((GameObject) object);
	}

	@Override
	public Set<Object> getAllGameObjects(World world) throws ModelException {
		//return world.getGameObjects();
		Set<Object> objecten = new HashSet<Object>();
		List<GameObject> list = new ArrayList<GameObject>(world.getGameObjects());
		int index;
		for (index=0; index <list.size();index++)
			objecten.add(list.get(index));
		return objecten;		
		
	
	}

	@Override
	public Mazub getMazub(World world) throws ModelException {
		return world.getPlayableMazub();
	}

	@Override
	public void addGameObject(Object object, World world) throws ModelException {
		if (object == null)
			throw new ModelException("Cannot add gameobject");
		if ((((GameObject) object).getPixelX() >= world.getWorldWidth()) || 
				(((GameObject) object).getPixelY() >= world.getWorldHeight()))
			throw new ModelException("Game object outside world boundaries");
		if ((((GameObject) object).getWorld()) != null)
			throw new ModelException("Game object already has world");
		for (GameObject gameObject: world.getGameObjects()) {
			if ((gameObject instanceof Mazub) && (! (object instanceof Plant))){
				if (((GameObject) object).collidesWith(gameObject.getPixelX(),gameObject.getPixelY(),
						gameObject.getCurrentSprite().getWidth(),gameObject.getCurrentSprite().getHeight())) { 
					throw new ModelException("Cannot add gameobject,");
				}
			}
			}			
		try{world.addGameObject((GameObject) object);}
		catch (RuntimeException exc) {throw new ModelException("Cannot add gameobject");}
		
	}

	@Override
	public void removeGameObject(Object object, World world) throws ModelException {
		try {
		world.removeGameObject((GameObject) object);} 
		catch (RuntimeException exc) {throw new ModelException("Cannot remove this object from this world");}
	}

	@Override
	public int[] getTargetTileCoordinate(World world) throws ModelException {
		return world.getTargetTile();
	}

	@Override
	public void setTargetTileCoordinate(World world, int[] tileCoordinate) throws ModelException {
		if (tileCoordinate == null)
			throw new ModelException("Illegal target tile");
		int[] copyTargetTile = tileCoordinate.clone();
		world.setTargetTile(copyTargetTile);
	}

	@Override
	public void startGame(World world) throws ModelException {
        if (world.getGameObjects().size() == 0) {
        	throw new ModelException("Cannot start game in this world.");
        	}
        else {
		world.startGame();	
        }
	}

	@Override
	public boolean isGameOver(World world) throws ModelException {
		if ((world.getPlayableMazub() == null) || (world.getPlayableMazub().isDead()))
				return true;
		if (world.reachedTargetTile()) {
			return true;
		}
		else{
		return false;
		}
	}

	@Override
	public boolean didPlayerWin(World world) throws ModelException {
//		if ((! this.isGameOver(world)) & (world.reachedTargetTile()))
		if (! world.isStarted())
			return false;
		if (world.getPlayableMazub() == null)
			return false;
		if (world.reachedTargetTile()) {
			return true;
		}
		else 
			return false;
	}

	@Override
	public void advanceWorldTime(World world, double dt) {
		if (Double.isNaN(dt)) 
			throw new ModelException("Illegal advance time");
		try { world.advanceTime(dt);
		}
		catch (IllegalArgumentException exc) {throw new ModelException("Illegal advance time "); }
	}

	@Override
	public void terminateGameObject(Object gameObject) throws ModelException {
		((GameObject) gameObject).terminate();
		
	}

	@Override
	public boolean isTerminatedGameObject(Object gameObject) throws ModelException {
		return ((GameObject) gameObject).isTerminated();
	}

	@Override
	public boolean isDeadGameObject(Object gameObject) throws ModelException {
		return ((GameObject) gameObject).isDead();
	}

	@Override
	public double[] getActualPosition(Object gameObject) throws ModelException {
		double[] position= {((GameObject) gameObject).getActualX(),((GameObject) gameObject).getActualY()};
		return position;
	}

	@Override
	public void changeActualPosition(Object gameObject, double[] newPosition) throws ModelException {
		if (newPosition == null) {
			throw new ModelException("New position is illegal");
		}
		if ((Double.isNaN(newPosition[0])) | (Double.isNaN(newPosition[1]))) {
			throw new ModelException("New position is illegal");
		}
		
		if (newPosition.length >2) {
			throw new ModelException("New position is illegal");
		}
		if ((newPosition[0] > ((((GameObject) gameObject).getPixelWidth())/100)+1) |( newPosition[1] > 
		(((GameObject) gameObject).getPixelHeight())/100)|
				(newPosition[0] < 0 )|(newPosition[1] < 0)) {
			throw new ModelException("New position is illegal");
		}

		else {
			try {
		((GameObject) gameObject).setActualX(newPosition[0]);
		((GameObject) gameObject).setActualY(newPosition[1]);
			}
			catch (RuntimeException exc) {throw new ModelException("New position is illegal"); }
		
	}
	}
	
	

	@Override
	public int[] getPixelPosition(Object gameObject) throws ModelException {
		int[] position = {((GameObject) gameObject).getPixelX(),((GameObject) gameObject).getPixelY()};
		return position;
	}

	@Override
	public int getOrientation(Object gameObject) throws ModelException {
		 ;
		if (((GameObject) gameObject).getOrientation() == "right")
			return 1;
		if (((GameObject) gameObject).getOrientation() == "left")
			return -1;
		if (((GameObject) gameObject).getOrientation() == "up")
			return 2;
		else
			return 0;
	}

	@Override
	public double[] getVelocity(Object gameObject) throws ModelException {
		double[] velocity = {((GameObject) gameObject).getHorizontalVelocity(),
				((GameObject) gameObject).getVerticalVelocity()};
		return velocity;
		
	}

	@Override
	public World getWorld(Object object) throws ModelException {
		if (object instanceof School)
			return ((School) object).getWorld();
		return ((GameObject) object).getWorld();
		
	}

	@Override
	public int getHitPoints(Object object) throws ModelException {
		return ((GameObject) object).getHitPoints();
	}

	@Override
	public Sprite[] getSprites(Object gameObject) throws ModelException {
		return ((GameObject) gameObject).getSprites();
	}
	@Override
	public Sprite getCurrentSprite(Object gameObject) throws ModelException {
		if (!isTeamSolution()) {
			if (gameObject instanceof Mazub) {
				return JumpingAlienSprites.DEFAULT_MAZUB_SPRITE;
			} else if (gameObject instanceof Sneezewort || gameObject instanceof Skullcab) {
				return JumpingAlienSprites.DEFAULT_PLANT_SPRITE; 
			} else if (gameObject instanceof Slime) {
				return JumpingAlienSprites.DEFAULT_SLIME_SPRITE;
			} else if (gameObject instanceof Shark) {
				return JumpingAlienSprites.DEFAULT_SHARK_SPRITE;
			}
		}
		return ((GameObject) gameObject).getCurrentSprite();
	}
	
	
	@Override
	public void advanceTime(Object gameObject, double dt) throws ModelException {
		if (Double.isNaN(dt))
			throw new ModelException("Illegal advance time");
		try { ((GameObject) gameObject).advanceTime(dt);
		}
		catch (IllegalArgumentException exc) {throw new ModelException("Illegal advance time"); }

		
	}

	@Override
	public double[] getAcceleration(Object gameObject) throws ModelException {
		return new double[] {((GameObject) gameObject).getHorizontalAcceleration(),
				((GameObject) gameObject).getVerticalAcceleration()};
	}

	@Override
	public Sneezewort createSneezewort(int pixelLeftX, int pixelBottomY, Sprite... sprites) throws ModelException {
		if ((sprites == null) ||  (sprites.length != 2)  || (sprites[0] == null) || (sprites[1] == null))
			throw new ModelException("Sneezewort has no valid sprites");
		
		return new Sneezewort(pixelLeftX,pixelBottomY,sprites);
	}

	@Override
	public Skullcab createSkullcab(int pixelLeftX, int pixelBottomY, Sprite... sprites) throws ModelException {
		return new Skullcab(pixelLeftX,pixelBottomY,sprites);
	}

	@Override
	public Slime createSlime(long id, int pixelLeftX, int pixelBottomY, School school, Sprite... sprites)
			throws ModelException {
		if (Long.valueOf(id) == null)
			throw new ModelException("Not legal slime");
		if (id <0)
			throw new ModelException("Not legal ID");
		if (sprites == null)
			throw new ModelException("Not legal slime");
		if (sprites.length != 2)
			throw new ModelException("Not legal slime");
		if ((sprites[0] == null) | (sprites[1] == null))
			throw new ModelException("Not legal slime");
		try {
			return new Slime(id,pixelLeftX,pixelBottomY,school,sprites);
			} catch (RuntimeException exc) {
			throw new ModelException("Cannot create slime");}
	}

	@Override
	public long getIdentification(Slime slime) throws ModelException {
		return slime.getID();
	}

	@Override
	public School createSchool(World world) throws ModelException {
		try{ return new School(world);} catch (RuntimeException exc)
		{throw new ModelException("Cannot create school in this world");}
	}

	@Override
	public boolean hasAsSlime(School school, Slime slime) throws ModelException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<? extends Slime> getAllSlimes(School school) {
		// TODO Auto-generated method stub
		return  school.getAllSlimes();
	}

	@Override
	public void addAsSlime(School school, Slime slime) throws ModelException {
		if (slime == null)
			throw new ModelException("Cannot add slime");
		if ((slime.isTerminated()) || (school.isTerminated() ))
			throw new ModelException("Cannot add slime, school or slime is terminated");
		try {
			school.addSlime(slime);
		} catch (RuntimeException exc) {
			throw new ModelException("Cannot add slime");}
	}

	@Override
	public void removeAsSlime(School school, Slime slime) throws ModelException {
		// TODO Auto-generated method stub
		try {school.removeSlime(slime);} catch (RuntimeException exc) 
		{throw new ModelException("Cannot remove Slime from this school");}
		
	}

	@Override
	public void switchSchool(School newSchool, Slime slime) throws ModelException {
		try{slime.switchSchool(newSchool);} catch (RuntimeException exc) 
		{ throw new ModelException("Cannot switch from school");}
		
	}

	@Override
	public School getSchool(Slime slime) throws ModelException {
		// TODO Auto-generated method stub
		return slime.getSchool();
	}
	@Override
	public Shark createShark(int pixelLeftX, int pixelBottomY, Sprite... sprites) throws ModelException {
		try{
			return new Shark(pixelLeftX,pixelBottomY,sprites);
		}
		catch (RuntimeException exc) {throw new ModelException("Cannot create this Shark"); } 
		
	}

	@Override
	public boolean isLateTeamSplit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<School> getAllSchools(World world) throws ModelException {
		// TODO Auto-generated method stub
		return world.getSchools();
	}

	@Override
	public void cleanAllSlimeIds() {
		// TODO Auto-generated method stub
		Slime.clearSlimeIDs();
	}

	@Override
	public void terminateSchool(School school) throws ModelException {
		school.terminate();
	}

	@Override
	public boolean hasImplementedWorldWindow() {
		// TODO Auto-generated method stub
		return false;
	}	

}
