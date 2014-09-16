package net.ddns.drj7z.pollme.pollmesrv.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Login extends Thread {
	
	Socket socket;
	
	public Login (Socket socket) {
		super(socket.getInetAddress().toString());
		this.socket= socket;
	}

	@Override
	public void run () {
		
		System.out.println("in thread '" + this.getName());
		
		try {
		PrintWriter out =
		        new PrintWriter(socket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
		        new InputStreamReader(socket.getInputStream()));
		    String inputLine, outputLine;
		    while ((inputLine = in.readLine()) != null) {
                outputLine ="you told me: " + inputLine;
                out.println(outputLine);
                if (outputLine.equals("Bye"))
                    break;
            }
            socket.close();
		} catch ( Exception e ) {
			System.out.println("error - " + e.getMessage());
		}
	}

}
