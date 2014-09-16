/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.pesrsistence.exception;

/**
 * @author emaschietto
 *
 */
public class NoMatchingItems extends PersistenceException {

  private static final long serialVersionUID= 5315678119166367338L;



  public NoMatchingItems (String message)
  {
    super(message);
  }

  public NoMatchingItems (Throwable cause)
  {
    super(cause);
  }

  public NoMatchingItems (String message, Throwable cause)
  {
    super(message,cause);
  }

}
