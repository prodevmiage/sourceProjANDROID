package com.miage.projetandroid;

import java.io.IOException;

import com.miage.projetandroid.controller.ParametreController;
import com.miage.projetandroid.model.Parametre;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	 private ParametreController paramController;
	 private TextView displayJson;
	    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		paramController = new ParametreController();
		displayJson = (TextView) findViewById(R.id.textView1);
		
	    Button startParsing = (Button) findViewById(R.id.button1);
	    startParsing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gettingJson();	
			}
		});
    }
    
    final void gettingJson() {
        final Thread checkUpdate = new Thread() {
            public void run() {
            Parametre p = paramController.init();
            final StringBuilder str = new StringBuilder("param : ");
            str.append("\n").append("font : ").append(p.getFont());
            str.append("\n").append("taille : ").append(p.getTaille());
            str.append("\n").append("couleur : ").append(p.getCouleur());
            str.append("\n").append("couleur bouton : ").append(p.getCouleur_bouton());
            runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        displayJson.setText(str.toString());
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
