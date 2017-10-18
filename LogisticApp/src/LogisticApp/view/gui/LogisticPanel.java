package LogisticApp.view.gui;

import javax.swing.JPanel;

import LogisticApp.view.vo.PanelInfoVO;

public abstract class LogisticPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public abstract void buildPanel();
	
	public abstract PanelInfoVO getPanelData() throws Exception;

}
