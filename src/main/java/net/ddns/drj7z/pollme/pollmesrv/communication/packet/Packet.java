/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication.packet;


/**
 * @author emaschietto
 *
 */
public interface Packet {

  /**
   * All valid packet types.
   *
   * @author emaschietto
   *
   */
  public static enum Types implements Header {
    ACTION,
    ACK,
    NAK,
    HELO
  }

  /**
   * Super-model design pattern for packet header.
   * @author emaschietto
   *
   */
  public static interface Header {}

  /**
   * Super model design pattern for packet payload.
   * @author emaschietto
   *
   */
  public static interface Payload {}



  /**
   * Return the packet header.
   * @return the packet header.
   */
  public Header getHeader ();

  /**
   * Return the packet payload.
   * @return the packet payload.
   */
  public Payload getPayload ();

}
