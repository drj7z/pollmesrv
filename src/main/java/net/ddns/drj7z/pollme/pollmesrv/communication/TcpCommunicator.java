/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import net.ddns.drj7z.pollme.pollmesrv.communication.exception.CommunicationException;
import net.ddns.drj7z.pollme.pollmesrv.support.Injected;
import net.ddns.drj7z.pollme.pollmesrv.support.WireLogger;

import org.slf4j.Logger;

/**
 * @author emaschietto
 *
 */
public class TcpCommunicator implements Communicator {

  /**
   * Input stream from which read.
   */
  private ObjectInputStream in= null;

  /**
   * Output stream to write to.
   */
  private ObjectOutputStream out= null;

  @Injected
  @WireLogger
  private Logger logger;



  /**
   * Do nothing.
   * Defined only to allow default construction and then object
   * initialization via setters.
   */
  public TcpCommunicator ()
  { /* do nothing */ }

  /**
   * Construct setting in.
   * @param in
   * @param out
   */
  public TcpCommunicator (InputStream in, OutputStream out) throws CommunicationException
  {
    setIn(in);
    setOut(out);
  }

  /**
   * Construct setting in
   * @param in
   */
  public TcpCommunicator (ObjectInputStream in, ObjectOutputStream out )
  {
    setIn(in);
    setOut(out);
  }



  public <T> T receive () throws CommunicationException
  {
    try {
      @SuppressWarnings("unchecked") // checked: catch ClassCastException
      T packet= (T)getIn().readObject();
      return packet;
    } catch ( IOException e ) {
      throw new CommunicationException("cannot receive packet (io exception)",e);
    } catch ( ClassNotFoundException e ) {
      throw new CommunicationException("cannot receive packet as it is " +
          "unknown type (class not found exception)",e);
    } catch ( ClassCastException e ) {
      throw new CommunicationException("received packet is not os the " +
          "specified type T (class cast exception",e);
    }
  }

  public <T> T receive (Class<T> clazz) throws CommunicationException
  {
    try {
      return clazz.cast(receive());
    } catch ( ClassCastException e) {
      throw new CommunicationException("received packet is not a '" +
          clazz.getName() + "'",e);
    }
  }


  public <T> boolean send (T object)  throws CommunicationException
  {
    try {
      getOut().writeObject(object);
      getOut().flush();
      return true;
    } catch ( IOException e ) {
      throw new CommunicationException("cannot send packet",e);
    }
  }


  /**
   * @return the input stream.
   */
  protected ObjectInputStream getIn ()
  {
    if ( in == null ) {
      throw new IllegalStateException("in cannot be null");
    }
    return in;
  }

  /**
   * Set the input stream.
   * @param in the input stream.
   */
  public void setIn (ObjectInputStream in)
  {
    this.in= in;
  }

  /**
   * Set the input stream from the provided one.
   * @param is the input stream from which the input stream.
   * @throws CommunicationException if cannot set the input stream from in.
   */
  public void setIn (InputStream is) throws CommunicationException
  {
    try {
      setIn(new ObjectInputStream(is));
    } catch ( IOException e ) {
      throw new CommunicationException("cannot set input stream from " +
          "socket",e);
    }
  }

  /**
   * Set the input stream from the specified socket.
   * @param socket socket from which set the input stream.
   * @throws CommunicationException if cannot set the input stream from the
   * socket.
   */
  public void setIn (Socket socket) throws CommunicationException
  {
    try {
      setIn(socket.getInputStream());
      logger.debug("set in stream for socket [{}]: {}.",socket,in);
    } catch ( IOException e ) {
      throw new CommunicationException("cannot set input stream from socket",e);
    }
  }


  /**
   * @return the out stream.
   */
  public ObjectOutputStream getOut ()
  {
    if ( out == null ) {
      throw new IllegalStateException("'out' cannot be null");
    }
    return out;
  }

  /**
   * Set the output stream.
   * @param out the out to set.
   */
  public void setOut (ObjectOutputStream out)
  {
    this.out= out;
  }

  /**
   * Set the output stream from the provided one.
   * @param is the output stream from which the input stream.
   * @throws CommunicationException if cannot set the output stream from in.
   */
  public void setOut (OutputStream os) throws CommunicationException
  {
    try {
      setOut(new ObjectOutputStream(os));
    } catch ( IOException e ) {
      throw new CommunicationException("cannot set output stream from " +
          "socket",e);
    }
  }

  /**
   * Set the output stream from the specified socket.
   * @param socket socket from which set the output stream.
   * @throws CommunicationException if cannot set the output stream from the
   * socket.
   */
  public void setOut (Socket socket) throws CommunicationException
  {
    try {
      setOut(socket.getOutputStream());
      logger.debug("set out stream for socket [{}]: {}.",socket,out);
    } catch ( IOException e ) {
      throw new CommunicationException("cannot set output stream from socket",e);
    }
  }

}

