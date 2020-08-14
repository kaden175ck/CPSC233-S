package model;
import java.lang.Exception;
public class InvalidSetupDataException extends Exception{

	public InvalidSetupDataException() {
		
	}
	public InvalidSetupDataException(String msg) {
		super(msg);
	}
	public InvalidSetupDataException(Throwable cause) {
		super(cause);
	}
	public InvalidSetupDataException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
