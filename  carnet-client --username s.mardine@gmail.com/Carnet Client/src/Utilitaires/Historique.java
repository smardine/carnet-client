package Utilitaires;


import java.io.FileWriter;
import java.io.IOException;


public class Historique {
	/**
	 * ecrire une info dans le fichier historique.txt
	 * @param Text -String L'info souhaitée.
	 */
	public static void ecrire (String Text) throws IOException{
		String Date = RecupDate.date();
		String repTravail = GestionRepertoire.RecupRepTravail();
		String ligne = "Le " +Date+"   "+Text+"\r\n" ;
		
		
		FileWriter writer = null;
		try{
		     writer = new FileWriter(repTravail+"/historique.txt", true);
		     writer.write(ligne,0,ligne.length());
		   
		     
		}catch(IOException ex){
		    ex.printStackTrace();
		}finally{
		  if(writer != null){
		     writer.close();
		  }
		}
		
		
	}
	/**
	 * ouvrir le fichier historique.txt avec le programme par defaut du systeme.
	 * 
	 */
	public static void lire (){
		String repTravail = GestionRepertoire.RecupRepTravail();
		OpenWithDefaultViewer.open(repTravail+"/historique.txt");
	}

}
