package LogisticApp.application;

import LogisticApp.view.LogisticAppFrame;

public class Main {
	
	public static void main(String[] args) {
		try {
			LogisticAppFrame frame = new LogisticAppFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
