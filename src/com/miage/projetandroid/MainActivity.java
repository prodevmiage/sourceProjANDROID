package com.miage.projetandroid;

import java.io.IOException;

import com.miage.projetandroid.controller.controlModel.ParametreController;
import com.miage.projetandroid.model.Parametre;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.ColorStateList;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	 private ParametreController paramController;
	 private TextView titreApplication;
	 private ImageView logo;
	 private LinearLayout barreTitre;
	    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		paramController = new ParametreController();
		titreApplication = (TextView) findViewById(R.id.titre_application);
		logo = (ImageView) findViewById(R.id.logo);
		barreTitre = (LinearLayout) findViewById(R.id.barreTitre);
		
	  /*  Button startParsing = (Button) findViewById(R.id.button1);
	    startParsing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		*/
		gettingJson();	
		/*	}
		});*/
    }
    
    final void gettingJson() {
        final Thread checkUpdate = new Thread() {
            public void run() {
            final Parametre p = paramController.init();
            runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    	barreTitre.setBackgroundColor(0xFF3C3B8E);
                    	titreApplication.setText(p.getTitreApplication());
                        titreApplication.setTextSize(p.getTaille());
                        titreApplication.setTextColor(0xFFE9EEF2);                       
                        
                    }
                });

            }
        };
        checkUpdate.start();
        }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
