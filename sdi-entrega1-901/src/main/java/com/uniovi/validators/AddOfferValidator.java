package com.uniovi.validators;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import com.uniovi.entities.Offer;
import com.uniovi.services.OffersService;

@Component
public class AddOfferValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Offer.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Offer offer = (Offer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "details", "Error.empty");
		if (offer.getPrice() <= 0) {
			errors.rejectValue("price", "Error.price.negative");
		}
	}

}
