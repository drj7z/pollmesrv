/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.logging;

import net.ddns.drj7z.pollme.pollmesrv.support.WireLogger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;


/**
 * @author emaschietto
 *
 */
@Aspect
public class CommunicationLoggingAspect {

  @WireLogger
  private final Logger logger;


  public CommunicationLoggingAspect ()
  {
    logger= null;
  }

  public CommunicationLoggingAspect (Logger logger)
  {
    this.logger= logger;
  }



  @Pointcut("execution(* net.ddns.drj7z.pollme.pollmesrv.communication.PacketReceiver.receive(..))")
  private void pointcutPacketReceiver () {}

  @Before("pointcutPacketReceiver()")
  public void packetReceiverReceiveStart ()
  {
    getLogger().info("[communication] receiving a packet...");
  }

  @AfterReturning("pointcutPacketReceiver()")
  public void packetReceiverReceiveEnd ()
  {
    getLogger().info("[communication]: packet correctly received.");
  }

  @AfterThrowing(pointcut="pointcutPacketReceiver()",
      throwing="e")
  public void packetReceiverReceiveError (Exception e)
  {
    getLogger().error("[communication]: cannot receive packet due an exception: {}",e.getMessage());
  }


  protected Logger getLogger ()
  {
    return logger;
  }

}
