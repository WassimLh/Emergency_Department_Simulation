package fr.cs.simergy.materialresources;

/**
 * A WaitingRoom is a Room where patients wait for
 * different services. This class has not been used in our program.
 */
public class WaitingRoom extends Room {
	
	private double cost;
	
	//Constructor
	/**
	 * @see Room#Room()
	 */
	public WaitingRoom(){
		super();
	}
	/**
	 * @see Room#Room(String)
	 */
	public WaitingRoom(String s){
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
