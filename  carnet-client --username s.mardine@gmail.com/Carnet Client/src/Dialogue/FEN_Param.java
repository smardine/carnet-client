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
	private JComboBox Cat�goriejComboBox = null;
	private JLabel jLabel = null;
	private JTextField NouvelleCat�goriejTextField = null;
	private JLabel jLabel1 = null;
	private JButton AjoutCat�goriejButton = null;
	private JButton ModifCat�goriejButton = null;
	private JButton SupprCat�goriejButton = null;
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
		remplirListe (Cat�goriejComboBox,"SELECT CATEGORIE FROM CATEGORIE");
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
			jTabbedPane.addTab("Cat�gorie", null, getJPanel(), null);
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
			jLabel.setText(" Cat�gorie(s) existante");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setFont(new Font("Candara", Font.PLAIN, 12));
			jPanel.add(getCat�goriejComboBox(), null);
			jPanel.add(jLabel, null);
			jPanel.add(getNouvelleCat�goriejTextField(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getAjoutCat�goriejButton(), null);
			jPanel.add(getModifCat�goriejButton(), null);
			jPanel.add(getSupprCat�goriejButton(), null);
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
	 * This method initializes Cat�goriejComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCat�goriejComboBox() {
		if (Cat�goriejComboBox == null) {
			Cat�goriejComboBox = new JComboBox();
			Cat�goriejComboBox.setLocation(new Point(38, 35));
			Cat�goriejComboBox.setFont(new Font("Candara", Font.PLAIN, 12));
			Cat�goriejComboBox.setSize(new Dimension(225, 35));
		}
		return Cat�goriejComboBox;
	}

	/**
	 * This method initializes NouvelleCat�goriejTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNouvelleCat�goriejTextField() {
		if (NouvelleCat�goriejTextField == null) {
			NouvelleCat�goriejTextField = new JTextField();
			NouvelleCat�goriejTextField.setLocation(new Point(38, 109));
			NouvelleCat�goriejTextField.setFont(new Font("Candara", Font.PLAIN, 12));
			NouvelleCat�goriejTextField.setSize(new Dimension(186, 35));
		}
		return NouvelleCat�goriejTextField;
	}

	/**
	 * This method initializes AjoutCat�goriejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAjoutCat�goriejButton() {
		if (AjoutCat�goriejButton == null) {
			AjoutCat�goriejButton = new JButton();
			AjoutCat�goriejButton.setIcon(new ImageIcon(getClass().getResource("/ajouter.png")));
			AjoutCat�goriejButton.setLocation(new Point(282, 109));
			AjoutCat�goriejButton.setSize(new Dimension(150, 35));
			AjoutCat�goriejButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			AjoutCat�goriejButton.setFont(new Font("Candara", Font.PLAIN, 12));
			AjoutCat�goriejButton.setText(" Ajouter");
			AjoutCat�goriejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String NOUVELLE = NouvelleCat�goriejTextField.getText();
					String INSERTCATEGORIE = "INSERT INTO CATEGORIE (CATEGORIE) VALUES ('"+NOUVELLE+"')";
					
					boolean succes = GestionDemandes.executeRequete(INSERTCATEGORIE);
					if (succes==true){//l'insert s'est correctement d�roul�
						 JOptionPane.showMessageDialog(null, "Cat�gorie ajout�e",
									"Ok", JOptionPane.INFORMATION_MESSAGE);
						 NouvelleCat�goriejTextField.setText("");
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
					
					remplirListe (Cat�goriejComboBox,"SELECT CATEGORIE FROM CATEGORIE");
					
					
				}
			});
		}
		return AjoutCat�goriejButton;
	}

	/**
	 * This method initializes ModifCat�goriejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModifCat�goriejButton() {
		if (ModifCat�goriejButton == null) {
			ModifCat�goriejButton = new JButton();
			ModifCat�goriejButton.setIcon(new ImageIcon(getClass().getResource("/modifier.png")));
			ModifCat�goriejButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			ModifCat�goriejButton.setLocation(new Point(282, 35));
			ModifCat�goriejButton.setSize(new Dimension(150, 35));
			ModifCat�goriejButton.setFont(new Font("Candara", Font.PLAIN, 12));
			ModifCat�goriejButton.setText(" Modifier");
			ModifCat�goriejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int ID_CATEGORIE=0;
					String NOUVELLE = NouvelleCat�goriejTextField.getText();
					String CATEGORIE = Cat�goriejComboBox.getSelectedItem().toString();
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
					if (succes==true){//l'insert s'est correctement d�roul�
						 JOptionPane.showMessageDialog(null, "Cat�gorie modifi�e",
									"Ok", JOptionPane.INFORMATION_MESSAGE);
						 NouvelleCat�goriejTextField.setText("");
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
					
					remplirListe (Cat�goriejComboBox,"SELECT CATEGORIE FROM CATEGORIE");
				}
			});
		}
		return ModifCat�goriejButton;
	}

	/**
	 * This method initializes SupprCat�goriejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSupprCat�goriejButton() {
		if (SupprCat�goriejButton == null) {
			SupprCat�goriejButton = new JButton();
			SupprCat�goriejButton.setIcon(new ImageIcon(getClass().getResource("/supprimer.png")));
			SupprCat�goriejButton.setText(" Supprimer");
			SupprCat�goriejButton.setLocation(new Point(475, 35));
			SupprCat�goriejButton.setSize(new Dimension(150, 35));
			SupprCat�goriejButton.setFont(new Font("Candara", Font.PLAIN, 12));
			SupprCat�goriejButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			SupprCat�goriejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int ID_CATEGORIE=0;
					//String NOUVELLE = NouvellejTextField.getText();
					String CATEGORIE = Cat�goriejComboBox.getSelectedItem().toString();
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
					if (succes==true){//l'insert s'est correctement d�roul�
						try {
							Historique.ecrire("Supression d'une categorie avec la requete : "+SUPPRCAT);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						String UpdteClient = "UPDATE CLIENT SET CATEGORIE = 0 where CATEGORIE ='"+ID_CATEGORIE+"'";
						boolean succes1 = GestionDemandes.executeRequete(UpdteClient);
						
						if (succes1==true){
							 JOptionPane.showMessageDialog(null, "Cat�gorie suprim�e",
										"Ok", JOptionPane.INFORMATION_MESSAGE);	
							 NouvelleCat�goriejTextField.setText("");
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
					Cat�goriejComboBox.removeAll();
					remplirListe (Cat�goriejComboBox,"SELECT CATEGORIE FROM CATEGORIE");
				}
				
			});
		}
		return SupprCat�goriejButton;
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
					if (succes==true){//l'insert s'est correctement d�roul�
						 JOptionPane.showMessageDialog(null, "Secteur ajout�",
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
					if (succes==true){//l'insert s'est correctement d�roul�
						 JOptionPane.showMessageDialog(null, "Secteur modifi�",
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
					if (succes==true){//l'insert s'est correctement d�roul�
						try {
							Historique.ecrire("Supression d'un secteur avec la requete : "+SUPPRSECTEUR);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						String UpdteClient = "UPDATE CLIENT SET SECTEUR = 0 where SECTEUR ='"+ID_SECTEUR+"'";
						boolean succes1 = GestionDemandes.executeRequete(UpdteClient);
						
						if (succes1==true){
							 JOptionPane.showMessageDialog(null, "Secteur suprim�",
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
