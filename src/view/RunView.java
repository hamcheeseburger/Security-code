package view;

import java.awt.EventQueue;

public class RunView {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
