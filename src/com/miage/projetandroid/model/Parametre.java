package com.miage.projetandroid.model;

import org.codehaus.jackson.annotate.JsonProperty;


public class Parametre {
	private String font;
	private int taille;
	private String couleur;
	private String couleur_bouton;
	private String titreApplication;
	private String logo;
	
	@JsonProperty("Parametre")
	private Parametre param;
	
	public Parametre (){
		super();
		this.font = "test";
		this.taille = 0;
		this.couleur = "test";
		this.couleur_bouton = "test";
		this.titreApplication = "test";
		this.logo = "";
	};
	
	public Parametre (String unFont, int uneTaille, String uneCouleur, String uneCoulBtn, String unTitre, String unLogo){
		super();
		this.font = unFont;
		this.taille = uneTaille;
		this.couleur = uneCouleur;
		this.couleur_bouton = uneCoulBtn;
		this.titreApplication = unTitre;
		this.logo = unLogo;
	}

	public String getTitreApplication() {
		return titreApplication;
	}

	public void setTitreApplication(String titreApplication) {
		this.titreApplication = titreApplication;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getCouleur_bouton() {
		return couleur_bouton;
	}

	public void setCouleur_bouton(String couleur_bouton) {
		this.couleur_bouton = couleur_bouton;
	}

	public Parametre getParam() {
		return param;
	}

	public void setParam(Parametre param) {
		this.param = param;
	};
	
	
	
}
