/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication.packet;

/**
 * Represent an Aknowledge (ACK) packet.
 *
 * @author emaschietto
 * @version 1.0.0
 *
 */
public class AckPacket extends PacketBase {

  public AckPacket()
  {
    super(Packet.Types.ACK);
  }

}
