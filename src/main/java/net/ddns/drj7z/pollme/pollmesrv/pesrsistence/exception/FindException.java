/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception;

/**
 * @author emaschietto
 *
 */
public class FindException extends PersistenceException {

  private static final long serialVersionUID= 5067486407956820358L;



  /**
   * @param message
   */
  public FindException(String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public FindException(Throwable cause)
  {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public FindException(String message, Throwable cause)
  {
    super(message, cause);
  }

}
