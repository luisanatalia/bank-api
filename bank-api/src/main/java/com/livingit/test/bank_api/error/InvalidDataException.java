package com.livingit.test.bank_api.error;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * 
 * @author lrey
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = 6836812826247002335L;
	
	public InvalidDataException(String msg) {
		super(msg);
	}
}
