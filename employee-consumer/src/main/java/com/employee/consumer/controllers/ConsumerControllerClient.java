package com.employee.consumer.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;

@RestController
public class ConsumerControllerClient {

	/*
	 * @Autowired private DiscoveryClient discoveryClient;
	 */

	@Autowired
	private LoadBalancerClient loadBalancer;

	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String getEmployee() throws RestClientException, IOException {

		/*
		 * List<ServiceInstance> instances =
		 * discoveryClient.getInstances("employee-producer"); ServiceInstance info =
		 * instances.get(0);
		 * 
		 * String baseUrl = info.getUri().toString();
		 */

		ServiceInstance serviceInstance = loadBalancer.choose("employee-producer");

		System.out.println(serviceInstance.getUri());

		String baseUrl = serviceInstance.getUri().toString();

		baseUrl = baseUrl + "/employee";

		// String baseUrl = "http://localhost:8082/employee";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println(response.getBody());
		return response.getBody();
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}