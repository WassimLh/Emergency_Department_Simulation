package fr.cs.simergy.clui;
/**
 * Class that throws a file reader exception related to CLUI with an appropriate message each time
 * it is thrown.
 */
public class FileReaderException extends Exception {
	
	private static final long serialVersionUID = 2018998205625613L;

	public FileReaderException(){
		super("Your file doesn't fit requirements. Please see 'initconfig.txt' to see how a use case can be defined");
	}
	
	public FileReaderException(String s){
		super(s);
	}
}
