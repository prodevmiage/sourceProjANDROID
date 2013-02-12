package com.miage.projetandroid.controller.controlModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class WebService{

	// services web
	protected static String httpGetMethod (String path) throws ClientProtocolException, IOException {

		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(path);	         
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
			stringBuffer.append("\n");
			lineBuffer = bufferedReader.readLine();
		}

		bufferedReader.close();

		return stringBuffer.toString();
	}

}
