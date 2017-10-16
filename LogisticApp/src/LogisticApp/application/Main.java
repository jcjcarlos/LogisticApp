package LogisticApp.application;

import LogisticApp.view.MenuFrame;
import LogisticApp.view.interfaces.IApplicationView;

public class Main {

	public static void main(String[] args) {
		(new Main()).run();
	}

	public void run() {
		IApplicationView app = new MenuFrame();
		app.start();
	}

}
