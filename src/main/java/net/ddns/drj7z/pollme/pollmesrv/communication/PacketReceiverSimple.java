/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication;

import net.ddns.drj7z.pollme.pollmesrv.communication.exception.CommunicationException;
import net.ddns.drj7z.pollme.pollmesrv.communication.exception.InvalidPacketException;
import net.ddns.drj7z.pollme.pollmesrv.communication.json.JsonEncoder;
import net.ddns.drj7z.pollme.pollmesrv.communication.json.JsonException;
import net.ddns.drj7z.pollme.pollmesrv.communication.packet.Packet;
import net.ddns.drj7z.pollme.pollmesrv.communication.packet.PacketBase;
import net.ddns.drj7z.pollme.pollmesrv.support.Injected;
import net.ddns.drj7z.pollme.pollmesrv.support.WireLogger;

import org.slf4j.Logger;

/**
 * @author emaschietto
 *
 */
public class PacketReceiverSimple implements PacketTransferWithCommunicatorSupport {

  /**
   * The logger.
   */
  @Injected
  @WireLogger
  private Logger logger;

  /**
   * Json encode / decoder.
   */
  @Injected
  private JsonEncoder jsonEncoder;

  /**
   * helper that manages the communication actually.
   */
  @Injected
  private Communicator communicator;



  /**
   * Do nothing.
   * Defined only to allow default construction and then object
   * initialization via setters.
   */
  public PacketReceiverSimple ()
  { /* do nothing */ }



  /**
   * Receive a json representation of a {@see PacketBase} packet.
   * @return the received object.
   * @throws CommunicationException if there are any communication error.
   */
  public Packet receive () throws CommunicationException
  {
    return receive(PacketBase.class);
  }

  /**
   * Receive a json representation of a packet of the specified class type.
   * @param clazz class of the type of the packet to be received.
   * @throws CommunicationException if there are any communication error.
   */
  public Packet receive (Class<? extends Packet> clazz) throws CommunicationException
  {
    try {

      logger.debug("receiving a json string...");
      String jsonPacket= getCommunicator().receive(String.class);
      logger.debug("json string received: {}.",jsonPacket);

      logger.debug("decoding json [{}] to {}",jsonPacket,clazz);
      Packet packet= (Packet)getJsonEncoder().decode(jsonPacket,clazz);
      logger.debug("json correctly decoded.");

      logger.debug("actual type of received packet is: {}.",
          ((PacketBase.Header)packet.getHeader()).getType());

      return packet;

    } catch ( JsonException e ) {
      throw new InvalidPacketException("received packed is not valid",e);
    }

  }


  /**
   * Send a json representation of the specified packet.
   * @param object packet to be send as json.
   * @throws CommunicationException if something is wrong while sending the
   * packet.
   */
  public boolean send (Packet object)  throws CommunicationException
  {
    try {

      logger.debug("encoding object [{}] to json...",object);
      String json= getJsonEncoder().encode(object);
      logger.debug("object encoded as json: {}",json);

      getCommunicator().send((json));
      return true;

    } catch ( JsonException e ) {
      throw new CommunicationException("cannot send packet as it cannot " +
          "be converted to json", e);
    }
  }


  public JsonEncoder getJsonEncoder ()
  {
    return jsonEncoder;
  }

  public void setJsonEncoder (JsonEncoder jsonEncoder)
  {
    this.jsonEncoder= jsonEncoder;
  }


  /**
   * @return the communicator
   */
  public Communicator getCommunicator ()
  {
    return communicator;
  }

  /**
   * @param communicator the communicator to set
   */
  public void setCommunicator (Communicator communicator)
  {
    this.communicator= communicator;
  }

}
