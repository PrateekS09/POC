package org.poc.business;

import org.poc.model.CustomerOrder;
import org.poc.model.ResponsePojo;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class DrinkOutImpl {
	
	public ResponsePojo createOrder(Message<?> msg) {
		
		CustomerOrder order = (CustomerOrder)msg.getPayload();

		ResponsePojo burgerOut = new ResponsePojo();
		if (order.getDrinkType()!= null && order.getDrinkType().equalsIgnoreCase("Soda")) {
			burgerOut.setItem("Coca Cola");
			burgerOut.setItemPrice("1.99");
		} else {
			burgerOut.setItem("Water");
			burgerOut.setItemPrice("0.99");
		}
		
		return burgerOut;
		
	}

}
