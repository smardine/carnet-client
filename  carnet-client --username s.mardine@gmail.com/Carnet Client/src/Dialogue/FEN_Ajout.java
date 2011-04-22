package Dialogue;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Utilitaires.Historique;
import Utilitaires.ParseString;
import accesBDD.GestionDemandes;

public class FEN_Ajout extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField NomjTextField = null;
	private JTextField PNomjTextField = null;
	private JTextField AdressejTextField = null;
	private JTextField CPjTextField = null;
	private JTextField VillejTextField = null;
	private JTextField TELjTextField = null;
	private JTextField GSMjTextField = null;
	private JComboBox CatégoriejComboBox = null;
	private JTextArea CommentairejTextArea = null;
	private JLabel jLabel = null;
	private JButton EnregistrerjButton = null;
	private JButton RAZjButton = null;
	private JComboBox SecteurjComboBox = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	/**
	 * This is the default constructor
	 */
	public FEN_Ajout() {
		super();
		initialize();
		remplirListe (CatégoriejComboBox,"SELECT CATEGORIE FROM CATEGORIE");
		remplirListe (SecteurjComboBox,"SELECT SECTEUR FROM SECTEUR");
	}

	private void remplirListe(JComboBox comboBoxName, String REQUETE) {
		// TODO Auto-generated method stub
		//List<String> connections = readList(directory);
		comboBoxName.removeAllItems();
		GestionDemandes.executeRequeteEtAfficheJComboBox(REQUETE, comboBoxName);
		
	}

	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(703, 403);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/nouveau.png")));
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Ajout");
		this.setVisible(true);
		this.setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel9 = new JLabel();
			jLabel9.setBounds(new Rectangle(383, 65, 156, 20));
			jLabel9.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel9.setText(" Commentaire(s) éventuel(s)");
			jLabel8 = new JLabel();
			jLabel8.setBounds(new Rectangle(195, 255, 92, 20));
			jLabel8.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel8.setText(" Portable");
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(14, 255, 142, 20));
			jLabel7.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel7.setText(" Téléphone");
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(195, 198, 109, 20));
			jLabel6.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel6.setText(" Ville");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(14, 198, 132, 20));
			jLabel5.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel5.setText(" Code Postal");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(14, 136, 158, 20));
			jLabel4.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel4.setText(" Adresse");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(195, 79, 114, 20));
			jLabel3.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel3.setText(" Prénom");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(14, 79, 135, 20));
			jLabel2.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel2.setText(" Nom");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(14, 311, 110, 20));
			jLabel1.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel1.setText(" Secteur");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 22, 102, 20));
			jLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jLabel.setHorizontalAlignment(SwingConstants.LEFT);
			jLabel.setHorizontalTextPosition(SwingConstants.LEFT);
			jLabel.setFont(new Font("Candara", Font.PLAIN, 14));
			jLabel.setText(" Catégorie");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getNomjTextField(), null);
			jContentPane.add(getPNomjTextField(), null);
			jContentPane.add(getAdressejTextField(), null);
			jContentPane.add(getCPjTextField(), null);
			jContentPane.add(getVillejTextField(), null);
			jContentPane.add(getTELjTextField(), null);
			jContentPane.add(getGSMjTextField(), null);
			jContentPane.add(getCatégoriejComboBox(), null);
			jContentPane.add(getCommentairejTextArea(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getEnregistrerjButton(), null);
			jContentPane.add(getRAZjButton(), null);
			jContentPane.add(getSecteurjComboBox(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(jLabel9, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes NomjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNomjTextField() {
		if (NomjTextField == null) {
			NomjTextField = new JTextField();
			NomjTextField.setBounds(new Rectangle(14, 99, 172, 25));
			NomjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			NomjTextField.setText("");
		}
		return NomjTextField;
	}

	/**
	 * This method initializes PNomjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPNomjTextField() {
		if (PNomjTextField == null) {
			PNomjTextField = new JTextField();
			PNomjTextField.setBounds(new Rectangle(195, 99, 172, 25));
			PNomjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			PNomjTextField.setText("");
		}
		return PNomjTextField;
	}

	/**
	 * This method initializes AdressejTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAdressejTextField() {
		if (AdressejTextField == null) {
			AdressejTextField = new JTextField();
			AdressejTextField.setBounds(new Rectangle(14, 156, 353, 25));
			AdressejTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			AdressejTextField.setText("");
		}
		return AdressejTextField;
	}

	/**
	 * This method initializes CPjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCPjTextField() {
		if (CPjTextField == null) {
			CPjTextField = new JTextField();
			CPjTextField.setBounds(new Rectangle(14, 217, 172, 25));
			CPjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			CPjTextField.setText("");
		}
		return CPjTextField;
	}

	/**
	 * This method initializes VillejTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getVillejTextField() {
		if (VillejTextField == null) {
			VillejTextField = new JTextField();
			VillejTextField.setBounds(new Rectangle(195, 217, 172, 25));
			VillejTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			VillejTextField.setText("");
		}
		return VillejTextField;
	}

	/**
	 * This method initializes TELjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTELjTextField() {
		if (TELjTextField == null) {
			TELjTextField = new JTextField();
			TELjTextField.setBounds(new Rectangle(14, 276, 172, 25));
			TELjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			TELjTextField.setText("");
		}
		return TELjTextField;
	}

	/**
	 * This method initializes GSMjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getGSMjTextField() {
		if (GSMjTextField == null) {
			GSMjTextField = new JTextField();
			GSMjTextField.setBounds(new Rectangle(195, 276, 172, 25));
			GSMjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			GSMjTextField.setText("");
		}
		return GSMjTextField;
	}

	/**
	 * This method initializes CatégoriejComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCatégoriejComboBox() {
		if (CatégoriejComboBox == null) {
			CatégoriejComboBox = new JComboBox();
			CatégoriejComboBox.setBounds(new Rectangle(14, 41, 225, 30));
			CatégoriejComboBox.setFont(new Font("Candara", Font.PLAIN, 12));
		}
		return CatégoriejComboBox;
	}

	/**
	 * This method initializes CommentairejTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getCommentairejTextArea() {
		if (CommentairejTextArea == null) {
			CommentairejTextArea = new JTextArea();
			CommentairejTextArea.setBounds(new Rectangle(383, 90, 285, 151));
			CommentairejTextArea.setWrapStyleWord(true);
			CommentairejTextArea.setFont(new Font("Candara", Font.PLAIN, 12));
			CommentairejTextArea.setText("");
		}
		return CommentairejTextArea;
	}

	/**
	 * This method initializes EnregistrerjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEnregistrerjButton() {
		if (EnregistrerjButton == null) {
			EnregistrerjButton = new JButton();
			EnregistrerjButton.setIcon(new ImageIcon(getClass().getResource("/enregistrer.png")));
			EnregistrerjButton.setLocation(new Point(206, 321));
			EnregistrerjButton.setSize(new Dimension(150, 40));
			EnregistrerjButton.setFont(new Font("Candara", Font.PLAIN, 12));
			EnregistrerjButton.setText("Enregistrer");
			EnregistrerjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int ID_SECTEUR=0,ID_CATEGORIE=0;
					//lancement de l'enregistrement
					String NOM = NomjTextField.getText();
					NOM = ParseString.removeApostrophe(NOM);
					
					String PNOM = PNomjTextField.getText();
					PNOM = ParseString.removeApostrophe(PNOM);
					
					String ADRESSE = AdressejTextField.getText();
					ADRESSE = ParseString.removeApostrophe(ADRESSE);
					
					String CP = CPjTextField.getText();
					CP = ParseString.removeApostrophe(CP);
					
					String VILLE = VillejTextField.getText();
					VILLE = ParseString.removeApostrophe(VILLE);
					
					String TEL = TELjTextField.getText();
					TEL = ParseString.removeApostrophe(TEL);
					
					String GSM = GSMjTextField.getText();
					GSM = ParseString.removeApostrophe(GSM);
					
					String SECTEUR = SecteurjComboBox.getSelectedItem().toString();
					SECTEUR = ParseString.removeApostrophe(SECTEUR);
					
					String COMMENTAIRE = CommentairejTextArea.getText();
					COMMENTAIRE = ParseString.removeApostrophe(COMMENTAIRE);
					COMMENTAIRE = ParseString.removeLineFeed(COMMENTAIRE);
					COMMENTAIRE = ParseString.removeCageReturn(COMMENTAIRE);
					
					String CATEGORIE = CatégoriejComboBox.getSelectedItem().toString();
					
					String GETCATEGORIEID = "SELECT ID_CATEGORIE FROM CATEGORIE where CATEGORIE='"+CATEGORIE+"'";
					try {
						ID_CATEGORIE = Integer.parseInt (GestionDemandes.executeRequeteEtRetourne1Champ(GETCATEGORIEID));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					String GETSECTEURID = "SELECT ID_SECTEUR FROM SECTEUR where SECTEUR='"+SECTEUR+"'";
					try {
						ID_SECTEUR = Integer.parseInt (GestionDemandes.executeRequeteEtRetourne1Champ(GETSECTEURID));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					String INSERT = "INSERT INTO CLIENT (CATEGORIE, NOM, PNOM, ADRESSE, CP, VILLE, TEL, GSM, SECTEUR, COMMENTAIRE)" +
							" VALUES ('"+
							ID_CATEGORIE+"','"+ 
							 NOM+"','"+ 
							 PNOM+"','"+ 
							 ADRESSE+"','"+ 
							 CP+"','"+ 
							 VILLE+"','"+ 
							 TEL+"','"+ 
							 GSM+"','"+ 
							 ID_SECTEUR+"','"+ 
							 COMMENTAIRE+"' )";
					boolean succes = GestionDemandes.executeRequete(INSERT);
					
					if (succes==true){//l'insert s'est correctement déroulé
						 JOptionPane.showMessageDialog(null, "Client enregistré",
									"Ok", JOptionPane.INFORMATION_MESSAGE);
						 try {
							Historique.ecrire("Enregistrement d'un client avec la requete : "+INSERT);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if (succes==false){//il y a eu une erreur lors de l'insert
						JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement \n\r" +
								"veuillez recommencer!!",
								"Erreur", JOptionPane.WARNING_MESSAGE);
						try {
							Historique.ecrire("Erreur lors de l'enregistrement d'un client avec la requete : "+INSERT);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					
				}
			});
		}
		return EnregistrerjButton;
	}

	/**
	 * This method initializes RAZjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRAZjButton() {
		if (RAZjButton == null) {
			RAZjButton = new JButton();
			RAZjButton.setIcon(new ImageIcon(getClass().getResource("/corbeille.png")));
			RAZjButton.setLocation(new Point(450, 321));
			RAZjButton.setSize(new Dimension(150, 40));
			RAZjButton.setFont(new Font("Candara", Font.PLAIN, 12));
			RAZjButton.setText(" Remise à zéro");
			RAZjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					NomjTextField.setText("Nom");
					PNomjTextField.setText("Prénom");
					AdressejTextField.setText("Adresse");
					CPjTextField.setText("Code Postal");
					VillejTextField.setText("Ville");
					TELjTextField.setText("Tel Fixe");
					GSMjTextField.setText("Tel Portable");
					//SecteurjTextField.setText("Secteur");
					CommentairejTextArea.setText("Commentaire(s) éventuel(s)");
				}
			});
		}
		return RAZjButton;
	}

	/**
	 * This method initializes SecteurjComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSecteurjComboBox() {
		if (SecteurjComboBox == null) {
			SecteurjComboBox = new JComboBox();
			SecteurjComboBox.setBounds(new Rectangle(14, 331, 170, 30));
			SecteurjComboBox.setFont(new Font("Candara", Font.PLAIN, 12));
		}
		return SecteurjComboBox;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
