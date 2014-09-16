/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.communication.packet;

import net.ddns.drj7z.pollme.pollmesrv.action.ActionData;
import net.ddns.drj7z.pollme.pollmesrv.action.ActionNames;

/**
 * @author emaschietto
 *
 */
public class ActionPacket extends PacketBase {

  public static class Header extends PacketBase.Header {

    private ActionNames actionName;

    public Header ()
    {
      super(Packet.Types.ACTION);
      actionName= null;
    }

    public Header (ActionNames actionName)
    {
      //super(Packet.Types.ACTION);
      this();
      this.actionName= actionName;
    }

    /**
     * @return the actionName
     */
    public ActionNames getActionName ()
    {
      return actionName;
    }

    /**
     * @param actionName the actionName to set
     */
    public void setActionName (ActionNames actionName)
    {
      this.actionName= actionName;
    }

  }


  public static class Payload implements Packet.Payload, ActionData {

    private ActionData actionData;

    public Payload ()
    {
      actionData= null;
    }

    public Payload (ActionData actionData)
    {
      this();
      this.actionData= actionData;
    }

    /**
     * @return the payload
     */
    public ActionData getActionData()
    {
      return actionData;
    }

    /**
     * @param actionData the actionData to set
     */
    public void setActionData (ActionData actionData)
    {
      this.actionData= actionData;
    }

  }


  private Packet.Payload payload;



  public ActionPacket (ActionNames actionName, ActionData actionData)
  {
    super();

    header= createHeader();
    ((Header)header).setActionName(actionName);

    payload= createPayload();
    ((Payload)payload).setActionData(actionData);
  }

  @Override
  protected Packet.Header createHeader ()
  {
    return new Header();
  }

  protected Packet.Payload createPayload ()
  {
    return new Payload();

  }



  @Override
  public Packet.Header getHeader()
  {
    return header;
  }

  protected void setHeader (Packet.Header header)
  {
    if ( !(payload instanceof Header) ) {
      throw new IllegalArgumentException("'header' must be an 'ActionPacket.Header'");
    }
    this.header= header;
  }

  @Override
  public Packet.Payload getPayload ()
  {
    return payload;
  }

  protected void setPayload (Packet.Payload payload)
  {
    if ( !(payload instanceof Payload) ) {
      throw new IllegalArgumentException("'payload' must be an 'ActionPacket.Payload'");
    }
    this.payload= payload;
  }

}
