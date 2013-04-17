package com.miage.projetandroid.activity;

import com.miage.projetandroid.R;
import com.miage.projetandroid.model.Evenement;
import com.miage.projetandroid.model.Parametre;
import com.miage.projetandroid.persistance.ParametreController;
import com.miage.projetandroid.util.Constante;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FicheDescEvenementActivity extends Activity {

	 private ParametreController paramController;
	 private TextView titreApplication;
	 private ImageView logo;
	 private LinearLayout barreTitre;
	 private LinearLayout fondEcran;
	 private ImageButton boutonHome;
	 private Evenement evtSelect;
	 private Parametre paramAppli;
	 private LinearLayout layoutBarreTitreFicheEvenement;
	 private TextView titre_vueFicheDescEvt;
	 private TextView TV_FD_nomEvt;
	 private TextView TV_FD_descEvt;
	 private ImageView IV_FD_photo;
	 private LinearLayout layoutFicheDesc_boutonLink;
	 private Button BTN_FD_telephone;
	 private Button BTN_FD_mail;
	 private Button BTN_FD_siteWeb;
	 private LinearLayout layoutFicheDesc_ComplementEvt;
	 private TextView TV_FD_infoContact;
	 private TextView TV_FD_InfoPratiquesEvt;
	 private LinearLayout layoutFicheDesc_InfoPratiqueEvt;
	 private TextView TV_FD_natureEvt;
	 private TextView TV_FD_tarifEvt;
	 private TextView TV_FD_telephone;
	 private TextView TV_FD_email;
	 private TextView TV_FD_siteweb;

	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fiche_desc_evenement);
		
		//Initialisation des composants d'écran
		titreApplication = (TextView) findViewById(R.id.FDEtitre_application);
		logo = (ImageView) findViewById(R.id.FDElogo);
		barreTitre = (LinearLayout) findViewById(R.id.FDElayoutBarreTitre);
		fondEcran = (LinearLayout) findViewById(R.id.FDElayoutApplication);
		boutonHome = (ImageButton) findViewById(R.id.boutonHome_vueFicheEvt);
		layoutBarreTitreFicheEvenement = (LinearLayout) findViewById(R.id.layoutBarreTitreFicheEvenement);
		titre_vueFicheDescEvt = (TextView) findViewById(R.id.titre_vueFicheDescEvt);
		TV_FD_nomEvt = (TextView) findViewById(R.id.TV_FD_nomEvt);
		TV_FD_descEvt = (TextView) findViewById(R.id.TV_FD_descEvt);
		IV_FD_photo = (ImageView) findViewById(R.id.IV_FD_photo);
	    layoutFicheDesc_boutonLink = (LinearLayout) findViewById(R.id.layoutFicheDesc_boutonLink);
	    TV_FD_telephone = (TextView) findViewById(R.id.TV_FD_telephone);
	    TV_FD_email = (TextView) findViewById(R.id.TV_FD_email);
	    TV_FD_siteweb = (TextView) findViewById(R.id.TV_FD_siteweb);		
		layoutFicheDesc_ComplementEvt = (LinearLayout) findViewById(R.id.layoutFicheDesc_ComplementEvt);
		TV_FD_infoContact = (TextView) findViewById(R.id.TV_FD_infoContact);
		TV_FD_InfoPratiquesEvt = (TextView) findViewById(R.id.TV_FD_InfoPratiquesEvt);
		layoutFicheDesc_InfoPratiqueEvt = (LinearLayout) findViewById(R.id.layoutFicheDesc_InfoPratiqueEvt);
		TV_FD_natureEvt = (TextView) findViewById(R.id.TV_FD_natureEvt);
		TV_FD_tarifEvt = (TextView) findViewById(R.id.TV_FD_tarifEvt);
		
		 Intent intent = getIntent();
		//si le context n'est pas vide
		if(intent != null){	
		    //récupération des informations sur l'évènement sélectionné
			evtSelect  = mappingBundleToEvenement();
	        
	        //récupération des informations sur le paramétrage de l'application
			paramAppli = mappingBundleToParametre();
			
			if(paramAppli != null){
				parametreComposant(paramAppli);
			}
			
			if(evtSelect != null){
				remplirChampsFicheDescEvt(evtSelect);
			}
		}
	
		//gère l'évènement déclenché au click sur le bouton home
	    // cela provoque la récupération des données et l'affichage de la page home
		boutonHome.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) { 
				Intent intent = new Intent(FicheDescEvenementActivity.this, MainActivity.class);
				startActivity(intent);
				finish();
			} 
		});
		
	}

	private Parametre mappingBundleToParametre() {
		Bundle parametrebundle  = this.getIntent().getBundleExtra(Constante.EXTRA_PARAMETRE);
		//On récupère les données du Bundle
        if (parametrebundle != null) {
        	paramAppli = new Parametre(parametrebundle.getString(Constante.EXTRA_PARAM_FONTTITRE),
        			parametrebundle.getString(Constante.EXTRA_PARAM_FONTTEXTE),
        			parametrebundle.getInt(Constante.EXTRA_PARAM_TAILLEPOLICETITRE, 12),
        			parametrebundle.getInt(Constante.EXTRA_PARAM_TAILLEPOLICETEXTE, 12),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_BARRETITRE),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_FOND),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_POLICETITRE),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_POLICE),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_BOUTON),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_THEME1),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_THEME2),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_THEME3),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_THEME4),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_THEME5),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_THEME6),
        			parametrebundle.getString(Constante.EXTRA_PARAM_COULEUR_THEME7),
        			parametrebundle.getString(Constante.EXTRA_PARAM_TITREAPPLICATION),
        			parametrebundle.getString(Constante.EXTRA_PARAM_LOGO));
        }else {
        	//Erreur création d'un parametre par défaut
        	paramAppli = new Parametre();
        }
        return paramAppli;
	}

	private Evenement mappingBundleToEvenement() {
		Bundle evenementbunble = this.getIntent().getBundleExtra(Constante.EXTRA_EVENEMENT);
		//On récupère les données du Bundle
        if (evenementbunble != null) {
        	evtSelect = new Evenement(evenementbunble.getInt(Constante.EXTRA_EVT_ID, 0),
        			evenementbunble.getString(Constante.EXTRA_EVT_NOM), 
        			evenementbunble.getString(Constante.EXTRA_EVT_DESCRIPTION),
        			evenementbunble.getInt(Constante.EXTRA_EVT_PUBLICATION, 0),
        			evenementbunble.getInt(Constante.EXTRA_EVT_IDTYPEEVT, 0),
        			evenementbunble.getString(Constante.EXTRA_EVT_LOCALISATION),
        			evenementbunble.getString(Constante.EXTRA_EVT_POSITIONGPS),
        			evenementbunble.getInt(Constante.EXTRA_EVT_IDZONE, 0),
        			evenementbunble.getString(Constante.EXTRA_EVT_CONTACT_TEL),
        			evenementbunble.getString(Constante.EXTRA_EVT_CONTACT_MAIL),
        			evenementbunble.getString(Constante.EXTRA_EVT_INFOCONTACT),
        			evenementbunble.getString(Constante.EXTRA_EVT_WEB),
        			evenementbunble.getString(Constante.EXTRA_EVT_NATURE),
        			evenementbunble.getString(Constante.EXTRA_EVT_TARIF),
        			evenementbunble.getString(Constante.EXTRA_EVT_PHOTO));
        	
        }else {
        	//Erreur création d'un évènement par défaut
        	evtSelect = new Evenement();
        }
		return evtSelect;
	}
    
  //fonction qui permet de paramétrer l'application
  	private void parametreComposant(Parametre p) {
  		barreTitre.setBackgroundColor(Color.parseColor(p.getCouleur_barreTitre()));
      	fondEcran.setBackgroundColor(Color.parseColor(p.getCouleur_fond()));
      	titreApplication.setText(p.getTitreApplication());
        titreApplication.setTextSize(p.getTaillePoliceTitre());
        titreApplication.setTextColor(Color.parseColor(p.getCouleur_policeTitre()));  
        boutonHome.setBackgroundColor(Color.parseColor(p.getCouleur_bouton()));
        titre_vueFicheDescEvt.setTextColor(Color.parseColor(p.getCouleur_barreTitre()));
        titre_vueFicheDescEvt.setTextSize(p.getTaillePoliceTitre()-2);
        TV_FD_telephone.setTextColor(Color.parseColor("#1E7FCB"));
        TV_FD_email.setTextColor(Color.parseColor("#1E7FCB"));
        TV_FD_siteweb.setTextColor(Color.parseColor("#1E7FCB"));

  	}
  	
	private void remplirChampsFicheDescEvt(Evenement evenementSelect) {
		TV_FD_nomEvt.setText(evenementSelect.getNom());
		TV_FD_descEvt.setText(evenementSelect.getDescription());
		//type evenement
		if(evenementSelect.getIdTypeEvt() != 6 && evenementSelect.getIdTypeEvt() != 7){
			layoutFicheDesc_ComplementEvt.setVisibility(View.VISIBLE);
			TV_FD_infoContact.setText(evenementSelect.getInfoContact());
			TV_FD_telephone.setText(evenementSelect.getContact_tel());
			TV_FD_email.setText(evenementSelect.getContact_mail());
			TV_FD_siteweb.setText(evenementSelect.getWeb());
			TV_FD_natureEvt.setText(evenementSelect.getNature());
			TV_FD_tarifEvt.setText(evenementSelect.getTarif()+" euros.");
		}else{
			layoutFicheDesc_ComplementEvt.setVisibility(View.GONE);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_fiche_desc_evenement, menu);
		return true;
	}

}
