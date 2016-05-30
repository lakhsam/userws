package me.ahmed.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import me.ahmed.batch.model.User;

public class UserItemProcessor implements ItemProcessor<User, User> {

	@Override
	public User process(User user) throws Exception {
		System.out.println("Processing result : " + user.getUsername());
		/*
		 * Only return results which are bank code 23890
		 *
		 */
		if (!"23890".equals(user.getCodeBanque())) {
			System.out.println("USER  : " + user.getUsername() + " IGNORED");
			return null;
		}
		return user;
	}

}
