/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import net.ddns.drj7z.pollme.pollmesrv.action.Action;
import net.ddns.drj7z.pollme.pollmesrv.action.ActionException;
import net.ddns.drj7z.pollme.pollmesrv.action.ActionFactory;
import net.ddns.drj7z.pollme.pollmesrv.communication.PacketReceiver;
import net.ddns.drj7z.pollme.pollmesrv.communication.exception.CommunicationException;
import net.ddns.drj7z.pollme.pollmesrv.communication.exception.InvalidPacketException;
import net.ddns.drj7z.pollme.pollmesrv.communication.packet.AckPacket;
import net.ddns.drj7z.pollme.pollmesrv.communication.packet.ActionPacket;
import net.ddns.drj7z.pollme.pollmesrv.communication.packet.NakPacket;
import net.ddns.drj7z.pollme.pollmesrv.support.Injected;
import net.ddns.drj7z.pollme.pollmesrv.support.WireLogger;

import org.slf4j.Logger;

/**
 * @author emaschietto
 *
 */
public class ServerThreadSimple implements ServerThread {

  private Socket socket;

  private PacketReceiver packetReceiver;

  @Injected
  @WireLogger
  private Logger logger;

  @Injected
  private final ActionFactory actionFactory;

  @Injected
  private final int socketTimeout;



  public ServerThreadSimple (ActionFactory actionFactory, int socketTimeout)
  {
    this.actionFactory= actionFactory;
    this.socketTimeout= socketTimeout;
  }



  // must not be accessible from outside and none must change behavior.
  protected final Socket getSocket ()
  {
    return socket;
  }

  /* (non-Javadoc)
   * @see net.ddns.drj7z.pollme.pollmesrv.ServerThread#setSocket(java.net.Socket)
   */
  public void setSocket (Socket socket) throws ServerException
  {
    this.socket= socket;
    try {
      socket.setSoTimeout(getSocketTimeout());
    } catch ( SocketException e ) {
      throw new ServerException("exception while setting timeout [" +
          getSocketTimeout() + "] to socket [" + socket + "]",e);
    }
  }


  // must not be accessible from outside and none must change behavior.
  protected final PacketReceiver getPacketReceiver ()
  {
    return packetReceiver;
  }

  /* (non-Javadoc)
   * @see net.ddns.drj7z.pollme.pollmesrv.ServerThread#setPacketReceiver(net.ddns.drj7z.pollme.pollmesrv.communication.PacketReceiver)
   */
  public void setPacketReceiver (PacketReceiver packetReceiver)
  {
    this.packetReceiver= packetReceiver;
  }


  /**
   * @return the actionFactory
   */
  protected final ActionFactory getActionFactory ()
  {
    return actionFactory;
  }

  // setActionFactory not available as field is final.


  /**
   * @return the socketTimeout
   */
  public int getSocketTimeout ()
  {
    return socketTimeout;
  }

  // setSocketTimeout not available as field is final.


  public void run ()
  {
    try {

      logger.debug("receiving an action packet...");
      ActionPacket packet= (ActionPacket)getPacketReceiver().
          receive(ActionPacket.class);
      logger.debug("action packet received.");

      if ( manageActionPacket(packet) ) {
        logger.debug("sending ack...");
        getPacketReceiver().send(new AckPacket());
        logger.debug("ack sent.");
      }
      else {
        logger.debug("sending nak...");
        getPacketReceiver().send(new NakPacket());
        logger.debug("nak sent.");
      }

      // we are done: logged via AOP.

    } catch ( InvalidPacketException e ) {
      logger.error("[server-thread]: got invalid packet got from {}.",
          getSocket().getInetAddress());
    } catch ( CommunicationException e) {
      logger.error("[server-thread]: something went wrong while " +
          "comunicating with {}: {}.",getSocket(),e.getCause().getMessage());
    } finally {
      close();
    }
  }


  /**
   * Execute the action corresponding to the packet.
   * @param actionPacket packet with information concerning action to execute.
   * @return outcome of action execution.
   */
  private boolean manageActionPacket (ActionPacket actionPacket)
  {
    ActionPacket.Header header= (ActionPacket.Header)actionPacket.getHeader();
    ActionPacket.Payload payload= (ActionPacket.Payload)actionPacket.getPayload();

    Action action= getActionFactory().createAction(header.getActionName());

    try {
      return action.execute(payload.getActionData());
    } catch ( ActionException e) {
      logger.error("[server-thread]: something went wrong while " +
          "executing action {}: {}.",action,e.getMessage());
      return false;
    }
  }


  private void close ()
  {
    logger.debug("[server-thread] would like to close the socket {}...",
        getSocket());
    if ( (socket != null) && !getSocket().isClosed() ) {
      try {
        logger.debug("[server-thread]: closing socket {}",getSocket());
        socket.close();
      } catch ( IOException e ) {
        logger.error("[server-thread]: cannot close socket {} due an " +
            "exception: {}.",e.getMessage());
        // nothing more to do.
      }
    }
    else {
      logger.warn("[server-thread] not closing socket {} as already either "
          + "closed or null; why? should not happen.");
    }
  }

}
