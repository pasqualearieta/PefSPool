package igpe.billiard.gui;

import igpe.billiard.core.GameMode;
import igpe.billiard.core.HowToPlay;
import igpe.billiard.file.FileProvider;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class LoadSave extends JPanel {

	private static final long serialVersionUID = 1L;

	private GameMode modeOfTheGame;
	private MainFrame principalMainFrame;
	private int indexOfCurrentRescueSelected = 0;
	private Vector<String> namesOfTheRescue = new Vector<String>();
	private JList<String> containerOfTheName;
	private JScrollPane scrollPane;
	private JButton back;
	private JButton play;
	private JButton delete;
	private JButton modify;
	private DefaultListModel<String> model;

	public LoadSave(final MainFrame mainFrame, final GameMode gameMode) {

		ImageProvider.refreshImageOfLevelCreated();
		ImageProvider.refreshImageOfSavedMatch();
		this.modeOfTheGame = gameMode;
		this.principalMainFrame = mainFrame;

		setPreferredSize(new Dimension(MainFrame.STANDAR_WIDTH, MainFrame.STANDARD_HEIGHT));
		setMinimumSize(new Dimension(MainFrame.STANDAR_WIDTH, MainFrame.STANDARD_HEIGHT));
		setMaximumSize(new Dimension(MainFrame.STANDAR_WIDTH, MainFrame.STANDARD_HEIGHT));
		setLayout(new GridBagLayout());
		setBackground(Color.GRAY);
		setBorder(null);
		setOpaque(false);

		back = new JButton(new ImageIcon(ImageProvider.getBack()));
		back.setPreferredSize(new Dimension(120, 60));
		back.setContentAreaFilled(false);
		back.setBorder(null);
		back.setFocusPainted(false);
		back.setRolloverEnabled(true);
		back.setRolloverIcon(new ImageIcon(ImageProvider.getBackRollovered()));
		back.setVisible(true);

		play = new JButton(new ImageIcon(ImageProvider.getPlay()));
		play.setPreferredSize(new Dimension(250, 150));
		play.setContentAreaFilled(false);
		play.setBorder(null);
		play.setFocusPainted(false);
		play.setRolloverIcon(new ImageIcon(ImageProvider.getPlay().getScaledInstance(110, 90, Image.SCALE_SMOOTH)));
		play.setVisible(true);

		delete = new JButton(new ImageIcon(ImageProvider.getDeleteButton()));
		delete.setPreferredSize(new Dimension(250, 150));
		delete.setContentAreaFilled(false);
		delete.setBorder(null);
		delete.setFocusPainted(false);
		delete.setRolloverEnabled(true);
		delete.setRolloverIcon(
				new ImageIcon(ImageProvider.getDeleteButton().getScaledInstance(130, 100, Image.SCALE_SMOOTH)));
		delete.setVisible(true);

		modify = new JButton(new ImageIcon(ImageProvider.getModify()));
		modify.setPreferredSize(new Dimension(250, 150));
		modify.setContentAreaFilled(false);
		modify.setBorder(null);
		modify.setFocusPainted(false);
		modify.setRolloverEnabled(true);
		modify.setVisible(true);

		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				principalMainFrame.switchToResumeContinuePanel();
			}

		});
		modify.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				principalMainFrame.startLevelEditor(indexOfCurrentRescueSelected + 1);
			}
		});
		modify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

			}
		});
		play.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (modeOfTheGame.equals(GameMode.MATCH))
					principalMainFrame.startGame(HowToPlay.LOAD, indexOfCurrentRescueSelected + 1);
				else
					principalMainFrame.startGame(HowToPlay.EDITOR, indexOfCurrentRescueSelected + 1);
			}
		});
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (modeOfTheGame.equals(GameMode.MATCH)) {
					FileProvider.deleteRescue(GameMode.MATCH, indexOfCurrentRescueSelected + 1);
					refreshNamesOfTheRescue();
					repaint();
					model.remove(indexOfCurrentRescueSelected);

				}

				else if (modeOfTheGame.equals(GameMode.EDITOR)) {
					FileProvider.deleteRescue(GameMode.EDITOR, indexOfCurrentRescueSelected + 1);
					FileProvider.deleteRescue(GameMode.EDITOR, indexOfCurrentRescueSelected + 1);
					refreshNamesOfTheRescue();
					repaint();
					model.remove(indexOfCurrentRescueSelected);

				}
			}
		});

		refreshNamesOfTheRescue();

		model = new DefaultListModel<String>();
		containerOfTheName = new JList<String>(model);

		for (String s : namesOfTheRescue)
			model.addElement(s);

		DefaultListCellRenderer renderer = (DefaultListCellRenderer) containerOfTheName.getCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		containerOfTheName.setCellRenderer(new MyListRenderer());
		containerOfTheName.setCellRenderer(renderer);

		containerOfTheName.setBorder(null);
		containerOfTheName.setFont(new Font("BOLD", Font.ITALIC, 16));
		containerOfTheName.setBackground(new Color(255, 255, 255, 0));

		scrollPane = new JScrollPane(containerOfTheName);
		scrollPane.setBackground(Color.GRAY);
		scrollPane.setPreferredSize(new Dimension(300, 300));
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVisible(true);

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 1;
		constraints.gridy = 0;

		JPanel panelForRescue = new JPanel();
		panelForRescue.setBackground(new Color(0, 0, 0, 50));
		panelForRescue.setBorder(null);
		panelForRescue.add(scrollPane);
		this.add(panelForRescue, constraints);

		constraints.gridx = 2;
		constraints.gridy = 0;
		this.add(play, constraints);

		if (modeOfTheGame.equals(GameMode.EDITOR)) {
			constraints.gridx = 1;
			constraints.gridy = 3;
			this.add(modify, constraints);
			constraints.gridx = 1;
			constraints.gridy = 5;
			this.add(back, constraints);
		}

		else {
			constraints.gridx = 1;
			constraints.gridy = 3;
			this.add(back, constraints);
		}
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.add(delete, constraints);

		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				final boolean adjust = listSelectionEvent.getValueIsAdjusting();
				if (!adjust) {
					JList<?> list = (JList<?>) listSelectionEvent.getSource();
					final int selections[] = list.getSelectedIndices();

					Object selectionValues = (Object) list.getSelectedValuesList();
					for (int i = 0, n = selections.length; i < n; i++) {

						new String(selectionValues.toString());
						indexOfCurrentRescueSelected = selections[i];
						repaint();
					}
				}
			}
		};

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList<?> jList = (JList<?>) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					final int index = jList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						if (modeOfTheGame.equals(GameMode.MATCH))
							principalMainFrame.startGame(HowToPlay.LOAD, index + 1);
						else if (modeOfTheGame.equals(GameMode.EDITOR))
							principalMainFrame.startGame(HowToPlay.EDITOR, index + 1);
					}
				}
			}
		};
		containerOfTheName.addMouseListener(mouseListener);
		containerOfTheName.addListSelectionListener(listSelectionListener);
	}

	private class MyListRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = 1L;
		private HashMap<Object, String> theChosen = new HashMap<Object, String>();

		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (isSelected) {
				theChosen.put(value, "chosen");
				setForeground(Color.YELLOW.brighter().brighter());
			}
			return (this);
		}
	}

	private void refreshNamesOfTheRescue() {

		namesOfTheRescue = new Vector<String>(FileProvider.getRescueNames(modeOfTheGame));

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (modeOfTheGame.equals(GameMode.MATCH))
			g.drawImage(ImageProvider.getImageSaveMatch(indexOfCurrentRescueSelected), 0, 0, null);
		else

			g.drawImage(ImageProvider.getImageSaveEditor(indexOfCurrentRescueSelected), 0, 0, null);

		g.drawImage(ImageProvider.getTransparency(), 0, 0, null);
	}
}
