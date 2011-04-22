package Thread;

import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import lecture_ecriture.ReadFile;
import Animation.AnimatedPanel;
import Utilitaires.GestionRepertoire;
import Utilitaires.Historique;
import Utilitaires.ManipFichier;
import Utilitaires.OpenWithDefaultViewer;

public class Thread_ImportEquinox extends Thread {
	
	protected JTextField User,Password,emplacement,serveur;
	protected JCheckBox caseClient,caseCommune,caseMedecin,caseMutuelle;
	protected DefaultListModel log;
	protected JList liste;
	protected AnimatedPanel animation;
	protected JPanel panelAnimation;
	protected JProgressBar progress;
	protected int nbDerreur=0;
	//protected AnimatedPanel animationClient,animationReseau;
	/**
     * Import des diff�rentes donn�es ddes fichiers .csv vers Equinox
     * @param UserEquinox -JTextField nom d'utilisateur pour la connexion � la bdd
     * @param PasswordEquinox -JTextField mot de passe pour la connexion a la bdd
     * @param CheminEquinox -JTextField chemin de la base sur l'hote
     * @param Serveur - JTextField le nom du serveur qui heberge la base agathe (sous la forme 172.30.20.56 ou localhost ou MICPTDVP)
     * @param client - JCheckBox defini si on exporte ou pas
     * @param commune - JCheckBox defini si on exporte ou pas
     * @param medecin - JCheckBox defini si on exporte ou pas
     * @param mutuelle - JCheckBox defini si on exporte ou pas
     * @param text -DefaultListModel => li� a la liste
     * @param List - JList => sert a afficher des infos pour l'utilisateur
     * @param anime - AnimatedPanel => pour l'animation
     * @param panelAnime - JPanel => contient l'animation
     * @param ProgressBar - JProgressBar => affiche la progression de l'import
     * 
     */
	public Thread_ImportEquinox (JTextField UserEquinox,JTextField PasswordEquinox,JTextField CheminEquinox,JTextField Serveur,
			JCheckBox client,JCheckBox commune,JCheckBox medecin,JCheckBox mutuelles,
			DefaultListModel text,
			JList List,
			AnimatedPanel anime,
			JPanel panelAnime,
			JProgressBar ProgressBar)
			//AnimatedPanel patient,
			//AnimatedPanel reseau)
	
	{
	User=UserEquinox;
	Password=PasswordEquinox;
	emplacement=CheminEquinox;
	serveur=Serveur;
	
	caseClient=client;
	caseCommune=commune;
	caseMedecin=medecin;
	caseMutuelle=mutuelles;
	
	log=text;
	liste=List;
	animation=anime;
	panelAnimation=panelAnime;
	progress=ProgressBar;
	//animationClient=patient;
	//animationReseau=reseau;
	}
	
