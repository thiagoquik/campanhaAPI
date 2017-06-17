package br.com.tgolopes.controller;

import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tgolopes.controller.facade.TorcedorCampanhaFacade;
import br.com.tgolopes.controller.request.TorcedorCampanhaRequest;
import br.com.tgolopes.controller.response.TorcedorCampanhaResponse;
import br.com.tgolopes.exception.AssociacaoExistenteException;
import br.com.tgolopes.exception.AssociacaoNaoExisteException;
import br.com.tgolopes.exception.CampanhaInvalidaException;
import br.com.tgolopes.exception.CampanhaNaoEncontradaException;
import br.com.tgolopes.exception.TorcedorNaoTemCampanhaAssociada;

@RestController
@RequestMapping(value = "v1/associacoes")
public class TorcedorCampanhaController {

	@Autowired
	private TorcedorCampanhaFacade torcedorCampanhaFacade;
	
	@ApiOperation(value = "Associa o Torcedor a uma Campanha", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public TorcedorCampanhaResponse associar(@RequestBody @Valid TorcedorCampanhaRequest torcedorCampanhaRequest) throws CampanhaInvalidaException, AssociacaoExistenteException{
		return torcedorCampanhaFacade.salvar(torcedorCampanhaRequest);
	}
	
	@ApiOperation(value = "Exclui uma Associacao", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void excluir(@PathVariable Long id) throws AssociacaoNaoExisteException {
		this.torcedorCampanhaFacade.excluir(id);
	}
	
	@ApiOperation(value = "Consulta todas as Associacoes", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TorcedorCampanhaResponse> buscarTodasAssociacoes() {
		return torcedorCampanhaFacade.buscarTodas();
	}
	
	@ApiOperation(value = "Consulta uma Associacao por id da Campanha", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/campanha/{idCampanha}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TorcedorCampanhaResponse> buscarPorCampanha(@PathVariable Long idCampanha) throws CampanhaNaoEncontradaException {
		return torcedorCampanhaFacade.buscarPorIdCampanha(idCampanha);
	}
	
	@ApiOperation(value = "Consulta uma Associacao por Email do Torcedor", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/torcedor", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<TorcedorCampanhaResponse> buscarPorEmailTorcedor(@RequestBody @Valid String email) throws TorcedorNaoTemCampanhaAssociada {
		return torcedorCampanhaFacade.buscarPorEmail(email);
	}
}