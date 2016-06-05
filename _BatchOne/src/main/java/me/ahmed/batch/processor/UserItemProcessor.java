package me.ahmed.batch.processor;

import me.ahmed.batch.model.User;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

public class UserItemProcessor implements ItemProcessor<User, User> {

	private Validator validator;

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Override
	public User process(User user) throws Exception {
		System.out.println("Processing result : " + user.getUsername());
		/*
		 * Only return results which are bank code 23890
		 */
		if (!"23890".equals(user.getCodeBanque())) {
			System.out.println("USER  : " + user.getUsername() + " IGNORED");
			return null;
		}
		BindingResult results = BindAndValidate(user);
		if (results.hasErrors())
			buildValidationException(results);
		return user;
	}

	private BindingResult BindAndValidate(User item) {
		DataBinder binder = new DataBinder(item);
		binder.setValidator(validator);

		binder.validate();

		return binder.getBindingResult();

	}

	private void buildValidationException(BindingResult results) {
		StringBuilder msg = new StringBuilder();
		for (ObjectError error : results.getAllErrors()) {
			msg.append("-*-*-*- \n" + error.toString() + "-*-*-*- \n");
		}
		throw new ValidationException(msg.toString());
	}

}
