package igpe.billiard.gui;

import igpe.billiard.core.GameMode;
import igpe.billiard.core.HowToPlay;
import igpe.billiard.core.MatchManager;
import igpe.billiard.core.Modality;
import igpe.billiard.file.FileProvider;
import igpe.billiard.sound.SoundProvider;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static final int STANDARD_HEIGHT = 720;
	protected static final int STANDAR_WIDTH = 1200;
	protected static final int GAME_HEIGHT = 810;
	protected static final int GAME_WIDTH = 1240;
	protected static final int DIALOG_HEIGHT = 300;
	protected static final int DIALOG_WIDTH = 500;

	private LoadSave loadSavePanel;
	private LevelEditorPanel levelEditorPanel;
	private GamePanel gamePanel;
	private InitialPanel initialPanel;
	private ResumeContinue resumeContinuePanel;
	private LoadPanel loadPanel;
	private ChooseHowToPlayPanel chooseHowToPlayPanel;
	private SettingPanel settingsPanel;
	private PausePanel pausePanel;
	private SavePanel savePanel;
	private SoundProvider soundOfTheGame;
	private boolean musicIsActivate = true;
	private String playerName=new String("");
	private Modality modalityOfTheGame = Modality.OFFLINE;

	public MainFrame() {
		setPreferredSize(new Dimension(STANDAR_WIDTH, STANDARD_HEIGHT));
		setMinimumSize(new Dimension(STANDAR_WIDTH, STANDARD_HEIGHT));
		setMaximumSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("P & F's Pool");
		setIconImage(ImageProvider.getBlackBall());
		pack();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				showExitConfirm();
			}
		});
		FileProvider.setPrincipalMainFrame(this);
		initialPanel = new InitialPanel(this);
		gamePanel = new GamePanel(this);
		settingsPanel = new SettingPanel(this);
		resumeContinuePanel = new ResumeContinue(this);
		chooseHowToPlayPanel = new ChooseHowToPlayPanel(this);
		loadPanel = new LoadPanel(this);
		
		pausePanel = new PausePanel(this);
		savePanel = new SavePanel(this, GameMode.MATCH);
		loadSavePanel = new LoadSave(this, GameMode.MATCH);
		modalityOfTheGame = Modality.OFFLINE;
		soundOfTheGame = new SoundProvider();
		playerName = new String("");
		setContentPane(initialPanel);
		activeMusic();
	}

	protected Modality getModalityOfTheGame() {
		return modalityOfTheGame;
	}

	protected String getPlayerName() {
		return playerName;
	}

	protected GamePanel getGamePanel() {
		return gamePanel;
	}

	protected void setModalityOfTheGame(final Modality modalityOfTheGame) {
		this.modalityOfTheGame = modalityOfTheGame;
	}

	protected void setPlayerName(final String playerName) {
		this.playerName = playerName;
	}

	protected void setMusicOn(final boolean musicOn) {
		this.musicIsActivate = musicOn;
	}

	protected void showExitConfirm() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(8), 0, 0, null);
				g.drawImage(ImageProvider.getDoYouReallyWantToExit(), 150, 50,
						null);
			}
		};
		panel.setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		panel.setMaximumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
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
				dialog.dispose();
				System.exit(0);
			}
		});
		no.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Exit");
		dialog.setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);

	}

	protected void showDoYouWantToGoOutAndDisconnectFromTheGame() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(8), 0, 0, null);
				g.drawImage(ImageProvider
						.getDoYouWantToGoOutAndDisconnectFromTheGame(), 150,
						50, null);
			}
		};
		panel.setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		panel.setMaximumSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
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
				dialog.dispose();
				System.exit(0);
			}
		});
		no.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Exit");
		dialog.setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);

	}

	private void changeSizeTheScreen(final int mode) {
		if (mode == 1) {
			setPreferredSize(new Dimension(STANDAR_WIDTH, STANDARD_HEIGHT));
			setMinimumSize(new Dimension(STANDAR_WIDTH, STANDARD_HEIGHT));
			setMaximumSize(new Dimension(STANDAR_WIDTH, STANDARD_HEIGHT));
		} else if (mode == 2) {
			setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
			setMinimumSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
			setMaximumSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		}
	}

	public void startGame(final HowToPlay howToPlay, final int indexOfSave) {
		changeSizeTheScreen(2);
		gamePanel = new GamePanel(this);

		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			gamePanel.setHowToPlay(howToPlay, indexOfSave);
			setContentPane(gamePanel);
			gamePanel.requestFocus();
			try {
				gamePanel.start();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Impossible Start Game",
						"Start Game Error", JOptionPane.ERROR_MESSAGE);
				this.switchToInitialPanel();
			}
			gamePanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	MatchManager startNetworkGame(final ConnectionManager connectionManager) {
		changeSizeTheScreen(2);
		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			setContentPane(gamePanel);
			gamePanel.updateUI();
			gamePanel.requestFocus();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});

		return gamePanel.startNetworkGame(connectionManager);
	}

	public void startLevelEditor(final int index) {
		changeSizeTheScreen(2);
		levelEditorPanel = new LevelEditorPanel(this);
		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			levelEditorPanel.resumeLevelCreated(index);
			try {
				levelEditorPanel.start();
			} catch (Exception e) {
				JOptionPane
						.showMessageDialog(this,
								"Impossible Load or Created Level",
								"Load or Create Level Error",
								JOptionPane.ERROR_MESSAGE);
				this.switchToInitialPanel();
			}
			setContentPane(levelEditorPanel);
			levelEditorPanel.requestFocus();
			levelEditorPanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	public void switchToChooseHowToPlayPanel() {
		changeSizeTheScreen(1);
		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			setContentPane(chooseHowToPlayPanel);
			chooseHowToPlayPanel.requestFocus();
			chooseHowToPlayPanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	public void switchToSavePanel(final GameMode gameMode) {
		changeSizeTheScreen(2);
		savePanel = new SavePanel(this, gameMode);
		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			setContentPane(savePanel);
			savePanel.requestFocus();
			savePanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	public void switchToPausePanelFromSettingPanel() {
		changeSizeTheScreen(2);
		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			gamePanel.repaint();
			gamePanel.setImageScreenFile();
			setContentPane(pausePanel);
			pausePanel.requestFocus();
			pausePanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	public void switchToSettingsPanel() {
		changeSizeTheScreen(2);
		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			setContentPane(settingsPanel);
			settingsPanel.requestFocus();
			settingsPanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	public void switchToPausePanel() {
		changeSizeTheScreen(2);
		SwingUtilities.invokeLater(() -> {
			setContentPane(pausePanel);
			pausePanel.requestFocus();
			pausePanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});

	}

	public void switchToConnectionPanel() {
		JDialog dialog = new JDialog(this);
		if(playerName.equals(""))
		insertNameoOfThePlayer();
		
		dialog.setModal(true);
		dialog.setPreferredSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
		dialog.setContentPane(new ConnectionPanel(this, dialog));
		dialog.pack();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	public void switchToGamePanelfromPausePanel() {
		changeSizeTheScreen(2);
		SwingUtilities.invokeLater(() -> {
			gamePanel.removePause();
			setVisible(false);
			setContentPane(gamePanel);
			gamePanel.requestFocus();
			gamePanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});

	}

	public void switchToInitialPanel() {
		changeSizeTheScreen(1);
		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			setContentPane(initialPanel);
			initialPanel.requestFocus();
			initialPanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	public void switchToResumeContinuePanel() {
		changeSizeTheScreen(1);
		
		if(playerName.equals(""))
		insertNameoOfThePlayer();
		
		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			setContentPane(resumeContinuePanel);
			resumeContinuePanel.requestFocus();
			resumeContinuePanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	public void switchToLoadOrSave(final GameMode gameMode) {
		changeSizeTheScreen(2);
		loadSavePanel = new LoadSave(this, gameMode);
		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			setContentPane(loadSavePanel);
			loadSavePanel.requestFocus();
			loadSavePanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	public void switchToLoadPanel() {
		changeSizeTheScreen(1);
		SwingUtilities.invokeLater(() -> {
			setVisible(false);
			setContentPane(loadPanel);
			loadPanel.requestFocus();
			loadPanel.updateUI();
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		});
	}

	public void activeMusic() {
		soundOfTheGame.activeMusic(musicIsActivate);
	}

	public void stopMusic() {
		soundOfTheGame.stopMusic();
	}

	public void pauseMusic() {
		soundOfTheGame.pauseMusic();
	}

	public void nextMusic() {
		soundOfTheGame.nextMusic();

	}

	public void previusMusic() {
		soundOfTheGame.previusMusic();
	}

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
	
	private void insertNameoOfThePlayer() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(1), 0, 0, null);
				g.drawImage(ImageProvider.getInsertYourName(), 170, 50, null);

			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setLayout(null);

		JTextField name = new JTextField();
		name.setVisible(true);
		name.setBounds(190, 155, 200, 30);
		name.setText("Username");
		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				name.setText("");
			}
		});
		panel.add(name);
		JDialog dialog = new JDialog();
		JButton ok = new JButton(new ImageIcon(ImageProvider.getOk()));
		ok.setBounds(240, 200, 100, 50);
		ok.setContentAreaFilled(false);
		ok.setBorder(null);
		ok.setFocusPainted(false);
		ok.setRolloverEnabled(true);
		ok.setRolloverIcon(new ImageIcon(ImageProvider.getOkRollovered()));
		ok.setVisible(true);
		panel.add(ok);
		ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);

				if (!name.getText().equals("")
						&& !name.getText().equals("Username")) {
					dialog.dispose();
					setPlayerName(name.getText());
				}
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);

	}

}
