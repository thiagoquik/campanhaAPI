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

import br.com.tgolopes.controller.facade.CampanhaFacade;
import br.com.tgolopes.controller.request.CampanhaRequest;
import br.com.tgolopes.controller.response.CampanhaResponse;
import br.com.tgolopes.exception.CampanhaNaoEncontradaException;

@RestController
@RequestMapping(value = "v1/campanha")
public class CampanhaController {

	@Autowired
	private CampanhaFacade campanhaFacade;
	
	@ApiOperation(value = "Salva uma campanha", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CampanhaResponse salvar(@RequestBody @Valid CampanhaRequest campanhaRequest) {
		return this.campanhaFacade.salvar(campanhaRequest);
	}
	
	@ApiOperation(value = "Altera uma campanha", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CampanhaResponse alterar(@PathVariable Long id, @RequestBody @Valid CampanhaRequest campanhaRequest) throws CampanhaNaoEncontradaException {
		return this.campanhaFacade.alterar(id, campanhaRequest);
	}

	@ApiOperation(value = "Exclui uma campanha", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE })
	public void excluir(@PathVariable Long id) throws CampanhaNaoEncontradaException {
		this.campanhaFacade.excluir(id);
	}
	
	@ApiOperation(value = "Consulta um Campanha pelo id", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public CampanhaResponse pesquisarId(@PathVariable Long id) throws CampanhaNaoEncontradaException {
		return this.campanhaFacade.buscar(id);
	}
	
	@ApiOperation(value = "Consulta todas as Campanhas vigentes por time", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/time/{codigoTime}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<CampanhaResponse> exibirVigentesPorTime(@PathVariable Long codigoTime) {
		return campanhaFacade.buscarVigentesPorTime(codigoTime);
	}
	
	@ApiOperation(value = "Consulta todas as Campanhas vigentes", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<CampanhaResponse> exibirVigentes() {
		return campanhaFacade.buscarTodasVigentes();
	}
}