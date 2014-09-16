/**
 * 
 */
package net.ddns.drj7z.pollme.pollmesrv.communication.exception;

/**
 * @author emaschietto
 *
 */
public class InvalidPacketException extends CommunicationException {

	private static final long serialVersionUID = -4730819872205491023L;

	public InvalidPacketException (String arg0, Throwable arg1) 
	{
		super(arg0, arg1);
	}

	public InvalidPacketException (String arg0) 
	{
		super(arg0);
	}

	public InvalidPacketException (Throwable arg0)
	{
		super(arg0);
	}

}
