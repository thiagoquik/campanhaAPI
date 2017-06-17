package br.com.tgolopes.controller.response;

import br.com.tgolopes.entity.TorcedorCampanha;

public class TorcedorCampanhaResponse {
	
	private Long codigoAssociacao;
	private String email;
	private String nomeTime;
	private Long codigoCampanha;
	
	public TorcedorCampanhaResponse(TorcedorCampanha torcedorCampanha){
		codigoAssociacao = torcedorCampanha.getId();
		email = torcedorCampanha.getEmail();
		nomeTime = torcedorCampanha.getNomeTime();
		codigoCampanha = torcedorCampanha.getIdCampanha();
	}

	public Long getCodigoAssociacao() {
		return codigoAssociacao;
	}

	public void setCodigoAssociacao(Long codigoAssociacao) {
		this.codigoAssociacao = codigoAssociacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeTime() {
		return nomeTime;
	}

	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}

	public Long getCodigoCampanha() {
		return codigoCampanha;
	}

	public void setCodigoCampanha(Long codigoCampanha) {
		this.codigoCampanha = codigoCampanha;
	}
}