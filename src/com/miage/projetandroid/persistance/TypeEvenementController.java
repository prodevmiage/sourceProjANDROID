package com.miage.projetandroid.persistance;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

public class TypeEvenementController extends WebService{
	private static final String DL_URL_TYPE_EVENEMENTS = "http://prodevmiage.netii.net/BaseDonnees/typeEvenement.json";

	public String contenuFic = "";
	
	private ObjectMapper objectMapper = null;
    private JsonFactory jsonFactory = null;
    private JsonParser jp = null;

    public TypeEvenementController(){
        objectMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }
}
