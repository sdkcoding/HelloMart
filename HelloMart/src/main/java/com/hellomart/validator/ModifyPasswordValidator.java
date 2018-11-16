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
public class ModifyPasswordValidator implements Validator {
	
	@Autowired
	private AccountService service;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ModifyPasswordValidator.class);
	
	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		
		
		String id = account.getId();
		String pw = account.getPassword();
		String pw_db = service.getPasswd(id);
		String new_pw = account.getNew_password();
		String re_pw = account.getRe_password();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "new_password", "form.error.required");
		
		ValidationTools.rejectIfNotEquals(errors, "form.error.notequal.password",
					"password", pw, pw_db);
		
		ValidationTools.rejectIfNotMatch(errors, "form.error.notvalidate.password",
					"new_password", new_pw, "[a-zA-Z](?=.*\\d{3,})(?=.*\\W)[0-9a-zA-Z!@#$%^&*]{7,15}");
		
		ValidationTools.rejectIfNotEquals(errors, "form.error.notequal.password",
					"new_password","re_password", new_pw, re_pw);
		
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz); 
	}
}

