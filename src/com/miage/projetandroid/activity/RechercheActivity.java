package com.miage.projetandroid.activity;

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
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemClickListener;

public class RechercheActivity extends Activity {
	
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
	 private EditText barreRechercher;
	 private ListView listeTypeEvenement;
	 private Parametre paramApplication;
	 private ArrayList<TypeEvenement> malistetypeEvt;
	 ArrayList<HashMap<String, String>> listeItemTypeEvenement = new ArrayList<HashMap<String, String>>();	    
   	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recherche);	
		
		 //Initialisation des controllers
		paramController = new ParametreController();
		evtController = new EvenementController();
		zoneController = new ZoneController();
		typeEvtController = new TypeEvenementController();
		
		//Initialisation des composants d'Žcran
		layoutApplication = (LinearLayout) findViewById(R.id.RECHlayoutApplication);
		titreApplication = (TextView) findViewById(R.id.RECHtitre_application);
		logo = (ImageView) findViewById(R.id.RECHlogo);
		barreTitre = (LinearLayout) findViewById(R.id.RECHlayoutBarreTitre);
		boutonHome = (ImageButton) findViewById(R.id.boutonHome_vueRecherche);
		boutonRecherche = (ImageButton) findViewById(R.id.RECHboutonAccesRecherche);
		listeTypeEvenement = (ListView) findViewById(R.id.RECH_listeTypeEvenement);
		barreRechercher = (EditText) findViewById(R.id.RECHbarre_rechercher_evenement);
		
		//bouton rechercher
		/*boutonRecherche.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) {		
				listeItemTypeEvenement.clear();
				String texte = barreRechercher.getText().toString();
				gestionAffichageEvenementfiltre(malistetypeEvt, texte);
			} 
		});
		*/
		boutonRecherche.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		    	String champtexte = barreRechercher.getText().toString();
		    	//si le champs de recherche est vide on affiche un message d'erreur
		    	if(champtexte.equals("")){
		    		Toast.makeText(RechercheActivity.this,
		    				R.string.erreurzoneviderecherche,
		    				Toast.LENGTH_LONG).show();
		    	}else{
		    		//si celui ci est saisie, on appel l'activité qui gère le résultat de la recherche
			    	Intent intent=new Intent(RechercheActivity.this, ResultatRechercheActivity.class);
					intent.putExtra("typeRecherche", 1);
			    	intent.putExtra("critereRecherche",champtexte);
					intent.putExtra("id_typeEvenement","");
					intent.putExtra("nom_typeEvenement","");
					intent.putExtra("zone","");
			    	startActivity(intent);
			    	finish();
		    	}
		      }
		});
			
		
		//gère l'évènement déclenché au click sur le bouton home
	    // cela provoque la récupération des données et l'affichage de la page home
		boutonHome.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) { 
				Intent intent = new Intent(RechercheActivity.this, MainActivity.class);
				finish();
				startActivity(intent);
			} 
		});
		
		//lancement de la rŽcupŽration des donnŽes
		gettingJson();
		
		
		//Enfin on met un écouteur d'évènement sur notre listView
		listeTypeEvenement.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
		    	//on récupère les informations saisies dans la barre de recherche
		    	String champtexte = barreRechercher.getText().toString();
		    	//on récupère la HashMap contenant les infos de notre item (titre, description, img)
		        HashMap<String, String> map = (HashMap<String, String>) listeTypeEvenement.getItemAtPosition(position);
		    	Intent intent=new Intent(RechercheActivity.this, ResultatRechercheActivity.class);
		        //je renvoi l'id de l'element selectionné
		        for(int i = 0; i <malistetypeEvt.size(); i++)
		        {
		        	int id_evt = malistetypeEvt.get(i).getId(); 
					if (malistetypeEvt.get(i).getNom()==map.get("description")) 
					{
						intent.putExtra("id_typeEvenement",Integer.toString(id_evt));
						intent.putExtra("nom_typeEvenement",malistetypeEvt.get(i).getNom());
						if(!champtexte.equals("")){
							intent.putExtra("typeRecherche", 3);
							intent.putExtra("critereRecherche",champtexte);
						}else{
							intent.putExtra("typeRecherche", 2);
							intent.putExtra("critereRecherche","");
						}
						intent.putExtra("zone","");
						break;
					}
		        }
		    	  startActivity(intent);
		    	  finish();
		      }
		});
			
	}

	
	
	//fonction permettant de rŽcupŽrer les donnŽes contenues dans les fichiers Json
    final void gettingJson() {
        final Thread checkUpdate = new Thread() {
            public void run() {
            	final Parametre p = paramController.initParametre();
            	final ArrayList<TypeEvenement> listetypeEvt = typeEvtController.initTypeEvenement();
            	runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    	parametreComposant(p);
                    	gestionAffichage(listetypeEvt);
                    	malistetypeEvt = listetypeEvt;
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
    	titreApplication.setText(p.getTitreApplication());
        titreApplication.setTextSize(p.getTaillePoliceTitre());
        titreApplication.setTextColor(Color.parseColor(p.getCouleur_policeTitre()));  
        boutonHome.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));
        boutonRecherche.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));    
	}
	
	
