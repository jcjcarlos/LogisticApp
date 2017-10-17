package LogisticApp.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import LogisticApp.view.interfaces.IApplicationView;
import LogisticApp.view.interfaces.ILogisticFrame;

public class MenuFrame extends JFrame implements ActionListener, IApplicationView, ILogisticFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnCadastro;
	private JButton btnContratacao;
	private JPanel contentPane;
	private JLabel lblTitle;

	public void actionPerformed(ActionEvent e) {
		ILogisticFrame next = null;
		if (e.getSource().equals(this.btnCadastro))
			next = new CadastroFrame();
		else if (e.getSource().equals(this.btnContratacao))
			next = new MenuContratacaoTransporteFrame();
		if (next != null) {
			next.initialize();
			this.dispose();
		}
	}

	@Override
	public void initialize(){
		this.setTitle("LogisticApp");
		this.setBounds(100, 100, 273, 299);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.initializeElements();
		this.setVisible(true);
	}

	private void initializeButtons() {
		this.btnCadastro = new JButton("Cadastro");
		this.btnCadastro.addActionListener(this);
		this.btnContratacao = new JButton("Contratação de Transporte");
		this.btnContratacao.addActionListener(this);
	}

	private void initializeContentPane() {
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.setContentPane(contentPane);
	}

	private void initializeElements() {
		this.initializeContentPane();
		this.initializeLabel();
		this.initializeButtons();
		this.initializeGroupLayout();
	}

	private void initializeGroupLayout() {
		GroupLayout groupLayout = new GroupLayout(contentPane);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap()
								.addComponent(this.btnContratacao, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addGap(79).addComponent(this.btnCadastro,
								GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap(45, Short.MAX_VALUE)
						.addComponent(this.lblTitle, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
						.addGap(40)));

		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(33)
				.addComponent(this.lblTitle, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE).addGap(39)
				.addComponent(btnCadastro, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE).addGap(33)
				.addComponent(this.btnContratacao, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(47, Short.MAX_VALUE)));

		this.contentPane.setLayout(groupLayout);
	}

	private void initializeLabel() {
		this.lblTitle = new JLabel("LogisticApp");
		this.lblTitle.setVerticalAlignment(SwingConstants.TOP);
		this.lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
	}
	
	@Override
	public void start() {
		this.initialize();
	}

}
