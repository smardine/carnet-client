package lecture_ecriture;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import accesBDD.GestionDemandes;

import Utilitaires.Historique;
import Utilitaires.ParseString;




public class ReadFile {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * Lecture ligne à ligne d'un fichier texte et affichage dans une jList
     * @param chemin -String le chemin du fichier texte
     * @param listModel -DefaultModelList le model de liste
     * @param nbLigne -JLabel sert a afficher le nb de ligne
     * @param nbAdresse -int le nb d'adresse trouvée.
     */

	public static int ReadLine(String chemin, DefaultListModel listModel) {
		 
		  int nbAdresse=0;
		 			  try{
					    // Open the file that is the first 
					    // command line parameter
					    FileInputStream fstream = new FileInputStream(chemin);
					    // Get the object of DataInputStream
					    DataInputStream in = new DataInputStream(fstream);
					    BufferedReader br = new BufferedReader(new InputStreamReader(in));
					    String strLine;
					    //Read File Line By Line
					    while ((strLine = br.readLine()) != null)   {
					        	
					      // Print the content on the console
					     // System.out.println (strLine);
					      
					      if (!strLine.equals(""))
					      {
					      listModel.addElement (strLine);
					      nbAdresse++;
					      }
					    }
					    //Close the input stream
					    in.close();
					    
					    }catch (Exception e){//Catch exception if any
					      System.err.println("Error: " + e.getMessage());
					    }
					return nbAdresse;  
			  }
		
		  
		 
		
			  
	/**
     * Trouver une chaine de caracteres dans un fichier
     * @param cheminFichier -String le chemin du fichier
     * @param OccurToFind -String la chaine a trouver ex "abc@hotmail.com"
     * @return result -boolean vrai si on trouve la chaine de caracteres.
     */
	public static boolean FindOccurInFile (String cheminFichier, String OccurToFind){
		 
		String line = null;
		boolean result=false;
		
		try
		   {
		   BufferedReader br = new BufferedReader (new FileReader(cheminFichier));
		
		 int i = 1; //initialisation du numero de ligne
		 while ((line = br.readLine()) != null)
		   {
		     if ( line.indexOf(OccurToFind) != -1)
		     {
		     System.out.println("Mot trouve a la ligne " + i );
		     result=true;
		     return result;
		     }
		     i++;
		   }	   
		 br.close();
		}
		 catch(FileNotFoundException exc) { System.out.println("File not found" );  }
		 catch(IOException ioe) { System.out.println("Erreur IO" ); }
		return result;
				 
		}





