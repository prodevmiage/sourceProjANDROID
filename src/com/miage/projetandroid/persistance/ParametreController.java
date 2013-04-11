package com.miage.projetandroid.persistance;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.miage.projetandroid.model.Parametre;

public class ParametreController extends WebService{
	//variable contenant l'url sur laquelle on a déposé notre fichier
	private static final String DL_URL_PARAMETRE = "http://ftpaurelie.onlyonenooz.net/BaseDonnees/reglages.json";
	
	public String contenuFic = "";
	
	private ObjectMapper objectMapper = null;
    private JsonFactory jsonFactory = null;
    private JsonParser jp = null;
    private Parametre param = null;
    
    public ParametreController(){
        objectMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }
    
    public Parametre initParametre() {
        try{
        	contenuFic = httpGetMethod(DL_URL_PARAMETRE);
            jp = jsonFactory.createJsonParser(contenuFic);
            param = objectMapper.readValue(jp, Parametre.class);
        } catch(JsonParseException e) {
            e.printStackTrace();
        }catch(ClientProtocolException e){
        	e.printStackTrace();
    	}catch(IOException e) {
            e.printStackTrace();
        }
        return param.getParam();
    }
    
    public void majBDD_Parametre(){
    	
    }
}
