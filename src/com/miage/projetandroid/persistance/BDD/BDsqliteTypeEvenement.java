package com.miage.projetandroid.persistance.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BDsqliteTypeEvenement extends SQLiteOpenHelper {

	private static final String TABLE_TYPEEVT = "TYPEEVENEMENT";
	private static final String COL_ID = "ID";
	private static final String COL_NOM = "NOM";
	
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_TYPEEVT + " ("
			+ COL_ID + " INTEGER, " + COL_NOM + " TEXT);";
	
	public BDsqliteTypeEvenement(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
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
		db.execSQL("DROP TABLE " + TABLE_TYPEEVT + ";");
		onCreate(db);
	}

}
