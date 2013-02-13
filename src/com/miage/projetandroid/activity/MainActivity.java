package com.miage.projetandroid.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.miage.projetandroid.R;
import com.miage.projetandroid.model.Evenement;
import com.miage.projetandroid.model.Parametre;
import com.miage.projetandroid.persistance.EvenementController;
import com.miage.projetandroid.persistance.ParametreController;
import com.miage.projetandroid.persistance.TypeEvenementController;
import com.miage.projetandroid.persistance.ZoneController;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	 private LinearLayout layoutApplication;
	 private ParametreController paramController;
	 private EvenementController evtController;
	 private ZoneController zoneController;
	 private TypeEvenementController typeEvtController;
	 private TextView titreApplication;
	 private ImageView logo;
	 private LinearLayout barreTitre;
	 private LinearLayout fondEcran;
	 private ImageButton boutonHome;
	 private ImageButton boutonRecherche;
	 private ToggleButton boutonHorsLigne;
	 private ListView listeViewEvtAlaUne;
	 private Parametre paramApplication;
	 ArrayList<HashMap<String, String>> listItemEvtAlaUne = new ArrayList<HashMap<String, String>>();	    
    
	 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        //Initialisation des controllers
		paramController = new ParametreController();
		evtController = new EvenementController();
		zoneController = new ZoneController();
		typeEvtController = new TypeEvenementController();
		
		//Initialisation des composants d'écran
		layoutApplication = (LinearLayout) findViewById(R.id.layoutApplication);
		titreApplication = (TextView) findViewById(R.id.titre_application);
		logo = (ImageView) findViewById(R.id.logo);
		barreTitre = (LinearLayout) findViewById(R.id.layoutBarreTitre);
		fondEcran = (LinearLayout) findViewById(R.id.layoutApplication);
		boutonHome = (ImageButton) findViewById(R.id.boutonHome);
		boutonHorsLigne = (ToggleButton) findViewById(R.id.boutonHorsLigne);
		boutonRecherche = (ImageButton) findViewById(R.id.boutonAccesRecherche);
		listeViewEvtAlaUne = (ListView) findViewById(R.id.listeEvenementUne);
		
		//gère l'évènement déclenché au click sur le bouton home
	    // cela provoque la récupération des données et l'affichage de la page home
		boutonHome.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) { gettingJson(); } 
		});
		
		//lancement de la récupération des données
		gettingJson();
    }
			
    //fonction permettant de récupérer les données contenues dans les fichiers Json
    final void gettingJson() {
        final Thread checkUpdate = new Thread() {
            public void run() {
            	final Parametre p = paramController.initParametre();
            	final ArrayList<Evenement> listeEvt = evtController.initEvenement();
            	runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    	parametreComposant(p);
                    	gestionAffichageEvenementAlaUne(listeEvt);
                    }
                });
            }
        };
        checkUpdate.start();
        }
    
    //fonction qui permet de paramètrer l'application
	private void parametreComposant(Parametre p) {
		barreTitre.setBackgroundColor(Color.parseColor(p.getCouleur_barreTitre()));
    	fondEcran.setBackgroundColor(Color.parseColor(p.getCouleur_fond()));
    	titreApplication.setText(p.getTitreApplication());
        titreApplication.setTextSize(p.getTaillePoliceTitre());
        titreApplication.setTextColor(Color.parseColor(p.getCouleur_policeTitre()));  
        boutonHome.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));
        boutonRecherche.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));    
	}
	
	private void gestionAffichageEvenementAlaUne(ArrayList<Evenement> listeEvt) {
		
		//On déclare la HashMap qui contiendra les informations pour un item
		HashMap<String, String> map;
		
		//liste évènement à afficher, on parcours la liste des évènements et on affiche uniquement les actualités
		for(int i = 0; i < listeEvt.size(); i++){
			//if(listeEvt.get(i).getIdTypeEvt()==6){
				//Création d'une HashMap pour insérer les informations du premier item de notre listView
				map = new HashMap<String, String>();
				map.put("titre", listeEvt.get(i).getNom());
				map.put("description", listeEvt.get(i).getDescription());
				listItemEvtAlaUne.add(map);
			//}
		}
		//Création d'un SimpleAdapter qui se chargera de mettre les items présents dans notre list (listItem) dans la vue
		SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItemEvtAlaUne,
				R.layout.affichageitemhome,	new String[] {"titre", "description"}, 
				new int[] {R.id.titre, R.id.description});
	
		//On attribue à notre listView l'adapter que l'on vient de créer
		listeViewEvtAlaUne.setAdapter(mSchedule);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
