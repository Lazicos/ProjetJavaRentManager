package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;

	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

//	private VehicleService() {
//		this.vehicleDao = VehicleDao.getInstance();
//	}
//
//	public static VehicleService getInstance() {
//		if (instance == null) {
//			instance = new VehicleService();
//		}
//
//		return instance;
//	}

	public long create(Vehicle vehicle) throws ServiceException {
		if (vehicle.getConstructeur() == null) {
			throw new ServiceException("Erreur dans le service : champ constructeur vide");
		} else if (vehicle.getNbPlaces() < 1) {
			throw new ServiceException("Erreur dans le service : moins de 1 place dans le vehicule");
		} else {
			try {
				return this.vehicleDao.create(vehicle);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public Vehicle findById(int id) throws ServiceException {
		try {
			return this.vehicleDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public long delete(int id) throws ServiceException {
		try {
			return this.vehicleDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long update(Vehicle vehicle) throws ServiceException {
		try {
			return this.vehicleDao.update(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public long count() throws ServiceException {
		try {
			return this.vehicleDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
