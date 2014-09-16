/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv;

import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * @author emaschietto
 *
 */
public class ServerThreadSpawnerNew implements ServerThreadSpawner {

  private final class Spawner implements Runnable {

    private final ExecutorService executorService;
    private final ServerThreadBuilder serverThreadBuilder;
    private final Socket socket;

    public Spawner(ExecutorService executorService,
        ServerThreadBuilder serverThreadBuilder, Socket socket)
    {
      this.executorService= executorService;
      this.serverThreadBuilder= serverThreadBuilder;
      this.socket= socket;
    }

    public void run () throws RuntimeException
    {
      try {
        executorService.execute(serverThreadBuilder.build(socket));
      } catch ( ServerThreadBuilderException e ) {
        throw new RuntimeException(e);
      }
    }

  }



  private final ExecutorService executorService;
  private final ServerThreadBuilder serverThreadBuilder;



  public ServerThreadSpawnerNew(ExecutorService executorService,
      ServerThreadBuilder serverThreadBuilder)
  {
    this.executorService= executorService;
    this.serverThreadBuilder= serverThreadBuilder;
  }



  public void spawn (Socket socket) throws ServerThreadSpawnerException
  {
    try {
      executorService.execute(new Spawner(executorService,serverThreadBuilder,
          socket));
    } catch ( RuntimeException e ) {
      throw new ServerThreadSpawnerException("cannot spawn server thread " +
          "as an execption occured: " + e.getMessage(),e.getCause());
    }
  }

}
