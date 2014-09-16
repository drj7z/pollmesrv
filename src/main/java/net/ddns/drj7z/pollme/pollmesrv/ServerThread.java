package net.ddns.drj7z.pollme.pollmesrv;

import java.net.Socket;

import net.ddns.drj7z.pollme.pollmesrv.communication.PacketReceiver;

public interface ServerThread extends Runnable {

  public void setSocket(Socket socket) throws ServerException;

  public void setPacketReceiver(PacketReceiver packetReceiver);

}