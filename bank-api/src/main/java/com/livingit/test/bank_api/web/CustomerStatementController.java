package com.livingit.test.bank_api.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.livingit.test.bank_api.model.Statement;
import com.livingit.test.bank_api.service.ICustomerStatementService;

@RestController
@RequestMapping(value = "/statement")
class CustomerStatementController {
	
	@Autowired
    private ICustomerStatementService service;

	@PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void create(@RequestBody final List<Statement> resource, final HttpServletResponse response) { 
		service.validateCustomerStatement(resource);
	}
}
