package fr.cs.simergy.simulation;
/**
 * Defines the queues that we use in our system 
 *
 */
public class Head extends Linkage {
	/**
	 * Head constructor
	 */
	public Head(){
		PRED = SUC = this;
	}
	/**
	 * Returns a reference to the first member of the list
	 * (null if the list is empty)
	 */
	public final Link first(){
		return suc();
	}
	/**
	 * Returns a reference to the last member of the list
	 * (null if the list is empty)
	 */
	public final Link last(){
		return pred();
	}
	/**
	 * Returns trye if the list has no members, null otherwise
	 */
	public final boolean empty(){
		return  SUC == this;
	}
	/**
	 * Returns the number of members of the list
	 * (null if the list is empty)
	 */
	public final int cardinal(){
		int i = 0;
		for(Link ptr = first(); ptr != null; ptr = ptr.suc())
			i++;
		return i;
	}
	/**
	 * Removes all members from the list
	 */
	public final void clear(){
		while(first() != null)
			first().out();
	}

}
