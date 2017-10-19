package LogisticApp.view.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import LogisticApp.business.session.ContratacaoTransporte;
import LogisticApp.business.session.interfaces.IContratacaoTransporteSession;
import LogisticApp.exception.LogisticException;
import LogisticApp.view.gui.interfaces.ILogisticFrame;
import LogisticApp.view.vo.LocalidadeVO;
import LogisticApp.view.vo.RotaCapacitadaVO;

public class ContratacaoTransporteFrame extends JFrame implements ILogisticFrame, ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnContratar;
	private JButton btnVoltar;
	private JPanel contentPane;
	private JLabel lblContratacaoTransporte;
	private JLabel lblDestino;
	private JLabel lblLocalidadeDestino;
	private JLabel lblLocalidadeOrigem;
	private JLabel lblOrigem;
	private JLabel lblPeso;
	private JLabel lblRotas;
	private JLabel lblValorPeso;
	private JList<RotaCapacitadaVO> listRotas;
	private JPanel panelFirst;
	private JPanel panelFourth;
	private JPanel panelSecond;
	private JPanel panelThird;

	private IContratacaoTransporteSession contratacaoTransporte;
	private LocalidadeVO localidadeDestino;
	private LocalidadeVO localidadeOrigem;
	private double pesoVolume;
	private List<RotaCapacitadaVO> rotas;

	public ContratacaoTransporteFrame(LocalidadeVO localidadeOrigem, LocalidadeVO localidadeDestino, double pesoVolume,
			Collection<RotaCapacitadaVO> rotas) {
		this.localidadeOrigem = localidadeOrigem;
		this.localidadeDestino = localidadeDestino;
		this.pesoVolume = pesoVolume;
		this.rotas = new ArrayList<RotaCapacitadaVO>();
		this.rotas.addAll(rotas);
		this.contratacaoTransporte = new ContratacaoTransporte();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.btnVoltar)) {
			ILogisticFrame next = new MenuContratacaoTransporteFrame();
			next.initialize();
			this.dispose();
		} else if (e.getSource().equals(this.btnContratar)) {
			try {
				RotaCapacitadaVO rota = this.listRotas.getSelectedValue();

				if (rota == null)
					throw new LogisticException("Por favor selecione uma rota para contratação.");

				this.contratacaoTransporte.atualizarRota(rota.getId(), this.pesoVolume);
				JOptionPane.showMessageDialog(null,
						"Rota " + rota.getNome() + " contratada para o serviço de transporte.", "Sucesso",
						JOptionPane.PLAIN_MESSAGE);

			} catch (LogisticException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private DefaultListModel<RotaCapacitadaVO> createListModel(List<RotaCapacitadaVO> elements) {
		DefaultListModel<RotaCapacitadaVO> model = new DefaultListModel<RotaCapacitadaVO>();
		for (RotaCapacitadaVO element : elements)
			model.addElement(element);
		return model;
	}

	@Override
	public void initialize() {
		this.setTitle("Contratação de Transporte");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 331, 423);
		this.setLocationRelativeTo(null);
		this.initializeElements();
		this.initializeData();
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

		this.btnContratar = new JButton("Contratar");
		this.btnContratar.addActionListener(this);

		GridBagConstraints gbc_btnContratar = new GridBagConstraints();
		gbc_btnContratar.insets = new Insets(0, 0, 5, 0);
		gbc_btnContratar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnContratar.gridx = 0;
		gbc_btnContratar.gridy = 4;
		this.contentPane.add(this.btnContratar, gbc_btnContratar);
	}

	private void initializeComponents() {

		Component verticalGlue_3 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_3 = new GridBagConstraints();
		gbc_verticalGlue_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_3.gridx = 4;
		gbc_verticalGlue_3.gridy = 0;
		this.panelFirst.add(verticalGlue_3, gbc_verticalGlue_3);

		Component verticalGlue_5 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_5 = new GridBagConstraints();
		gbc_verticalGlue_5.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_5.gridx = 2;
		gbc_verticalGlue_5.gridy = 1;
		this.panelFirst.add(verticalGlue_5, gbc_verticalGlue_5);

		Component verticalGlue_4 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_4 = new GridBagConstraints();
		gbc_verticalGlue_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_4.gridx = 3;
		gbc_verticalGlue_4.gridy = 2;
		this.panelFirst.add(verticalGlue_4, gbc_verticalGlue_4);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
		gbc_horizontalGlue_1.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_1.gridx = 0;
		gbc_horizontalGlue_1.gridy = 3;
		this.panelFirst.add(horizontalGlue_1, gbc_horizontalGlue_1);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_3 = new GridBagConstraints();
		gbc_horizontalGlue_3.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_3.gridx = 1;
		gbc_horizontalGlue_3.gridy = 3;
		this.panelFirst.add(horizontalGlue_3, gbc_horizontalGlue_3);

		Component horizontalGlue_4 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_4 = new GridBagConstraints();
		gbc_horizontalGlue_4.insets = new Insets(0, 0, 0, 5);
		gbc_horizontalGlue_4.gridx = 6;
		gbc_horizontalGlue_4.gridy = 3;
		this.panelFirst.add(horizontalGlue_4, gbc_horizontalGlue_4);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_2 = new GridBagConstraints();
		gbc_horizontalGlue_2.gridx = 7;
		gbc_horizontalGlue_2.gridy = 3;
		this.panelFirst.add(horizontalGlue_2, gbc_horizontalGlue_2);

		Component verticalGlue_6 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_6 = new GridBagConstraints();
		gbc_verticalGlue_6.insets = new Insets(0, 0, 5, 0);
		gbc_verticalGlue_6.gridx = 2;
		gbc_verticalGlue_6.gridy = 0;
		this.panelSecond.add(verticalGlue_6, gbc_verticalGlue_6);

		Component verticalGlue_2 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_2 = new GridBagConstraints();
		gbc_verticalGlue_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_2.gridx = 1;
		gbc_verticalGlue_2.gridy = 1;
		this.panelSecond.add(verticalGlue_2, gbc_verticalGlue_2);

		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue.gridx = 0;
		gbc_horizontalGlue.gridy = 2;
		this.panelSecond.add(horizontalGlue, gbc_horizontalGlue);

		Component verticalGlue = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue = new GridBagConstraints();
		gbc_verticalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue.gridx = 1;
		gbc_verticalGlue.gridy = 3;
		this.panelSecond.add(verticalGlue, gbc_verticalGlue);

		Component verticalGlue_1 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_1 = new GridBagConstraints();
		gbc_verticalGlue_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalGlue_1.gridx = 1;
		gbc_verticalGlue_1.gridy = 5;
		this.panelSecond.add(verticalGlue_1, gbc_verticalGlue_1);

		Component verticalGlue_7 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_7 = new GridBagConstraints();
		gbc_verticalGlue_7.insets = new Insets(0, 0, 5, 0);
		gbc_verticalGlue_7.gridx = 1;
		gbc_verticalGlue_7.gridy = 0;
		this.panelThird.add(verticalGlue_7, gbc_verticalGlue_7);

		Component horizontalGlue_5 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_5 = new GridBagConstraints();
		gbc_horizontalGlue_5.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue_5.gridx = 0;
		gbc_horizontalGlue_5.gridy = 1;
		this.panelThird.add(horizontalGlue_5, gbc_horizontalGlue_5);

		Component verticalGlue_8 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_8 = new GridBagConstraints();
		gbc_verticalGlue_8.insets = new Insets(0, 0, 5, 0);
		gbc_verticalGlue_8.gridx = 0;
		gbc_verticalGlue_8.gridy = 3;
		this.contentPane.add(verticalGlue_8, gbc_verticalGlue_8);

		Component verticalGlue_9 = Box.createVerticalGlue();
		GridBagConstraints gbc_verticalGlue_9 = new GridBagConstraints();
		gbc_verticalGlue_9.gridx = 0;
		gbc_verticalGlue_9.gridy = 5;
		this.contentPane.add(verticalGlue_9, gbc_verticalGlue_9);
	}

	private void initializeContentPane() {
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 66, 89, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };

		this.contentPane.setLayout(gbl_contentPane);
	}

	private void initializeData() {
		this.lblLocalidadeOrigem.setText(this.localidadeOrigem.getDescricao());
		this.lblLocalidadeDestino.setText(this.localidadeDestino.getDescricao());
		this.lblValorPeso.setText(String.format("%.2f", this.pesoVolume) + " KG");
	}

	private void initializeElements() {
		this.initializeContentPane();
		this.initializePanels();
		this.initializeButtons();
		this.initializeLabels();
		this.initializeList();
		this.initializeComponents();
	}

	private void initializeLabels() {
		this.lblContratacaoTransporte = new JLabel("Contratação de Transporte");
		GridBagConstraints gbc_lblContrataoDeTransporte = new GridBagConstraints();
		gbc_lblContrataoDeTransporte.insets = new Insets(0, 0, 0, 5);
		gbc_lblContrataoDeTransporte.gridx = 5;
		gbc_lblContrataoDeTransporte.gridy = 3;
		this.panelFirst.add(this.lblContratacaoTransporte, gbc_lblContrataoDeTransporte);

		this.lblOrigem = new JLabel("Origem:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		this.panelSecond.add(this.lblOrigem, gbc_lblNewLabel);

		this.lblLocalidadeOrigem = new JLabel("Label_Origem");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		this.panelSecond.add(this.lblLocalidadeOrigem, gbc_lblNewLabel_1);

		this.lblDestino = new JLabel("Destino:");
		GridBagConstraints gbc_lblDestino = new GridBagConstraints();
		gbc_lblDestino.insets = new Insets(0, 0, 5, 5);
		gbc_lblDestino.anchor = GridBagConstraints.WEST;
		gbc_lblDestino.gridx = 1;
		gbc_lblDestino.gridy = 4;
		this.panelSecond.add(this.lblDestino, gbc_lblDestino);

		this.lblLocalidadeDestino = new JLabel("Label_Destino");
		GridBagConstraints gbc_lblLabeldestino = new GridBagConstraints();
		gbc_lblLabeldestino.anchor = GridBagConstraints.WEST;
		gbc_lblLabeldestino.insets = new Insets(0, 0, 5, 0);
		gbc_lblLabeldestino.gridx = 2;
		gbc_lblLabeldestino.gridy = 4;
		this.panelSecond.add(this.lblLocalidadeDestino, gbc_lblLabeldestino);

		this.lblPeso = new JLabel("Peso:");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.insets = new Insets(0, 0, 0, 5);
		gbc_lblPeso.anchor = GridBagConstraints.WEST;
		gbc_lblPeso.gridx = 1;
		gbc_lblPeso.gridy = 6;
		this.panelSecond.add(this.lblPeso, gbc_lblPeso);

		this.lblValorPeso = new JLabel("Label_Peso");
		GridBagConstraints gbc_lblLabelpeso = new GridBagConstraints();
		gbc_lblLabelpeso.anchor = GridBagConstraints.WEST;
		gbc_lblLabelpeso.gridx = 2;
		gbc_lblLabelpeso.gridy = 6;
		this.panelSecond.add(this.lblValorPeso, gbc_lblLabelpeso);

		this.lblRotas = new JLabel("Rotas:");
		GridBagConstraints gbc_lblRotas = new GridBagConstraints();
		gbc_lblRotas.gridx = 0;
		gbc_lblRotas.gridy = 0;
		this.panelFourth.add(this.lblRotas, gbc_lblRotas);
	}

	private void initializeList() {
		this.listRotas = new JList<RotaCapacitadaVO>();
		this.listRotas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listRotas.setLayoutOrientation(JList.VERTICAL);

		this.listRotas.setModel(this.createListModel(this.rotas));

		JScrollPane listScroller = new JScrollPane(this.listRotas);

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 3;
		this.panelThird.add(listScroller, gbc_list);
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
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };

		this.panelFirst.setLayout(gbl_panel);

		this.panelSecond = new JPanel();
		this.panelSecond.setBackground(Color.LIGHT_GRAY);
		this.panelSecond.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;

		this.contentPane.add(this.panelSecond, gbc_panel_1);

		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };

		this.panelSecond.setLayout(gbl_panel_1);

		this.panelThird = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		this.contentPane.add(this.panelThird, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 20, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		this.panelThird.setLayout(gbl_panel_2);

		this.panelFourth = new JPanel();
		this.panelFourth.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 2;
		this.panelThird.add(this.panelFourth, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 0, 0 };
		gbl_panel_3.rowHeights = new int[] { 0, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		this.panelFourth.setLayout(gbl_panel_3);
	}

}
