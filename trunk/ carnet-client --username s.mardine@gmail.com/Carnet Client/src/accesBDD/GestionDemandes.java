package accesBDD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Utilitaires.ParseString;

public class GestionDemandes {
	private static Vector<String> nomColonnesCommunes = new Vector<String>();
	private static Vector<String> nomColonnesMedecins = new Vector<String>();
	@SuppressWarnings("unchecked")
	private static Vector<Vector> tabLignes = new Vector<Vector>();
	
	public static Vector<String> getNomColonnesCommunes(){
		return nomColonnesCommunes;
		}
	public static Vector<String> getNomColonnesMedecins(){
		return nomColonnesMedecins;
	}
	@SuppressWarnings("unchecked")
	public static Vector<Vector> getTabLignes(){
		return tabLignes;
	}
	public static Connection laConnexion = ControleConnexion.getConnexion();
	
	public DefaultTableModel model;
	
	
	/**
	 * Lance une requete dans la table des statisitques
	 * 
	 * @param ModuleOuvert -String le nom du module lancé
	 * 
	 */
	
	
	
	/**
	 * execute une requete et retourne le resultat sous forme de champs delimité par des ; 
	 * 
	 * @param requete -String la recherche effectuée (select * from....)
	 * 
	 * @return les valeurs separées par des ;
	 * Si la requete ne retourne rien, on retourne un champ vide "" 
	 * 
	 */
	
	public static String executeRequeteEtRetourne1Champ(String requete) throws SQLException {
		// TODO Auto-generated method stub
		
		  Statement	state1 = laConnexion.createStatement();
		  ResultSet jeuEnregistrements1 = state1.executeQuery(requete);
		  ResultSetMetaData infojeuEnregistrements1 = jeuEnregistrements1.getMetaData();
		
		
		 String chaine_champ = null;
		  
		  while(jeuEnregistrements1.next()) {
		
			  for(int i=1; i <= infojeuEnregistrements1.getColumnCount(); i++) {
			  chaine_champ = jeuEnregistrements1.getString(i);
			  }
			
		  }
		  jeuEnregistrements1.close();
		  state1.close();
		  if (chaine_champ==null){
			  return "";
		  }else{
			  return chaine_champ;	  
		  }
		  
		
	}

	
	
	/**
	 * On execute simplement une requete sur la base
	 * 
	 * @param requete -String la recherche effectuée (delete, truncate...)
	 * 
	 * @return vrai si ca a marché, sinon faux
	 * 
	 * 
	 */
	public static boolean executeRequete (String requete) {
		//Connection laConnexion = ControleConnexion.getConnexion();
				
		Statement state = null;
		try {
			state = laConnexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e);
			return false;
		}
		try {
			state.executeUpdate(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e);
			try {
				Utilitaires.Historique.ecrire ("Message d'erreur: "+e +"\n\r sur la requete : " + requete);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			return false;
		}
		return true;
		
		//ControleConnexion.fermetureSession();
		
		}
	
