package com.epf.rentmanager.model;

public class Vehicle {
	private int id;
	private String constructeur;
	private int nbPlaces;
	
	public Vehicle() {
	}
	
	public Vehicle(int id, String constructeur, int nbPlaces) {
		this.id = id;
		this.constructeur = constructeur;
		this.nbPlaces = nbPlaces;
	}
	
	public Vehicle(String constructeur, int nbPlaces) {
		this.constructeur = constructeur;
		this.nbPlaces = nbPlaces;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConstructeur() {
		return constructeur;
	}

	public void setConstructeur(String constructeur) {
		this.constructeur = constructeur;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String toString() {
		return "id : " + id 
				+ "\nConstructeur : " + constructeur
				+ "\nNumber of places : " + nbPlaces;
	}
}
