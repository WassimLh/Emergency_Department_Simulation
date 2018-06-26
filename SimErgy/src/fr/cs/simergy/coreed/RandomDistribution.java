package fr.cs.simergy.coreed;

import java.util.Random;
/**
 * This class defines many specific probability distributions.
 * It was made for a general purpose and has not been used in our program.
 *
 */

public class RandomDistribution extends Random {

	private static final long serialVersionUID = -3126998878902358586L;

	public RandomDistribution() { 
		super(); 
	}
	
    public RandomDistribution(long seed) { 
    	super(seed); 
    }
    
    public boolean draw(double a) {        
    	return a < nextDouble();    
    }
    
    public int randInt(int a, int b) {        
    	if (b < a)            
    		error("randInt: Second parameter is lower than first parameter");        
    	return (int) (a + nextDouble()*(b - a + 1));    
    	}
    
    public double uniform(double a, double b) {        
    	if (b <= a)            
    		error("uniform: Second parameter is not greater than first parameter");        
    	return a + nextDouble()*(b - a);    
    }
    
    public double normal(double a, double b) {        
    	return a + b*nextGaussian();    
    }
    
    public double negexp(double a) {        
    	if (a <= 0)            
    		error("negexp: First parameter is lower than zero");        
    	return -Math.log(nextDouble())/a;    
    }
    
    public int poisson(double a) {        
    	double limit = Math.exp(-a), prod = nextDouble();        
    	int n;        
    	for (n = 0; prod >= limit; n++)            
    		prod *= nextDouble();        
    	return n;    
    }

    public double erlang(double a, double b) {        
    	if (a <= 0)            
    		error("erlang: First parameter is not greater than zero");        
    	if (b <= 0)            
    		error("erlang: Second parameter is not greater than zero");        
    	long bi = (long) b, ci;        
    	if (bi == b)            
    		bi--;       
    	double sum = 0;        
    	for (ci = 1; ci <= bi; ci++)            
    		sum += Math.log(nextDouble());        
    	return -(sum + (b - (ci-1))*Math.log(nextDouble()))/(a*b);    
    }
    
    public int discrete(double[] a) {        
    	double basic = nextDouble();        
    	int i;        
    	for (i = 0; i < a.length; i++)            
    		if (a[i] > basic)                
    			break;        
    	return i;    
    }
    
    public double linear(double[] a, double[] b) {        
    	if (a.length != b.length)            
    		error("linear: arrays have different length");        
    	if (a[0] != 0.0 || a[a.length-1] != 1.0)            
    		error("linear: Illegal value in first array");        
    	double basic = nextDouble();        
    	int i;        
    	for (i = 1; i < a.length; i++)             
    		if (a[i] >= basic)                 
    			break;        
    	double d = a[i] - a[i-1];        
    	if (d == 0.0)            
    		return b[i-1];        
    	return b[i-1] + (b[i]-b[i-1])*(basic-a[i-1])/d;    
    }
    
    public int histd(double[] a) {        
    	double sum = 0.0;        
    	int i;        
    	for (i = 0; i < a.length; i++)            
    		sum += a[i];        
    	double weight = nextDouble() * sum;        
    	sum = 0.0;        
    	for (i = 0; i < a.length - 1; i++) {            
    		sum += a[i];            
    		if (sum >= weight)                
    			break;        
    	}        
    	return i;    
    }
    
    private static void error(String msg) {        
    	throw new RuntimeException(msg);    
    }
    
}
