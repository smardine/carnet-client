package Dialogue;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import Thread.Thread_Sauvegarde;
import Utilitaires.Historique;
import Utilitaires.ManipFichier;
import Utilitaires.RecupDate;

public class FEN_Sauvegarde extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField EmplacementjTextField = null;
	private JButton ParcourirjButton = null;
	private JButton SauvegardejButton = null;
	private JProgressBar jProgressBar = null;
	private JLabel jLabel = null;

	/**
	 * This is the default constructor
	 */
	public FEN_Sauvegarde() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(632, 208);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sauvegarde.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Sauvegarde");
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
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(19, 130, 450, 24));
			jLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel.setText("");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getEmplacementjTextField(), null);
			jContentPane.add(getParcourirjButton(), null);
			jContentPane.add(getSauvegardejButton(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(jLabel, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes EmplacementjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getEmplacementjTextField() {
		if (EmplacementjTextField == null) {
			EmplacementjTextField = new JTextField();
			EmplacementjTextField.setBounds(new Rectangle(19, 23, 450, 27));
			EmplacementjTextField.setFont(new Font("Candara", Font.PLAIN, 12));
		}
		return EmplacementjTextField;
	}

	/**
	 * This method initializes ParcourirjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getParcourirjButton() {
		if (ParcourirjButton == null) {
			ParcourirjButton = new JButton();
			ParcourirjButton.setBounds(new Rectangle(488, 20, 117, 32));
			ParcourirjButton.setFont(new Font("Candara", Font.PLAIN, 12));
			ParcourirjButton.setText(" Parcourir");
			ParcourirjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String Date = RecupDate.dateEtHeure();
					String FileName = Date+"_CarnetClient.zip";
					
					String cheminSauvegarde = ManipFichier.SaveFile(FileName);
					EmplacementjTextField.setText(cheminSauvegarde);
					try {
						Historique.ecrire("Chemin de la sauvegarde : " +cheminSauvegarde);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return ParcourirjButton;
	}

	/**
	 * This method initializes SauvegardejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSauvegardejButton() {
		if (SauvegardejButton == null) {
			SauvegardejButton = new JButton();
			SauvegardejButton.setBounds(new Rectangle(488, 83, 117, 32));
			SauvegardejButton.setFont(new Font("Candara", Font.PLAIN, 12));
			SauvegardejButton.setText(" Sauvegarder");
			SauvegardejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String Emplacement = EmplacementjTextField.getText();
					if (Emplacement.equals("")==true){
						JOptionPane.showMessageDialog(null, "Erreur, veuillez spécifier le chemin de la sauvegarde", "Sauvegarde Impossible", JOptionPane.ERROR_MESSAGE);
						try {
							Historique.ecrire("Erreur, Aucun chemin defini pour la sauvegarde !! ");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else{
						Thread_Sauvegarde save = new Thread_Sauvegarde (Emplacement,jProgressBar,jLabel);
						save.start();	
						try {
							Historique.ecrire("Lancement de la sauvegarde ." );
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			});
		}
		return SauvegardejButton;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(19, 86, 450, 27));
			jProgressBar.setFont(new Font("Candara", Font.PLAIN, 12));
			jProgressBar.setStringPainted(true);
		}
		return jProgressBar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
