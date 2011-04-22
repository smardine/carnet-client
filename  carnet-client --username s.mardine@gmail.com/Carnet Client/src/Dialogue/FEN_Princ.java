package Dialogue;
import ini_Manager.ConfigMgt;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import Utilitaires.GestionRepertoire;
import Utilitaires.Historique;
import Utilitaires.OpenWithDefaultViewer;
import Utilitaires.VariableEnvironement;

public class FEN_Princ extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenu FichierjMenu = null;
	private JMenu AidejMenu = null;
	private JMenuItem RecherchejMenuItem = null;
	private JMenuItem AjoutjMenuItem = null;
	private JMenuItem ExplorerjMenuItem = null;
	private JMenuItem AProposjMenuItem = null;
	private JMenuBar jJMenuBar = null;
	private JButton NouveujButton = null;
	private JButton RecherchejButton = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JButton ParamjButton = null;
	private JLabel jLabel2 = null;
	private JButton SavejButton = null;
	private JLabel jLabel21 = null;
	private JMenuItem ParamjMenuItem = null;
	private JMenuItem SauvegardejMenuItem = null;
	private JMenuItem MailjMenuItem = null;
	private JButton QuitjButton = null;
	private JLabel jLabel4 = null;
	/**
	 * This method initializes FichierjMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFichierjMenu() {
		if (FichierjMenu == null) {
			FichierjMenu = new JMenu();
			FichierjMenu.setText("Fichier");
			FichierjMenu.setFont(new Font("Dialog", Font.PLAIN, 12));
			FichierjMenu.add(getRecherchejMenuItem());
			FichierjMenu.add(getAjoutjMenuItem());
			FichierjMenu.add(getParamjMenuItem());
			FichierjMenu.add(getSauvegardejMenuItem());
		}
		return FichierjMenu;
	}

	/**
	 * This method initializes AidejMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getAidejMenu() {
		if (AidejMenu == null) {
			AidejMenu = new JMenu();
			AidejMenu.setText("Aide");
			AidejMenu.setFont(new Font("Dialog", Font.PLAIN, 12));
			AidejMenu.add(getExplorerjMenuItem());
			AidejMenu.add(getMailjMenuItem());
			AidejMenu.add(getAProposjMenuItem());
		}
		return AidejMenu;
	}

	/**
	 * This method initializes RecherchejMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getRecherchejMenuItem() {
		if (RecherchejMenuItem == null) {
			RecherchejMenuItem = new JMenuItem();
			RecherchejMenuItem.setText("Rechercher");
			RecherchejMenuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
			RecherchejMenuItem.setIcon(new ImageIcon(getClass().getResource("/recherche_petit.png")));
			RecherchejMenuItem.setActionCommand("Rechercher");
			RecherchejMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (e.getActionCommand().equals("Rechercher")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new FEN_Recherche();
						}
					}
			});
		}
		return RecherchejMenuItem;
	}

	/**
	 * This method initializes AjoutjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAjoutjMenuItem() {
		if (AjoutjMenuItem == null) {
			AjoutjMenuItem = new JMenuItem();
			AjoutjMenuItem.setText("Nouvelle Fiche");
			AjoutjMenuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
			AjoutjMenuItem.setIcon(new ImageIcon(getClass().getResource("/nouveau_petit.png")));
			AjoutjMenuItem.setActionCommand("Nouvelle Fiche");
			
			AjoutjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (e.getActionCommand().equals("Nouvelle Fiche")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new FEN_Ajout();
						}
					}	
				
			});
		}
		return AjoutjMenuItem;
	}

	/**
	 * This method initializes ExplorerjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExplorerjMenuItem() {
		if (ExplorerjMenuItem == null) {
			ExplorerjMenuItem = new JMenuItem();
			ExplorerjMenuItem.setText("Explorer");
			ExplorerjMenuItem.setActionCommand("Explorer");
			ExplorerjMenuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
			ExplorerjMenuItem.setIcon(new ImageIcon(getClass().getResource("/loupe.png")));
			ExplorerjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (e.getActionCommand().equals("Explorer")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						OpenWithDefaultViewer.open(GestionRepertoire.RecupRepTravail());
						}
					}
				
			});
		}
		return ExplorerjMenuItem;
	}

	/**
	 * This method initializes AProposjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAProposjMenuItem() {
		if (AProposjMenuItem == null) {
			AProposjMenuItem = new JMenuItem();
			AProposjMenuItem.setText("A Propos");
			AProposjMenuItem.setActionCommand("A Propos");
			AProposjMenuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
			AProposjMenuItem.setIcon(new ImageIcon(getClass().getResource("/interrogation.png")));
			AProposjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (e.getActionCommand().equals("A Propos")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new FEN_APropos();
						}
					}
			});
		}
		return AProposjMenuItem;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFichierjMenu());
			jJMenuBar.add(getAidejMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes NouveujButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNouveujButton() {
		if (NouveujButton == null) {
			NouveujButton = new JButton();
			NouveujButton.setLocation(new Point(36, 32));
			NouveujButton.setActionCommand("Nouvelle Fiche");
			NouveujButton.setIcon(new ImageIcon(getClass().getResource("/nouveau.png")));
			NouveujButton.setFont(new Font("Dialog", Font.PLAIN, 12));
			NouveujButton.setSize(new Dimension(120, 120));
			NouveujButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				//	System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (e.getActionCommand().equals("Nouvelle Fiche")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new FEN_Ajout();
						}
				}
			});
			
		}
		return NouveujButton;
	}

	/**
	 * This method initializes RecherchejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRecherchejButton() {
		if (RecherchejButton == null) {
			RecherchejButton = new JButton();
			RecherchejButton.setLocation(new Point(192, 32));
			RecherchejButton.setIcon(new ImageIcon(getClass().getResource("/recherche.png")));
			RecherchejButton.setActionCommand("Rechercher");
			RecherchejButton.setFont(new Font("Dialog", Font.PLAIN, 12));
			RecherchejButton.setSize(new Dimension(120,120));
			RecherchejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (e.getActionCommand().equals("Rechercher")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new FEN_Recherche();
						}
				}
			});
		}
		return RecherchejButton;
	}

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FEN_Princ thisClass = new FEN_Princ();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
				
			}
		});
	}*/

	/**
	 * This is the default constructor
	 */
	public FEN_Princ() {
		super();
		initialize();
		
		
					
					
	
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(512, 457);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/carnet.png")));
		this.setResizable(false);
		this.setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Carnet Client");
		this.setVisible(true);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				//System.out.println("windowClosing()"); // TODO Auto-generated Event stub windowClosing()
				//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				File fbTexte = new File (GestionRepertoire.RecupRepTravail()+"\\fbserver.txt");
				boolean succesDelete = fbTexte.delete();
				if (succesDelete==false){
					fbTexte.deleteOnExit();
				}
				
				new Fen_Fermeture();
				
				
			
			}
		});
		this.setLocationRelativeTo(null);
	
		
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel4 = new JLabel();
			jLabel4.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel4.setLocation(new Point(296, 355));
			jLabel4.setSize(new Dimension(120, 30));
			jLabel4.setText(" Quitter");
			jLabel21 = new JLabel();
			jLabel21.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel21.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel21.setText(" Sauvegarde");
			jLabel21.setLocation(new Point(88, 355));
			jLabel21.setSize(new Dimension(120, 30));
			jLabel21.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabel2 = new JLabel();
			jLabel2.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel2.setLocation(new Point(348, 165));
			jLabel2.setSize(new Dimension(120, 30));
			jLabel2.setText(" Parametre(s)");
			jLabel1 = new JLabel();
			jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel1.setLocation(new Point(192, 164));
			jLabel1.setSize(new Dimension(120, 30));
			jLabel1.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabel1.setText(" Rech / Modif / Suppr");
			jLabel = new JLabel();
			jLabel.setHorizontalAlignment(SwingConstants.CENTER);
			jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabel.setLocation(new Point(36, 164));
			jLabel.setSize(new Dimension(120, 30));
			jLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabel.setText(" Ajouter");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getNouveujButton(), null);
			jContentPane.add(getRecherchejButton(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getParamjButton(), null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getSavejButton(), null);
			jContentPane.add(jLabel21, null);
			jContentPane.add(getQuitjButton(), null);
			jContentPane.add(jLabel4, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes ParamjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getParamjButton() {
		if (ParamjButton == null) {
			ParamjButton = new JButton();
			ParamjButton.setLocation(new Point(348, 33));
			ParamjButton.setIcon(new ImageIcon(getClass().getResource("/parametres.png")));
			ParamjButton.setActionCommand("Fen_Param");
			ParamjButton.setFont(new Font("Dialog", Font.PLAIN, 12));
			ParamjButton.setSize(new Dimension(120, 120));
			ParamjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (e.getActionCommand().equals("Fen_Param")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new FEN_Param();
						}
				}
			});
		}
		return ParamjButton;
	}

	/**
	 * This method initializes SavejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSavejButton() {
		if (SavejButton == null) {
			SavejButton = new JButton();
			SavejButton.setBounds(new Rectangle(88, 228, 120, 120));
			
			SavejButton.setFont(new Font("Dialog", Font.PLAIN, 12));
			SavejButton.setIcon(new ImageIcon(getClass().getResource("/sauvegarde.png")));
			SavejButton.setActionCommand("Sauvegarde");
			SavejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (e.getActionCommand().equals("Sauvegarde")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new FEN_Sauvegarde();
						}
				}
			});
		}
		return SavejButton;
	}

	/**
	 * This method initializes ParamjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getParamjMenuItem() {
		if (ParamjMenuItem == null) {
			ParamjMenuItem = new JMenuItem();
			ParamjMenuItem.setText("Parametres");
			ParamjMenuItem.setIcon(new ImageIcon(getClass().getResource("/parametres_petit.png")));
			ParamjMenuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
			ParamjMenuItem.setActionCommand("Parametres");
			ParamjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (e.getActionCommand().equals("Parametres")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new FEN_Param();
						}
					}	
				
			});
		}
		return ParamjMenuItem;
	}

	/**
	 * This method initializes SauvegardejMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSauvegardejMenuItem() {
		if (SauvegardejMenuItem == null) {
			SauvegardejMenuItem = new JMenuItem();
			SauvegardejMenuItem.setText("Sauvegarde");
			SauvegardejMenuItem.setIcon(new ImageIcon(getClass().getResource("/sauvegarde_petit.png")));
			SauvegardejMenuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
			SauvegardejMenuItem.setActionCommand("Sauvegarde");
			SauvegardejMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (e.getActionCommand().equals("Sauvegarde")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new FEN_Sauvegarde();
						}
					}	
				
			});
		}
		return SauvegardejMenuItem;
	}

	/**
	 * This method initializes MailjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMailjMenuItem() {
		if (MailjMenuItem == null) {
			MailjMenuItem = new JMenuItem();
			MailjMenuItem.setText(" Envoyer un mail au support");
			MailjMenuItem.setActionCommand("Envoyer un mail au support");
			MailjMenuItem.setIcon(new ImageIcon(getClass().getResource("/mail_envoyer_petit.png")));
			MailjMenuItem.setFont(new Font("Dialog", Font.PLAIN, 12));
			MailjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if (e.getActionCommand().equals("Envoyer un mail au support")) {
						try {
							Historique.ecrire("Ouverture de : "+e);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new FEN_SendMail();
						}
					}
				
			});
		}
		return MailjMenuItem;
	}

	/**
	 * This method initializes QuitjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getQuitjButton() {
		if (QuitjButton == null) {
			QuitjButton = new JButton();
			QuitjButton.setBounds(new Rectangle(296, 228, 120, 120));
			QuitjButton.setIcon(new ImageIcon(getClass().getResource("/quitter.png")));
			QuitjButton.setText("");
			QuitjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					
					/////////////////////////////////////////////////////
					///// ENVOI FICHIER TRACE AU SUPPORT A LA FERMETURE//
					/////////////////////////////////////////////////////
					
					String [] destinataire = {"s.mardine@gmail.com"};
					String from = "carnet.client@laposte.net";
					String password = "gouranga08";
					String [] Files = {GestionRepertoire.RecupRepTravail()+"\\historique.txt",GestionRepertoire.RecupRepTravail()+"\\IniFile\\version.ini",GestionRepertoire.RecupRepTravail()+"\\IniFile\\AccesBdd.ini"};
					String Sujet = "Utilisation carnet client";
					
					String MACHINE_NAME=VariableEnvironement.VarEnvSystem("COMPUTERNAME");
					String USERNAME=VariableEnvironement.VarEnvSystem("USERNAME");
					
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
					
					
					
					
					String Message = "L'ordinateur "+ MACHINE_NAME + "à utilisé ce logiciel.\n\r" +
							"L'utilisateur qui a lancé le logiciel est : " + USERNAME +"\n\r" +
									"La version utlilisée est : "+VERSION;
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
					
					
					new Fen_Fermeture ();
					
				}
			});
		}
		return QuitjButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
