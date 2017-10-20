package LogisticApp.view.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import LogisticApp.business.session.ContratacaoTransporte;
import LogisticApp.business.session.interfaces.IContratacaoTransporteSession;
import LogisticApp.exception.LogisticException;
import LogisticApp.view.gui.interfaces.ILogisticFrame;
import LogisticApp.view.vo.LocalidadeVO;
import LogisticApp.view.vo.RotaCapacitadaVO;

public class MenuContratacaoTransporteFrame extends JFrame implements ActionListener, ILogisticFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnBuscar;
	private JButton btnVoltar;
	private JComboBox<LocalidadeVO> cboxDestino;
	private JComboBox<LocalidadeVO> cboxOrigem;
	private JPanel contentPane;
	private IContratacaoTransporteSession contratacaoTransporte;
	private JLabel lblContrataoDeTransporte;
	private JLabel lblEscolhaALocalidadeDestino;
	private JLabel lblEscolhaALocalidadeOrigem;
	private JLabel lblPeso;
	private JLabel lblSelecione;
	private JLabel lblSelecione_1;
	private JPanel panelFirst;
	private JPanel panelSecond;
	private JFormattedTextField txtPeso;

	@Override
	public void actionPerformed(ActionEvent e) {
		ILogisticFrame next = null;
		if (e.getSource().equals(this.btnVoltar))
			next = new MenuFrame();
		else if (e.getSource().equals(this.btnBuscar)) {
			try {
				LocalidadeVO localidadeOrigem = (LocalidadeVO) this.cboxOrigem.getSelectedItem();
				LocalidadeVO localidadeDestino = (LocalidadeVO) this.cboxDestino.getSelectedItem();
				double pesoVolume = Double.parseDouble(this.txtPeso.getText());
				
				this.validadeInformation(localidadeOrigem, localidadeDestino, pesoVolume);
				
				Collection<RotaCapacitadaVO> rotas = this.contratacaoTransporte
						.getInfoRotasCapacitadas(localidadeOrigem.getId(), localidadeDestino.getId(), pesoVolume);
				
				next = new ContratacaoTransporteFrame(localidadeOrigem,
													  localidadeDestino, 
													  pesoVolume,
													  rotas);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (next != null) {
			next.initialize();
			this.dispose();
		}
	}

	private void validadeInformation(LocalidadeVO localidadeOrigem, LocalidadeVO localidadeDestino, double pesoVolume) throws Exception {
		if (localidadeOrigem == null)
			throw new LogisticException("Por favor insira uma Localidade origem.");
		else if (localidadeDestino == null)
			throw new LogisticException("Por favor insira uma Localidade destino.");
		else if (pesoVolume <= 0.0)
			throw new LogisticException("Não é permitido um peso igual ou abaixo de zero.");
	}
	
	public MenuContratacaoTransporteFrame(){
		this.contratacaoTransporte = new ContratacaoTransporte();
	}

	public void initialize() {
		try {
			this.setTitle("Contratação de Transporte");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setBounds(100, 100, 327, 382);
			this.setLocationRelativeTo(null);
			this.initializeElements();
			this.setResizable(false);
			this.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao inicializar a interface.", "Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	private void initializeButtons() {

		this.btnVoltar = new JButton("< Voltar");
		this.btnVoltar.addActionListener(this);

		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 1;

		this.panelFirst.add(this.btnVoltar, gbc_button);

		this.btnBuscar = new JButton("Buscar");
		this.btnBuscar.addActionListener(this);

		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.fill = GridBagConstraints.VERTICAL;
		gbc_btnBuscar.gridwidth = 16;
		gbc_btnBuscar.gridx = 0;
		gbc_btnBuscar.gridy = 12;

		panelSecond.add(this.btnBuscar, gbc_btnBuscar);

	}

	private void initializeComboBoxes() throws Exception {
		List<LocalidadeVO> localidades = this.contratacaoTransporte.recuperarLocalidades();

		this.cboxOrigem = new JComboBox<LocalidadeVO>();

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 7;
		gbc_comboBox.gridy = 3;
		this.panelSecond.add(this.cboxOrigem, gbc_comboBox);

		this.startComboBoxValues(this.cboxOrigem, localidades);

		this.cboxDestino = new JComboBox<LocalidadeVO>();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.gridwidth = 2;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 7;
		gbc_comboBox_1.gridy = 7;
		this.panelSecond.add(this.cboxDestino, gbc_comboBox_1);

		this.startComboBoxValues(this.cboxDestino, localidades);
	}

	private void initializeComponents() {

		Component verticalGlue = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue = new GridBagConstraints();
		gbc_verticalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue.gridx = 7;
		gbc_verticalGlue.gridy = 0;
		this.panelSecond.add(verticalGlue, gbc_verticalGlue);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_1.gridx = 1;
		gbc_rigidArea_1.gridy = 0;
		this.panelFirst.add(rigidArea_1, gbc_rigidArea_1);

		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue.gridx = 0;
		gbc_horizontalGlue.gridy = 1;
		this.panelFirst.add(horizontalGlue, gbc_horizontalGlue);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_3 = new GridBagConstraints();
		gbc_horizontalGlue_3.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_3.gridx = 2;
		gbc_horizontalGlue_3.gridy = 1;
		this.panelFirst.add(horizontalGlue_3, gbc_horizontalGlue_3);

		Component verticalGlue_1 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_1 = new GridBagConstraints();
		gbc_verticalGlue_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_1.gridx = 7;
		gbc_verticalGlue_1.gridy = 2;
		this.panelSecond.add(verticalGlue_1, gbc_verticalGlue_1);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
		gbc_horizontalGlue_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue_1.gridx = 1;
		gbc_horizontalGlue_1.gridy = 3;
		this.panelSecond.add(horizontalGlue_1, gbc_horizontalGlue_1);

		Component horizontalGlue_4 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_4 = new GridBagConstraints();
		gbc_horizontalGlue_4.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue_4.gridx = 5;
		gbc_horizontalGlue_4.gridy = 3;
		this.panelSecond.add(horizontalGlue_4, gbc_horizontalGlue_4);

		Component horizontalGlue_5 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_5 = new GridBagConstraints();
		gbc_horizontalGlue_5.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalGlue_5.gridx = 11;
		gbc_horizontalGlue_5.gridy = 3;
		this.panelSecond.add(horizontalGlue_5, gbc_horizontalGlue_5);

		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_3 = new GridBagConstraints();
		gbc_rigidArea_3.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_3.gridx = 7;
		gbc_rigidArea_3.gridy = 4;
		this.panelSecond.add(rigidArea_3, gbc_rigidArea_3);

		Component verticalGlue_2 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_2 = new GridBagConstraints();
		gbc_verticalGlue_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_2.gridx = 7;
		gbc_verticalGlue_2.gridy = 6;
		this.panelSecond.add(verticalGlue_2, gbc_verticalGlue_2);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_2 = new GridBagConstraints();
		gbc_horizontalGlue_2.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue_2.gridx = 0;
		gbc_horizontalGlue_2.gridy = 7;
		this.panelSecond.add(horizontalGlue_2, gbc_horizontalGlue_2);

		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_5 = new GridBagConstraints();
		gbc_rigidArea_5.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea_5.gridx = 7;
		gbc_rigidArea_5.gridy = 8;
		this.panelSecond.add(rigidArea_5, gbc_rigidArea_5);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 7;
		gbc_rigidArea.gridy = 11;
		this.panelSecond.add(rigidArea, gbc_rigidArea);

		Component verticalGlue_3 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_3 = new GridBagConstraints();
		gbc_verticalGlue_3.gridx = 0;
		gbc_verticalGlue_3.gridy = 2;
		this.contentPane.add(verticalGlue_3, gbc_verticalGlue_3);
	}

	private void initializeContentPane() {
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 78, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };

		this.contentPane.setLayout(gbl_contentPane);
	}

	private void initializeElements() throws Exception {
		this.initializeContentPane();
		this.initializePanels();
		this.initializeLabels();
		this.initializeButtons();
		this.initializeComboBoxes();
		this.initializeTextFields();
		this.initializeComponents();
	}

	private void initializeLabels() {
		this.lblContrataoDeTransporte = new JLabel("Contratação de Transporte");
		GridBagConstraints gbc_lblContrataoDeTransporte = new GridBagConstraints();
		gbc_lblContrataoDeTransporte.gridx = 3;
		gbc_lblContrataoDeTransporte.gridy = 1;
		this.panelFirst.add(this.lblContrataoDeTransporte, gbc_lblContrataoDeTransporte);

		this.lblEscolhaALocalidadeOrigem = new JLabel("Escolha a localidade origem:");
		GridBagConstraints gbc_lblEscolhaALocalidade = new GridBagConstraints();
		gbc_lblEscolhaALocalidade.gridwidth = 10;
		gbc_lblEscolhaALocalidade.insets = new Insets(0, 0, 5, 0);
		gbc_lblEscolhaALocalidade.gridx = 2;
		gbc_lblEscolhaALocalidade.gridy = 1;
		this.panelSecond.add(this.lblEscolhaALocalidadeOrigem, gbc_lblEscolhaALocalidade);

		this.lblSelecione = new JLabel("Selecione:");
		GridBagConstraints gbc_lblSelecione = new GridBagConstraints();
		gbc_lblSelecione.anchor = GridBagConstraints.EAST;
		gbc_lblSelecione.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecione.gridx = 2;
		gbc_lblSelecione.gridy = 3;
		this.panelSecond.add(this.lblSelecione, gbc_lblSelecione);

		this.lblEscolhaALocalidadeDestino = new JLabel("Escolha a localidade destino:");
		GridBagConstraints gbc_lblEscolhaALocalidade_1 = new GridBagConstraints();
		gbc_lblEscolhaALocalidade_1.gridwidth = 10;
		gbc_lblEscolhaALocalidade_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblEscolhaALocalidade_1.gridx = 2;
		gbc_lblEscolhaALocalidade_1.gridy = 5;
		this.panelSecond.add(this.lblEscolhaALocalidadeDestino, gbc_lblEscolhaALocalidade_1);

		this.lblSelecione_1 = new JLabel("Selecione:");
		GridBagConstraints gbc_lblSelecione_1 = new GridBagConstraints();
		gbc_lblSelecione_1.anchor = GridBagConstraints.EAST;
		gbc_lblSelecione_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelecione_1.gridx = 2;
		gbc_lblSelecione_1.gridy = 7;
		this.panelSecond.add(this.lblSelecione_1, gbc_lblSelecione_1);

		this.lblPeso = new JLabel("Peso (KG):");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.gridwidth = 10;
		gbc_lblPeso.insets = new Insets(0, 0, 5, 0);
		gbc_lblPeso.gridx = 2;
		gbc_lblPeso.gridy = 9;
		this.panelSecond.add(this.lblPeso, gbc_lblPeso);
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
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };

		this.panelFirst.setLayout(gbl_panel);

		this.panelSecond = new JPanel();

		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;

		contentPane.add(this.panelSecond, gbc_panel_1);

		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };

		this.panelSecond.setLayout(gbl_panel_1);

	}

	private void initializeTextFields() {

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');

		NumberFormat format = new DecimalFormat("0.00", symbols);
		format.setMaximumFractionDigits(2);

		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setMinimum(0.0);
		formatter.setMaximum(10000000.0);
		formatter.setAllowsInvalid(false);

		this.txtPeso = new JFormattedTextField(formatter);
		this.txtPeso.setValue(0.0);

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 10;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 10;
		this.panelSecond.add(this.txtPeso, gbc_textField);
		this.txtPeso.setColumns(10);

	}

	private void startComboBoxValues(JComboBox<LocalidadeVO> comboBox, List<LocalidadeVO> itens) {
		comboBox.addItem(null);
		for (LocalidadeVO item : itens)
			comboBox.addItem(item);
	}

}
