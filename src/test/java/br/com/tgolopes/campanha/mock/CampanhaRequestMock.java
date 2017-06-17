package br.com.tgolopes.campanha.mock;

import java.util.Calendar;

import br.com.tgolopes.controller.request.CampanhaRequest;

public class CampanhaRequestMock {
	public static CampanhaRequest getCampanhaRequest(){
		CampanhaRequest campanhaRequest = new CampanhaRequest();
		campanhaRequest.setNomeCampanha("Campanha de fim de ano");
		campanhaRequest.setCodigoTimeCoracao(5L);
		campanhaRequest.setDataInicio(Calendar.getInstance());
		campanhaRequest.setDataFim(Calendar.getInstance());
		return campanhaRequest;
	}
}
