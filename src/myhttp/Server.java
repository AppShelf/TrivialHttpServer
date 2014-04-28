/*
 * Server: http multiservice server
 */

package myhttp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Sandro
 */
public class Server {

  int port;
  boolean isRunning;

  public Server( int port ) {
    this.port = port;
    isRunning = true;
  }
  
  public void start() {
    
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(port);
      System.out.println("http.Server started on socket " + serverSocket);
      while(isRunning) {
        Socket clientSocket = serverSocket.accept();
        System.out.println("Connection accepted: "+ clientSocket);
        try {
          Service s = new Service(clientSocket);
          s.start();
        } catch(IOException e) {
          clientSocket.close();
        }
      }
      serverSocket.close();
      System.out.println("http.Server closed");
    } catch (IOException ex) {
      System.out.println("Error in http.Server: " + ex.getMessage() );
      System.exit(1);
    }
    
  }
 
}
