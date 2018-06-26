package fr.cs.simergy.materialresources;

/**
 * A BoxRoom is a Room where L3, L4 and L5 patients
 * are first visited by the physician
 */
public class BoxRoom extends Room {
	
	private double cost = 50;
	
	//Constructor
	/**
	 * @see Room#Room()
	 */
	public BoxRoom(){
		super();
	}
	/**
	 * @see Room#Room(String)
	 */
	public BoxRoom(String s){
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
