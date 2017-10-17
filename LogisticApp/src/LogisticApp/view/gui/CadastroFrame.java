package LogisticApp.view.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import LogisticApp.view.interfaces.ILogisticFrame;

public class CadastroFrame extends JFrame implements ActionListener, ILogisticFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnLocalidade;
	private JButton btnRota;
	private JButton btnVoltar;
	private JPanel contentPane;
	private JLabel lblCadastro;
	private JPanel panelFirst;
	private JPanel panelSecond;

	@Override
	public void actionPerformed(ActionEvent e) {
		ILogisticFrame next = null;
		if (e.getSource().equals(this.btnLocalidade))
			next = new LocalidadeFrame();
		else if (e.getSource().equals(this.btnRota))
			next = new RotaFrame();
		else if (e.getSource().equals(this.btnVoltar))
			next = new MenuFrame();
		if (next != null) {
			next.initialize();
			this.dispose();
		}
	}
	
	@Override
	public void initialize() {
		this.setTitle("Cadastro");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 278, 354);
		this.setLocationRelativeTo(null);
		this.initializeElements();
		this.setVisible(true);
	}
	
	private void initializeButtons(){
		this.btnVoltar = new JButton("< Voltar");
		GridBagConstraints gbc_btnVoltar = new GridBagConstraints();
		gbc_btnVoltar.insets = new Insets(0, 0, 5, 5);
		gbc_btnVoltar.gridx = 1;
		gbc_btnVoltar.gridy = 1;
		this.panelFirst.add(this.btnVoltar, gbc_btnVoltar);
		
		this.btnLocalidade = new JButton("Localidade");
		GridBagConstraints gbc_lblLocalidade = new GridBagConstraints();
		gbc_lblLocalidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLocalidade.gridwidth = 2;
		gbc_lblLocalidade.anchor = GridBagConstraints.NORTH;
		gbc_lblLocalidade.insets = new Insets(0, 0, 5, 0);
		gbc_lblLocalidade.gridx = 0;
		gbc_lblLocalidade.gridy = 2;
		this.panelSecond.add(this.btnLocalidade, gbc_lblLocalidade);
		
		this.btnRota = new JButton("Rota");
		GridBagConstraints gbc_btnRota = new GridBagConstraints();
		gbc_btnRota.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRota.gridwidth = 2;
		gbc_btnRota.insets = new Insets(0, 0, 5, 0);
		gbc_btnRota.gridx = 0;
		gbc_btnRota.gridy = 5;
		this.panelSecond.add(this.btnRota, gbc_btnRota);
		
		this.btnRota.addActionListener(this);
		this.btnVoltar.addActionListener(this);
		this.btnLocalidade.addActionListener(this);
	}
	
	private void initializeContentPane(){
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{262, 0};
		gbl_contentPane.rowHeights = new int[]{72, 72, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
	}
	
	private void initializeElements(){
		this.initializeContentPane();
		this.initializePanels();
		this.initializeLabels();
		this.initializeButtons();
	}
	
	private void initializeLabels(){
		this.lblCadastro = new JLabel("Cadastro");
		this.lblCadastro.setFont(new Font("Dialog", Font.BOLD, 14));
		
		GridBagConstraints gbc_lblCadastro = new GridBagConstraints();
		gbc_lblCadastro.insets = new Insets(0, 0, 5, 0);
		gbc_lblCadastro.gridwidth = 4;
		gbc_lblCadastro.gridx = 4;
		gbc_lblCadastro.gridy = 1;
		
		this.panelFirst.add(this.lblCadastro, gbc_lblCadastro);
	}

	private void initializePanels(){
		
		/* PANELFIRST INITIALIZATION */
		
		this.panelFirst = new JPanel();
		this.panelFirst.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		
		this.contentPane.add(this.panelFirst, gbc_panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		this.panelFirst.setLayout(gbl_panel);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 5;
		gbc_verticalStrut_1.gridy = 0;
		
		this.panelFirst.add(verticalStrut_1, gbc_verticalStrut_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 1;
		
		this.panelFirst.add(horizontalStrut, gbc_horizontalStrut);
		
		/* END PANELFIRST INITIALIZATION */
		
		/* PANELSECOND INITIALIZATION */
		
		this.panelSecond = new JPanel();
		this.panelSecond.setSize(282, 300);
		
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		
		this.contentPane.add(this.panelSecond, gbc_panel_1);
		
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{60, 77, 0};
		gbl_panel_1.rowHeights = new int[]{36, 0, 0, 0, 0, 0, 0, 0, 15, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		
		this.panelSecond.setLayout(gbl_panel_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 3;
		
		this.panelSecond.add(verticalStrut, gbc_verticalStrut);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut_2.gridx = 1;
		gbc_verticalStrut_2.gridy = 4;
		
		this.panelSecond.add(verticalStrut_2, gbc_verticalStrut_2);
		
		/* END PANELSECOND INITIALIZATION */
	}

}
