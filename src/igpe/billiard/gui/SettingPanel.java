package igpe.billiard.gui;

import igpe.billiard.core.MatchManager;
import igpe.billiard.file.FileProvider;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class SettingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainFrame principalMainFrame;
	private JButton hit;
	private JButton rotateStickRight;
	private JButton rotateStickLeft;
	private JButton incrementPower;
	private JButton decrementPower;
	private JButton pause;
	private JButton saveNewCommand;
	private JButton resetAll;
	private JButton confirmButton;
	private JButton previousTable;
	private JButton nextTable;
	private JButton tableButton;
	private JButton musicON;
	private JButton musicOFF;
	private JButton playMusic;
	private JButton stopMusic;
	private JButton pauseMusic;
	private JButton previusMusic;
	private JButton nextMusic;
	private int keyCodePressed = -1;
	private List<String> commandChoose;
	private boolean isHitButtonPressed = false;
	private boolean isRotateRightButtonPressed = false;
	private boolean isRotateLeftButtonPressed = false;
	private boolean isIncrementPowerPressed = false;
	private boolean isDecrementPowerPressed = false;
	private boolean isPausePressed = false;
	private boolean permanentNewCommand = false;
	private boolean changedEffectuated = false;
	private int currentTableSelected = 1;

	public SettingPanel(final MainFrame mainFrame) {
		principalMainFrame = mainFrame;
		commandChoose = FileProvider.getAllCommand();

		setPreferredSize(new Dimension(MainFrame.GAME_WIDTH,
				MainFrame.GAME_HEIGHT));
		setMinimumSize(new Dimension(MainFrame.GAME_WIDTH,
				MainFrame.GAME_HEIGHT));
		setMaximumSize(new Dimension(MainFrame.GAME_WIDTH,
				MainFrame.GAME_HEIGHT));
		setAllButtonPresentOnThePanel();
		requestFocus(true);
		setLayout(new BorderLayout());
		requestFocusInWindow();
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (ImageProvider.getKeyBoard()
						.containsKey(e.getKeyCode() + "")) {
					if (isHitButtonPressed) {
						changedEffectuated = true;
						keyCodePressed = e.getKeyCode();
						checkIfExistsDuplicateKeyForCommandOrAddIt(0);
					}

					else if (isRotateRightButtonPressed) {
						changedEffectuated = true;
						keyCodePressed = e.getKeyCode();
						checkIfExistsDuplicateKeyForCommandOrAddIt(1);
					}

					else if (isRotateLeftButtonPressed) {
						changedEffectuated = true;
						keyCodePressed = e.getKeyCode();
						checkIfExistsDuplicateKeyForCommandOrAddIt(2);
					}

					else if (isIncrementPowerPressed) {
						changedEffectuated = true;
						keyCodePressed = e.getKeyCode();
						checkIfExistsDuplicateKeyForCommandOrAddIt(3);

					}

					else if (isDecrementPowerPressed) {
						changedEffectuated = true;
						keyCodePressed = e.getKeyCode();
						checkIfExistsDuplicateKeyForCommandOrAddIt(4);

					}

					else if (isPausePressed) {
						changedEffectuated = true;
						keyCodePressed = e.getKeyCode();
						checkIfExistsDuplicateKeyForCommandOrAddIt(5);

					}
					repaint();
				}
			}

		});

		JPanel buttonCommandPanel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);
				this.setBackground(Color.GRAY);

				if (commandChoose.get(0) != "-1")
					g.drawImage(
							ImageProvider.getKeyPressed(commandChoose.get(0)),
							250, 80, null);// HIT BUTTON
				else
					g.drawImage(ImageProvider.getDivietoPointer(), 250, 80, 40,
							40, null);// ERROR

				if (commandChoose.get(1) != "-1")
					g.drawImage(
							ImageProvider.getKeyPressed(commandChoose.get(1)),
							250, 180, null);// ROTATE
				// RIGHT //
				// STICK
				// BUTTON
				else
					g.drawImage(ImageProvider.getDivietoPointer(), 250, 180,
							40, 40, null);// ERROR

				if (commandChoose.get(2) != "-1")
					g.drawImage(
							ImageProvider.getKeyPressed(commandChoose.get(2)),
							250, 280, null);// ROTATE LEFT
				// STICK
				// BUTTON
				else
					g.drawImage(ImageProvider.getDivietoPointer(), 250, 280,
							40, 40, null);
				;// ERROR

				if (commandChoose.get(3) != "-1")
					g.drawImage(
							ImageProvider.getKeyPressed(commandChoose.get(3)),
							250, 380, null);// INCREMENT
				// BUTTON
				else
					g.drawImage(ImageProvider.getDivietoPointer(), 250, 380,
							40, 40, null);// ERROR

				if (commandChoose.get(4) != "-1")
					g.drawImage(
							ImageProvider.getKeyPressed(commandChoose.get(4)),
							250, 480, null);// DECREMENT
				// BUTTON
				else
					g.drawImage(ImageProvider.getDivietoPointer(), 250, 480,
							40, 40, null);// ERROR

				if (commandChoose.get(5) != "-1")
					g.drawImage(
							ImageProvider.getKeyPressed(commandChoose.get(5)),
							250, 580, null);// Pause
				// BUTTON
				else
					g.drawImage(ImageProvider.getDivietoPointer(), 250, 580,
							40, 40, null);// ERROR

			}
		};

		buttonCommandPanel.setOpaque(false);
		buttonCommandPanel.setBackground(new Color(0, 0, 0, 1));
		buttonCommandPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		buttonCommandPanel.add(hit, constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		buttonCommandPanel.add(rotateStickRight, constraints);
		constraints.gridx = 0;
		constraints.gridy = 3;
		buttonCommandPanel.add(rotateStickLeft, constraints);
		constraints.gridx = 0;
		constraints.gridy = 4;
		buttonCommandPanel.add(incrementPower, constraints);
		constraints.gridx = 0;
		constraints.gridy = 5;
		buttonCommandPanel.add(decrementPower, constraints);
		constraints.gridx = 0;
		constraints.gridy = 6;
		buttonCommandPanel.add(pause, constraints);

		JPanel buttonSettingPanel = new JPanel();
		buttonSettingPanel.setOpaque(false);
		buttonSettingPanel.setBackground(new Color(0, 0, 0, 1));
		buttonSettingPanel.setLayout(new GridBagLayout());
		constraints.gridx = 1;
		constraints.weightx = 2;
		constraints.gridy = 0;
		buttonSettingPanel.add(resetAll, constraints);
		constraints.gridx = 4;
		constraints.weightx = 2;
		constraints.gridy = 0;
		buttonSettingPanel.add(saveNewCommand, constraints);
		constraints.gridx = 9;
		constraints.weightx = 2;
		constraints.gridy = 0;
		buttonSettingPanel.add(confirmButton, constraints);

		JPanel changeTablePanel = new JPanel();
		changeTablePanel.setOpaque(false);
		changeTablePanel.setBackground(new Color(0, 0, 0, 1));
		changeTablePanel.setLayout(new GridBagLayout());
		constraints.gridx = 1;
		constraints.weightx = 2;
		constraints.gridy = 0;
		changeTablePanel.add(previousTable, constraints);
		constraints.gridx = 6;
		constraints.weightx = 2;
		constraints.gridy = 0;
		changeTablePanel.add(tableButton, constraints);
		constraints.gridx = 12;
		constraints.weightx = 2;
		constraints.gridy = 0;
		changeTablePanel.add(nextTable, constraints);

		previousTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				changedEffectuated = true;
				currentTableSelected = Integer.parseInt(commandChoose.get(6));
				if (currentTableSelected == 1) {
					commandChoose.add(6, 3 + "");
					commandChoose.remove(7);
					currentTableSelected = Integer.parseInt(commandChoose
							.get(6));
					tableButton.setIcon(new ImageIcon(ImageProvider
							.getTableImage().get(currentTableSelected)
							.getScaledInstance(300, 180, 0)));
				} else {
					currentTableSelected = currentTableSelected - 1;
					commandChoose.add(6, currentTableSelected + "");
					commandChoose.remove(7);
					tableButton.setIcon(new ImageIcon(ImageProvider
							.getTableImage().get(currentTableSelected)
							.getScaledInstance(300, 180, 0)));
				}
				repaint();
			}
		});

		nextTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				changedEffectuated = true;
				currentTableSelected = Integer.parseInt(commandChoose.get(6));
				if (currentTableSelected == 3) {
					commandChoose.add(6, 1 + "");
					commandChoose.remove(7);
					currentTableSelected = Integer.parseInt(commandChoose
							.get(6));
					tableButton.setIcon(new ImageIcon(ImageProvider
							.getTableImage().get(currentTableSelected)
							.getScaledInstance(300, 180, 0)));
				} else {
					currentTableSelected = currentTableSelected + 1;
					commandChoose.add(6, currentTableSelected + "");
					commandChoose.remove(7);
					tableButton.setIcon(new ImageIcon(ImageProvider
							.getTableImage().get(currentTableSelected)
							.getScaledInstance(300, 180, 0)));
				}
				repaint();
			}
		});

		JPanel panelSettingMusic = new JPanel(new GridBagLayout());
		GridBagConstraints constraintsMusicPanel = new GridBagConstraints();
		panelSettingMusic.setOpaque(false);
		panelSettingMusic.setPreferredSize(new Dimension(400,
				MatchManager.HEIGHT));
		panelSettingMusic.setBackground(Color.BLACK);
		panelSettingMusic.setBackground(new Color(0, 0, 0, 1));
		constraintsMusicPanel.gridx = 0;
		constraintsMusicPanel.gridy = 1;
		panelSettingMusic.add(musicON, constraintsMusicPanel);

		JPanel panelChooseMusic = new JPanel(new GridBagLayout());
		panelChooseMusic.setOpaque(false);
		GridBagConstraints constraintChoseMusic = new GridBagConstraints();
		constraintChoseMusic.gridx = 0;
		constraintChoseMusic.gridy = 0;
		panelChooseMusic.add(previusMusic, constraintChoseMusic);
		constraintChoseMusic.gridx = 2;
		constraintChoseMusic.gridy = 0;
		panelChooseMusic.add(playMusic, constraintChoseMusic);
		constraintChoseMusic.gridx = 4;
		constraintChoseMusic.gridy = 0;
		panelChooseMusic.add(pauseMusic, constraintChoseMusic);
		constraintChoseMusic.gridx = 6;
		constraintChoseMusic.gridy = 0;
		panelChooseMusic.add(stopMusic, constraintChoseMusic);
		constraintChoseMusic.gridx = 8;
		constraintChoseMusic.gridy = 0;
		panelChooseMusic.add(nextMusic, constraintChoseMusic);
		constraintsMusicPanel.gridx = 0;
		constraintsMusicPanel.gridy = 2;
		panelSettingMusic.add(panelChooseMusic, constraintsMusicPanel);
		constraintsMusicPanel.gridx = 0;
		constraintsMusicPanel.gridy = 3;
		panelSettingMusic.add(musicOFF, constraintsMusicPanel);

		add(panelSettingMusic, BorderLayout.EAST);
		add(changeTablePanel, BorderLayout.WEST);
		add(buttonSettingPanel, BorderLayout.SOUTH);
		add(buttonCommandPanel, BorderLayout.CENTER);

		previusMusic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.previusMusic();
			}
		});

		nextMusic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.nextMusic();
			}

		});
		musicON.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.setMusicOn(true);
				principalMainFrame.activeMusic();
			}

		});

		playMusic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.activeMusic();
			}

		});

		stopMusic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.stopMusic();
			}
		});

		pauseMusic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.pauseMusic();
			}

		});

		musicOFF.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.setMusicOn(false);
				principalMainFrame.stopMusic();
			}
		});

		resetAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				showDoYouReallyWantToResetTheCommands();
			}
		});

		saveNewCommand.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (commandChoose.contains("-1")) {
					for (String s : commandChoose) {
						if (s.equals("-1")) {
							showCommandNotSet(commandChoose.indexOf(s));
							break;
						}
					}
				} else {
					if (changedEffectuated)
						showDoYouWantToSaveYourChangesSoPermanently();
					else
						showYouHaveNotMadeAnyChangesYouWantToGoBack();
				}
			}
		});

		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (commandChoose.contains("-1")) {
					for (String s : commandChoose) {
						if (s.equals("-1")) {
							showCommandNotSet(commandChoose.indexOf(s));
							break;
						}
					}
				} else {
					if (changedEffectuated)
						showDoYouWantToSaveChangesToTheCurrentGame();
					else
						showYouHaveNotMadeAnyChangesYouWantToGoBack();
				}
			}
		});

		hit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				resetBoolean();
				isHitButtonPressed = true;

				hit.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {

						super.keyPressed(e);
					}
				});
			}
		});

		rotateStickRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				resetBoolean();
				isRotateRightButtonPressed = true;
			}

		});

		rotateStickLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				resetBoolean();
				isRotateLeftButtonPressed = true;
			}
		});
		incrementPower.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				resetBoolean();
				isIncrementPowerPressed = true;
			}

		});

		decrementPower.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				resetBoolean();
				isDecrementPowerPressed = true;
			}

		});

		pause.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				resetBoolean();
				isPausePressed = true;
			}
		});

	}

	private void setAllButtonPresentOnThePanel() {
		hit = new JButton(new ImageIcon(ImageProvider.getHitButton()));
		hit.setPreferredSize(new Dimension(250, 100));
		hit.setContentAreaFilled(false);
		hit.setBorder(null);
		hit.setFocusPainted(false);
		hit.setFocusable(false);
		hit.setVisible(true);

		rotateStickRight = new JButton(new ImageIcon(
				ImageProvider.getRotateStickRightButton()));
		rotateStickRight.setPreferredSize(new Dimension(100, 110));
		rotateStickRight.setContentAreaFilled(false);
		rotateStickRight.setBorder(null);
		rotateStickRight.setFocusPainted(false);
		rotateStickRight.setFocusable(false);
		rotateStickRight.setPressedIcon(new ImageIcon(ImageProvider
				.getRotateStickRightButtonRollovered()));
		rotateStickRight.setVisible(true);

		tableButton = new JButton(new ImageIcon(ImageProvider.getTableImage()
				.get(currentTableSelected).getScaledInstance(300, 180, 0)));
		tableButton.setPreferredSize(new Dimension(300, 180));
		tableButton.setContentAreaFilled(false);
		tableButton.setBorder(null);
		tableButton.setFocusPainted(false);
		tableButton.setFocusable(false);

		previousTable = new JButton(new ImageIcon(ImageProvider.getTurnLeft()));
		previousTable.setPreferredSize(new Dimension(100, 110));
		previousTable.setContentAreaFilled(false);
		previousTable.setBorder(null);
		previousTable.setFocusPainted(false);
		previousTable.setFocusable(false);
		previousTable.setPressedIcon(new ImageIcon(ImageProvider
				.getTurnLeftRollovered()));
		previousTable.setVisible(true);

		nextTable = new JButton(new ImageIcon(ImageProvider.getTurnRight()));
		nextTable.setPreferredSize(new Dimension(100, 110));
		nextTable.setContentAreaFilled(false);
		nextTable.setBorder(null);
		nextTable.setFocusPainted(false);
		nextTable.setFocusable(false);
		nextTable.setPressedIcon(new ImageIcon(ImageProvider
				.getTurnRightRollovered()));
		nextTable.setVisible(true);

		rotateStickLeft = new JButton(new ImageIcon(
				ImageProvider.getRotateStickLeftButton()));
		rotateStickLeft.setPreferredSize(new Dimension(100, 110));
		rotateStickLeft.setContentAreaFilled(false);
		rotateStickLeft.setBorder(null);
		rotateStickLeft.setFocusPainted(false);
		rotateStickLeft.setFocusable(false);
		rotateStickLeft.setPressedIcon(new ImageIcon(ImageProvider
				.getRotateStickLeftButtonRollovered()));
		rotateStickLeft.setVisible(true);

		incrementPower = new JButton(new ImageIcon(
				ImageProvider.getIncrementIndicatorButton()));
		incrementPower.setPreferredSize(new Dimension(100, 100));
		incrementPower.setContentAreaFilled(false);
		incrementPower.setBorder(null);
		incrementPower.setFocusPainted(false);
		incrementPower.setFocusable(false);
		incrementPower.setPressedIcon(new ImageIcon(ImageProvider
				.getIncrementIndicatorButtonRollovered()));
		incrementPower.setVisible(true);

		decrementPower = new JButton(new ImageIcon(
				ImageProvider.getDecrementIndicatorButton()));
		decrementPower.setPreferredSize(new Dimension(100, 100));
		decrementPower.setContentAreaFilled(false);
		decrementPower.setBorder(null);
		decrementPower.setFocusPainted(false);
		decrementPower.setFocusable(false);
		decrementPower.setPressedIcon(new ImageIcon(ImageProvider
				.getDecrementIndicatorButtonRollovered()));
		decrementPower.setVisible(true);

		pause = new JButton(new ImageIcon(ImageProvider.getPauseButton()));
		pause.setPreferredSize(new Dimension(100, 100));
		pause.setContentAreaFilled(false);
		pause.setBorder(null);
		pause.setFocusPainted(false);
		pause.setFocusable(false);
		pause.setPressedIcon(new ImageIcon(ImageProvider
				.getPauseButtonRollovered()));
		pause.setVisible(true);

		saveNewCommand = new JButton(new ImageIcon(
				ImageProvider.getSaveChangesButton()));
		saveNewCommand.setPreferredSize(new Dimension(300, 100));
		saveNewCommand.setContentAreaFilled(false);
		saveNewCommand.setBorder(null);
		saveNewCommand.setFocusPainted(false);
		saveNewCommand.setFocusable(false);
		saveNewCommand.setRolloverEnabled(true);
		saveNewCommand.setRolloverIcon(new ImageIcon(ImageProvider
				.getSaveChangesButtonRollovered()));
		saveNewCommand.setVisible(true);

		resetAll = new JButton(new ImageIcon(ImageProvider.getReset()));
		resetAll.setPreferredSize(new Dimension(200, 100));
		resetAll.setContentAreaFilled(false);
		resetAll.setBorder(null);
		resetAll.setFocusPainted(false);
		resetAll.setFocusable(false);
		resetAll.setRolloverEnabled(true);
		resetAll.setRolloverIcon(new ImageIcon(ImageProvider
				.getResetRollovered()));
		resetAll.setVisible(true);

		confirmButton = new JButton(new ImageIcon(
				ImageProvider.getConfirmButton()));
		confirmButton.setPreferredSize(new Dimension(200, 100));
		confirmButton.setContentAreaFilled(false);
		confirmButton.setBorder(null);
		confirmButton.setFocusPainted(false);
		confirmButton.setFocusable(false);
		confirmButton.setRolloverEnabled(true);
		confirmButton.setRolloverIcon(new ImageIcon(ImageProvider
				.getConfirmButtonRollovered()));
		confirmButton.setVisible(true);

		musicON = new JButton(new ImageIcon(ImageProvider.getMusicOn()));
		musicON.setPreferredSize(new Dimension(200, 100));
		musicON.setContentAreaFilled(false);
		musicON.setBorder(null);
		musicON.setFocusPainted(false);
		musicON.setFocusable(false);
		musicON.setRolloverEnabled(true);
		musicON.setRolloverIcon(new ImageIcon(ImageProvider
				.getMusicOnRollovered()));
		musicON.setVisible(true);

		musicOFF = new JButton(new ImageIcon(ImageProvider.getMusicOf()));
		musicOFF.setPreferredSize(new Dimension(200, 100));
		musicOFF.setContentAreaFilled(false);
		musicOFF.setBorder(null);
		musicOFF.setFocusPainted(false);
		musicOFF.setFocusable(false);
		musicOFF.setRolloverEnabled(true);
		musicOFF.setRolloverIcon(new ImageIcon(ImageProvider
				.getMusicOfRolloverd()));
		musicOFF.setVisible(true);

		playMusic = new JButton(new ImageIcon(ImageProvider.getPlayMusic()));
		playMusic.setPreferredSize(new Dimension(200, 100));
		playMusic.setContentAreaFilled(false);
		playMusic.setBorder(null);
		playMusic.setFocusPainted(false);
		playMusic.setFocusable(false);
		playMusic.setRolloverEnabled(true);
		playMusic.setRolloverIcon(new ImageIcon(ImageProvider
				.getPlayMusicRollovered()));
		playMusic.setVisible(true);

		stopMusic = new JButton(new ImageIcon(ImageProvider.getStopMusic()));
		stopMusic.setPreferredSize(new Dimension(200, 100));
		stopMusic.setContentAreaFilled(false);
		stopMusic.setBorder(null);
		stopMusic.setFocusPainted(false);
		stopMusic.setFocusable(false);
		stopMusic.setRolloverEnabled(true);
		stopMusic.setRolloverIcon(new ImageIcon(ImageProvider
				.getStopMusicRollovered()));
		stopMusic.setVisible(true);

		pauseMusic = new JButton(new ImageIcon(ImageProvider.getPauseMusic()));
		pauseMusic.setPreferredSize(new Dimension(200, 100));
		pauseMusic.setContentAreaFilled(false);
		pauseMusic.setBorder(null);
		pauseMusic.setFocusPainted(false);
		pauseMusic.setFocusable(false);
		pauseMusic.setRolloverEnabled(true);
		pauseMusic.setRolloverIcon(new ImageIcon(ImageProvider
				.getPauseMusicRollovered()));
		pauseMusic.setVisible(true);

		nextMusic = new JButton(new ImageIcon(ImageProvider.getNextMusic()));
		nextMusic.setPreferredSize(new Dimension(60, 60));
		nextMusic.setContentAreaFilled(false);
		nextMusic.setBorder(null);
		nextMusic.setFocusPainted(false);
		nextMusic.setFocusable(false);
		nextMusic.setRolloverEnabled(true);
		nextMusic.setRolloverIcon(new ImageIcon(ImageProvider
				.getNextMusicRollovered()));
		nextMusic.setVisible(true);

		previusMusic = new JButton(new ImageIcon(
				ImageProvider.getPreviusMusic()));
		previusMusic.setPreferredSize(new Dimension(60, 60));
		previusMusic.setContentAreaFilled(false);
		previusMusic.setBorder(null);
		previusMusic.setFocusPainted(false);
		previusMusic.setFocusable(false);
		previusMusic.setRolloverEnabled(true);
		previusMusic.setRolloverIcon(new ImageIcon(ImageProvider
				.getPreviusMusicRollovered()));
		previusMusic.setVisible(true);
	}

	private void checkIfExistsDuplicateKeyForCommandOrAddIt(int position) {

		if (commandChoose.contains(keyCodePressed + "")) {
			for (String s : commandChoose) {
				if (s.equals(keyCodePressed + "")
						&& !s.equals(commandChoose.get(position))) {
					int indexOfDuplicateKey = commandChoose.indexOf(s);
					commandChoose.add(position, keyCodePressed + "");
					commandChoose.remove(position + 1);
					commandChoose.add(indexOfDuplicateKey, -1 + "");
					commandChoose.remove(indexOfDuplicateKey + 1);
					break;
				}
			}
		} else {
			commandChoose.add(position, keyCodePressed + "");
			commandChoose.remove(position + 1);
		}
	}

	private void resetCommands() {
		FileProvider.standardCommand();
		commandChoose = FileProvider.getAllCommand();
		currentTableSelected = 1;
		tableButton.setIcon(new ImageIcon(ImageProvider.getTableImage()
				.get(currentTableSelected).getScaledInstance(300, 180, 0)));
		repaint();
	}

	private void resetBoolean() {
		isPausePressed = false;
		isHitButtonPressed = false;
		isRotateRightButtonPressed = false;
		isRotateLeftButtonPressed = false;
		isIncrementPowerPressed = false;
		isDecrementPowerPressed = false;
	}

	private void showDoYouWantToSaveChangesToTheCurrentGame() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(14), 0, 0, null);
				g.drawImage(ImageProvider
						.getDoYouWantToSaveChangesToTheCurrentGame(), 150, 50,
						null);
			}
		};

		panel.setSize(new Dimension(500, 300));
		panel.setPreferredSize(new Dimension(500, 300));
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
				if (!commandChoose.contains("-1")) {
					FileProvider.setAllCommands(commandChoose,
							permanentNewCommand);
					principalMainFrame.switchToPausePanelFromSettingPanel();
				}
				dialog.dispose();
				principalMainFrame.switchToPausePanelFromSettingPanel();
			}
		});
		no.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				commandChoose = FileProvider.getAllCommand();

				dialog.dispose();
				principalMainFrame.switchToPausePanelFromSettingPanel();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setSize(500, 300);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);

	}

	private void showYouHaveNotMadeAnyChangesYouWantToGoBack() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(13), 0, 0, null);
				g.drawImage(ImageProvider
						.getYouHaveNotMadeAnyChangesYouWantToGoBack(), 150, 50,
						null);
			}
		};

		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
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
				principalMainFrame.switchToPausePanelFromSettingPanel();
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
		dialog.setTitle("Back");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showDoYouReallyWantToResetTheCommands() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(12), 0, 0, null);
				g.drawImage(
						ImageProvider.getDoYouReallyWantToResetTheCommands(),
						150, 50, null);
			}
		};

		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
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
				resetCommands();
				dialog.dispose();
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
		dialog.setTitle("Reset");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);

	}

	private void showDoYouWantToSaveYourChangesSoPermanently() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(11), 0, 0, null);
				g.drawImage(ImageProvider
						.getDoYouWantToSaveYourChangesSoPermanently(), 150, 50,
						null);
			}
		};

		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
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
				if (!commandChoose.contains("-1")) {
					permanentNewCommand = true;
					FileProvider.setAllCommands(commandChoose,
							permanentNewCommand);
					principalMainFrame.switchToPausePanelFromSettingPanel();
				}
				dialog.dispose();
				principalMainFrame.switchToPausePanelFromSettingPanel();
			}
		});
		no.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				commandChoose = FileProvider.getAllCommand();
				dialog.dispose();
				principalMainFrame.switchToPausePanelFromSettingPanel();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Save Commands");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);

	}

	private void showCommandNotSet(int indexOfCommandNotSets) {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider
						.getLittleBackground(indexOfCommandNotSets), 0, 0, null);
				g.drawImage(
						ImageProvider.getCommandNotSet(indexOfCommandNotSets),
						150, 50, null);
			}
		};

		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setLayout(null);
		JDialog dialog = new JDialog();
		JButton ok = new JButton(new ImageIcon(ImageProvider.getOk()));
		ok.setBounds(150, 200, 100, 50);
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

				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Command Not Set");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageProvider.getSettingBackground(), 0, 0, null);
	}
}
