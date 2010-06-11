package Forum.Server.ControllerLayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import Forum.Settings;

/**
 * This class runs the server (currently set to port 1234).
 * 
 * @author Tomer Heber
 */
public class ServerConnectionController extends Thread {
		
	private static final Logger log = Logger.getLogger(Settings.loggerName);

	@Override
	public void run() {
		short port = 1234;
		
		try {
			ServerSocket ss = new ServerSocket(port);
			log.info("Server has started running on port "+port+".");
			while (true) {
				Socket s = ss.accept();
				log.info("A connection was accepted from: "+s.getInetAddress()+".");
				ServerSingleConnectionController.startConnection(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.severe("An error has accoured in the server (IOException).");
		}
		
		log.info("Server has closed.");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
		Thread t = new ServerConnectionController();
		/* Start the thread */
		t.start();
		try {
			/* Wait for the thread to finish running */
			t.join();
		} catch (InterruptedException e) {			
			e.printStackTrace();
			
		}
				
	}

}