	/**
	 * execute une requete et retourne le resultat sous forme de champs delimité par des ; 
	 * 
	 * @param requete -String la recherche effectuée (select * from....)
	 * @param BENEF_NUM_MUTUELLE le numero de complementaire de l'assuré
	 * 
	 * @return BENEF_NUM_MUTUELLE+";"+LIBELLE_AMC+";";
	 * 
	 * 
	 */
	public static boolean executeRequeteEtAfficheJComboBox(String requete,JComboBox comboBox) {
		
		
		Statement state;
		try {
			state = laConnexion.createStatement();
			ResultSet jeuEnregistrements = state.executeQuery(requete);
			ResultSetMetaData infojeuEnregistrements = jeuEnregistrements.getMetaData();
		
			
			 while(jeuEnregistrements.next()) {
				 // Vector<String> ligne = new Vector<String>();
					  for(int i=1; i <= infojeuEnregistrements.getColumnCount(); i++) {
					  String chaine_champ = jeuEnregistrements.getString(i);
					
					  
						  if ((chaine_champ!=null)&&chaine_champ.equals("")==false){
							  chaine_champ=ParseString.removevirgule(chaine_champ);
							  comboBox.addItem(chaine_champ);
						  }
					  }
						  
				    	
				  }
				  jeuEnregistrements.close();
				  state.close();
	 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				Utilitaires.Historique.ecrire ("Message d'erreur: "+e +"\n\r sur la requete : " + requete);
				return false;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return true;
		
		
			
	}
	/**
	 * execute une requete et retourne le resultat sous forme de champs delimité par des ; 
	 * 
	 * @param requete -String la recherche effectuée (select * from....)
	 * @return Valeur => contient les lignes trouvées 
	 */
	public static String executeRequeteEtRetourne1ListeDeValeur(String requete) {
		String Valeur = null;	
		
		
		// TODO Auto-generated method stub
		Statement state;
		try {
			state = laConnexion.createStatement();
			ResultSet jeuEnregistrements = state.executeQuery(requete);
			ResultSetMetaData infojeuEnregistrements = jeuEnregistrements.getMetaData();
		
			
			 while(jeuEnregistrements.next()) {
				  Vector<String> ligne = new Vector<String>();
					  for(int i=1; i <= infojeuEnregistrements.getColumnCount(); i++) {
					  String chaine_champ = jeuEnregistrements.getString(i);
					
					  if ((chaine_champ==null)||(chaine_champ.equals("")==true)){
							 if (chaine_champ==null){
								 ligne.add(chaine_champ);  
							 }
							 else {
								 ligne.add("''''");
							 }
						  }
						  if ((chaine_champ!=null)&&chaine_champ.equals("")==false){
							  chaine_champ=ParseString.removevirgule(chaine_champ);
							  ligne.add("''"+chaine_champ+"''");
						  }
					  }
						  Valeur = ligne.toString();
						  Valeur = ParseString .remplaceVirguleParPointVirgule(Valeur);
						  Valeur = ParseString.removeCrochet(Valeur);
						  Valeur = ParseString.removeParenthese(Valeur);
						  Valeur = ParseString.removeApostrophe(Valeur);
				  }
				  jeuEnregistrements.close();
				  state.close();
				  
				  return Valeur;	  
				  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				Utilitaires.Historique.ecrire ("Message d'erreur: "+e +"\n\r sur la requete : " + requete);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return Valeur;
	}
	
	/**
	 * Lance une recherche dans la base en passant une requete en parametre et affiche le resultat
	 * dans une JTable mais le nom des colonnes et leur tailles sont fixées dans le code
	 * 
	 * @param requete -String la recherche effectuée (select * from....)
	 * @param table -JTable pour l'affichage
	 * @param model -DefaultTableModel lié a la table
	 * @return nb enregistrement
	 * 
	 */
	public static  int lanceRechercheConvNumMutFirst(String requete,JTable table,DefaultTableModel model) throws SQLException {
		
		int nbClients = 0;
		int tailleCol0,tailleCol1,tailleCol2,tailleCol3,tailleCol4,tailleCol5,tailleCol6,tailleCol7,tailleCol8;
		int tailleColmax0=250,tailleColmax1=100,tailleColmax2=250,tailleColmax3=100,tailleColmax4=100,tailleColmax5=100,tailleColmax6 = 500,tailleColmax7=100,tailleColmax8=10;
		  Statement	state = laConnexion.createStatement();
		  
		  ResultSet jeuEnregistrements = state.executeQuery(requete);
		  ResultSetMetaData infojeuEnregistrements = jeuEnregistrements.getMetaData();
		
		  if (model.getColumnCount()==0){//si il n'y a pas deja des colonnes on les crées
			 
			  model.addColumn("Nom");
			  model.addColumn("Adresse");
			  model.addColumn("Code Postal");
			  model.addColumn ("Ville");
			  model.addColumn("Tel Fixe");
			  model.addColumn("Tel Portable");
			  model.addColumn("Secteur");
			  model.addColumn("Commentaire(s)");
			  model.addColumn ("Prénom");
			  model.addColumn("Num");
			  
			  
			  
		  }
		 
		  
		  while(jeuEnregistrements.next()) {
		  Vector<String> ligne = new Vector<String>();
			  for(int i=1; i <= infojeuEnregistrements.getColumnCount(); i++) {
			  String chaine_champ = jeuEnregistrements.getString(i);
			  ligne.add(chaine_champ);
			  
			  
			  	
				  if (i==1){
					  tailleCol0=chaine_champ.length();
					  if (tailleColmax0 < tailleCol0){
						  tailleColmax0=tailleCol0;
					  }
				  }
				  if (i==2){
					  tailleCol1=chaine_champ.length();
					  if (tailleColmax1 < tailleCol1){
						  tailleColmax1=tailleCol1;
					  } 
				  }
				  
				  if (i==3){
					  tailleCol3=chaine_champ.length();
					  if (tailleColmax3 < tailleCol3){
						  tailleColmax3=tailleCol3;
					  }	  
				  }
				  if (i==4){
					  tailleCol4=chaine_champ.length();
					  if (tailleColmax4 < tailleCol4){
						  tailleColmax4=tailleCol4;
					  }  
				  }
				  if (i==5){
					  tailleCol5=chaine_champ.length();
					  if (tailleColmax5 < tailleCol5){
						  tailleColmax5=tailleCol5;
					  } 
				  }
				  if (i==6){
					  tailleCol6=chaine_champ.length();
					  if (tailleColmax6 < tailleCol6){
						  tailleColmax6=tailleCol6;
					  }
				  }
				  if (i==7){
					  tailleCol7=chaine_champ.length();
					  if (tailleColmax7 < tailleCol7){
						  tailleColmax7=tailleCol7;
					  }
				  }
				  if (i==8){
					  tailleCol8=chaine_champ.length();
					  if (tailleColmax8 < tailleCol8){
						  tailleColmax8=tailleCol8;
					  }
				  }
				  
				  if (i==9){
					  tailleCol2=chaine_champ.length();
					  if (tailleColmax2 < tailleCol2){
						  tailleColmax2=tailleCol2;
					  }
				  }
			  
			  }
			 model.insertRow (0, ligne);//on insert les lignes
			 tabLignes.add(ligne);
			  
		  nbClients = nbClients + 1;
		  }
		  table.getColumnModel().getColumn(0).setPreferredWidth(50);
		  table.getColumnModel().getColumn(1).setPreferredWidth(tailleColmax0);
		  table.getColumnModel().getColumn(2).setPreferredWidth(tailleColmax1);
		  table.getColumnModel().getColumn(3).setPreferredWidth(tailleColmax2);
		  table.getColumnModel().getColumn(4).setPreferredWidth(tailleColmax3);
		  table.getColumnModel().getColumn(5).setPreferredWidth(tailleColmax4);
		  table.getColumnModel().getColumn(6).setPreferredWidth(tailleColmax5);
		  table.getColumnModel().getColumn(7).setPreferredWidth(tailleColmax6);
		  table.getColumnModel().getColumn(8).setPreferredWidth(tailleColmax7);
		  table.getColumnModel().getColumn(9).setPreferredWidth(tailleColmax8);
		  
		  jeuEnregistrements.close();
		  state.close();
		 
		  return nbClients;	
	}
	
	


}
