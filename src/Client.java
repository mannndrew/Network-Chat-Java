import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	// initialize socket and input output streams
	private Socket socket = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;

	public boolean connectionStatus = false;

	// constructor to put ip address and port

	void start(String address, int port) {
		// establish a connection

		try {

			socket = new Socket(address, port);
			connectionStatus = true;
			System.out.println("Connected to Server!");

			// takes input from terminal
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
		}

		catch (UnknownHostException u) {
			System.out.println(u);
		}

		catch (IOException i) {
			System.out.println(i);
		}

	}

	void sendMessage(String message) {

		try {
			out.writeUTF(message);
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	String recieveMessage() {

		String message = null;

		try {
			message = in.readUTF();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return message;
	}

}