package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.service.VehicleService;

@Repository
public class VehicleDao {

//	private static VehicleDao instance = null;
	private VehicleDao() {
	}

//	public static VehicleDao getInstance() {
//		if(instance == null) {
//			instance = new VehicleDao();
//		}
//		return instance;
//	}

	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String FIND_VEHICLES_BY_RES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle JOIN Reservation ON id=vehicule_id WHERE client_id = ?;";
	private static final String COUNT_VEHICLES_QUERY = "SELECT COUNT(id) AS count FROM Vehicle;";

	@Autowired
	VehicleService vehicleService;

	public long create(Vehicle vehicle) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_VEHICLE_QUERY);

			pstmt.setString(1, vehicle.getConstructeur());
			pstmt.setInt(2, vehicle.getNbPlaces());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public long delete(Vehicle vehicle) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_VEHICLE_QUERY);

			pstmt.setInt(1, vehicle.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public Optional<Vehicle> findById(int id) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLE_QUERY);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			int idVehicle = rs.getInt("id");
			String constructeur = rs.getString("constructeur");
			int nbPlaces = rs.getInt("nb_places");

			Vehicle vehicle = new Vehicle(idVehicle, constructeur, nbPlaces);

			return Optional.of(vehicle); // ou Optional.offNullable(client)

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Vehicle> findAll() throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();

			List<Vehicle> listVehicle = new ArrayList<>();

			while (rs.next()) {
				int idVehicle = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nbPlaces = rs.getInt("nb_places");

				Vehicle vehicle = new Vehicle(idVehicle, constructeur, nbPlaces);

				listVehicle.add(vehicle);
			}

			return listVehicle;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public long count() throws DaoException {
		int count = 0;
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_VEHICLES_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	public List<Vehicle> findVehicleByRes(int id) throws DaoException, ServiceException {
	
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_VEHICLES_BY_RES_QUERY);

			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();

			List<Vehicle> listVehicle = new ArrayList<>();

			while (rs.next()) {
				int idVehicle = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nbPlaces = rs.getInt("nb_places");

				Vehicle vehicle = new Vehicle(idVehicle, constructeur, nbPlaces);

				listVehicle.add(vehicle);
			}

			return listVehicle;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
