package igpe.billiard.gui;

import igpe.billiard.core.HowToPlay;
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

public class ChooseHowToPlayPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private HowToPlay howToPlay = HowToPlay.MEVSME;
	private JButton back;
	private JButton MevsMe;
	private JButton Easy;
	private JButton Medium;
	private JButton Hard;
	private MainFrame principalMainFrame;

	public ChooseHowToPlayPanel(final MainFrame mainFrame) {
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
		MevsMe = new JButton(new ImageIcon(ImageProvider.getMevsMe()));
		MevsMe.setPreferredSize(new Dimension(300, 150));
		MevsMe.setContentAreaFilled(false);
		MevsMe.setBorder(null);
		MevsMe.setFocusPainted(false);
		MevsMe.setRolloverEnabled(true);
		MevsMe.setRolloverIcon(new ImageIcon(ImageProvider
				.getMevsMeRollovered()));
		MevsMe.setVisible(true);
		Easy = new JButton(new ImageIcon(ImageProvider.getEasy()));
		Easy.setPreferredSize(new Dimension(320, 150));
		Easy.setContentAreaFilled(false);
		Easy.setBorder(null);
		Easy.setFocusPainted(false);
		Easy.setRolloverEnabled(true);
		Easy.setRolloverIcon(new ImageIcon(ImageProvider.getEasyRollovered()));
		Easy.setVisible(true);
		Medium = new JButton(new ImageIcon(ImageProvider.getMedium()));
		Medium.setPreferredSize(new Dimension(320, 150));
		Medium.setContentAreaFilled(false);
		Medium.setBorder(null);
		Medium.setFocusPainted(false);
		Medium.setRolloverEnabled(true);
		Medium.setRolloverIcon(new ImageIcon(ImageProvider
				.getMediumRollovered()));
		Medium.setVisible(true);
		Hard = new JButton(new ImageIcon(ImageProvider.getHard()));
		Hard.setPreferredSize(new Dimension(320, 150));
		Hard.setContentAreaFilled(false);
		Hard.setBorder(null);
		Hard.setFocusPainted(false);
		Hard.setRolloverEnabled(true);
		Hard.setRolloverIcon(new ImageIcon(ImageProvider.getHardRollovered()));
		Hard.setVisible(true);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(20, 20, 20, 20);
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(MevsMe, constraints);
		constraints.gridy = 2;
		add(Easy, constraints);
		constraints.gridy = 4;
		add(Medium, constraints);
		constraints.gridy = 6;
		add(Hard, constraints);
		constraints.gridy = 10;
		add(back, constraints);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.switchToResumeContinuePanel();
			}
		});
		MevsMe.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				howToPlay = HowToPlay.MEVSME;
				principalMainFrame.startGame(howToPlay, -1);
			}
		});
		Easy.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				howToPlay = HowToPlay.EASY;
				principalMainFrame.startGame(howToPlay, -1);
			}
		});
		Medium.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				howToPlay = HowToPlay.MEDIUM;
				principalMainFrame.startGame(howToPlay, -1);
			}
		});
		Hard.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				howToPlay = HowToPlay.HARD;
				principalMainFrame.startGame(howToPlay, -1);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageProvider.getFourthBackground(), 0, 0, null);
	}
}
