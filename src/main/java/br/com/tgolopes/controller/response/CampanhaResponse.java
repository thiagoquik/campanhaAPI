package br.com.tgolopes.controller.response;


import java.util.Calendar;

import br.com.tgolopes.controller.util.CalendarSerializer;
import br.com.tgolopes.entity.Campanha;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CampanhaResponse {

	private Long codigo;
	private String nomeCampanha;
	private Long codigoTimeCoracao;
	@JsonSerialize(using = CalendarSerializer.class)
	private Calendar dataInicio;
	@JsonSerialize(using = CalendarSerializer.class)
	private Calendar dataFim;
	
	public CampanhaResponse(Campanha campanha){
		codigo = campanha.getId();
		nomeCampanha = campanha.getNomeCampanha();
		codigoTimeCoracao = campanha.getIdTimeCoracao();
		dataInicio = campanha.getDataInicio();
		dataFim = campanha.getDataFim();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

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
}