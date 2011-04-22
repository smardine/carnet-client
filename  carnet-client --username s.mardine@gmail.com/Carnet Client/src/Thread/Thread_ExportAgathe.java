package Thread;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Animation.AnimatedPanel;
import Utilitaires.GestionRepertoire;
import Utilitaires.Historique;
import Utilitaires.OpenWithDefaultViewer;
import accesBDDAgathe.GestionDemandesAgathe;

public class Thread_ExportAgathe extends Thread {
	
	protected JTextField User,Password,emplacement,serveur;
	protected JCheckBox caseClient,caseCommune,caseMedecin,caseMutuelle;
	protected DefaultListModel log;
	protected JList liste;
	protected AnimatedPanel animation;
	protected JPanel panelAnimation;
	protected int nbDerreur=0;
	
	/**
     * Export des différentes données d'Agathe vers des fichiers .csv.
     * @param UserAgathe -JTextField nom d'utilisateur pour la connexion à la bdd
     * @param PasswordAgathe -JTextField mot de passe pour la connexion a la bdd
     * @param CheminAgathe -JTextField chemin de la base sur l'hote
     * @param Serveur - JTextField le nom du serveur qui heberge la base agathe (sous la forme 172.30.20.56 ou localhost ou MICPTDVP)
     * @param client - JCheckBox defini si on exporte ou pas
     * @param commune - JCheckBox defini si on exporte ou pas
     * @param medecin - JCheckBox defini si on exporte ou pas
     * @param mutuelle - JCheckBox defini si on exporte ou pas
     * @param text -DefaultListModel => lié a la liste
     * @param List - JList => sert a afficher des infos pour l'utilisateur
     * @param anime - AnimatedPanel => pour l'animation
     * @param panelAnime - JPanel => contient l'animation
     */
	public Thread_ExportAgathe (JTextField UserAgathe,JTextField PasswordAgathe,JTextField CheminAgathe,JTextField Serveur,
			JCheckBox client,JCheckBox commune,JCheckBox medecin,JCheckBox mutuelle,
			DefaultListModel text,
			JList List,
			AnimatedPanel anime,
			JPanel panelAnime)
	
	{
	User=UserAgathe;
	Password=PasswordAgathe;
	emplacement=CheminAgathe;
	serveur=Serveur;
	
	caseClient=client;
	caseCommune=commune;
	caseMedecin=medecin;
	caseMutuelle=mutuelle;
	
	log=text;
	liste=List;
	animation=anime;
	panelAnimation=panelAnime;
	
	}

