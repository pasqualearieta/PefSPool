package igpe.billiard.network;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OlinePlayerManager {

	private List<ClientManager> clientInWait;

	private List<ClientManager> clientCreatorOfTable;

	private List<String> nameOfClient;

	public OlinePlayerManager() {
		setClientInWait(new ArrayList<ClientManager>());

		setClientCreatorOfTable(new ArrayList<ClientManager>());

		nameOfClient = new ArrayList<String>();
	}

	public void createTable(ClientManager creator) {
		if (!nameOfClient.contains(creator.getName())) {
			getClientCreatorOfTable().add(creator);
			creator.dispatch("#WAIT");

			nameOfClient.add(creator.getName());
			notifyClients();

		} else {
			creator.dispatch("#ALREADYUSED");
		}
	}

	public void joinATable(ClientManager rival, String creator) {

		ServerGameCore serverGameCore = new ServerGameCore();

		ClientManager creatore = null;
		for (ClientManager clientManager : getClientCreatorOfTable()) {
			if (clientManager.getName().equals(creator)) {
				creatore = clientManager;
			}
		}
		creatore.setiAmPlaying(true);
		getClientCreatorOfTable().remove(creatore);
		getClientInWait().remove(rival);

		creatore.setServerGameCore(serverGameCore);
		rival.setServerGameCore(serverGameCore);
		rival.setiAmPlaying(true);

		nameOfClient.remove(creatore.getName());
		nameOfClient.remove(rival.getName());

		serverGameCore.addClientManager(creatore);
		serverGameCore.addClientManager(rival);

		serverGameCore.setReady(rival);
		serverGameCore.setReady(creatore);
		notifyClients();
		try {
			serverGameCore.startGame();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Impossible Start Game", "Start Game Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void addClientWhoChoose(ClientManager clientManager) {
		if (!nameOfClient.contains(clientManager.getName()) && !clientCreatorOfTable.isEmpty()) {
			if (clientCreatorOfTable.isEmpty())
				clientManager.dispatch("#NOTABLE");
			getClientInWait().add(clientManager);
			nameOfClient.add(clientManager.getName());
			notifyClients();
		} else if (clientCreatorOfTable.isEmpty())
			clientManager.dispatch("#NOTABLE");
		else if (nameOfClient.contains(clientManager.getName())) {
			clientManager.dispatch("#ALREADYUSED");
		}
	}

	public void notifyClients() {
		StringBuilder sb = new StringBuilder();

		if (!getClientCreatorOfTable().isEmpty()) {
			for (ClientManager clientManager : getClientCreatorOfTable())
				sb.append(clientManager.getName() + "&&");
		}

		if (!getClientInWait().isEmpty()) {
			for (ClientManager clientManagerConnected : getClientInWait()) {
				clientManagerConnected.dispatch("#LIST!!" + sb.toString());
			}
		}

	}

	public List<ClientManager> getClientInWait() {
		return clientInWait;
	}

	public void setClientInWait(List<ClientManager> clientInWait) {
		this.clientInWait = clientInWait;
	}

	public List<ClientManager> getClientCreatorOfTable() {
		return clientCreatorOfTable;
	}

	public void setClientCreatorOfTable(List<ClientManager> clientCreatorOfTable) {
		this.clientCreatorOfTable = clientCreatorOfTable;
	}

	public void removeClientCreator(final ClientManager clientDisconnected) {
		if (clientCreatorOfTable.contains(clientDisconnected)) {
			clientCreatorOfTable.remove(clientDisconnected);
			if (nameOfClient.contains(clientDisconnected.getName()))
				nameOfClient.remove(clientDisconnected.getName());
			notifyClients();

		}
	}

	public void removeClientWait(final ClientManager clientDisconnected) {
		if (clientInWait.contains(clientDisconnected))
			clientInWait.remove(clientDisconnected);
		if (nameOfClient.contains(clientDisconnected.getName()))
			nameOfClient.remove(clientDisconnected.getName());
	}

}
