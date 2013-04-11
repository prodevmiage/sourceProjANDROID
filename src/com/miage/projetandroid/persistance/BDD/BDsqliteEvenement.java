package com.miage.projetandroid.persistance.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BDsqliteEvenement extends SQLiteOpenHelper {

	private static final String TABLE_EVENEMENT = "EVENEMENTS";
	private static final String COL_ID = "ID";
	private static final String COL_NOM = "NOM";
	private static final String COL_DESCRIPTION = "DESCRIPTION";
	private static final String COL_PUBLICATION = "PUBLICATION";
	private static final String COL_IDTYPEEVT = "IDTYPEEVT";
	private static final String COL_LOCALISATION = "LOCALISATION";
	private static final String COL_POSITIONGPS = "POSITIONGPS";
	private static final String COL_IDZONE = "IDZONE";
	private static final String COL_CONTACT_TEL = "CONTACT_TEL";
	private static final String COL_CONTACT_MAIL = "CONTACT_MAIL";
	private static final String COL_INFOCONTACT = "INFOCONTACT";
	private static final String COL_WEB = "WEB";
	private static final String COL_NATURE = "NATURE";
	private static final String COL_TARIF = "TARIF";
	private static final String COL_PHOTO = "PHOTO";
 
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_EVENEMENT + " ("
	+ COL_ID + " INTEGER PRIMARY KEY, " + COL_NOM + " TEXT NOT NULL, "
	+ COL_DESCRIPTION + " TEXT NOT NULL, " + COL_PUBLICATION + " INTEGER, "+ COL_IDTYPEEVT + " INTEGER, "
	+ COL_LOCALISATION + " TEXT, "+ COL_POSITIONGPS + " TEXT, "+ COL_IDZONE + " INTEGER, "
	+ COL_CONTACT_TEL + " TEXT, "+ COL_CONTACT_MAIL + " TEXT, "+ COL_INFOCONTACT + " TEXT, "
	+ COL_WEB + " TEXT, "+ COL_NATURE + " TEXT, "+ COL_TARIF + " TEXT, "
	+ COL_PHOTO + " TEXT);";
	
	public BDsqliteEvenement(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//on créé la table à partir de la requête écrite dans la variable CREATE_BDD
		db.execSQL(CREATE_BDD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		//On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
		//comme ça lorsque je change la version les id repartent de 0
		db.execSQL("DROP TABLE " + TABLE_EVENEMENT + ";");
		onCreate(db);
	}
}
