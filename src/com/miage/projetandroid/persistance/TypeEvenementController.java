package com.miage.projetandroid.persistance;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.miage.projetandroid.model.TypeEvenement;

public class TypeEvenementController extends WebService{
	private static final String DL_URL_TYPE_EVENEMENTS = "http://ftpaurelie.onlyonenooz.net/BaseDonnees/typeEvenement.json";

	public String contenuFic = "";
	
	private ObjectMapper objectMapper = null;
    private JsonFactory jsonFactory = null;
    private JsonParser jp = null;
    private TypeEvenement te = null;
    private ArrayList<TypeEvenement> listeTypeEvenement = null;

    public TypeEvenementController(){
        objectMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }
    
    
    public ArrayList<TypeEvenement>  initTypeEvenement() {
        try{
        	contenuFic = httpGetMethod(DL_URL_TYPE_EVENEMENTS);        	
            jp = jsonFactory.createJsonParser(contenuFic);
            te = objectMapper.readValue(jp, TypeEvenement.class);
            listeTypeEvenement = te.getListeTypesEvt();
        } catch(JsonParseException e) {
            e.printStackTrace();
        }catch(ClientProtocolException e){
        	e.printStackTrace();
    	}catch(IOException e) {
            e.printStackTrace();
        }
        return listeTypeEvenement;
    }

}
