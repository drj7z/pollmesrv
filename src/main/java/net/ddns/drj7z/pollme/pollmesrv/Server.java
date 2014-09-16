/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv;

import java.io.IOException;
import java.net.ServerSocket;

import net.ddns.drj7z.pollme.pollmesrv.support.Injected;
import net.ddns.drj7z.pollme.pollmesrv.support.WireLogger;

import org.slf4j.Logger;

/**
 * @author emaschietto
 *
 */
public class Server {

  /**
   * Default listening server port.
   */
  public static final int DEFAULT_SERVER_PORT= 1171;



  /**
   * Server socket (binds to {@link #serverPort}).
   */
  private ServerSocket serverSocket;

  @Injected
  @WireLogger
  private Logger logger;

  /**
   * Server thread spawner.
   */
  @Injected
  private ServerThreadSpawner serverThreadSpawner;

  /**
   * Port which the server answer to.
   */
  @Injected
  int serverPort;



  public Server (ServerThreadSpawner serverThreadSpawner)
  {
    //    this.executorService= executorService;
    this.serverThreadSpawner= serverThreadSpawner;
    setServerPort(Server.DEFAULT_SERVER_PORT);
  }



  public void run () throws IOException
  {
    setServerSocket(createServerSocket());
    servingLoop();
  }


  private ServerSocket createServerSocket () throws IOException
  {
    ServerSocket serverSocket= null;
    try {
      getLogger().info("creating server socket bound on port {}",
          getServerPort());
      serverSocket= new ServerSocket(getServerPort());
      getLogger().info("server socket created: {}",serverSocket);
      return serverSocket;
    } catch ( IOException e ) {
      getLogger().error("'IOException' thrown by new ServerSocket({})",
          getServerPort());
      throw e;
    }
  }

  private void servingLoop (/*ExecutorService executorService*/)
  {
    // TODO (production priority) implement gracefully shutdown
    // run forever
    while ( true ) {
      try {
        serverThreadSpawner.spawn(getServerSocket().accept());
      } catch ( Exception e ) {
        // TODO (production priority) fine grained exception handling in order to
        // implement error recovery
        getLogger().error("exception occured while accepting, creating " +
            "or executing server thread: {}",e.getMessage());
      }
    }
  }


  @Override
  public void finalize ()
  {
    try {
      getServerSocket().close();
    } catch ( IOException e ) {
      getLogger().warn("'IOException' while closing socket '{}': {}",
          getServerSocket(),e.getMessage());
      // nothing more to do.
    }
  }


  /**
   * @return the logger
   */
  public final Logger getLogger()
  {
    return logger;
  }


  private ServerSocket getServerSocket ()
  {
    return serverSocket;
  }

  private void setServerSocket (ServerSocket serverSocket)
  {
    this.serverSocket = serverSocket;
  }

  /**
   * @return the serverPort
   */
  public int getServerPort() {
    return serverPort;
  }

  /**
   * @param serverPort the serverPort to set
   */
  public final void setServerPort (int serverPort) {
    this.serverPort= serverPort;
  }

}
