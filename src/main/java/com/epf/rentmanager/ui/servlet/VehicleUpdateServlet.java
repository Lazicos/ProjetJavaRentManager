package com.epf.rentmanager.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/update")
public class VehicleUpdateServlet extends HttpServlet {

	private static final String carsUpdate = "/WEB-INF/views/vehicles/update.jsp";

	private static final long serialVersionUID = 1L;
	
	private Vehicle oldVehicle = null;
	
	private int id;

	@Autowired
	VehicleService vehicleService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("id"));
		
		try {
			oldVehicle = vehicleService.findById(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		request.setAttribute("constructeur", oldVehicle.getConstructeur());
		request.setAttribute("nbPlaces", oldVehicle.getNbPlaces());

		this.getServletContext().getRequestDispatcher(carsUpdate).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		String contructeur = request.getParameter("constructeur");
		int nbPlaces = Integer.valueOf(request.getParameter("nb_places"));

		Vehicle newVehicle = new Vehicle(id, contructeur, nbPlaces);

		try {
			this.vehicleService.update(newVehicle);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		response.sendRedirect("http://localhost:8080/rentmanager/cars");
	}
}
