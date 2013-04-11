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
	 private Parametre paramApplication;
	 private ArrayList<TypeEvenement> malistetypeEvt;
	 ArrayList<HashMap<String, String>> listItemEvtAlaUne1 = new ArrayList<HashMap<String, String>>();	    
   	
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
	layoutApplication1 = (LinearLayout) findViewById(R.id.layoutApplication1);
	titreApplication1 = (TextView) findViewById(R.id.titre_application1);
	logo1 = (ImageView) findViewById(R.id.logo1);
	barreTitre1 = (LinearLayout) findViewById(R.id.layoutBarreTitre1);
	fondEcran1 = (LinearLayout) findViewById(R.id.layoutApplication1);
	boutonHome1 = (ImageButton) findViewById(R.id.boutonHome1);
	boutonRecherche1 = (ImageButton) findViewById(R.id.boutonAccesRecherche1);
	listeViewEvtAlaUne1 = (ListView) findViewById(R.id.listeEvenementUne1);
	barreRechercher1 = (EditText) findViewById(R.id.barre_rechercher_evenement1);
	
	//bouton rechercher
	boutonRecherche1.setOnClickListener(new OnClickListener() { 
		public void onClick(View v) {
			
			listItemEvtAlaUne1.clear();
			String texte = barreRechercher1.getText().toString();
			gestionAffichageEvenementfiltre(malistetypeEvt, texte);

			} 
	});
	
	
	
	//lancement de la rŽcupŽration des donnŽes
			gettingJson();
			
			
			//Enfin on met un écouteur d'évènement sur notre listView
			listeViewEvtAlaUne1.setOnItemClickListener(new OnItemClickListener() {
	        @Override
	        @SuppressWarnings("unchecked")
	        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
		        //on récupère la HashMap contenant les infos de notre item (titre, description, img)
		        HashMap<String, String> map = (HashMap<String, String>)
		        listeViewEvtAlaUne1.getItemAtPosition(position);
		    	Intent intent=new Intent(RechercheActivity.this,ResultatRechercheActivity.class);
		        //je renvoi l'id de l'element selectionné
		        for(int i = 0; i <malistetypeEvt.size(); i++)
		        {
		        	int id_evt = malistetypeEvt.get(i).getId(); 
					if (malistetypeEvt.get(i).getNom()==map.get("description")) 
					{
						intent.putExtra("id_evenement",Integer.toString(id_evt));
						intent.putExtra("nom_evenement",malistetypeEvt.get(i).getNom());
						break;
					}
		        }
		    	  startActivity(intent);
		      }
			});
			
	}

	
	
	//fonction permettant de rŽcupŽrer les donnŽes contenues dans les fichiers Json
    final void gettingJson() {
        final Thread checkUpdate = new Thread() {
            public void run() {
            	final Parametre p = paramController.initParametre();
            	final ArrayList<TypeEvenement> listetypeEvt = typeEvtController.initTypeEvenement();
            	//final String liste = typeEvtController.initTypeEvenement();
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
		barreTitre1.setBackgroundColor(Color.parseColor(p.getCouleur_barreTitre()));
    	fondEcran1.setBackgroundColor(Color.parseColor(p.getCouleur_fond()));
    	titreApplication1.setText(p.getTitreApplication());
        titreApplication1.setTextSize(p.getTaillePoliceTitre());
        titreApplication1.setTextColor(Color.parseColor(p.getCouleur_policeTitre()));  
        boutonHome1.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));
        boutonRecherche1.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));    
	}
	
	
private void gestionAffichage(ArrayList<TypeEvenement> listetypeEvt) {
	
		HashMap<String, String> map;

		for(int i = 0; i < listetypeEvt.size(); i++){
			
				map = new HashMap<String, String>();
				map.put("titre", "");
				map.put("description", listetypeEvt.get(i).getNom());
				listItemEvtAlaUne1.add(map);
		}
		
		//CrŽation d'un SimpleAdapter qui se chargera de mettre les items prŽsents dans notre list (listItem) dans la vue
		SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItemEvtAlaUne1,
				R.layout.affichageitemhome,	new String[] {"titre", "description"}, 
				new int[] {R.id.titre, R.id.description});
	
		//On attribue ˆ notre listView l'adapter que l'on vient de crŽer
		listeViewEvtAlaUne1.setAdapter(mSchedule);
		
	}

	private void gestionAffichageEvenementfiltre(ArrayList<TypeEvenement> listetypeEvt, String texte) {
		
		//On dŽclare la HashMap qui contiendra les informations pour un item
		HashMap<String, String> map;
		
		//liste Žv�nement ˆ afficher, on parcours la liste des Žv�nements et on affiche uniquement les actualitŽs
		for(int i = 0; i < listetypeEvt.size(); i++){
			//if(listeEvt.get(i).getIdTypeEvt()==6){
				//CrŽation d'une HashMap pour insŽrer les informations du premier item de notre listView
	
				if (listetypeEvt.get(i).getNom().indexOf(texte,0)!=-1)
				{
					map = new HashMap<String, String>();
					map.put("titre", "");
					map.put("description", listetypeEvt.get(i).getNom());
				listItemEvtAlaUne1.add(map);
				}
			//}
		}
		//CrŽation d'un SimpleAdapter qui se chargera de mettre les items prŽsents dans notre list (listItem) dans la vue
		SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItemEvtAlaUne1,
				R.layout.affichageitemhome,	new String[] {"titre", "description"}, 
				new int[] {R.id.titre, R.id.description});
	
		//On attribue ˆ notre listView l'adapter que l'on vient de crŽer
		listeViewEvtAlaUne1.setAdapter(mSchedule);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
	    getMenuInflater().inflate(R.menu.activity_main, menu);
	    return true;
	}
    
}
