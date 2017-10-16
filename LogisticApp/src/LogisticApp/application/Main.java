package LogisticApp.application;

import LogisticApp.view.LogisticAppFrame;
import LogisticApp.view.interfaces.IApplicationView;

public class Main {

	public static void main(String[] args) {
		IApplicationView app = new LogisticAppFrame();
		app.start();
	}

}
