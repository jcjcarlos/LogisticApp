package LogisticApp.view.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import LogisticApp.business.session.CadastroLocalidade;
import LogisticApp.business.session.interfaces.ICadastroLocalidadeSession;
import LogisticApp.exception.CadastroException;
import LogisticApp.view.interfaces.ILogisticFrame;

public class LocalidadeFrame extends JFrame implements ILogisticFrame, ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnFeito;
	private JButton btnVoltar;
	private JPanel contentPane;
	private JLabel lblId;
	private JLabel lblLocalidade;
	private JLabel lblNome;
	private JPanel panelFirst;
	private JPanel panelSecond;
	private JTextField txtId;
	private JTextField txtNome;

	private void saveLocalidade() throws CadastroException {
		try {
			int id = this.txtId.getText().isEmpty() ? 0 : Integer.parseInt(this.txtId.getText());
			String nome = this.txtNome.getText();
			if (id == 0)
				throw new CadastroException("Por favor informe um ID para a Localidade.");
			else if (nome.isEmpty())
				throw new CadastroException("Por favor informe um nome para a Localidade.");
			else {
				ICadastroLocalidadeSession cadastroLocalidade = new CadastroLocalidade();
				cadastroLocalidade.createLocalidade(id, nome);
			}
		} catch (CadastroException ex) {
			throw ex;
		}
		JOptionPane.showMessageDialog(null, "Localidade salva com sucesso.", "Sucesso", JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ILogisticFrame next = null;
		if (e.getSource().equals(this.btnFeito)) {
			try {
				this.saveLocalidade();
			} catch (CadastroException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource().equals(this.btnVoltar))
			next = new CadastroFrame();
		if (next != null) {
			next.initialize();
			this.dispose();
		}
	}

	@Override
	public void initialize() {
		this.setTitle("Localidade");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 343, 332);
		this.setLocationRelativeTo(null);
		this.initializeElements();
		this.setVisible(true);
	}

	private void initializeAreas() {
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut_2.gridx = 10;
		gbc_verticalStrut_2.gridy = 0;
		this.panelFirst.add(verticalStrut_2, gbc_verticalStrut_2);

		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_9 = new GridBagConstraints();
		gbc_rigidArea_9.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_9.gridx = 0;
		gbc_rigidArea_9.gridy = 1;
		this.panelFirst.add(rigidArea_9, gbc_rigidArea_9);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_3.gridx = 7;
		gbc_horizontalStrut_3.gridy = 1;
		this.panelFirst.add(horizontalStrut_3, gbc_horizontalStrut_3);

		Component rigidArea_10 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_10 = new GridBagConstraints();
		gbc_rigidArea_10.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_10.gridx = 9;
		gbc_rigidArea_10.gridy = 1;
		this.panelFirst.add(rigidArea_10, gbc_rigidArea_10);

		Component verticalStrut_5 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_5 = new GridBagConstraints();
		gbc_verticalStrut_5.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_5.gridx = 8;
		gbc_verticalStrut_5.gridy = 2;
		this.panelFirst.add(verticalStrut_5, gbc_verticalStrut_5);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalStrut_2.gridx = 5;
		gbc_horizontalStrut_2.gridy = 3;
		this.panelFirst.add(horizontalStrut_2, gbc_horizontalStrut_2);

		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_4 = new GridBagConstraints();
		gbc_rigidArea_4.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_4.gridx = 2;
		gbc_rigidArea_4.gridy = 1;
		this.panelSecond.add(rigidArea_4, gbc_rigidArea_4);

		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_7 = new GridBagConstraints();
		gbc_rigidArea_7.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_7.gridx = 3;
		gbc_rigidArea_7.gridy = 2;
		this.panelSecond.add(rigidArea_7, gbc_rigidArea_7);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_4 = new GridBagConstraints();
		gbc_horizontalStrut_4.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_4.gridx = 8;
		gbc_horizontalStrut_4.gridy = 2;
		this.panelSecond.add(horizontalStrut_4, gbc_horizontalStrut_4);

		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_5 = new GridBagConstraints();
		gbc_rigidArea_5.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_5.gridx = 2;
		gbc_rigidArea_5.gridy = 3;
		this.panelSecond.add(rigidArea_5, gbc_rigidArea_5);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_3 = new GridBagConstraints();
		gbc_rigidArea_3.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea_3.gridx = 0;
		gbc_rigidArea_3.gridy = 5;
		this.panelSecond.add(rigidArea_3, gbc_rigidArea_3);
	}

	private void initializeButtons() {
		this.btnVoltar = new JButton("< Voltar");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridwidth = 2;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 1;
		this.panelFirst.add(this.btnVoltar, gbc_button);

		this.btnFeito = new JButton("Feito");
		GridBagConstraints gbc_btnFeito = new GridBagConstraints();
		gbc_btnFeito.insets = new Insets(0, 0, 5, 5);
		gbc_btnFeito.gridx = 8;
		gbc_btnFeito.gridy = 1;
		this.panelFirst.add(this.btnFeito, gbc_btnFeito);

		this.btnFeito.addActionListener(this);
		this.btnVoltar.addActionListener(this);
	}

	private void initializeContentPane() {
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 52, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);
	}

	private void initializeElements() {
		this.initializeContentPane();
		this.initializePanels();
		this.initializeAreas();
		this.initializeButtons();
		this.initializeLabels();
		this.initializeTextFields();
	}

	private void initializeLabels() {
		this.lblLocalidade = new JLabel("Localidade");
		GridBagConstraints gbc_lblLocalidade = new GridBagConstraints();
		gbc_lblLocalidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocalidade.gridx = 6;
		gbc_lblLocalidade.gridy = 1;
		panelFirst.add(this.lblLocalidade, gbc_lblLocalidade);

		this.lblId = new JLabel("ID:");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 2;
		gbc_lblId.gridy = 2;
		panelSecond.add(this.lblId, gbc_lblId);

		this.lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 0, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 2;
		gbc_lblNome.gridy = 5;
		panelSecond.add(this.lblNome, gbc_lblNome);
	}

	private void initializePanels() {

		this.panelFirst = new JPanel();
		this.panelFirst.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;

		this.contentPane.add(this.panelFirst, gbc_panel_1);

		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.panelFirst.setLayout(gbl_panel_1);

		this.panelSecond = new JPanel();

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;

		contentPane.add(this.panelSecond, gbc_panel);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.panelSecond.setLayout(gbl_panel);

	}

	private void initializeTextFields() {

		this.txtId = new JFormattedTextField();
		this.txtId.setColumns(10);

		this.txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String keys = "0123456789";
				char number = e.getKeyChar();
				if (!keys.contains(number + "") || txtId.getText().length() == 10)
					e.consume();
			}
		});

		GridBagConstraints gbc_txtId = new GridBagConstraints();
		gbc_txtId.gridwidth = 4;
		gbc_txtId.insets = new Insets(0, 0, 5, 5);
		gbc_txtId.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtId.gridx = 4;
		gbc_txtId.gridy = 2;
		this.panelSecond.add(this.txtId, gbc_txtId);

		this.txtNome = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 5;
		this.panelSecond.add(this.txtNome, gbc_textField);
		this.txtNome.setColumns(10);
	}

}
