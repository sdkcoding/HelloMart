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
public class JoinFormValidator implements Validator {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(JoinFormValidator.class);
	
	@Autowired
	private AccountService service;
	
	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		
		this.rejectIfEmptyOrWhitespace(errors, account);
		this.rejectIfNotMatch(errors, account);
		this.rejectIfDuplicate(errors, account);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz); 
	}
	
	private void rejectIfDuplicate(Errors errors, Account account) {
		ValidationTools.rejectIfTrue(errors, "form.error.duplicate.id", "id", service.countId(account.getId()) == 1);
		ValidationTools.rejectIfTrue(errors, "form.error.duplicate.email", "email", service.countEmail(account.getEmail()) == 1);
	}
	
	private void rejectIfEmptyOrWhitespace(Errors errors, Account account) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postCode", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "form.error.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "form.error.required");
		
		if(ValidationTools.isNullOrEmpty(account.getRoadAddress())) {
			errors.rejectValue("roadAddress", "form.error.required");
		} else if(ValidationTools.isNullOrEmpty(account.getDetailAddress())) {
			errors.rejectValue("detailAddress", "form.error.detailaddress.required");
		}
		
		if(ValidationTools.isNullOrEmpty(account.getBirthYear()) ||
		   ValidationTools.isNullOrEmpty(account.getBirthMonth()) ||
		   ValidationTools.isNullOrEmpty(account.getBirthDay())) {
			errors.rejectValue("birthYear", "form.error.required");
		}
	}
	
	private void rejectIfNotMatch(Errors errors, Account account) {
		ValidationTools.rejectIfNotMatch(errors, "form.error.notvalidate.id", 
				"id", account.getId(), "[a-zA-Z][0-9a-zA-Z]{5,19}");
		ValidationTools.rejectIfNotMatch(errors, "form.error.notvalidate.password",
				"password", account.getPassword(), "[a-zA-Z](?=.*\\d{3,})(?=.*\\W)[0-9a-zA-Z!@#$%^&*]{7,15}");
		ValidationTools.rejectIfNotEquals(errors, "form.error.notequal.password",
				"password", "re_password", account.getPassword(), account.getRe_password());
		ValidationTools.rejectIfNotMatch(errors, "form.error.notvalidate.email", 
				"email", account.getEmail(), "[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.[a-zA-Z]{2,3}");
	}

}

