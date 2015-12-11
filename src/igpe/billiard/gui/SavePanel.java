package igpe.billiard.gui;

import igpe.billiard.core.GameMode;
import igpe.billiard.file.FileProvider;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Vector;


import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SavePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private MainFrame principalMainFrame;
	private Vector<String> nameOfSave;
	private JList<String> containerNamesOfTheRescue;
	private JScrollPane scrollPane;
	private JButton save;
	private JButton back;
	private int indexOfCurrentRescueSelected;
	private TextField nameOfTheRescue;
	private GameMode modeOfTheGame;

	public SavePanel(final MainFrame mainFrame,final GameMode gameMode) {
		this.modeOfTheGame = gameMode;
		this.principalMainFrame = mainFrame;

		setPreferredSize(new Dimension(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT));
		setMinimumSize(new Dimension(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT));
		setMaximumSize(new Dimension(MainFrame.GAME_WIDTH, MainFrame.GAME_HEIGHT));
		setLayout(new BorderLayout());
		
		nameOfSave = FileProvider.getRescueNames(modeOfTheGame);
		
		nameOfTheRescue = new TextField("Insert Name Of The Rescue");
		nameOfTheRescue.setMaximumSize(new Dimension(300,300));
	
		nameOfTheRescue.setVisible(true);
		
		nameOfTheRescue.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				nameOfTheRescue.setText("");
			}
		});
		
		nameOfTheRescue.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if (!nameOfSave.contains(nameOfTheRescue.getText().toString())) 
					{
						FileProvider.addRescue(modeOfTheGame, nameOfTheRescue.getText());
						showCorrectRescue();
					} 
					else 
						showOverrideRescueConfirm();
					
				}
			}
			
		});

		JPanel panelForTheNameOfRescue = new JPanel();
		panelForTheNameOfRescue.setLayout(new GridBagLayout());
		panelForTheNameOfRescue.setOpaque(false);
		GridBagConstraints constraint = new GridBagConstraints();

		containerNamesOfTheRescue = new JList<String>(nameOfSave);
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)containerNamesOfTheRescue.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);
	
		
		
		containerNamesOfTheRescue.setCellRenderer(new MyListRenderer());
		containerNamesOfTheRescue.setCellRenderer(renderer);
		containerNamesOfTheRescue.setBorder(null);
		containerNamesOfTheRescue.setFont(new Font("BOLD", Font.ITALIC, 16));
		containerNamesOfTheRescue.setBackground(new Color(255, 255, 255, 0));
		containerNamesOfTheRescue.setOpaque(false);
		((javax.swing.DefaultListCellRenderer) containerNamesOfTheRescue.getCellRenderer())
				.setOpaque(false);

		
		scrollPane = new JScrollPane(containerNamesOfTheRescue);
		scrollPane.setBackground(Color.GRAY);
		scrollPane.setPreferredSize(new Dimension(300, 300));
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVisible(true);

		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				boolean adjust = listSelectionEvent.getValueIsAdjusting();
				if (!adjust) {
					JList<?> list = (JList<?>) listSelectionEvent.getSource();
					int selections[] = list.getSelectedIndices();
					@SuppressWarnings("deprecation")
					Object selectionValues[] = list.getSelectedValues();
					for (int i = 0, n = selections.length; i < n; i++) {
						new String(selectionValues[i].toString());
						indexOfCurrentRescueSelected = selections[i];
						nameOfTheRescue.setText(nameOfSave.get(indexOfCurrentRescueSelected)
								.substring(
										0,
										nameOfSave.get(indexOfCurrentRescueSelected)
												.length()));
					}
				}
			}
		};

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				JList<?> theList = (JList<?>) mouseEvent.getSource();
				if (mouseEvent.getClickCount() == 2) {
					int index = theList.locationToIndex(mouseEvent.getPoint());
					if (index >= 0) {
						nameOfTheRescue.setText(nameOfSave.get(indexOfCurrentRescueSelected));
					}
				}
			}
		};

		containerNamesOfTheRescue.addMouseListener(mouseListener);
		containerNamesOfTheRescue.addListSelectionListener(listSelectionListener);

		save = new JButton(new ImageIcon(ImageProvider.getSave()));
		save.setPreferredSize(new Dimension(180, 80));
		save.setContentAreaFilled(false);
		save.setBorder(null);
		save.setFocusPainted(false);
		save.setRolloverEnabled(true);
		save.setRolloverIcon(new ImageIcon(ImageProvider.getSaveRollovered()));
		save.setVisible(true);

		back = new JButton(new ImageIcon(ImageProvider.getBack().getScaledInstance(165, 65, Image.SCALE_SMOOTH)));
		back.setPreferredSize(new Dimension(180, 80));
		back.setContentAreaFilled(false);
		back.setBorder(null);
		back.setFocusPainted(false);
		back.setRolloverEnabled(true);
		back.setRolloverIcon(new ImageIcon(ImageProvider.getBackRollovered().getScaledInstance(170, 70, Image.SCALE_SMOOTH)));
		back.setVisible(true);

		constraint.gridx = 0;
		constraint.gridy = 0;
		panelForTheNameOfRescue.add(nameOfTheRescue, constraint);
		constraint.gridx = 0;
		constraint.gridy = 1;
		panelForTheNameOfRescue.add(scrollPane, constraint);

		add(panelForTheNameOfRescue, BorderLayout.CENTER);
		
		JPanel panelButton = new JPanel(new GridBagLayout());
		GridBagConstraints constraintButton = new GridBagConstraints();
		panelButton.setOpaque(false);
		panelButton.add(back, constraintButton);
		constraintButton.gridx = 1;
		panelButton.add(save, constraintButton);

		add(panelButton, BorderLayout.SOUTH);

		back.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				principalMainFrame.switchToPausePanel();
			}
		});

		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if (!nameOfSave.contains(nameOfTheRescue.getText().toString())) {
					FileProvider.addRescue(modeOfTheGame, nameOfTheRescue.getText());
					showCorrectRescue();
				} else 
					showOverrideRescueConfirm();
			}
		});
	}

	private void showOverrideRescueConfirm() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(4), 0, 0, null);
				g.drawImage(ImageProvider.getDoYouWantToOverwriteTheRescue(),
						150, 50, null);
			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
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
				String nameSave = nameOfTheRescue.getText();
				FileProvider.overrideRescue(modeOfTheGame, indexOfCurrentRescueSelected + 1,
						nameSave);
				if (modeOfTheGame.equals(GameMode.MATCH))
					principalMainFrame.switchToPausePanel();
				else if (modeOfTheGame.equals(GameMode.EDITOR))
					principalMainFrame.switchToChooseHowToPlayPanel();
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
		dialog.setTitle("OverWrite Rescue");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	private void showCorrectRescue() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(2), 0, 0, null);
				g.drawImage(ImageProvider.getSaveMadeCorrectly(), 150, 50, null);
			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
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

		ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				dialog.dispose();
				if (modeOfTheGame.equals(GameMode.MATCH))
					principalMainFrame.switchToPausePanel();
				else if (modeOfTheGame.equals(GameMode.EDITOR))
					principalMainFrame.switchToResumeContinuePanel();
			}
		});
		panel.add(ok);
		dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		dialog.add(panel);
		dialog.setTitle("Correct Saved");
		dialog.setSize(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(
				Toolkit.getDefaultToolkit().getScreenSize().width / 3, Toolkit
						.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(FileProvider.getScreenOfTheGame(), PausePanel.WIDTH,
				PausePanel.HEIGHT, null);
		g.drawImage(ImageProvider.getTransparency(), 0, 0, null);
	}

	// class for opaque jlist
	private class MyListRenderer extends DefaultListCellRenderer {
		private static final long serialVersionUID = 1L;
		private HashMap<Object, String> theChosen = new HashMap<Object, String>();

		public Component getListCellRendererComponent(JList<?> list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected,
					cellHasFocus);
			if (isSelected) {
				theChosen.put(value, "chosen");
				setForeground(Color.YELLOW);

			} else {
				setForeground(Color.BLACK);
			}
			return (this);
		}
	}
}
