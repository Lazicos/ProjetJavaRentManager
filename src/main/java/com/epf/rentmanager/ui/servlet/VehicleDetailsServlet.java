package com.epf.rentmanager.ui.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/details")
public class VehicleDetailsServlet extends HttpServlet {

	private static final String carsDetails = "/WEB-INF/views/vehicles/details.jsp";

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
		int id = Integer.parseInt(request.getParameter("id"));

		List<Reservation> listResaVehicle = new ArrayList<>();
		List<Client> listClient = new ArrayList<>();
		try {
			listResaVehicle = this.reservationService.findResaByVehicleId(id);
			request.setAttribute("vehicle", this.vehicleService.findById(id));
			request.setAttribute("nb_reservations", this.reservationService.countByClientId(id));
			request.setAttribute("listRents", listResaVehicle);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			for (int i = 0; i < listResaVehicle.size(); i++) {
				listClient.add(clientService.findById(listResaVehicle.get(i).getClientId()));
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		request.setAttribute("listClient", listClient);
		request.setAttribute("nb_clients", listClient.size());
		
		this.getServletContext().getRequestDispatcher(carsDetails).forward(request, response);
	}
}
