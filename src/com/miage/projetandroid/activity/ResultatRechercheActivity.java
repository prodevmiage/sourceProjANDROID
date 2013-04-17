package com.miage.projetandroid.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.miage.projetandroid.R;
import com.miage.projetandroid.R.id;
import com.miage.projetandroid.model.Evenement;
import com.miage.projetandroid.model.Parametre;
import com.miage.projetandroid.persistance.EvenementController;
import com.miage.projetandroid.persistance.ParametreController;
import com.miage.projetandroid.persistance.TypeEvenementController;
import com.miage.projetandroid.persistance.ZoneController;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ResultatRechercheActivity extends Activity {
	 private LinearLayout layoutApplication;
	 private ParametreController paramController;
	 private EvenementController evtController;
	 private ZoneController zoneController;
	 private TypeEvenementController typeEvtController;
	 private TextView TV_titreApplication;
	 private ImageView logo;
	 private LinearLayout barreTitre;
	 private ImageButton boutonHome;
	 private ListView LV_listeResultatRecherche;
	 private TextView TV_titreEvt;
	 private Parametre paramApplication;
	 private ArrayList<Evenement> malisteEvt;
	 private String id_evenement;
	 private ArrayList<HashMap<String, String>> listeItemResultatRecherche = new ArrayList<HashMap<String, String>>();
	 private Button boutonRetour;	    
  
	
	
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
		layoutApplication = (LinearLayout) findViewById(R.id.RESRECH_layoutApplication);
		TV_titreApplication = (TextView) findViewById(R.id.RESRECH_titre_application);
		logo = (ImageView) findViewById(R.id.RESRECH_logo);
		barreTitre = (LinearLayout) findViewById(R.id.RESRECH_layoutBarreTitre);
		boutonHome = (ImageButton) findViewById(R.id.RESRECH_boutonHome);
		boutonRetour = (Button) findViewById(id.RESRECH_btnRetourRecherche);
		LV_listeResultatRecherche = (ListView) findViewById(R.id.RESRECH_listeResultatRecherche);
		
		Bundle infoNom = this.getIntent().getExtras();
		if(infoNom != null){
			id_evenement = infoNom.getString("id_evenement");
			TV_titreEvt.setText(infoNom.getString("nom_evenement"));
		}
		
		boutonRetour.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) {
				finish();
			} 
		});
		
		//gère l'évènement déclenché au click sur le bouton home
	    // cela provoque la récupération des données et l'affichage de la page home
		boutonHome.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) { 
				Intent intent = new Intent(ResultatRechercheActivity.this, MainActivity.class);
				finish();
				startActivity(intent);
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
	   barreTitre.setBackgroundColor(Color.parseColor(p.getCouleur_barreTitre()));
	   layoutApplication.setBackgroundColor(Color.parseColor(p.getCouleur_fond()));
   	   TV_titreApplication.setText(p.getTitreApplication());
       TV_titreApplication.setTextSize(p.getTaillePoliceTitre());
   	   TV_titreApplication.setTextColor(Color.parseColor(p.getCouleur_policeTitre()));  
       boutonHome.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));
       boutonRetour.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));
       //setImage(logo1,"http://www.google.fr/intl/en_com/images/srpr/logo1w.png");
  
	}
	
	private void gestionAffichage(ArrayList<Evenement> listeEvt) {
		
		HashMap<String, String> map;

		for(int i = 0; i < listeEvt.size(); i++)
		if(Integer.toString(listeEvt.get(i).getIdTypeEvt()).equals(id_evenement))
		{
			
				map = new HashMap<String, String>();
				map.put("titre", listeEvt.get(i).getNom());
				map.put("description", listeEvt.get(i).getDescription());
				listeItemResultatRecherche.add(map);
		}
		
		//CrŽation d'un SimpleAdapter qui se chargera de mettre les items prŽsents dans notre list (listItem) dans la vue
		SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listeItemResultatRecherche,
				R.layout.affichageitemhome,	new String[] {"titre", "description"}, 
				new int[] {R.id.titre_item_evt_home, R.id.description_item_evt_home});

		//On attribue ˆ notre listView l'adapter que l'on vient de crŽer
		LV_listeResultatRecherche.setAdapter(mSchedule);	
	}
	
}
