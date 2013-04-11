package com.miage.projetandroid.persistance;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.miage.projetandroid.model.TypeEvenement;
import com.miage.projetandroid.model.Zone;

public class ZoneController extends WebService {
	private static final String DL_URL_ZONE = "http://ftpaurelie.onlyonenooz.net/BaseDonnees/zone.json";

	public String contenuFic = "";
	
	private ObjectMapper objectMapper = null;
    private JsonFactory jsonFactory = null;
    private JsonParser jp = null;
    private Zone zone = null;
    private ArrayList<Zone> listeZones = null;

    public ZoneController(){
        objectMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }
    
    public ArrayList<Zone>  initTypeEvenement() {
        try{
        	contenuFic = httpGetMethod(DL_URL_ZONE);        	
            jp = jsonFactory.createJsonParser(contenuFic);
            zone = objectMapper.readValue(jp, Zone.class);
            listeZones = zone.getZones();
        } catch(JsonParseException e) {
            e.printStackTrace();
        }catch(ClientProtocolException e){
        	e.printStackTrace();
    	}catch(IOException e) {
            e.printStackTrace();
        }
        return listeZones;
    }
}
