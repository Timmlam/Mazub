package jumpingalien.model;

import java.util.HashSet;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Value;

/**
 * An enumeration introducing different geological features for a gaming world.
 * 
 * @version 3.0
 * 
 * @author Timm Lamberechts
 * @author Maarten Volkaerts
 *
 */
@Value
public enum Geological {
	
	AIR(0,0), SOLID_GROUND(1,1),WATER(2,0),MAGMA(3,0),
	ICE(4,1),GAS(5,0);
	
	/**
	 * Initialize this geological feature with the given number and passable state.
	 * @param number
	 * 		The number of the geological feature.
	 * @param passable
	 * 		The passable state of this geological feature(0 is passable, 1 is impassable).
	 * @post The number of the geological feature is equal to the given number
	 * 		| new.getNumber() = number
	 * @post The passable state of the geological feature is equal to the passable state
	 * 		| new.getPassable() = passable
	 */
	@Raw
	private Geological(int number,int passable) {
		this.passable = passable;
		this.number = number;
	}

	/**
	 * Return the number of this geological feature.
	 */
	@Basic
	@Raw
	@Immutable
	public int getNumber() {
		return number;
	}
	/**
	 * Return the passable state of this geological feature.
	 */
	public int getPassable() {
		return passable;
	}
	/**
	 * Variable registering the number of this geological feature.
	 */
	private final int number;
	
	/**
	 * Variable registering the passable state of this geological feature.
	 */
	private final int passable;
	
	/**
	 * Return (a set of) all impassable geological features.
	 */
	public static  HashSet<Geological> getAllImpassable(){
		HashSet<Geological> allImpassable = new HashSet<>();
		for (Geological geologicals:Geological.values())
			if (geologicals.getPassable() == 1)
				allImpassable.add(geologicals);
		return allImpassable;
	}
	
	/**
	 * Return (a set of) all passable geological features.
	 */
	public static HashSet<Geological> getAllPassable(){
		HashSet<Geological> allPassable = new HashSet<>();
		for (Geological geologicals:Geological.values())
			if (geologicals.getPassable() == 1)
				allPassable.add(geologicals);
		return allPassable;
	}
	
	/**
	 * Return the geological feature who's number is corresponding to the given number.
	 * @param number
	 * 		The given number to correspond with.
	 * @return
	 * 		| for (Geological geologicals:Geological.values())
 	 *		|	if (geologicals.getNumber() == number)
	 *		|	then result == geologicals
	 */
	public static Geological getGeologicalWithNumber(int number) {
		Geological geo = AIR;
		for (Geological geologicals:Geological.values())
			if (geologicals.getNumber() == number)
				geo = geologicals;
	return geo;
	}
}
