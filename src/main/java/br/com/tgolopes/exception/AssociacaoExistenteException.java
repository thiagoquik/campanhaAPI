package br.com.tgolopes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Associacao Ja Existe!")
public class AssociacaoExistenteException extends Exception{
	private static final long serialVersionUID = 7889465L;
}
