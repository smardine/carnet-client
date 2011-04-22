package Utilitaires;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class Copy {
	// Dans le contructeur on va utiliser notre methode copy
	// et donc on vas faire quelques ptit test
	protected String src,dest;
	File DEST,SRC;
	int nbTotal;
	int nbDerreur=0;
	/**
	 * Copie le contenu d'un repertoire vers un autre et affiche le status de la copie dans une barre de progression.
	 * @param src -String Le r�pertoire source
	 * @param dest -String le repertoire de destination
	 * @param nbTotal -int le nb total de fichier a copier qui permet de calculer la progression.
	 * @param progress -JProgressBar la barre de progression.
	 * @param sortieModel -DefaultModelList model de liste
	 * @param sortieList -JList le composant JList.
	 */
	
	public Copy (String src, String dest,final int nbTotal,final JProgressBar progressTotal,final String RepRacineLocal,final JLabel label){

		
		this.nbTotal=nbTotal;
		this.src=src;
		this.dest=dest;
		this.SRC = new File (src);
		this.DEST = new File (dest);
		// ben si le rep dest n'existe pas, et notre source est un repertoire
		if (!DEST.exists()){
			if (SRC.isDirectory()){
			// Alors on cree un rep destination
				try {
					Historique.ecrire ("Cr�ation du r�p�rtoire : "+ DEST);
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				DEST.mkdir();
				//DEST.deleteOnExit();
				//nbEncours++;
			}			
		}
		// Mais si jammais c'est un fichier, on fait un simple copie
		if (SRC.isFile()){
			if (src.indexOf(".exe")!=-1){
				FixeDateSystemeALaDateDeCreationDuFichier (src);
				try {
					Historique.ecrire ("Copie de : " + SRC + "  vers : " + DEST);
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			
			boolean succes = copyAvecProgress(SRC, DEST,progressTotal);
			if (succes==false){
				nbDerreur++;
				try {
					Historique.ecrire("Erreur lors de la copie du fichier : "+SRC+" vers : "+DEST );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			
			//long date = dateFichier.getFileDateTime(src);
			//DEST.setLastModified(date);
			//sortieModel.addElement(DEST);
			//label.setText (DEST.toString());
			
		// et si notre source est un repertoire qu'on doit copi�!!! 
		}else if (SRC.isDirectory()){
		// on parcour tout les elements de ce catalogue, 
			for (File f:SRC.listFiles()){
			// et hop on fait un appel recursif a cette classe en mettant a jour les path de src et dest: et le tour est jou�
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						/**
						 * {@inheritDoc}
						 */
						@Override public void run() {
							
							
							Comptage count = new Comptage (RepRacineLocal);
							int nbEncours =count.getNbFichier();
							
							//int nbEncours = GestionRepertoire.CompteNbFichierDansRepEtSousRep(dest);
							//int nbEncours = sortieList.getModel().getSize();;
							int PourcentProgression = (100*(nbEncours+1))/nbTotal;
							label.setText ("Copie de "+nbEncours+" fichier(s)  / sur "+nbTotal + " au total");
							/*if (nbEncours>0){
								sortieList.setSelectedIndex (nbEncours);	
								sortieList.ensureIndexIsVisible(sortieList.getSelectedIndex());
							}*/
							
							progressTotal.setValue(PourcentProgression);
							progressTotal.setString ("Total : "+PourcentProgression+" %");
							
						 }
						}) ;
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
														
				new Copy(f.getAbsolutePath(),DEST.getAbsoluteFile()+"/"+f.getName(),nbTotal,progressTotal,RepRacineLocal,label);
				
			}
		}
		
	}
	
	

	/**
	 * Permet de fixer la date systeme en fonction de la date de cr�ation d'un fichier
	 * @param cheminDuFichier -String le fichier dont on se sert pour fixer la date Systeme
	 * 
	 */
	public static void FixeDateSystemeALaDateDeCreationDuFichier(String cheminDuFichier) {
		// TODO Auto-generated method stub
		//on cr�er la commande qui servira a recuperer la date du fichier
	    
		Runtime r= Runtime.getRuntime();
		String cmdRecupDate = String.format("cmd.exe /c dir /TC %s | find \"/\"  > tmp.txt",cheminDuFichier);
	     try {
			Process p = r.exec(cmdRecupDate);
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//on extrait la date systeme du fichier text et on fixe la date systeme
		String cmdSetDate = String.format("cmd.exe /c FOR /F \"tokens=1-4 delims= \" %%i in (tmp.txt) do DATE %%i");
		 try {
				Process p = r.exec(cmdSetDate);
				try {
					p.waitFor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String cmdEffaceTmpText = String.format("cmd.exe /c del tmp.txt");
			 try {
					Process p = r.exec(cmdEffaceTmpText);
					try {
						p.waitFor();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	/** copie le fichier source dans le fichier resultat
	 * retourne vrai si cela r�ussit
	 * @param source -File fichier a copier
	 * @param destination -File destination
	 */
	/*private boolean copy( File source, File destination )
	{
	        boolean resultat = false;
	        
	        //D�claration des stream d'entree sortie
	        java.io.FileInputStream sourceFile=null;
	        java.io.FileOutputStream destinationFile=null;
	        
	        try {
	                // Cr�ation du fichier :
	                destination.createNewFile();
	                
	                // Ouverture des flux
	                sourceFile = new java.io.FileInputStream(source);
	                destinationFile = new java.io.FileOutputStream(destination);
	                
	                // Lecture par segment de 0.5Mo 
	                byte buffer[]=new byte[512*1024];
	                int nbLecture;
	                
	                while( (nbLecture = sourceFile.read(buffer)) != -1 ) {
	                        destinationFile.write(buffer, 0, nbLecture);
	                } 
	                
	                // si tout va bien
	                resultat = true;
	        } catch( java.io.FileNotFoundException f ) {
	                
	        } catch( java.io.IOException e ) {
	                
	        } finally {
	                // Quelque soit on ferme les flux
	                try {
	                        sourceFile.close();
	                } catch(Exception e) { }
	                try {
	                        destinationFile.close();
	                        
	                } catch(Exception e) { }
	        } 
	        return( resultat );
	} 
	
	*/private boolean copyAvecProgress(File sRC2, File dEST2,JProgressBar progressEnCours) {
		 boolean resultat = false;
		// long PourcentEnCours=0;
		 //progressEnCours.setValue(0);    
        // progressEnCours.setString("Fichier en cours : 0 %"); 
	        
	        //D�claration des stream d'entree sortie
	        java.io.FileInputStream sourceFile=null;
	        java.io.FileOutputStream destinationFile=null;
	        
	        try {
	                // Cr�ation du fichier :
	        		dEST2.createNewFile();
	                
	                // Ouverture des flux
	                sourceFile = new java.io.FileInputStream(sRC2);
	                destinationFile = new java.io.FileOutputStream(dEST2);
	                
	              //  long tailleTotale = sRC2.length();
	                
	                // Lecture par segment de 0.5Mo 
	                byte buffer[]=new byte[512*1024];
	                int nbLecture;
	                
	                while( (nbLecture = sourceFile.read(buffer)) != -1 ) {
	                        destinationFile.write(buffer, 0, nbLecture);
	                        //long tailleEnCours=dEST2.length();
	                       // PourcentEnCours=((100*(tailleEnCours+1))/tailleTotale);
	                        //int Pourcent=(int)PourcentEnCours;
	                       // progressEnCours.setValue(Pourcent);    
	                       // progressEnCours.setString("Fichier en cours : "+Pourcent+" %"); 
	                } 
	                
	                // si tout va bien
	                resultat = true;
	                //dEST2.deleteOnExit();
	                
	        } catch( java.io.FileNotFoundException f ) {
	                
	        } catch( java.io.IOException e ) {
	                
	        } finally {
	                // Quelque soit on ferme les flux
	                try {
	                        sourceFile.close();
	                } catch(Exception e) { }
	                try {
	                        destinationFile.close();
	                        
	                } catch(Exception e) { }
	        } 
	        return( resultat );
		
	}



	public int getNbErreur() {
		// TODO Auto-generated method stub
		return nbDerreur;
	}
}
