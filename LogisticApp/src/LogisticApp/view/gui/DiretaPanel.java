package LogisticApp.view.gui;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import LogisticApp.business.session.interfaces.ICadastroRotaSession;
import LogisticApp.view.vo.LocalidadeVO;
import LogisticApp.view.vo.PanelInfoVO;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class DiretaPanel extends LogisticPanel {
	
	private static final long serialVersionUID = 1L;
	private ICadastroRotaSession cadastroRota;
	private JComboBox<LocalidadeVO> cboxOrigem;
	private JComboBox<LocalidadeVO> cboxDestino;
	private JTextField textCapacidade;
	private JTextField textCustoPorGrama;
	private JTextField textTempoEntrega;

	@Override
	public void buildPanel() throws Exception {
		this.initializeLayout();
		this.initializeLabels();
		this.initializeComboBoxes();
		this.initializeTextField();
		this.setVisible(true);
	}

	@Override
	public PanelInfoVO getPanelData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void initializeComboBoxes() throws Exception{
		
		this.cboxOrigem = new JComboBox<LocalidadeVO>();
		this.startComboBoxValues(this.cboxOrigem, this.cadastroRota.recuperarLocalidades());
		GridBagConstraints gbc_cboxOrigem = new GridBagConstraints();
		gbc_cboxOrigem.insets = new Insets(0, 0, 5, 0);
		gbc_cboxOrigem.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxOrigem.gridx = 1;
		gbc_cboxOrigem.gridy = 0;
		this.add(cboxOrigem, gbc_cboxOrigem);
		
		this.cboxDestino = new JComboBox<LocalidadeVO>();
		this.startComboBoxValues(this.cboxDestino, this.cadastroRota.recuperarLocalidades());
		GridBagConstraints gbc_cboxDestino  = new GridBagConstraints();
		gbc_cboxDestino.insets = new Insets(0, 0, 5, 0);
		gbc_cboxDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxDestino.gridx = 1;
		gbc_cboxDestino.gridy = 1;
		this.add(cboxDestino, gbc_cboxDestino);
		
	}

	private void initializeLabels(){
		
		JLabel lblOrigem = new JLabel("Origem:");
		GridBagConstraints gbc_lblOrigem = new GridBagConstraints();
		gbc_lblOrigem.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrigem.anchor = GridBagConstraints.EAST;
		gbc_lblOrigem.gridx = 0;
		gbc_lblOrigem.gridy = 0;
		this.add(lblOrigem, gbc_lblOrigem);
		
		JLabel lblDest = new JLabel("Dest.:");
		GridBagConstraints gbc_lblDest = new GridBagConstraints();
		gbc_lblDest.anchor = GridBagConstraints.EAST;
		gbc_lblDest.insets = new Insets(0, 0, 5, 5);
		gbc_lblDest.gridx = 0;
		gbc_lblDest.gridy = 1;
		this.add(lblDest, gbc_lblDest);
		
		JLabel lblCapac = new JLabel("Capacidade total:");
		GridBagConstraints gbc_lblCapac = new GridBagConstraints();
		gbc_lblCapac.anchor = GridBagConstraints.EAST;
		gbc_lblCapac.insets = new Insets(0, 0, 5, 5);
		gbc_lblCapac.gridx = 0;
		gbc_lblCapac.gridy = 2;
		this.add(lblCapac, gbc_lblCapac);
		
		JLabel lblCustoGrama = new JLabel("Custo por grama:");
		GridBagConstraints gbc_lblCustoGrama = new GridBagConstraints();
		gbc_lblCustoGrama.anchor = GridBagConstraints.EAST;
		gbc_lblCustoGrama.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustoGrama.gridx = 0;
		gbc_lblCustoGrama.gridy = 3;
		this.add(lblCustoGrama, gbc_lblCustoGrama);
		
		JLabel lblTempo = new JLabel("Tempo:");
		GridBagConstraints gbc_lblTempo = new GridBagConstraints();
		gbc_lblTempo.anchor = GridBagConstraints.EAST;
		gbc_lblTempo.insets = new Insets(0, 0, 0, 5);
		gbc_lblTempo.gridx = 0;
		gbc_lblTempo.gridy = 4;
		this.add(lblTempo, gbc_lblTempo);
		
	}

	private void initializeLayout(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gridBagLayout);
	}
	
	private void initializeTextField(){
		
		this.textCapacidade = new JTextField();
		GridBagConstraints gbc_textCapacidade = new GridBagConstraints();
		gbc_textCapacidade.insets = new Insets(0, 0, 5, 0);
		gbc_textCapacidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCapacidade.gridx = 1;
		gbc_textCapacidade.gridy = 2;
		this.add(textCapacidade, gbc_textCapacidade);
		this.textCapacidade.setColumns(10);
		
		this.textCustoPorGrama = new JTextField();
		GridBagConstraints gbc_textCustoPorGrama = new GridBagConstraints();
		gbc_textCustoPorGrama.insets = new Insets(0, 0, 5, 0);
		gbc_textCustoPorGrama.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCustoPorGrama.gridx = 1;
		gbc_textCustoPorGrama.gridy = 3;
		this.add(textCustoPorGrama, gbc_textCustoPorGrama);
		this.textCustoPorGrama.setColumns(10);
		
		
		
		this.textTempoEntrega = new JTextField();
		GridBagConstraints gbc_textTempoEntrega = new GridBagConstraints();
		gbc_textTempoEntrega.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTempoEntrega.gridx = 1;
		gbc_textTempoEntrega.gridy = 4;
		this.add(textTempoEntrega, gbc_textTempoEntrega);
		this.textTempoEntrega.setColumns(10);
	}
	
	
	private void startComboBoxValues(JComboBox<LocalidadeVO> comboBox, List<LocalidadeVO> itens) throws Exception {
		comboBox.addItem(null);
		for (LocalidadeVO item : itens)
			comboBox.addItem(item);
	}

}
