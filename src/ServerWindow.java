import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerWindow {

	JTextField enterMessage = new JTextField();
	JTextField enterPort = new JTextField();

	String Message = new String();
	String Port = new String();
	String username;
	String otherUser = "";

	Server server = new Server();

	boolean attempt = false;
	boolean checkUser = false;

	public ServerWindow(String username) {

		enterMessage.setPreferredSize(new Dimension(300, 30));
		enterMessage.setForeground(new Color(0xFFFFFF));
		enterMessage.setBackground(Color.black);
		enterMessage.setCaretColor(Color.white);
		enterMessage.setEnabled(false);

		enterPort.setPreferredSize(new Dimension(50, 20));
		enterPort.setForeground(new Color(0xFFFFFF));
		enterPort.setBackground(Color.black);
		enterPort.setCaretColor(Color.white);
		enterPort.setText("5000");

		JTextArea chat = new JTextArea();
		chat.setForeground(new Color(0xFFFFFF));
		chat.setBackground(Color.darkGray);
		chat.setEditable(false);
		chat.setLineWrap(true);
		chat.setWrapStyleWord(true);

		JScrollPane scroll = new JScrollPane(chat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JButton send = new JButton();
		send.setFocusable(false);
		send.setEnabled(false);
		send.setText("Send");

		JButton start = new JButton();
		start.setFocusable(false);
		start.setText("Start Server");

		JLabel titlePort = new JLabel("   Port: ");
		titlePort.setForeground(new Color(0xFFFFFF));

		JLabel titleSpacer = new JLabel("   ");
		titleSpacer.setForeground(new Color(0xFFFFFF));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 0, 0, 30);
		bottomPanel.setBackground(Color.darkGray);
		bottomPanel.add(enterMessage);
		bottomPanel.add(send);

		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0, 40));
		topPanel.setBackground(Color.blue);
		topPanel.add(titlePort);
		topPanel.add(enterPort);
		topPanel.add(titleSpacer);
		topPanel.add(start);

		JFrame frame = new JFrame();
		frame.setTitle("Network Chat - Server");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(200, 200);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(400, 500);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.add(scroll, BorderLayout.CENTER);
		frame.add(topPanel, BorderLayout.NORTH);

		Thread thread = new Thread() {

			public void run() {
				server.start(Integer.valueOf(Port));

			}
		};

		enterMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message = enterMessage.getText();
				enterMessage.setText("");

				server.sendMessage(username + ":\n" + Message);
				chat.setText(chat.getText() + username + ":\n" + Message + "\n\n");

			}
		});

		send.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Message = enterMessage.getText();
				enterMessage.setText("");

				server.sendMessage(username + ":\n" + Message);
				chat.setText(chat.getText() + username + ":\n" + Message + "\n\n");
			}
		});

		start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Port = enterPort.getText();

				if (attempt == false) {
					thread.start();
					attempt = true;
				}
			}
		});

		scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});

		while (true) {

			if (server.startAttempt == true) {

				enterPort.setEditable(false);
				start.setEnabled(false);
				start.setText("Started");
				break;
			}

			try {
				Thread.sleep(20);
			} catch (InterruptedException e2) {

				e2.printStackTrace();
			}
		}

		while (true) {

			while (server.connectionStatus == false) {

				System.out.println("Not connected...");

				try {
					Thread.sleep(1000);
				}

				catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}

			enterMessage.setEnabled(true);
			send.setEnabled(true);

			if (checkUser == false) {
				server.sendMessage(username);
				otherUser = server.recieveMessage();
				checkUser = true;
			}

			Message = server.recieveMessage();

			if (Message == null) {
				enterMessage.setEnabled(false);
				send.setEnabled(false);
				chat.setText(chat.getText() + otherUser + " has left the chat.");
				break;
			}

			chat.setText(chat.getText() + Message + "\n\n");

		}
	}
}
