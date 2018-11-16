package com.hellomart.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hellomart.dto.Account;
import com.hellomart.service.AccountService;

@Component
public class DeleteAccountValidator implements Validator {
	
	@Autowired
	private AccountService service;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DeleteAccountValidator.class);
	
	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		String id = account.getId();
		String pw = account.getPassword();
		String pw_db = service.getPasswd(id);
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "form.error.required");
		ValidationTools.rejectIfNotEquals(errors, "form.error.notequal.password",
				"password", pw, pw_db);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz); 
	}
	
}
