package com.epf.rentmanager.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

@Service
public class ClientService {

	private static ClientDao clientDao;
//	public static ClientService instance;

//	private ClientService() {
//		this.clientDao = ClientDao.getInstance();
//	}

	private ClientService(ClientDao clientDao) {
		ClientService.clientDao = clientDao;
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
		} else if (!isLegal(client.getBirthday())) {
			throw new ServiceException("Erreur dans le service : le client doit avoir plus de 18 ans");
		} else if (!isEmailAvailable(client)) {
			throw new ServiceException("Erreur dans le service : l'email a deja ete utilise");
		}
		try {
			return clientDao.create(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public Client findById(int id) throws ServiceException {
		try {
			return clientDao.findById(id).get();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return clientDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public long delete(int id) throws ServiceException {
		try {
			return clientDao.delete(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long update(Client client) throws ServiceException {
		try {
			return clientDao.update(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public long count() throws ServiceException {
		try {
			return clientDao.count();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean isLegal(LocalDate birthday) throws ServiceException {
		try {
			return clientDao.isLegal(birthday);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isEmailAvailable(Client client) throws ServiceException {
		try {
			return clientDao.isEmailAvailable(client);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return false;
	}
}
