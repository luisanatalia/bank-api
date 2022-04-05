package com.livingit.test.bank_api.service;

public class IBANValidatorService {
	
	public static boolean isValidAccountNumber(final String accNumber) {
		if(accNumber.startsWith("53")) {
			return true;
		} 
		return false;
	}

}
