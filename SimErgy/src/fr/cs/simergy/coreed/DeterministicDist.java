package fr.cs.simergy.coreed;
/**
 * This class provides a deterministic probability distribution
 * given one parameter.
 */
public class DeterministicDist extends RandomDist {
	
	private static final long serialVersionUID = -3126998878902358587L;
	//Attributes
	/**
	 * Parameter delta of the deterministic distribution
	 */
	private double delta;
	
	//Constructor
	public DeterministicDist(double delta){
		this.delta = delta;
	}
	
	//Methods
	/**
	 * Gives a sampling from the deterministic distribution
	 * @return sample of the deterministic distribution
	 */
	@Override
	public double getSample(){

		return delta;
	}
}
