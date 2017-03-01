package org.poc.business;

import org.poc.model.CustomerOrder;
import org.poc.model.ResponsePojo;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class BurgerOutImpl {
	
	public ResponsePojo createOrder(Message<?> msg) {
		
		CustomerOrder order = (CustomerOrder)msg.getPayload();
		
		ResponsePojo burgerOut = new ResponsePojo();
		if (order.getSandwitchType()!= null && order.getSandwitchType().equals("Veg")) {
			burgerOut.setItem("Veg Burger");
			burgerOut.setItemPrice("6.99");
		} else {
			burgerOut.setItem("Mc Chicken Burger");
			burgerOut.setItemPrice("1.99");
		}
		
		return burgerOut;
		
	}

}
