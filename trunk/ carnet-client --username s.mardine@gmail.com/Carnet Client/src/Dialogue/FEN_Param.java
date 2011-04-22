package Dialogue;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Utilitaires.Historique;
import accesBDD.GestionDemandes;

public class FEN_Param extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JComboBox CatégoriejComboBox = null;
	private JLabel jLabel = null;
	private JTextField NouvelleCatégoriejTextField = null;
	private JLabel jLabel1 = null;
	private JButton AjoutCatégoriejButton = null;
	private JButton ModifCatégoriejButton = null;
	private JButton SupprCatégoriejButton = null;
	private JComboBox SecteurjComboBox = null;
	private JLabel jLabel2 = null;
	private JTextField NouveauSecteurjTextField = null;
	private JLabel jLabel11 = null;
	private JButton AjoutSecteurjButton = null;
	private JButton ModifSecteurjButton = null;
	private JButton SupprSecteurjButton = null;
	/**
	 * This is the default constructor
	 */
	public FEN_Param() {
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
		this.setSize(728, 257);
		this.setContentPane(getJTabbedPane());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/parametres.png")));
		this.setTitle(" Parametres");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setFont(new Font("Candara", Font.BOLD, 12));
			jTabbedPane.addTab("Catégorie", null, getJPanel(), null);
			jTabbedPane.addTab("Secteur", null, getJPanel1(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(38, 86, 128, 21));
			jLabel1.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel1.setText(" Nouvelle");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(38, 9, 183, 24));
			jLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel.setText(" Catégorie(s) existante");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setFont(new Font("Candara", Font.PLAIN, 12));
			jPanel.add(getCatégoriejComboBox(), null);
			jPanel.add(jLabel, null);
			jPanel.add(getNouvelleCatégoriejTextField(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getAjoutCatégoriejButton(), null);
			jPanel.add(getModifCatégoriejButton(), null);
			jPanel.add(getSupprCatégoriejButton(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel11 = new JLabel();
			jLabel11.setBounds(new Rectangle(37, 86, 128, 21));
			jLabel11.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel11.setText(" Nouveau");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(37, 9, 183, 24));
			jLabel2.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel2.setText(" Secteur(s) existant(s)");
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.setFont(new Font("Candara", Font.PLAIN, 12));
			jPanel1.add(getSecteurjComboBox(), null);
			jPanel1.add(jLabel2, null);
			jPanel1.add(getNouveauSecteurjTextField(), null);
			jPanel1.add(jLabel11, null);
			jPanel1.add(getAjoutSecteurjButton(), null);
			jPanel1.add(getModifSecteurjButton(), null);
			jPanel1.add(getSupprSecteurjButton(), null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes CatégoriejComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCatégoriejComboBox() {
		if (CatégoriejComboBox == null) {
			CatégoriejComboBox = new JComboBox();
			CatégoriejComboBox.setLocation(new Point(38, 35));
			CatégoriejComboBox.setFont(new Font("Candara", Font.PLAIN, 12));
			CatégoriejComboBox.setSize(new Dimension(225, 35));
		}
		return CatégoriejComboBox;
	}

	/**
	 * This method initializes NouvelleCatégoriejTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNouvelleCatégoriejTextField() {
		if (NouvelleCatégoriejTextField == null) {
			NouvelleCatégoriejTextField = new JTextField();
			NouvelleCatégoriejTextField.setLocation(new Point(38, 109));
			NouvelleCatégoriejTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			NouvelleCatégoriejTextField.setSize(new Dimension(186, 35));
		}
		return NouvelleCatégoriejTextField;
	}

	/**
	 * This method initializes AjoutCatégoriejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAjoutCatégoriejButton() {
		if (AjoutCatégoriejButton == null) {
			AjoutCatégoriejButton = new JButton();
			AjoutCatégoriejButton.setIcon(new ImageIcon(getClass().getResource("/ajouter.png")));
			AjoutCatégoriejButton.setLocation(new Point(282, 109));
			AjoutCatégoriejButton.setSize(new Dimension(150, 35));
			AjoutCatégoriejButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			AjoutCatégoriejButton.setFont(new Font("Candara", Font.PLAIN, 12));
			AjoutCatégoriejButton.setText(" Ajouter");
			AjoutCatégoriejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String NOUVELLE = NouvelleCatégoriejTextField.getText();
					String INSERTCATEGORIE = "INSERT INTO CATEGORIE (CATEGORIE) VALUES ('"+NOUVELLE+"')";
					
					boolean succes = GestionDemandes.executeRequete(INSERTCATEGORIE);
					if (succes==true){//l'insert s'est correctement déroulé
						 JOptionPane.showMessageDialog(null, "Catégorie ajoutée",
									"Ok", JOptionPane.INFORMATION_MESSAGE);
						 NouvelleCatégoriejTextField.setText("");
						 try {
							Historique.ecrire("Ajout d'une categorie avec la requete : "+INSERTCATEGORIE);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if (succes==false){//il y a eu une erreur lors de l'insert
						JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout \n\r" +
								"veuillez recommencer!!",
								"Erreur", JOptionPane.WARNING_MESSAGE);
						try {
							Historique.ecrire("Erreur lors de l'ajout d'une categorie avec la requete : "+INSERTCATEGORIE);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					remplirListe (CatégoriejComboBox,"SELECT CATEGORIE FROM CATEGORIE");
					
					
				}
			});
		}
		return AjoutCatégoriejButton;
	}

	/**
	 * This method initializes ModifCatégoriejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModifCatégoriejButton() {
		if (ModifCatégoriejButton == null) {
			ModifCatégoriejButton = new JButton();
			ModifCatégoriejButton.setIcon(new ImageIcon(getClass().getResource("/modifier.png")));
			ModifCatégoriejButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			ModifCatégoriejButton.setLocation(new Point(282, 35));
			ModifCatégoriejButton.setSize(new Dimension(150, 35));
			ModifCatégoriejButton.setFont(new Font("Candara", Font.PLAIN, 12));
			ModifCatégoriejButton.setText(" Modifier");
			ModifCatégoriejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int ID_CATEGORIE=0;
					String NOUVELLE = NouvelleCatégoriejTextField.getText();
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
					
					String UPDATECAT = "UPDATE CATEGORIE SET CATEGORIE = '"+NOUVELLE+"' where ID_CATEGORIE='"+ID_CATEGORIE+"'";
					
					boolean succes = GestionDemandes.executeRequete(UPDATECAT);
					if (succes==true){//l'insert s'est correctement déroulé
						 JOptionPane.showMessageDialog(null, "Catégorie modifiée",
									"Ok", JOptionPane.INFORMATION_MESSAGE);
						 NouvelleCatégoriejTextField.setText("");
							try {
								Historique.ecrire("Modification d'une categorie avec la requete : "+UPDATECAT);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

					}
					if (succes==false){//il y a eu une erreur lors de l'insert
						JOptionPane.showMessageDialog(null, "Erreur lors de la modification \n\r" +
								"veuillez recommencer!!",
								"Erreur", JOptionPane.WARNING_MESSAGE);
						try {
							Historique.ecrire("Erreur lors de la modification d'une categorie avec la requete : "+UPDATECAT);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					//ReadFile.supprElementsList(jComboBox,catASuppr,"IniFile/categorie.properties");
					//jComboBox.removeAll();
					
					remplirListe (CatégoriejComboBox,"SELECT CATEGORIE FROM CATEGORIE");
				}
			});
		}
		return ModifCatégoriejButton;
	}

	/**
	 * This method initializes SupprCatégoriejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSupprCatégoriejButton() {
		if (SupprCatégoriejButton == null) {
			SupprCatégoriejButton = new JButton();
			SupprCatégoriejButton.setIcon(new ImageIcon(getClass().getResource("/supprimer.png")));
			SupprCatégoriejButton.setText(" Supprimer");
			SupprCatégoriejButton.setLocation(new Point(475, 35));
			SupprCatégoriejButton.setSize(new Dimension(150, 35));
			SupprCatégoriejButton.setFont(new Font("Candara", Font.PLAIN, 12));
			SupprCatégoriejButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			SupprCatégoriejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int ID_CATEGORIE=0;
					//String NOUVELLE = NouvellejTextField.getText();
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
					
					String SUPPRCAT = "DELETE FROM CATEGORIE WHERE ID_CATEGORIE='"+ID_CATEGORIE+"'";
					
					boolean succes = GestionDemandes.executeRequete(SUPPRCAT);
					if (succes==true){//l'insert s'est correctement déroulé
						try {
							Historique.ecrire("Supression d'une categorie avec la requete : "+SUPPRCAT);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						String UpdteClient = "UPDATE CLIENT SET CATEGORIE = 0 where CATEGORIE ='"+ID_CATEGORIE+"'";
						boolean succes1 = GestionDemandes.executeRequete(UpdteClient);
						
						if (succes1==true){
							 JOptionPane.showMessageDialog(null, "Catégorie suprimée",
										"Ok", JOptionPane.INFORMATION_MESSAGE);	
							 NouvelleCatégoriejTextField.setText("");
							 try {
								Historique.ecrire("Maj des fiches client suite a la supression d'une categorie avec la requete : "+UpdteClient);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						 
					}
					if (succes==false){//il y a eu une erreur lors de l'insert
						JOptionPane.showMessageDialog(null, "Erreur lors de la supression \n\r" +
								"veuillez recommencer!!",
								"Erreur", JOptionPane.WARNING_MESSAGE);
						try {
							Historique.ecrire("Erreur lors de la supression d'une categorie avec la requete : "+SUPPRCAT);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					//ReadFile.supprElementsList(jComboBox,catASuppr,"IniFile/categorie.properties");
					//jComboBox.removeAll();
					CatégoriejComboBox.removeAll();
					remplirListe (CatégoriejComboBox,"SELECT CATEGORIE FROM CATEGORIE");
				}
				
			});
		}
		return SupprCatégoriejButton;
	}

	/**
	 * This method initializes SecteurjComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSecteurjComboBox() {
		if (SecteurjComboBox == null) {
			SecteurjComboBox = new JComboBox();
			SecteurjComboBox.setFont(new Font("Candara", Font.PLAIN, 12));
			SecteurjComboBox.setSize(new Dimension(225, 35));
			SecteurjComboBox.setLocation(new Point(38, 35));
		}
		return SecteurjComboBox;
	}

	/**
	 * This method initializes NouveauSecteurjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNouveauSecteurjTextField() {
		if (NouveauSecteurjTextField == null) {
			NouveauSecteurjTextField = new JTextField();
			NouveauSecteurjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			NouveauSecteurjTextField.setSize(new Dimension(186, 35));
			NouveauSecteurjTextField.setLocation(new Point(38, 109));
		}
		return NouveauSecteurjTextField;
	}

	/**
	 * This method initializes AjoutSecteurjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAjoutSecteurjButton() {
		if (AjoutSecteurjButton == null) {
			AjoutSecteurjButton = new JButton();
			AjoutSecteurjButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			AjoutSecteurjButton.setIcon(new ImageIcon(getClass().getResource("/ajouter.png")));
			AjoutSecteurjButton.setText(" Ajouter");
			AjoutSecteurjButton.setLocation(new Point(280, 109));
			AjoutSecteurjButton.setSize(new Dimension(150, 35));
			AjoutSecteurjButton.setFont(new Font("Candara", Font.PLAIN, 12));
			AjoutSecteurjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String NOUVELLE = NouveauSecteurjTextField.getText();
					String INSERTSECTEUR = "INSERT INTO SECTEUR (SECTEUR) VALUES ('"+NOUVELLE+"')";
					
					boolean succes = GestionDemandes.executeRequete(INSERTSECTEUR);
					if (succes==true){//l'insert s'est correctement déroulé
						 JOptionPane.showMessageDialog(null, "Secteur ajouté",
									"Ok", JOptionPane.INFORMATION_MESSAGE);
						 NouveauSecteurjTextField.setText("");
							try {
								Historique.ecrire("Ajout d'un secteur avec la requete : "+INSERTSECTEUR);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					if (succes==false){//il y a eu une erreur lors de l'insert
						JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout \n\r" +
								"veuillez recommencer!!",
								"Erreur", JOptionPane.WARNING_MESSAGE);
						try {
							Historique.ecrire("Erreur lors de l'ajout d'un secteur avec la requete : "+INSERTSECTEUR);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					remplirListe (SecteurjComboBox,"SELECT SECTEUR FROM SECTEUR");
					
					
				}
			});
		}
		return AjoutSecteurjButton;
	}

	/**
	 * This method initializes ModifSecteurjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModifSecteurjButton() {
		if (ModifSecteurjButton == null) {
			ModifSecteurjButton = new JButton();
			ModifSecteurjButton.setIcon(new ImageIcon(getClass().getResource("/modifier.png")));
			ModifSecteurjButton.setText(" Modifier");
			ModifSecteurjButton.setFont(new Font("Candara", Font.PLAIN, 12));
			ModifSecteurjButton.setLocation(new Point(280, 35));
			ModifSecteurjButton.setSize(new Dimension(150, 35));
			ModifSecteurjButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			ModifSecteurjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int ID_SECTEUR=0;
					String NOUVELLE = NouveauSecteurjTextField.getText();
					String SECTEUR = SecteurjComboBox.getSelectedItem().toString();
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
					
					String UPDATESECTEUR = "UPDATE SECTEUR SET SECTEUR = '"+NOUVELLE+"' where ID_SECTEUR='"+ID_SECTEUR+"'";
					
					boolean succes = GestionDemandes.executeRequete(UPDATESECTEUR);
					if (succes==true){//l'insert s'est correctement déroulé
						 JOptionPane.showMessageDialog(null, "Secteur modifié",
									"Ok", JOptionPane.INFORMATION_MESSAGE);
						 NouveauSecteurjTextField.setText("");
						 try {
							Historique.ecrire("Modification d'un secteur avec la requete : "+UPDATESECTEUR);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if (succes==false){//il y a eu une erreur lors de l'insert
						JOptionPane.showMessageDialog(null, "Erreur lors de la modification \n\r" +
								"veuillez recommencer!!",
								"Erreur", JOptionPane.WARNING_MESSAGE);
						 try {
							Historique.ecrire("Erreur lors de la modification d'un secteur avec la requete : "+UPDATESECTEUR);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					//ReadFile.supprElementsList(jComboBox,catASuppr,"IniFile/categorie.properties");
					//jComboBox.removeAll();
					
					remplirListe (SecteurjComboBox,"SELECT SECTEUR FROM SECTEUR");
				}
			});
		}
		return ModifSecteurjButton;
	}

	/**
	 * This method initializes SupprSecteurjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSupprSecteurjButton() {
		if (SupprSecteurjButton == null) {
			SupprSecteurjButton = new JButton();
			SupprSecteurjButton.setIcon(new ImageIcon(getClass().getResource("/supprimer.png")));
			SupprSecteurjButton.setText(" Supprimer");
			SupprSecteurjButton.setFont(new Font("Candara", Font.PLAIN, 12));
			SupprSecteurjButton.setLocation(new Point(475, 35));
			SupprSecteurjButton.setSize(new Dimension(150, 35));
			SupprSecteurjButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			SupprSecteurjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int ID_SECTEUR=0;
					//String NOUVELLE = NouvellejTextField.getText();
					String SECTEUR = SecteurjComboBox.getSelectedItem().toString();
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
					
					String SUPPRSECTEUR = "DELETE FROM SECTEUR WHERE ID_SECTEUR='"+ID_SECTEUR+"'";
					
					boolean succes = GestionDemandes.executeRequete(SUPPRSECTEUR);
					if (succes==true){//l'insert s'est correctement déroulé
						try {
							Historique.ecrire("Supression d'un secteur avec la requete : "+SUPPRSECTEUR);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						String UpdteClient = "UPDATE CLIENT SET SECTEUR = 0 where SECTEUR ='"+ID_SECTEUR+"'";
						boolean succes1 = GestionDemandes.executeRequete(UpdteClient);
						
						if (succes1==true){
							 JOptionPane.showMessageDialog(null, "Secteur suprimé",
										"Ok", JOptionPane.INFORMATION_MESSAGE);	
							 NouveauSecteurjTextField.setText("");
							 try {
								Historique.ecrire("Maj des fiche client suite a la supression d'un secteur avec la requete : "+SUPPRSECTEUR);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						 
					}
					if (succes==false){//il y a eu une erreur lors de l'insert
						JOptionPane.showMessageDialog(null, "Erreur lors de la supression \n\r" +
								"veuillez recommencer!!",
								"Erreur", JOptionPane.WARNING_MESSAGE);
						try {
							Historique.ecrire("Erreur a la supression d'un secteur avec la requete : "+SUPPRSECTEUR);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					//ReadFile.supprElementsList(jComboBox,catASuppr,"IniFile/categorie.properties");
					//jComboBox.removeAll();
					//
					remplirListe (SecteurjComboBox,"SELECT SECTEUR FROM SECTEUR");
				}
			});
		}
		return SupprSecteurjButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
