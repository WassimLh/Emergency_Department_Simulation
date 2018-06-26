package fr.cs.simergy.materialresources;

/**
 * A MRIRoom is a Room where patients can have
 * a MRIgraphy exam
 */
public class MRIRoom extends Room {
	
	
	//Attributes
	private double cost = 10000;
	
	//Constructor
	/**
	 * @see Room#Room()
	 */
	public MRIRoom(){
		super();
	}
	/**
	 * @see Room#Room(String)
	 */
	public MRIRoom(String s){
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
