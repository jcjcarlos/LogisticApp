package LogisticApp.view.gui.interfaces;

import LogisticApp.view.vo.PanelInfoVO;

public interface ILogisticPanel {
	
	public abstract void buildPanel();
	
	public abstract PanelInfoVO getPanelData() throws Exception;

}
