package fr.cs.simergy.materialresources;

/**
 * A RadioRoom is a Room where patients can have
 * a radiography exam
 */
public class RadioRoom extends Room {
	
	//Attributes
	private double cost = 1000;
	
	//Constructor
	/**
	 * @see Room#Room()
	 */
	public RadioRoom(){
		super();
	}
	/**
	 * @see Room#Room(String)
	 */
	public RadioRoom(String s){
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
