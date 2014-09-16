/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception;

/**
 * @author emaschietto
 *
 */
public class InsertException extends PersistenceException {

  /**
   *
   */
  private static final long serialVersionUID= -882447700954813666L;

  /**
   * @param message
   */
  public InsertException(String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public InsertException(Throwable cause)
  {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public InsertException(String message, Throwable cause)
  {
    super(message, cause);
  }

}
