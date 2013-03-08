package com.miage.projetandroid.model;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

public class Evenement {
	
	@JsonProperty("Evenement")
	private ArrayList<Evenement> listeEvts;
	
	private int id;
	private String nom;
	private String description;
	//si la valeur de publication = 1, l'evenement doit etre publie
	//si la valeur de publication = 0 on ne le publie pas
	private int publication;
	private int idTypeEvt;
	private String localisation;
	private String positionGPS;
	private int idZone;
	private String contact_tel;
	private String contact_mail;
	private String infoContact;
	private String web;
	private String nature;
	private String tarif; 
	private String photo;
	
	public Evenement(){
		super();
		this.id = 0;
		this.nom = "";
		this.description = "";
		this.publication = 0;
		this.idTypeEvt = 0;
		this.localisation = "";
		this.positionGPS = "";
		this.idZone = 0;
		this.contact_tel = "";
		this.contact_mail = "";
		this.infoContact = "";
		this.web = "";
		this.nature = "";
		this.tarif = "";
		this.photo = "";
	}
	
	public Evenement(int id, String nom, String description, int publication,
			int idTypeEvt, String localisation, String positionGPS, int idZone,
			String contact_tel, String contact_mail, String infoContact,
			String web, String nature, String tarif, String photo) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.publication = publication;
		this.idTypeEvt = idTypeEvt;
		this.localisation = localisation;
		this.positionGPS = positionGPS;
		this.idZone = idZone;
		this.contact_tel = contact_tel;
		this.contact_mail = contact_mail;
		this.infoContact = infoContact;
		this.web = web;
		this.nature = nature;
		this.tarif = tarif;
		this.photo = photo;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPublication() {
		return publication;
	}

	public void setPublication(int publication) {
		this.publication = publication;
	}

	public int getIdTypeEvt() {
		return idTypeEvt;
	}

	public void setIdTypeEvt(int idTypeEvt) {
		this.idTypeEvt = idTypeEvt;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getPositionGPS() {
		return positionGPS;
	}

	public void setPositionGPS(String positionGPS) {
		this.positionGPS = positionGPS;
	}

	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public String getContact_tel() {
		return contact_tel;
	}

	public void setContact_tel(String contact_tel) {
		this.contact_tel = contact_tel;
	}

	public String getContact_mail() {
		return contact_mail;
	}

	public void setContact_mail(String contact_mail) {
		this.contact_mail = contact_mail;
	}

	public String getInfoContact() {
		return infoContact;
	}

	public void setInfoContact(String infoContact) {
		this.infoContact = infoContact;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getTarif() {
		return tarif;
	}

	public void setTarif(String tarif) {
		this.tarif = tarif;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public ArrayList<Evenement> getListeEvts() {
		return listeEvts;
	}

	public void setListeEvts(ArrayList<Evenement> listeEvts) {
		this.listeEvts = listeEvts;
	}
	
}
