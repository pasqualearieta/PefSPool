package igpe.billiard.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ResumeContinue extends JPanel {
	private static final long serialVersionUID = 1L;
	private MainFrame principalMainFrame;
	private JButton back;
	private JButton newGame;
	private JButton loadGame;
	private JButton createGame;

	public ResumeContinue(final MainFrame mainFrame) {
		setPreferredSize(new Dimension(MainFrame.STANDAR_WIDTH,
				MainFrame.STANDARD_HEIGHT));
		setMinimumSize(new Dimension(MainFrame.STANDAR_WIDTH,
				MainFrame.STANDARD_HEIGHT));
		setMaximumSize(new Dimension(MainFrame.STANDAR_WIDTH,
				MainFrame.STANDARD_HEIGHT));
		principalMainFrame = mainFrame;

		back = new JButton(new ImageIcon(ImageProvider.getBack()));
		back.setPreferredSize(new Dimension(120, 60));
		back.setContentAreaFilled(false);
		back.setBorder(null);
		back.setFocusPainted(false);
		back.setRolloverEnabled(true);
		back.setRolloverIcon(new ImageIcon(ImageProvider.getBackRollovered()));
		back.setVisible(true);

		newGame = new JButton(new ImageIcon(ImageProvider.getNewGame()));
		newGame.setPreferredSize(new Dimension(310, 120));
		newGame.setContentAreaFilled(false);
		newGame.setBorder(null);
		newGame.setFocusPainted(false);
		newGame.setRolloverEnabled(true);
		newGame.setRolloverIcon(new ImageIcon(ImageProvider
				.getNewGameRollovered()));
		newGame.setVisible(true);

		loadGame = new JButton(new ImageIcon(ImageProvider.getLoadGame()));
		loadGame.setPreferredSize(new Dimension(310, 120));
		loadGame.setContentAreaFilled(false);
		loadGame.setBorder(null);
		loadGame.setFocusPainted(false);
		loadGame.setRolloverEnabled(true);
		loadGame.setRolloverIcon(new ImageIcon(ImageProvider
				.getLoadGameRollovered()));
		loadGame.setVisible(true);

		createGame = new JButton(new ImageIcon(ImageProvider.getEditor()));
		createGame.setContentAreaFilled(false);
		createGame.setPreferredSize(new Dimension(310, 120));
		createGame.setBorder(null);
		createGame.setFocusPainted(false);
		createGame.setRolloverEnabled(true);
		createGame.setRolloverIcon(new ImageIcon(ImageProvider
				.getEditorRollover()));
		createGame.setVisible(true);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(50, 50, 50, 50);
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(newGame, constraints);
		constraints.gridy = 1;
		add(loadGame, constraints);
		constraints.gridy = 2;
		add(createGame, constraints);

		constraints.gridy = 3;
		add(back, constraints);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.switchToInitialPanel();
			}
		});
		loadGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				principalMainFrame.switchToLoadPanel();

			}
		});
		createGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				principalMainFrame.startLevelEditor(0);
			}
		});

		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				principalMainFrame.switchToChooseHowToPlayPanel();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageProvider.getThirdBackground(), 0, 0, null);
	}
}