	public void run (){
		
		if ((caseClient.isSelected()==false)&&(caseCommune.isSelected()==false)&&(caseMedecin.isSelected()==false)&&(caseMutuelle.isSelected()==false)){
			//si aucune des case n'est cochée, alors on affiche un message et on sort:
			JOptionPane.showMessageDialog(null, "Aucune option d'export n'est selectionnée," +
					" veuillez en choisir au moins une",
					"Erreur", JOptionPane.WARNING_MESSAGE);
			
			log.addElement ("Aucune option d'export n'est selectionnée, veuillez en choisir au moins une ");
			try {
				Historique.ecrire("Aucune option d'import n'est selectionnée, veuillez en choisir au moins une");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int nbligne=liste.getModel().getSize(); 
			
			liste.setSelectedIndex(nbligne);
			
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					/**
					 * {@inheritDoc}
					 */
					@Override public void run() {
						liste.ensureIndexIsVisible(liste.getSelectedIndex());
					 }
					}) ;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			
			
			return;
			
		}else {
			panelAnimation.setVisible(true);
			animation.start();
			String CheminExportAgathe = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\";
			File repExport = new File (CheminExportAgathe);
			if (repExport.exists()==false){
				repExport.mkdirs();
			}
		}
		if (caseClient.isSelected()==true){
			//export des données clients
			
			log.addElement("Export des patients");
			String SciptExportPatient="SELECT ass.NUMSS,ass.NUMSSCLE," +
					"cg.CODECINFO,cai.NUMDES,cai.CAIPAY," +
					"ass.PRENOM,ass.NOM," +
					"pat.ORIGDROIT," +
					"pat.DATENAISS," +
					"pat.PRENOM,pat.NOM," +
					"pat.RANGNAISS," +
					"pat.ORIGDROIT," +
					"pat.TXC1," +
					"pat.LIEN," +
					"pat.VISVILLE,pat.VISCP," +
					"pat.TEL,pat.TEL2," +
					"pat.VISAD1,pat.VISAD2," +
					"mut.IDAMC,mut.NUMADH" +
					" from ASSURE ass " +
					"left join PATIENT pat on (pat.CODEASSUR=ass.CODEASSUR)" +
					"left join CAISSE cai on (pat.CODEC1=cai.CODECAISSE)" +
					"left join CAISSEGEST cg on (cai.CODECGEST=cg.CODECGEST)" +
					"left join MUTPAT mut on (mut.CODEPAT=pat.CODEPAT)";
			
				int nbenregistrement =  GestionDemandesAgathe.RequeteExportPatient(SciptExportPatient, log);
				if (nbenregistrement==-1){
					nbDerreur++;
					log.addElement("Il y a eu un problème à l'export des patients.");
					try {
						Historique.ecrire("Il y a eu un problème à l'export des patients.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					log.addElement(nbenregistrement+ " patient(s) exporté(s).");
					
					try {
						Historique.ecrire(nbenregistrement+ " patient(s) exporté(s).");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			
				// TODO Auto-generated catch block
				
		     
		      
			}
			
		
		if (caseCommune.isSelected()==true){
			log.addElement("Export des Communes");//export des communes
			
			String ScriptExportCommune="select CP,VILLE,KMPLA,KMMON,KMSKI from LOCALITE where (not CP is null or not VILLE is null) group by CP,VILLE,KMPLA,KMMON,KMSKI";
			
				int nbenregistrement = GestionDemandesAgathe.RequeteExportCommune(ScriptExportCommune, log);
				
				
				if (nbenregistrement==-1){
					nbDerreur++;
					log.addElement("Il y a eu un problème à l'export des communes.");
					try {
						Historique.ecrire("Il y a eu un problème à l'export des communes.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					log.addElement(nbenregistrement+ " communes(s) exportée(s).");
					
					try {
						Historique.ecrire(nbenregistrement+ " communes(s) exportée(s).");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			
				
			
		     
			
			int nbligne=liste.getModel().getSize(); 
			liste.setSelectedIndex(nbligne);
			
			
		//	GestionDemandesAgathe.executeRequete(ScriptExportCommune);
		}
		if (caseMedecin.isSelected()==true){
			//export des medecins
			log.addElement ("Export des medecins");
			String ScriptExportMedecin = "SELECT a.MEDSECU,a.CODESPEC, a.NOM,a.PRENOM,a.TEL,a.FAX,a.CP, a.VILLE, a.AD1, a.SALARIE, a.EMAIL FROM MEDECIN a";
			
				int nbenregistrement = GestionDemandesAgathe.RequeteExportMedecin(ScriptExportMedecin, log);
				if (nbenregistrement==-1){
					nbDerreur++;
					log.addElement("Il y a eu un problème à l'export des médecins.");
					try {
						Historique.ecrire("Il y a eu un problème à l'export des médecins.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					log.addElement(nbenregistrement+ " médecin(s) exporté(s).");
					
					try {
						Historique.ecrire(nbenregistrement+ " médecin(s) exporté(s).");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			
		     
			
			int nbligne=liste.getModel().getSize(); 
			liste.setSelectedIndex(nbligne);
		}
		if (caseMutuelle.isSelected()==true){
			//export des medecins
			String CheminExportAgathe = GestionRepertoire.RecupRepTravail()+"\\exportAgathe\\";
			File fichierExport = new File (CheminExportAgathe+"exportMutuelles.csv");
			if (fichierExport.exists()==true){
				fichierExport.delete();
			}
			log.addElement ("Export des mutuelles");
			String ScriptExportMutuelle = "SELECT a.MUTNUM FROM MUTPAT a";
			String ScriptExportAMC = "SELECT a.IDAMC FROM MUTPAT a";
			
				int nbenregistrement1 = GestionDemandesAgathe.RequeteExportMutuelle(ScriptExportMutuelle, log);
				int nbenregistrement2 = GestionDemandesAgathe.RequeteExportMutuelle(ScriptExportAMC, log);
				
				if ((nbenregistrement1==-1)||(nbenregistrement2==-1)){
					nbDerreur++;
					log.addElement("Il y a eu un problème à l'export des médecins.");
					try {
						Historique.ecrire("Il y a eu un problème à l'export des médecins.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					log.addElement(nbenregistrement1+nbenregistrement2+ " mutuelle(s) exportée(s).");
					
					try {
						Historique.ecrire(nbenregistrement1+nbenregistrement2+ " mutuelle(s) exportée(s).");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			
				
				
			
		      
			
			int nbligne=liste.getModel().getSize(); 
			liste.setSelectedIndex(nbligne);
		}
		
		if (nbDerreur>0){
			/*JOptionPane.showMessageDialog(null, " veuillez consulter le fichier Historique.txt",
					"Attention", JOptionPane.WARNING_MESSAGE);*/
			try {
				Historique.ecrire("Export Terminé avec des erreurs");
				Historique.ecrire("----------------------------------------------");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int ouvrirHisto = JOptionPane.showConfirmDialog(null, "Il y a eu des problèmes lors de l'exportation des données\n"
					+
					"\n Voulez vous consulter le fichier Historique.txt?");
			if (ouvrirHisto==0){// on accepte 
				OpenWithDefaultViewer.open(GestionRepertoire.RecupRepTravail()+"/historique.txt");
			}
			
			log.addElement("Export Terminé avec des erreurs");
			log.addElement("----------------------------------------------");
			
			
		}else{
			JOptionPane.showMessageDialog(null, "Export Terminé",
					"Ok", JOptionPane.INFORMATION_MESSAGE);
			log.addElement("Export Terminé");
			log.addElement("----------------------------------------------");	
			try {
				Historique.ecrire("Export Terminé");
				Historique.ecrire("----------------------------------------------");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		animation.stop();
		panelAnimation.setVisible(false);
		
	}

}
