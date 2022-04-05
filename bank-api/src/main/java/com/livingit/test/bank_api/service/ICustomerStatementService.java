package com.livingit.test.bank_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.livingit.test.bank_api.model.Statement;

@Service
public interface ICustomerStatementService {
	
	/**
	 * 
	 * @param statements
	 */
	void validateCustomerStatement(List<Statement> statements);
}
