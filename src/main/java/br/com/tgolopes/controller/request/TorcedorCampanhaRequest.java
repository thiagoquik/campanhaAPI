package br.com.tgolopes.controller.request;

import javax.validation.constraints.NotNull;

import br.com.tgolopes.entity.TorcedorCampanha;

public class TorcedorCampanhaRequest {
	
	@NotNull(message = "O Email é obrigatório!")
	private String email;
	
	@NotNull(message = "O Nome do Time é obrigatório!")
	private String nomeTime;
	
	@NotNull(message = "O Código da Campanha é obrigatório!")
	private Long codigoCampanha;
	
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

	public TorcedorCampanha toTorcedorCampanha(){
		TorcedorCampanha torcedorCampanha = new TorcedorCampanha();
		torcedorCampanha.setEmail(email);
		torcedorCampanha.setNomeTime(nomeTime);
		torcedorCampanha.setIdCampanha(codigoCampanha);
		return torcedorCampanha;
	}
}