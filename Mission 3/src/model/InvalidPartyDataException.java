/**
 * @Title: InvalidPartyDataException.java
 * @Package model
 * @version V1.0
 */

package model;

/**
  * @ClassName: InvalidPartyDataException
  * @Description: handle invalid party data, confirm the seats and votes are valid number.
  * @author Haoyang SHi
  * @date 2020
  *
  */

public class InvalidPartyDataException extends java.lang.Exception{
	/**
	  * InvalidPartyDataException. 
	  * <p>Description: empty constructor </p>
	  */
	public InvalidPartyDataException() {
		super();
	}
	/**
	
	  * InvalidPartyDataException. 
	  * <p>Title: </p>
	  * <p>Description: constructor that takes a string as argument</p>
	  * @param message
	  */
	public InvalidPartyDataException(String message) {
		super(message);
	}
	/**
	
	  * InvalidPartyDataException. 
	  * <p>Description: constructor that takes a string and a throwable cause as argument</p>
	  * @param message
	  * @param cause
	  */
	public InvalidPartyDataException(String message, Throwable cause) {
		super(message,cause);
	}
	/**
	
	  * InvalidPartyDataException. 
	  * <p>Description: constructor that takes a string and a throwable cause and boolean enableSuppression,boolean writableStackTrace as argument</p>
	  * @param message
	  * @param cause
	  * @param enableSuppression
	  * @param writableStackTrace
	  */
	public InvalidPartyDataException(String message, Throwable cause,boolean enableSuppression,boolean writableStackTrace){
		super(message,cause,enableSuppression,writableStackTrace);
	}
	/**
	
	  *  InvalidPartyDataException. 
	  * <p>Description: constructor that takes a throwable cause as argument</p>
	  * @param cause
	  */
	public InvalidPartyDataException(Throwable cause) {
		super(cause);
	}

	
	
	

	
	
	
	
	
	
}
