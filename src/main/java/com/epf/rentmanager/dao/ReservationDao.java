package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ReservationDao {

//	private static ReservationDao instance = null;
	private ReservationDao() {
	}
//	public static ReservationDao getInstance() {
//		if(instance == null) {
//			instance = new ReservationDao();
//		}
//		return instance;
//	}

	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATION_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String COUNT_RESERVATIONS_QUERY = "SELECT COUNT(id) AS count FROM Reservation;";
	private static final String COUNT_RESERVATIONS_BY_CLIENT_QUERY = "SELECT COUNT(id) AS count FROM Reservation WHERE client_id=?;";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id = ?, vehicle_id = ?, debut = ?, fin = ? WHERE id=?;";

	public long create(Reservation reservation) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_RESERVATION_QUERY);

			pstmt.setInt(1, reservation.getClientId());
			pstmt.setInt(2, reservation.getVehiculeId());
			pstmt.setDate(3, Date.valueOf(reservation.getDebut()));
			pstmt.setDate(4, Date.valueOf(reservation.getFin()));

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public long delete(int id) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_RESERVATION_QUERY);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public long update(Reservation reservation) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_RESERVATION_QUERY);

			pstmt.setInt(1, reservation.getClientId());
			pstmt.setInt(2, reservation.getVehiculeId());
			pstmt.setDate(3, Date.valueOf(reservation.getDebut()));
			pstmt.setDate(4, Date.valueOf(reservation.getFin()));

			pstmt.setInt(5, reservation.getId());

			pstmt.execute();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public List<Reservation> findResaByClientId(int clientId) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);

			pstmt.setInt(1, clientId);

			ResultSet rs = pstmt.executeQuery();

			List<Reservation> listReservation = new ArrayList<>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int vehicleId = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(id, clientId, vehicleId, debut, fin);

				listReservation.add(reservation);
			}
			return listReservation;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findResaByVehicleId(int vehicleId) throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);

			pstmt.setInt(1, vehicleId);

			ResultSet rs = pstmt.executeQuery();

			List<Reservation> listReservation = new ArrayList<>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int clientId = rs.getInt("client_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(id, clientId, vehicleId, debut, fin);

				listReservation.add(reservation);
			}
			return listReservation;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Reservation> findAll() throws DaoException {
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATIONS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			List<Reservation> listReservation = new ArrayList<>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int clientId = rs.getInt("client_id");
				int vehicleId = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();

				Reservation reservation = new Reservation(id, clientId, vehicleId, debut, fin);

				listReservation.add(reservation);
			}

			return listReservation;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Optional<Reservation> findById(int id) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_RESERVATION_QUERY);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			int clientId = rs.getInt("client_id");
			int vehicleId = rs.getInt("vehicle_id");
			LocalDate debut = rs.getDate("debut").toLocalDate();
			LocalDate fin = rs.getDate("fin").toLocalDate();

			Reservation reservation = new Reservation(id, clientId, vehicleId, debut, fin);

			return Optional.of(reservation);
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
			PreparedStatement pstmt = conn.prepareStatement(COUNT_RESERVATIONS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}
	
	public long countByClientId(int id) throws DaoException {
		int count = 0;
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(COUNT_RESERVATIONS_BY_CLIENT_QUERY);

			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}
}
