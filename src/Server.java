import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server {
	// initialize socket and input output streams
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;

	public boolean connectionStatus = false;
	public boolean startAttempt = false;

	// constructor to put ip address and port

	Scanner input = new Scanner(System.in);

	void start(int port) {
		// establish a connection

		try {

			server = new ServerSocket(port);
			startAttempt = true;

			System.out.println("Server started!");

			socket = server.accept();
			connectionStatus = true;
			System.out.println("Connected to Client!");

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