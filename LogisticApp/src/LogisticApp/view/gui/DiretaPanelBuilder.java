package LogisticApp.view.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;
import java.awt.Component;
import javax.swing.Box;

import LogisticApp.business.session.CadastroRota;
import LogisticApp.business.session.interfaces.ICadastroRotaSession;
import LogisticApp.exception.CadastroException;
import LogisticApp.view.vo.LocalidadeVO;
import LogisticApp.view.vo.RotaPanelInfoVO;

public class DiretaPanelBuilder extends RotaPanelBuilder {

	private ICadastroRotaSession cadastroRota;
	private JComboBox<LocalidadeVO> cboxDestino;
	private JComboBox<LocalidadeVO> cboxOrigem;
	private JLabel lblCapacidadeTotal;
	private JLabel lblCustoPorGrama;
	private JLabel lblDestino;
	private JLabel lblOrigem;
	private JLabel lblTempoDeEntrega;
	private JFormattedTextField txtCapacidade;
	private JFormattedTextField txtCusto;
	private JFormattedTextField txtTempo;

	public DiretaPanelBuilder(JPanel panel) {
		super(panel);
		this.cadastroRota = new CadastroRota();
	}

	@Override
	public void buildPanel() throws Exception {
		this.panel.removeAll();
		this.panel.revalidate();
		this.panel.repaint();
		this.initializeLayout();
		this.initializeLabels();
		this.initializeComboBoxes();
		this.initializeTextFields();
		this.initializeComponents();
	}

	@Override
	public RotaPanelInfoVO getPanelData() throws Exception {
		LocalidadeVO origem = (LocalidadeVO) this.cboxOrigem.getSelectedItem();
		LocalidadeVO destino = (LocalidadeVO) this.cboxDestino.getSelectedItem();
		double capacidadeTotal = Double.parseDouble(this.txtCapacidade.getText());
		double custo = Double.parseDouble(this.txtCusto.getText());
		int totalTempo = this.txtTempo.getText() == null || this.txtTempo.getText().trim().isEmpty() ? 0
				: Integer.parseInt(this.txtTempo.getText());

		if (origem == null)
			throw new CadastroException("Por favor selecione a localidade origem.");
		else if (destino == null)
			throw new CadastroException("Por favor selecione a localidade destino.");
		else if(origem.equals(destino))
			throw new CadastroException("A Localidade origem deve ser diferente da Localidade destino.");
		else if (capacidadeTotal == 0.0)
			throw new CadastroException("A capacidade total deve ser maior que zero.");
		else if (custo == 0.0)
			throw new CadastroException("O custo por grama deve ser maior que zero.");
		else if (totalTempo == 0)
			throw new CadastroException("<html>A quantidade de dias para entrega n&atilde;o pode ser nula ou igual a zero.</html>");

		RotaPanelInfoVO rotaInfo = new RotaPanelInfoVO();
		rotaInfo.setIdOrigem(origem.getId());
		rotaInfo.setIdDestino(destino.getId());
		rotaInfo.setCapacidadeTotal(capacidadeTotal);
		rotaInfo.setCustoGrama(custo);
		rotaInfo.setTempoEntrega(totalTempo);
		return rotaInfo;
	}

	private void initializeComboBoxes() throws Exception {

		List<LocalidadeVO> localidades = this.cadastroRota.recuperarLocalidades();

		this.cboxOrigem = new JComboBox<LocalidadeVO>();
		this.startComboBoxValues(this.cboxOrigem, localidades);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 3;
		this.panel.add(this.cboxOrigem, gbc_comboBox);

		this.cboxDestino = new JComboBox<LocalidadeVO>();
		this.startComboBoxValues(this.cboxDestino, localidades);

		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 3;
		gbc_comboBox_1.gridy = 8;
		this.panel.add(this.cboxDestino, gbc_comboBox_1);
	}

