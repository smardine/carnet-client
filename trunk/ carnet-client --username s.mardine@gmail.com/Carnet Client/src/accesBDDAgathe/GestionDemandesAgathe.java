package accesBDDAgathe;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import lecture_ecriture.WriteFile;
import Utilitaires.GestionRepertoire;
import Utilitaires.ParseString;

public class GestionDemandesAgathe {
	private static Vector<String> nomColonnesCommunes = new Vector<String>();
	private static Vector<String> nomColonnesMedecins = new Vector<String>();
	@SuppressWarnings("unused")
	private static Vector<String> nomColonnesMutuelles = new Vector<String>();
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
	public static Connection laConnexion = ControleConnexionAgathe.getConnexion();
	
	public DefaultTableModel model;
	
	
	

	/**
	 * Exporte les communes dans un fichier .csv 
	 * 
	 * @param requete -String la recherche effectuée (select * from....)
	 * @param model -DefaultTableModel affiche des infos pour l'utilisateur
	 * @return nb enregistrement
	 * 
	 * la requete d'export est la suivante:
	 * "select CP,VILLE,KMPLA,KMMON,KMSKI from LOCALITE where (not CP is null or not VILLE is null) group by CP,VILLE,KMPLA,KMMON,KMSKI"
	 * 
	 */
	
