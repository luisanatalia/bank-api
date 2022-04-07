package com.livingit.test.bank_api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbidenException extends RuntimeException {

	private static final long serialVersionUID = 7012020128334152800L;

}
