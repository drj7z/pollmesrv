/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication.exception;

/**
 * @author emaschietto
 *
 */
public class CommunicationException extends Exception {

  private static final long serialVersionUID = -239502959913955318L;

  public CommunicationException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public CommunicationException(String arg0) {
    super(arg0);
  }

  public CommunicationException(Throwable arg0) {
    super(arg0);
  }

}
