package Thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.mail.MessagingException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import lecture_ecriture.ReadFile;
import zip.OutilsZip;
import Dialogue.Fen_Download;
import Dialogue.SendMailUsingAuthenticationWithAttachement;
import Utilitaires.GestionRepertoire;
import Utilitaires.Historique;
import Utilitaires.RecupDate;
import Utilitaires.VariableEnvironement;
import accesBDD.GestionDemandes;

public class Thread_VerifMajEnLigne extends Thread{
	
	protected String urlversion,urlsetup;
	protected JProgressBar progress;
	protected JLabel operation2;
	protected JTextField messageAFairePasser;

	public Thread_VerifMajEnLigne(String urlVersionIni, String urlSetupExe,JProgressBar progressBar,JLabel operation,JTextField Message) 
	{
		// TODO Auto-generated constructor stub
		
		urlversion=urlVersionIni;
		urlsetup=urlSetupExe;
		progress=progressBar;
		operation2=operation;
		messageAFairePasser = Message;
	}
	
		public void run() {
			InputStream input = null;
			FileOutputStream writeFile=null;	
			String cheminFichier=null;
			File fichier = null;
			int erreurOuverture=0;
			messageAFairePasser.setText(" Vérification de la présence d'une mise à jour");
			File setup = new File (GestionRepertoire.RecupRepTravail()+"\\setupCarnetClient.exe");
			if (setup.exists()==true){
				boolean effacé = setup.delete();
				if (effacé==false){
					setup.deleteOnExit();
				}
			}
			
			
				try
				{
					messageAFairePasser.setText(" Récuperation de la version disponible sur le site");
					URL url = new URL(urlversion);
					URLConnection connection = url.openConnection();
					final int fileLength = connection.getContentLength();

					if ((fileLength == -1)||(fileLength==0))
					{
						System.out.println("Invalide URL or file.");
						erreurOuverture++;
						//return false;
					}
					
					input = connection.getInputStream();
					String fileName = url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
					if (fileName.contains("%20")==true){
						fileName=fileName.replaceAll("%20", " ");
					}
					if (fileName.contains("&amp;")==true){
						fileName=fileName.replaceAll("&amp;", " and ");
					}
					cheminFichier=GestionRepertoire.RecupRepTravail()+"\\"+fileName;
					
					fichier = new File (cheminFichier);
					writeFile = new FileOutputStream(cheminFichier);
					//lecture par segment de 4Mo
					byte[] buffer = new byte[4096*1024];
					int read;

					while ((read = input.read(buffer)) > 0){
						writeFile.write(buffer, 0, read);
														
					}
						
					
					writeFile.flush();
				}
				catch (IOException e)
				{
					System.out.println("Error while trying to download the file.");
					e.printStackTrace();
					
				}
				finally
				{
					try
					{
						if (erreurOuverture==0){
							writeFile.close();
							input.close();	
						}
						
					}
					catch (IOException e)
					{
						e.printStackTrace();
						//return false;
					}
				}
				//le telech est fini, on verifie la version hebergée sur le site
				ini_Manager.ConfigMgt versionSite = null;
				try {
					versionSite = new ini_Manager.ConfigMgt ("version.ini",GestionRepertoire.RecupRepTravail()+"\\",'[');
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String VersionDispo =versionSite.getValeurDe("version");
				ini_Manager.ConfigMgt versionInstallée = null;
				try {
					versionInstallée = new ini_Manager.ConfigMgt ("version.ini",GestionRepertoire.RecupRepTravail()+"\\IniFile\\",'[');
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				String VersionEnCours = versionInstallée.getValeurDe("version");
				boolean effacé = fichier.delete();
				if (effacé==false){
					fichier.deleteOnExit();
				}
				
				messageAFairePasser.setText(" Comparaison avec la version actuelle");
				
				if (VersionEnCours.equals(VersionDispo)==true){//la version est a jour, on regarde si on vient de faire une maj, il y a des fichier dans le repertoire "export
					try {
						ImportSQLDansBDD (GestionRepertoire.RecupRepTravail()+"\\export\\");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if (VersionEnCours.equals(VersionDispo)==false){//il y a une autre version sur le site de maj
																//proposition de la maj a l'utilisateur
					int demandeMaj = JOptionPane.showConfirmDialog(null, 
							"La version "+VersionDispo+" est disponible\n" +
					" Voulez vous faire la mise à jour");		//si il repond oui, dl du setup puis execution
																//si il repond non => poursuite du programme
					if (demandeMaj==0){//maj acceptée
					
						
						/////////////////////////////////////////////////////
						///// ENVOI FICHIER TRACE AU SUPPORT A LA FERMETURE//
						/////////////////////////////////////////////////////
						
						String [] destinataire = {"s.mardine@gmail.com"};
						String from = "carnet.client@laposte.net";
						String password = "gouranga08";
						String [] Files = {GestionRepertoire.RecupRepTravail()+"\\historique.txt",GestionRepertoire.RecupRepTravail()+"\\IniFile\\version.ini",GestionRepertoire.RecupRepTravail()+"\\IniFile\\AccesBdd.ini"};
						String Sujet = "Mise à jour carnet client";
						
						String MACHINE_NAME=VariableEnvironement.VarEnvSystem("COMPUTERNAME");
						String USERNAME=VariableEnvironement.VarEnvSystem("USERNAME");
						
											
						
						
						String Message = "L'ordinateur "+ MACHINE_NAME + "à accépté la mise à jour.\n\r" +
								"L'utilisateur qui a lancé la mise à jour est : " + USERNAME +"\n\r" +
										"La version téléchargée est : "+VersionDispo;
						SendMailUsingAuthenticationWithAttachement smtpMailSender = new SendMailUsingAuthenticationWithAttachement();
					    boolean succesEnvoiMail = false;
						try {
							succesEnvoiMail = smtpMailSender.postMail( destinataire, Sujet, Message, from, password, Files);
						} catch (MessagingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						if (succesEnvoiMail==false){//il y a eu un pb lors de l'envoi, on re essaye une fois
							
							try {
								succesEnvoiMail = smtpMailSender.postMail( destinataire, Sujet, Message, from, password, Files);
							} catch (MessagingException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
						
						
						
						new Fen_Download();
						
						
					}
					if (demandeMaj==1){//maj refusée
										
						
					}
					
					
				
			}
				//return true;
			
	
		}

		private void ImportSQLDansBDD(String chemin) throws IOException {
		// TODO Auto-generated method stub
		File Client = new File (chemin+"\\Client.export");
		File Catégorie = new File (chemin+"\\Categorie.export");
		File Document = new File (chemin+"\\Document.export");
		File Secteur = new File (chemin+"\\Secteur.export");
		
		if (Client.exists()==true||Catégorie.exists()==true||Document.exists()==true||Secteur.exists()==true){
			String Date = RecupDate.dateEtHeure();
			String FileName = Date+"_ImportCarnetClient.zip";
			File RepArchive = new File (GestionRepertoire.RecupRepTravail()+"\\archives\\");
			if (RepArchive.exists()==false){
				RepArchive.mkdirs();
			}
			try {
				OutilsZip.zipDir(chemin, GestionRepertoire.RecupRepTravail()+"\\archives\\"+FileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		if (Secteur.exists()==true){
			String DeleteTable = "delete from SECTEUR";
			boolean succes = GestionDemandes.executeRequete(DeleteTable);
			if (succes==true){
				int nbligneAimporter = ReadFile.compteNbLigne(chemin+"\\Secteur.export");
				int nbDimport = ReadFile.SecteurReadLineEtInsereEnBase(chemin+"\\Secteur.export",nbligneAimporter,progress);
				if (nbDimport==nbligneAimporter){//on a eu autant d'import que de ligne a importer, tt s'est donc bien passé.
					boolean succes1 = Secteur.delete();
					if (succes1==false){
						Secteur.deleteOnExit();
					}
					Historique.ecrire(nbDimport +"Secteur(s) importé(s)");
				}
			}
			
			
			
		}
		if (Catégorie.exists()==true){
			String DeleteTable = "delete from CATEGORIE";
			boolean succes = GestionDemandes.executeRequete(DeleteTable);
			if (succes==true){
				int nbligneAimporter = ReadFile.compteNbLigne(chemin+"\\Categorie.export");
				int nbDimport = ReadFile.CatégorieReadLineEtInsereEnBase(chemin+"\\Categorie.export",nbligneAimporter,progress);
				if (nbDimport==nbligneAimporter){//on a eu autant d'import que de ligne a importer, tt s'est donc bien passé.
					boolean succes1 = Catégorie.delete();
					if (succes1==false){
						Catégorie.deleteOnExit();
					}
					Historique.ecrire(nbDimport +"Catégorie(s) importée(s)");
				}
			}
			

		}
		if (Document.exists()==true){
			String DeleteTable = "delete from DOCUMENTS";
			boolean succes = GestionDemandes.executeRequete(DeleteTable);
			if (succes==true){
				int nbligneAimporter = ReadFile.compteNbLigne(chemin+"\\Document.export");
				int nbDimport = ReadFile.DocumentsReadLineEtInsereEnBase(chemin+"\\Document.export",nbligneAimporter,progress);
				if (nbDimport==nbligneAimporter){//on a eu autant d'import que de ligne a importer, tt s'est donc bien passé.
					boolean succes1 = Document.delete();
					if (succes1==false){
						Document.deleteOnExit();
					}	
					Historique.ecrire(nbDimport +"Document(s) importé(s)");
				}
			}
			
			
			
		}
		if (Client.exists()==true){
			String DeleteTable = "delete from CLIENT";
			boolean succes = GestionDemandes.executeRequete(DeleteTable);
			if (succes==true){
				int nbligneAimporter = ReadFile.compteNbLigne(chemin+"\\Client.export");
				int nbDimport = ReadFile.ClientReadLineEtInsereEnBase(chemin+"\\Client.export",nbligneAimporter,progress);
				
				if (nbDimport==nbligneAimporter){//on a eu autant d'import que de ligne a importer, tt s'est donc bien passé.
					boolean succes1 = Client.delete();//on efface le fichier est on passe a la suite
					if (succes1==false){
						Client.deleteOnExit();
					}
					Historique.ecrire(nbDimport +"Client(s) importé(s)");
				}	
			}
			
			
			
		}
		
		
	}

}
