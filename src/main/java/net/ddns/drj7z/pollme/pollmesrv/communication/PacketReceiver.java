/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication;

import net.ddns.drj7z.pollme.pollmesrv.communication.exception.CommunicationException;
import net.ddns.drj7z.pollme.pollmesrv.communication.packet.Packet;

/**
 * @author emaschietto
 *
 */
public interface PacketReceiver {

  /**
   * Return received object whatever his type is.
   * @return the received packet whatever its type is.
   * @throws CommunicationException is paccket cannot be received.
   */
  public Packet receive () throws CommunicationException;

  /**
   * Return received object only if it can be converted to the specified class.
   * @param clazz
   * @return the received packet.
   * @throws CommunicationException is paccket cannot be received.
   */
  public Packet receive (Class<? extends Packet> clazz)
      throws CommunicationException;

  /**
   * Send a packet.
   * @param object packet to be sent.
   * @return true is transmission went fine, false otherwise.
   * @throws CommunicationException is paccket cannot be received.
   */
  public boolean send (Packet object) throws CommunicationException;

}