	public void run(){
		
		
		
		
		if ((caseClient.isSelected()==false)&&(caseCommune.isSelected()==false)&&(caseMedecin.isSelected()==false)&&(caseMutuelle.isSelected()==false)){
			//si aucune des case n'est coch�e, alors on affiche un message et on sort:
			JOptionPane.showMessageDialog(null, "Aucune option d'import n'est selectionn�e," +
					" veuillez en choisir au moins une",
					"Erreur", JOptionPane.WARNING_MESSAGE);
			log.addElement ("Aucune option d'import n'est selectionn�e, veuillez en choisir au moins une ");
			try {
				Historique.ecrire("Aucune option d'import n'est selectionn�e, veuillez en choisir au moins une");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
			return;
			
		}else {
			panelAnimation.setVisible(true);
			animation.start();
			//animationReseau.start();
			progress.setVisible(true);
		}
		
		
		if (caseMutuelle.isSelected()==true){
			//import des communes dans equinox
			log.addElement("Import des Mutuelles");
			String CheminImportAgatheMutuelle = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\exportMutuelles.csv";
			File mutuelleAgathe = new File (CheminImportAgatheMutuelle);
				if (mutuelleAgathe.exists()==false){
					JOptionPane.showMessageDialog(null, "Le fichier "+CheminImportAgatheMutuelle+ " n'a pas �t� trouv�, import des mutuelles impossible",
							"Erreur", JOptionPane.WARNING_MESSAGE);
					log.addElement ("Le fichier "+CheminImportAgatheMutuelle+ " n'a pas �t� trouv�, import des mutuelles impossible");
					nbDerreur++;
					try {
						Historique.ecrire("Le fichier "+CheminImportAgatheMutuelle+ "\n\r n'a pas �t� trouv�, import des mutuelles impossible");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else {
					int nbligneAimporter = ReadFile.compteNbLigne(CheminImportAgatheMutuelle);
					int nbDimport = ReadFile.MutuelleReadLineEtInsereEnBase(CheminImportAgatheMutuelle,nbligneAimporter,progress);
					
					if (nbDimport==-1){
					log.addElement	("Il y a eu un probl�me � l'import des mutuelles");
					try {
						Historique.ecrire("Il y a eu un probl�me � l'import des mutuelles");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					nbDerreur++;
					}else {
						log.addElement("Import des Mutuelles termin�");
						log.addElement(nbDimport+ " Mutuelle(s) import�e(s).");
						
						try {
							Historique.ecrire("Import des Mutuelles termin�");
							Historique.ecrire(nbDimport+ " Mutuelle(s) import�e(s).");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						File mutuelleOri = new File (CheminImportAgatheMutuelle);
						File mutuelleImport�e = new File (GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\MutuellesImport�es.csv");
						ManipFichier.deplacer(mutuelleOri, mutuelleImport�e);
					}
					
				}
			
			}
		if (caseCommune.isSelected()==true){
			//import des communes dans equinox
			log.addElement("Import des Communes");
			String CheminImportAgatheCommune = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\exportCommunes.csv";
			File communeAgathe = new File (CheminImportAgatheCommune);
				if (communeAgathe.exists()==false){
					JOptionPane.showMessageDialog(null, "Le fichier "+CheminImportAgatheCommune+ " n'a pas �t� trouv�, import des communes impossible",
							"Erreur", JOptionPane.WARNING_MESSAGE);
					log.addElement ("Le fichier "+CheminImportAgatheCommune+ " n'a pas �t� trouv�, import des communes impossible");
					nbDerreur++;
					try {
						Historique.ecrire("Le fichier "+CheminImportAgatheCommune+ "\n\r n'a pas �t� trouv�, import des communes impossible");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					int nbligneAimporter = ReadFile.compteNbLigne(CheminImportAgatheCommune);
					int nbDimport = ReadFile.CommuneReadLineEtInsereEnBase(CheminImportAgatheCommune,nbligneAimporter,progress);
					if (nbDimport==-1){
						log.addElement	("Il y a eu un probl�me � l'import des communes");
						try {
							Historique.ecrire("Il y a eu un probl�me � l'import des communes");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						nbDerreur++;
						}else{
							log.addElement("Import des Communes termin�");
							log.addElement(nbDimport-1+ " Commune(s) import�e(s).");
							try {
								Historique.ecrire("Import des Communes termin�");
								Historique.ecrire(nbDimport-1+ " Commune(s) import�e(s).");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							File communeOri = new File (CheminImportAgatheCommune);
							File communeImport�e = new File (GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\CommunesImport�es.csv");
							ManipFichier.deplacer(communeOri, communeImport�e);
						}
					
					
				}
			
			
			}
		if (caseMedecin.isSelected()==true){
			log.addElement("Import des M�decins");
			String CheminImportAgatheMedecin = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\exportM�decins.csv";
			File medecinsAgathe = new File (CheminImportAgatheMedecin);
				if (medecinsAgathe.exists()==false){
					JOptionPane.showMessageDialog(null, "Le fichier "+CheminImportAgatheMedecin+ " n'a pas �t� trouv�, import des m�decins impossible",
							"Erreur", JOptionPane.WARNING_MESSAGE);
					log.addElement ("Le fichier "+CheminImportAgatheMedecin+ " n'a pas �t� trouv�, import des m�decins impossible");
					nbDerreur++;
					try {
						Historique.ecrire("Le fichier "+CheminImportAgatheMedecin+ "\n\r n'a pas �t� trouv�, import des m�decins impossible");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					int nbligneAimporter = ReadFile.compteNbLigne(CheminImportAgatheMedecin);
					int nbDimport =ReadFile.MedecinReadLineEtInsereEnBase(CheminImportAgatheMedecin,nbligneAimporter,progress);
					if (nbDimport==-1){
						log.addElement	("Il y a eu un probl�me � l'import des m�decins");
						try {
							Historique.ecrire("Il y a eu un probl�me � l'import des m�decins");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						nbDerreur++;
						}else{
							log.addElement("Import des M�decins termin�.");
							log.addElement(nbDimport-1+ " M�decin(s) import�(s).");	
							try {
								Historique.ecrire("Import des M�decins termin�.");
								Historique.ecrire(nbDimport-1+ " M�decin(s) import�(s).");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							File medecinOri = new File (CheminImportAgatheMedecin);
							File medecinImport�e = new File (GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\M�decinsImport�s.csv");
							ManipFichier.deplacer(medecinOri, medecinImport�e);	
						}
					
				}
			
			
			
		}
		if (caseClient.isSelected()==true){
			
			
			
			log.addElement("Import des Patients");
			String CheminImportAgathePatient = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\exportPatients.csv";
			File patientsAgathe = new File (CheminImportAgathePatient);
			if (patientsAgathe.exists()==false){
				JOptionPane.showMessageDialog(null, "Le fichier "+CheminImportAgathePatient+ " n'a pas �t� trouv�, import des patients impossible",
						"Erreur", JOptionPane.WARNING_MESSAGE);
				log.addElement ("Le fichier "+CheminImportAgathePatient+ " n'a pas �t� trouv�, import des patients impossible");
				nbDerreur++;
				try {
					Historique.ecrire("Le fichier "+CheminImportAgathePatient+ "\n\r n'a pas �t� trouv�, import des patients impossible");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				int nbligneAimporter = ReadFile.compteNbLigne(CheminImportAgathePatient);
				int nbDimport =ReadFile.PatientReadLineEtInsereEnBase(CheminImportAgathePatient,nbligneAimporter,progress);
				if (nbDimport==-1){
					log.addElement	("Il y a eu un probl�me � l'import des patients");
					try {
						Historique.ecrire("Il y a eu un probl�me � l'import des patients");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					nbDerreur++;
					}else{
				log.addElement("Import des Patients termin�.");
				log.addElement(nbDimport-1+ " Patient(s) import�(s).");
				try {
					Historique.ecrire("Import des Patients termin�.");
					Historique.ecrire(nbDimport-1+  "Patient(s) import�(s).");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				File patientOri = new File (CheminImportAgathePatient);
				File patientImport�e = new File (GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\PatientsImport�s.csv");
				ManipFichier.deplacer(patientOri, patientImport�e);
					}
			}
			
			}
		
		if (nbDerreur>0){
			/*JOptionPane.showMessageDialog(null, " veuillez consulter le fichier Historique.txt",
					"Attention", JOptionPane.WARNING_MESSAGE);*/
			try {
				Historique.ecrire("Import Termin� avec des erreurs");
				Historique.ecrire("----------------------------------------------");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int ouvrirHisto = JOptionPane.showConfirmDialog(null, "Il y a eu des probl�mes lors de l'importation des donn�es\n"
					+
					"\n Voulez vous consulter le fichier Historique.txt?");
			if (ouvrirHisto==0){// on accepte 
				OpenWithDefaultViewer.open(GestionRepertoire.RecupRepTravail()+"/historique.txt");
			}
			
			log.addElement("Import Termin� avec des erreurs");
			log.addElement("----------------------------------------------");
			
			
		}else{
			JOptionPane.showMessageDialog(null, "Import Termin�",
					"Ok", JOptionPane.INFORMATION_MESSAGE);
			log.addElement("Import Termin�");
			log.addElement("----------------------------------------------");	
			try {
				Historique.ecrire("Import Termin�");
				Historique.ecrire("----------------------------------------------");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		animation.stop();
		panelAnimation.setVisible(false);
		progress.setVisible(false);
		
	}

}
