package fr.cs.simergy.clui;
/**
 * Class that defines a command exception related to CLUI with an appropriate message each time
 * it is thrown.
 */
public class CommandException extends Exception {
	
	
	private static final long serialVersionUID = 2018998205625610L;

	public CommandException(){
		super("Arguments should be revisited");
	}
	
	public CommandException(String s){
		super(s);
	}
}
