package me.ahmed.batch.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.listener.ItemListenerSupport;

import me.ahmed.batch.model.User;

public class ItemFailureLoggerListener extends ItemListenerSupport<User, User> {

	private static Log logger = LogFactory.getLog("item.error");

	public void onReadError(Exception ex) {
		logger.error("Encountered error on read", ex);
	}

	public void onWriteError(Exception ex, Object item) {
		logger.error("Encountered error on write", ex);
	}

}