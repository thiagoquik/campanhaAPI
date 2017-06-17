package br.com.tgolopes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Campanha Inválida!")
public class CampanhaInvalidaException extends Exception{
	private static final long serialVersionUID = 7889465L;
}
