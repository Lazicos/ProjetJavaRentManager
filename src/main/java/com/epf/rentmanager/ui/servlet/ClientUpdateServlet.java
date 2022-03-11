package com.epf.rentmanager.ui.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;

@WebServlet("/users/update")
public class ClientUpdateServlet extends HttpServlet {

	private static final String clientUpdate = "/WEB-INF/views/users/update.jsp";

	private static final long serialVersionUID = 1L;
	
	private Client oldClient = null;
	
	private int id;

	@Autowired
	ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("id"));
		
		try {
			oldClient = clientService.findById(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		String birthday = oldClient.getBirthday().format(DateTimeFormatter
			    .ofLocalizedDate(FormatStyle.SHORT));

		request.setAttribute("lastName", oldClient.getLastName());
		request.setAttribute("firstName", oldClient.getFirstName());
		request.setAttribute("email", oldClient.getEmail());
		request.setAttribute("birthday", birthday);

		this.getServletContext().getRequestDispatcher(clientUpdate).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String lastName = request.getParameter("last_name");
		String firstName = request.getParameter("first_name");
		String email = request.getParameter("email");
		LocalDate naissance = LocalDate.parse(request.getParameter("naissance"),
				DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		Client newClient = new Client(id, lastName, firstName, email, naissance);

		try {
			this.clientService.update(newClient);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.sendRedirect("http://localhost:8080/rentmanager/users");
	}
}
