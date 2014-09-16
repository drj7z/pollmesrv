package net.ddns.drj7z.pollme.pollmesrv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App
{
  // TODO (very low priority) @WireLogger if can get app as spring bean!
  //private Logger logger= null;


  // TODO (database priority) code here is an example of sqlite db connection...
  // it does not concern db creation
  private void createDb (DataSource ds)
  {
    try {
      Connection conn= ds.getConnection();
      Statement stmt= conn.createStatement();
      ResultSet rs= stmt.executeQuery("select * from user;");
      while ( rs.next() ) {
        String account= rs.getString("account");
        // TODO (database priority) logger.info("account: {}",account);
        System.out.println("account: " + account);
      }
    } catch ( SQLException sqle ) {
      // TODO (database priority) logger.info("error: {}.,e.getMessage());
      System.out.println("error - " + sqle.getMessage());
    }

  }

  public void run ()
  {

    ApplicationContext context =
        new ClassPathXmlApplicationContext("application.xml");
    ((AbstractApplicationContext)context).registerShutdownHook();

    // TODO (very low priority) a way not use  getBean
    Server server= (Server)context.getBean("server");

    try {
      server.run();
    } catch ( Exception e ) {
      // do nothing: logging managed by AOP.
    } finally {
      // close the context.
      ((AbstractApplicationContext)context).close();
    }
  }



  public static void main( String[] args )
  {
    App app= new App();
    app.run();
  }

}
