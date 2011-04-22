package Dialogue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import Utilitaires.GestionRepertoire;
import Utilitaires.Historique;
import Utilitaires.OpenWithDefaultViewer;
import accesBDDAgathe.GestionDemandesAgathe;

public class Fen_Download extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton = null;
	private JLabel jLabel = null;
	private JLabel operation_jLabel = null;
	private JProgressBar jProgressBar = null;
	private JLabel jLabel1 = null;
	/**
	 * This is the default constructor
	 */
	public Fen_Download() {
		super();
		initialize();
		
		long HeureDebut = System.currentTimeMillis();
		long HeureActuelle;
		String urlsetup = "http://downloads.sourceforge.net/project/carnetclient/setup/setupCarnetClient.exe";
		int erreurOuverture=0;
		InputStream input = null;
		String cheminFichier = "";
		File fichier;
		FileOutputStream writeFile = null;
		
		
		try
		{
			
		//                                                     //
	//															//
			//la maj est acceptée, il faut extraire le contenu de la base avant de lancer le setup
			
			String CheminExport = GestionRepertoire.RecupRepTravail()+"\\export\\";
			//ExportBddDansSQL (CheminExport);
			int nbDerreur=0;
			File repExport = new File (CheminExport);
			if (repExport.exists()==false){
				repExport.mkdirs();
			}
			
			String ScriptExportClient="SELECT a.ID_CLIENT, a.NOM, a.PNOM, a.ADRESSE, a.CP, a.VILLE, a.TEL, a.GSM, a.COMMENTAIRE, a.CATEGORIE, a.SECTEUR " +
					" FROM CLIENT a";
			

		int nbenregistrement =  GestionDemandesAgathe.RequeteExportDonnéesTable(ScriptExportClient,GestionRepertoire.RecupRepTravail()+"\\export\\Client.export");
		if (nbenregistrement==-1){
			nbDerreur++;
			jLabel1.setText("Il y a eu un problème à l'export des clients.");
			try {
				Historique.ecrire("Il y a eu un problème à l'export des clients.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			jLabel1.setText(nbenregistrement+ " client(s) exporté(s).");
			
			try {
				Historique.ecrire(nbenregistrement+ " client(s) exporté(s).");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		String ScriptExportCategorie = "SELECT a.CATEGORIE, a.ID_CATEGORIE FROM CATEGORIE a";
		 nbenregistrement =  GestionDemandesAgathe.RequeteExportDonnéesTable(ScriptExportCategorie,GestionRepertoire.RecupRepTravail()+"\\export\\Categorie.export");
		if (nbenregistrement==-1){
			nbDerreur++;
			jLabel1.setText("Il y a eu un problème à l'export des catégorie.");
			try {
				Historique.ecrire("Il y a eu un problème à l'export des catégorie.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			jLabel1.setText(nbenregistrement+ " catégorie(s) exportée(s).");
			
			try {
				Historique.ecrire(nbenregistrement+ " catégorie(s) exportée(s).");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String ScriptExportDocument = "SELECT a.DOCUMENT_NOM, a.ID_CLIENT, a.EMPLACEMENT, a.ID_DOCUMENT FROM DOCUMENTS a";
		 nbenregistrement =  GestionDemandesAgathe.RequeteExportDonnéesTable(ScriptExportDocument,GestionRepertoire.RecupRepTravail()+"\\export\\Document.export");
		if (nbenregistrement==-1){
			nbDerreur++;
			jLabel1.setText("Il y a eu un problème à l'export des documents.");
			try {
				Historique.ecrire("Il y a eu un problème à l'export des documents.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			jLabel1.setText(nbenregistrement+ " documents(s) exportée(s).");
			
			try {
				Historique.ecrire(nbenregistrement+ " documents(s) exportée(s).");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		String ScriptExportSecteur = "SELECT a.SECTEUR, a.ID_SECTEUR FROM SECTEUR a";
		 nbenregistrement =  GestionDemandesAgathe.RequeteExportDonnéesTable(ScriptExportSecteur,GestionRepertoire.RecupRepTravail()+"\\export\\Secteur.export");
		if (nbenregistrement==-1){
			nbDerreur++;
			jLabel1.setText("Il y a eu un problème à l'export des secteurs.");
			try {
				Historique.ecrire("Il y a eu un problème à l'export des secteurs.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			jLabel1.setText(nbenregistrement+ " secteurs(s) exporté(s).");
			
			try {
				Historique.ecrire(nbenregistrement+ " secteurs(s) exporté(s).");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			
			jLabel1.setText("Export Terminé avec des erreurs");
			//jLabel1.setText("----------------------------------------------");
			
			
		}else{
			/*JOptionPane.showMessageDialog(null, "Export Terminé",
					"Ok", JOptionPane.INFORMATION_MESSAGE);*/
			jLabel1.setText("Export Terminé");
			//jLabel1.setText("----------------------------------------------");	
			try {
				Historique.ecrire("Export Terminé");
				Historique.ecrire("----------------------------------------------");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
			
			
			////////////////////////////////////////////////////////////////////////////////////
			URL url = new URL(urlsetup);
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
			jLabel1.setText(" Fichier en cours : "+ fileName);
			fichier = new File (cheminFichier);
			writeFile = new FileOutputStream(cheminFichier);
			//lecture par segment de 4Mo
			byte[] buffer = new byte[4096*1024];
			int read;

			while ((read = input.read(buffer)) > 0){
				writeFile.write(buffer, 0, read);
				long TailleEncours = fichier.length();
				int progressionEnCours = (int) ((100*(TailleEncours+1))/fileLength);
				//int Pourcent=(int) progressionEnCours;
				
				HeureActuelle = System.currentTimeMillis();
				
				long Vitesse = (long) (TailleEncours / (HeureActuelle-HeureDebut));
								
			
			
				jLabel1.setText("Téléchargement de la nouvelle version , Vitesse Actuelle : "+ Vitesse + " Ko/s");
				jProgressBar.setValue(progressionEnCours);
				jProgressBar.setString(progressionEnCours + " %");
								
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
			}
		}
		
		
		
		Runtime r1 = Runtime.getRuntime();
		
		String cmdExecuteSetup = ("cmd /c \""+cheminFichier+"\"");
		try {
		r1.exec(cmdExecuteSetup);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e,
					"Erreur au lancement du setup", JOptionPane.WARNING_MESSAGE);
			try {
				Historique.ecrire("Erreur au lancement du setup : "+e);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		System.exit(0);
		//p.waitFor();
					
		
		
		
	}


	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(526, 233);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setPreferredSize(new Dimension(526, 233));
		this.setMaximumSize(new Dimension(526, 233));
		this.setMinimumSize(new Dimension(526, 233));
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/carnet.png")));
		this.setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
		this.setTitle("Mise à jour");
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(150, 103, 356, 27));
			jLabel1.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel1.setText("");
			operation_jLabel = new JLabel();
			operation_jLabel.setBounds(new Rectangle(150, 62, 355, 26));
			operation_jLabel.setText(" Opération en cours");
			operation_jLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(150, 18, 354, 26));
			jLabel.setText(" Veuillez patienter pendant la mise à jour...");
			jLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setFont(new Font("Candara", Font.BOLD, 12));
			jContentPane.add(getJButton(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(operation_jLabel, null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(jLabel1, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(15, 35, 117, 119));
			jButton.setIcon(new ImageIcon(getClass().getResource("/carnet.png")));
			jButton.setFont(new Font("Candara", Font.PLAIN, 12));
		}
		return jButton;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(150, 152, 358, 26));
			jProgressBar.setStringPainted(true);
			jProgressBar.setFont(new Font("Candara", Font.PLAIN, 12));
		}
		return jProgressBar;
	}

}
