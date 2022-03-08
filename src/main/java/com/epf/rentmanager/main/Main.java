package com.epf.rentmanager.main;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

public class Main {

	public static void main(String[] args) throws DaoException {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		ClientService clientService = context.getBean(ClientService.class);
		VehicleService vehicleService = context.getBean(VehicleService.class);
		ReservationService reservationService = context.getBean(ReservationService.class);
		try {
//			clientService.create(new Client("vqv", "vzvv", "ffffff", LocalDate.of(2022, 02, 20)));
//			System.out.println(clientService.findAll());
//			System.out.println(vehicleService.findAll());
//			reservationService.create(new Reservation(1, 4,  LocalDate.of(2022, 02, 10), LocalDate.of(2022, 03, 30)));
//			reservationService.delete(new Reservation(2, 1, 2,  LocalDate.of(2022, 02, 20), LocalDate.of(2022, 02, 25)));
			List<Reservation> resClient = reservationService.findResaByClientId(1);
			System.out.println(resClient);
			System.out.println(vehicleService.findVehicleByRes(1));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
