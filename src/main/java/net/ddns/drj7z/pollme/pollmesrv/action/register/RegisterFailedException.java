/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.action.register;

import net.ddns.drj7z.pollme.pollmesrv.action.ActionException;

/**
 * @author emaschietto
 *
 */
public class RegisterFailedException extends ActionException {

  private static final long serialVersionUID= 725308882876675915L;

  /**
   * @param message
   */
  public RegisterFailedException (String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public RegisterFailedException (Throwable cause)
  {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public RegisterFailedException (String message, Throwable cause)
  {
    super(message,cause);
  }

}
