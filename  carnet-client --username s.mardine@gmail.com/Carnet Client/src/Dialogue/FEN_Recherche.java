package Dialogue;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import lecture_ecriture.WriteFile;
import Thread.Thread_MAJJTable;
import Utilitaires.GestionRepertoire;
import Utilitaires.Historique;
import Utilitaires.OpenWithDefaultViewer;
import Utilitaires.ParseString;
import accesBDD.GestionDemandes;

public class FEN_Recherche extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JComboBox CatégoriejComboBox = null;
	private JLabel jLabel = null;
	private JTextField NomjTextField = null;
	private JTextField PNomjTextField = null;
	private JTextField AdressejTextField = null;
	private JTextField CPjTextField = null;
	private JTextField VillejTextField = null;
	private JTextField TELjTextField = null;
	private JTextField GSMjTextField = null;
	private JTextArea CommentairejTextArea = null;
	private JButton RecherchejButton = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel9 = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private DefaultTableModel modelDeTable;
	private JLabel NumClientjLabel = null;
	private JButton ModfjButton = null;
	private JButton SupprjButton = null;
	private JTextField SecteurjTextField = null;
	private JButton PlanjButton = null;
	/**
	 * This is the default constructor
	 */
	public FEN_Recherche() {
		super();
		modelDeTable = new DefaultTableModel ();
		initialize();
		remplirListe (CatégoriejComboBox,"SELECT CATEGORIE FROM CATEGORIE");
		//remplirListe (SecteurjComboBox,"SELECT SECTEUR FROM SECTEUR");
		
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
		this.setSize(974, 638);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recherche.png")));
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		this.setTitle("Recherche");
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
			NumClientjLabel = new JLabel();
			NumClientjLabel.setBounds(new Rectangle(283, 57, 71, 28));
			NumClientjLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			NumClientjLabel.setText("");
			jLabel9 = new JLabel();
			jLabel9.setText(" Commentaire(s)");
			jLabel9.setSize(new Dimension(100, 25));
			jLabel9.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel9.setLocation(new Point(406, 89));
			jLabel8 = new JLabel();
			jLabel8.setText(" Secteur");
			jLabel8.setSize(new Dimension(100, 25));
			jLabel8.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel8.setLocation(new Point(30, 330));
			jLabel7 = new JLabel();
			jLabel7.setText(" Tel Portable");
			jLabel7.setSize(new Dimension(100, 25));
			jLabel7.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel7.setLocation(new Point(210, 270));
			jLabel6 = new JLabel();
			jLabel6.setText(" Tel Fixe");
			jLabel6.setSize(new Dimension(100, 25));
			jLabel6.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel6.setLocation(new Point(29, 268));
			jLabel5 = new JLabel();
			jLabel5.setText(" Ville");
			jLabel5.setSize(new Dimension(100, 25));
			jLabel5.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel5.setLocation(new Point(210, 210));
			jLabel4 = new JLabel();
			jLabel4.setText(" Code Postal");
			jLabel4.setSize(new Dimension(100, 25));
			jLabel4.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel4.setLocation(new Point(30, 211));
			jLabel3 = new JLabel();
			jLabel3.setText(" Adresse");
			jLabel3.setSize(new Dimension(100, 25));
			jLabel3.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel3.setLocation(new Point(30, 150));
			jLabel2 = new JLabel();
			jLabel2.setText(" Prénom");
			jLabel2.setSize(new Dimension(100, 25));
			jLabel2.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel2.setLocation(new Point(210, 92));
			jLabel1 = new JLabel();
			jLabel1.setText(" Nom");
			jLabel1.setSize(new Dimension(100, 25));
			jLabel1.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel1.setLocation(new Point(30, 93));
			jLabel = new JLabel();
			jLabel.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel.setLocation(new Point(32, 30));
			jLabel.setSize(new Dimension(100, 25));
			jLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel.setText("Catégorie(s)");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setFont(new Font("Candara", Font.BOLD, 12));
			jContentPane.add(getCatégoriejComboBox(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getNomjTextField(), null);
			jContentPane.add(getPNomjTextField(), null);
			jContentPane.add(getAdressejTextField(), null);
			jContentPane.add(getCPjTextField(), null);
			jContentPane.add(getVillejTextField(), null);
			jContentPane.add(getTELjTextField(), null);
			jContentPane.add(getGSMjTextField(), null);
			jContentPane.add(getCommentairejTextArea(), null);
			jContentPane.add(getRecherchejButton(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jLabel7, null);
			jContentPane.add(jLabel8, null);
			jContentPane.add(jLabel9, null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(NumClientjLabel, null);
			jContentPane.add(getModfjButton(), null);
			jContentPane.add(getSupprjButton(), null);
			jContentPane.add(getSecteurjTextField(), null);
			jContentPane.add(getPlanjButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes CatégoriejComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCatégoriejComboBox() {
		if (CatégoriejComboBox == null) {
			CatégoriejComboBox = new JComboBox();
			CatégoriejComboBox.setLocation(new Point(31, 56));
			CatégoriejComboBox.setFont(new Font("Candara", Font.PLAIN, 12));
			CatégoriejComboBox.setSize(new Dimension(226, 30));
		}
		return CatégoriejComboBox;
	}

	/**
	 * This method initializes NomjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNomjTextField() {
		if (NomjTextField == null) {
			NomjTextField = new JTextField();
			NomjTextField.setLocation(new Point(30, 120));
			NomjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			NomjTextField.setSize(new Dimension(172, 25));
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
			PNomjTextField.setBounds(new Rectangle(210, 120, 172, 25));
			PNomjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
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
			AdressejTextField.setLocation(new Point(29, 180));
			AdressejTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			AdressejTextField.setSize(new Dimension(353, 25));
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
			CPjTextField.setLocation(new Point(30, 240));
			CPjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			CPjTextField.setSize(new Dimension(172, 25));
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
			VillejTextField.setLocation(new Point(210, 240));
			VillejTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			VillejTextField.setSize(new Dimension(172, 25));
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
			TELjTextField.setLocation(new Point(30, 300));
			TELjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			TELjTextField.setSize(new Dimension(172, 25));
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
			GSMjTextField.setLocation(new Point(210, 300));
			GSMjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			GSMjTextField.setSize(new Dimension(172, 25));
		}
		return GSMjTextField;
	}

	/**
	 * This method initializes CommentairejTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getCommentairejTextArea() {
		if (CommentairejTextArea == null) {
			CommentairejTextArea = new JTextArea();
			CommentairejTextArea.setLocation(new Point(406, 121));
			CommentairejTextArea.setFont(new Font("Candara", Font.PLAIN, 12));
			CommentairejTextArea.setSize(new Dimension(353, 256));
		}
		return CommentairejTextArea;
	}

	/**
	 * This method initializes RecherchejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRecherchejButton() {
		if (RecherchejButton == null) {
			RecherchejButton = new JButton();
			RecherchejButton.setText(" Rechercher");
			RecherchejButton.setSize(new Dimension(150, 40));
			RecherchejButton.setIcon(new ImageIcon(getClass().getResource("/recherche_petit.png")));
			RecherchejButton.setFont(new Font("Candara", Font.PLAIN, 12));
			RecherchejButton.setLocation(new Point(782, 121));
			RecherchejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					//lance recherche et affiche dans la jtable
					int ID_CATEGORIE=0,ID_SECTEUR=0;
					int nbcolonne = modelDeTable.getColumnCount();
					
					if (nbcolonne!=0){//si il y a des colonnes, on reinitialise le model
						modelDeTable = new DefaultTableModel();
						jTable.setModel(modelDeTable);
					}
					String NOM = NomjTextField.getText();
					String PNOM = PNomjTextField.getText();
					String ADRESSE = AdressejTextField.getText();
					String CP = CPjTextField.getText();
					String VILLE = VillejTextField.getText();
					String TEL = TELjTextField.getText();
					String GSM = GSMjTextField.getText();
					
					
					//String SECTEUR = SecteurjComboBox.getSelectedItem().toString();
					String SECTEUR = SecteurjTextField.getText();
					
					
					String COMMENTAIRE = CommentairejTextArea.getText();
					String CATEGORIE = CatégoriejComboBox.getSelectedItem().toString();
					
					String GET_CATEGORIEID = "SELECT ID_CATEGORIE FROM CATEGORIE WHERE CATEGORIE = '"+CATEGORIE+"'";
					try {
						ID_CATEGORIE = Integer.parseInt (GestionDemandes.executeRequeteEtRetourne1Champ(GET_CATEGORIEID));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					if (SECTEUR.equals("")==false){
						String GET_SECTEURID = "SELECT ID_SECTEUR FROM SECTEUR WHERE SECTEUR = '"+SECTEUR+"'";
						try {
							ID_SECTEUR = Integer.parseInt (GestionDemandes.executeRequeteEtRetourne1Champ(GET_SECTEURID));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
					}else{
						ID_SECTEUR=0;
					}
					
					
					String RECHERCHE = "SELECT a.NOM,a.ADRESSE, a.CP, a.VILLE, a.TEL, a.GSM, a.SECTEUR, a.COMMENTAIRE, a.PNOM,a.ID_CLIENT " +
							"FROM CLIENT a " +
							"where a.CATEGORIE = '"+ID_CATEGORIE+"'" +
							" or (a.NOM like '"+NOM+"' or a.PNOM like '"+PNOM+"' or a.ADRESSE like '"+ADRESSE+"' or a.CP like '"+CP+"' or a.VILLE like '"+VILLE+"' or a.TEL like '"+TEL+"' or a.GSM like '" +GSM+"' or a.COMMENTAIRE like '"+COMMENTAIRE+"' or a.SECTEUR like '"+ID_SECTEUR+"')" +
							" ORDER BY a.ID_CLIENT " +
							" DESC";
					
					try {
						Historique.ecrire("Recherche à l'aide de la requete suivante : "+RECHERCHE);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						
						int nbClient = GestionDemandes.lanceRechercheConvNumMutFirst(RECHERCHE, jTable, modelDeTable);
						if (nbClient==0){//aucun enregistrement en remonte, on previens l'utilisateur
							 JOptionPane.showMessageDialog(null, "Aucun client ne correspond au(x)critére(s) demandé(s)",
										"Erreur", JOptionPane.WARNING_MESSAGE);
							 try {
								Historique.ecrire("Aucun client trouvé avec les criteres demandés : "+RECHERCHE);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else {
							try {
								Historique.ecrire("Resultat de la recherche : "+nbClient+" trouvé(s)");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
			});
		}
		return RecherchejButton;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(30, 396, 904, 177));
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jScrollPane.setFont(new Font("Candara", Font.PLAIN, 14));
			jScrollPane.setViewportView(getJTable());
			
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable(modelDeTable);
			jTable.setAutoCreateColumnsFromModel(true);
			jTable.setCellSelectionEnabled(false);
			//jTable.setColumnSelectionAllowed(true);
			jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTable.setRowSelectionAllowed(true);
			jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jTable.setColumnSelectionAllowed(false);
			jTable.setEnabled(true);
			jTable.setFont(new Font("Candara", Font.PLAIN, 12));
			jTable.setVisible(true);
			//jTable.setAutoCreateRowSorter(true);
			//jTable.setRowSorter(arg0)
			



			jTable.addMouseListener(new java.awt.event.MouseListener() {
				
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Thread_MAJJTable maj = new Thread_MAJJTable (jTable,NumClientjLabel, NomjTextField, PNomjTextField, AdressejTextField, CPjTextField, VillejTextField, TELjTextField, GSMjTextField, SecteurjTextField, CommentairejTextArea);
					maj.start();
				}
				public void mousePressed(java.awt.event.MouseEvent e) {
				}
				public void mouseReleased(java.awt.event.MouseEvent e) {
				}
				public void mouseEntered(java.awt.event.MouseEvent e) {
				}
				public void mouseExited(java.awt.event.MouseEvent e) {
				}
			});
			
			
		}
		return jTable;
	}

	/**
	 * This method initializes ModfjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModfjButton() {
		if (ModfjButton == null) {
			ModfjButton = new JButton();
			ModfjButton.setText("Modifier");
			ModfjButton.setSize(new Dimension(150, 40));
			ModfjButton.setIcon(new ImageIcon(getClass().getResource("/modifier.png")));
			ModfjButton.setFont(new Font("Candara", Font.PLAIN, 12));
			ModfjButton.setLocation(new Point(782, 229));
			ModfjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
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
					
					//String SECTEUR = SecteurjComboBox.getSelectedItem().toString();
					String SECTEUR = SecteurjTextField.getText();
					SECTEUR = ParseString.removeApostrophe(SECTEUR);
					
					String COMMENTAIRE = CommentairejTextArea.getText();
					COMMENTAIRE = ParseString.removeApostrophe(COMMENTAIRE);
					COMMENTAIRE = ParseString.removeLineFeed(COMMENTAIRE);
					COMMENTAIRE = ParseString.removeCageReturn(COMMENTAIRE);
					
					String CATEGORIE = CatégoriejComboBox.getSelectedItem().toString();
					String ID = NumClientjLabel.getText();
					int ID_CATEGORIE=0,ID_SECTEUR=0;
					
					
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
					
					if (ID_SECTEUR==0){
						GestionDemandes.executeRequete("INSERT INTO SECTEUR (SECTEUR) values ('"+SECTEUR+"')");
					}
					GETSECTEURID = "SELECT ID_SECTEUR FROM SECTEUR where SECTEUR='"+SECTEUR+"'";
					try {
						ID_SECTEUR = Integer.parseInt (GestionDemandes.executeRequeteEtRetourne1Champ(GETSECTEURID));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					String INSERT = "UPDATE CLIENT SET NOM='"+NOM+"' where ID_CLIENT='"+ID+"'";
					String INSERT1 = "UPDATE CLIENT SET PNOM='"+PNOM+"' where ID_CLIENT='"+ID+"'";
					String INSERT2 = "UPDATE CLIENT SET ADRESSE='"+ADRESSE+"' where ID_CLIENT='"+ID+"'";
					String INSERT3 = "UPDATE CLIENT SET CP='"+CP+"' where ID_CLIENT='"+ID+"'";
					String INSERT4 = "UPDATE CLIENT SET VILLE='"+VILLE+"' where ID_CLIENT='"+ID+"'";
					String INSERT5 = "UPDATE CLIENT SET TEL='"+TEL+"' where ID_CLIENT='"+ID+"'";
					String INSERT6 = "UPDATE CLIENT SET GSM='"+GSM+"' where ID_CLIENT='"+ID+"'";
					String INSERT7 = "UPDATE CLIENT SET SECTEUR='"+ID_SECTEUR+"' where ID_CLIENT='"+ID+"'";
					String INSERT8 = "UPDATE CLIENT SET COMMENTAIRE='"+COMMENTAIRE+"' where ID_CLIENT='"+ID+"'";
					String INSERT9 = "UPDATE CLIENT SET CATEGORIE='"+ID_CATEGORIE+"' where ID_CLIENT='"+ID+"'";
					
					boolean succes = GestionDemandes.executeRequete(INSERT);
					boolean succes1 = GestionDemandes.executeRequete(INSERT1);
					boolean succes2 = GestionDemandes.executeRequete(INSERT2);
					boolean succes3 = GestionDemandes.executeRequete(INSERT3);
					boolean succes4 = GestionDemandes.executeRequete(INSERT4);
					boolean succes5 = GestionDemandes.executeRequete(INSERT5);
					boolean succes6 = GestionDemandes.executeRequete(INSERT6);
					boolean succes7 = GestionDemandes.executeRequete(INSERT7);
					boolean succes8 = GestionDemandes.executeRequete(INSERT8);
					boolean succes9 = GestionDemandes.executeRequete(INSERT9);
					
					try {
						Historique.ecrire("Maj de la fiche client avec la requete suivante : "+INSERT);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Historique.ecrire("Maj de la fiche client avec la requete suivante : "+INSERT1);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Historique.ecrire("Maj de la fiche client avec la requete suivante : "+INSERT2);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Historique.ecrire("Maj de la fiche client avec la requete suivante : "+INSERT3);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Historique.ecrire("Maj de la fiche client avec la requete suivante : "+INSERT4);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Historique.ecrire("Maj de la fiche client avec la requete suivante : "+INSERT5);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Historique.ecrire("Maj de la fiche client avec la requete suivante : "+INSERT6);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Historique.ecrire("Maj de la fiche client avec la requete suivante : "+INSERT7);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Historique.ecrire("Maj de la fiche client avec la requete suivante : "+INSERT8);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						Historique.ecrire("Maj de la fiche client avec la requete suivante : "+INSERT9);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					if ((succes==true)&&(succes1==true)&&(succes2==true)&&(succes3==true)&&(succes4==true)&&(succes5==true)&&(succes6==true)&&(succes7==true)&&(succes8==true)&&(succes9==true)){//l'insert s'est correctement déroulé
						 JOptionPane.showMessageDialog(null, "Modification éfféctuée",
									"Ok", JOptionPane.INFORMATION_MESSAGE);
						 try {
							Historique.ecrire("Maj de la fiche client reussie .");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
					}
					if ((succes==false)||(succes1==false)||(succes2==false)||(succes3==false)||(succes4==false)||(succes5==false)||(succes6==false)||(succes7==false)||(succes8==false)||(succes9==false)){//il y a eu une erreur lors de l'insert
						JOptionPane.showMessageDialog(null, "Erreur lors de la modification \n\r" +
								"veuillez recommencer!!",
								"Erreur", JOptionPane.WARNING_MESSAGE);
						try {
							Historique.ecrire("Erreur lors de la modification de la fiche !!");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					ID_CATEGORIE=0;
					ID_SECTEUR=0;
					int nbcolonne = modelDeTable.getColumnCount();
					
					if (nbcolonne!=0){//si il y a des colonnes, on reinitialise le model
						modelDeTable = new DefaultTableModel();
						jTable.setModel(modelDeTable);
					}
					NOM = NomjTextField.getText();
					PNOM = PNomjTextField.getText();
					ADRESSE = AdressejTextField.getText();
					CP = CPjTextField.getText();
					VILLE = VillejTextField.getText();
					TEL = TELjTextField.getText();
					GSM = GSMjTextField.getText();
					//SECTEUR = SecteurjComboBox.getSelectedItem().toString();
					SECTEUR = SecteurjTextField.getText();
					COMMENTAIRE = CommentairejTextArea.getText();
					CATEGORIE = CatégoriejComboBox.getSelectedItem().toString();
					
					String GET_CATEGORIEID = "SELECT ID_CATEGORIE FROM CATEGORIE WHERE CATEGORIE = '"+CATEGORIE+"'";
					try {
						ID_CATEGORIE = Integer.parseInt (GestionDemandes.executeRequeteEtRetourne1Champ(GET_CATEGORIEID));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					String GET_SECTEURID = "SELECT ID_SECTEUR FROM SECTEUR WHERE SECTEUR = '"+SECTEUR+"'";
					try {
						ID_SECTEUR = Integer.parseInt (GestionDemandes.executeRequeteEtRetourne1Champ(GET_SECTEURID));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
					String RECHERCHE = "SELECT a.NOM,a.ADRESSE, a.CP, a.VILLE, a.TEL, a.GSM, a.SECTEUR, a.COMMENTAIRE, a.PNOM,a.ID_CLIENT " +
					"FROM CLIENT a " +
					"where a.CATEGORIE = '"+ID_CATEGORIE+"'" +
					" or (a.NOM like '"+NOM+"' or a.PNOM like '"+PNOM+"' or a.ADRESSE like '"+ADRESSE+"' or a.CP like '"+CP+"' or a.VILLE like '"+VILLE+"' or a.TEL like '"+TEL+"' or a.GSM like '" +GSM+"' or a.COMMENTAIRE like '"+COMMENTAIRE+"' or a.SECTEUR like '"+ID_SECTEUR+"')" +
					" ORDER BY a.ID_CLIENT " +
					" DESC";
					
					
					try {
						
						int nbClient = GestionDemandes.lanceRechercheConvNumMutFirst(RECHERCHE, jTable, modelDeTable);
						if (nbClient==0){//aucun enregistrement en remonte, on previens l'utilisateur
							 JOptionPane.showMessageDialog(null, "Aucun client ne correspond au(x)critére(s) demandé(s)",
										"Erreur", JOptionPane.WARNING_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				
				}
			});
		}
		return ModfjButton;
	}

	/**
	 * This method initializes SupprjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSupprjButton() {
		if (SupprjButton == null) {
			SupprjButton = new JButton();
			SupprjButton.setText("Supprimer");
			SupprjButton.setSize(new Dimension(150, 40));
			SupprjButton.setIcon(new ImageIcon(getClass().getResource("/supprimer.png")));
			SupprjButton.setFont(new Font("Candara", Font.PLAIN, 12));
			SupprjButton.setLocation(new Point(782, 337));
			SupprjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String ID = NumClientjLabel.getText();
					String DELETE = "DELETE FROM CLIENT WHERE ID_CLIENT='"+ID+"'";
					boolean succes = GestionDemandes.executeRequete(DELETE);
					if (succes==true){
						JOptionPane.showMessageDialog(null, "Supression Effectuée",
								"Ok", JOptionPane.INFORMATION_MESSAGE);
						try {
							Historique.ecrire("Supression de la la fiche a l'aide de la requete : " +DELETE);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if (succes==false){
						JOptionPane.showMessageDialog(null, "La spuression n'a pas fonctionnée!!",
								"Attention", JOptionPane.WARNING_MESSAGE);
						try {
							Historique.ecrire("Erreur lors de la supression de la la fiche a l'aide de la requete : " +DELETE);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					int ID_CATEGORIE=0,ID_SECTEUR=0;
					int nbcolonne = modelDeTable.getColumnCount();
					
					if (nbcolonne!=0){//si il y a des colonnes, on reinitialise le model
						modelDeTable = new DefaultTableModel();
						jTable.setModel(modelDeTable);
					}
					String NOM = NomjTextField.getText();
					String PNOM = PNomjTextField.getText();
					String ADRESSE = AdressejTextField.getText();
					String CP = CPjTextField.getText();
					String VILLE = VillejTextField.getText();
					String TEL = TELjTextField.getText();
					String GSM = GSMjTextField.getText();
					//String SECTEUR = SecteurjComboBox.getSelectedItem().toString();
					String SECTEUR = SecteurjTextField.getText();
					String COMMENTAIRE = CommentairejTextArea.getText();
					String CATEGORIE = CatégoriejComboBox.getSelectedItem().toString();
					
					String GET_CATEGORIEID = "SELECT ID_CATEGORIE FROM CATEGORIE WHERE CATEGORIE = '"+CATEGORIE+"'";
					try {
						ID_CATEGORIE = Integer.parseInt (GestionDemandes.executeRequeteEtRetourne1Champ(GET_CATEGORIEID));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					String GET_SECTEURID = "SELECT ID_SECTEUR FROM SECTEUR WHERE SECTEUR = '"+SECTEUR+"'";
					try {
						ID_SECTEUR = Integer.parseInt (GestionDemandes.executeRequeteEtRetourne1Champ(GET_SECTEURID));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
					String RECHERCHE = "SELECT a.NOM,a.ADRESSE, a.CP, a.VILLE, a.TEL, a.GSM, a.SECTEUR, a.COMMENTAIRE, a.PNOM,a.ID_CLIENT " +
					"FROM CLIENT a " +
					"where a.CATEGORIE = '"+ID_CATEGORIE+"'" +
					" or (a.NOM like '"+NOM+"' or a.PNOM like '"+PNOM+"' or a.ADRESSE like '"+ADRESSE+"' or a.CP like '"+CP+"' or a.VILLE like '"+VILLE+"' or a.TEL like '"+TEL+"' or a.GSM like '" +GSM+"' or a.COMMENTAIRE like '"+COMMENTAIRE+"' or a.SECTEUR like '"+ID_SECTEUR+"')" +
					" ORDER BY a.ID_CLIENT " +
					" DESC";
					
					
					try {
						
						int nbClient = GestionDemandes.lanceRechercheConvNumMutFirst(RECHERCHE, jTable, modelDeTable);
						if (nbClient==0){//aucun enregistrement en remonte, on previens l'utilisateur
							 JOptionPane.showMessageDialog(null, "Aucun client ne correspond au(x)critére(s) demandé(s)",
										"Erreur", JOptionPane.WARNING_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				
					
				}
			});
		}
		return SupprjButton;
	}

	/**
	 * This method initializes SecteurjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSecteurjTextField() {
		if (SecteurjTextField == null) {
			SecteurjTextField = new JTextField();
			SecteurjTextField.setLocation(new Point(32, 354));
			SecteurjTextField.setFont(new Font("Candara", Font.PLAIN, 14));
			SecteurjTextField.setSize(new Dimension(172, 25));
		}
		return SecteurjTextField;
	}

	/**
	 * This method initializes PlanjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPlanjButton() {
		if (PlanjButton == null) {
			PlanjButton = new JButton();
			PlanjButton.setBounds(new Rectangle(210, 350, 172, 32));
			PlanjButton.setToolTipText("se base sur l'adresse et le nom de la ville");
			PlanjButton.setText(" Plan mappy");
			PlanjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String adresse = AdressejTextField.getText();
					
					String ville = VillejTextField.getText();
					
					if (adresse.contains(",")==true){
						adresse=adresse.replace(",", "+");
					}
					if (adresse.contains(" ")==true){
						adresse=adresse.replace(" ", "+");
					}
					
					if ((ville.equals("")==false)&&(adresse.equals("")==false)){
						String URL = "URL=http://fr.mappy.com/#d="+adresse+","+ville+"&p=map\r";
						File racourci = new File (GestionRepertoire.RecupRepTravail()+"\\raccourci.url");
						if (racourci.exists()==true){
							racourci.delete();
						}
						try {
							racourci.createNewFile();
							WriteFile.WriteLine("[InternetShortcut]\r", GestionRepertoire.RecupRepTravail()+"\\raccourci.url");
							WriteFile.WriteLine(URL, GestionRepertoire.RecupRepTravail()+"\\raccourci.url");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						OpenWithDefaultViewer.open(GestionRepertoire.RecupRepTravail()+"\\raccourci.url");
						
						boolean succes =racourci.delete();
						if (succes==false){
							racourci.deleteOnExit();
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "l'adresse et / ou la ville sont vides\r\n Avez vous selecitonné une fiche dans le tableau?", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
			});
		}
		return PlanjButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
