package br.com.tgolopes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Torcedor não tem nenhuma campanha associada!")
public class TorcedorNaoTemCampanhaAssociada extends Exception{
	private static final long serialVersionUID = 7889465L;
}
