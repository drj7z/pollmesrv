/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication;

/**
 * @author emaschietto
 *
 */
public interface PacketTransferWithCommunicatorSupport extends PacketReceiver {

  public void setCommunicator (Communicator communicator);

}
