/**
 * 
 * @author ajson, jasonyu
 * Exception in case the formatting of the files is off or otherwise broken. 
 */
package clueGame;

public class BadConfigFormatException extends RuntimeException{
	
	public BadConfigFormatException(){
		
		super("Error: Bad Configuration");
		
	}
	
	public BadConfigFormatException(String message){
		
		super(message);
		
	}

}
