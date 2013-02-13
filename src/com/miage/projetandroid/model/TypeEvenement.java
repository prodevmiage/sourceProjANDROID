package com.miage.projetandroid.model;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class TypeEvenement {

	private int id;
	private String nom;
	
	@JsonProperty("TypeEvenements")
	private ArrayList<TypeEvenement> listeTypesEvt;
	
	public TypeEvenement() {
		super();
		this.id = 0;
		this.nom = "";
	}
	
	public TypeEvenement(int id, String nom) {
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

	public ArrayList<TypeEvenement> getListeTypesEvt() {
		return listeTypesEvt;
	}

	public void setListeTypesEvt(ArrayList<TypeEvenement> listeTypesEvt) {
		this.listeTypesEvt = listeTypesEvt;
	}
}
