package fr.cs.simergy.simulation;
/**
 * Defines the general behavior of objects that can feed
 * a Head, such as human or material resources
 *
 */
public class Link extends Linkage {
	/**
	 * Removes a Link object (a resource) from the queues of which
	 * it is a member. It has no effect if the Link object has no membership
	 */
	public final void out(){
		if (SUC != null){
			SUC.PRED = PRED;
			PRED.SUC = SUC;
			SUC = PRED = null;
		}
	}
	
	public final void follow(Linkage ptr){
		out();
		if (ptr != null && ptr.SUC != null){
			PRED = ptr;
			SUC = ptr.SUC;
			SUC.PRED = ptr.SUC = this;
		}
	}
	
	public final void precede(Linkage ptr){
		out();
		if (ptr != null && ptr.SUC != null){
			SUC = ptr;
			PRED = ptr.PRED;
			PRED.SUC = ptr.PRED = this;
		}
	}
	/**
	 * Removes the Link object from the lists of which it is a member
	 * and inserts it as the last member of the given Head parameter
	 * @param s Head in which we insert the Link object
	 */
	public final void into(Head s){
		precede(s);
	}
	
//	public final void intoS(Head s){
//		out();
//		if (s != null && s.SUC != null){
//			SUC = s; // SUC 
//			PRED = s.PRED; // s.PRED is the last member of s
//			PRED.SUC = s.PRED = this; // PRED.SUC successor of the last member of s
//									// s.PRED is the last member of
//			System.out.println(SUC);
//			while (s.PRED.PRED.getSeverityLevel() > s.PRED.getSeverityLevel()){
//				Linkage copy = s.PRED;
//				// PRED.SUC = s.PRED = s.PRED.PRED;
//				// PRED.PRED.SUC = s.PRED.PRED = this;
//				PRED.SUC = s.PRED = s.PRED.PRED;
//				s.PRED.PRED = this;
//				System.out.println(s.PRED);
//				System.out.println(s.PRED.PRED);
//				System.out.println(SUC);
//				System.out.println(PRED);
//			}
//		}
//	}
}


