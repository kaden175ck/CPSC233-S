package model;

/**
  * @ClassName: PollFullException
  * @Description: this class handle poll full exception and make sure that user not add a party to a full poll.
  * @author Yuan Liu
  * @date 2020
  *
  */
public class PollFullException extends Exception {

	/**
	
	  * PollFullException. 
	  * <p>Description: empty constructor</p>
	  */
	public PollFullException() {
		// TODO Auto-generated constructor stub
	}

	/**
	
	  * PollFullException. 
	  * <p>Description: constructor that takes a string as argument</p>
	  * @param message
	  */
	public PollFullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	
	  * PollFullException. 
	  * <p>Description:constructor that takes a throwable cause as argument </p>
	  * @param cause
	  */
	public PollFullException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	
	  * PollFullException. 
	  * <p>Description: constructor that takes a string and a throwable cause as argument</p>
	  * @param message
	  * @param cause
	  */
	public PollFullException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	
	  * PollFullException. 
	  * <p>Description: constructor that takes a string and a throwable cause and boolean enableSuppression,boolean writableStackTrace as argument</p>
	  * @param message
	  * @param cause
	  * @param enableSuppression
	  * @param writableStackTrace
	  */
	public PollFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
