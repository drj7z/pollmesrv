/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv;

import java.net.Socket;

/**
 * @author emaschietto
 *
 */
public interface ServerThreadBuilder {

  /**
   * Build a server thread bind to specified socket.
   * @param socket socket which the server thread binds to.
   * @return a server thread bind to specified socket.
   */
  public Runnable build (Socket socket) throws ServerThreadBuilderException;

}
