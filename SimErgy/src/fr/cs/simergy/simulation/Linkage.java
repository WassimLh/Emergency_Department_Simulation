package fr.cs.simergy.simulation;

public class Linkage {
	public final Link pred(){
		return PRED instanceof Link ? (Link) PRED:null;
	}
	
	public final Link suc(){
		return SUC instanceof Link ? (Link) SUC:null;
	}
	
	public final Linkage prev(){
		return PRED;
	}
	
	Linkage PRED, SUC;
}
