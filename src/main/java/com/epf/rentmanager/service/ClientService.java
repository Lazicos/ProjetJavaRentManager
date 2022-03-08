package com.epf.rentmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

@Service
public class ClientService {

	private ClientDao clientDao;
//	public static ClientService instance;

//	private ClientService() {
//		this.clientDao = ClientDao.getInstance();
//	}

	private ClientService(ClientDao clientDao) {
		this.clientDao = clientDao;
	}

//	public static ClientService getInstance() {
//		if (instance == null) {
//			instance = new ClientService();
//		}
//
//		return instance;
//	}

	public long create(Client client) throws ServiceException {
		if (client.getLastName() == null || client.getFirstName() == null) {
			throw new ServiceException("Erreur dans le service : champs nom et/ou prenom vide");
		} else {
			try {
				return this.clientDao.create(client);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public Client findById(int id) throws ServiceException {
		try {
			return this.clientDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public long delete(Client client) throws ServiceException {
		try {
			return this.clientDao.delete(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long count() throws ServiceException {
		try {
			return this.clientDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
