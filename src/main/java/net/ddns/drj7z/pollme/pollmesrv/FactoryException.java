/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv;

/**
 * Exception thrown by factories.
 *
 * @author emaschietto
 * @version 1.0.0
 *
 */
public class FactoryException extends RuntimeException {

  private static final long serialVersionUID= 1453311433407851783L;

  /**
   * @param arg0
   */
  public FactoryException(String arg0)
  {
    super(arg0);
  }

  /**
   * @param arg0
   * @param arg1
   */
  public FactoryException(String arg0, Throwable arg1)
  {
    super(arg0,arg1);
  }

}
