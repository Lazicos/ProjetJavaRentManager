package com.epf.rentmanager.ui.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/users/details")
public class ClientDetailsServlet extends HttpServlet {

	private static final String clientsDetails = "/WEB-INF/views/users/details.jsp";

	private static final long serialVersionUID = 1L;

	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			List<Reservation> listResaClient = this.reservationService.findResaByClientId(id);
			
			request.setAttribute("user", this.clientService.findById(id));
			request.setAttribute("nb_reservations", this.reservationService.countByClientId(id));
			request.setAttribute("listRents", listResaClient);
			
			int vehicleId = Integer.parseInt(request.getParameter("rents.vehicleId"));
			
			request.setAttribute("vehicle", this.vehicleService.findById(vehicleId));
			
			this.getServletContext().getRequestDispatcher(clientsDetails).forward(request, response);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
