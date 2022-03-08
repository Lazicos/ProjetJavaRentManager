package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {
	private int id;
	private int clientId;
	private int vehiculeId;
	private LocalDate debut;
	private LocalDate fin;
	
	public Reservation() {
	}
	
	public Reservation(int clientId, int vehiculeId, LocalDate debut, LocalDate fin) {
		this.clientId = clientId;
		this.vehiculeId = vehiculeId;
		this.debut = debut;
		this.fin = fin;
	}
	
	public Reservation(int id, int clientId, int vehiculeId, LocalDate debut, LocalDate fin) {
		this.id = id;
		this.clientId = clientId;
		this.vehiculeId = vehiculeId;
		this.debut = debut;
		this.fin = fin;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getVehiculeId() {
		return vehiculeId;
	}

	public void setVehiculeId(int vehiculeId) {
		this.vehiculeId = vehiculeId;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public String toString() {
		return "\nid : " + id 
				+ "\nClient id : " + clientId
				+ "\nVehicle id : " + vehiculeId
				+ "\nDebut : " + debut
				+ "\nFin : " + fin;
	}
}
