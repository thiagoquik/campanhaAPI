package br.com.tgolopes.controller.request;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import br.com.tgolopes.entity.Campanha;

public class CampanhaRequest {

	@NotNull(message = "O Nome da Campanha é obrigatório!")
	private String nomeCampanha;
	
	@NotNull(message = "O Código do Time é obrigatório!")
	private Long codigoTimeCoracao;
	
	@NotNull(message = "A Data de Início é obrigatória!")
	private Calendar dataInicio;
	
	@NotNull(message = "A Data de Fim é obrigatória!")
	private Calendar dataFim;

	public String getNomeCampanha() {
		return nomeCampanha;
	}

	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	public Long getCodigoTimeCoracao() {
		return codigoTimeCoracao;
	}

	public void setCodigoTimeCoracao(Long codigoTimeCoracao) {
		this.codigoTimeCoracao = codigoTimeCoracao;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
	
	public Campanha toCampanha(){
		Campanha campanha = new Campanha();
		campanha.setNomeCampanha(nomeCampanha);
		campanha.setIdTimeCoracao(codigoTimeCoracao);
		campanha.setDataInicio(dataInicio);
		campanha.setDataFim(dataFim);
		return campanha;
	}
}