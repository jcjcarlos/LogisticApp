package LogisticApp.view.gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

import LogisticApp.business.session.CadastroRota;
import LogisticApp.business.session.interfaces.ICadastroRotaSession;
import LogisticApp.exception.CadastroException;
import LogisticApp.exception.LogisticException;
import LogisticApp.view.vo.RotaPanelInfoVO;
import LogisticApp.view.vo.RotaVO;

public class FracionalPanelBuilder extends RotaPanelBuilder implements ActionListener {

	private JButton btnBuscar;
	private ICadastroRotaSession cadastroRota;
	private JLabel lblBuscarTrecho;
	private JLabel lblTrechos;
	private JList<RotaVO> listTrechos;
	private JPanel panelFirst;
	private JPanel panelSecond;
	private JTextField txtTrecho;

	public FracionalPanelBuilder(JPanel panel) {
		super(panel);
		this.cadastroRota = new CadastroRota();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.btnBuscar)) {
			try {
				String trecho = this.txtTrecho.getText();
				if (trecho == null || trecho.trim().isEmpty())
					throw new LogisticException("Por favor, insira um nome de um trecho antes de pesquisar.");
				RotaVO rota = this.cadastroRota.recuperarRotaPorNome(trecho);
				this.updateListTrechos(rota);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void buildPanel() {
		this.panel.removeAll();
		this.panel.revalidate();
		this.panel.repaint();
		this.initializeLayout();
		this.initializePanels();
		this.initializeLabels();
		this.initializeTextField();
		this.initializeButtons();
		this.initializeList();
		this.initializeComponents();
	}

	@Override
	public RotaPanelInfoVO getPanelData() throws Exception {
		RotaPanelInfoVO rotaPanelVO = new RotaPanelInfoVO();
		List<Integer> trechos = new ArrayList<Integer>();
		ListModel<RotaVO> listModel = this.listTrechos.getModel();
		for (int i = 0; i < listModel.getSize(); i++) {
			RotaVO rota = listModel.getElementAt(i);
			trechos.add(rota.getId());
		}
		if(trechos.isEmpty())
			throw new CadastroException("Por favor selecione um ou mais trechos.");
		rotaPanelVO.setTrechos(trechos);
		return rotaPanelVO;
	}

	private void initializeButtons() {
		this.btnBuscar = new JButton("Buscar");
		this.btnBuscar.addActionListener(this);
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 5;
		gbc_btnBuscar.gridy = 0;
		this.panelSecond.add(btnBuscar, gbc_btnBuscar);
	}

	private void initializeComponents() {

		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue.gridx = 0;
		gbc_horizontalGlue.gridy = 0;
		this.panelSecond.add(horizontalGlue, gbc_horizontalGlue);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_2 = new GridBagConstraints();
		gbc_horizontalGlue_2.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue_2.gridx = 2;
		gbc_horizontalGlue_2.gridy = 0;
		this.panelSecond.add(horizontalGlue_2, gbc_horizontalGlue_2);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_3 = new GridBagConstraints();
		gbc_horizontalGlue_3.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalGlue_3.gridx = 4;
		gbc_horizontalGlue_3.gridy = 0;
		this.panelSecond.add(horizontalGlue_3, gbc_horizontalGlue_3);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue_1 = new GridBagConstraints();
		gbc_horizontalGlue_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalGlue_1.gridx = 6;
		gbc_horizontalGlue_1.gridy = 0;
		this.panelSecond.add(horizontalGlue_1, gbc_horizontalGlue_1);

		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.fill = GridBagConstraints.VERTICAL;
		gbc_verticalStrut.gridx = 0;
		gbc_verticalStrut.gridy = 2;
		this.panel.add(verticalStrut, gbc_verticalStrut);

	}

	private void initializeLabels() {
		this.lblTrechos = new JLabel("Trechos");
		GridBagConstraints gbc_lblTrechos = new GridBagConstraints();
		gbc_lblTrechos.fill = GridBagConstraints.VERTICAL;
		gbc_lblTrechos.gridx = 0;
		gbc_lblTrechos.gridy = 0;
		this.panelFirst.add(this.lblTrechos, gbc_lblTrechos);

		this.lblBuscarTrecho = new JLabel(
				"<html><p align=\"right\" style=\"word-wrap: break-word;\">Buscar&nbsp;trecho por nome:</p></html>");
		GridBagConstraints gbc_lblBuscarTrecho = new GridBagConstraints();
		gbc_lblBuscarTrecho.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscarTrecho.gridx = 1;
		gbc_lblBuscarTrecho.gridy = 0;
		this.panelSecond.add(this.lblBuscarTrecho, gbc_lblBuscarTrecho);
	}

	private void initializeLayout() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 33, 350, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		this.panel.setLayout(gridBagLayout);
	}

	private void initializeList() {

		this.listTrechos = new JList<RotaVO>();
		this.listTrechos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listTrechos.setLayoutOrientation(JList.VERTICAL);

		this.listTrechos.setModel(new DefaultListModel<RotaVO>());

		JScrollPane listScroller = new JScrollPane(this.listTrechos);

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 2;
		gbc_list.gridwidth = 5;
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 1;
		this.panelSecond.add(listScroller, gbc_list);
	}

	private void initializePanels() {
		this.panelFirst = new JPanel();
		this.panelFirst.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		this.panel.add(this.panelFirst, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		this.panelFirst.setLayout(gbl_panel);

		this.panelSecond = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		this.panel.add(this.panelSecond, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 41, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		this.panelSecond.setLayout(gbl_panel_1);
	}

	private void initializeTextField() {
		this.txtTrecho = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 0;
		this.panelSecond.add(this.txtTrecho, gbc_textField);
		this.txtTrecho.setColumns(10);
	}

	private void updateListTrechos(RotaVO rota) throws CadastroException {
		DefaultListModel<RotaVO> model = new DefaultListModel<RotaVO>();
		ListModel<RotaVO> currentModel = this.listTrechos.getModel();
		
		// as rotas precisam ser sequenciais
		// logo se a origem da nova rota não for igual ao destino da última, lançar exceção
		if(currentModel.getSize() > 0){
			RotaVO last = currentModel.getElementAt(currentModel.getSize() - 1);
			if(!last.getDescricaoDestino().equals(rota.getDescricaoOrigem()))
				throw new CadastroException("Trecho inválido. A próxima rota precisa possuir " + last.getDescricaoDestino() + " como origem.");
		}
		
		for (int i = 0; i < currentModel.getSize(); i++) {
			RotaVO trecho = currentModel.getElementAt(i);
			model.addElement(trecho);
		}
		model.addElement(rota);
		this.listTrechos.setModel(model);
	}

}
