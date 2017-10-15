package LogisticApp.view.interfaces;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

public class LogisticAppFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton cadastroButton;
	private JButton contratacaoButton;

	public LogisticAppFrame() {
		super("LogisticApp - Menu principal");
		this.initializeButtons();
		
		// TODO: initialize container
		// TODO: initialize layout and add buttons
		// TODO: organize buttons
		// TODO: create buttons events
		// TODO: set page position parameters
		
	}

	private void initializeButtons() {
		this.cadastroButton = new JButton("Cadastro");
		this.cadastroButton.setFont(new Font("Arial", Font.BOLD, 20));
		this.cadastroButton.setBackground(new Color(14, 122, 254));

		this.contratacaoButton = new JButton("Contratação de Transporte");
		this.contratacaoButton.setFont(new Font("Arial", Font.BOLD, 20));
		this.contratacaoButton.setBackground(new Color(14, 122, 254));
	}

}
