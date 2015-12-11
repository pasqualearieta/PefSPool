package igpe.billiard.gui;

import igpe.billiard.core.MatchManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ConnectionManager implements Runnable {

	private final MainFrame principalMainFrame;
	private final String nameOfThePlayer;
	private List<String> playerNames;
	private PrintWriter printWriter;
	private final Socket socket;
	private JDialog dialog;
	private boolean disconnected = false;
	private boolean win = false;
	private boolean lose = false;
	private Vector<String> clientConnected;
	private JDialog listOFCLient = null;
	private boolean iChooseTheOpponent;
	private String modality;

	public ConnectionManager(final Socket socket, final String name, final MainFrame mainFrame, String modality) {
		this.socket = socket;
		this.nameOfThePlayer = name;
		this.principalMainFrame = mainFrame;
		dialog = new JDialog();
		this.modality = modality;

	}

	public void close() {
		try {
			socket.close();
		} catch (final IOException e) {
			// do nothing
		}
	}

	public void dispatch(final String message) {
		printWriter.println(message);
	}

	@Override
	public void run() {
		try {
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(socket.getOutputStream(), true);

			dispatch(nameOfThePlayer + "&&" + modality);

			String statusConnection = bufferedReader.readLine();

			while (statusConnection.equals("#NOTABLE")) {
				showNoTable();
				statusConnection = bufferedReader.readLine();
				
			}

			while (statusConnection.equals("#ALREADYUSED")) {
				showAlreadyUsed();
				statusConnection = bufferedReader.readLine();

			}

			if (statusConnection.equals("#WAIT"))
				showDialogWait();
			else {

				if (statusConnection.contains("#LIST")) {
					String[] splitBuffer = statusConnection.split("!!");

					if (splitBuffer[0].equals("#LIST")) {
						iChooseTheOpponent = false;
						while (!iChooseTheOpponent) {
							prepareListOfClientConnected(splitBuffer[1]);
							printListOfClientConnected();
							statusConnection = bufferedReader.readLine();
							listOFCLient.dispose();
							splitBuffer = statusConnection.split("!!");

						}

					}
				}
			}

			while (!statusConnection.equals("#START")) {
				final String[] namesPlayers = statusConnection.split(";");
				if (namesPlayers.length != 0) {
					playerNames = new ArrayList<>();
					for (final String nameOfPlayer : namesPlayers) {
						playerNames.add(nameOfPlayer);
					}
				}
				statusConnection = bufferedReader.readLine();
			}

			dialog.dispose();

			final MatchManager matchManager = principalMainFrame.startNetworkGame(this);
			statusConnection = bufferedReader.readLine();

			while (statusConnection != null) {
				String[] statusTheGame = statusConnection.split(":");
				if (statusTheGame.length == 2 && statusTheGame[1].equals("#ConnectionClosed")) {

					if (!statusTheGame[0].equals(nameOfThePlayer)) {
						disconnected = true;
						showDialog();
					}
					socket.close();
					break;
				}

				else if (statusTheGame.length == 2 && statusTheGame[1].equals("#WIN")) {
					if (statusTheGame[0].equals(nameOfThePlayer)) {
						win = true;
						showDialog();
					}

					else if (!statusTheGame[0].equals(nameOfThePlayer)) {
						lose = true;
						showDialog();
					}

					socket.close();
					break;
				} else if (statusTheGame[0].equals("#UPDATECHAT")) {
					principalMainFrame.getGamePanel().setHistoryChat(statusTheGame[1]);
					statusConnection = bufferedReader.readLine();
				}

				else {
					matchManager.clearWorld();
					matchManager.parseStatusFromString(statusConnection);
					principalMainFrame.getGamePanel().repaint();
					statusConnection = bufferedReader.readLine();
				}
			}
		} catch (final IOException e) {
			System.out.println("Connection closed");
		}
	}

	private void showNoTable() {
		JPanel panel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(ImageProvider.getLittleBackground(4), 0, 0, null);
				g.drawImage(ImageProvider.getThereAreNoTableCreated(), 150, 50, null);

			}

		};

		panel.setLayout(null);
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));

		JButton ok = new JButton(new ImageIcon(ImageProvider.getOk()));
		ok.setBounds(150, 200, 100, 50);
		ok.setContentAreaFilled(false);
		ok.setBorder(null);
		ok.setFocusPainted(false);
		ok.setRolloverEnabled(true);
		ok.setRolloverIcon(new ImageIcon(ImageProvider.getOkRollovered()));
		ok.setVisible(true);

		panel.add(ok);

		listOFCLient = new JDialog();
		listOFCLient.add(panel);
		listOFCLient.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		listOFCLient.setResizable(false);
		listOFCLient.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		listOFCLient.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 3 + 100,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3 + 100);
		listOFCLient.setVisible(true);

		ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				listOFCLient.dispose();
			}
		});

	}

	private void prepareListOfClientConnected(String s) {
		clientConnected = new Vector<String>();
		String[] nameOfClient = s.split("&&");
		for (int i = 0; i < nameOfClient.length; i++)
			clientConnected.add(nameOfClient[i]);
	}

	private void printListOfClientConnected() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				g.drawImage(ImageProvider.getBackgroundList(), 0, 0, null);

			}

		};

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));

		JList<String> containerOfTheName = new JList<String>(clientConnected);

		containerOfTheName.setPreferredSize(new Dimension(200, 230));
		containerOfTheName.setMaximumSize(new Dimension(200, 230));
		containerOfTheName.setFont(new Font("BOLD", Font.ITALIC, 20));
		containerOfTheName.setCellRenderer(new MyListRenderer());
		containerOfTheName.setOpaque(false);
		((javax.swing.DefaultListCellRenderer) containerOfTheName.getCellRenderer()).setOpaque(false);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList<?> jList = (JList<?>) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					final int index = jList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						String s = new String(clientConnected.get(index));
						iChooseTheOpponent = true;
						dispatch("#PLAY:::" + s);
					}
				}
			}
		};
		containerOfTheName.addMouseListener(mouseListener);

		JScrollPane scrollPane = new JScrollPane(containerOfTheName);

		scrollPane.setAlignmentX(0.5f);
		scrollPane.setAlignmentY(0.5f);
		scrollPane.setBorder(BorderFactory.createEtchedBorder());
		scrollPane.setPreferredSize(new Dimension(200, 230));
		scrollPane.setMaximumSize(new Dimension(200, 230));

		scrollPane.setOpaque(false);

		scrollPane.getViewport().setOpaque(false);
		scrollPane.setAutoscrolls(true);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVisible(true);

		panel.add(Box.createVerticalStrut(20));
		panel.add(scrollPane);

		listOFCLient = new JDialog();
		listOFCLient.add(panel);
		listOFCLient.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		listOFCLient.setResizable(false);
		listOFCLient.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		listOFCLient.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 3 + 100,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3 + 100);
		listOFCLient.setVisible(true);

	}

	private void showDialogWait() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(9), 0, 0, null);

				g.drawImage(ImageProvider.getWaitForYourOpponentToConnectToTheGame(), 150, 50, null);
			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setLayout(null);

		dialog.add(panel);
		dialog.setTitle("Wait Other Player");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showDialog() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(12), 0, 0, null);
				if (disconnected)
					g.drawImage(ImageProvider.getDisconnected(), 150, 50, null);
				else if (win)
					g.drawImage(ImageProvider.getYouWinOnLineMode(), 150, 50, null);
				else if (lose)
					g.drawImage(ImageProvider.getYouLoseOnLineMode(), 150, 50, null);
			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setLayout(null);
		JDialog dialog = new JDialog();
		JButton yes = new JButton(new ImageIcon(ImageProvider.getYes()));
		yes.setBounds(150, 200, 100, 50);
		yes.setContentAreaFilled(false);
		yes.setBorder(null);
		yes.setFocusPainted(false);
		yes.setRolloverEnabled(true);
		yes.setRolloverIcon(new ImageIcon(ImageProvider.getYesRollovered()));
		yes.setVisible(true);
		JButton no = new JButton(new ImageIcon(ImageProvider.getNo()));
		no.setBounds(300, 200, 100, 50);
		no.setContentAreaFilled(false);
		no.setBorder(null);
		no.setFocusPainted(false);
		no.setRolloverEnabled(true);
		no.setRolloverIcon(new ImageIcon(ImageProvider.getNoRollovered()));
		no.setVisible(true);
		panel.add(yes);
		panel.add(no);

		yes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.switchToInitialPanel();
				dialog.dispose();
			}
		});
		no.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				dialog.dispose();
				System.exit(0);
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("End of Game");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showAlreadyUsed() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(12), 0, 0, null);

				g.drawImage(ImageProvider.getSelectAnotherName(), 150, 50, null);
			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setLayout(null);
		JDialog dialog = new JDialog();
		JButton yes = new JButton(new ImageIcon(ImageProvider.getYes()));
		yes.setBounds(150, 200, 100, 50);
		yes.setContentAreaFilled(false);
		yes.setBorder(null);
		yes.setFocusPainted(false);
		yes.setRolloverEnabled(true);
		yes.setRolloverIcon(new ImageIcon(ImageProvider.getYesRollovered()));
		yes.setVisible(true);

		panel.add(yes);

		yes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				dialog.dispose();
				principalMainFrame.setPlayerName("");
				principalMainFrame.switchToInitialPanel();

			}
		});

		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Select Another Name");
		dialog.setSize(500, 300);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);

	}

	private class MyListRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;
		private HashMap<Object, String> theChosen = new HashMap<Object, String>();

		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (isSelected) {
				theChosen.put(value, "chosen");
				setForeground(Color.RED);
			}
			return (this);
		}
	}

}
