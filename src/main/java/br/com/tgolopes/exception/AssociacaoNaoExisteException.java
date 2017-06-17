package br.com.tgolopes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Associacao não existe!")
public class AssociacaoNaoExisteException extends Exception{
	private static final long serialVersionUID = 7889465L;
}
