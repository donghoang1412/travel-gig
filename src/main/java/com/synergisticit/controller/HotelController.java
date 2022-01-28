package com.synergisticit.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.synergisticit.domain.FAQ;
import com.synergisticit.domain.HelpSupport;
import com.synergisticit.domain.MailRequest;
import com.synergisticit.domain.MailResponse;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.domain.UserResponse;
import com.synergisticit.jwt.JwtUtil;
import com.synergisticit.jwt.models.AuthenticationRequest;
import com.synergisticit.jwt.models.AuthenticationResponse;
import com.synergisticit.restclient.RestClient;
import com.synergisticit.service.EmailSenderService;
import com.synergisticit.service.FAQService;
import com.synergisticit.service.HelpSupportService;
import com.synergisticit.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class HotelController {

	@Autowired
	RestClient restClient;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private FAQService faqService;

	@Autowired
	private HelpSupportService helpSupportService;

	@Autowired
	private EmailSenderService emailSenderService;

	@GetMapping(value = "/searchHotels")
	public ResponseEntity<List<Object>> searchHotels(@RequestParam(value = "hotelName") String hotelName) {
		ResponseEntity<List<Object>> response = new ResponseEntity<>(restClient.searchHotels(hotelName),
				HttpStatus.ACCEPTED);
		return response;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToke(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		Set<String> roles = new HashSet<>();
		userDetails.getAuthorities().forEach(e -> roles.add(e.toString()));
		AuthenticationResponse authenticationJwt = new AuthenticationResponse(jwt, userDetails.getUsername(), roles);
		return ResponseEntity.ok(authenticationJwt);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<?> test() {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		node.put("name", "HelloWord");
		return ResponseEntity.ok(node);
	}

	@PostMapping(value = "/saveBooking", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveBooking(@RequestBody String body) {
		return restClient.saveBooking(body);
	}

	@RequestMapping(value = "/getLoginUser", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@RequestParam(value = "userName") String userName) {
		User user = userService.findByUserName(userName);
		UserResponse userResponse = new UserResponse(user.getUserName(), user.getCustomerMobile());
		return ResponseEntity.ok(userResponse);
	}

	@PostMapping(value = "/getBooking")
	public ResponseEntity<List<Object>> getBookings(@RequestBody String body) {
		ResponseEntity<List<Object>> response = new ResponseEntity<>(restClient.getBooking(body), HttpStatus.ACCEPTED);
		return response;
	}

	@GetMapping(value = "/getHotels")
	public ResponseEntity<Object> getHotelById(@RequestParam(value = "hotelId") int hotelId) {
		ResponseEntity<Object> response = new ResponseEntity<>(restClient.getHotelById(hotelId), HttpStatus.ACCEPTED);
		return response;
	}

	@GetMapping(value = "/getGuest")
	public ResponseEntity<Object> getGuest(@RequestParam(value = "bookingId") int bookingId) {
		ResponseEntity<Object> response = new ResponseEntity<>(restClient.getGuests(bookingId), HttpStatus.ACCEPTED);
		return response;
	}

	@PostMapping(value = "/cancelBooking")
	public ResponseEntity<Object> cancelBooking(@RequestBody String body) {
		ResponseEntity<Object> response = new ResponseEntity<>(restClient.cancelBooking(body), HttpStatus.ACCEPTED);
		return response;
	}

	@PostMapping(value = "/changeStay")
	public ResponseEntity<Object> changeStay(@RequestBody String body) {
		ResponseEntity<Object> response = new ResponseEntity<>(restClient.changeStay(body), HttpStatus.ACCEPTED);
		return response;
	}

	@PostMapping(value = "/saveFAQs")
	public ResponseEntity<FAQ> saveFaq(@RequestBody String body) {
		Gson gson = new Gson();
		FAQ faq = gson.fromJson(body, FAQ.class);
		return ResponseEntity.ok(faqService.save(faq));
	}

	@GetMapping(value = "/getFAQs")
	public ResponseEntity<?> getFaqs() {
		return ResponseEntity.ok(faqService.findAllFAQs());
	}

	@PostMapping(value = "/saveHelpSupportQuestions")
	public ResponseEntity<?> saveHelpSupportQuestions(@RequestBody String body) {
		Gson gson = new Gson();
		HelpSupport helpSupport = gson.fromJson(body, HelpSupport.class);
		return ResponseEntity.ok(helpSupportService.save(helpSupport));
	}

	@GetMapping(value = "/getHelpSupportQuestions")
	public ResponseEntity<?> saveHelpSupportQuestions() {
		return ResponseEntity.ok(helpSupportService.getAllHelpSupport());
	}
	
	@PutMapping(value="/deleteSupportQuestion")
	public ResponseEntity<?> deleteSupportQuestion (@RequestParam int id){
		helpSupportService.delete(id);
		return ResponseEntity.ok(id);
	}

	@PostMapping(value = "/answerQuestionToMail")
	public ResponseEntity<?> sendMail(@RequestBody MailRequest mailRequest) {
		emailSenderService.sendSimpleEmail(mailRequest.getTo(), mailRequest.getBody(), mailRequest.getSubject());
		MailResponse mailResponse = new MailResponse();
		mailResponse.setStatus("success");
		return ResponseEntity.ok(mailResponse);
	}
	
	@PostMapping(value="/saveReview")
	public ResponseEntity<?> saveReview (@RequestBody String body) {
		ResponseEntity<?> response = new ResponseEntity<>(restClient.saveReview(body), HttpStatus.ACCEPTED);
		return response;
	}
	
}
