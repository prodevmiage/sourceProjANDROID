package com.miage.projetandroid.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private String 		dbName;
	public 	Connection 	connection;
	private Statement	requete;
	
	/**
	 * Constructeur de la classe Database
	 * @param dbName Le nom de la base de donnes
	 */
	public Database (String dbName)
	{
		// Charge le driver sqlite JDBC en utilisant le class loader actuel
		try 
		{
			Class.forName("org.sqlite.JDBC");
		} 
		catch (ClassNotFoundException e1) 
		{
			System.err.println(e1.getMessage());
		}

		this.dbName 	= dbName;
		this.connection = null;
	}
	
	/**
	 * Constructeur par défaut de la classe Database
	 */
	public Database ()
	{
		// Charge le driver sqlite JDBC en utilisant le class loader actuel
		try 
		{
			Class.forName("org.sqlite.JDBC");
		} 
		catch (ClassNotFoundException e1) 
		{
			System.err.println(e1.getMessage());
		}
		//par defaut notre base de donnees est celle du CROUS
		this.dbName 	= "databaseAndroid.sqlite";
		try {
			//lors de l'appel du constructeur de la base de donnees on lance la connexion
			this.connection = DriverManager.getConnection("jdbc:sqlite:"+this.dbName);
			this.requete = this.connection.createStatement();
			this.requete.executeUpdate("PRAGMA synchronous = NORMAL;");
			this.requete.setQueryTimeout(30);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ouvre la base de donnees specifiee
	 * @return True si la connection a ete reussie. False sinon.
	 */
	public boolean connect ()
	{
		try
		{
			// Etabli la connection
			connection = DriverManager.getConnection("jdbc:sqlite:"+this.dbName);
			// Dclare l'objet qui permet de faire les requ?tes
			requete = connection.createStatement();
			
			// Le PRAGMA synchronous de SQLite permet de vrifier chaque criture
			// avant d'en faire une nouvelle.
			// Le PRAGMA count_changes de SQLite permet de compter le nombre de 
			// changements fait sur la base
			// Rsultats de mes tests :
			// synchronous OFF, une insertion est 20 fois plus rapide.
			// La diffrences avec le count_changes est de l'ordre de la µs.
			// Les autres PRAGMA : http://www.sqlite.org/pragma.html
			
			requete.executeUpdate("PRAGMA synchronous = NORMAL;");
			requete.setQueryTimeout(30);
			
			return true;
		}
		catch(SQLException e)
		{
			// message = "out of memory" souvent le resultat de la BDD pas trouve 
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Ferme la connection a la base de donnees
	 * @return True si la connection a bien ete fermee. False sinon.
	 */
	public boolean disconnect ()
	{
		try
		{
			if(connection != null)
				connection.close();
			
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Permet de faire une requete SQL
	 * @param requete La requete SQL (avec un ";" a la fin)
	 * Utilisé pour les requetes de type SELECT
	 * @return Un ResultSet contenant le rsultat de la requete
	 */
	public ResultSet getResultOf (String requete)
	{
		try 
		{
			return this.requete.executeQuery(requete);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Permet de modifier une entre de la base de donnes.</br>
	 * Utilisé pour les requetes de type INSERT UPDATE et DELETE
	 * @param requete La requete SQL de modification
	 */
	public void updateValue (String requete)
	{
		try 
		{
			this.requete.executeUpdate(requete);
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
