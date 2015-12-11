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

public class LoadPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton back;
	private JButton loadMatch;
	private JButton loadLevel;
	private MainFrame principalMainFrame;

	public LoadPanel(final MainFrame mainFrame) {
		principalMainFrame = mainFrame;
		setPreferredSize(new Dimension(MainFrame.STANDAR_WIDTH,
				MainFrame.STANDARD_HEIGHT));
		setMinimumSize(new Dimension(MainFrame.STANDAR_WIDTH,
				MainFrame.STANDARD_HEIGHT));
		setMaximumSize(new Dimension(MainFrame.STANDAR_WIDTH,
				MainFrame.STANDARD_HEIGHT));
		back = new JButton(new ImageIcon(ImageProvider.getBack()));
		back.setPreferredSize(new Dimension(120, 60));
		back.setContentAreaFilled(false);
		back.setBorder(null);
		back.setFocusPainted(false);
		back.setRolloverEnabled(true);
		back.setRolloverIcon(new ImageIcon(ImageProvider.getBackRollovered()));
		back.setVisible(true);

		loadMatch = new JButton(new ImageIcon(ImageProvider.getLoadMatch()));
		loadMatch.setPreferredSize(new Dimension(300, 120));
		loadMatch.setContentAreaFilled(false);
		loadMatch.setBorder(null);
		loadMatch.setFocusPainted(false);
		loadMatch.setRolloverEnabled(true);
		loadMatch.setRolloverIcon(new ImageIcon(ImageProvider
				.getLoadMatchRollover()));
		loadMatch.setVisible(true);

		loadLevel = new JButton(new ImageIcon(ImageProvider.getLoadLevel()));
		loadLevel.setPreferredSize(new Dimension(300, 120));
		loadLevel.setContentAreaFilled(false);
		loadLevel.setBorder(null);
		loadLevel.setFocusPainted(false);
		loadLevel.setRolloverEnabled(true);
		loadLevel.setRolloverIcon(new ImageIcon(ImageProvider
				.getLoadLevelRollover()));
		loadLevel.setVisible(true);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(loadLevel, constraints);
		constraints.gridy = 1;
		add(loadMatch, constraints);
		constraints.gridy = 2;
		add(back, constraints);
		loadMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!FileProvider.getThatThereAreMatchRescueSaved())
					showThereAreNoSavedGame();
				else
					principalMainFrame.switchToLoadOrSave(GameMode.MATCH);
			}
		});
		loadLevel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (!FileProvider.getThatThereAreEditorRescueSaved())
					showThereAreNoSavedCreateGame();
				else
					principalMainFrame.switchToLoadOrSave(GameMode.EDITOR);
			}
		});
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.switchToResumeContinuePanel();
			}
		});
	}

	public void showThereAreNoSavedGame() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(7), 0, 0, null);
				g.drawImage(ImageProvider.getThereAreNoSavedGame(), 150, 50,
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
		dialog.setTitle("No Saved Game");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);

	}

	public void showThereAreNoSavedCreateGame() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;
	
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(9), 0, 0, null);
				g.drawImage(ImageProvider.getThereAreNoSavedGameCreated(), 150,
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
		dialog.setTitle("No Level Created");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageProvider.getBackgroundHowToPlay(), 0, 0, null);
	}

}
