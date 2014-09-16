/**
 *
 */
package net.ddns.drj7z.pollme.pollmesrv.logging;

import java.net.Socket;

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
public class ServerLoggingAspect {

  @WireLogger
  private final Logger logger;


  public ServerLoggingAspect ()
  {
    logger= null;
  }

  public ServerLoggingAspect (Logger logger)
  {
    this.logger= logger;
  }




  @Pointcut("execution(public void net.ddns.drj7z.pollme.pollmesrv.Server.run())")
  private void pointcutRun() {}

  @Before("pointcutRun()")
  public void start ()
  {
    getLogger().info("[server] server running...");
  }

  @AfterReturning("pointcutRun()")
  public void end ()
  {
    getLogger().info("[server]: server ended.");
  }

  @AfterThrowing(pointcut="pointcutRun()",
      throwing="e")
  public void error (Exception e)
  {
    getLogger().error("[server]: server terminated by an exception: {}",e.getMessage());
  }



  @Pointcut("execution(* net.ddns.drj7z.pollme.pollmesrv.ServerThreadSpawner.spawn()) && args(socket)")
  private void pointcutServerThreadSpawner (Socket socket) {}

  @Before("pointcutServerThreadSpawner(socket)")
  public void serverThreadSpawnerStart (Socket socket)
  {
    getLogger().info("[server] spawning new server thread for socket {}...",socket);
  }

  @AfterReturning("pointcutServerThreadSpawner(socket)")
  public void serverThreadSpawnerEnd (Socket socket)
  {
    getLogger().info("[server]: new server thread for socket [{}] spawn correctley.",socket);
  }

  @AfterThrowing(pointcut="pointcutServerThreadSpawner(socket)",
      throwing="e")
  public void serverThreadSpawnerError (Socket socket, Exception e)
  {
    getLogger().error("[server]: cannot spawn server thread due an exception: {}",e.getMessage());
  }


  @Pointcut("execution(* net.ddns.drj7z.pollme.pollmesrv.ServerThread.run())")
  private void pointcutServertThread () {}

  @Before("pointcutServertThread()")
  public void serverThreadStart ()
  {
    getLogger().info("[server-thread] server thread running...");
  }

  @AfterReturning("pointcutServertThread()")
  public void serverThreadEnd ()
  {
    getLogger().info("[server-thread]: server thread ended.");
  }

  @AfterThrowing(pointcut="pointcutServertThread()",
      throwing="e")
  public void serverThreadError (Exception e)
  {
    getLogger().error("[server-thread]: server thread terminated by an " +
        "exception: {}",e.getMessage());
  }



  protected Logger getLogger ()
  {
    return logger;
  }

}
