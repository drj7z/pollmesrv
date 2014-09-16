package net.ddns.drj7z.pollme.pollmesrv.communication;

import net.ddns.drj7z.pollme.pollmesrv.communication.exception.CommunicationException;

public interface Communicator {

  /**
   * Receive data.
   * @return the received data.
   * @throws CommunicationException if something is wrong during communication.
   */
  public <T> T receive () throws CommunicationException;

  /**
   * Receive data which type is of specified class.
   * @param clazz class which type of received data must be.
   * @return the received data.
   * @throws CommunicationException if either something is wrong during
   * communication or data type is not of specified class.
   */
  public <T> T receive (Class<T> clazz) throws CommunicationException;

  /**
   * Send an object.
   * @param object to be sent.
   * @return <code>true</code> if object is sent correctly; <code>false</code>
   * is never returned: if something is wrong an exception is thrown.
   * @throws CommunicationException if something is wrong during communication.
   */
  public <T> boolean send (T object) throws CommunicationException;

}
