package com.miage.projetandroid.activity;


import java.util.ArrayList;
import java.util.HashMap;

import com.miage.projetandroid.R;
import com.miage.projetandroid.controller.EvenementAdapter;
import com.miage.projetandroid.controller.EvenementAdapter.EvenementAdapterListener;
import com.miage.projetandroid.model.Evenement;
import com.miage.projetandroid.model.Parametre;
import com.miage.projetandroid.persistance.EvenementController;
import com.miage.projetandroid.persistance.ParametreController;
import com.miage.projetandroid.persistance.TypeEvenementController;
import com.miage.projetandroid.persistance.ZoneController;
import com.miage.projetandroid.util.Constante;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity implements EvenementAdapterListener{

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
	 private ListView listeViewEvtAlaUne;
	 private Parametre param = new Parametre();
    	 
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
		titreApplication = (TextView) findViewById(R.id.titre_application);
		logo = (ImageView) findViewById(R.id.logo);
		barreTitre = (LinearLayout) findViewById(R.id.layoutBarreTitre);
		fondEcran = (LinearLayout) findViewById(R.id.layoutApplication);
		boutonHome = (ImageButton) findViewById(R.id.boutonHome);
		boutonRecherche = (ImageButton) findViewById(R.id.boutonAccesRecherche);
		
		//gère l'évènement déclenché au click sur le bouton home
	    // cela provoque la récupération des données et l'affichage de la page home
		boutonHome.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) { gettingJson(); } 
		});
		
		//bouton rechercher
		boutonRecherche.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,RechercheActivity.class);
				startActivity(intent);
				finish();
			} 
		});
				
		//lancement de la récupération des données
		gettingJson();
    }
			
    //fonction permettant de récupérer les données contenues dans les fichiers Json
    final void gettingJson() {
        final Thread checkUpdate = new Thread() {
            public void run() {
            	param = paramController.initParametre();
            	final ArrayList<Evenement> listeEvt = evtController.initEvenement();
            	
            	runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    	parametreComposant(param);
                    	gestionAffichageEvenementAlaUne(listeEvt);
                    }
                });
            }
        };
        checkUpdate.start();
    }
    
    //fonction qui permet de paramétrer l'application
	private void parametreComposant(Parametre p) {
		/*barreTitre.setBackgroundColor(Color.parseColor(p.getCouleur_barreTitre().substring(1, p.getCouleur_barreTitre().length()-2)));
    	fondEcran.setBackgroundColor(Color.parseColor(p.getCouleur_fond().substring(1, p.getCouleur_fond().length()-2)));
    	titreApplication.setText(p.getTitreApplication().substring(1, p.getTitreApplication().length()-2));
        titreApplication.setTextSize(p.getTaillePoliceTitre());
        titreApplication.setTextColor(Color.parseColor(p.getCouleur_policeTitre().substring(1, p.getCouleur_policeTitre().length()-2)));  
        boutonHome.setBackgroundColor(Color.parseColor(p.getCouleur_bouton().substring(1, p.getCouleur_bouton().length()-2)));
        boutonRecherche.setBackgroundColor(Color.parseColor(p.getCouleur_bouton().substring(1, p.getCouleur_bouton().length()-2)));   
        */ 
		
		barreTitre.setBackgroundColor(Color.parseColor(p.getCouleur_barreTitre()));
    	fondEcran.setBackgroundColor(Color.parseColor(p.getCouleur_fond()));
    	titreApplication.setText(p.getTitreApplication());
        titreApplication.setTextSize(p.getTaillePoliceTitre());
        titreApplication.setTextColor(Color.parseColor(p.getCouleur_policeTitre()));  
        boutonHome.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));
        boutonRecherche.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));   
		
	}
	
	private void gestionAffichageEvenementAlaUne(ArrayList<Evenement> listeEvt) {
		ArrayList<Evenement> listEvtAlaUne = new ArrayList<Evenement>();
		
		//Création et initialisation de l'Adapter pour les évènements
	    for(Evenement e:listeEvt){
	    	if(e.getPublication() == 1){
	    		listEvtAlaUne.add(e);
	    	}
	    }
		
		EvenementAdapter adapter = new EvenementAdapter(this, listeEvt);
	     
		//Ecoute des évènements sur la liste
	    adapter.addListener(this);
		
	    //Récupération du composant ListView
		listeViewEvtAlaUne = (ListView) findViewById(R.id.listeEvenementUne);
	        
	    //Initialisation de la liste avec les données
		listeViewEvtAlaUne.setAdapter(adapter);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClickEvenement(Evenement item, int position) {
		Intent intent = new Intent(MainActivity.this, FicheDescEvenementActivity.class);
		Bundle evenementBundle = new Bundle();
		//mapping dans un objet bundle des information sur l'évènement sélectionné
		evenementBundle.putInt(Constante.EXTRA_EVT_ID, item.getId());
		evenementBundle.putString(Constante.EXTRA_EVT_NOM, item.getNom());
		evenementBundle.putString(Constante.EXTRA_EVT_DESCRIPTION, item.getDescription());
		evenementBundle.putInt(Constante.EXTRA_EVT_PUBLICATION, item.getPublication());
		evenementBundle.putInt(Constante.EXTRA_EVT_IDTYPEEVT, item.getIdTypeEvt());
		evenementBundle.putString(Constante.EXTRA_EVT_LOCALISATION, item.getLocalisation());
		evenementBundle.putString(Constante.EXTRA_EVT_POSITIONGPS, item.getPositionGPS());
		evenementBundle.putInt(Constante.EXTRA_EVT_IDZONE, item.getIdZone());
		evenementBundle.putString(Constante.EXTRA_EVT_CONTACT_TEL, item.getContact_tel());
		evenementBundle.putString(Constante.EXTRA_EVT_CONTACT_MAIL, item.getContact_mail());
		evenementBundle.putString(Constante.EXTRA_EVT_INFOCONTACT, item.getInfoContact());
		evenementBundle.putString(Constante.EXTRA_EVT_WEB, item.getWeb());
		evenementBundle.putString(Constante.EXTRA_EVT_NATURE, item.getNature());
		evenementBundle.putString(Constante.EXTRA_EVT_TARIF, item.getTarif());
		evenementBundle.putString(Constante.EXTRA_EVT_PHOTO, item.getPhoto());
		
		Bundle parametreBundle = new Bundle();
		//mapping dans un objet bundle des informations sur le paramétrage de l'application
		parametreBundle.putString(Constante.EXTRA_PARAM_FONTTITRE, param.getFontTitre());
		parametreBundle.putString(Constante.EXTRA_PARAM_FONTTEXTE, param.getFontTexte());
		parametreBundle.putInt(Constante.EXTRA_PARAM_TAILLEPOLICETITRE, param.getTaillePoliceTitre());
		parametreBundle.putInt(Constante.EXTRA_PARAM_TAILLEPOLICETEXTE, param.getTaillePoliceTexte());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_BARRETITRE, param.getCouleur_barreTitre());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_FOND, param.getCouleur_fond());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_POLICETITRE, param.getCouleur_policeTitre());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_POLICE, param.getCouleur_police());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_BOUTON, param.getCouleur_bouton());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_THEME1, param.getCouleur_theme1());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_THEME2, param.getCouleur_theme2());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_THEME3, param.getCouleur_theme3());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_THEME4, param.getCouleur_theme4());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_THEME5, param.getCouleur_theme5());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_THEME6, param.getCouleur_theme6());
		parametreBundle.putString(Constante.EXTRA_PARAM_COULEUR_THEME7, param.getCouleur_theme7());
		parametreBundle.putString(Constante.EXTRA_PARAM_TITREAPPLICATION, param.getTitreApplication());
		parametreBundle.putString(Constante.EXTRA_PARAM_LOGO, param.getLogo());
		
		intent.putExtra(Constante.EXTRA_EVENEMENT, evenementBundle);
		intent.putExtra(Constante.EXTRA_PARAMETRE, parametreBundle);
		
		startActivity(intent);
		finish();
	}
}