	public static  int RequeteExportCommune(String requete,DefaultListModel model) {
		
		int nbClients = 0;
		String CheminExportAgathe = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\";
		File fichierExport = new File (CheminExportAgathe+"exportCommunes.csv");
		if (fichierExport.exists()==true){
			fichierExport.delete();
		}
		  Statement state;
		try {
			state = laConnexion.createStatement();
			ResultSet jeuEnregistrements = state.executeQuery(requete);
			  ResultSetMetaData infojeuEnregistrements = jeuEnregistrements.getMetaData();
			
			 for( int i=1; i <= infojeuEnregistrements.getColumnCount(); i++){
				 nomColonnesCommunes.add(infojeuEnregistrements.getColumnLabel(i));
					 
				  } 
			 	String NomDesColonnes = nomColonnesCommunes.toString();
			 	NomDesColonnes = ParseString.removeCrochet(NomDesColonnes);
				//model.addElement(NomDesColonnes);
				
				 try {
					WriteFile.WriteLine(NomDesColonnes, CheminExportAgathe+"exportCommunes.csv");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			  
			  while(jeuEnregistrements.next()) {
			  Vector<String> ligne = new Vector<String>();
				  for(int i=1; i <= infojeuEnregistrements.getColumnCount(); i++) {
				  String chaine_champ = jeuEnregistrements.getString(i);
				
				  if ((chaine_champ==null)||(chaine_champ.equals("")==true)){
						 if (chaine_champ==null){
							 ligne.add(null);  
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
				  
				  String Valeur = ligne.toString();
				  Valeur = ParseString .remplaceVirguleParPointVirgule(Valeur);
				  Valeur = ParseString.removeCrochet(Valeur);
				  Valeur = ParseString.removeApostrophe(Valeur);
				//  model.addElement(Valeur);
			  try {
				WriteFile.WriteLine(Valeur, CheminExportAgathe+"exportCommunes.csv");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				  
			  nbClients = nbClients + 1;
			  }
			  jeuEnregistrements.close();
			  state.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erreur : "+ e1,
					"Erreur", JOptionPane.ERROR_MESSAGE);
			try {
				Utilitaires.Historique.ecrire ("Message d'erreur: "+e1 +"\n\r sur la requete : " + requete);
			} catch (IOException e11) {
				// TODO Auto-generated catch block
				e11.printStackTrace();
			}
			return -1;	
		}
		  
		 
		  return nbClients;	
	}
	
	/**
	 * Exporte les patients dans un fichier .csv 
	 * 
	 * @param requete -String la recherche effectuée (select * from....)
	 * @param model -DefaultTableModel affiche des infos pour l'utilisateur
	 * @return nb enregistrement
	 *  
	 *  la requete d'export est la suivante:
	 *  "SELECT ass.NUMSS,ass.NUMSSCLE," +
	*				"cg.CODECINFO,cai.NUMDES,cai.CAIPAY," +
	*				"ass.PRENOM,ass.NOM," +
	*				"pat.ORIGDROIT," +
	*				"pat.DATENAISS," +
	*				"pat.PRENOM,pat.NOM," +
	*				"pat.RANGNAISS," +
	*				"pat.ORIGDROIT," +
	*				"pat.TXC1," +
	*				"pat.LIEN," +
	*				"pat.VISVILLE,pat.VISCP," +
	*				"pat.TEL,pat.TEL2," +
	*				"pat.VISAD1,pat.VISAD2," +
	*				"mut.IDAMC,mut.NUMADH" +
	*				" from ASSURE ass " +
	*				"left join PATIENT pat on (pat.CODEASSUR=ass.CODEASSUR)" +
	*				"left join CAISSE cai on (pat.CODEC1=cai.CODECAISSE)" +
	*				"left join CAISSEGEST cg on (cai.CODECGEST=cg.CODECGEST)" +
	*				"left join MUTPAT mut on (mut.CODEPAT=pat.CODEPAT)"
	*				
	 */
	
	public static int RequeteExportDonnéesTable(String requete,String CheminExportEtNomDeFichier) {
		// TODO Auto-generated method stub
		int nbClients = 0;
		//String CheminExport = GestionRepertoire.RecupRepTravail()+"\\export\\";
		File fichierExport = new File (CheminExportEtNomDeFichier);
		if (fichierExport.exists()==true){
			fichierExport.delete();
		}
		  Statement state;
		try {
			state = laConnexion.createStatement();
			 ResultSet jeuEnregistrements = state.executeQuery(requete);
			  ResultSetMetaData infojeuEnregistrements = jeuEnregistrements.getMetaData();
			  for( int i=1; i <= infojeuEnregistrements.getColumnCount(); i++){
					 nomColonnesCommunes.add(infojeuEnregistrements.getColumnLabel(i));
						 
					  } 
				 	String NomDesColonnes = nomColonnesCommunes.toString();
				 	NomDesColonnes = ParseString.removeCrochet(NomDesColonnes);
					//model.addElement(NomDesColonnes);
					
					 try {
						WriteFile.WriteLine(NomDesColonnes, CheminExportEtNomDeFichier);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
				  
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
					  
					  String Valeur = ligne.toString();
					  Valeur = ParseString .remplaceVirguleParPointVirgule(Valeur);
					  Valeur = ParseString.removeCrochet(Valeur);
					  Valeur = ParseString.removeApostrophe(Valeur);
					  Valeur = ParseString.removeLineFeed (Valeur);
					  Valeur = ParseString.removeCageReturn (Valeur);
					//  model.addElement(Valeur);
				  try {
					WriteFile.WriteLine(Valeur,CheminExportEtNomDeFichier);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					  
				  nbClients = nbClients + 1;
				  }
				  jeuEnregistrements.close();
				  state.close();
				  if (nbClients==0){
					  boolean succes = fichierExport.delete();
					  if (succes==false){
						  fichierExport.deleteOnExit(); 
					  }
					  
				  }
				  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			JOptionPane.showMessageDialog(null, "Erreur : "+ e1,
					"Erreur", JOptionPane.ERROR_MESSAGE);
			try {
				Utilitaires.Historique.ecrire ("Message d'erreur: "+e1 +"\n\r sur la requete : " + requete);
			} catch (IOException e11) {
				// TODO Auto-generated catch block
				e11.printStackTrace();
			}
			return -1;	
		}
		 
		
		
		 
		  return nbClients;	
		
	}

	
	/**
	 * Exporte les médecins dans un fichier .csv 
	 * 
	 * @param requete -String la recherche effectuée (select * from....)
	 * @param model -DefaultTableModel affiche des infos pour l'utilisateur
	 * @return nb enregistrement
	 * 
	 * la requete d'export est la suivante:
	 * "SELECT a.MEDSECU,a.CODESPEC, a.NOM,a.PRENOM,a.TEL,a.FAX,a.CP, a.VILLE, a.AD1, a.SALARIE, a.EMAIL FROM MEDECIN a"
	 * 
	 */
	public static  int RequeteExportMedecin(String requete,DefaultListModel model) {
		
		int nbClients = 0;
		String CheminExportAgathe = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\";
		File fichierExport = new File (CheminExportAgathe+"exportMédecins.csv");
		if (fichierExport.exists()==true){
			fichierExport.delete();
		}
		
		  Statement state;
		try {
			state = laConnexion.createStatement();
			 ResultSet jeuEnregistrements = state.executeQuery(requete);
			  ResultSetMetaData infojeuEnregistrements = jeuEnregistrements.getMetaData();
			
			 for( int i=1; i <= infojeuEnregistrements.getColumnCount(); i++){
				 nomColonnesMedecins.add(infojeuEnregistrements.getColumnLabel(i));
					 
				  } 
		
				String NomDesColonnes = nomColonnesMedecins.toString();
				NomDesColonnes = ParseString.removeCrochet(NomDesColonnes);
				
				//model.addElement(NomDesColonnes);
				
				try {
					WriteFile.WriteLine(NomDesColonnes, CheminExportAgathe+"exportMédecins.csv");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			  
			  while(jeuEnregistrements.next()) {
			  Vector<String> ligne = new Vector<String>();
			
				  for(int i=1; i <= infojeuEnregistrements.getColumnCount(); i++) {
				  String chaine_champ = jeuEnregistrements.getString(i);
				 if ((chaine_champ==null)||(chaine_champ.equals("")==true)){
					 if (chaine_champ==null){
						 ligne.add(null);  
					 }
					 else {
						 ligne.add("''");
					 }
						  
								  
				  }
				  if ((chaine_champ!=null)&&chaine_champ.equals("")==false){
					  chaine_champ=ParseString.removevirgule(chaine_champ);
					  ligne.add("''"+chaine_champ+"''");
				  }
				  
				 
				 
				  }
				  
			  String Valeur = ligne.toString();
			  Valeur = ParseString .remplaceVirguleParPointVirgule(Valeur);
			  Valeur = ParseString.removeCrochet(Valeur);
			  Valeur = ParseString.removeApostrophe(Valeur);
			 
			  try {
				WriteFile.WriteLine(Valeur, CheminExportAgathe+"exportMédecins.csv");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				  
			  nbClients = nbClients + 1;
			  }
			  jeuEnregistrements.close();
			  state.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erreur : "+ e1,
			"Erreur", JOptionPane.ERROR_MESSAGE);
			try {
				Utilitaires.Historique.ecrire ("Message d'erreur: "+e1 +"\n\r sur la requete : " + requete);
			} catch (IOException e11) {
				// TODO Auto-generated catch block
				e11.printStackTrace();
			}
			return -1;	
		}
		 
		 
		  return nbClients;	
	}
	
	
	/**
	 * On execute simplement une requete sur la base
	 * 
	 * @param requete -String la requete à effectuer (delete, truncate...)
	 * 
	 * @return vrai si ca a marché, sinon faux
	 * si il y a une erreur sql, on inscrit l'erreur dans l'historique
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
			return false;
		}
		try {
			state.executeUpdate(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			try {
				Utilitaires.Historique.ecrire ("Message d'erreur: "+e);
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
	 * Exporte les mutuelles dans un fichier .csv 
	 * 
	 * @param requete -String la recherche effectuée (select * from....)
	 * @param model -DefaultTableModel affiche des infos pour l'utilisateur
	 * @return nb enregistrement
	 * 
	 * la requete d'export est la suivante:
	 * "SELECT a.MUTNUM FROM MUTPAT a"
	 * puis
	 * "SELECT a.IDAMC FROM MUTPAT a";
	 * 
	 */
	public static int RequeteExportMutuelle(String requete,DefaultListModel model){
		int nbClients = 0;
		String CheminExportAgathe = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\";
		
		  Statement state;
		try {
			state = laConnexion.createStatement();
			 ResultSet jeuEnregistrements = state.executeQuery(requete);
			  ResultSetMetaData infojeuEnregistrements = jeuEnregistrements.getMetaData();
			
			/* for( int i=1; i <= infojeuEnregistrements.getColumnCount(); i++){
				 nomColonnesMutuelles.add(infojeuEnregistrements.getColumnLabel(i));
					 
				  } 
		
				String NomDesColonnes = nomColonnesMutuelles.toString();
				NomDesColonnes = ParseString.removeCrochet(NomDesColonnes);
				
				try {
					WriteFile.WriteLine(NomDesColonnes, CheminExportAgathe+"exportMutuelles.csv");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			 
			  
			  while(jeuEnregistrements.next()) {
			  Vector<String> ligne = new Vector<String>();
			
				  for(int i=1; i <= infojeuEnregistrements.getColumnCount(); i++) {
				  String chaine_champ = jeuEnregistrements.getString(i);
				 if ((chaine_champ==null)||(chaine_champ.equals("")==true)){
					 if (chaine_champ==null){
						 ligne.add(null);  
					 }
					 else {
						 ligne.add("''");
					 }
						  
								  
				  }
				  if ((chaine_champ!=null)&&chaine_champ.equals("")==false){
					  chaine_champ=ParseString.removevirgule(chaine_champ);
					  ligne.add("''"+chaine_champ+"''");
				  }
			
				  }
				  
			  String Valeur = ligne.toString();
			  Valeur = ParseString .remplaceVirguleParPointVirgule(Valeur);
			  Valeur = ParseString.removeCrochet(Valeur);
			  Valeur = ParseString.removeApostrophe(Valeur);
			 
			  try {
				WriteFile.WriteLine(Valeur, CheminExportAgathe+"exportMutuelles.csv");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				  
			  nbClients = nbClients + 1;
			  }
			  jeuEnregistrements.close();
			  state.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erreur : "+ e1,
			"Erreur", JOptionPane.ERROR_MESSAGE);
			try {
				Utilitaires.Historique.ecrire ("Message d'erreur: "+e1 +"\n\r sur la requete : " + requete);
			} catch (IOException e11) {
				// TODO Auto-generated catch block
				e11.printStackTrace();
			}
			return -1;	
		}
		 
		 
		  return nbClients;	
	}


}