	private void initializeComponents() {
		Component verticalGlue = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue = new GridBagConstraints();
		gbc_verticalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue.gridx = 3;
		gbc_verticalGlue.gridy = 0;
		this.panel.add(verticalGlue, gbc_verticalGlue);

		Component verticalGlue_6 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_6 = new GridBagConstraints();
		gbc_verticalGlue_6.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_6.gridx = 3;
		gbc_verticalGlue_6.gridy = 1;
		this.panel.add(verticalGlue_6, gbc_verticalGlue_6);

		Component verticalGlue_7 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_7 = new GridBagConstraints();
		gbc_verticalGlue_7.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_7.gridx = 3;
		gbc_verticalGlue_7.gridy = 2;
		this.panel.add(verticalGlue_7, gbc_verticalGlue_7);

		Component verticalGlue_8 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_8 = new GridBagConstraints();
		gbc_verticalGlue_8.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_8.gridx = 3;
		gbc_verticalGlue_8.gridy = 4;
		this.panel.add(verticalGlue_8, gbc_verticalGlue_8);

		Component verticalGlue_14 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_14 = new GridBagConstraints();
		gbc_verticalGlue_14.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_14.gridx = 3;
		gbc_verticalGlue_14.gridy = 5;
		this.panel.add(verticalGlue_14, gbc_verticalGlue_14);

		Component verticalGlue_13 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_13 = new GridBagConstraints();
		gbc_verticalGlue_13.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_13.gridx = 3;
		gbc_verticalGlue_13.gridy = 6;
		this.panel.add(verticalGlue_13, gbc_verticalGlue_13);

		Component verticalGlue_1 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_1 = new GridBagConstraints();
		gbc_verticalGlue_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_1.gridx = 3;
		gbc_verticalGlue_1.gridy = 7;
		this.panel.add(verticalGlue_1, gbc_verticalGlue_1);

		Component verticalGlue_9 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_9 = new GridBagConstraints();
		gbc_verticalGlue_9.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_9.gridx = 3;
		gbc_verticalGlue_9.gridy = 9;
		this.panel.add(verticalGlue_9, gbc_verticalGlue_9);

		Component verticalGlue_16 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_16 = new GridBagConstraints();
		gbc_verticalGlue_16.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_16.gridx = 3;
		gbc_verticalGlue_16.gridy = 10;
		this.panel.add(verticalGlue_16, gbc_verticalGlue_16);

		Component verticalGlue_15 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_15 = new GridBagConstraints();
		gbc_verticalGlue_15.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_15.gridx = 3;
		gbc_verticalGlue_15.gridy = 11;
		this.panel.add(verticalGlue_15, gbc_verticalGlue_15);

		Component verticalGlue_2 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_2 = new GridBagConstraints();
		gbc_verticalGlue_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_2.gridx = 3;
		gbc_verticalGlue_2.gridy = 12;
		this.panel.add(verticalGlue_2, gbc_verticalGlue_2);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_2 = new GridBagConstraints();
		gbc_horizontalGlue_2.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue_2.gridx = 0;
		gbc_horizontalGlue_2.gridy = 13;
		this.panel.add(horizontalGlue_2, gbc_horizontalGlue_2);

		Component verticalGlue_5 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_5 = new GridBagConstraints();
		gbc_verticalGlue_5.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_5.gridx = 1;
		gbc_verticalGlue_5.gridy = 13;
		this.panel.add(verticalGlue_5, gbc_verticalGlue_5);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
		gbc_horizontalGlue_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue_1.gridx = 4;
		gbc_horizontalGlue_1.gridy = 13;
		this.panel.add(horizontalGlue_1, gbc_horizontalGlue_1);

		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalGlue.gridx = 5;
		gbc_horizontalGlue.gridy = 13;
		this.panel.add(horizontalGlue, gbc_horizontalGlue);

		Component verticalGlue_17 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_17 = new GridBagConstraints();
		gbc_verticalGlue_17.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_17.gridx = 3;
		gbc_verticalGlue_17.gridy = 14;
		this.panel.add(verticalGlue_17, gbc_verticalGlue_17);

		Component verticalGlue_10 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_10 = new GridBagConstraints();
		gbc_verticalGlue_10.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_10.gridx = 3;
		gbc_verticalGlue_10.gridy = 15;
		this.panel.add(verticalGlue_10, gbc_verticalGlue_10);

		Component verticalGlue_3 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_3 = new GridBagConstraints();
		gbc_verticalGlue_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_3.gridx = 3;
		gbc_verticalGlue_3.gridy = 16;
		this.panel.add(verticalGlue_3, gbc_verticalGlue_3);

		Component verticalGlue_18 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_18 = new GridBagConstraints();
		gbc_verticalGlue_18.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_18.gridx = 3;
		gbc_verticalGlue_18.gridy = 17;
		this.panel.add(verticalGlue_18, gbc_verticalGlue_18);

		Component verticalGlue_19 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_19 = new GridBagConstraints();
		gbc_verticalGlue_19.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_19.gridx = 3;
		gbc_verticalGlue_19.gridy = 19;
		this.panel.add(verticalGlue_19, gbc_verticalGlue_19);

		Component verticalGlue_11 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_11 = new GridBagConstraints();
		gbc_verticalGlue_11.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_11.gridx = 3;
		gbc_verticalGlue_11.gridy = 20;
		this.panel.add(verticalGlue_11, gbc_verticalGlue_11);

		Component verticalGlue_4 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_4 = new GridBagConstraints();
		gbc_verticalGlue_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_4.gridx = 3;
		gbc_verticalGlue_4.gridy = 21;
		this.panel.add(verticalGlue_4, gbc_verticalGlue_4);

		Component verticalGlue_20 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_20 = new GridBagConstraints();
		gbc_verticalGlue_20.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_20.gridx = 3;
		gbc_verticalGlue_20.gridy = 22;
		this.panel.add(verticalGlue_20, gbc_verticalGlue_20);

		Component verticalGlue_12 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_12 = new GridBagConstraints();
		gbc_verticalGlue_12.insets = new Insets(0, 0, 0, 5);
		gbc_verticalGlue_12.gridx = 3;
		gbc_verticalGlue_12.gridy = 24;
		this.panel.add(verticalGlue_12, gbc_verticalGlue_12);
	}

