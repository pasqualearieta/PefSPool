package igpe.billiard.gui;

import igpe.billiard.core.Ball;
import igpe.billiard.core.ColorBall;
import igpe.billiard.core.NameofCommand;
import igpe.billiard.core.PowerIndicator;
import igpe.billiard.core.GameMode;
import igpe.billiard.core.HowToPlay;
import igpe.billiard.core.MatchManager;
import igpe.billiard.core.Modality;
import igpe.billiard.file.FileProvider;

import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	private MainFrame principalMainFrame;
	private MatchManager matchManager;
	private HowToPlay howToPlay;
	private DirectionPaint directionPaint;
	private StickPaint stickPaint;
	private BufferedImage bufferedImage;
	private Image screenStatusOfTheGame;
	private ConnectionManager connectionManager;
	private int indexOfRescue = -1;
	private StringBuilder historyChat = null;
	private boolean showChatOfTheGame = false;
	private boolean rolloverChat = false;

	private JFrame chat;
	private JTextArea textArea;

	public GamePanel(final MainFrame mainFrame) {

		principalMainFrame = mainFrame;

		setPreferredSize(new Dimension(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT));
		setMinimumSize(new Dimension(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT));
		setMaximumSize(new Dimension(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT));

		setBackground(Color.GRAY.darker().darker());
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		matchManager = new MatchManager(principalMainFrame.getModalityOfTheGame());
		directionPaint = new DirectionPaint();
		stickPaint = new StickPaint();
		howToPlay = HowToPlay.MEVSME;

		matchManager.getFirstPlayer().setName(principalMainFrame.getPlayerName());

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == FileProvider.getCommand(NameofCommand.PAUSE)) {
					if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE) {
						setImageScreenFile();
						FileProvider.setCurrentStatusOfTheGameIntoAFile(GameMode.MATCH,
								matchManager.getCurrentStatusOfTheGame().toString());
						matchManager.setPause(true);
						principalMainFrame.switchToPausePanel();
					} else
						principalMainFrame.showDoYouWantToGoOutAndDisconnectFromTheGame();
				}

				if (e.getKeyCode() == FileProvider.getCommand(NameofCommand.DIRECTION_RIGHT)) {
					if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE)
						matchManager.moveRight();
					else {
						StringBuilder sb = new StringBuilder();
						sb.append(principalMainFrame.getPlayerName() + ":" + "RIGHT");
						signalServer(sb.toString());
					}
				}
				if (e.getKeyCode() == FileProvider.getCommand(NameofCommand.DIRECTION_LEFT)) {
					if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE)
						matchManager.moveLeft();
					else {
						StringBuilder sb = new StringBuilder();
						sb.append(principalMainFrame.getPlayerName() + ":" + "LEFT");
						signalServer(sb.toString());
					}
				}
				if (e.getKeyCode() == FileProvider.getCommand(NameofCommand.INCREMENT_POWER)) {
					if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE)
						matchManager.moveUp();
					else {
						StringBuilder sb = new StringBuilder();
						sb.append(principalMainFrame.getPlayerName() + ":" + "UP");
						signalServer(sb.toString());
					}
				}
				if (e.getKeyCode() == FileProvider.getCommand(NameofCommand.DECREMENT_POWER)) {
					if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE)
						matchManager.moveDown();
					else {
						StringBuilder sb = new StringBuilder();
						sb.append(principalMainFrame.getPlayerName() + ":" + "DOWN");
						signalServer(sb.toString());
					}
				}
				if (e.getKeyCode() == FileProvider.getCommand(NameofCommand.HIT)) {
					if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE)
						matchManager.hit();
					else {
						StringBuilder sb = new StringBuilder();
						sb.append(principalMainFrame.getPlayerName() + ":" + "HIT");
						signalServer(sb.toString());
					}
				}
			};

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE)
					matchManager.stop();
				else {
					StringBuilder sb = new StringBuilder();
					sb.append(principalMainFrame.getPlayerName() + ":" + "STOP");
					signalServer(sb.toString());
				}

			}
		});
	}

	public void start() throws IOException {

		this.requestFocus();

		matchManager.setHowToPlay(howToPlay, indexOfRescue);
		matchManager.start(new Runnable() {
			@Override
			public void run() {

				if (matchManager.getInterrupted())
					showInterrupted();
				else
					repaint();
			}
		});
	}

	MatchManager startNetworkGame(final ConnectionManager connectionManager) {
		historyChat = new StringBuilder("");
		// showChat();
		// principalMainFrame.setSize(new Dimension(MatchManager.WIDTH,
		// MatchManager.HEIGHT));
		this.connectionManager = connectionManager;
		requestFocus();

		matchManager.startNetworkGame();
		return matchManager;
	}

	public void setHowToPlay(final HowToPlay howToPlay, final int indexOfRescue) {
		this.howToPlay = howToPlay;
		this.indexOfRescue = indexOfRescue;
	}

	public Image getScreen() {
		return screenStatusOfTheGame;
	}

	public void setEndOfGame() {
		matchManager.setEndOfGame(true);
	}

	public void setImageScreenFile() {
		this.bufferedImage = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
		this.paint(bufferedImage.createGraphics());
		this.screenStatusOfTheGame = bufferedImage;
		FileProvider.setCurrentStatusTheScreenOfTheGameIntoAFile(GameMode.MATCH, bufferedImage);
	}

	public void signalServer(final String status) {
		if (connectionManager != null)
			connectionManager.dispatch(status);
	}

	public void removePause() {
		matchManager.setPause(false);
	}

	private void showInterrupted() {
		JOptionPane.showMessageDialog(this, "Impossible continue to play, the game is close.", "Error",
				JOptionPane.ERROR_MESSAGE);

		principalMainFrame.switchToInitialPanel();

	}

	private void showChat() {
		chat = new JFrame();
		chat.setResizable(false);
		chat.setBackground(Color.WHITE);
		chat.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		chat.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				showChatOfTheGame = false;
				rolloverChat = false;
				chat.dispose();
			}
		});
		chat.setSize(400, 400);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setSize(400, 400);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setVisible(true);

		TextField textToSend = new TextField();

		textToSend.setPreferredSize(new Dimension(350, 50));

		JButton send = new JButton(new ImageIcon(ImageProvider.getSendMessage()));
		send.setPreferredSize(new Dimension(350, 50));
		send.setContentAreaFilled(false);
		send.setBorder(null);
		send.setFocusPainted(false);
		send.setRolloverEnabled(true);
		send.setRolloverIcon(new ImageIcon(ImageProvider.getSendMessageRollovered()));
		send.setVisible(true);

		JButton clear = new JButton(new ImageIcon(ImageProvider.getClearChat()));
		clear.setPreferredSize(new Dimension(350, 50));
		clear.setContentAreaFilled(false);
		clear.setBorder(null);
		clear.setFocusPainted(false);
		clear.setRolloverEnabled(true);
		clear.setRolloverIcon(new ImageIcon(ImageProvider.getClearChatRollovered()));
		clear.setVisible(true);
		clear.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				historyChat = new StringBuilder("");
				textArea.setText(historyChat.toString());
				textArea.revalidate();
			}
		});

		textToSend.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!textToSend.getText().equals("")) {
						String b = new String(
								"#CHAT:" + textToSend.getText() + "&&" + principalMainFrame.getPlayerName());
						signalServer(b);

						textToSend.setText("");
					}

				}
			}
		});
		send.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				if (!textToSend.getText().equals("")) {
					String b = new String("#CHAT:" + textToSend.getText() + "&&" + principalMainFrame.getPlayerName());
					signalServer(b);
					textToSend.setText("");
				}

			}
		});

		textArea = new JTextArea(historyChat.toString());
		textArea.setAutoscrolls(true);
		textArea.setFont(new Font("BOLD", Font.ITALIC, 20));
		textArea.setForeground(Color.WHITE);
		textArea.setEditable(false);
		JPanel panelButton = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			}

		};

		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));

		panelButton.setVisible(true);

		panelButton.add(send);
		panelButton.add(clear);

		JPanel app = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.drawImage(ImageProvider.getBackgroundChat(), 0, 0, null);
			}

		};

		app.add(textArea);
		JScrollPane scrool = new JScrollPane(app);
		scrool.setAutoscrolls(true);
		scrool.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrool.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrool.setVisible(true);

		textArea.setBackground(new Color(255, 255, 255, 0));

		textArea.setOpaque(false);
		textArea.setPreferredSize(new Dimension(400, 350));
		textArea.setVisible(true);

		panel.add(scrool);
		panel.add(panelButton);
		panel.add(textToSend);

		chat.add(panel);
		chat.setVisible(true);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE) {
			if (!matchManager.getPhysicsBallMovement()
					.checkThatThereAreNoOverlappingBalls(matchManager.getBallsOnTheTable())) {
				matchManager.setWhiteBallReposition();
				matchManager.setRepositionWithMouse(false);
			} else {
				JOptionPane.showInternalConfirmDialog(GamePanel.this,
						"You can not reposition WhiteBall over other Ball", "", JOptionPane.CLOSED_OPTION);
				Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				repaint();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE) {
			final Cursor cursor;
			final Toolkit toolkit = getToolkit();
			final Point cursorHotSpot = new Point(15, 15);
			if (matchManager.getPhysicsBallMovement()
					.checkThatThereAreNoOverlappingBalls(matchManager.getBallsOnTheTable()))
				cursor = toolkit.createCustomCursor(ImageProvider.getDivietoPointer(), cursorHotSpot, "wrong");
			else
				cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
			setCursor(cursor);
			if (matchManager.getPhysicsBallMovement().checkThatTheBallDoesNotExceedTheEdgesOfTheTable(e.getX(),
					e.getY(), matchManager.getTable())) {
				matchManager.setXMouse(e.getX());
				matchManager.setYMouse(e.getY());
				this.repaint();
			}
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (principalMainFrame.getModalityOfTheGame() == Modality.ONLINE && !showChatOfTheGame) {
			if (e.getX() >= 25 && e.getX() <= 85 && e.getY() >= 620 && e.getY() <= 680)
				rolloverChat = true;
			else
				rolloverChat = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// ignore these events
		if (principalMainFrame.getModalityOfTheGame() == Modality.ONLINE && !showChatOfTheGame) {
			if (e.getX() >= 25 && e.getX() <= 85 && e.getY() >= 620 && e.getY() <= 680) {
				showChatOfTheGame = true;
				showChat();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// ignore these events
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// ignore these events
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE) {
			if (matchManager.getWhiteBallReposition()) {
				if (matchManager.getPhysicsBallMovement().checkThatTheMouseIntersectWithAWhiteBall(e.getX(), e.getY(),
						matchManager.getBallsOnTheTable())) {
					matchManager.setRepositionWithMouse(true);
					matchManager.setXMouse(e.getX());
					matchManager.setYMouse(e.getY());
				}
			}
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(ImageProvider.getBackgroundGamePanel(), 0, 0, null);

		if (principalMainFrame.getModalityOfTheGame() == Modality.OFFLINE) {
			if (matchManager.getFirstPlayer().isWin() || matchManager.getSecondPlayer().isWin()) {
				if (matchManager.canPrintWinOrLose()) {
					if (matchManager.getIAmPlayingWithArtificialIntelligence() && matchManager.getFirstPlayer().isWin())
						g.drawImage(ImageProvider.getYouWin(), 300, 300, null);
					else if (matchManager.getIAmPlayingWithArtificialIntelligence()
							&& matchManager.getSecondPlayer().isWin())
						g.drawImage(ImageProvider.getYouLose(), 300, 300, null);
					else
						g.drawImage(ImageProvider.getYouWin(), 300, 300, null);
				} else
					principalMainFrame.switchToInitialPanel();

				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
			}
		}

		g.drawImage(ImageProvider.getTableImage().get(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED)),
				matchManager.getTable().getX() - 49, matchManager.getTable().getY() - 45, null);
		// Print chronometer for the turn
		final long minute1 = matchManager.getChronometerOfTheMatch().getMinute() / 10;
		final long minute2 = matchManager.getChronometerOfTheMatch().getMinute() % 10;
		final long second1 = matchManager.getChronometerOfTheMatch().getElapsedTime() % 60 / 10;
		final long second2 = matchManager.getChronometerOfTheMatch().getElapsedTime() % 60 % 10;

		g.drawImage(ImageProvider.getNumber((int) minute1), 520, 110, null);
		g.drawImage(ImageProvider.getNumber((int) minute2), 560, 110, null);

		g.drawImage(ImageProvider.getNumber(10), 600, 112, null);
		g.drawImage(ImageProvider.getNumber((int) second1), 640, 110, null);
		g.drawImage(ImageProvider.getNumber((int) second2), 680, 110, null);

		if ((matchManager.getFirstPlayer().getName().equals(principalMainFrame.getPlayerName())
				&& matchManager.getFirstPlayer().isTurn()))
			g.drawImage(ImageProvider.getFirstPlayerTurn(), 15, 80, null);
		if (matchManager.getSecondPlayer().getName().equals(principalMainFrame.getPlayerName())
				&& matchManager.getSecondPlayer().isTurn())
			g.drawImage(ImageProvider.getSecondPlayerTurn(), 1020, 70, null);

		if (matchManager.getIsThirdStepOfTheGame()) {
			if (matchManager.getFirstPlayer().getColorBall() == ColorBall.YELLOW) {
				if (matchManager.getYellowBalls().size() < MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
					g.drawImage(ImageProvider.getContainerYellowBall().get(matchManager.getYellowBalls().size()), 150,
							700, null);
				else if (matchManager.getYellowBalls().size() == MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
					g.drawImage(ImageProvider.getContainerBlackBall(), 150, 700, null);
				if (matchManager.getRedBalls().size() < MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
					g.drawImage(ImageProvider.getContainerRedBall().get(matchManager.getRedBalls().size()), 800, 700,
							null);
				else if (matchManager.getRedBalls().size() == MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
					g.drawImage(ImageProvider.getContainerBlackBall(), 800, 700, null);
			} else {
				if (matchManager.getYellowBalls().size() < MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
					g.drawImage(ImageProvider.getContainerYellowBall().get(matchManager.getYellowBalls().size()), 800,
							700, null);
				else if (matchManager.getYellowBalls().size() == MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
					g.drawImage(ImageProvider.getContainerBlackBall(), 800, 700, null);
				if (matchManager.getRedBalls().size() < MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
					g.drawImage(ImageProvider.getContainerRedBall().get(matchManager.getRedBalls().size()), 150, 700,
							null);
				else if (matchManager.getRedBalls().size() == MatchManager.MAX_SIZE_SET_OF_BALL_PLAYER)
					g.drawImage(ImageProvider.getContainerBlackBall(), 150, 700, null);
			}
		}

		if (principalMainFrame.getModalityOfTheGame() == Modality.ONLINE) {
			if (!rolloverChat && !showChatOfTheGame)
				g.drawImage(ImageProvider.getChatIcon(), 25, 620, null);
			else if (!showChatOfTheGame && rolloverChat)
				g.drawImage(ImageProvider.getChatIcon().getScaledInstance(65, 65, Image.SCALE_SMOOTH), 25, 620, null);
		}
		// print Chronometer first Player
		if (matchManager.getFirstPlayer().isTurn()) {
			final long second1Match = ((MatchManager.MAX_WAIT_TIME_TO_PLAY_THE_TURN
					- matchManager.getChronometerOfTheTurn().getElapsedTime()) - 1) / 10;
			final long second2Match = ((MatchManager.MAX_WAIT_TIME_TO_PLAY_THE_TURN
					- matchManager.getChronometerOfTheTurn().getElapsedTime()) - 1) % 10;
			final int posCrhonometer = 100 + (35 * matchManager.getFirstPlayer().getName().length()) / 2;

			if (second1Match != 0)
				g.drawImage(ImageProvider.getNumber((int) second1Match), posCrhonometer - 20, 90, null);
			g.drawImage(ImageProvider.getNumber((int) second2Match), posCrhonometer + 20, 90, null);

			final long second1VS = MatchManager.MAX_WAIT_TIME_TO_PLAY_THE_TURN / 10;
			final long second2VS = MatchManager.MAX_WAIT_TIME_TO_PLAY_THE_TURN % 10;
			final int posCrhonometer2 = 750 + (35 * matchManager.getSecondPlayer().getName().length()) / 2;

			g.drawImage(ImageProvider.getNumber((int) second1VS), posCrhonometer2 - 20, 90, null);
			g.drawImage(ImageProvider.getNumber((int) second2VS), posCrhonometer2 + 20, 90, null);
		} else if (matchManager.getSecondPlayer().isTurn()) {// Print
																// chronometer
																// second player
			final long second1VS = MatchManager.MAX_WAIT_TIME_TO_PLAY_THE_TURN / 10;
			final long second2VS = MatchManager.MAX_WAIT_TIME_TO_PLAY_THE_TURN % 10;
			final int posCrhonometer = 100 + (35 * matchManager.getFirstPlayer().getName().length()) / 2;

			g.drawImage(ImageProvider.getNumber((int) second1VS), posCrhonometer - 20, 90, null);
			g.drawImage(ImageProvider.getNumber((int) second2VS), posCrhonometer + 20, 90, null);

			final long second1Match = ((MatchManager.MAX_WAIT_TIME_TO_PLAY_THE_TURN
					- matchManager.getChronometerOfTheTurn().getElapsedTime()) - 1) / 10;
			final long second2Match = ((MatchManager.MAX_WAIT_TIME_TO_PLAY_THE_TURN
					- matchManager.getChronometerOfTheTurn().getElapsedTime()) - 1) % 10;
			final int posCrhonometer2 = 750 + (35 * matchManager.getSecondPlayer().getName().length()) / 2;

			if (second1Match != 0)
				g.drawImage(ImageProvider.getNumber((int) second1Match), posCrhonometer2 - 20, 90, null);

			g.drawImage(ImageProvider.getNumber((int) second2Match), posCrhonometer2 + 20, 90, null);
		}

		// Print name first Player
		int xPositionCharFirstPlayerName = 110;
		final int yPositionCharFirstPlayerName = 35;
		for (final char c : matchManager.getFirstPlayer().getName().toString().toCharArray()) {
			final int key = KeyEvent.getExtendedKeyCodeForChar(c);
			g.drawImage(ImageProvider.getKeyPressed(key + ""), xPositionCharFirstPlayerName,
					yPositionCharFirstPlayerName, null);
			xPositionCharFirstPlayerName += 35;
		}
		int xPositionCharSecondPlayerName = 750;
		final int yPositionCharSecondPlayerName = 35;

		// Print name second Player
		for (char c : matchManager.getSecondPlayer().getName().toString().toCharArray()) {
			final int key = KeyEvent.getExtendedKeyCodeForChar(c);
			g.drawImage(ImageProvider.getKeyPressed(key + ""), xPositionCharSecondPlayerName,
					yPositionCharSecondPlayerName, null);
			xPositionCharSecondPlayerName += 35;
		}

		for (final Ball ball : matchManager.getBallsOnTheTable()) {
			if (!ball.getIsPocketed()) {
				if (ball.getIdentificativeNumber() == 0)
					g.drawImage(ImageProvider.getWhiteBall(), ball.getX(), ball.getY(), null);
				if (ball.getIdentificativeNumber() == 8)
					g.drawImage(ImageProvider.getBlackBall(), ball.getX(), ball.getY(), null);
				if (ball.getIdentificativeNumber() >= 1 && ball.getIdentificativeNumber() <= 7)
					g.drawImage(ImageProvider.getYellowBall(), ball.getX(), ball.getY(), null);
				if (ball.getIdentificativeNumber() >= 9 && ball.getIdentificativeNumber() <= 15)
					g.drawImage(ImageProvider.getRedBall(), ball.getX(), ball.getY(), null);
			}
		}

		int xdraw;
		if (matchManager.getFirstPlayer().isTurn()) {

			g.drawImage(ImageProvider.getPowerIndicator(), matchManager.getPowerIndicator().getX(),
					matchManager.getPowerIndicator().getY(), null);
			xdraw = matchManager.getPowerIndicator().getX() + 10;

		} else {
			g.drawImage(ImageProvider.getPowerIndicator(), matchManager.getPowerIndicator().getX() - 1050,
					matchManager.getPowerIndicator().getY(), null);

			xdraw = matchManager.getPowerIndicator().getX() - 1050 + 10;
		}
		int ydraw = matchManager.getPowerIndicator().getY() + PowerIndicator.getHeight() - 50;
		for (int i = 0; i < (int) matchManager.getPowerIndicator().getPower(); i++) {
			g.drawImage(ImageProvider.getPower(i), xdraw, ydraw, null);
			ydraw -= 13;
		}

		g.setColor(Color.GRAY.brighter().brighter().brighter());
		g.setColor(Color.RED);

		if (!matchManager.getIAmPlayingMevsMe()) {
			if (matchManager.canPrintFoul()) {
				if (matchManager.getFoulBallHitTheOppositeColor() && (matchManager.getFirstPlayer().isMakeFoul()
						&& matchManager.getFirstPlayer().getName().equals(principalMainFrame.getPlayerName())
						|| matchManager.getSecondPlayer().isMakeFoul() && matchManager.getSecondPlayer().getName()
								.equals(principalMainFrame.getPlayerName()))) {
					g.drawImage(ImageProvider.getOpponentfirstBallTouchedIsTheOppositeColorFoul()
							.get(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED)), 400, 700, null);

				} else if (matchManager.getFoulBallHitTheOppositeColor())
					g.drawImage(ImageProvider.getFirstBallTouchedIsTheOppositeColorFoul()
							.get(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED)), 400, 700, null);

				if (matchManager.getFoulWhiteBallPocketed() && (matchManager.getFirstPlayer().isMakeFoul()
						&& matchManager.getFirstPlayer().getName().equals(principalMainFrame.getPlayerName())
						|| matchManager.getSecondPlayer().isMakeFoul() && matchManager.getSecondPlayer().getName()
								.equals(principalMainFrame.getPlayerName()))) {
					g.drawImage(ImageProvider.getOpponentWhiteBallFoulImage()
							.get(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED)), 400, 700, null);
				} else if (matchManager.getFoulWhiteBallPocketed())
					g.drawImage(ImageProvider.getWhiteBallFoulImage()
							.get(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED)), 400, 700, null);
				if (matchManager.getFoulNoneBallsTouched() && (matchManager.getFirstPlayer().isMakeFoul()
						&& matchManager.getFirstPlayer().getName().equals(principalMainFrame.getPlayerName())
						|| matchManager.getSecondPlayer().isMakeFoul() && matchManager.getSecondPlayer().getName()
								.equals(principalMainFrame.getPlayerName()))) {
					g.drawImage(ImageProvider.getOpponentnoneBallTouchedFoulImage()
							.get(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED)), 400, 700, null);
				} else if (matchManager.getFoulNoneBallsTouched())
					g.drawImage(ImageProvider.getNoneBallTouchedFoulImage()
							.get(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED)

					), 400, 700, null);
			}
		} else if (matchManager.getIAmPlayingMevsMe()) {
			if (matchManager.canPrintFoul()) {
				if (matchManager.getFoulBallHitTheOppositeColor()) {
					g.drawImage(ImageProvider.getFirstBallTouchedIsTheOppositeColorFoul()
							.get(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED)), 400, 700, null);

				} else if (matchManager.getFoulNoneBallsTouched()) {
					g.drawImage(ImageProvider.getNoneBallTouchedFoulImage()
							.get(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED)

					), 400, 700, null);

				} else if (matchManager.getFoulWhiteBallPocketed()) {
					g.drawImage(ImageProvider.getWhiteBallFoulImage()
							.get(FileProvider.getCommand(NameofCommand.INDEX_TABLE_SELECTED)), 400, 700, null);
				}
			}
		}

		if (principalMainFrame.getModalityOfTheGame() == Modality.ONLINE) {
			if (matchManager.getPhysicsBallMovement().everyBallsStopped(matchManager.getBallsOnTheTable())
					&& !matchManager.getWhiteBallReposition() && !matchManager.getStick().getIsHit()) {

				stickPaint.paint(g, matchManager.getStick().getAngleOfRotation(), matchManager.getStick().getX(),
						matchManager.getStick().getY());
				directionPaint.paintDirectionOfStick(g, matchManager.getStick().getX(), matchManager.getStick().getY());
			}
		}

		else {
			if (matchManager.getPhysicsBallMovement().everyBallsStopped(matchManager.getBallsOnTheTable())
					&& !matchManager.getWhiteBallReposition()) {
				stickPaint.paint(g, matchManager.getStick().getAngleOfRotation(), matchManager.getStick().getX(),
						matchManager.getStick().getY());
				if (matchManager.getFirstPlayer().isTurn() && matchManager.getIAmPlayingWithArtificialIntelligence()
						|| !matchManager.getIAmPlayingWithArtificialIntelligence())
					directionPaint.paintDirectionOfStick(g, matchManager.getStick().getX(),
							matchManager.getStick().getY());
			}
		}
	}

	public String getHistoryChat() {
		return historyChat.toString();
	}

	public void setHistoryChat(String historyChat) {

		this.historyChat.append(historyChat + "\n");
		if (showChatOfTheGame) {
			textArea.setText(this.historyChat.toString() + "\n");
			textArea.revalidate();
		}

	}
}
