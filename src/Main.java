public class Main {

	@SuppressWarnings("unused")

	public static void main(String[] args) {

		StartWindow menu = new StartWindow();

		while (menu.clientCheck == false && menu.serverCheck == false) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		UsernameWindow getUsername = new UsernameWindow();

		while (getUsername.validUsername == false) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		menu.frame.dispose();

		if (menu.clientCheck == true) {
			ClientWindow client = new ClientWindow(getUsername.username);
		}

		else if (menu.serverCheck == true) {
			ServerWindow server = new ServerWindow(getUsername.username);
		}
	}
}
