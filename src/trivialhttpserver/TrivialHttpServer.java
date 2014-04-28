/*
 * TrivialHttpServer
 */

package trivialhttpserver;

/**
 *
 * @author Sandro
 */
public class TrivialHttpServer {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    myhttp.Server s = new myhttp.Server(8080);
    s.start();
  }

}
