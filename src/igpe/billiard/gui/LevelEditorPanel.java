package igpe.billiard.gui;

import igpe.billiard.core.Ball;
import igpe.billiard.core.ColorBall;
import igpe.billiard.core.GameMode;
import igpe.billiard.core.LevelEditorManager;
import igpe.billiard.core.Table;
import igpe.billiard.file.FileProvider;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LevelEditorPanel extends JPanel implements MouseListener,
		MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private static final String[] DIFFICULT = new String[] { "MEVSME", "EASY",
			"MEDIUM", "HARD", };
	private static final String[] COLOR_BALL = new String[] { "Yellow", "Red" };

	private MainFrame principalMainFrame;
	private LevelEditorManager levelEditorManager = new LevelEditorManager();
	private JComboBox<String> difficult = new JComboBox<String>(DIFFICULT);
	private JComboBox<String> selectColorBall = new JComboBox<String>(
			COLOR_BALL);
	private JButton addWhiteBall;
	private JButton addBlackBall;
	private JButton addYellowBall;
	private JButton addRedBall;
	private JButton deleteBall;
	private JButton save;
	private JButton clearAll;
	private JButton back;
	private JButton nextTable;
	private JButton previousTable;
	private boolean canDraw = false;
	private boolean uncorrectPositionOfBall = false;
	private boolean canDelete = false;
	private int indexJComBoxDifficult = 0;
	private int indexJComboBoxSelectColorBall = 0;

	public LevelEditorPanel(final MainFrame mainFrame) {
		setPreferredSize(new Dimension(MainFrame.GAME_WIDTH,
				MainFrame.GAME_HEIGHT));
		setMinimumSize(new Dimension(MainFrame.GAME_WIDTH,
				MainFrame.GAME_HEIGHT));
		setMaximumSize(new Dimension(MainFrame.GAME_WIDTH,
				MainFrame.GAME_HEIGHT));
		setLayout(null);
		this.principalMainFrame = mainFrame;
		back = new JButton(new ImageIcon(ImageProvider.getBack()));
		back.setBounds(200, 680, 120, 90);
		back.setContentAreaFilled(false);
		back.setBorder(null);
		back.setFocusPainted(false);
		back.setRolloverEnabled(true);
		back.setRolloverIcon(new ImageIcon(ImageProvider.getBackRollovered()));
		back.setVisible(true);
		this.add(back);
		save = new JButton(new ImageIcon(ImageProvider.getSave()
				.getScaledInstance(150, 40, Image.SCALE_SMOOTH)));
		save.setContentAreaFilled(false);
		save.setBorder(null);
		save.setFocusPainted(false);
		save.setVisible(true);
		save.setRolloverEnabled(true);
		save.setRolloverIcon(new ImageIcon(ImageProvider.getSaveRollovered()
				.getScaledInstance(152, 42, Image.SCALE_SMOOTH)));
		save.setBounds(350, 700, 150, 40);
		this.add(save);
		deleteBall = new JButton(new ImageIcon(ImageProvider.getDeleteBall()));
		deleteBall.setContentAreaFilled(false);
		deleteBall.setBorder(null);
		deleteBall.setFocusPainted(false);
		deleteBall.setVisible(true);
		deleteBall.setRolloverEnabled(true);
		deleteBall.setRolloverIcon(new ImageIcon(ImageProvider
				.getDeleteBallRollovered()));
		deleteBall.setBounds(580, 700, 200, 60);
		this.add(deleteBall);

		clearAll = new JButton(new ImageIcon(ImageProvider.getClearALl()));
		clearAll.setContentAreaFilled(false);
		clearAll.setBorder(null);
		clearAll.setFocusPainted(false);
		clearAll.setVisible(true);
		clearAll.setRolloverEnabled(true);
		clearAll.setRolloverIcon(new ImageIcon(ImageProvider
				.getClearAllRollovered()));
		clearAll.setBounds(800, 700, 200, 60);
		this.add(clearAll);

		nextTable = new JButton(new ImageIcon(ImageProvider.getFrecciaSu()));
		nextTable.setContentAreaFilled(false);
		nextTable.setBorder(null);
		nextTable.setFocusPainted(false);
		nextTable.setVisible(true);
		nextTable.setBounds(1000, 150, 200, 80);
		this.add(nextTable);
		previousTable = new JButton(
				new ImageIcon(ImageProvider.getFrecciaGiu()));
		previousTable.setContentAreaFilled(false);
		previousTable.setBorder(null);
		previousTable.setFocusPainted(false);
		previousTable.setVisible(true);
		previousTable.setBounds(1000, 500, 200, 80);
		this.add(previousTable);
		addWhiteBall = new JButton(new ImageIcon(
				ImageProvider.getWhiteBallButton()));
		addWhiteBall.setContentAreaFilled(false);
		addWhiteBall.setBorder(null);
		addWhiteBall.setFocusPainted(false);
		addWhiteBall.setRolloverEnabled(true);
		addWhiteBall.setRolloverIcon(new ImageIcon(ImageProvider
				.getWhiteBallButtonRollovered()));
		addWhiteBall.setVisible(true);
		addWhiteBall.setBounds(40, 150, 80, 80);
		this.add(addWhiteBall);
		addBlackBall = new JButton(new ImageIcon(
				ImageProvider.getBlackBallButton()));
		addBlackBall.setContentAreaFilled(false);
		addBlackBall.setBorder(null);
		addBlackBall.setFocusPainted(false);
		addBlackBall.setVisible(true);
		addBlackBall.setRolloverEnabled(true);
		addBlackBall.setRolloverIcon(new ImageIcon(ImageProvider
				.getBlackBallButtonRollovered()));
		addBlackBall.setBounds(40, 250, 80, 80);
		this.add(addBlackBall);
		addYellowBall = new JButton(new ImageIcon(
				ImageProvider.getYellowBallButton()));
		addYellowBall.setContentAreaFilled(false);
		addYellowBall.setBorder(null);
		addYellowBall.setFocusPainted(false);
		addYellowBall.setVisible(true);
		addYellowBall.setRolloverEnabled(true);
		addYellowBall.setRolloverIcon(new ImageIcon(ImageProvider
				.getYellowBallButtonRollovered()));
		addYellowBall.setBounds(40, 350, 80, 80);
		this.add(addYellowBall);
		addRedBall = new JButton(
				new ImageIcon(ImageProvider.getRedBallButton()));
		addRedBall.setContentAreaFilled(false);
		addRedBall.setBorder(null);
		addRedBall.setFocusPainted(false);
		addRedBall.setVisible(true);
		addRedBall.setRolloverEnabled(true);
		addRedBall.setRolloverIcon(new ImageIcon(ImageProvider
				.getRedBallButtonRollovered()));
		addRedBall.setBounds(40, 450, 80, 80);
		this.add(addRedBall);

		difficult.setBounds(300, 60, 130, 40);
		difficult.setAutoscrolls(true);
		difficult.setSelectedIndex(indexJComBoxDifficult);
		difficult.setName("Difficul");
		difficult.setVisible(true);
		difficult.setFont(new Font("Serif", 3, 20));
		this.add(difficult);
		selectColorBall.setBounds(600, 60, 130, 40);
		selectColorBall.setAutoscrolls(true);
		selectColorBall.setSelectedIndex(indexJComboBoxSelectColorBall);
		selectColorBall.setVisible(true);
		selectColorBall.setFont(new Font("Serif", 3, 20));
		this.add(selectColorBall);

		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				showDoYouWantToSaveBeforeExit();
			}
		});
		save.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);

				getValuesFromComboBoxs();
				if (checkIfIsPossibleSave())
					principalMainFrame.switchToSavePanel(GameMode.EDITOR);
			}
		});
		clearAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				showDoYouWantToDeleteEveryThingAndRestartToReposition();
			}
		});
		nextTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (levelEditorManager.getIndexTable() < ImageProvider
						.getTableImage().size())
					levelEditorManager.setIndexTable(levelEditorManager
							.getIndexTable() + 1);
				else {
					levelEditorManager.setIndexTable(levelEditorManager
							.getIndexTable() + 1);

					levelEditorManager.setIndexTable(levelEditorManager
							.getIndexTable()
							% ImageProvider.getTableImage().size());
				}
			}
		});
		previousTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (levelEditorManager.getIndexTable() > 1)
					levelEditorManager.setIndexTable(levelEditorManager
							.getIndexTable() - 1);
				else {
					levelEditorManager.setIndexTable(ImageProvider
							.getTableImage().size() - 1);
				}
			}
		});
		deleteBall.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (levelEditorManager.getBallsOnTheTable().isEmpty())
					showYouCanNotDeleteIfYouDoNotHaveBallsOnTheTable();
				else
					canDelete = true;
			}
		});
		addWhiteBall.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);

				if (uncorrectPositionOfBall)
					showYouCanNotAddAnotherBall();
				else if (levelEditorManager.getBallsOnTheTable().size() < LevelEditorManager.MAX_NUMBER_OF_BALLS_ON_THE_TABLE)
					levelEditorManager.addWhiteBall();
				else if ((levelEditorManager.getBallsOnTheTable().size() == LevelEditorManager.MAX_NUMBER_OF_BALLS_ON_THE_TABLE))
					showMaximumNumberOfBallReached();
				repaint();
			}
		});
		addBlackBall.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (uncorrectPositionOfBall)
					showYouCanNotAddAnotherBall();
				else if (levelEditorManager.getBallsOnTheTable().size() < LevelEditorManager.MAX_NUMBER_OF_BALLS_ON_THE_TABLE)
					levelEditorManager.addBlackBall();
				else if ((levelEditorManager.getBallsOnTheTable().size() == LevelEditorManager.MAX_NUMBER_OF_BALLS_ON_THE_TABLE))
					showMaximumNumberOfBallReached();
				repaint();
			}
		});
		addYellowBall.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (uncorrectPositionOfBall)
					showYouCanNotAddAnotherBall();
				else if (levelEditorManager.getBallsOnTheTable().size() < LevelEditorManager.MAX_NUMBER_OF_BALLS_ON_THE_TABLE)
					levelEditorManager.addYellowBall();
				else if ((levelEditorManager.getBallsOnTheTable().size() == LevelEditorManager.MAX_NUMBER_OF_BALLS_ON_THE_TABLE))
					showMaximumNumberOfBallReached();

				repaint();
			}
		});

		addRedBall.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (uncorrectPositionOfBall)
					showYouCanNotAddAnotherBall();
				else if (levelEditorManager.getBallsOnTheTable().size() < LevelEditorManager.MAX_NUMBER_OF_BALLS_ON_THE_TABLE)
					levelEditorManager.addRedBall();
				else if ((levelEditorManager.getBallsOnTheTable().size() == LevelEditorManager.MAX_NUMBER_OF_BALLS_ON_THE_TABLE))
					showMaximumNumberOfBallReached();
				repaint();
			}
		});
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

	public void start() throws IOException {
		this.requestFocus();
		levelEditorManager.start(new Runnable() {
			@Override
			public void run() {

				if (levelEditorManager.getInterrupted())
					showInterrupted();

				else
					repaint();
			}
		});
	}

	public void resumeLevelCreated(final int indexOfLevelCreated) {
		if (indexOfLevelCreated > 0)
			levelEditorManager.resumeLevelCreated(indexOfLevelCreated);
	}

	public boolean checkIfIsPossibleSave() {
		boolean flag = false;

		final BufferedImage screenOfTheGame = new BufferedImage(
				MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		this.paint(screenOfTheGame.createGraphics());

		FileProvider.setCurrentStatusTheScreenOfTheGameIntoAFile(GameMode.EDITOR,
				screenOfTheGame);
		FileProvider.setCurrentStatusOfTheGameIntoAFile(GameMode.EDITOR,
				levelEditorManager.getStringCurrentStatusOfTheGame());

		if (levelEditorManager.getBallsOnTheTable().size() <= LevelEditorManager.MAX_NUMBER_OF_BALLS_ON_THE_TABLE) {
			if (levelEditorManager.getBallsOnTheTable().size() < LevelEditorManager.MINIMUN_NUMBER_OF_BALLS_ON_THE_TABLE)
				showYouMustAddBall();
			else if (!levelEditorManager.whiteBallIsPresentOnTheTable())
				showTheWhiteBallIsNotOnTheTable();
			else if (!levelEditorManager.blackBallIsPresentOnTheTable())
				showTheBlackBallIsNotOnTheTable();
			else if (!levelEditorManager
					.atLeastOneYellowBallIsPresentOnTheTable())
				showOnTheTableThereMustAtLeastAYellowBall();
			else if (!levelEditorManager.atLeastOneRedBallIsPresentOnTheTable())
				showOnTheTableThereMustAtLeastARedBall();
			else {
				flag = true;
			}
		}
		return flag;
	}

	private void getValuesFromComboBoxs() {

		final String difficultSelected = difficult.getSelectedItem().toString();
		final String colorBallSelected = selectColorBall.getSelectedItem()
				.toString();

		if (difficultSelected != levelEditorManager.getDifficultSelected())
			levelEditorManager.setDifficult(difficultSelected);

		if (colorBallSelected.equals(COLOR_BALL[0])) {
			levelEditorManager.setColorBallFirstPlayer(ColorBall.YELLOW);
			levelEditorManager.setColorBallSecondPlayer(ColorBall.RED);
		} else if (colorBallSelected.equals(COLOR_BALL[1])) {
			levelEditorManager.setColorBallFirstPlayer(ColorBall.RED);
			levelEditorManager.setColorBallSecondPlayer(ColorBall.YELLOW);
		}

		indexJComboBoxSelectColorBall = selectColorBall.getSelectedIndex();
		indexJComBoxDifficult = difficult.getSelectedIndex();
	}

	private void showYouMustAddBall() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(2), 0, 0, null);
				g.drawImage(ImageProvider.getYouMustAddBall(), 150, 50, null);
				g.drawImage(
						ImageProvider
								.getNumber(LevelEditorManager.MINIMUN_NUMBER_OF_BALLS_ON_THE_TABLE
										- levelEditorManager
												.getBallsOnTheTable().size()),
						430, 50, null);
			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setLayout(null);
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
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("You Must add "
				+ (LevelEditorManager.MINIMUN_NUMBER_OF_BALLS_ON_THE_TABLE - levelEditorManager
						.getBallsOnTheTable().size()) + " balls");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showDoYouWantToSaveBeforeExit() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(14), 0, 0, null);
				g.drawImage(ImageProvider.getDoYouWantToSaveBeforeExit(), 150,
						50, null);
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
				final boolean result = checkIfIsPossibleSave();
				if (result)
					principalMainFrame.switchToInitialPanel();
			}
		});
		no.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				dialog.dispose();
				principalMainFrame.switchToResumeContinuePanel();
			}
		});

		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Do You Want To Save?");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showYouCanNotPlaceABallOnAnotherBall() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(8), 0, 0, null);
				g.drawImage(
						ImageProvider.getYouCanNotPlaceABallOnAnotherBall(),
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
				dialog.dispose();
			}
		});

		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("You can't place a ball over another ball");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showTheWhiteBallIsNotOnTheTable() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(3), 0, 0, null);
				g.drawImage(ImageProvider.getTheWhiteBallIsNotOnTheTable(),
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
				dialog.dispose();
			}
		});

		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("White Ball isn't on the table");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showYouCanNotRemoveTheWhiteBall() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(3), 0, 0, null);
				g.drawImage(ImageProvider.getYouCanNotRemoveTheWhiteBall(),
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
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle(" Error ");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showYouCanNotRemoveTheBlackBall() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(4), 0, 0, null);
				g.drawImage(ImageProvider.getYouCanNotRemoveTheBlackBall(),
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
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle(" Error ");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showTheBlackBallIsNotOnTheTable() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(4), 0, 0, null);
				g.drawImage(ImageProvider.getTheBlackBallIsNotOnTheTable(),
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
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Error");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showOnTheTableThereMustAtLeastAYellowBall() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(1), 0, 0, null);
				g.drawImage(ImageProvider
						.getOnTheTableThereMustBeAtLeastAYellowBall(), 150, 50,
						null);
			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setLayout(null);
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
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Insert Yellow Ball");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showOnTheTableThereMustAtLeastARedBall() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(9), 0, 0, null);
				g.drawImage(
						ImageProvider.getOnTheTableThereMustBeAtLeastARedBall(),
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
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Insert Red Ball");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showMaximumNumberOfBallReached() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(2), 0, 0, null);
				g.drawImage(ImageProvider.getMaximumNumberOfBallReached(), 150,
						50, null);
			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setLayout(null);
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
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Error");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showYouCanNotAddAnotherBall() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(8), 0, 0, null);
				g.drawImage(ImageProvider.getYouCanNotAddAnotherBall(), 150,
						50, null);
			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH,
				MainFrame.DIALOG_HEIGHT));
		panel.setLayout(null);
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
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Error");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showYouCanNotDeleteIfYouDoNotHaveBallsOnTheTable() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(0), 0, 0, null);
				g.drawImage(ImageProvider
						.getYouCanNotDeleteIfYouDoNotHaveBallsOnTheTable(),
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
				dialog.dispose();
			}
		});
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Insert Some Balls");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showDoYouWantToDeleteEveryThingAndRestartToReposition() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(7), 0, 0, null);
				g.drawImage(
						ImageProvider
								.getDoYouReallyWantToDeleteEverythingAndRestartToReposion(),
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
				levelEditorManager.clear();
				dialog.dispose();
				repaint();
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
		dialog.setTitle("Clear");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showDoYouWantToRemoveThisBall(final Ball b) {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(16), 0, 0, null);
				g.drawImage(ImageProvider.getDoYouReallyWantToRemoveThisBall(),
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
				levelEditorManager.getBallsOnTheTable().remove(b);
				canDelete = false;
				levelEditorManager.resetBallsForPositionItOnTheTable();
				repaint();
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
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setTitle("Remove Ball");
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showInterrupted() {
		JOptionPane.showMessageDialog(this,
				"Impossible continue to create Level, the editor is close.",
				"Error", JOptionPane.ERROR_MESSAGE);

		principalMainFrame.switchToInitialPanel();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		final int xMouse = e.getX();
		final int yMouse = e.getY();
		if (!uncorrectPositionOfBall) {
			if (levelEditorManager.checkThatTheMouseIntersectWithABall(xMouse,
					yMouse)) {
				if (canDelete) {
					final Ball b = levelEditorManager.checkBallIWantDelete(
							xMouse, yMouse);
					if (b.getIdentificativeNumber() == 0)
						showYouCanNotRemoveTheWhiteBall();
					else if (b.getIdentificativeNumber() == 8)
						showYouCanNotRemoveTheBlackBall();
					else
						showDoYouWantToRemoveThisBall(b);
					canDelete = false;
				}
				canDraw = true;
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.repaint();
		final Cursor cursor;
		final Toolkit toolkit = getToolkit();
		final Point cursorHotSpot = new Point(15, 15);
		if (levelEditorManager.checkThatThereAreNoOverlappingBalls()) {
			cursor = toolkit.createCustomCursor(
					ImageProvider.getDivietoPointer(), cursorHotSpot, "wrong");
			setCursor(cursor);
		} else if (e.getX() >= 200 && e.getY() <= 200 + Table.WIDTH
				&& e.getY() >= 200 && e.getY() <= 200 + Table.HEIGHT) {
			cursor = toolkit.createCustomCursor(ImageProvider.getHandPointer(),
					cursorHotSpot, "hand");
			setCursor(cursor);
		}
		if (canDraw)
			levelEditorManager.updateBall(e.getX(), e.getY());
		this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
	} // ignore these events

	@Override
	public void mouseMoved(MouseEvent e) {
		final Cursor cursor;
		final Toolkit toolkit = getToolkit();
		final Point cursorHotSpot = new Point(15, 15);
		if (e.getX() >= 200 && e.getY() <= 200 + Table.WIDTH && e.getY() >= 200
				&& e.getY() <= 200 + Table.HEIGHT
				&& !levelEditorManager.checkThatThereAreNoOverlappingBalls()
				&& !levelEditorManager.getBallsOnTheTable().isEmpty()) {
			cursor = toolkit.createCustomCursor(ImageProvider.getHandPointer(),
					cursorHotSpot, "hand");
			setCursor(cursor);
		} else {
			cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
			setCursor(cursor);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	} // ignore these events

	@Override
	public void mouseClicked(MouseEvent e) {
	} // ignore these events

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!levelEditorManager.checkThatThereAreNoOverlappingBalls()) {
			levelEditorManager.resetBallsForPositionItOnTheTable();
			uncorrectPositionOfBall = false;
		} else {
			showYouCanNotPlaceABallOnAnotherBall();
			Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
			repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.GRAY);
		g.drawImage(ImageProvider.getBackgroundEditor(), 0, 0, null);
		g.drawImage(ImageProvider.getDifficult(), 200, 60, null);
		g.drawImage(ImageProvider.getColorBall(), 500, 60, null);
		g.drawImage(
				ImageProvider.getTableImage().get(
						levelEditorManager.getIndexTable()), levelEditorManager
						.getTable().getX() - 49, levelEditorManager.getTable()
						.getY() - 45, null);
		if (!levelEditorManager.getBallsOnTheTable().isEmpty()) {
			for (Ball ball : levelEditorManager.getBallsOnTheTable()) {
				if (!ball.getIsPocketed()) {
					if (ball.getIdentificativeNumber() == 0)
						g.drawImage(ImageProvider.getWhiteBall(), ball.getX(),
								ball.getY(), null);
					if (ball.getIdentificativeNumber() == 8)
						g.drawImage(ImageProvider.getBlackBall(), ball.getX(),
								ball.getY(), null);
					if (ball.getIdentificativeNumber() >= 1
							&& ball.getIdentificativeNumber() <= 7)
						g.drawImage(ImageProvider.getYellowBall(), ball.getX(),
								ball.getY(), null);
					if (ball.getIdentificativeNumber() >= 9
							&& ball.getIdentificativeNumber() <= 15)
						g.drawImage(ImageProvider.getRedBall(), ball.getX(),
								ball.getY(), null);
				}
			}
		}
	}
}
