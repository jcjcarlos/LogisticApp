package LogisticApp.view.gui;

import java.util.List;

import LogisticApp.view.gui.interfaces.ILogisticFrame;
import LogisticApp.view.vo.RotaVO;

public class ContratacaoTransporteFrame implements ILogisticFrame {
	
	private List<RotaVO> rotas;
	
	public ContratacaoTransporteFrame(List<RotaVO> rotas){
		this.rotas = rotas;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
