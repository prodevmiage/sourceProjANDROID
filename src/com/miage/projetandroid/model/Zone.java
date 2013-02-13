package com.miage.projetandroid.model;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Zone {
	
	private int id;
	private String nom;
	
	@JsonProperty("zone")
	private ArrayList<Zone> zones;
	
	public Zone() {
		super();
		this.id = 0;
		this.nom = "";
	}
	
	public Zone(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Zone> getZones() {
		return zones;
	}

	public void setZones(ArrayList<Zone> zones) {
		this.zones = zones;
	}
	
	
	
}
