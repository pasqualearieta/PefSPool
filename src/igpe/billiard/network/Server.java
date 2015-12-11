package igpe.billiard.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private int port;
	private ServerSocket serverSocket;
	private boolean running = true;
	
	private OlinePlayerManager onLinePlayer=new OlinePlayerManager();
	
	public static void main(String[] args) throws IOException {
		int port = 2727;
		final Server server = new Server(port);
		server.run();
	}

	public Server(int port) {
		this.port = port;
	}

	private void run() throws IOException {
		serverSocket = new ServerSocket(port);
		
		while (running) {
		
			System.out.println("Waiting connections");
			
			final Socket firstSocketClient = serverSocket.accept();
			final ClientManager firstClientManager = new ClientManager(firstSocketClient,onLinePlayer);
			firstClientManager.setup();
			new Thread(firstClientManager,firstClientManager.getName()).start();
			
			System.out.println("First Client connected !");
			

		}
		serverSocket.close();
	}
}
