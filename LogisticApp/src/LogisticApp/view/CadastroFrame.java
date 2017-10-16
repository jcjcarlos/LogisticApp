package LogisticApp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import LogisticApp.view.interfaces.IApplicationView;

public class CadastroFrame extends JFrame implements ActionListener, IApplicationView {

	private static final long serialVersionUID = 1L;
	private JButton btnLocalidade;
	private JButton btnRota;
	private JButton btnVoltar;

	@Override
	public void actionPerformed(ActionEvent e) {
		IApplicationView next = null;
		if (e.getSource().equals(this.btnLocalidade))
			next = new LocalidadeFrame();
		else if (e.getSource().equals(this.btnRota))
			next = new RotaFrame();
		else if (e.getSource().equals(this.btnVoltar))
			next = new MenuFrame();
		if (next != null) {
			next.start();
			this.dispose();
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

}
