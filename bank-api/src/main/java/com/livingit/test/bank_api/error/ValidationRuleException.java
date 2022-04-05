package com.livingit.test.bank_api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ValidationRuleException extends RuntimeException {

	private static final long serialVersionUID = 2719634812837009249L;

}
