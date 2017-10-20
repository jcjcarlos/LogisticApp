package LogisticApp.view.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import LogisticApp.business.session.CadastroRota;
import LogisticApp.business.session.interfaces.ICadastroRotaSession;
import LogisticApp.exception.CadastroException;
import LogisticApp.view.gui.interfaces.ILogisticFrame;
import LogisticApp.view.vo.RotaPanelInfoVO;

public class RotaFrame extends JFrame implements ILogisticFrame, ActionListener {

	private static final String DIRETA = "Direta";
	private static final String FRACIONAL = "Fracional";
	private static String previousTipo = null;
	private static final long serialVersionUID = 1L;

	private JButton btnFeito;
	private JButton btnVoltar;
	private ICadastroRotaSession cadastroRota;
	private JComboBox<String> cbxTipo;
	private JPanel contentPane;
	private JLabel lblNome;
	private JLabel lblRota;
	private JLabel lblTipo;
	private JPanel panelFirst;
	private JPanel panelRota;
	private JPanel panelSecond;
	private RotaPanelBuilder rotaPanelBuilder;
	private JTextField txtNome;

	public RotaFrame() {
		this.cadastroRota = new CadastroRota();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.cbxTipo)) {
			String value = (String) this.cbxTipo.getSelectedItem();
			if (value == null)
				this.clearPanelRota();
			else if (!value.equals(RotaFrame.previousTipo)) {
				if (value.equals(RotaFrame.DIRETA))
					this.rotaPanelBuilder = new DiretaPanelBuilder(this.panelRota);
				else if (value.equals(RotaFrame.FRACIONAL))
					this.rotaPanelBuilder = new FracionalPanelBuilder(this.panelRota);

				try {
					this.rotaPanelBuilder.buildPanel();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
			RotaFrame.previousTipo = value;
		} else if (e.getSource().equals(this.btnFeito)) {
			try {
				String nome = this.txtNome.getText();
				if (nome == null || nome.trim().equals(""))
					throw new CadastroException("Por favor insira um nome para a Rota.");
				else if (this.cbxTipo.getSelectedItem() == null)
					throw new CadastroException("Por favor insira um tipo para a Rota.");

				String tipoSelecionado = this.cbxTipo.getSelectedItem().toString();
				char tipo = '\0';
				if (tipoSelecionado.equals(RotaFrame.DIRETA))
					tipo = 'D';
				else if (tipoSelecionado.equals(RotaFrame.FRACIONAL))
					tipo = 'F';

				RotaPanelInfoVO rotaInfo = this.rotaPanelBuilder.getPanelData();
				this.cadastroRota.createRota(nome, tipo, rotaInfo.getIdOrigem(), rotaInfo.getIdDestino(),
						rotaInfo.getCapacidadeTotal(), rotaInfo.getCustoGrama(), rotaInfo.getTempoEntrega(),
						rotaInfo.getTrechos());
				JOptionPane.showMessageDialog(null, "Rota cadastrada com sucesso.", "Sucesso",
						JOptionPane.PLAIN_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource().equals(this.btnVoltar)) {
			ILogisticFrame next = new CadastroFrame();
			next.initialize();
			this.dispose();
		}
	}

	private void clearPanelRota() {
		this.panelRota.removeAll();
		this.panelRota.revalidate();
		this.panelRota.repaint();
		this.rotaPanelBuilder = null;
	}

	@Override
	public void initialize() {
		this.setTitle("Rota");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 295, 539);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.initializeElements();
		this.setVisible(true);
	}

	private void initializeButtons() {
		this.btnVoltar = new JButton("< Voltar");
		this.btnVoltar.addActionListener(this);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 3;
		this.panelFirst.add(this.btnVoltar, gbc_button);

		this.btnFeito = new JButton("Feito");
		this.btnFeito.addActionListener(this);
		GridBagConstraints gbc_btnFeito = new GridBagConstraints();
		gbc_btnFeito.insets = new Insets(0, 0, 0, 5);
		gbc_btnFeito.gridx = 16;
		gbc_btnFeito.gridy = 3;
		this.panelFirst.add(this.btnFeito, gbc_btnFeito);
	}

	private void initializeComboBoxes() {
		this.cbxTipo = new JComboBox<String>();
		this.setComboBoxContent(this.cbxTipo);
		this.cbxTipo.addActionListener(this);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 4;
		this.panelSecond.add(this.cbxTipo, gbc_comboBox);
	}

	private void initializeComponents() {
		Component verticalGlue = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue = new GridBagConstraints();
		gbc_verticalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue.gridx = 2;
		gbc_verticalGlue.gridy = 0;
		this.panelFirst.add(verticalGlue, gbc_verticalGlue);

		Component verticalGlue_1 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_1 = new GridBagConstraints();
		gbc_verticalGlue_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_1.gridx = 2;
		gbc_verticalGlue_1.gridy = 1;
		this.panelFirst.add(verticalGlue_1, gbc_verticalGlue_1);

		Component verticalGlue_2 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_2 = new GridBagConstraints();
		gbc_verticalGlue_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_2.gridx = 2;
		gbc_verticalGlue_2.gridy = 2;
		this.panelFirst.add(verticalGlue_2, gbc_verticalGlue_2);

		Component horizontalGlue_15 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_15 = new GridBagConstraints();
		gbc_horizontalGlue_15.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_15.gridx = 0;
		gbc_horizontalGlue_15.gridy = 3;
		this.panelFirst.add(horizontalGlue_15, gbc_horizontalGlue_15);

		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue.gridx = 1;
		gbc_horizontalGlue.gridy = 3;
		this.panelFirst.add(horizontalGlue, gbc_horizontalGlue);

		Component horizontalGlue_12 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_12 = new GridBagConstraints();
		gbc_horizontalGlue_12.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_12.gridx = 3;
		gbc_horizontalGlue_12.gridy = 3;
		this.panelFirst.add(horizontalGlue_12, gbc_horizontalGlue_12);

		Component horizontalGlue_9 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_9 = new GridBagConstraints();
		gbc_horizontalGlue_9.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_9.gridx = 4;
		gbc_horizontalGlue_9.gridy = 3;
		this.panelFirst.add(horizontalGlue_9, gbc_horizontalGlue_9);

		Component horizontalGlue_4 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_4 = new GridBagConstraints();
		gbc_horizontalGlue_4.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_4.gridx = 5;
		gbc_horizontalGlue_4.gridy = 3;
		this.panelFirst.add(horizontalGlue_4, gbc_horizontalGlue_4);

		Component horizontalGlue_13 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_13 = new GridBagConstraints();
		gbc_horizontalGlue_13.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_13.gridx = 6;
		gbc_horizontalGlue_13.gridy = 3;
		this.panelFirst.add(horizontalGlue_13, gbc_horizontalGlue_13);

		Component horizontalGlue_8 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_8 = new GridBagConstraints();
		gbc_horizontalGlue_8.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_8.gridx = 7;
		gbc_horizontalGlue_8.gridy = 3;
		this.panelFirst.add(horizontalGlue_8, gbc_horizontalGlue_8);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
		gbc_horizontalGlue_1.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_1.gridx = 8;
		gbc_horizontalGlue_1.gridy = 3;
		this.panelFirst.add(horizontalGlue_1, gbc_horizontalGlue_1);

		Component horizontalGlue_5 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_5 = new GridBagConstraints();
		gbc_horizontalGlue_5.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_5.gridx = 10;
		gbc_horizontalGlue_5.gridy = 3;
		this.panelFirst.add(horizontalGlue_5, gbc_horizontalGlue_5);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_2 = new GridBagConstraints();
		gbc_horizontalGlue_2.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_2.gridx = 11;
		gbc_horizontalGlue_2.gridy = 3;
		this.panelFirst.add(horizontalGlue_2, gbc_horizontalGlue_2);

		Component horizontalGlue_7 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_7 = new GridBagConstraints();
		gbc_horizontalGlue_7.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_7.gridx = 12;
		gbc_horizontalGlue_7.gridy = 3;
		this.panelFirst.add(horizontalGlue_7, gbc_horizontalGlue_7);

		Component horizontalGlue_10 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_10 = new GridBagConstraints();
		gbc_horizontalGlue_10.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_10.gridx = 13;
		gbc_horizontalGlue_10.gridy = 3;
		this.panelFirst.add(horizontalGlue_10, gbc_horizontalGlue_10);

		Component horizontalGlue_6 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_6 = new GridBagConstraints();
		gbc_horizontalGlue_6.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_6.gridx = 14;
		gbc_horizontalGlue_6.gridy = 3;
		this.panelFirst.add(horizontalGlue_6, gbc_horizontalGlue_6);

		Component horizontalGlue_11 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_11 = new GridBagConstraints();
		gbc_horizontalGlue_11.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_11.gridx = 15;
		gbc_horizontalGlue_11.gridy = 3;
		this.panelFirst.add(horizontalGlue_11, gbc_horizontalGlue_11);

		Component horizontalGlue_14 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_14 = new GridBagConstraints();
		gbc_horizontalGlue_14.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_14.gridx = 17;
		gbc_horizontalGlue_14.gridy = 3;
		this.panelFirst.add(horizontalGlue_14, gbc_horizontalGlue_14);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_3 = new GridBagConstraints();
		gbc_horizontalGlue_3.gridx = 18;
		gbc_horizontalGlue_3.gridy = 3;
		this.panelFirst.add(horizontalGlue_3, gbc_horizontalGlue_3);

		Component verticalGlue_3 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_3 = new GridBagConstraints();
		gbc_verticalGlue_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_3.gridx = 1;
		gbc_verticalGlue_3.gridy = 0;
		this.panelSecond.add(verticalGlue_3, gbc_verticalGlue_3);

		Component verticalGlue_4 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_4 = new GridBagConstraints();
		gbc_verticalGlue_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_4.gridx = 1;
		gbc_verticalGlue_4.gridy = 1;
		this.panelSecond.add(verticalGlue_4, gbc_verticalGlue_4);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 2;
		this.panelSecond.add(rigidArea, gbc_rigidArea);

		Component horizontalGlue_16 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_16 = new GridBagConstraints();
		gbc_horizontalGlue_16.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue_16.gridx = 2;
		gbc_horizontalGlue_16.gridy = 2;
		this.panelSecond.add(horizontalGlue_16, gbc_horizontalGlue_16);

		Component verticalGlue_5 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_5 = new GridBagConstraints();
		gbc_verticalGlue_5.insets = new Insets(0, 0, 5, 0);
		gbc_verticalGlue_5.gridx = 3;
		gbc_verticalGlue_5.gridy = 3;
		this.panelSecond.add(verticalGlue_5, gbc_verticalGlue_5);

	}

	private void initializeContentPane() {
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 64, 56, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);
	}

	private void initializeElements() {
		this.initializeContentPane();
		this.initializePanels();
		this.initializeComboBoxes();
		this.initializeButtons();
		this.initializeLabels();
		this.initializeTextFields();
		this.initializeComponents();
	}

	private void initializeLabels() {
		this.lblRota = new JLabel("Rota");
		GridBagConstraints gbc_lblRota = new GridBagConstraints();
		gbc_lblRota.insets = new Insets(0, 0, 0, 5);
		gbc_lblRota.gridx = 9;
		gbc_lblRota.gridy = 3;
		this.panelFirst.add(this.lblRota, gbc_lblRota);

		this.lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 2;
		this.panelSecond.add(this.lblNome, gbc_lblNome);

		this.lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.insets = new Insets(0, 0, 0, 5);
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.gridx = 1;
		gbc_lblTipo.gridy = 4;
		this.panelSecond.add(this.lblTipo, gbc_lblTipo);
	}

	private void initializePanels() {
		this.panelFirst = new JPanel();
		this.panelFirst.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		this.contentPane.add(this.panelFirst, gbc_panel);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.panelFirst.setLayout(gbl_panel);

		this.panelSecond = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		this.contentPane.add(this.panelSecond, gbc_panel_1);

		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.panelSecond.setLayout(gbl_panel_1);

		this.panelRota = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		contentPane.add(panelRota, gbc_panel_2);
	}

	private void initializeTextFields() {
		this.txtNome = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 2;
		this.panelSecond.add(this.txtNome, gbc_textField);
		this.txtNome.setColumns(10);
	}

	private void setComboBoxContent(JComboBox<String> comboBox) {
		comboBox.addItem(null);
		comboBox.addItem(RotaFrame.DIRETA);
		comboBox.addItem(RotaFrame.FRACIONAL);
	}

}
