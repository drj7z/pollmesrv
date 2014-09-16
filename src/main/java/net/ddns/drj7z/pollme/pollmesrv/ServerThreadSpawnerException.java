/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv;

/**
 * @author emaschietto
 *
 */
public class ServerThreadSpawnerException extends Exception {

  private static final long serialVersionUID= -835539760850065471L;



  /**
   * @param message
   */
  public ServerThreadSpawnerException(String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public ServerThreadSpawnerException(Throwable cause)
  {
    super(cause);
  }

  /**
   * @param message
   * @param cause
   */
  public ServerThreadSpawnerException(String message, Throwable cause)
  {
    super(message, cause);
  }

}
