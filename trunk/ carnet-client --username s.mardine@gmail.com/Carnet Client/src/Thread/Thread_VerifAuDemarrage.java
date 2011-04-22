package Thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import lecture_ecriture.ReadFile;
import zip.OutilsZip;
import Dialogue.FEN_Princ;
import Utilitaires.GestionRepertoire;
import Utilitaires.Historique;
import Utilitaires.RecupDate;
import Utilitaires.VariableEnvironement;
import accesBDD.ControleConnexion;





//protected TYPE xxx

public class Thread_VerifAuDemarrage extends Thread{
	//tte les decalration necessaire...
	protected JLabel operation;
	protected JTextField messageAFairePasser;
	protected JProgressBar progress;
	protected JFrame frame;
	//protected AnimatedPanel animation;
	//protected JPanel panelAnimation;
	
	/**
	 * Affiche les differentes etapes du demarrage du logiciel,
	 * verifie certaines choses.
	 * 
	 * @param Fenetre -JFrame pour l'affichage des resultats
	 * @param operation_jLabel -JLabel message pour l'utilisateur
	 * @param jTextField -JTextField message pour l'utilisateur
	 * @param jProgressBar -JProgressBar pour la progression
	 */
	
	public Thread_VerifAuDemarrage(JFrame Fenetre,JLabel operation_jLabel,JTextField jTextField,JProgressBar jProgressBar){

		//on met les equivalence ici
	
		
		operation = operation_jLabel;
		messageAFairePasser = jTextField;
		progress = jProgressBar;
		frame = Fenetre;
		
	}
	
	
	
	
	public void run(){
		
		
		//*************** Verification de la taille de l'historique ********************//
	    //**Si il depasse 1Mo, on le compresse et on le range dans le dossier "archive"*//
		//**************** avec un format de fichier historique_date.zip ***************//
		File fbTxt = new File ("./fbserver.txt");
		if (fbTxt.exists()==true){
			fbTxt.delete();
		}
		File histo = new File ("./historique.txt");
		long tailleHisto = histo.length();
		System.out.println("taille histo :" + tailleHisto/1024 +" Ko");
		if (tailleHisto>=1000000){// le fichier est superieur à 1Mo (1Million d'octet)
			operation.setText("archivage de l'historique");
			System.out.println("taille histo :" + (tailleHisto/1024)/1024 +" Mo");
			try {
				String Date = RecupDate.dateEtHeure();
				File archive = new File ("./archive");
				if (archive.exists()==false){
					archive.mkdirs();
				}
				
				String nomDuFichierGzip = "./archive/historique_"+Date+".txt.gzip";
				
				String CheminHisto = "./historique.txt";
				
				boolean succes = OutilsZip.gzip(CheminHisto,nomDuFichierGzip);
				if (succes==true){
					//on a reussi a archiver l'historique, on efface le .txt
					histo.delete();
				}
				if (succes==false){
					//on a pas reussi a archiver, on continu avec le meme fichier
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	     
		try {
			Historique.ecrire ("------------ Lancement du logiciel ------------");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
			
		//on verifie la version de java installé:
		String version = System.getProperty("java.vm.version");
		try {
			Historique.ecrire ("Version de JVM installé: " + version);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//on verifie que les drivers my sql sont installé
		try {
			Historique.ecrire ("Verification de la presence de firebird");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		operation.setText("Verification de la presence de firebird");
		//VariableEnvironement.VarEnvSystemTotal();
		String ProgramFilesdir = VariableEnvironement.VarEnvSystem("ProgramFiles");
		File fbServer = new File (ProgramFilesdir+"\\Firebird\\Firebird_2_0\\bin\\fbserver.exe");
		
		if (fbServer.exists()==true){
			try {
				Historique.ecrire ("FireBird installés");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		if (fbServer.exists()==false){//le driver odbc n'est pas installé, on l'installe
			
			try {
				Historique.ecrire ("Firebird n'est pas installés");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			operation.setText("installation des drivers SQL");
			
			String repTravail = GestionRepertoire.RecupRepTravail();
			
			String cheminDriverInstall = "\""+repTravail+"\\Database\\Firebird-2.0.3.12981-1-Win32.exe\"";
			
			try {
				Historique.ecrire ("installation de Firebird à partir de " + cheminDriverInstall);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			//on affiche la progression dans la progressBar
			progress.setValue(25);
			progress.setString(25+" %");
			Runtime r = Runtime.getRuntime();
			Process p = null;
			try {
				p = r.exec("cmd /c" + cheminDriverInstall + "/silent");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			operation.setText("Installation de Firebird Ok");
			
			try {
				Historique.ecrire ("Installation de Firebird terminée");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}
		
		
			final String urlversion = "http://downloads.sourceforge.net/project/carnetclient/setup/version.ini";
			final String urlsetup = "http://downloads.sourceforge.net/project/carnetclient/setup/setupCarnetClient.exe";
			
			
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					/**
					 * {@inheritDoc}
					 */
					@Override public void run() {
						Thread_VerifMajEnLigne majOnline = new Thread_VerifMajEnLigne (urlversion,urlsetup,progress,operation,messageAFairePasser);
						majOnline.start();
						
						
					 }
					}) ;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			
			
			
			
			
		//On verifie que le server est bien lancé.
		operation.setText("Verification du lancement de firebird");	
		try {
			Historique.ecrire ("Verification du lancement de firebird");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		String repTravail = GestionRepertoire.RecupRepTravail();
		String cmdVerifFBServer = String.format("cmd /c tasklist.exe /FI \"IMAGENAME eq fbserver.exe\" /fo list > fbserver.txt");
			
		//on affiche la progression dans la progressBar
		progress.setValue(35);
		progress.setString(35+" %");
		Runtime r = Runtime.getRuntime();
		Process p = null;
		try {
			p = r.exec(cmdVerifFBServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean fbTourne = ReadFile.FindOccurInFile(repTravail+"\\fbserver.txt", "fbserver.exe");
		if (fbTourne==true){
			operation.setText("Firebird est lancé");
			File fbTexte = new File (GestionRepertoire.RecupRepTravail()+"\\fbserver.txt");
			
			fbTexte.deleteOnExit();
			
			
			try {
				Historique.ecrire ("Firebird est lancé");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}	
		}
		if (fbTourne==false){
			try {
				Historique.ecrire ("Firebird ne tourne pas, on le lance manuellement");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			//on lance le service firebird
			Runtime r1 = Runtime.getRuntime();
			Process p1 = null;
			try {
				p1 = r1.exec("cmd.exe /c net start FirebirdGuardianDefaultInstance");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				p1.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//on a fini de lancer firebird, on verifie qu'il soit bien lancé
			Runtime r2 = Runtime.getRuntime();
			Process p2 = null;
		     String cmdVerifFBServer1 = String.format("cmd /c tasklist.exe /FI \"IMAGENAME eq fbserver.exe\" /fo list > fbserver.txt");

			try {
				p2 = r2.exec(cmdVerifFBServer1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				p2.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			fbTourne = ReadFile.FindOccurInFile(repTravail+"\\fbserver.txt", "fbserver.exe");
			if (fbTourne==false){//il y a vraiment un pb avec firebird, conseil de contacter le support
				JOptionPane.showMessageDialog(null,"Impossible de lancer le moteur de la base de donnée \n\r" +
						"veuillez contacter le developpeur",
						"Erreur SQL", JOptionPane.WARNING_MESSAGE);
				
				try {
					Historique.ecrire ("Impossible de lancer le moteur de la base de donnée \n\r" +
						"veuillez contacter le developpeur");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				File fbTexte = new File (GestionRepertoire.RecupRepTravail()+"\\fbserver.txt");
				boolean succesDelete = fbTexte.delete();
				if (succesDelete==false){
					fbTexte.deleteOnExit();
				}
				
				System.exit(0);
				
			}
			
			
		}
		
		
			
		 boolean result = false;
		try {
			result = ControleConnexion.getControleConnexion();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			try {
				Historique.ecrire ("Message d'erreur: "+e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			e.printStackTrace();
		}
			if (result==true){
				progress.setValue(100);
				progress.setString(100+" %");
				operation.setText("ConnexionReussie");
				//animation.stop();
				frame.setVisible(false);
				FEN_Princ laFenetreMenu = new FEN_Princ();
				laFenetreMenu.setVisible(true);
				try {
					Historique.ecrire ("ConnexionReussie");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					//e2.printStackTrace();
				}
			}
			if (result==false){//on est pas arrivé a se connecter a la base de donnée mysql, on essaye de se connecter a la base firebird
				JOptionPane.showMessageDialog(null, "Impossible de se connecter a la base",
						"Erreur", JOptionPane.WARNING_MESSAGE);
				//animation.stop();
				frame.setVisible(false);
				FEN_Princ laFenetreMenu = new FEN_Princ();
				laFenetreMenu.setVisible(true);
				try {
					Historique.ecrire ("Impossible de se connecter a la base");
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
				
	}



	

	public static double floor(double a, int decimales, double plus)
    {
       double p = Math.pow(10.0, decimales);
       //return Math.floor((a*p) + 0.5) / p; // avec arrondi éventuel (sans arrondi >>>> + 0.0
       return Math.floor((a*p) + plus) / p;
    }

	
}