private void gestionAffichage(ArrayList<TypeEvenement> listetypeEvt) {
	
		HashMap<String, String> map;

		for(int i = 0; i < listetypeEvt.size(); i++){
				map = new HashMap<String, String>();
				map.put("titre", listetypeEvt.get(i).getNom());
				switch (listetypeEvt.get(i).getId()){
				case 1 :
					map.put("img", String.valueOf(R.drawable.ic_restaurant));
					break;
				case 2 :
					map.put("img", String.valueOf(R.drawable.ic_location));
					break;
				case 3 :
					map.put("img", String.valueOf(R.drawable.ic_hebergement));
					break;
				case 4 :
					map.put("img", String.valueOf(R.drawable.ic_activite));
					break;
				case 5 :
					map.put("img", String.valueOf(R.drawable.ic_siteculturel));
					break;
				case 6 :
					map.put("img", String.valueOf(R.drawable.ic_actualite));
					break;
				case 7 :
					map.put("img", String.valueOf(R.drawable.ic_infopratique));
					break;
				default :
					map.put("img", String.valueOf(R.drawable.ic_launcher));
					break;
				}
				listeItemTypeEvenement.add(map);
		}
		
		//CrŽation d'un SimpleAdapter qui se chargera de mettre les items prŽsents dans notre list (listItem) dans la vue
		SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listeItemTypeEvenement,
				R.layout.affichageitemrecherche,	new String[] {"img","titre"}, 
				new int[] {R.id.imgTypeEvenement, R.id.titreTypeEvenement});
	
		//On attribue ˆ notre listView l'adapter que l'on vient de crŽer
		listeTypeEvenement.setAdapter(mSchedule);
		
	}

	private void gestionAffichageEvenementfiltre(ArrayList<TypeEvenement> listetypeEvt, String texte) {
		
		//On dŽclare la HashMap qui contiendra les informations pour un item
		HashMap<String, String> map;
		
		//liste Žv�nement ˆ afficher, on parcours la liste des Žv�nements et on affiche uniquement les actualitŽs
		for(int i = 0; i < listetypeEvt.size(); i++){
				//CrŽation d'une HashMap pour insŽrer les informations du premier item de notre listView
				if (listetypeEvt.get(i).getNom().indexOf(texte,0)!=-1)
				{
					map = new HashMap<String, String>();
					map.put("titre", "");
					map.put("description", listetypeEvt.get(i).getNom());
					listeItemTypeEvenement.add(map);
				}
		}
		//CrŽation d'un SimpleAdapter qui se chargera de mettre les items prŽsents dans notre list (listItem) dans la vue
		SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listeItemTypeEvenement,
				R.layout.affichageitemhome,	new String[] {"titre", "description"}, 
				new int[] {R.id.titre_item_evt_home, R.id.description_item_evt_home});
	
		//On attribue ˆ notre listView l'adapter que l'on vient de crŽer
		listeTypeEvenement.setAdapter(mSchedule);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
	    getMenuInflater().inflate(R.menu.activity_main, menu);
	    return true;
	}
    
}
