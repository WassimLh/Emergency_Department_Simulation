package fr.cs.simergy.materialresources;

/**
 * A SchockRoom is a Room where L1 and L2 patients
 * are first visited by the physician
 */
public class ShockRoom extends Room {
	
	
	private double cost = 50;
	
	//Constructor
	/**
	 * @see Room#Room()
	 */
	public ShockRoom(){
		super();
	}
	/**
	 * @see Room#Room(String)
	 */
	public ShockRoom(String s){
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
