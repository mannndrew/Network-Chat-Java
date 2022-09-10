import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UsernameWindow {

	String username = "";
	boolean validUsername = false;

	public UsernameWindow() {

		JTextField enterUsername = new JTextField();
		enterUsername.setPreferredSize(new Dimension(190, 30));
		enterUsername.setForeground(new Color(0xFFFFFF));
		enterUsername.setBackground(Color.black);
		enterUsername.setCaretColor(Color.white);

		JButton submit = new JButton();
		submit.setPreferredSize(new Dimension(80, 30));
		submit.setBackground(Color.white);
		submit.setFocusable(false);
		submit.setText("Submit");

		JTextArea warning = new JTextArea();
		warning.setForeground(new Color(0xFF0000));
		warning.setBackground(Color.darkGray);
		warning.setCaretColor(Color.white);
		warning.setEditable(false);

		JLabel titleEnterUser = new JLabel("Username: ");
		titleEnterUser.setForeground(new Color(0xFFFFFF));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(0, 40));
		bottomPanel.setBackground(Color.darkGray);

		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.darkGray);
		centerPanel.add(enterUsername);
		centerPanel.add(submit);
		centerPanel.add(warning);

		JFrame frame = new JFrame();
		frame.setTitle("Enter Username");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(200, 200);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(300, 100);
		frame.add(centerPanel, BorderLayout.CENTER);

		enterUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				username = enterUsername.getText();

				if (username.equals("")) {
					warning.setText("Username cannot be blank.");
				}

				else {
					validUsername = true;
					frame.dispose();
				}

			}
		});

		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				username = enterUsername.getText();

				if (username.equals("")) {
					warning.setText("Username cannot be blank.");
				}

				else {
					validUsername = true;
					frame.dispose();
				}
			}
		});

	}
}