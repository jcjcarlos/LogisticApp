package LogisticApp.view.gui;

import javax.swing.JPanel;

import LogisticApp.view.vo.RotaPanelInfoVO;

public abstract class RotaPanelBuilder {
	
	protected JPanel panel;
	
	public RotaPanelBuilder(JPanel panel){
		this.panel = panel;
	}

	public abstract void buildPanel() throws Exception;
	
	public abstract RotaPanelInfoVO getPanelData() throws Exception;

}
