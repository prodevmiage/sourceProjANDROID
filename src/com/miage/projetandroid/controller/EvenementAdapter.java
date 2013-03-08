package com.miage.projetandroid.controller;

import java.util.ArrayList;
import java.util.List;

import com.miage.projetandroid.R;
import com.miage.projetandroid.model.Evenement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EvenementAdapter extends BaseAdapter {

	// Une liste d'évènements
	private List<Evenement> listeEvenements;
	    	
	//Le contexte dans lequel est présent notre adapter
	private Context mContext;
	    	
	//Un mécanisme pour gérer l'affichage graphique depuis un layout XML
	private LayoutInflater mInflater;


	/**
	 * Interface pour écouter les évènements sur le titre d'une évènement
	 */
	public interface EvenementAdapterListener {
	    public void onClickEvenement(Evenement item, int position);
	}


	/***
	 * Contient la liste des listeners
	 */
	private ArrayList<EvenementAdapterListener> mListListener = new ArrayList<EvenementAdapterListener>();
	
	/***
	 * Pour ajouter un listener sur notre adapter
	 * @param aListener
	 */
	public void addListener(EvenementAdapterListener aListener) {
	    mListListener.add(aListener);
	}

	/***
	 * permet de prévenir tous les listeners en même temps pour diffuser une information
	 * @param item
	 * @param position
	 */
	private void sendListener(Evenement item, int position) {
	    for(int i = mListListener.size()-1; i >= 0; i--) {
	    	mListListener.get(i).onClickEvenement(item, position);
	    }
	}

	public EvenementAdapter(Context context, List<Evenement> aListE) {
	  mContext = context;
	  listeEvenements = aListE;
	  mInflater = LayoutInflater.from(mContext);
	}

	public int getCount() {
		return listeEvenements.size();
	}

	public Object getItem(int position) {
		return listeEvenements.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		  LinearLayout layoutItem;
		  //(1) : Réutilisation des layouts
		  if (convertView == null) {
			  	//Initialisation de notre item à partir du  layout XML "affichageitemhome.xml"
			    layoutItem = (LinearLayout) mInflater.inflate(R.layout.affichageitemhome, parent, false);
		  } else {
			  	layoutItem = (LinearLayout) convertView;
		  }
		  
		  //(2) : Récupération des TextView de notre layout      
		  TextView titre_evt = (TextView)layoutItem.findViewById(R.id.titre_item_evt_home);
		  TextView description_evt = (TextView)layoutItem.findViewById(R.id.description_item_evt_home);
		        
		  //(3) : Renseignement des valeurs       
		  titre_evt.setText(listeEvenements.get(position).getNom());
		  description_evt.setText(listeEvenements.get(position).getDescription());
		
		 //On mémorise la position de l' "Evenement" dans le composant textview
		  titre_evt.setTag(position);
		  //On ajoute un listener
		  titre_evt.setOnClickListener(new OnClickListener() {
		  	public void onClick(View v) {
		  		//Lorsque l'on clique sur le titre, on récupère la position de l'"Evenement"
		  		Integer position = (Integer)v.getTag();
		  				
		  		//On prévient les listeners qu'il y a eu un clic sur le TextView "titre_evt".
		  		sendListener(listeEvenements.get(position), position);
		  	}     	
		  });
		  
		  //On mémorise la position de l' "Evenement" dans le composant textview
		  description_evt.setTag(position);
		  //On ajoute un listener
		  description_evt.setOnClickListener(new OnClickListener() {
		  	public void onClick(View v) {
		  		//Lorsque l'on clique sur la description, on récupère la position de l'"Evenement"
		  		Integer position = (Integer)v.getTag();
		  				
		  		//On prévient les listeners qu'il y a eu un clic sur le TextView "description_evt".
		  		sendListener(listeEvenements.get(position), position);
		  	}     	
		  });
		  
		  //On retourne l'item créé.
		  return layoutItem;
	}
}
