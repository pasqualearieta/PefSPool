package igpe.billiard.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JDialog dialogForConnection;
	private final JTextField ipTextField;
	private final MainFrame principalMainFrame;
	private final JTextField portTextField;
	private String modalityOfOnlineGame = null;
	JButton connectButton = null;

	public ConnectionPanel(final MainFrame mainFrame, JDialog dialog) {
		super(new BorderLayout());
		this.principalMainFrame = mainFrame;
		this.dialogForConnection = dialog;
		dialogForConnection.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		final JPanel content = new JPanel(new GridBagLayout());
		content.setSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(20, 20, 20, 20);
		constraints.gridx = 0;
		constraints.gridy = 0;
		content.add(new JLabel(new ImageIcon(ImageProvider.getServerIP())), constraints);
		ipTextField = new JTextField(20);
		ipTextField.setText("127.0.0.1");
		constraints.gridx++;
		constraints.weightx = 0.01;
		content.add(ipTextField, constraints);
		constraints.gridy++;
		constraints.gridx = 0;
		content.add(new JLabel(new ImageIcon(ImageProvider.getServerPort())), constraints);
		portTextField = new JTextField(20);
		portTextField.setText("2727");
		constraints.gridx++;
		content.add(portTextField, constraints);

		constraints.gridy++;
		constraints.gridx = 1;
		content.setOpaque(false);
		final JButton connectButton = new JButton(new ImageIcon(ImageProvider.getConnect()));
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {

					connectButton.setEnabled(false);
					dialog.dispose();
					createOrJoinTable();
				} catch (final Exception e1) {
					showImpossibleEstabilishedConnection();
				}

			}
		});
		connectButton.setContentAreaFilled(false);
		connectButton.setBorder(null);
		connectButton.setContentAreaFilled(false);
		connectButton.setRolloverEnabled(true);
		connectButton.setRolloverIcon(new ImageIcon(ImageProvider.getConnectRollovered()));
		content.add(connectButton, constraints);
		add(content, BorderLayout.CENTER);
	}

	protected void connectoToServer() throws Exception {
		final Socket socket = new Socket(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
		final ConnectionManager connectionManager = new ConnectionManager(socket, principalMainFrame.getPlayerName(),
				principalMainFrame, modalityOfOnlineGame);
		new Thread(connectionManager, "Connection Manager").start();
		dialogForConnection.dispose();
	}

	private void createOrJoinTable() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(12), 0, 0, null);

			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JDialog dialog = new JDialog();
		JButton createTable = new JButton(new ImageIcon(ImageProvider.getCreateGame()));

		createTable.setContentAreaFilled(false);
		createTable.setBorder(null);
		createTable.setFocusPainted(false);
		createTable.setRolloverEnabled(true);
		createTable.setRolloverIcon(new ImageIcon(ImageProvider.getCreateGameRollover()));
		createTable.setVisible(true);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		createTable.setAlignmentX(CENTER_ALIGNMENT);
		createTable.setAlignmentY(CENTER_ALIGNMENT);

		panel.add(Box.createVerticalStrut(50));

		panel.add(createTable);
		panel.add(Box.createVerticalStrut(50));

		createTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				dialog.dispose();
				setModalityOfOnlineGame("CREATE");
				try {
					connectoToServer();

				} catch (final Exception e1) {
					showImpossibleEstabilishedConnection();
				}
			}
		});

		JButton joinTable = new JButton(new ImageIcon(ImageProvider.getJoinATable()));

		joinTable.setContentAreaFilled(false);
		joinTable.setBorder(null);
		joinTable.setFocusPainted(false);
		joinTable.setRolloverEnabled(true);
		joinTable.setRolloverIcon(new ImageIcon(ImageProvider.getJoinATableRolloverd()));
		joinTable.setVisible(true);

		joinTable.setAlignmentX(CENTER_ALIGNMENT);
		joinTable.setAlignmentY(CENTER_ALIGNMENT);
		panel.add(joinTable);

		joinTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				dialog.dispose();
				setModalityOfOnlineGame("JOIN");
				try {
					connectoToServer();

				} catch (final Exception e1) {
					showImpossibleEstabilishedConnection();
				}
			}
		});

		dialog.add(panel);

		dialog.setSize(500, 300);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(ImageProvider.getLittleBackground(2), 0, 0, null);
	}

	private void showImpossibleEstabilishedConnection() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.black);
				g.drawImage(ImageProvider.getLittleBackground(0), 0, 0, null);
				g.drawImage(ImageProvider.getImpossibleEstabilishedConnection(), 150, 50, null);
			
			
			}
		};
		panel.setSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
		panel.setPreferredSize(new Dimension(MainFrame.DIALOG_WIDTH, MainFrame.DIALOG_HEIGHT));
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
		dialog.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
				Toolkit.getDefaultToolkit().getScreenSize().height / 3);
		dialog.setVisible(true);
	}

	public void setModalityOfOnlineGame(String modalityOfOnlineGame) {
		this.modalityOfOnlineGame = modalityOfOnlineGame;
	}
}
