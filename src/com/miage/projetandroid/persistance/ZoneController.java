package com.miage.projetandroid.persistance;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

public class ZoneController extends WebService {
	private static final String DL_URL_ZONE = "http://prodevmiage.netii.net/BaseDonnees/zone.json";

	public String contenuFic = "";
	
	private ObjectMapper objectMapper = null;
    private JsonFactory jsonFactory = null;
    private JsonParser jp = null;

    public ZoneController(){
        objectMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }
}
