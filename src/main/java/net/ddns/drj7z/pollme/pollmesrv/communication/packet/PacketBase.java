/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication.packet;


/**
 * A packet with header of type Types and <code>null</code> payload.
 *
 * @author emaschietto
 *
 */
public class PacketBase implements Packet {

  public static class Header implements Packet.Header {
    private Packet.Types type;

    protected Header ()
    {
      type= null;
    }

    public Header (Packet.Types type)
    {
      this.type= type;
    }

    public Packet.Types getType ()
    {
      return type;
    }

    protected void setType (Packet.Types type)
    {
      this.type= type;
    }

  }



  protected Packet.Header header;



  // needed by Gson, cannot be private otherwise subclasses cannot instantiate.
  protected PacketBase ()
  {
    header= null;
  }

  protected PacketBase (Packet.Types type)
  {
    header= createHeader();
    ((Header)header).setType(type);
  }



  protected Packet.Header createHeader ()
  {
    return new Header();
  }


  public Packet.Header getHeader()
  {
    return header;
  }

  public Packet.Payload getPayload ()
  {
    return null;
  }

}
