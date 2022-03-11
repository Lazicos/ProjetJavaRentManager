package com.epf.rentmanager.ui.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/users/create")
public class ClientCreateServlet extends HttpServlet {
	private static final String createUser = "/WEB-INF/views/users/create.jsp";

//	ClientService clientService = ClientService.getInstance();

	public ClientCreateServlet() {
	}

	private static final long serialVersionUID = 1L;

	@Autowired
	ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(createUser);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lastName = request.getParameter("last_name");
		String firstName = request.getParameter("first_name");
		String email = request.getParameter("email");
		LocalDate naissance = LocalDate.parse(request.getParameter("naissance"),
				DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		try {
			this.clientService.create(new Client(lastName, firstName, email, naissance));
			response.sendRedirect("http://localhost:8080/rentmanager/users");
		} catch (ServiceException e) {
			request.setAttribute("erreur", e.getMessage());
			request.setAttribute("lastName", lastName);
			request.setAttribute("firstName", firstName);
			request.setAttribute("email", email);
			request.setAttribute("naissance", naissance.format(DateTimeFormatter
				    .ofLocalizedDate(FormatStyle.SHORT)));
			RequestDispatcher dispatcher = request.getRequestDispatcher(createUser);
			dispatcher.forward(request, response);
		}

	}
}
