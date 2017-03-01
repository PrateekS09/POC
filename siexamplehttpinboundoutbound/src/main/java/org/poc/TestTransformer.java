package org.poc;

import java.util.ArrayList;
import java.util.List;

import org.poc.model.ResponsePojo;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class TestTransformer {
	
	
	public ResponseClass testData(Message<?> msg) {
		System.out.println("Inside aggregator");
		ResponsePojo response = (ResponsePojo)msg.getPayload();
		
		String value = (String)msg.getHeaders().get("prop");
		response.setHeaderValue(value);
		
		List<ResponsePojo> list = new ArrayList<ResponsePojo>();
		list.add(response);
		
		ResponseClass resp = new ResponseClass();
		resp.setResponseList(list);
		double price = 0;
		for(ResponsePojo pojo : list) {
			price = price + Double.parseDouble(pojo.getItemPrice());
		}
		resp.setTotalPrice("$"+price);
		
		
		return resp;
	}
	
	
	static class ResponseClass {
		
		List<ResponsePojo> responseList = new ArrayList<ResponsePojo>();
		String totalPrice;
		public List<ResponsePojo> getResponseList() {
			return responseList;
		}
		public void setResponseList(List<ResponsePojo> responseList) {
			this.responseList = responseList;
		}
		public String getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(String totalPrice) {
			this.totalPrice = totalPrice;
		}
		
	}

}
