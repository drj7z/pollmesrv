/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception;

/**
 * @author emaschietto
 *
 */
public class PersistenceException extends Exception {

  private static final long serialVersionUID= 1978767517652697996L;



  /**
   * @param message
   */
  public PersistenceException (String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public PersistenceException (Throwable cause)
  {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public PersistenceException (String message, Throwable cause)
  {
    super(message,cause);
  }

}
