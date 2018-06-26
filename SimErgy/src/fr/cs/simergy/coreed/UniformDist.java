package fr.cs.simergy.coreed;

/**
 * This class provides a uniform probability distribution
 * given two parameters.
 */
public class UniformDist extends RandomDist {
	
	
	private static final long serialVersionUID = -3126998878902358587L;
	//Attributes
	/**
	 * Lower Bound of the interval 
	 */
	private double lowerBound;
	/**
	 * Upper Bound of the interval
	 */
	private double upperBound;
	
	//Constructor
	/**
	 * Creates a uniform distribution with its given bound
	 * @param n lower bound
	 * @param p upper bound
	 */
	public UniformDist(double n, double p){
		this.lowerBound = n;
		this.upperBound = p;
	}
	
	//Methods
	/**
	 * Gives a sampling from the uniform distribution
	 * @return sample of the uniform distribution
	 */
	public double getSample(){
		return lowerBound + nextDouble()*(upperBound - lowerBound);
	}
}
