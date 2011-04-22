package Dialogue;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Utilitaires.GestionRepertoire;
import Utilitaires.Historique;

public class FEN_SendMail extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField ObjetMessagejTextField = null;
	private JLabel jLabel = null;
	private JTextArea MessagejTextArea = null;
	private JLabel jLabel1 = null;
	private JButton EnvoyerjButton = null;
	/**
	 * This is the default constructor
	 */
	public FEN_SendMail() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(537, 382);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/mail_envoyer.png")));
		this.setContentPane(getJContentPane());
		this.setTitle("Envoi Mail");
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
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(13, 76, 180, 21));
			jLabel1.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel1.setText(" Message");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(13, 14, 177, 23));
			jLabel.setFont(new Font("Candara", Font.PLAIN, 12));
			jLabel.setText(" Objet du message");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getObjetMessagejTextField(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getMessagejTextArea(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(getEnvoyerjButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes ObjetMessagejTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getObjetMessagejTextField() {
		if (ObjetMessagejTextField == null) {
			ObjetMessagejTextField = new JTextField();
			ObjetMessagejTextField.setBounds(new Rectangle(13, 39, 493, 29));
			ObjetMessagejTextField.setFont(new Font("Candara", Font.PLAIN, 12));
		}
		return ObjetMessagejTextField;
	}

	/**
	 * This method initializes MessagejTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getMessagejTextArea() {
		if (MessagejTextArea == null) {
			MessagejTextArea = new JTextArea();
			MessagejTextArea.setBounds(new Rectangle(13, 99, 493, 160));
			MessagejTextArea.setFont(new Font("Candara", Font.PLAIN, 12));
		}
		return MessagejTextArea;
	}

	/**
	 * This method initializes EnvoyerjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEnvoyerjButton() {
		if (EnvoyerjButton == null) {
			EnvoyerjButton = new JButton();
			EnvoyerjButton.setBounds(new Rectangle(187, 276, 145, 50));
			EnvoyerjButton.setIcon(new ImageIcon(getClass().getResource("/mail_envoyer.png")));
			EnvoyerjButton.setHorizontalTextPosition(SwingConstants.RIGHT);
			EnvoyerjButton.setFont(new Font("Candara", Font.PLAIN, 12));
			EnvoyerjButton.setText(" Envoyer");
			EnvoyerjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String Sujet = ObjetMessagejTextField.getText();
					String Message = MessagejTextArea.getText();
					
					if ((Sujet.equals("")==true)&&(Message.equals("")==true)){
						JOptionPane.showMessageDialog(null, "Veuillez rentrer au moins un sujet au message", "Erreur", JOptionPane.WARNING_MESSAGE);
					}else {
						
						String [] destinataire = {"s.mardine@gmail.com"};
						String from = "carnet.client@laposte.net";
						String password = "gouranga08";
						String [] Files = {GestionRepertoire.RecupRepTravail()+"\\historique.txt",GestionRepertoire.RecupRepTravail()+"\\IniFile\\version.ini",GestionRepertoire.RecupRepTravail()+"\\IniFile\\AccesBdd.ini"};
						
						SendMailUsingAuthenticationWithAttachement smtpMailSender = new SendMailUsingAuthenticationWithAttachement();
					    boolean succesEnvoiMail = false;
						try {
							succesEnvoiMail = smtpMailSender.postMail( destinataire, ObjetMessagejTextField.getText(), MessagejTextArea.getText(), from, password, Files);
						} catch (MessagingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					    
					    if (succesEnvoiMail==true){
					    	JOptionPane.showMessageDialog(null, "Message Envoyé avec succes", "Ok", JOptionPane.INFORMATION_MESSAGE);
					    	try {
								Historique.ecrire("Erreur lors de l'envoi du mail ");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    }
					}
					
				}
			});
		}
		return EnvoyerjButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
