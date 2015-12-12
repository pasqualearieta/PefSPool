package igpe.billiard.network;

import igpe.billiard.core.Direction;
import igpe.billiard.core.MatchManager;
import igpe.billiard.core.Modality;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerGameCore {

	private List<ClientManager> clientsConnected = new ArrayList<>();
	private List<ClientManager> clientsReadyToPlay = new ArrayList<>();
	private MatchManager matchManager;

	public ServerGameCore() {

	}

	public void addClientManager(final ClientManager clientManager) {
		clientsConnected.add(clientManager);
	}

	public void dispatch(final String message, final ClientManager sender) {
		for (final ClientManager clientManager : clientsConnected) {
			if (message.contains("#UPDATECHAT"))
				clientManager.dispatch(message);
			else {
				if (!message.equals("#START") && !message.equals("#ConnectionClosed") && !message.equals("#WIN"))
					clientManager.dispatch(message + ";");
				else {
					if (message.equals("#ConnectionClosed") || message.equals("#WIN")) {
						clientManager.dispatch(sender.getName() + ":" + message);
					} else
						clientManager.dispatch(message);
				}

			}
		}
	}

	public void receiveMessage(String buffer) throws IOException {
		String[] status = buffer.split(":");

		if (status[0].equals("#CHAT")) {
			handleHistoryChat(status[1]);
		} else {
			if (!status[1].equals("#ConnectionClosed")) {
				String nameOfTheSender = new String(status[0]);
				Direction direction = Direction.valueOf(status[1]);
				if (nameOfTheSender.equals(matchManager.getFirstPlayer().getName())
						&& matchManager.getFirstPlayer().isTurn()
						|| nameOfTheSender.equals(matchManager.getSecondPlayer().getName())
								&& matchManager.getSecondPlayer().isTurn()) {
					if (direction == Direction.RIGHT)
						matchManager.moveRight();
					else if (direction == Direction.LEFT)
						matchManager.moveLeft();
					else if (direction == Direction.UP)
						matchManager.moveUp();
					else if (direction == Direction.DOWN)
						matchManager.moveDown();
					else if (direction == Direction.STOP)
						matchManager.stop();
					else if (direction == Direction.HIT) {
						matchManager.hit();
					}
				}
			} else if (status[1].equals("#ConnectionClosed"))
				matchManager.closeNetworkGame(status[0]);

			else if (status[1].equals("#WIN"))
				matchManager.setEndOfGame(true);
		}

	}

	public void setReady(final ClientManager clientManager) {
		synchronized (clientsReadyToPlay) {
			clientsReadyToPlay.add(clientManager);
			if (clientsReadyToPlay.size() == 2) {
				dispatch("#START", null);
			}
		}
	}

	public void startGame() throws IOException {
		List<String> clientsNames = new ArrayList<>();
		matchManager = new MatchManager(Modality.ONLINE);

		for (int i = 0; i < clientsConnected.size(); i++) {
			clientsNames.add(clientsConnected.get(i).getName());
			if (i == 0)
				matchManager.getFirstPlayer().setName(clientsNames.get(0));
			else
				matchManager.getSecondPlayer().setName(clientsNames.get(1));
		}

		matchManager.start(new Runnable() {
			@Override
			public void run() {

				String currentStatusTheGame = matchManager.getCurrentStatusOfTheGame();

				String[] status = currentStatusTheGame.split(":");
				if (status.length == 2 && (status[1].equals("#ConnectionClosed") || status[1].equals("#WIN"))) {
					for (ClientManager clientManager : clientsConnected)
						if (status[0].equals(clientManager.getName()))
							dispatch(status[1], clientManager);
				} else
					dispatch(currentStatusTheGame, null);
			}
		});

	}

	private void handleHistoryChat(String message) {
		String[] splitChat = message.split("&&");

		String clientSender = new String("<" + splitChat[1] + ">");

		String text = new String(splitChat[0]);

		StringBuilder chat = new StringBuilder();
		chat.append(clientSender);
		chat.append(text);

		String s = new String("#UPDATECHAT:" + chat.toString());
		dispatch(s, null);

	}

}
