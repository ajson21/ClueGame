/**
 * 
 * @author ajson, jasonyu
 * Exception in case the formatting of the files is off or otherwise broken. 
 */
package clueGame;

/**
 * Custom exception used for checking if a bad file was used for configuration
 * 
 * @author ajson, jasonyu
 *
 */
public class BadConfigFormatException extends RuntimeException{
	
	public BadConfigFormatException(){
		
		super("Error: Bad Configuration");
		
	}
	
	public BadConfigFormatException(String message){
		
		super(message);
		
	}

}
