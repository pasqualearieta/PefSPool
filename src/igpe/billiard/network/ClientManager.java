package igpe.billiard.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManager implements Runnable {
	private final Socket socket;
	private ServerGameCore serverGameCore;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private String name;
	private String gameModality;
	private OlinePlayerManager onlinePlayerConnected;
	private boolean iHaveToDecideTheOpponent = true;
	private boolean iAmPlaying= false;

	

	public void setiAmPlaying(boolean iAmPlaying) {
		this.iAmPlaying = iAmPlaying;
	}

	public ClientManager(final Socket socketClient, OlinePlayerManager onlinePlayerConnected) {

		this.socket = socketClient;
		this.name = new String();
		this.onlinePlayerConnected = onlinePlayerConnected;

	}

	public void dispatch(String message) {
		if (printWriter != null && message != null) {
			printWriter.println(message);
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		try {

			if (iHaveToDecideTheOpponent) {
				String bufferSetup = bufferedReader.readLine();
				String[] bufferSplit = bufferSetup.split(":::");
				if (bufferSplit[0].equals("#PLAY"))
					onlinePlayerConnected.joinATable(this, bufferSplit[1]);

				iHaveToDecideTheOpponent = false;
			}

			final boolean running = true;
			while (running) {
				String buffer = bufferedReader.readLine();
				if (buffer != null) {
					getServerGameCore().receiveMessage(buffer);

				} else {
					System.out.println("disconnected client : " + name);
					break;
				}
			}

		} catch (IOException e) {
			try {
				if (iAmPlaying)
					serverGameCore.receiveMessage(name + ":" + "#ConnectionClosed");

				else if (gameModality.equals("CREATE"))
					onlinePlayerConnected.removeClientCreator(this);
				else if (gameModality.equals("JOIN"))
					onlinePlayerConnected.removeClientWait(this);
				
				socket.close();
			} catch (IOException e1) {
				System.out.println(name + ": disconnected !");
			}
		}
	}

	public void setup() throws IOException {
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		printWriter = new PrintWriter(socket.getOutputStream(), true);

		String buffer = bufferedReader.readLine();

		String[] bufferSplit = buffer.split("&&");
		name = bufferSplit[0];

		setGameModality(bufferSplit[1]);
		if (gameModality.equals("CREATE")) {
			onlinePlayerConnected.createTable(this);
			iHaveToDecideTheOpponent = false;

		} else if (gameModality.equals("JOIN")) {
			onlinePlayerConnected.addClientWhoChoose(this);

		}
	}

	public String getGameModality() {
		return gameModality;
	}

	public void setGameModality(String modalitaDigioco) {
		this.gameModality = modalitaDigioco;
	}

	public ServerGameCore getServerGameCore() {
		return serverGameCore;
	}

	public void setServerGameCore(ServerGameCore serverGameCore) {
		this.serverGameCore = serverGameCore;
	}
}
