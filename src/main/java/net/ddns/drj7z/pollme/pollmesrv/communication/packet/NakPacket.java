/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication.packet;

/**
 * Represent an NOT-Aknowledge (NAK) packet.
 *
 * @author emaschietto
 * @version 1.0.0
 *
 */
public class NakPacket extends PacketBase {

  public NakPacket()
  {
    super(Packet.Types.NAK);
  }

}
