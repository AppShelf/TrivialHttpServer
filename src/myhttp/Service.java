/*
 * Service: gestisce una connessione http al server
 */

package myhttp;

import java.io.*;
import java.net.*;

/**
 *
 * @author Sandro
 */
public class Service extends Thread {
  
  private static int counter = 0;
  
  final int id;
  final Socket socket;
  final BufferedReader in;
  final PrintWriter out;
  
  public Service(Socket s) throws IOException {
    socket = s;
    id = ++counter;
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
  }
  
  @Override
  public void run() {
    System.out.println("Service Thread "+id+": started");
    try {
      while (true) {
        String str = in.readLine();
        System.out.println("Service Thread "+id+" -> " + str);
        if (str.isEmpty()) break;
      }
      out.println("400 Bad Request\n");
      socket.close();
      System.out.println("Service Thread "+id+": closed");
    } catch (IOException ex) {
      System.out.println("Error in Service Thread " + id + ": " + ex.getMessage() + " on socket " + socket);
    }
  }
  
}
