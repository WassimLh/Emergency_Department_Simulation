package fr.cs.simergy.materialresources;

/**
 * A BloodTestRoom is a Room where patients can have
 * a BloodTestgraphy exam
 */
public class BloodTestRoom extends Room {
	
	private double cost = 50;
	
	//Constructor
	/**
	 * @see Room#Room()
	 */
	public BloodTestRoom(){
		super();
	}
	/**
	 * @see Room#Room(String)
	 */
	public BloodTestRoom(String s){
		super(s);
	}
	/**
	 * @see Room#getCost()
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * @see Room#setCost(double)
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
}
