package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;

@Repository
public class ClientDao {

//	private static ClientDao instance = null;

	private ClientDao() {
	}

//	public static ClientDao getInstance() {
//		if (instance == null) {
//			instance = new ClientDao();
//		}
//		return instance;
//	}

	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String COUNT_CLIENTS_QUERY = "SELECT COUNT(id) AS count FROM Client;";
	private static final String FIND_EMAIL_QUERY = "SELECT id FROM Client WHERE email=?;";

	public long create(Client client) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(CREATE_CLIENT_QUERY);

			pstmt.setString(1, client.getLastName());
			pstmt.setString(2, client.getFirstName());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthday()));

			pstmt.execute();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public long delete(int id) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(DELETE_CLIENT_QUERY);

			pstmt.setInt(1, id);

			pstmt.execute();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public long update(Client client) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(UPDATE_CLIENT_QUERY);

			pstmt.setString(1, client.getLastName());
			pstmt.setString(2, client.getFirstName());
			pstmt.setString(3, client.getEmail());
			pstmt.setDate(4, Date.valueOf(client.getBirthday()));

			pstmt.setInt(5, client.getId());

			pstmt.execute();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public Optional<Client> findById(int id) throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENT_QUERY);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			String clientLastName = rs.getString("nom");
			String clientFirstName = rs.getString("prenom");
			String clientEmail = rs.getString("email");
			LocalDate clientBirthday = rs.getDate("naissance").toLocalDate();

			Client client = new Client(id, clientLastName, clientFirstName, clientEmail, clientBirthday);
			rs.close();
			pstmt.close();
			conn.close();

			return Optional.of(client); // ou Optional.offNullable(client)
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public List<Client> findAll() throws DaoException {

		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_CLIENTS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			List<Client> listClient = new ArrayList<>();

			while (rs.next()) {
				int id = rs.getInt("id");
				String clientLastName = rs.getString("nom");
				String clientFirstName = rs.getString("prenom");
				String clientEmail = rs.getString("email");
				LocalDate clientBirthday = rs.getDate("naissance").toLocalDate();

				Client client = new Client(id, clientLastName, clientFirstName, clientEmail, clientBirthday);

				listClient.add(client);
			}

			rs.close();
			pstmt.close();
			conn.close();

			return listClient;
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
			PreparedStatement pstmt = conn.prepareStatement(COUNT_CLIENTS_QUERY);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt("count");
			}

			pstmt.execute();
			pstmt.close();
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	public boolean isLegal(LocalDate birthday) throws DaoException {
		LocalDate today = LocalDate.now();

		if (birthday.until(today, ChronoUnit.YEARS) >= 18) {
			return true;
		} else
			return false;
	}

	public boolean isEmailAvailable(Client client) throws DaoException {
		Boolean emailAvailable = true;
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(FIND_EMAIL_QUERY);
			pstmt.setString(1, client.getEmail());
			pstmt.execute();

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				emailAvailable = false;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emailAvailable;
	}

}
