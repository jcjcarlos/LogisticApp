package LogisticApp.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import LogisticApp.view.gui.interfaces.ILogisticFrame;

public class RotaFrame implements ILogisticFrame, ActionListener {
	
	private static final String DIRETA = "Direta";
	private static final String FRACIONAL = "Fracional";
	private static String previousTipo = null;
	
	private JComboBox<String> cbxTipo;
	private LogisticPanel rotaPanel;

	@Override
	public void initialize() {
		this.initializeComboBoxes();
	}
	
	private void initializeComboBoxes(){
		this.cbxTipo = new JComboBox<String>();
		this.setComboBoxContent(this.cbxTipo);
		this.cbxTipo.addActionListener(this);
	}
	
	private void setComboBoxContent(JComboBox<String> comboBox){
		comboBox.addItem(null);
		comboBox.addItem(RotaFrame.DIRETA);
		comboBox.addItem(RotaFrame.FRACIONAL);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(this.cbxTipo)){
			String value = (String)this.cbxTipo.getSelectedItem();
			if(value == null)
				this.rotaPanel.setVisible(false);
			else if(!value.equals(RotaFrame.previousTipo)){
				if(value.equals(RotaFrame.DIRETA)){
					//this.rotaPanel = new DiretaPanel();
				}
				else if(value.equals(RotaFrame.FRACIONAL)){
					//this.rotaPanel = new FracionalPanel();
				}
				this.rotaPanel.buildPanel();
			}
			RotaFrame.previousTipo = value;
		}
	}

}
