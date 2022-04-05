package com.livingit.test.bank_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.livingit.test.bank_api.error.InvalidDataException;
import com.livingit.test.bank_api.error.ValidationRuleException;
import com.livingit.test.bank_api.model.Statement;

/**
 * 
 * @author lrey
 *
 */
@Service 
public class CustomerStatementService implements ICustomerStatementService{
	
	private Map<String, String> txReferences = new HashMap<>();

	@Override
	public void validateCustomerStatement(List<Statement> statements) {
		for (Statement statement : statements) {
			String txreference = statement.getTransactionReference();
			String accountNum = statement.getAccountNumber();
			
			if(txReferences.containsKey(txreference)) {
				throw new InvalidDataException("Transaction references should be unique");
			}
			txReferences.put(txreference, txreference);
			
			Double balance = statement.getStartBalance() + statement.getMutation();		
			if(!balance.equals(statement.getEndBalance())) {
				throw new InvalidDataException("End balance is not consistent with given data");
			}
			
			boolean validIBAN = IBANValidatorService.isValidAccountNumber(accountNum);
			if(!validIBAN) {
				throw new ValidationRuleException();
			}
		}		
	}

}
