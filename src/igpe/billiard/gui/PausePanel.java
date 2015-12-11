package igpe.billiard.gui;

import igpe.billiard.core.GameMode;
import igpe.billiard.file.FileProvider;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class PausePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton settings;
	private JButton resume;
	private JButton save;
	private JButton exit;
	private JButton back;
	private MainFrame principalMainFrame;

	public PausePanel(final MainFrame mainFrame) {
		principalMainFrame = mainFrame;

		setPreferredSize(new Dimension(MainFrame.GAME_WIDTH,
				MainFrame.GAME_HEIGHT));
		setMinimumSize(new Dimension(MainFrame.GAME_WIDTH,
				MainFrame.GAME_HEIGHT));
		setMaximumSize(new Dimension(MainFrame.GAME_WIDTH,
				MainFrame.GAME_HEIGHT));
		setLayout(null);
		settings = new JButton(new ImageIcon(ImageProvider.getSettingsButton()));
		settings.setOpaque(false);
		settings.setContentAreaFilled(false);
		settings.setBorder(null);
		settings.setFocusPainted(false);
		settings.setRolloverEnabled(true);
		settings.setRolloverIcon(new ImageIcon(ImageProvider
				.getSettingsButtonRolloveres()));
		settings.setVisible(true);
		this.add(settings);
		resume = new JButton(new ImageIcon(ImageProvider.getResumeButton()));
		resume.setOpaque(false);
		resume.setContentAreaFilled(false);
		resume.setBorder(null);
		resume.setFocusPainted(false);
		resume.setRolloverEnabled(true);
		resume.setRolloverIcon(new ImageIcon(ImageProvider
				.getResumeButtonRollovered()));
		resume.setVisible(true);
		this.add(resume);
		save = new JButton(new ImageIcon(ImageProvider.getSave()));
		save.setOpaque(false);
		save.setContentAreaFilled(false);
		save.setBorder(null);
		save.setFocusPainted(false);
		save.setRolloverEnabled(true);
		save.setRolloverIcon(new ImageIcon(ImageProvider.getSaveRollovered()));
		save.setVisible(true);
		this.add(save);
		exit = new JButton(new ImageIcon(ImageProvider.getExitButton()));
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorder(null);
		exit.setFocusPainted(false);
		exit.setRolloverEnabled(true);
		exit.setRolloverIcon(new ImageIcon(ImageProvider
				.getExitButtonRolloverd()));
		exit.setVisible(true);
		this.add(exit);

		back = new JButton(new ImageIcon(ImageProvider.getBack()
				.getScaledInstance(150, 60, 0)));
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setBorder(null);
		back.setFocusPainted(false);
		back.setRolloverEnabled(true);
		back.setRolloverIcon(new ImageIcon(ImageProvider.getBackRollovered()
				.getScaledInstance(155, 65, 0)));
		back.setVisible(true);

		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(25, 25, 25, 25);
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(settings, constraints);
		constraints.gridy = 1;
		this.add(save, constraints);
		constraints.gridy = 2;
		this.add(resume, constraints);
		constraints.gridy = 3;
		this.add(back, constraints);
		constraints.gridy = 4;
		this.add(exit, constraints);

		settings.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.switchToSettingsPanel();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				principalMainFrame.switchToSavePanel(GameMode.MATCH);
			}
		});
		resume.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				principalMainFrame.switchToGamePanelfromPausePanel();
			}
		});

		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				super.mousePressed(arg0);
				principalMainFrame.getGamePanel().setEndOfGame();
				showDoYouLeaveTheMatch();
			}
		});

		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				super.mousePressed(arg0);
				principalMainFrame.showExitConfirm();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(FileProvider.getScreenOfTheGame(), PausePanel.WIDTH,
				PausePanel.HEIGHT, null);
		g.drawImage(ImageProvider.getTransparency(), 0, 0, null);
	}

	public void showDoYouLeaveTheMatch() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(5), 0, 0, null);
				g.drawImage(ImageProvider.getDoYouLeaveTheMatch(), 150, 50,
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
				principalMainFrame.switchToInitialPanel();
				principalMainFrame.getGamePanel().setEndOfGame();
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
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);

	}
}
