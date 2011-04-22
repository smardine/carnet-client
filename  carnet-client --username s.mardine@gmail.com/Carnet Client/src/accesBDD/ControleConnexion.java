package accesBDD;
//importation des classes pour JDBC

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ControleConnexion{
// PROPRIETES
static Parametres lesParametresEquinox;
static boolean etatConnexion;
static Connection laConnectionStatiqueEquinox;
	static {
	boolean ok = true;
	
	lesParametresEquinox = new Parametres();
	
	try{
		Class.forName("org.firebirdsql.jdbc.FBDriver");
		etatConnexion = true;
	}
	catch(ClassNotFoundException e){
	JOptionPane.showMessageDialog(null, "Classes non trouv�es"
	+ " pour le chargement du pilote Firebird",
	"ALERTE", JOptionPane.ERROR_MESSAGE);
	ok = false;
	etatConnexion = false;
	}

	if (ok == true){
		try {
		// r�cup�ration des param�tres pr�sents dans la classe Parametres
		//String urlBD = lesParametres.getServeurBD();
		String nomUtilisateur = lesParametresEquinox.getNomUtilisateur();
		String MDP = lesParametresEquinox.getMotDePasse();
		String DriverSGBD = lesParametresEquinox.getDriverSGBD();
		String Emplacement = lesParametresEquinox.getEmplacementBase();
		String Serveur = lesParametresEquinox.getHostName();
		
		
		String UrlDeConnexion = DriverSGBD+":"+Serveur+":"+Emplacement+"?encoding=ISO8859_1&user="+nomUtilisateur+"&password="+MDP;
			
		laConnectionStatiqueEquinox = DriverManager.getConnection(UrlDeConnexion);
		
		etatConnexion = true;
		}
	
		catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Impossible de se connecter" +
		" � la base de donn�es\n\r" + e,
		"ALERTE", JOptionPane.ERROR_MESSAGE);
		etatConnexion = false;
		JOptionPane.showMessageDialog(null, "     Pour des raisons de s�curit�, le programme va maintenant �tre ferm�" +
				" \n\r  il faudra le relancer et v�rifier les login, mot de passe, nom du serveur et chemin de la base de donn�es avant de lancer l'import",
				"ALERTE", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
		}
	}
}
// CONSTRUCTEUR
	public ControleConnexion(){
	}
// METHODES
// les accesseurs statiques
// ------------------------
	public static Parametres getParametres(){
	return lesParametresEquinox;
	}
	public static boolean getControleConnexion(){
	return etatConnexion;
	}
	public static Connection getConnexion(){
	return laConnectionStatiqueEquinox;
	}
// les autres m�thodes
// -------------------
	public static boolean controle(String Nom, String MotDePasse){
		boolean verificationSaisie;
		if (Nom.equals(lesParametresEquinox.getNomUtilisateur())&& MotDePasse.equals(lesParametresEquinox.getMotDePasse())){
		verificationSaisie = true;
		}
		else {
		JOptionPane.showMessageDialog(null, "V�rifier votre saisie.",
		"ERREUR", JOptionPane.ERROR_MESSAGE);
		verificationSaisie = false;
		}
		return verificationSaisie;
	}
	public static void fermetureSession(){
		try {
		laConnectionStatiqueEquinox.close();
		}
		catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Probl�me rencontr�" +
		"� la fermeture de la connexion",
		"ERREUR", JOptionPane.ERROR_MESSAGE);
		}
	}
	public boolean getControleSaisie() {
		// TODO Auto-generated method stub
		return true;
	}



}