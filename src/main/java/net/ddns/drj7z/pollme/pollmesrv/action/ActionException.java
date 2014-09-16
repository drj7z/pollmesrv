/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action;

/**
 * @author emaschietto
 *
 */
public class ActionException extends Exception {

  private static final long serialVersionUID= -4122616727348373398L;

  /**
   * @param message
   */
  public ActionException (String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public ActionException (Throwable cause)
  {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public ActionException(String message, Throwable cause)
  {
    super(message, cause);
  }

}
