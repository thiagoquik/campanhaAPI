package br.com.tgolopes.controller.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tgolopes.controller.request.TorcedorCampanhaRequest;
import br.com.tgolopes.controller.response.TorcedorCampanhaResponse;
import br.com.tgolopes.entity.TorcedorCampanha;
import br.com.tgolopes.exception.AssociacaoExistenteException;
import br.com.tgolopes.exception.AssociacaoNaoExisteException;
import br.com.tgolopes.exception.CampanhaInvalidaException;
import br.com.tgolopes.exception.CampanhaNaoEncontradaException;
import br.com.tgolopes.exception.TorcedorNaoTemCampanhaAssociada;
import br.com.tgolopes.repository.CampanhaRepository;
import br.com.tgolopes.repository.TorcedorCampanhaRepository;
import br.com.tgolopes.service.TorcedorCampanhaService;

@Component
public class TorcedorCampanhaFacade {
	@Autowired
	private TorcedorCampanhaService torcedorCampanhaService;
	
	@Autowired
	private TorcedorCampanhaRepository torcedorCampanhaRepository;
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	public TorcedorCampanhaResponse salvar(TorcedorCampanhaRequest torcedorCampanhaRequest) throws CampanhaInvalidaException, AssociacaoExistenteException {
		Optional.ofNullable(campanhaRepository.findOne(torcedorCampanhaRequest.getCodigoCampanha())).orElseThrow(() -> new CampanhaInvalidaException());
		if(torcedorCampanhaRepository.findByIdCampanhaAndEmail(torcedorCampanhaRequest.getCodigoCampanha(), torcedorCampanhaRequest.getEmail()) != null)
			throw new AssociacaoExistenteException();
		return new TorcedorCampanhaResponse (this.torcedorCampanhaService.salvar(torcedorCampanhaRequest.toTorcedorCampanha()));
	}
	
	public void excluir(Long id) throws AssociacaoNaoExisteException {
		TorcedorCampanha torcedorCampanha = Optional.ofNullable(torcedorCampanhaService.pesquisarPorId(id)).orElseThrow(() -> new AssociacaoNaoExisteException());
		this.torcedorCampanhaService.deletar(torcedorCampanha);
	}
	
	public TorcedorCampanhaResponse buscar(Long id) throws CampanhaNaoEncontradaException {
		return Optional.ofNullable(torcedorCampanhaService.pesquisarPorId(id)).map(TorcedorCampanhaResponse::new).orElseThrow(() -> new CampanhaNaoEncontradaException());
	}
	
	public List<TorcedorCampanhaResponse> buscarTodas() {
		return torcedorCampanhaService.pesquisarTodas().stream().map(TorcedorCampanhaResponse::new).collect(Collectors.toList());
	}
	
	public List<TorcedorCampanhaResponse> buscarPorIdCampanha(Long idCampanha) throws CampanhaNaoEncontradaException {
		List<TorcedorCampanhaResponse> lista =  torcedorCampanhaService.buscarPorIdCampanha(idCampanha).stream().map(TorcedorCampanhaResponse::new).collect(Collectors.toList());
		if(lista.isEmpty())
			throw new CampanhaNaoEncontradaException();
		return lista;
	}
	
	public List<TorcedorCampanhaResponse> buscarPorEmail(String email) throws TorcedorNaoTemCampanhaAssociada {
		List<TorcedorCampanhaResponse> lista = torcedorCampanhaService.buscarPorEmail(email).stream().map(TorcedorCampanhaResponse::new).collect(Collectors.toList());
		if(lista.isEmpty())
			throw new TorcedorNaoTemCampanhaAssociada();
		return lista;
	}
}