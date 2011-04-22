package Thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import zip.OutilsZip;
import Utilitaires.Comptage;
import Utilitaires.Copy;
import Utilitaires.FileUtility;
import Utilitaires.GestionRepertoire;
import Utilitaires.Historique;
import Utilitaires.RecupDate;
import Utilitaires.VariableEnvironement;





//protected TYPE xxx

public class Thread_Sauvegarde extends Thread{
	//tte les decalration necessaire...
	protected JLabel MESSAGE_UTILISATEUR;
	protected String EMPLACEMENT;
	protected JProgressBar PROGRESSION;
	//protected AnimatedPanel animation;
	//protected JPanel panelAnimation;
	
	/**
	 * Affiche les differentes etapes du demarrage du logiciel,
	 * verifie certaines choses.
	 * @param actionListener 
	 * 
	 * @param Fenetre -JFrame pour l'affichage des resultats
	 * @param operation_jLabel -JLabel message pour l'utilisateur
	 * @param jTextField -JTextField message pour l'utilisateur
	 * @param jProgressBar -JProgressBar pour la progression
	 */
	
	public Thread_Sauvegarde(String destination,JProgressBar progress,JLabel operation ){

		//on met les equivalence ici
	
		MESSAGE_UTILISATEUR = operation;
		EMPLACEMENT = destination;
		PROGRESSION = progress;

	}
	
	
	
	
	public void run(){
		
		String WorkingDirectory = GestionRepertoire.RecupRepTravail();
		
		String TempDirectory = VariableEnvironement.VarEnvSystem("TMP")+"\\"+RecupDate.dateEtHeure();
		
		
		
		//On cr�er le repertoire temporaire dans lequel on va stocker les diff�rents fichiers.
		File tempDirectory = new File (TempDirectory);
		if (tempDirectory.exists()==false){//si le dossier n'existe pas
			tempDirectory.mkdirs();//on cr�er le dossier
			try {
				Historique.ecrire("Cr�tion du repertoire temporaire : "+tempDirectory);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (tempDirectory.canWrite()==false){// si on ne peut pas ecrire dans le repetoire temporaire
			JOptionPane.showMessageDialog(null, "Impossible de r�aliser la sauvegarde\n\r" +
					"La creation du dossier temporaire � echou�", "Sauvegarde Impossible", JOptionPane.ERROR_MESSAGE);
			try {
				Historique.ecrire("Pb lors de la sauvegarde : La creation du dossier temporaire � echou�");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;// on arrete la sauvegarde
		}
		
		
		boolean succesCopie=false;
		Comptage count = new Comptage (WorkingDirectory);
		int nbFichierACopier =count.getNbFichier();
		try {
			Historique.ecrire("Nombre de fichier � sauvegarder :" +nbFichierACopier);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Copy save = new Copy (WorkingDirectory,TempDirectory,nbFichierACopier,PROGRESSION,TempDirectory,MESSAGE_UTILISATEUR);
		
		
		int nbderreur=save.getNbErreur();
		
		if (nbderreur!=0){
			succesCopie=false;
			try {
				Historique.ecrire("Il y a eu des pb lors de la copie des fichiers vers le repertoire temporaire ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Historique.ecrire("Nombre d'erreur lors de la copie : "+nbderreur);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			succesCopie=true;
		}

		
		boolean succesZip=false;
		
		if (succesCopie==true){//si les copies ont fonctionn�es
			
			try {
				Historique.ecrire("Copie des diff�rents fichiers dans le repertoire temporaire reussie.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
			try {
				MESSAGE_UTILISATEUR.setText("Compression en cours");
				Historique.ecrire("Archivage du dossier : "+TempDirectory+" vers le chemin : "+EMPLACEMENT);
					succesZip = OutilsZip.zipDir(TempDirectory, EMPLACEMENT,PROGRESSION,MESSAGE_UTILISATEUR);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println(e);
					JOptionPane.showMessageDialog(null, "Pb lors de la sauvegarde : \n\r"+e,
							"Erreur", JOptionPane.ERROR_MESSAGE);
					PROGRESSION.setValue(0);
					PROGRESSION.setString(0 + " %");
					MESSAGE_UTILISATEUR.setText("");
					try {
						Historique.ecrire("Pb lors de la sauvegarde : "+e);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println(e);
					JOptionPane.showMessageDialog(null, "Pb lors de la sauvegarde : \n\r"+e,
							"Erreur", JOptionPane.ERROR_MESSAGE);
					PROGRESSION.setValue(0);
					PROGRESSION.setString(0 + " %");
					MESSAGE_UTILISATEUR.setText("");
					try {
						Historique.ecrire("Pb lors de la sauvegarde : "+e);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return;
				}
				if (succesZip==true){//l'archivage a reussi, on previens l'utilisateur
					JOptionPane.showMessageDialog(null, "Sauvegarde �ffectu�e avec succ�s",
							"Sauvegarde Ok", JOptionPane.INFORMATION_MESSAGE);
					PROGRESSION.setValue(0);
					PROGRESSION.setString(0 + " %");
					MESSAGE_UTILISATEUR.setText("");
					try {
						Historique.ecrire("Sauvegarde �ffectu�e avec succ�s");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						FileUtility.recursifDelete(tempDirectory);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e);
					}
					
				
					
				}
			
			
			
		}
		else{
			JOptionPane.showMessageDialog(null, "La copie des fichiers vers le repertoire temporaire � echou�e.\n\r " +
					"Veuillez r�-essayer",
					"Erreur lors de la sauvegarde", JOptionPane.ERROR_MESSAGE);
			PROGRESSION.setValue(0);
			PROGRESSION.setString(0 + " %");
			MESSAGE_UTILISATEUR.setText("");
			return;
		}
			
	}

	
}




