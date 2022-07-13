package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AuthRequest;
import com.example.demo.Dto.AuthenticateResponse;
import com.example.demo.Dto.CustomerDto;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/customer")
	public Customer createCustomer(@RequestBody Customer cus) {
		Customer customer = new Customer();
		customer.setUserName(cus.getUserName());
		customer.setEmail(cus.getEmail());
		customer.setMobileNo(cus.getMobileNo());
		customer.setPassword(passwordEncoder.encode(cus.getPassword()));
		return customerRepository.save(customer);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticateResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		AuthenticateResponse authenticateResponse = new AuthenticateResponse();
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			// Find customer by username 
			//authentication.getName();
			Customer customer =customerRepository.findByUserName(authRequest.getUserName());
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCustomerId(customer.getCustomerId());
			customerDto.setUserName(customer.getUserName());
			customerDto.setEmail(customer.getEmail());
			customerDto.setMobileNo(customer.getMobileNo());
			
			authenticateResponse.setCustomerInfo(customerDto);
			authenticateResponse.setAccessToken(jwtUtil.generateToken(authentication.getName()));
			authenticateResponse.setStatus(true);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new Exception("inavalid userName/password");
		}
		return ResponseEntity.ok(authenticateResponse);
	}

}
