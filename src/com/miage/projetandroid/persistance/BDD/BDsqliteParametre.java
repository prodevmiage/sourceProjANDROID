package com.miage.projetandroid.persistance.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BDsqliteParametre extends SQLiteOpenHelper {

	private static final String TABLE_PARAMETRE = "PARAMETRE";
	private static final String COL_FONTTITRE = "FONTTITRE";
	private static final String COL_FONTTEXTE = "FONTTEXTE";
	private static final String COL_TAILLEPOLICETITRE = "TAILLEPOLICETITRE";
	private static final String COL_TAILLEPOLICETEXTE = "TAILLEPOLICETEXTE";
	private static final String COL_COULEURBARRETITRE = "COULEURBARRETITRE";
	private static final String COL_COULEURFOND = "COULEURFOND";
	private static final String COL_COULEURPOLICETITRE = "COULEURPOLICETITRE";
	private static final String COL_COULEURPOLICE = "COULEURPOLICE";
	private static final String COL_COULEURBOUTON = "COULEURBOUTON";
	private static final String COL_COULEURTHEME1 = "COULEURTHEME1";
	private static final String COL_COULEURTHEME2 = "COULEURTHEME2";
	private static final String COL_COULEURTHEME3 = "COULEURTHEME3";
	private static final String COL_COULEURTHEME4 = "COULEURTHEME4";
	private static final String COL_COULEURTHEME5 = "COULEURTHEME5";
	private static final String COL_COULEURTHEME6 = "COULEURTHEME6";
	private static final String COL_COULEURTHEME7 = "COULEURTHEME7";
	private static final String COL_TITREAPPLICATION = "TITREAPPLICATION";
	private static final String COL_LOGO = "LOGO";

	
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_PARAMETRE + " ("
			+ COL_FONTTITRE + " TEXT, " + COL_FONTTEXTE + " TEXT, "
			+ COL_TAILLEPOLICETITRE + " INTEGER, " + COL_TAILLEPOLICETEXTE + " INTEGER, "+ COL_COULEURBARRETITRE + " TEXT, "
			+ COL_COULEURFOND + " TEXT, "+ COL_COULEURPOLICETITRE + " TEXT, "+ COL_COULEURPOLICE + " TEXT, "
			+ COL_COULEURBOUTON + " TEXT, "+ COL_COULEURTHEME1 + " TEXT, "+ COL_COULEURTHEME2 + " TEXT, "
			+ COL_COULEURTHEME3 + " TEXT, "+ COL_COULEURTHEME4 + " TEXT, "+ COL_COULEURTHEME5 + " TEXT, "
			+ COL_COULEURTHEME6 + " TEXT, "+ COL_COULEURTHEME7 + " TEXT, "+ COL_TITREAPPLICATION + " TEXT, "
			+ COL_LOGO + " TEXT);";
	
	public BDsqliteParametre(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//on créé la table à partir de la requête écrite dans la variable CREATE_BDD
				db.execSQL(CREATE_BDD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
		//comme ça lorsque je change la version les id repartent de 0
		db.execSQL("DROP TABLE " + TABLE_PARAMETRE + ";");
		onCreate(db);
	}

}
