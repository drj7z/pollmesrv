package net.ddns.drj7z.pollme.pollmesrv;

import java.awt.peer.TextComponentPeer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.net.SocketFactory;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	
	// TODO
	// code here is an example of sqlite db connection...
	// it does not concern db creation
	private void createDb (DataSource ds)
	{
		try {
		Connection conn= ds.getConnection();
		Statement stmt= conn.createStatement();
		ResultSet rs= stmt.executeQuery("select * from user;");
		while ( rs.next() ) {
			String account= rs.getString("account");
			System.out.println("account: " + account);
		}
		} catch ( SQLException sqle ) {
			// TODO
			System.out.println("error - " + sqle.getMessage());
		}

	}
	
	private void init ()
	{}

	public void run ()
	{
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("application.xml");

		Socket socket;
		SocketAddress so;
		try {
		socket= new Socket();//SocketFactory.getDefault().createSocket("0.0.0.0",7001);
		SocketAddress sa= new InetSocketAddress("127.0.0.1",7001);
		socket.bind(sa);
		int port= socket.getLocalPort();
		InetAddress ia= socket.getInetAddress();
		System.out.println("port - " + port);
		System.out.println("address - " + ia);
		} catch ( Exception e ) {
			// TODO
			System.out.println("error - " + e.getMessage());
		}
	}
	
	
    public static void main( String[] args )
    {
        App app= new App();
        app.run();
    }
}
