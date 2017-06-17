package br.com.tgolopes.campanha.mock;

import br.com.tgolopes.controller.request.TorcedorCampanhaRequest;

public class TorcedorCampanhaRequestMock {
	public static TorcedorCampanhaRequest getTorcedorCampanhaRequest(){
		TorcedorCampanhaRequest torcedorcampanhaRequest = new TorcedorCampanhaRequest();
		torcedorcampanhaRequest.setEmail("thiago@gmail.com");
		torcedorcampanhaRequest.setNomeTime("Lindoia");
		torcedorcampanhaRequest.setCodigoCampanha(1L);
		return torcedorcampanhaRequest;
	}
}
