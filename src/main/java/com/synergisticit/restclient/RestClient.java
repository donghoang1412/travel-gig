package com.synergisticit.restclient;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;



@Component
public class RestClient {

	private static final String inventoryServiceUrlHotelManagement = "http://localhost:8282";
	private static final String inventoryServiceUrlBooking = "http://localhost:8181";
	
	
	
	public List<Object> searchHotels(String hotelName) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType
				.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(inventoryServiceUrlHotelManagement + "/getHotels?hotelName="+hotelName,
				Object[].class);
		
		Object[] objects = responseEntity.getBody();
		
		return Arrays.asList(objects);

	}
	
//	public Object saveBooking (JsonNode node) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType
//				.APPLICATION_JSON);
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(inventoryServiceUrlBooking+"/saveBooking", node, Object.class);
//		
//		Object obj = responseEntity.getBody();
//		
//		return obj;
//	}
	
	public ResponseEntity<?> saveBooking (String body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType
				.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<?> responseEntity = restTemplate.postForEntity(inventoryServiceUrlBooking+"/saveBooking", body, Object.class);
		
//		Object obj = responseEntity.getBody();
		
		return responseEntity;
	}
	
	public List<Object> getBooking(String body) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType
				.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = restTemplate.postForEntity(inventoryServiceUrlBooking+"/getBookings", body, Object[].class);
		
		Object[] objects = responseEntity.getBody();
		
		return Arrays.asList(objects);

	}
	

	public Object getHotelById(int hotelId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType
				.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity(inventoryServiceUrlHotelManagement + "/getHotelsById?hotelId="+hotelId,
				Object.class);
		
		Object objects = responseEntity.getBody();
		
		return objects;
	}
	
	public List<Object> getGuests(int bookingId) {
		System.out.println(bookingId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType
				.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(inventoryServiceUrlBooking + "/getGuests?bookingId="+bookingId,
				Object[].class);
		
		Object[] objects = responseEntity.getBody();
		
		return Arrays.asList(objects);
	}
	
	public Object cancelBooking (String body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType
				.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(inventoryServiceUrlBooking+"/cancelBooking", body, Object.class);

		Object objects = responseEntity.getBody();
		
		return objects;
	}
	
	public Object changeStay (String body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType
				.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(inventoryServiceUrlBooking+"/changeStay", body, Object.class);

		Object objects = responseEntity.getBody();
		
		return objects;
	}
	
	public Object saveReview (String body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType
				.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity(inventoryServiceUrlHotelManagement+"/saveReview", body, Object.class);

		Object objects = responseEntity.getBody();
		
		return objects;
	}
	
	
	
}