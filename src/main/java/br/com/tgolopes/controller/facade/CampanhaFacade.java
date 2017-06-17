package br.com.tgolopes.controller.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tgolopes.controller.request.CampanhaRequest;
import br.com.tgolopes.controller.response.CampanhaResponse;
import br.com.tgolopes.entity.Campanha;
import br.com.tgolopes.exception.CampanhaNaoEncontradaException;
import br.com.tgolopes.service.CampanhaService;

@Component
public class CampanhaFacade {
	@Autowired
	private CampanhaService campanhaService;
	
	public CampanhaResponse salvar(CampanhaRequest campanhaRequest) {
		return new CampanhaResponse (this.campanhaService.salvar(campanhaRequest.toCampanha()));
	}
	
	public CampanhaResponse alterar(Long id, CampanhaRequest campanhaRequest) throws CampanhaNaoEncontradaException { 
		Optional.ofNullable(campanhaService.pesquisarPorId(id)).orElseThrow(() -> new CampanhaNaoEncontradaException());
		Campanha campanha = campanhaRequest.toCampanha();
		campanha.setId(id);
		return new CampanhaResponse (this.campanhaService.salvar(campanha));
	}

	public void excluir(Long id) throws CampanhaNaoEncontradaException {
		Campanha campanha = Optional.ofNullable(campanhaService.pesquisarPorId(id)).orElseThrow(() -> new CampanhaNaoEncontradaException());
		this.campanhaService.deletar(campanha);
	}
	public CampanhaResponse buscar(Long id) throws CampanhaNaoEncontradaException {
		return Optional.ofNullable(campanhaService.pesquisarPorId(id)).map(CampanhaResponse::new).orElseThrow(() -> new CampanhaNaoEncontradaException());
	}
	
	public List<CampanhaResponse> buscarTodas() {
		return campanhaService.pesquisarTodas().stream().map(CampanhaResponse::new).collect(Collectors.toList());
	}
	
	public List<CampanhaResponse> buscarVigentesPorTime(Long codigoTime) {
		return campanhaService.pesquisarVigentesPorTime(codigoTime).stream().map(CampanhaResponse::new).collect(Collectors.toList());
	}
	
	public List<CampanhaResponse> buscarTodasVigentes() {
		return campanhaService.pesquisarVigentes().stream().map(CampanhaResponse::new).collect(Collectors.toList());
	}
}