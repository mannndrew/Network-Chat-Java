import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartWindow {
	
	boolean clientCheck = false;
	boolean serverCheck = false;
	
	JFrame frame = new JFrame();
	
	public StartWindow() {
	
		JButton client = new JButton();
		client.setPreferredSize(new Dimension(380, 50));
		client.setBackground(Color.white);
		client.setFocusable(false);
		client.setText("Client");

		JButton server = new JButton();
		server.setPreferredSize(new Dimension(380, 50));
		server.setBackground(Color.white);
		server.setFocusable(false);
		server.setText("Server");
		
		JButton go = new JButton();
		go.setFocusable(false);
		go.setText("Go!");
		
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(0, 115));
		topPanel.setBackground(Color.darkGray);
		topPanel.add(client);
		topPanel.add(server);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(0, 40));
		bottomPanel.setBackground(Color.darkGray);
		bottomPanel.add(go);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.darkGray);
		
		frame = new JFrame();
		frame.setTitle("Network Chat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(200, 200);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(400, 500);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(go, BorderLayout.SOUTH);
		
		
		
		client.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				client.setEnabled(false);
				client.setBackground(Color.darkGray);
				
				if (server.isEnabled()==false) {
					server.setEnabled(true);
					server.setBackground(Color.white);
				}	
			}
		});
		
		server.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				server.setEnabled(false);
				server.setBackground(Color.darkGray);
				
				if (client.isEnabled()==false) {
					client.setEnabled(true);
					client.setBackground(Color.white);
				}
			}
		});
		
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if (client.isEnabled()==false) {
					clientCheck = true;
				}
				
				else if (server.isEnabled()==false) {
					serverCheck = true;
				}
			}
		});
	
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}