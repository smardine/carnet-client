package Pop3;

 /**
 * ClientMail.java
 *
 *
 * Client simple pour serveur pop3
 *
 * @author ISSAMBN
 * pushmailp3a@gmail.com
 * comericsson
 */

 import javax.mail.*;

 import com.sun.mail.pop3.POP3SSLStore;
 import java.util.*;
 import java.io.*;

 public class ClientMail {

	 /** Dans cette chaine c'est le nom du serveur courier */

	 static String host = "pop.gmail.com";

	 public ClientMail() {
	 }


	 //public ClientMail(h String,lg String,pw String) {
	 //}
	 /*
	 * Méthode main , Fonction principale du client
	 */
	 public static void main(String args[])

	 {
	 /* Récupération des propriété du System */
	 Properties prop = System.getProperties();
	 System.out.println("Creation d'une session mail");
	 /* Creation d'un objet Session */
	 Session sess = Session.getDefaultInstance(prop, null);
	 sess.setDebug(true);

	 prop.list(System.out);

	 try {
		 /* No comment ;-) */
		 String user = "s.mardine";//pour le tester
		 String pwd = "gouranga08";//son mot de passe ,je ne suis pas redicule
		 //pour que j vous donner mon mot de passe mais je cree ce compte
		 //pour le teste seulement

		 /* Création de l'object qui va récupéré le contenu de la boite */
		// System.out.println("obtention d'un objet store");
		// Store st = sess.getStore("pop3");

		 /* Connection au serveur */
		 POP3SSLStore st = new POP3SSLStore(sess, new URLName(""));
		 st.connect(host, user, pwd);
		 System.out.println("connection ok");

		 System.out.println("st=:" + st);

		 System.out.println("Obtention d'un folder");

		/*
		* Ouverture du répertoire contenent les mails Par defaut INBOX
		*/
		 Folder f = st.getFolder("INBOX");
		 f.open(Folder.READ_ONLY);

		 /* Récupération des messages */
		 System.out.println("Obtention des messages");
		 Message msg[] = f.getMessages();
		 System.out.println("nombre de messages : " + f.getMessageCount());
		 System.out.println("nombre de nouveau messages : " + f.getNewMessageCount());
		 System.out.println("liste des nouveau messages");

		 for (int i = 0; i < msg.length; i++) {
			 if (msg[i].isMimeType("text/plain")) {
			 System.out.println("Expediteur: " + msg[i].getFrom()[0]);
			 System.out.println("Sujet: " + msg[i].getSubject());
			 System.out.println("Texte: " + (String) msg[i].getContent());
			 }
			System.out.println("fin des messages");
		 }

	 } /* fin de try */

	 /* Les exceptions */

	 catch (NoSuchProviderException e) {
		 System.out.println("Erreur sur le provider" + e.getMessage());
		 } catch (MessagingException e) {
		 System.out.println("Erreur sur Message" + e.getMessage());
		 } catch (IOException e) {
		 System.out.println("Erreur sur IO" + e.getMessage());
		 } catch (Exception e) {
		 System.out.println("Erreur indetermiee" + e.getMessage());
		 }
	 } /* fin de main */

 }