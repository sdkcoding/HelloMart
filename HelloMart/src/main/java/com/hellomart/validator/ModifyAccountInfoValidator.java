package com.hellomart.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hellomart.dto.Account;

public class ModifyAccountInfoValidator implements Validator {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ModifyAccountInfoValidator.class);
	
	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		
		this.rejectIfEmptyOrWhitespace(errors, account);
		this.rejectIfNotMatch(errors, account);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz); 
	}
	
	private void rejectIfEmptyOrWhitespace(Errors errors, Account account) {
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
		ValidationTools.rejectIfNotMatch(errors, "form.error.notvalidate.email", 
				"email", account.getEmail(), "[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.[a-zA-Z]{2,3}");
	}
	
}

