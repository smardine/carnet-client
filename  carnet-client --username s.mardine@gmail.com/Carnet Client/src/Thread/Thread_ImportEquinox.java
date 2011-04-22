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
     * Import des différentes données ddes fichiers .csv vers Equinox
     * @param UserEquinox -JTextField nom d'utilisateur pour la connexion à la bdd
     * @param PasswordEquinox -JTextField mot de passe pour la connexion a la bdd
     * @param CheminEquinox -JTextField chemin de la base sur l'hote
     * @param Serveur - JTextField le nom du serveur qui heberge la base agathe (sous la forme 172.30.20.56 ou localhost ou MICPTDVP)
     * @param client - JCheckBox defini si on exporte ou pas
     * @param commune - JCheckBox defini si on exporte ou pas
     * @param medecin - JCheckBox defini si on exporte ou pas
     * @param mutuelle - JCheckBox defini si on exporte ou pas
     * @param text -DefaultListModel => lié a la liste
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
			//si aucune des case n'est cochée, alors on affiche un message et on sort:
			JOptionPane.showMessageDialog(null, "Aucune option d'import n'est selectionnée," +
					" veuillez en choisir au moins une",
					"Erreur", JOptionPane.WARNING_MESSAGE);
			log.addElement ("Aucune option d'import n'est selectionnée, veuillez en choisir au moins une ");
			try {
				Historique.ecrire("Aucune option d'import n'est selectionnée, veuillez en choisir au moins une");
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
					JOptionPane.showMessageDialog(null, "Le fichier "+CheminImportAgatheMutuelle+ " n'a pas été trouvé, import des mutuelles impossible",
							"Erreur", JOptionPane.WARNING_MESSAGE);
					log.addElement ("Le fichier "+CheminImportAgatheMutuelle+ " n'a pas été trouvé, import des mutuelles impossible");
					nbDerreur++;
					try {
						Historique.ecrire("Le fichier "+CheminImportAgatheMutuelle+ "\n\r n'a pas été trouvé, import des mutuelles impossible");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else {
					int nbligneAimporter = ReadFile.compteNbLigne(CheminImportAgatheMutuelle);
					int nbDimport = ReadFile.MutuelleReadLineEtInsereEnBase(CheminImportAgatheMutuelle,nbligneAimporter,progress);
					
					if (nbDimport==-1){
					log.addElement	("Il y a eu un problème à l'import des mutuelles");
					try {
						Historique.ecrire("Il y a eu un problème à l'import des mutuelles");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					nbDerreur++;
					}else {
						log.addElement("Import des Mutuelles terminé");
						log.addElement(nbDimport+ " Mutuelle(s) importée(s).");
						
						try {
							Historique.ecrire("Import des Mutuelles terminé");
							Historique.ecrire(nbDimport+ " Mutuelle(s) importée(s).");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						File mutuelleOri = new File (CheminImportAgatheMutuelle);
						File mutuelleImportée = new File (GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\MutuellesImportées.csv");
						ManipFichier.deplacer(mutuelleOri, mutuelleImportée);
					}
					
				}
			
			}
		if (caseCommune.isSelected()==true){
			//import des communes dans equinox
			log.addElement("Import des Communes");
			String CheminImportAgatheCommune = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\exportCommunes.csv";
			File communeAgathe = new File (CheminImportAgatheCommune);
				if (communeAgathe.exists()==false){
					JOptionPane.showMessageDialog(null, "Le fichier "+CheminImportAgatheCommune+ " n'a pas été trouvé, import des communes impossible",
							"Erreur", JOptionPane.WARNING_MESSAGE);
					log.addElement ("Le fichier "+CheminImportAgatheCommune+ " n'a pas été trouvé, import des communes impossible");
					nbDerreur++;
					try {
						Historique.ecrire("Le fichier "+CheminImportAgatheCommune+ "\n\r n'a pas été trouvé, import des communes impossible");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					int nbligneAimporter = ReadFile.compteNbLigne(CheminImportAgatheCommune);
					int nbDimport = ReadFile.CommuneReadLineEtInsereEnBase(CheminImportAgatheCommune,nbligneAimporter,progress);
					if (nbDimport==-1){
						log.addElement	("Il y a eu un problème à l'import des communes");
						try {
							Historique.ecrire("Il y a eu un problème à l'import des communes");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						nbDerreur++;
						}else{
							log.addElement("Import des Communes terminé");
							log.addElement(nbDimport-1+ " Commune(s) importée(s).");
							try {
								Historique.ecrire("Import des Communes terminé");
								Historique.ecrire(nbDimport-1+ " Commune(s) importée(s).");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							File communeOri = new File (CheminImportAgatheCommune);
							File communeImportée = new File (GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\CommunesImportées.csv");
							ManipFichier.deplacer(communeOri, communeImportée);
						}
					
					
				}
			
			
			}
		if (caseMedecin.isSelected()==true){
			log.addElement("Import des Médecins");
			String CheminImportAgatheMedecin = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\exportMédecins.csv";
			File medecinsAgathe = new File (CheminImportAgatheMedecin);
				if (medecinsAgathe.exists()==false){
					JOptionPane.showMessageDialog(null, "Le fichier "+CheminImportAgatheMedecin+ " n'a pas été trouvé, import des médecins impossible",
							"Erreur", JOptionPane.WARNING_MESSAGE);
					log.addElement ("Le fichier "+CheminImportAgatheMedecin+ " n'a pas été trouvé, import des médecins impossible");
					nbDerreur++;
					try {
						Historique.ecrire("Le fichier "+CheminImportAgatheMedecin+ "\n\r n'a pas été trouvé, import des médecins impossible");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					int nbligneAimporter = ReadFile.compteNbLigne(CheminImportAgatheMedecin);
					int nbDimport =ReadFile.MedecinReadLineEtInsereEnBase(CheminImportAgatheMedecin,nbligneAimporter,progress);
					if (nbDimport==-1){
						log.addElement	("Il y a eu un problème à l'import des médecins");
						try {
							Historique.ecrire("Il y a eu un problème à l'import des médecins");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						nbDerreur++;
						}else{
							log.addElement("Import des Médecins terminé.");
							log.addElement(nbDimport-1+ " Médecin(s) importé(s).");	
							try {
								Historique.ecrire("Import des Médecins terminé.");
								Historique.ecrire(nbDimport-1+ " Médecin(s) importé(s).");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							File medecinOri = new File (CheminImportAgatheMedecin);
							File medecinImportée = new File (GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\MédecinsImportés.csv");
							ManipFichier.deplacer(medecinOri, medecinImportée);	
						}
					
				}
			
			
			
		}
		if (caseClient.isSelected()==true){
			
			
			
			log.addElement("Import des Patients");
			String CheminImportAgathePatient = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\exportPatients.csv";
			File patientsAgathe = new File (CheminImportAgathePatient);
			if (patientsAgathe.exists()==false){
				JOptionPane.showMessageDialog(null, "Le fichier "+CheminImportAgathePatient+ " n'a pas été trouvé, import des patients impossible",
						"Erreur", JOptionPane.WARNING_MESSAGE);
				log.addElement ("Le fichier "+CheminImportAgathePatient+ " n'a pas été trouvé, import des patients impossible");
				nbDerreur++;
				try {
					Historique.ecrire("Le fichier "+CheminImportAgathePatient+ "\n\r n'a pas été trouvé, import des patients impossible");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				int nbligneAimporter = ReadFile.compteNbLigne(CheminImportAgathePatient);
				int nbDimport =ReadFile.PatientReadLineEtInsereEnBase(CheminImportAgathePatient,nbligneAimporter,progress);
				if (nbDimport==-1){
					log.addElement	("Il y a eu un problème à l'import des patients");
					try {
						Historique.ecrire("Il y a eu un problème à l'import des patients");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					nbDerreur++;
					}else{
				log.addElement("Import des Patients terminé.");
				log.addElement(nbDimport-1+ " Patient(s) importé(s).");
				try {
					Historique.ecrire("Import des Patients terminé.");
					Historique.ecrire(nbDimport-1+  "Patient(s) importé(s).");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				File patientOri = new File (CheminImportAgathePatient);
				File patientImportée = new File (GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\PatientsImportés.csv");
				ManipFichier.deplacer(patientOri, patientImportée);
					}
			}
			
			}
		
		if (nbDerreur>0){
			/*JOptionPane.showMessageDialog(null, " veuillez consulter le fichier Historique.txt",
					"Attention", JOptionPane.WARNING_MESSAGE);*/
			try {
				Historique.ecrire("Import Terminé avec des erreurs");
				Historique.ecrire("----------------------------------------------");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int ouvrirHisto = JOptionPane.showConfirmDialog(null, "Il y a eu des problèmes lors de l'importation des données\n"
					+
					"\n Voulez vous consulter le fichier Historique.txt?");
			if (ouvrirHisto==0){// on accepte 
				OpenWithDefaultViewer.open(GestionRepertoire.RecupRepTravail()+"/historique.txt");
			}
			
			log.addElement("Import Terminé avec des erreurs");
			log.addElement("----------------------------------------------");
			
			
		}else{
			JOptionPane.showMessageDialog(null, "Import Terminé",
					"Ok", JOptionPane.INFORMATION_MESSAGE);
			log.addElement("Import Terminé");
			log.addElement("----------------------------------------------");	
			try {
				Historique.ecrire("Import Terminé");
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
