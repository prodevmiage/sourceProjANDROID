package com.miage.projetandroid.activity;

import android.app.Activity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miage.projetandroid.R;
import com.miage.projetandroid.model.Evenement;
import com.miage.projetandroid.model.Parametre;
import com.miage.projetandroid.model.TypeEvenement;
import com.miage.projetandroid.model.Zone;
import com.miage.projetandroid.persistance.EvenementController;
import com.miage.projetandroid.persistance.ParametreController;
import com.miage.projetandroid.persistance.TypeEvenementController;
import com.miage.projetandroid.persistance.ZoneController;

import android.R.integer;
import android.R.string;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ResultatRechercheActivity extends Activity {
	
	
	 private LinearLayout layoutApplication1;
	 private ParametreController paramController;
	 private EvenementController evtController;
	 private ZoneController zoneController;
	 private TypeEvenementController typeEvtController;
	 private TextView titreApplication1;
	 private ImageView logo1;
	 private LinearLayout barreTitre1;
	 private LinearLayout fondEcran1;
	 private ImageButton boutonHome1;
	 private ImageButton boutonRecherche1;
	 private EditText barreRechercher1;;
	 private ListView listeViewEvtAlaUne1;
	 private TextView titreEvt;
	 private Parametre paramApplication;
	 private ArrayList<Evenement> malisteEvt;
	 private String id_evenement;
	 ArrayList<HashMap<String, String>> listItemEvtAlaUne1 = new ArrayList<HashMap<String, String>>();	    
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_resultat_recherche);
	

	 //Initialisation des controllers
	paramController = new ParametreController();
	evtController = new EvenementController();
	zoneController = new ZoneController();
	typeEvtController = new TypeEvenementController();
	
	
	//Initialisation des composants d'Žcran
	layoutApplication1 = (LinearLayout) findViewById(R.id.layoutApplication1);
	titreApplication1 = (TextView) findViewById(R.id.titre_application1);
	logo1 = (ImageView) findViewById(R.id.logo1);
	barreTitre1 = (LinearLayout) findViewById(R.id.layoutBarreTitre1);
	fondEcran1 = (LinearLayout) findViewById(R.id.layoutApplication1);
	boutonHome1 = (ImageButton) findViewById(R.id.boutonHome1);
	boutonRecherche1 = (ImageButton) findViewById(R.id.boutonAccesRecherche1);
	listeViewEvtAlaUne1 = (ListView) findViewById(R.id.listeEvenementUne1);
	barreRechercher1 = (EditText) findViewById(R.id.barre_rechercher_evenement1);
	titreEvt = (TextView) findViewById(R.id.titreEvenement1);
	
	Bundle infoNom = this.getIntent().getExtras();
	if(infoNom != null){
	id_evenement = infoNom.getString("id_evenement");
	titreEvt.setText(infoNom.getString("nom_evenement"));
	}
	
	
	
	

	//bouton rechercher
	boutonRecherche1.setOnClickListener(new OnClickListener() { 
		public void onClick(View v) {
			
			listItemEvtAlaUne1.clear();
			String texte = barreRechercher1.getText().toString();
			gestionAffichagefiltre(malisteEvt, texte);

			} 
	});
	
	
	
	//lancement de la rŽcupŽration des donnŽes
	gettingJson();
	}
	
	
	//fonction permettant de rŽcupŽrer les donnŽes contenues dans les fichiers Json
    final void gettingJson() {
        final Thread checkUpdate = new Thread() {
            public void run() {
            	final Parametre p = paramController.initParametre();
            	final ArrayList<Evenement> listeEvt = evtController.initEvenement();
            	//final ArrayList<Zone> liste = zoneController.initZone();
            	runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    	parametreComposant(p);
                    	gestionAffichage(listeEvt);
                    	malisteEvt = listeEvt;
                    	
                    	
                    		
                    }
                });
            }
        };
        checkUpdate.start();
        }
    
    //fonction qui permet de param�trer l'application
	private void parametreComposant(Parametre p) {
		barreTitre1.setBackgroundColor(Color.parseColor(p.getCouleur_barreTitre()));
    	fondEcran1.setBackgroundColor(Color.parseColor(p.getCouleur_fond()));
    	titreApplication1.setText(p.getTitreApplication());
        titreApplication1.setTextSize(p.getTaillePoliceTitre());
        titreApplication1.setTextColor(Color.parseColor(p.getCouleur_policeTitre()));  
        boutonHome1.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));
        boutonRecherche1.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));    
        
        //setImage(logo1,"http://www.google.fr/intl/en_com/images/srpr/logo1w.png");
   
	}
	


	
	private void gestionAffichage(ArrayList<Evenement> listetypeEvt) {
		
		HashMap<String, String> map;

		for(int i = 0; i < listetypeEvt.size(); i++)
		if(Integer.toString(listetypeEvt.get(i).getIdTypeEvt()).equals(id_evenement))
		{
			
				map = new HashMap<String, String>();
				map.put("titre", listetypeEvt.get(i).getNom());
				map.put("description", listetypeEvt.get(i).getDescription());
				map.put("img",  String.valueOf(R.drawable.ic_launcher));
				listItemEvtAlaUne1.add(map);
		}
		
		//CrŽation d'un SimpleAdapter qui se chargera de mettre les items prŽsents dans notre list (listItem) dans la vue
		SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItemEvtAlaUne1,
				R.layout.affichageitemrecherche,	new String[] {"img","titre", "description"}, 
				new int[] {R.id.img,R.id.titre, R.id.description});

		//On attribue ˆ notre listView l'adapter que l'on vient de crŽer
		listeViewEvtAlaUne1.setAdapter(mSchedule);
		
	}
	
	
	
	
private void gestionAffichagefiltre(ArrayList<Evenement> listetypeEvt, String texte) {
		
		HashMap<String, String> map;

		for(int i = 0; i < listetypeEvt.size(); i++)
		if(Integer.toString(listetypeEvt.get(i).getIdTypeEvt()).equals(id_evenement))
		if ((listetypeEvt.get(i).getNom().indexOf(texte,0)!=-1) ||  (listetypeEvt.get(i).getDescription().indexOf(texte,0)!=-1))
		{
			
				map = new HashMap<String, String>();
				map.put("titre", listetypeEvt.get(i).getNom());
				map.put("description", listetypeEvt.get(i).getDescription());
				
				listItemEvtAlaUne1.add(map);
		}
		
		//CrŽation d'un SimpleAdapter qui se chargera de mettre les items prŽsents dans notre list (listItem) dans la vue
		SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItemEvtAlaUne1,
				R.layout.affichageitemhome,	new String[] {"titre", "description"}, 
				new int[] {R.id.titre, R.id.description});

		//On attribue ˆ notre listView l'adapter que l'on vient de crŽer
		listeViewEvtAlaUne1.setAdapter(mSchedule);
		
	}
	
	

}
