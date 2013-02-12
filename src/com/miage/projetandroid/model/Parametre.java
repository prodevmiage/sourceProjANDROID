package com.miage.projetandroid.model;

import org.codehaus.jackson.annotate.JsonProperty;


public class Parametre {
	private String font;
	private int taille;
	private String couleur_barreTitre;
	private String couleur_policeTitre;
	private String couleur_police;
	private String couleur_bouton;
	private String couleur_theme1;
	private String couleur_theme2;
	private String couleur_theme3;
	private String couleur_theme4;
	private String couleur_theme5;
	private String couleur_theme6;
	private String couleur_theme7;
	private String titreApplication;
	private String logo;
	
	@JsonProperty("Parametre")
	private Parametre param;
	
	public Parametre (){
		super();
		this.font = "test";
		this.taille = 0;
		this.couleur_barreTitre = "test";
		this.couleur_bouton = "test";
		this.titreApplication = "test";
		this.logo = "";
	};

	public Parametre(String font, int taille, String couleur_barreTitre,
			String couleur_policeTitre, String couleur_police,
			String couleur_bouton, String couleur_theme1,
			String couleur_theme2, String couleur_theme3,
			String couleur_theme4, String couleur_theme5,
			String couleur_theme6, String couleur_theme7,
			String titreApplication, String logo, Parametre param) {
		super();
		this.font = font;
		this.taille = taille;
		this.couleur_barreTitre = couleur_barreTitre;
		this.couleur_policeTitre = couleur_policeTitre;
		this.couleur_police = couleur_police;
		this.couleur_bouton = couleur_bouton;
		this.couleur_theme1 = couleur_theme1;
		this.couleur_theme2 = couleur_theme2;
		this.couleur_theme3 = couleur_theme3;
		this.couleur_theme4 = couleur_theme4;
		this.couleur_theme5 = couleur_theme5;
		this.couleur_theme6 = couleur_theme6;
		this.couleur_theme7 = couleur_theme7;
		this.titreApplication = titreApplication;
		this.logo = logo;
		this.param = param;
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
	}

	public String getCouleur_barreTitre() {
		return couleur_barreTitre;
	}

	public void setCouleur_barreTitre(String couleur_barreTitre) {
		this.couleur_barreTitre = couleur_barreTitre;
	}

	public String getCouleur_policeTitre() {
		return couleur_policeTitre;
	}

	public void setCouleur_policeTitre(String couleur_policeTitre) {
		this.couleur_policeTitre = couleur_policeTitre;
	}

	public String getCouleur_police() {
		return couleur_police;
	}

	public void setCouleur_police(String couleur_police) {
		this.couleur_police = couleur_police;
	}

	public String getCouleur_theme1() {
		return couleur_theme1;
	}

	public void setCouleur_theme1(String couleur_theme1) {
		this.couleur_theme1 = couleur_theme1;
	}

	public String getCouleur_theme2() {
		return couleur_theme2;
	}

	public void setCouleur_theme2(String couleur_theme2) {
		this.couleur_theme2 = couleur_theme2;
	}

	public String getCouleur_theme3() {
		return couleur_theme3;
	}

	public void setCouleur_theme3(String couleur_theme3) {
		this.couleur_theme3 = couleur_theme3;
	}

	public String getCouleur_theme4() {
		return couleur_theme4;
	}

	public void setCouleur_theme4(String couleur_theme4) {
		this.couleur_theme4 = couleur_theme4;
	}

	public String getCouleur_theme5() {
		return couleur_theme5;
	}

	public void setCouleur_theme5(String couleur_theme5) {
		this.couleur_theme5 = couleur_theme5;
	}

	public String getCouleur_theme6() {
		return couleur_theme6;
	}

	public void setCouleur_theme6(String couleur_theme6) {
		this.couleur_theme6 = couleur_theme6;
	}

	public String getCouleur_theme7() {
		return couleur_theme7;
	}

	public void setCouleur_theme7(String couleur_theme7) {
		this.couleur_theme7 = couleur_theme7;
	};
	
	
	
}
