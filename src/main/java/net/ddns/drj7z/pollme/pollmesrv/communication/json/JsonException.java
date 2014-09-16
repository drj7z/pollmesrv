/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication.json;

/**
 * @author emaschietto
 *
 */
public class JsonException extends Exception {

  private static final long serialVersionUID= -1752313368617283243L;



  /**
   * @param message
   */
  public JsonException (String message)
  {
    super(message);
  }

  /**
   * @param message
   * @param cause
   */
  public JsonException (String message, Throwable cause)
  {
    super(message, cause);
  }

}
