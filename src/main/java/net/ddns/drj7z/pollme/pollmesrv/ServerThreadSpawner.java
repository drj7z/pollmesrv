/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv;

import java.net.Socket;

/**
 * @author emaschietto
 *
 */
public interface ServerThreadSpawner {

  /**
   * Spawn a server thread for <code>socket</code>.
   * @param socket socket which the server thread binds to.
   * @throws ServerThreadSpawnerException if cannot spwan the server thread.
   */
  public void spawn (Socket socket) throws ServerThreadSpawnerException;

}
