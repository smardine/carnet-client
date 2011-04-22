package lecture_ecriture;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class WriteFile {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * Ecrire le contenu d'un String dans un fichier
     * @param contenu -String ce qu'il faut ecrire
     * @param chemin -String le chemin du fichier
     * 
     */

	public WriteFile(String contenu,String chemin) {
		  
		try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(chemin));
	        out.write(contenu);
	        out.close();
	    } catch (IOException e) {
	    }
	}

	/**
     * Ecrire une ligne dans un fichier
     * @param ligneAEcrire -String ce qu'il faut ecrire
     * @param CheminDuFichier -String le chemin du fichier
     * 
     */
	public static void WriteLine(String ligneAEcrire,String CheminDuFichier ) throws IOException {
		FileWriter writer = null;
		String texte = (ligneAEcrire+"\n");
				
		try{
		writer = new FileWriter(CheminDuFichier, true);
		writer.write(texte,0,texte.length());
		
		}catch(IOException ex){
		ex.printStackTrace();
		}finally{
		if(writer != null){
		writer.close();
		}
		}
	}
	/**
     * Ecrire une ligne dans un fichier
     * @param ligneAEcrire -Vector ce qu'il faut ecrire (idem qu'un string mais valeur separée par des ;
     * @param CheminDuFichier -String le chemin du fichier
     * 
     */
	public static void WriteLineVector(Vector<String> ligneAEcrire,String CheminDuFichier) {
		// TODO Auto-generated method stub
		FileWriter writer = null;
		String texte = ("\n"+ligneAEcrire+"\n");
				
		try{
		writer = new FileWriter(CheminDuFichier, true);
		writer.write(texte,0,texte.length());
		
		}catch(IOException ex){
		ex.printStackTrace();
		}finally{
		if(writer != null){
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	}
		
	

		
		
}