	private void initializeLabels() {
		this.lblOrigem = new JLabel("Origem:");
		GridBagConstraints gbc_lblOrigem = new GridBagConstraints();
		gbc_lblOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrigem.anchor = GridBagConstraints.EAST;
		gbc_lblOrigem.gridx = 2;
		gbc_lblOrigem.gridy = 3;
		this.panel.add(this.lblOrigem, gbc_lblOrigem);

		this.lblDestino = new JLabel("Destino:");
		GridBagConstraints gbc_lblDestino = new GridBagConstraints();
		gbc_lblDestino.anchor = GridBagConstraints.EAST;
		gbc_lblDestino.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestino.gridx = 2;
		gbc_lblDestino.gridy = 8;
		this.panel.add(this.lblDestino, gbc_lblDestino);

		this.lblCapacidadeTotal = new JLabel("<html><p align=\"right\">Capacidade Total (KG):</p></html>");
		this.lblCapacidadeTotal.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblCapacidadeTotal = new GridBagConstraints();
		gbc_lblCapacidadeTotal.anchor = GridBagConstraints.EAST;
		gbc_lblCapacidadeTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCapacidadeTotal.gridx = 2;
		gbc_lblCapacidadeTotal.gridy = 13;
		this.panel.add(this.lblCapacidadeTotal, gbc_lblCapacidadeTotal);

		this.lblCustoPorGrama = new JLabel(
				"<html><p align=\"right\" style=\"word-wrap: break-word;\">Custo&nbsp;por Grama&nbsp;(R$):</p></html>");
		GridBagConstraints gbc_lblCustoPorGrama = new GridBagConstraints();
		gbc_lblCustoPorGrama.anchor = GridBagConstraints.EAST;
		gbc_lblCustoPorGrama.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustoPorGrama.gridx = 2;
		gbc_lblCustoPorGrama.gridy = 18;
		this.panel.add(this.lblCustoPorGrama, gbc_lblCustoPorGrama);

		this.lblTempoDeEntrega = new JLabel(
				"<html><p align=\"right\" style=\"word-wrap: break-word;\">Qtd.&nbsp;de&nbsp;dias&nbsp;p/ entrega:</p></html>");
		GridBagConstraints gbc_lblTempoDeEntrega = new GridBagConstraints();
		gbc_lblTempoDeEntrega.anchor = GridBagConstraints.EAST;
		gbc_lblTempoDeEntrega.insets = new Insets(0, 0, 5, 5);
		gbc_lblTempoDeEntrega.gridx = 2;
		gbc_lblTempoDeEntrega.gridy = 23;
		this.panel.add(this.lblTempoDeEntrega, gbc_lblTempoDeEntrega);
	}

	private void initializeLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 70, 130, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 36, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.panel.setLayout(gridBagLayout);
	}

	private void initializeTextFields() throws ParseException {

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');

		NumberFormat format = new DecimalFormat("0.00", symbols);
		format.setMaximumFractionDigits(2);

		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setMinimum(0.0);
		formatter.setMaximum(10000000.0);
		formatter.setAllowsInvalid(false);

		this.txtCapacidade = new JFormattedTextField(formatter);
		this.txtCapacidade.setValue(0.0);

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 13;
		this.panel.add(this.txtCapacidade, gbc_textField);
		this.txtCapacidade.setColumns(10);

		this.txtCusto = new JFormattedTextField(formatter);
		this.txtCusto.setValue(0.0);

		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 18;
		this.panel.add(this.txtCusto, gbc_textField_1);
		this.txtCusto.setColumns(10);

		this.txtTempo = new JFormattedTextField();
		this.txtTempo.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e){
				String numbers = "0123456789";
				if(!numbers.contains(e.getKeyChar() + ""))
					e.consume();
			}	
		});
		
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 23;
		this.panel.add(this.txtTempo, gbc_textField_2);
		this.txtTempo.setColumns(10);
	}

	private void startComboBoxValues(JComboBox<LocalidadeVO> comboBox, List<LocalidadeVO> itens) throws Exception {
		comboBox.addItem(null);
		for (LocalidadeVO item : itens)
			comboBox.addItem(item);
	}
}
