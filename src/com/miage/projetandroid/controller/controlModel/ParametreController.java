package com.miage.projetandroid.controller.controlModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import com.miage.projetandroid.model.Parametre;

public class ParametreController {
	//variable contenant l'url sur laquelle on a déposé notre fichier
	private static final String DL_URL = "http://prodevmiage.netii.net/clienttest/reglages.json";
	
	public String contenuFic = "";
	
	private ObjectMapper objectMapper = null;
    private JsonFactory jsonFactory = null;
    private JsonParser jp = null;
    private Parametre param = null;
    
    public ParametreController(){
        objectMapper = new ObjectMapper();
        jsonFactory = new JsonFactory();
    }
    
    public Parametre init() {
        try{
        	contenuFic = httpGetMethod();
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

	private String httpGetMethod() throws ClientProtocolException, IOException {
			
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(DL_URL);	         
		HttpResponse httpResponse = httpClient.execute(httpGet);
		return getJsonResponse(httpResponse);
	}
	
	
	protected static String getJsonResponse(HttpResponse httpResponse) throws IOException{

		StringBuffer stringBuffer = new StringBuffer("");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		String lineBuffer = bufferedReader.readLine();

		while(lineBuffer !=null)
		{
			stringBuffer.append(lineBuffer);
			stringBuffer.append(" ");
			lineBuffer = bufferedReader.readLine();
		}

		bufferedReader.close();

		return stringBuffer.toString();
	}

	
	public Parametre getParam(){
		return param;
	}
}
