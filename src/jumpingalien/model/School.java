package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of Schools consisting of Slimes that is part of a gaming world.
 * @invar The world of each School must be a valid world for any School.
 * 		| isValidWorld(getWorld())
 * 
 * @version 3.0
 * 
 * @author Timm Lamberechts
 * @author Maarten Volkaerts
 */
public class School<T extends Slime> {
	/**
	 * Initialize this School with the given world.
	 * @param world
	 * 		The world of this new School.
	 * @effect
	 * 		| this.setWorld(world)
	 * @effect 
	 * 		| world.addSchool(this)
	 */
	public School(World world) {
		this.setWorld(world);
		if (world != null)
			world.addSchool(this);
	}
	
	/**
	 * Set the type of slime of this School to the given slime.
	 * @param slime
	 * 		The new slime of this School.
	 * @post
	 * 		| new.getTypeOfSlime() = slime
	 */
	public void setTypeOfSlime(T slime) {
		this.typeOfSlime= slime;
	}
	
	/**
	 * Return the type of the slime in this School.
	 */
	@Basic @Raw
	public T getTypeOfSlime() {
		return this.typeOfSlime;
	}
	
	/**
	 * Variable registering the type of the slime in this School.
	 */
	T typeOfSlime;
	
	/**
	 * Add the given slime to this School.
	 * @param slime
	 * 		The slime to add.
	 * @effect
	 * 		| slimes.add(slime)
	 * @effect 
	 * 		| slime.school = this
	 * @throws RuntimeException
	 * 		| (slime.getSchool() != null)
	 */
	public void addSlime(T slime) throws RuntimeException {
		
		if (slime!= null) {
			if (slime.getSchool() != null) {
				throw new RuntimeException();
			}
			slimes.add(slime);
			slime.school = (School<Slime>) this;
		}
	}
	
	/**
	 * Remove the given slime from this School.
	 * @param slime
	 * 		The slime to remove.
	 * @effect 
	 * 		| this.slimes.remove(slime)
	 * @effect 
	 * 		| slime.setSchool(null)
	 * @throws RuntimeException
	 * 		| (! this.hasAsSlime(slime))
	 */
	public void removeSlime(T slime) throws RuntimeException {
		if (this.hasAsSlime(slime)) {
			this.slimes.remove(slime);
			slime.setSchool(null);
		}
		else
			throw new RuntimeException();
	}

	/**
	 * Terminate this School.
	 * @effect
	 * 		| if (! this.isTerminated()) then
	 *		| 	this.isTerminated = true and 
	 *		| 	his.world = null
	 */
	public void terminate() {
		if (! this.isTerminated())
			this.isTerminated = true;
			this.world = null;
	}

	/**
	 * Boolean checking whether the school is terminated. 
	 * @return true if the school is terminated
	 */
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	/**
	 * Boolean registering whether the school is terminated.
	 */
	private boolean isTerminated = false;
	

	/**
	 * Checks whether this School has the given slime as one of is slimes.
	 * @param slime
	 * 		The slime to check.
	 * @return
	 * 		| result == (slimes.contains(slime))
	 */
	public boolean hasAsSlime(T slime) {
		if (slime != null) {
			if (slimes.contains(slime))
				return true;
		}
		return false;
	}
	
	/**
	 * Return (a list of) all slimes of this School.
	 */
	public List<T> getAllSlimes(){
		List<T> allSlimes = new ArrayList<>();
		this.slimes.stream().forEach((e) -> allSlimes.add(e));
		return allSlimes;
	}
	
	/**
	 * TreeSet registering all the slimes of this School.
	 */
	public TreeSet<T> slimes = new TreeSet<>();
	
	/**
	 * Set the world of this School to the given world.
	 * @param world
	 * 		The new world of this School.
	 * @post
	 * 		| new.getWorld() == world
	 */
	public void setWorld(World world) {
		this.world = world;
		
	}
	/**
	 * Return the world of this School.
	 */
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}
	/**
	 * Variable registering the World of the School.
	 */
	public World world;
	
}
