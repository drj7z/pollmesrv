/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv;

import java.net.Socket;

import net.ddns.drj7z.pollme.pollmesrv.communication.PacketTransferWithCommunicatorSupport;
import net.ddns.drj7z.pollme.pollmesrv.communication.TcpCommunicator;
import net.ddns.drj7z.pollme.pollmesrv.communication.exception.CommunicationException;
import net.ddns.drj7z.pollme.pollmesrv.support.Injected;
import net.ddns.drj7z.pollme.pollmesrv.support.WireLogger;

import org.slf4j.Logger;

/**
 * Build a TCP server thread.
 * <b>Note</b>: server is tight with {@link Socket}, {@link TcpCommunicator}
 * and {@link PacketTransferWithCommunicatorSupport}.
 *
 * @author emaschietto
 * @version 1.0.0
 *
 */
public abstract class TcpServerThreadBuilder implements ServerThreadBuilder {

  // Builder pattern.
  private class Builder {

    private Socket socket= null;

    private TcpCommunicator communicator;
    private PacketTransferWithCommunicatorSupport packetReceiver;

    public Builder (Socket socket)
    {
      this.socket= socket;
    }

    public Builder setPacketReceiver (PacketTransferWithCommunicatorSupport packetReceiver)
    {
      this.packetReceiver= packetReceiver;
      return this;
    }

    public Builder setCommunicator (TcpCommunicator communicator)
        throws ServerThreadBuilderException
    {
      this.communicator= communicator;

      try {
        this.communicator.setIn(socket);
        this.communicator.setOut(socket);
      } catch ( CommunicationException e ) {
        throw new ServerThreadBuilderException("cannot build server " +
            "thread as exception occured: " + e.getMessage(),e);
      }

      return this;
    }

    public Runnable build ()
    {
      ServerThread serverThread= createServerThread();
      packetReceiver.setCommunicator(communicator);
      serverThread.setPacketReceiver(packetReceiver);
      serverThread.setSocket(socket);
      return serverThread;
    }

  }



  @Injected
  @WireLogger
  private Logger logger;



  public Runnable build (Socket socket) throws ServerThreadBuilderException
  {
    logger.info("building server thread for socket [{}]...",socket);

    Runnable serverThread= new Builder(socket).setCommunicator(createCommunicator()).
        setPacketReceiver(createPacketReceiver()).build();

    logger.info("server thread for socket [{}] built.",socket);

    return serverThread;
  }


  // spring lookup method(s).
  protected abstract TcpCommunicator createCommunicator ();
  protected abstract PacketTransferWithCommunicatorSupport createPacketReceiver ();
  protected abstract ServerThread createServerThread ();

}
