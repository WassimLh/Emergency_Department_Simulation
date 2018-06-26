package fr.cs.simergy.coreed;
/**
 * This class provides an exponential probability distribution
 * given one parameter.
 */
public class ExponentialDist extends RandomDist {
	
	private static final long serialVersionUID = -3126998878902358587L;
	//Attributes
	/**
	 * Parameter lambda of the exponential distribution
	 */
	private double lambda;
	
	//Constructor
	public ExponentialDist(double lambda){
		this.lambda = lambda;
	}
	
	//Methods
	/**
	 * Gives a sampling from the exponential distribution
	 * @return sample of the exponential distribution
	 */
	public double getSample(){
		return -Math.log(nextDouble())/lambda;  
	}
}
