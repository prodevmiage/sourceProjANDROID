package com.miage.projetandroid.persistance;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.miage.projetandroid.model.Evenement;

public class EvenementController extends WebService{
    
	private static final String DL_URL_EVENEMENTS = "http://ftpaurelie.onlyonenooz.net/BaseDonnees/evenements.json";

	public String contenuFic = "";
	
	private ObjectMapper objectMapper = null;
    private JsonFactory jsonFactory = null;
    private JsonParser jp = null;
    private ArrayList<Evenement> listeEvt = null;
    private Evenement evt = null;
    
    public EvenementController(){
        objectMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }
    
    public ArrayList<Evenement> initEvenement() {
        try{
        	contenuFic = httpGetMethod(DL_URL_EVENEMENTS);
            jp = jsonFactory.createJsonParser(contenuFic);
            evt = objectMapper.readValue(jp, Evenement.class);
            listeEvt = evt.getListeEvts();
        } catch(JsonParseException e) {
            e.printStackTrace();
        }catch(ClientProtocolException e){
        	e.printStackTrace();
    	}catch(IOException e) {
            e.printStackTrace();
        }
        return listeEvt;
    }
}
