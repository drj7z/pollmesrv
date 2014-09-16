/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv;

/**
 * @author emaschietto
 *
 */
public class ServerException extends RuntimeException {

  private static final long serialVersionUID= -3664083652429297950L;



  /**
   * @param message
   */
  public ServerException (String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public ServerException (Throwable cause)
  {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public ServerException (String message, Throwable cause)
  {
    super(message,cause);
  }

}
