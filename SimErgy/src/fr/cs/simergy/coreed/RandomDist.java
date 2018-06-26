package fr.cs.simergy.coreed;

import java.util.Random;
/**
 * This class defines the general behavior of other 
 * specific probability distributions classes
 *
 */
public abstract class RandomDist extends Random {
	private static final long serialVersionUID = -3126998878902358587L;
	/**
	 * Gets a sample from a given probability distribution
	 * @return sample of a given probability distribution
	 */
	public abstract double getSample();
	
	protected void error(String msg) {        
    	throw new RuntimeException(msg);    
    }
}
