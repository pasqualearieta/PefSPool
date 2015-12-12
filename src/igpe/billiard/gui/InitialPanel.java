package igpe.billiard.gui;

import igpe.billiard.core.Modality;


import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JPanel;


public class InitialPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton OnLine;
	private JButton OffLine;
	private MainFrame principalMainFrame;

	public InitialPanel(final MainFrame mainFrame) {

		principalMainFrame = mainFrame;
		setPreferredSize(new Dimension(MainFrame.STANDAR_WIDTH,
				MainFrame.STANDARD_HEIGHT));
		setMinimumSize(new Dimension(MainFrame.STANDAR_WIDTH,
				MainFrame.STANDARD_HEIGHT));
		setMaximumSize(new Dimension(MainFrame.STANDAR_WIDTH,
				MainFrame.STANDARD_HEIGHT));
		setLayout(null);
		OnLine = new JButton(new ImageIcon(ImageProvider.getOnLine()));
		OnLine.setPreferredSize(new Dimension(250, 120));
		OnLine.setContentAreaFilled(false);
		OnLine.setBorder(null);
		OnLine.setFocusPainted(false);
		OnLine.setRolloverEnabled(true);
		OnLine.setRolloverIcon(new ImageIcon(ImageProvider
				.getOnLineRollovered()));
		OnLine.setVisible(true);

		OffLine = new JButton(new ImageIcon(ImageProvider.getOffLine()));
		OffLine.setPreferredSize(new Dimension(250, 120));
		OffLine.setContentAreaFilled(false);
		OffLine.setBorder(null);
		OffLine.setFocusPainted(false);
		OffLine.setRolloverEnabled(true);
		OffLine.setRolloverIcon(new ImageIcon(ImageProvider
				.getOffLineRollovered()));
		OffLine.setVisible(true);

		OffLine.setBounds(30, 30, 250, 120);
		OnLine.setBounds(30, 110, 250, 120);
		this.add(OffLine);
		this.add(OnLine);

		OffLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				principalMainFrame.setModalityOfTheGame(Modality.OFFLINE);
			
				
				principalMainFrame.switchToResumeContinuePanel();
			}
		});

		OnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				principalMainFrame.setModalityOfTheGame(Modality.ONLINE);
				
					
				principalMainFrame.switchToConnectionPanel();
			}
		});

	}

	
	@Override
	protected void paintComponent(Graphics graphic) {
		super.paintComponent(graphic);
		graphic.drawImage(ImageProvider.getBackgroundTwo(), 0, 0, null);
	}
}
