package com.epf.rentmanager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/Test")
public class ClientTest extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	ClientService clientService;
	
	@Test
	void isLegal_should_return_true_when_age_is_over_18() {
		// Given
		Client legalUser = new Client("John", "Doe", "john.doe@epf.fr", LocalDate.of(2000, 03, 30));

		// Then
		try {
			assertTrue(ClientService.isLegal(legalUser.getBirthday()));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void isLegal_should_return_false_when_age_is_under_18() {
		// Given
		Client ilegalUser = new Client("John", "Doe", "john.doe@epf.fr", LocalDate.of(2010, 03, 30));

		// Then
		try {
			assertFalse(ClientService.isLegal(ilegalUser.getBirthday()));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void isEmailAvailable_should_return_true_if_the_email_is_not_used() {
		// Given
		Client client = new Client("John", "Doe", "john.doe@epf.fr", LocalDate.of(2000, 03, 30));
		try {
			clientService.create(client);
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Client alreadyUsedEmail = new Client("Janne", "D Arc", "janne.darc@epf.fr", LocalDate.of(2000, 02, 30));
		// Then
		try {
			assertFalse(ClientService.isEmailAvailable(alreadyUsedEmail));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void isEmailAvailable_should_return_false_if_the_email_is_used() {
		// Given
		Client client = new Client("John", "Doe", "john.doe@epf.fr", LocalDate.of(2000, 03, 30));
		try {
			clientService.create(client);
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Client alreadyUsedEmail = new Client("Janne", "D Arc", "john.doe@epf.fr", LocalDate.of(2000, 02, 30));
		// Then
		try {
			assertFalse(ClientService.isEmailAvailable(alreadyUsedEmail));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}