	public static int compteNbLigne(String chemin) {
		int nbLigne=0;
		try{
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream = new FileInputStream(chemin);
		    // Get the object of DataInputStream
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    //Read File Line By Line
		    while ((strLine = br.readLine()) != null)   {
		      // Print the content on the console
		     // System.out.println (strLine);
		      
		      if (!strLine.equals(""))
		      {	    
		    	  nbLigne++;
		      }
		    }
		    //Close the input stream
		    in.close();
		    
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		      JOptionPane.showMessageDialog(null, "Erreur : "+ e,
						"Erreur", JOptionPane.ERROR_MESSAGE);
		      try {
					Historique.ecrire("Erreur : "+ e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      return -1;
		    }
		return nbLigne-1;  
		}





	public static int ClientReadLineEtInsereEnBase(String chemin, int nbligneAimporter,JProgressBar progress) {
		int progression=0;
		int ID_CLIENT=0;
		
		String NOM="",PRENOM="",ADRESSE="",CP="",VILLE="",TEL="",
		GSM="",COMMENTAIRES="";
		int SECTEUR=0;
		int CATEGORIE=0;
		int nbLigne=0,nbDinsert=0;
		DataInputStream in = null;
		BufferedReader br;
		 
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream;
			try {
				fstream = new FileInputStream(chemin);
				 // Get the object of DataInputStream
			    in = new DataInputStream(fstream);
			    br = new BufferedReader(new InputStreamReader(in));
			    String strLine;
			    
			    //Read File Line By Line
			    try {
					while ((strLine = br.readLine()) != null)   {
					    	
						if (nbLigne>=1){//si c'est la deuxieme ligne (ou plus) (la premiere contient les différents champs)
							strLine = strLine.replace("(", " ");
							strLine= strLine.replace(")", " ");
							strLine=strLine.replace("?","é");
							strLine = strLine.trim();
							String [] tabChaine = strLine.split(";");
							ID_CLIENT = Integer.parseInt(tabChaine[0].trim());
							NOM = tabChaine[1].trim();
							PRENOM = tabChaine[2].trim();
							ADRESSE = tabChaine[3].trim();
							CP=tabChaine[4].trim();
							VILLE=tabChaine[5].trim();
							TEL=tabChaine[6].trim();
							GSM=tabChaine[7].trim();
							COMMENTAIRES=tabChaine[8].trim();
							CATEGORIE=Integer.parseInt(tabChaine[9].trim());
							SECTEUR=Integer.parseInt(tabChaine[10].trim());
							
							COMMENTAIRES = ParseString.remplaceLineFeed (COMMENTAIRES);
							COMMENTAIRES = ParseString.remplaceCageReturn (COMMENTAIRES);
					    	    	
							String InsertClient ="INSERT INTO CLIENT " +
									"(ID_CLIENT, NOM, PNOM, ADRESSE, CP, VILLE, TEL, GSM, COMMENTAIRE, CATEGORIE, SECTEUR) " +
							"VALUES " +
							"('"+ID_CLIENT+"','"+ NOM+"','"+PRENOM+"','"+ADRESSE+"','"+CP+"','"+VILLE+"','"+TEL+"','"+GSM+"','"+COMMENTAIRES+"','"+CATEGORIE+"','"+SECTEUR+"')";
							
							Historique.ecrire("Insertion d'un client avec la requete : "+InsertClient);
							
							boolean resultInsereMutuelle = GestionDemandes.executeRequete(InsertClient);
							
							if (resultInsereMutuelle==false){
								Historique.ecrire("Insertion d'un client échoué");
								nbDinsert--;
							}else {
								Historique.ecrire("Insertion d'un client réussi");
								nbDinsert++;
							}
									

						} else nbLigne++;
									
					    	
						}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
			    	
			    	
			    	nbLigne++;
			    	progression = (100*nbLigne)/nbligneAimporter;
			    	progress.setValue (progression);
			    	progress.setString ("Import Client " +progression+" %");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		   
		    	
		    	
		    	
		    
		    //Close the input stream
		    try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		    
		    	    
		    return nbDinsert;
	}





	public static int CatégorieReadLineEtInsereEnBase(String chemin,
		int nbligneAimporter, JProgressBar progress) {
		int progression=0;
		
		String CATEGORIE="";
		int ID_CATEGORIE=0;
		int nbLigne=0,nbDinsert=0;
		DataInputStream in = null;
		BufferedReader br;
		 
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream;
			try {
				fstream = new FileInputStream(chemin);
				 // Get the object of DataInputStream
			    in = new DataInputStream(fstream);
			    br = new BufferedReader(new InputStreamReader(in));
			    String strLine;
			    
			    //Read File Line By Line
			    try {
					while ((strLine = br.readLine()) != null)   {
					    	
						if (nbLigne>=1){//si c'est la deuxieme ligne (ou plus) (la premiere contient les différents champs)
							strLine = strLine.replace("(", " ");
							strLine= strLine.replace(")", " ");
							strLine = strLine.trim();
							String [] tabChaine = strLine.split(";");
							CATEGORIE = tabChaine[0].trim();
							ID_CATEGORIE = Integer.parseInt(tabChaine[1].trim());
							
					    	    	
							String InsertCatégorie ="INSERT INTO CATEGORIE (CATEGORIE, ID_CATEGORIE) " +
									"VALUES ('"+CATEGORIE+"','"+ID_CATEGORIE+"')";
									 
							Historique.ecrire("Insertion d'une catégorie avec la requete : "+InsertCatégorie);
							
							boolean resultInsereCategorie = GestionDemandes.executeRequete(InsertCatégorie);
								if (resultInsereCategorie==false){
									Historique.ecrire("Insertion d'une catégorie echouée");
									nbDinsert--;
								}else {
									Historique.ecrire("Insertion d'une catégorie réussie");
									nbDinsert++;
								}

							}else nbLigne++;
			
						}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
			    	
			    	
			    	nbLigne++;
			    	progression = (100*nbLigne)/nbligneAimporter;
			    	progress.setValue (progression);
			    	progress.setString ("Import Categorie " +progression+" %");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		   
		    	
		    	
		    	
		    
		    //Close the input stream
		    try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		    
		    	    
		    return nbDinsert;
	}





	public static int DocumentsReadLineEtInsereEnBase(String chemin,
		int nbligneAimporter, JProgressBar progress) {
		int progression=0;
		int ID_CLIENT=0,ID_DOCUMENT=0,nbDinsert=0;
		
		
		String DOCUMENT_NOM="",EMPLACEMENT="";
		
		int nbLigne=0;
		DataInputStream in = null;
		BufferedReader br;
		 
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream;
			try {
				fstream = new FileInputStream(chemin);
				 // Get the object of DataInputStream
			    in = new DataInputStream(fstream);
			    br = new BufferedReader(new InputStreamReader(in));
			    String strLine;
			    
			    //Read File Line By Line
			    try {
					while ((strLine = br.readLine()) != null)   {
					    	
						if (nbLigne>=1){//si c'est la deuxieme ligne (ou plus) (la premiere contient les différents champs)
							strLine = strLine.replace("(", " ");
							strLine= strLine.replace(")", " ");
							strLine = strLine.trim();
							String [] tabChaine = strLine.split(";");
							DOCUMENT_NOM = tabChaine[0].trim();
							ID_CLIENT = Integer.parseInt(tabChaine[1].trim());
							EMPLACEMENT = tabChaine[2].trim();
							ID_DOCUMENT = Integer.parseInt(tabChaine[3].trim());
							
							String insertDocument ="INSERT INTO DOCUMENTS (DOCUMENT_NOM, ID_CLIENT, EMPLACEMENT, ID_DOCUMENT) VALUES ('"+DOCUMENT_NOM+"','"+ID_CLIENT+"','"+EMPLACEMENT+"','"+ID_DOCUMENT+"')";
							Historique.ecrire("Insertion d'un document avec la requete : "+insertDocument);   	
							boolean resultInsereDocument = GestionDemandes.executeRequete(insertDocument);
								if (resultInsereDocument==false){
									Historique.ecrire("Insertion d'un document échoué");
									nbDinsert--;
								}else {
									Historique.ecrire("Insertion d'un document réusssi");
									nbDinsert++;
								}

							}else nbLigne++;
									
					    	
						}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
			    	
			    	
			    	nbLigne++;
			    	progression = (100*nbLigne)/nbligneAimporter;
			    	progress.setValue (progression);
			    	progress.setString ("Import Document " +progression+" %");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		   
		    	
		    	
		    	
		    
		    //Close the input stream
		    try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		    
		    	    
		    return nbDinsert;
	}





	public static int SecteurReadLineEtInsereEnBase(String chemin,int nbligneAimporter, JProgressBar progress) {
			int progression=0;
		
		String SECTEUR="";
		int ID_SECTEUR=0;
		int nbLigne=0,nbDinsert=0;
		DataInputStream in = null;
		BufferedReader br;
		 
		    // Open the file that is the first 
		    // command line parameter
		    FileInputStream fstream;
			try {
				fstream = new FileInputStream(chemin);
				 // Get the object of DataInputStream
			    in = new DataInputStream(fstream);
			    br = new BufferedReader(new InputStreamReader(in));
			    String strLine;
			    
			    //Read File Line By Line
			    try {
					while ((strLine = br.readLine()) != null)   {
					    	
						if (nbLigne>=1){//si c'est la deuxieme ligne (ou plus) (la premiere contient les différents champs)
							strLine = strLine.replace("(", " ");
							strLine= strLine.replace(")", " ");
							strLine = strLine.trim();
							String [] tabChaine = strLine.split(";");
							SECTEUR = tabChaine[0].trim();
							ID_SECTEUR = Integer.parseInt(tabChaine[1].trim());
							
					    	    	
							String InsertSecteur ="INSERT INTO SECTEUR (SECTEUR, ID_SECTEUR) " +
									"VALUES ('"+SECTEUR+"','"+ID_SECTEUR+"')";
							
							Historique.ecrire("Insertion d'un secteur avec la requete : "+InsertSecteur);
									 
							boolean resultInsereSecteur = GestionDemandes.executeRequete(InsertSecteur);
								if (resultInsereSecteur==false){
									nbDinsert--;
									Historique.ecrire("Insertion d'un secteur echouée");
								}else {
									nbDinsert++;
									Historique.ecrire("Insertion d'un secteur réussie");
									
								}

							}
						else nbLigne++;
			
						}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return -1;
				}
			    	
			    	
			    	nbLigne++;
			    	progression = (100*nbLigne)/nbligneAimporter;
			    	progress.setValue (progression);
			    	progress.setString ("Import Secteur " +progression+" %");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		   
		    	
		    	
		    	
		    
		    //Close the input stream
		    try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		    
		    	    
		    return nbDinsert;
	}
}






	

		

	