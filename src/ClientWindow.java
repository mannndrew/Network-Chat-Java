import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientWindow {

	JTextField enterMessage = new JTextField();
	JTextField enterIP = new JTextField();
	JTextField enterPort = new JTextField();

	String Message = new String();
	String IP = new String();
	String Port = new String();
	String otherUser = "";

	Client client = new Client();

	boolean checkUser = false;

	public ClientWindow(String username) {

		enterMessage.setPreferredSize(new Dimension(300, 30));
		enterMessage.setForeground(new Color(0xFFFFFF));
		enterMessage.setBackground(Color.black);
		enterMessage.setCaretColor(Color.white);
		enterMessage.setEnabled(false);

		enterIP.setPreferredSize(new Dimension(100, 20));
		enterIP.setForeground(new Color(0xFFFFFF));
		enterIP.setBackground(Color.black);
		enterIP.setCaretColor(Color.white);

		enterPort.setPreferredSize(new Dimension(50, 20));
		enterPort.setForeground(new Color(0xFFFFFF));
		enterPort.setBackground(Color.black);
		enterPort.setCaretColor(Color.white);
		enterPort.setText("5000");

		JTextArea chat = new JTextArea();
		chat.setForeground(new Color(0xFFFFFF));
		chat.setBackground(Color.darkGray);
		chat.setCaretColor(Color.white);
		chat.setEditable(false);
		chat.setLineWrap(true);
		chat.setWrapStyleWord(true);

		JScrollPane scroll = new JScrollPane(chat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JButton send = new JButton();
		send.setFocusable(false);
		send.setEnabled(false);
		send.setText("Send");

		JButton connect = new JButton();
		connect.setFocusable(false);
		connect.setText("Connect");

		JLabel titleIP = new JLabel("IP: ");
		titleIP.setForeground(new Color(0xFFFFFF));

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
		topPanel.add(titleIP);
		topPanel.add(enterIP);
		topPanel.add(titlePort);
		topPanel.add(enterPort);
		topPanel.add(titleSpacer);
		topPanel.add(connect);

		JFrame frame = new JFrame();
		frame.setTitle("Network Chat - Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(200, 200);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(400, 500);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.add(scroll, BorderLayout.CENTER);
		frame.add(topPanel, BorderLayout.NORTH);

		enterMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Message = enterMessage.getText();
				enterMessage.setText("");

				client.sendMessage(username + ":\n" + Message);
				chat.setText(chat.getText() + username + ":\n" + Message + "\n\n");
			}
		});

		send.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Message = enterMessage.getText();
				enterMessage.setText("");

				client.sendMessage(username + ":\n" + Message);
				chat.setText(chat.getText() + username + ":\n" + Message + "\n\n");
			}
		});

		connect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				IP = enterIP.getText();
				Port = enterPort.getText();

				client.start(IP, Integer.valueOf(Port));

			}
		});

		while (true) {

			while (client.connectionStatus == false) {

				System.out.println("Not connected...");

				try {
					Thread.sleep(1000);
				}

				catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}

			connect.setText("Connected");
			connect.setEnabled(false);
			enterIP.setEditable(false);
			enterPort.setEditable(false);
			enterMessage.setEnabled(true);
			send.setEnabled(true);

			if (checkUser == false) {
				client.sendMessage(username);
				otherUser = client.recieveMessage();
				checkUser = true;
			}

			Message = client.recieveMessage();

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
