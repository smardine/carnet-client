package Dialogue;

import ini_Manager.ConfigMgt;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utilitaires.GestionRepertoire;
import accesBDD.GestionDemandes;

public class FEN_APropos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel NbClientjLabel = null;
	private JLabel nbCategoriejLabel = null;
	private JLabel nbSecteurjLabel = null;
	private JLabel VersionjLabel = null;

	/**
	 * This is the default constructor
	 */
	public FEN_APropos() {
		super();
		initialize();
		
		String GetnbClient = "select count (*) FROM CLIENT";
		String GetnbCategorie = "select count (*) FROM CATEGORIE";
		String GetnbSecteur = "select count (*) FROM SECTEUR";
		// On crée une instance config qui permet de gérer la gestion d'un fichier   	
		
		
		int nbClient = 0;
		try {
			nbClient = Integer.parseInt(GestionDemandes.executeRequeteEtRetourne1Champ(GetnbClient));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int nbCategorie = 0;
		try {
			nbCategorie = Integer.parseInt(GestionDemandes.executeRequeteEtRetourne1Champ(GetnbCategorie));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int nbSecteur = 0;
		try {
			nbSecteur = Integer.parseInt(GestionDemandes.executeRequeteEtRetourne1Champ(GetnbSecteur));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if ((nbClient!=0)&&(nbCategorie!=0)&&(nbSecteur!=0)){
			NbClientjLabel.setText("Nombre de Client(s) : "+ nbClient);
			nbCategoriejLabel.setText ("Nombre de Catégorie(s) : "+nbCategorie);
			nbSecteurjLabel.setText("Nombre de Secteur(s) : "+nbSecteur);
		}
try {
			
			
			
			ConfigMgt config = null;
			try {
				config = new ConfigMgt("version.ini",GestionRepertoire.RecupRepTravail()+"\\IniFile\\",'[');
			} catch (NullPointerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String VERSION =  config.getValeurDe("version");
			VersionjLabel.setText(" Version du logiciel : "+VERSION);
	
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/interrogation.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("A propos");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			VersionjLabel = new JLabel();
			VersionjLabel.setBounds(new Rectangle(19, 124, 252, 30));
			VersionjLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			VersionjLabel.setText("  Version du logiciel:");
			nbSecteurjLabel = new JLabel();
			nbSecteurjLabel.setBounds(new Rectangle(19, 85, 252, 28));
			nbSecteurjLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			nbSecteurjLabel.setText("");
			nbCategoriejLabel = new JLabel();
			nbCategoriejLabel.setBounds(new Rectangle(19, 47, 252, 27));
			nbCategoriejLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			nbCategoriejLabel.setText("");
			NbClientjLabel = new JLabel();
			NbClientjLabel.setBounds(new Rectangle(19, 11, 252, 25));
			NbClientjLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			NbClientjLabel.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(NbClientjLabel, null);
			jContentPane.add(nbCategoriejLabel, null);
			jContentPane.add(nbSecteurjLabel, null);
			jContentPane.add(VersionjLabel, null);
		}
		return jContentPane;
	}

}
